package com.hotel.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequestDto {

    private Long userId;

    private Integer places;

    private String roomClass;

    private LocalDate checkIn;

    private LocalDate checkOut;
}