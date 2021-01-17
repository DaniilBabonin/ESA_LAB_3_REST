package com.example.demo.repository;

import com.example.demo.entity.Cat;
import org.springframework.data.repository.CrudRepository;

public interface CatRepos extends CrudRepository <Cat, String> {

}
