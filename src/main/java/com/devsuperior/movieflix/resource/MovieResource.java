package com.devsuperior.movieflix.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

	@Autowired
	private MovieService movieService;

	@Autowired
	private ReviewService reviewService;

	@GetMapping
	public ResponseEntity<Page<MovieGenreDTO>> findAll(Pageable pageable,
			@RequestParam(name = "genreId", defaultValue = "0L") Long genreId) {
		Page<MovieGenreDTO> movies = movieService.findAll(pageable, genreId);
		return ResponseEntity.ok(movies);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(movieService.findById(id));
	}

	@GetMapping(value = "/{movieId}/reviews")
	public ResponseEntity<List<ReviewDTO>> findMovieReviews(@PathVariable Long movieId) {
		List<ReviewDTO> dto = reviewService.findByMovie(movieId);
		return ResponseEntity.ok(dto);
	}
}
