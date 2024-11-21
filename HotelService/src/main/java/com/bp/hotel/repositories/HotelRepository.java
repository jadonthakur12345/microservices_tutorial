package com.bp.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bp.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {
	
	
}
