package com.user.service.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.external.service.HotelService;
import com.user.service.reporsitories.UserRepository;
import com.user.service.service.UserService;
import com.user.service.service.exception.ResourceNotFoundException;

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
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public List<User> geAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		
		// TODO Auto-generated method stub
		
		//get user details by id
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with given id is not found in server id : "+userId));
		
		//fetch rating detail
		//http://localhost:8083/ratings/users/f4056abd-ee39-48b9-9ce8-ceaa2f2cb9f1
		
		Rating[] forObject = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
//		logger.info("{} ",forObject);
		List<Rating> ratings = Arrays.stream(forObject).toList();
//		user.setRatings(ratings);
		
		List<Rating> ratingList = ratings.stream().map(rating->{
			//http://localhost:8082/hotels/f9d6b1f2-5c14-4c6f-985a-4a6cd0456891
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		return user;
	}

}
