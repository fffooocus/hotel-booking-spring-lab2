package com.hotel.service;

import com.hotel.dto.BookingRequestDto;
import com.hotel.entity.Booking;
import com.hotel.entity.Invoice;
import com.hotel.entity.Room;
import com.hotel.entity.User;
import com.hotel.repository.BookingRepository;
import com.hotel.repository.InvoiceRepository;
import com.hotel.repository.RoomRepository;
import com.hotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final InvoiceRepository invoiceRepository;

    public Booking createBooking(BookingRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = Booking.builder()
                .user(user)
                .places(dto.getPlaces())
                .roomClass(dto.getRoomClass())
                .checkIn(dto.getCheckIn())
                .checkOut(dto.getCheckOut())
                .status("PENDING")
                .build();

        return bookingRepository.save(booking);
    }

    public Booking approveBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Room room = roomRepository
                .findFirstByCapacityGreaterThanEqualAndRoomClassAndStatus(
                        booking.getPlaces(),
                        booking.getRoomClass(),
                        "AVAILABLE"
                )
                .orElseThrow(() -> new RuntimeException("Available room not found"));

        booking.setRoom(room);
        booking.setStatus("APPROVED");

        room.setStatus("BOOKED");
        roomRepository.save(room);

        long days = ChronoUnit.DAYS.between(
                booking.getCheckIn(),
                booking.getCheckOut()
        );

        BigDecimal amount = room.getPricePerNight()
                .multiply(BigDecimal.valueOf(days));

        Invoice invoice = Invoice.builder()
                .booking(booking)
                .amount(amount)
                .paid(false)
                .build();

        invoiceRepository.save(invoice);

        return bookingRepository.save(booking);
    }
}