package receipt.processor.challenge.receiptProcessorChallenge;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//ReceiptService class
@Service
public class ReceiptService {
 public int processReceipt(Receipt receipt) {
     // Implement logic to calculate points based on the specified rules
     // ...
//     return calculatedPoints;
     int retailerPoints = getAlphanumericValue(receipt.getRetailer());
     System.out.println("retailerPoints");
     System.out.println(retailerPoints);

     int amountPoints = isRoundDollarAmount(receipt.getTotal());
     System.out.println("amountPoints");
     System.out.println(amountPoints);

     int itemPoints = calculatePointsForItems(receipt.getItems());
     System.out.println("itemPoints");
     System.out.println(itemPoints);

     int shortDescriptionPoints = calculatePointsForShortDescription(receipt.getItems());
     System.out.println("shortDescriptionPoints");
     System.out.println(shortDescriptionPoints);

     int calculatePointsForPurchaseDate = calculatePointsForPurchaseDate(receipt.getPurchaseDate());
     System.out.println("calculatePointsForPurchaseDate");
     System.out.println(calculatePointsForPurchaseDate);

     int calculatePointsForPurchaseTime = calculatePointsForPurchaseTime(receipt.getPurchaseTime());
     System.out.println("calculatePointsForPurchaseTime");
     System.out.println(calculatePointsForPurchaseTime);

     int totalPoints = retailerPoints + amountPoints + itemPoints + shortDescriptionPoints + calculatePointsForPurchaseDate + calculatePointsForPurchaseTime;

     return totalPoints;
 }

    public int getAlphanumericValue(String input) {
        // One point for every alphanumeric character in the retailer name.
        // Use regular expression to filter out non-alphanumeric characters
        String alphanumericValue = input.replaceAll("[^a-zA-Z0-9]", "");
        return alphanumericValue.length();
    }

    public int isRoundDollarAmount(String total) {
    int totalPoints;
        // Convert the total to a double
        double totalAmount = Double.parseDouble(total);

        // Check if the total is a round dollar amount with no cents
        boolean isRoundDollarAmount = totalAmount == Math.floor(totalAmount);
        if (isRoundDollarAmount == true ){
            totalPoints = 50;
        } else{
            totalPoints = 0;
        }
        // Check if the total is a multiple of 0.25
        if (totalAmount % 0.25 ==0){
            return 25+ totalPoints;
        } else {
            return totalPoints;
        }

    }

    private int calculatePointsForItems(List<Items> items) {
        // Calculate 5 points for every two items on the receipt
        int numberOfItems = items.size();
        int pointsForItems = (numberOfItems / 2) * 5;

        return pointsForItems;
    }

    private int calculatePointsForShortDescription(List<Items> items) {
        int pointsForShortDescription = 0;

        for (Items item : items) {
            String shortDescription = item.getShortDescription().trim();

            // Check if the trimmed length of shortDescription is a multiple of 3
            if (shortDescription.length() % 3 == 0) {
                // Multiply the price by 0.2 and round up to the nearest integer
                BigDecimal price = new BigDecimal(item.getPrice());
                BigDecimal points = price.multiply(BigDecimal.valueOf(0.2));
                points = points.setScale(0, RoundingMode.UP);

                pointsForShortDescription += points.intValue();
            }
        }

        return pointsForShortDescription;
    }

    private int calculatePointsForPurchaseDate(String purchaseDate) {
        // Assuming purchaseDate is in the format "yyyy-MM-dd"
        LocalDate date = LocalDate.parse(purchaseDate);

        // Check if the day in the purchase date is odd
        if (date.getDayOfMonth() % 2 != 0) {
            // Return 6 points
            return 6;
        } else {
            return 0;
        }
    }

    private int calculatePointsForPurchaseTime(String purchaseTime) {
        // Assuming purchaseTime is in the format "HH:mm"
        LocalTime time = LocalTime.parse(purchaseTime);

        // Check if the time of purchase is after 2:00pm and before 4:00pm
        if (time.isAfter(LocalTime.parse("14:00")) && time.isBefore(LocalTime.parse("16:00"))) {
            // Return 10 points
            return 10;
        } else {
            return 0;
        }
    }

}