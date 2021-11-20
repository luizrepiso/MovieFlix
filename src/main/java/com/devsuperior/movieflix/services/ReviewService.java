package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private AuthService authService;

	
	@Transactional(readOnly = true)
	Page<ReviewDTO> notificationsForCurrentUser(Pageable pageable){
		User user = authService.authenticated();
		Page<Review> page = repository.findByUser(user, pageable);
		return page.map(x -> new ReviewDTO(x));
		
	}
}
		