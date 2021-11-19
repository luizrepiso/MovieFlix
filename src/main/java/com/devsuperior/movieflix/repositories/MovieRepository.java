package com.devsuperior.movieflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.movieflix.entities.Role;

public interface MovieRepository extends JpaRepository<Role, Long>{
}
