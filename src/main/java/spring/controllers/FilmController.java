package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.model.Film;
import spring.services.FilmService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping(value = "/films", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Film> list(Model model) { return filmService.listAllFilms(); }

    @GetMapping(value = "/films/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Film getById(@PathVariable("id") Integer id) {
        return filmService.getFilmById(id).orElseGet(null);
    }

    @PostMapping(value = "/films")
    public ResponseEntity<Film> create(@RequestBody @NonNull @Valid Film film) {
        filmService.saveFilm(film);
        return ResponseEntity.ok().body(film);
    }

    @PutMapping(value = "/films")
    public ResponseEntity<Void> edit(@RequestBody Film film) {
        if(!filmService.checkIfExist(film.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            filmService.saveFilm(film);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/films/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {

        filmService.deleteFilm(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
