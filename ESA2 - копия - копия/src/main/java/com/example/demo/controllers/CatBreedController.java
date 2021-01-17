package com.example.demo.controllers;

import com.example.demo.entity.Cat;
import com.example.demo.repository.CatRepos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatBreedController {
    @Autowired
    private CatRepos catRepos;

    @GetMapping("/tables")
    public String tableMain(Model model) {
        Iterable <Cat> cats = catRepos.findAll();
        model.addAttribute("cats", cats);
        return "page";
    }
}
