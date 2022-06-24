package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.model.Film;
import spring.model.Studio;
import spring.services.StudioService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class StudioController {
    @Autowired
    private StudioService studioService;

    @GetMapping(value = "/studios", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Studio> list(Model model) { return studioService.listAllStudios(); }


    @GetMapping(value = "/studios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Studio getById(@PathVariable("id") Integer id) {
        return studioService.getStudioById(id).orElseGet(null);
    }

    @GetMapping(value = "/studios/{id}/produced", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Film> getStudioProduced(@PathVariable("id") Integer id) {return studioService.getFilmByStudio(id); }

    @PostMapping(value = "/studios")
    public ResponseEntity<Studio> create(@RequestBody @NonNull @Valid Studio studio) {
        studioService.saveStudio(studio);
        return ResponseEntity.ok().body(studio);
    }

    @PutMapping(value = "/studios")
    public ResponseEntity<Void> edit(@RequestBody Studio studio) {
        if(!studioService.checkIfExist(studio.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            studioService.saveStudio(studio);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/studios/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        studioService.deleteStudio(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
