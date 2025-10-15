package Flys.dto;

public class Flight {
    private FlightDetails outbound;
    private FlightDetails inbound;

    // Getters and setters
    public FlightDetails getOutbound() {
        return outbound;
    }

    public void setOutbound(FlightDetails outbound) {
        this.outbound = outbound;
    }

    public FlightDetails getInbound() {
        return inbound;
    }

    public void setInbound(FlightDetails inbound) {
        this.inbound = inbound;
    }
}