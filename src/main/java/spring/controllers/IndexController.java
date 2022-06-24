package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import spring.model.*;
import spring.services.*;

import java.time.ZonedDateTime;

@CrossOrigin
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    ActorService actorService;
    @Autowired
    ChairmanService chairmanService;
    @Autowired
    StudioService studioService;
    @Autowired
    FilmService filmService;
    @Autowired
    RatingsService ratingsService;


    @GetMapping(value = "")
    String index() { return "index"; }

    @PostMapping(value = "generateModel", produces = MediaType.TEXT_PLAIN_VALUE)
    public String generateModel(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        Actor actor1 = new Actor("12345678910",  "Daniel", "Radcliffe");
        Actor actor2 = new Actor("10987654321",  "Tom",  "Holland");
        actor1.setMainGenre("Fantasy");
        actor2.setMainGenre("Superhero");

        Actor actor3 = new Actor("44444488888", "Ted", "Cruz");

        Chairman chairman1 = new Chairman("11111111111", "John", "Smith", 4000);
        Chairman chairman2 = new Chairman ("00000000000", "Walt", "Disney", 10000);

        Studio studio1 = new Studio("Disney", chairman2);
        studio1.setAddress("3rd Avenue 37");
        Studio studio2 = new Studio("Marvel Studios", chairman1);
        studio2.setAddress("Lincoln Street 20");

        Film film1 = new Film("Harry Potter One", "Fantasy", 130, zonedDateTime.minusYears(7));
        film1.addActor(actor1);
        actor1.addFilms(film1);
        film1.setStudio(studio1);

        Film film2 = new Film("Spiderman", "Superhero", 150, zonedDateTime.minusYears(4));
        film2.addActor(actor2);
        actor2.addFilms(film2);
        film2.setStudio(studio2);

        Film film3 = new Film("Harry Potter Two", "Fantasy", 140, zonedDateTime.minusYears(6));
        film3.addActor(actor1);
        actor1.addFilms(film3);
        film3.setStudio(studio1);
        studio1.addFilm(film3);

        Ratings ratings1 = new Ratings(8.63, 9.0, 45, film1);
        film1.setRatings(ratings1);
        Ratings ratings2 = new Ratings(7.5, 7.8, 79, film2);
        film2.setRatings(ratings2);
        Ratings ratings3 = new Ratings(7.8, 8.4, 55, film3);
        film3.setRatings(ratings3);

        studioService.saveStudio(studio1);
        studioService.saveStudio(studio2);

        filmService.saveFilm(film1);
        filmService.saveFilm(film2);
        filmService.saveFilm(film3);

        actorService.saveActor(actor1);
        actorService.saveActor(actor2);
        actorService.saveActor(actor3);


        Actor actor4 = new Actor("99998888777",  "Emma",  "Wattson");
        actor4.setMainGenre("Fantasy");
        film1.addActor(actor4);
        film3.addActor(actor4);
        actor4.addFilms(film1);
        actor4.addFilms(film3);

        actorService.saveActor(actor4);

        return "model generated";

    }

    @PostMapping(value = "generateBobs", produces = MediaType.TEXT_PLAIN_VALUE)
    public String generateBobs(){
        Chairman bob = new Chairman("22222222222", "Bob", "Smith", 2000);
        Chairman bob2 = new Chairman("33333333333", "Bob", "Backlund", 3000);
        Chairman bob3 = new Chairman("44444444444", "Bob", "Jones", 4000);
        Chairman bob4 = new Chairman("55555555555", "Bob", "Alexy", 5000);
        Chairman bob5 = new Chairman("66666666666", "Bob", "Karpov", 6000);
        Chairman bob6 = new Chairman("77777777777", "Bob", "Builder", 7000);
        Chairman bob7 = new Chairman("88888888888", "Bob", "Bobowski", 8000);
        Chairman bob8 = new Chairman("99999999999", "Bob", "Bobsson", 9000);
        Chairman bob9 = new Chairman("10101010101", "Bob", "Bobovich", 10000);
        Chairman bob10 = new Chairman("01010101010", "Bob", "Boborenko", 11000);

        chairmanService.saveChairman(bob);
        chairmanService.saveChairman(bob2);
        chairmanService.saveChairman(bob3);
        chairmanService.saveChairman(bob4);
        chairmanService.saveChairman(bob5);
        chairmanService.saveChairman(bob6);
        chairmanService.saveChairman(bob7);
        chairmanService.saveChairman(bob8);
        chairmanService.saveChairman(bob9);
        chairmanService.saveChairman(bob10);

        return "bobs generated";

    }
}
