package Flys.Controllers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import Flys.dto.ItineraryResponse;

@Component
public class InMemoryCache {
    private final ConcurrentHashMap<String, CacheEntry> cache = new ConcurrentHashMap<>();
    
    public void put(String key, ItineraryResponse value, Duration ttl) {
        LocalDateTime expiry = LocalDateTime.now().plus(ttl);
        cache.put(key, new CacheEntry(value, expiry));
    }
    
    public ItineraryResponse get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry == null || entry.isExpired()) {
            cache.remove(key);
            return null;
        }
        return entry.getValue();
    }
    
    private static class CacheEntry {
        private final ItineraryResponse value;
        private final LocalDateTime expiry;
        
        public CacheEntry(ItineraryResponse value, LocalDateTime expiry) {
            this.value = value;
            this.expiry = expiry;
        }
        
        public ItineraryResponse getValue() {
            return value;
        }
        
        public boolean isExpired() {
            return LocalDateTime.now().isAfter(expiry);
        }
    }
}