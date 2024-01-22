package receipt.processor.challenge.receiptProcessorChallenge;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

//Receipt model class
public class Receipt {
 // Define fields for retailer, purchaseDate, purchaseTime, items, total
	
	 	@JsonProperty("retailer")
	    private String retailer;

	    @JsonProperty("purchaseDate")
	    @JsonFormat(pattern = "yyyy-MM-dd")
	    private String purchaseDate;

	    @JsonProperty("purchaseTime")
	    @JsonFormat(pattern = "HH:mm")
	    private String purchaseTime;

	    @JsonProperty("items")
	    private List<Items> items;

	    @JsonProperty("total")
	    private String total;

		public String getRetailer() {
			return retailer;
		}

		public void setRetailer(String retailer) {
			this.retailer = retailer;
		}

		public String getPurchaseDate() {
			return purchaseDate;
		}

		public void setPurchaseDate(String purchaseDate) {
			this.purchaseDate = purchaseDate;
		}

		public String getPurchaseTime() {
			return purchaseTime;
		}

		public void setPurchaseTime(String purchaseTime) {
			this.purchaseTime = purchaseTime;
		}

		public List<Items> getItems() {
			return items;
		}

		public void setItems(List<Items> items) {
			this.items = items;
		}

		public String getTotal() {
			return total;
		}

		public void setTotal(String total) {
			this.total = total;
		}

	@Override
	public String toString() {
		return "Receipt{" +
				"retailer='" + retailer + '\'' +
				", purchaseDate='" + purchaseDate + '\'' +
				", purchaseTime='" + purchaseTime + '\'' +
				", items=" + items +
				", total='" + total + '\'' +
				'}';
	}
}