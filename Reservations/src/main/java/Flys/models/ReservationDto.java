package Flys.models;

public class ReservationDto {
    private String reservationId;
    private String status;
    private String hotelConfirmation;
    private Money money;
    
    public ReservationDto() {
    }
    public ReservationDto(String reservationId, String status, String hotelConfirmation, Money money) {
        this.reservationId = reservationId;
        this.status = status;
        this.hotelConfirmation = hotelConfirmation;
        this.money = money;
    }
    public String getReservationId() {
        return reservationId;
    }
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getHotelConfirmation() {
        return hotelConfirmation;
    }
    public void setHotelConfirmation(String hotelConfirmation) {
        this.hotelConfirmation = hotelConfirmation;
    }
    public Money getMoney() {
        return money;
    }
    public void setMoney(Money money) {
        this.money = money;
    }



}
