package com.launchacademy.javastarships.controllers;


import com.launchacademy.javastarships.models.StarShip;
import com.launchacademy.javastarships.services.StarShipService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/starships")
public class ShipsController {
  @Autowired
  private StarShipService service;

  @GetMapping
  public String listStarShips(Model model) {
    List<StarShip> starships = service.findAll();
    model.addAttribute("starships", starships);
    return "starships/index";
  }

  @GetMapping("/{starshipId}")
  public String showStarship(@PathVariable Integer starshipId, Model model) {
    StarShip starship = service.get(starshipId);
    model.addAttribute("starship", starship);
    return "starships/show";
  }

  @GetMapping("/new")
  public String newForm(@ModelAttribute StarShip starship) {
    return "starships/new";
  }

  @PostMapping
  public String getStarShip(@ModelAttribute StarShip starShip) {
    starShip.setId(service.findAll().size() + 1);
    service.addToList(starShip);
    return "redirect:/starships/" + starShip.getId();
  }

}
