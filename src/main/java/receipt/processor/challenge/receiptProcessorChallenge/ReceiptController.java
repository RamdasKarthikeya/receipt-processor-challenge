package receipt.processor.challenge.receiptProcessorChallenge;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//ReceiptController class
@RestController
@RequestMapping("/receipts")
public class ReceiptController {
 @Autowired
 private ReceiptService receiptService;

 @Autowired
 private MemoryService memoryService;

 @PostMapping("/process")
 public ResponseEntity<Map<String, String>> processReceipt(@RequestBody Receipt receipt) {
     // Call the service to process the receipt
     int points = receiptService.processReceipt(receipt);
     String receiptId = UUID.randomUUID().toString();
     memoryService.storeValue(receiptId, points);
     Map<String, String> response = Collections.singletonMap("id", receiptId);
     return ResponseEntity.ok(response);
 }

 @GetMapping("/{id}/points")
 public ResponseEntity<Map<String, Object>> getPoints(@PathVariable String id) {
     // Implement logic to retrieve points based on receipt ID
     // ...
     memoryService.retrieveValue(id);

     Map<String, Object> responseNew = Collections.singletonMap("points", memoryService.retrieveValue(id));
     return ResponseEntity.ok(responseNew);
 }
}