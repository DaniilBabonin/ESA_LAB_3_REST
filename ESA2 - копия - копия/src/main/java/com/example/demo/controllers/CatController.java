package com.example.demo.controllers;


import com.example.demo.entity.Breed;
import com.example.demo.entity.Cat;
import com.example.demo.repository.BreedRepos;
import com.example.demo.repository.CatRepos;
import org.hibernate.event.spi.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import response.BadResponse;
import response.GoodResponse;
import response.ServerResponse;

@RestController
public class CatController
{
    private final CatRepos catRepos;
    private final BreedRepos breedRepos;

    @Autowired
    public CatController(CatRepos catRepos, BreedRepos breedRepos) {
        this.catRepos = catRepos;
        this.breedRepos = breedRepos;
    }


    @GetMapping(path = "/cat", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Iterable<Cat> findAll()
    {
        return catRepos.findAll();
    }

    @GetMapping(path = "/add/cat", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse add(String name, String color, int age, long breed_id)
    {
        Breed breed = breedRepos.findById(breed_id).orElse(null);
        if (breed == null)
        {
            return new BadResponse("Breed not found");
        }
        Cat newCat = new Cat();
        newCat.setName(name);
        newCat.setColor(color);
        newCat.setAge(age);
        newCat.setBreed(breed);
        return new GoodResponse(newCat);
    }

    @GetMapping(path = "/delete/cat", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse delete(String name){
        Cat cat = catRepos.findById(name).orElse(null);
        if (cat == null)
        {
            return new BadResponse("Cat not found");
        }
        catRepos.delete(cat);
        return new GoodResponse();
    }
}

