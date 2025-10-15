package Flys.models;

public class FlightRef {
    private String pnr;
    private String carrier;
    private Boolean ticketing;

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Boolean getTicketing() {
        return ticketing;
    }

    public void setTicketing(Boolean ticketing) {
        this.ticketing = ticketing;
    }

    public FlightRef(String pnr, String carrier, Boolean ticketing) {
        this.pnr = pnr;
        this.carrier = carrier;
        this.ticketing = ticketing;
    }

    
}
