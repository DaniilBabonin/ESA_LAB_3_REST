package com.example.demo.repository;

import com.example.demo.entity.Breed;
import org.springframework.data.repository.CrudRepository;

public interface BreedRepos extends CrudRepository <Breed, Long> {

}
