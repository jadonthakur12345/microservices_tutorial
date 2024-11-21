package com.bp.rating.services;

import java.util.List;

import com.bp.rating.entities.Rating;

public interface RatingService {
	
	
	//create
	Rating create(Rating rating);
	
	
	//get all ratings
	List<Rating> getRatings();
	
	
	
	//get all by UserId
	List<Rating> getRatingByUserId(String userId);
	
	
	//get all by HotelId
	List<Rating> getRatingByHotelID(String hotelId);
	
	
}
