package com.bp.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bp.user.service.entities.Hotel;
import com.bp.user.service.entities.Rating;
import com.bp.user.service.entities.User;
import com.bp.user.service.exceptions.ResourceNotFoundException;
import com.bp.user.service.external.services.HotelService;
import com.bp.user.service.repositories.UserRepository;
import com.bp.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server !!: " + userId));
		// fetch rating of the above user from rating service.
		// http://localhost:8083/ratings/user/402d4729-ba73-4877-8b16-caf9652606ae
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" + userId,
				Rating[].class);
		logger.info("{} ", ratingsOfUser);
		List<Rating> ratings=Arrays.stream(ratingsOfUser).collect(Collectors.toList());
		// to map hotels with ratings
		List<Rating> ratingList = ratings.stream().map(rating -> {
			// api call to hotelservice to get the hotel
			// http://localhost:8082/hotels/17a6917a-9a00-4b76-85e8-8881c62d95091
			//ResponseEntity<Hotel> forEntity = restTemplate
			//		.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
			//Hotel hotel= forEntity.getBody();
			Hotel hotel= hotelService.getHotel(rating.getHotelId());
			// set the hotel to rating
			rating.setHotel(hotel);
			// return the rating
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingList);
		return user;
	}

}
