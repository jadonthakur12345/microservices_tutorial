package com.bp.rating.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bp.rating.entities.Rating;
import com.bp.rating.repository.RatingRepository;
import com.bp.rating.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating create(Rating rating) {
		// TODO Auto-generated method stub
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelID(String hotelId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByHotelId(hotelId);
	}

}
