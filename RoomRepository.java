package com.hotel.repository;

import com.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findFirstByCapacityGreaterThanEqualAndRoomClassAndStatus(
            Integer capacity,
            String roomClass,
            String status
    );
}