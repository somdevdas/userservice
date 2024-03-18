package com.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.service.entity.Hotel;
import com.user.service.external.service.HotelService;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private HotelService service;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void createHotel() {
		Hotel hotel = Hotel.builder().id("").name("Laxmi hotel").about("5 star hotel").location("pune").build();
		service.createHotel(hotel);
	}

}
