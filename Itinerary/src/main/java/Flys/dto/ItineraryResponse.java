package Flys.dto;

import java.util.List;

public class ItineraryResponse {
    private List<Itinerary> itineraries;
    private String searchId;

    // Getters and setters
    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }
}