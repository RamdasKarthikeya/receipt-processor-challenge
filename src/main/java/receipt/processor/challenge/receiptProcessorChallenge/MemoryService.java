package receipt.processor.challenge.receiptProcessorChallenge;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemoryService {

    private final Map<String, Integer> inMemoryStore = new HashMap<>();

    public void storeValue(String key, int value) {
        inMemoryStore.put(key, value);
    }

    public Integer retrieveValue(String key) {
        return inMemoryStore.get(key);
    }
}
