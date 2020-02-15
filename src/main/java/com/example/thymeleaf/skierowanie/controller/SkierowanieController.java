package com.example.thymeleaf.skierowanie.controller;

import com.example.thymeleaf.skierowanie.dao.SkierowanieDoLekarzaDao;
import com.example.thymeleaf.skierowanie.dto.SkierowanieDoLekarzaDTO;
import com.example.thymeleaf.skierowanie.model.SkierowanieDoLekarza;
import com.example.thymeleaf.skierowanie.service.SkierowanieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/skierowanie")
public class SkierowanieController {

    @Autowired
    SkierowanieDoLekarzaDao skierowanieDoLekarzaDao;
    SkierowanieService service;

    public SkierowanieController(SkierowanieService service) {
        this.service = service;
    }
@GetMapping("/test")
public List<SkierowanieDoLekarza> test()
    {
return skierowanieDoLekarzaDao.findAllByPacjentAndTerminOrderById("sadfasfdas",new Date());
}
    @GetMapping("/list") // /skierowanie/list -> list-skierowanie.html
    public String listSkierowanie(Model model) {
        model.addAttribute("skierowania", service.listSkierowanie());
        return "list-skierowanie";
    }

    @GetMapping("/{id}")
    public String getSkierowanie(@PathVariable Integer id, Model model) {
        SkierowanieDoLekarzaDTO skierowanieDoLekarza = service.getSkierowanie(id);
        model.addAttribute("skierowanie", skierowanieDoLekarza);
        return "get-skierowanie";
    }

    @GetMapping("/dodaj")
    public String dodajSkierowanie(Model model) {
        model.addAttribute("skierowanie", new SkierowanieDoLekarzaDTO());
        return "dodaj-skierowanie";
    }

    @PostMapping("/dodaj")
    public String stworzSkierowanie(
            @Valid
            @ModelAttribute SkierowanieDoLekarzaDTO skierowanieDoLekarza,
            BindingResult bindingResult,
            Model model
            ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("skierowanie", skierowanieDoLekarza);
            return "dodaj-skierowanie";
        }

        service.createSkierowanie(skierowanieDoLekarza);
        return "redirect:/skierowanie/list";
    }

    @GetMapping("/modyfikuj/{id}")
    public String modyfikujSkierowanie(@PathVariable Integer id, Model model) {
        model.addAttribute("skierowanie", service.getSkierowanie(id));
        return "modifikuj-skierowanie";
    }

    @PostMapping("/modyfikuj")
    public String updateSkierowanie(
            @Valid
            @ModelAttribute SkierowanieDoLekarzaDTO skierowanieDoLekarza,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("skierowanie", skierowanieDoLekarza);
            return "modifikuj-skierowanie";
        }
        service.updateSkierowanie(skierowanieDoLekarza);
        return String.format("redirect:/skierowanie/%d", skierowanieDoLekarza.getId());
    }

    @GetMapping("/usun/{id}")
    public String usunSkierowanie(@PathVariable Integer id) {
        service.deleteSkierowanie(id);
        return "redirect:/skierowanie/list";
    }

}
