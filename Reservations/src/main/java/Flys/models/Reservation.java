package Flys.models;

public class Reservation {
    private String itineraryId;
    private FlightRef flightRef;
    private Guest guest;
    private Contact contact;
    private Payment payment;

    public String getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(String itineraryId) {
        this.itineraryId = itineraryId;
    }

    public FlightRef getFlightRef() {
        return flightRef;
    }

    public void setFlightRef(FlightRef flightRef) {
        this.flightRef = flightRef;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Reservation() {}

    public Reservation(String itineraryId, FlightRef flightRef, Guest guest, Contact contact, Payment payment) {
        this.itineraryId = itineraryId;
        this.flightRef = flightRef;
        this.guest = guest;
        this.contact = contact;
        this.payment = payment;
    }

}
