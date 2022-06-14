package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	
	public Page<MovieGenreDTO> findAll(Pageable pageable, Long genreId) {
		if(genreId != null) {
			Pageable sortedByTitle = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"));
			Page<Movie> movies = repository.findAllOrGenreId(pageable.getSort().isSorted() ? pageable : sortedByTitle,
					genreId);
			return movies.map(x -> new MovieGenreDTO(x));			
		}else {
			Pageable sortedByTitle = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"));
			Page<Movie> movies = repository.findAll(pageable.getSort().isSorted() ? pageable : sortedByTitle);
			return movies.map(x -> new MovieGenreDTO(x));	
		}
	}

	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
		return new MovieDTO(entity);
		
	}
	
	
}

	
	

