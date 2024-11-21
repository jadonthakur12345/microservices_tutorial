package com.bp.hotel.services;

import java.util.List;

import com.bp.hotel.entities.Hotel;

public interface HotelService {
	
	//create
	Hotel create(Hotel hotel);
	
	
	//getall
	List<Hotel> getAll();
	
	
	//get one
	Hotel get(String id);
	
}
