package com.example.demo.controllers;

import com.example.demo.entity.Breed;
import com.example.demo.repository.BreedRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import response.BadResponse;
import response.GoodResponse;
import response.ServerResponse;

@RestController
public class BreedController
{

    private final BreedRepos breedRepos;




    @Autowired
    public BreedController(BreedRepos breedRepos) {
        this.breedRepos = breedRepos;

    }


    @GetMapping(path = "/breed", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Iterable<Breed> findAll()
    {
        return breedRepos.findAll();
    }

    @GetMapping(path = "/add/breed", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse add(String breedName)
    {
        Breed breed = new Breed();
        breed.setBreedName(breedName);
        Breed newbreed = breedRepos.save(breed);
        return new GoodResponse(newbreed);
    }

    @GetMapping(path = "/delete/breed", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse delete(Long id){
        Breed breed = breedRepos.findById(id).orElse(null);
        if (breed == null)
        {
            return new BadResponse("Breed not found");
        }
        breedRepos.delete(breed);
        return new GoodResponse();
    }
}
