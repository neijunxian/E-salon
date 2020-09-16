package com.example.assigment.Modal;

public class Booking {
    private String BookingDate, BookingTime, BookingService, BookingId;

    public Booking(String bookingDate, String bookingTime, String bookingService, String bookingId) {
        this.BookingDate = bookingDate;
        this.BookingTime = bookingTime;
        this.BookingService = bookingService;
        this.BookingId = bookingId;
    }

    public Booking(){

    }
    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.BookingDate = bookingDate;
    }

    public String getBookingTime() {
        return BookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.BookingTime = bookingTime;
    }

    public String getBookingService() {
        return BookingService;
    }

    public void setBookingService(String bookingService) {
        this.BookingService = bookingService;
    }

    public String getBookingId() {
        return BookingId;
    }

    public void setBookingId(String bookingId) {
        this.BookingId = bookingId;
    }
}
