package com.hotel.controller;

import com.hotel.dto.BookingRequestDto;
import com.hotel.entity.Booking;
import com.hotel.repository.BookingRepository;
import com.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequestDto dto) {
        return bookingService.createBooking(dto);
    }

    @PutMapping("/{id}/approve")
    public Booking approveBooking(@PathVariable Long id) {
        return bookingService.approveBooking(id);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}