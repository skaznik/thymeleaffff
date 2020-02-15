package com.example.thymeleaf.skierowanie.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test_miejsce")
public class Miejsce {

    @Id
    @GeneratedValue
    Integer id;

    String adres;

    String kodPocztowy;

    String miasto;
    @Transient
    String dyskoteka;

    @OneToMany
    @JoinColumn
    List<Pracownik>pracowniks = new ArrayList<>();

    public List<Pracownik> getPracowniks() {
        return pracowniks;
    }

    public void setPracowniks(List<Pracownik> pracowniks) {
        this.pracowniks = pracowniks;
    }

    // @OneToMany(mappedBy = "miejsce")
    //List<SkierowanieDoLekarza> skierowanieDoLekarzas;

    public Miejsce() {

    }

    public Miejsce(String adres, String kodPocztowy, String miasto, String dyskoteka) {
        this.adres = adres;
        this.kodPocztowy = kodPocztowy;
        this.miasto = miasto;
        this.dyskoteka = dyskoteka;
    }

//    public List<SkierowanieDoLekarza> getSkierowanieDoLekarzas() {
//        return skierowanieDoLekarzas;
//    }
//
//    public void setSkierowanieDoLekarzas(List<SkierowanieDoLekarza> skierowanieDoLekarzas) {
//        this.skierowanieDoLekarzas = skierowanieDoLekarzas;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getDyskoteka() {
        return dyskoteka;
    }

    public void setDyskoteka(String dyskoteka) {
        this.dyskoteka = dyskoteka;
    }
}
