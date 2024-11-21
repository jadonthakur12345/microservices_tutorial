package com.bp.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bp.user.service.entities.Rating;
import com.bp.user.service.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	/*
	 * @Autowired private RatingService ratingService;
	 * 
	 * @Test void createRating() { Rating rating= new Rating("", "", "", 10,
	 * "This is created using feign client", null); Rating
	 * savedRating=ratingService.createRating(rating);
	 * System.out.println("new rating created");
	 * 
	 * }
	 */
}
