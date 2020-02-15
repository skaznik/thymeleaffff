package com.example.thymeleaf.skierowanie.controller;

import com.example.thymeleaf.skierowanie.dao.MiejsceDAO;
import com.example.thymeleaf.skierowanie.dao.PracownikDAO;
import com.example.thymeleaf.skierowanie.dao.SkierowanieDoLekarzaDao;
import com.example.thymeleaf.skierowanie.dto.SkierowanieDoLekarzaDTO;
import com.example.thymeleaf.skierowanie.model.Miejsce;
import com.example.thymeleaf.skierowanie.model.Pracownik;
import com.example.thymeleaf.skierowanie.model.SkierowanieDoLekarza;
import com.example.thymeleaf.skierowanie.service.SkierowanieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/skierowanie")
public class SkierowanieController {

    @Autowired
    SkierowanieDoLekarzaDao skierowanieDoLekarzaDao;
    @Autowired
    MiejsceDAO miejsceDAO;
    @Autowired
    PracownikDAO pracownikDAO;

    SkierowanieService service;

    public SkierowanieController(SkierowanieService service) {
        this.service = service;
    }
    @ResponseBody
    @GetMapping("/test")
public List<SkierowanieDoLekarza> test() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
return skierowanieDoLekarzaDao
        //.findAllByPacjentAndTerminOrderById("sadfasfdas",dateFormat.parse("2020-02-14"));
        //.test();
        .cwiczenie("AZ");
}
@ResponseBody
@GetMapping("/test2")
public Miejsce test2() throws ParseException {
    Miejsce miejsce = new Miejsce();
    miejsce.setAdres("Kulkowo 28");
    miejsce.setKodPocztowy("43-100");
    miejsce.setMiasto("Katowice");
    miejsce = miejsceDAO.save(miejsce);

    SkierowanieDoLekarza skierowanieDoLekarza = skierowanieDoLekarzaDao.findById(5).get();
    skierowanieDoLekarza.setMiejsce(miejsce);
    skierowanieDoLekarza = skierowanieDoLekarzaDao.save(skierowanieDoLekarza);
   // miejsce = miejsceDAO.findById(miejsce.getId()).get();
   // miejsce.getSkierowanieDoLekarzas().size();
    List<Pracownik> pracowniks = new ArrayList<>();
    for (int i = 0; i < 5; ++i) {
        Pracownik pracownik = new Pracownik();
        pracownik.setImie("kiba" + i);
        pracownik = pracownikDAO.save(pracownik);
        pracowniks.add(pracownik);
    }
    miejsce.setPracowniks(pracowniks);
    miejsce = miejsceDAO.save(miejsce);
    return miejsce;
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
