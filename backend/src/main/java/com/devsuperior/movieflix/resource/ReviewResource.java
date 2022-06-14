package com.devsuperior.movieflix.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
	@RequestMapping(value = "/reviews")
	public class ReviewResource {

		@Autowired
		private ReviewService reviewService;
		
		@PostMapping
		public ResponseEntity<ReviewDTO> insert(@RequestBody @Valid  ReviewDTO dto){
			ReviewDTO reviewDTO = reviewService.insert(dto);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
			return ResponseEntity.created(uri).body(reviewDTO);
		}

}
