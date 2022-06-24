package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.model.Actor;
import spring.model.Film;
import spring.services.ActorService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ActorController {
    @Autowired
    private ActorService actorService;

    @GetMapping(value = "/actors", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Actor> list(Model model) { return actorService.listAllActors(); }

    @GetMapping(value = "/actors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Actor getById(@PathVariable("id") String id) {
        return actorService.getActorById(id).orElseGet(null);
    }

    @GetMapping(value = "/actors/{id}/appearances", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> getAllAppearances(@PathVariable("id") String id) {
        return actorService.lookupAppearances(id);
    }

    @GetMapping(value = "/specializedActors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Actor> getSpecializedActors() { return actorService.lookUpSpecializedActors(); }

    @PostMapping(value = "/actors")
    public ResponseEntity<Actor> create(@RequestBody @NonNull @Valid Actor actor) {
        actorService.saveActor(actor);
        return ResponseEntity.ok().body(actor);
    }

    @PutMapping(value = "/actors")
    public ResponseEntity<Void> edit(@RequestBody Actor actor) {
        if(!actorService.checkIfExist(actor.getPESEL()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            actorService.saveActor(actor);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/actors/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        actorService.deleteActor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }






}
