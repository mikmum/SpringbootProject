package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.model.Ratings;
import spring.services.RatingsService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RatingsController {
    @Autowired
    private RatingsService ratingsService;

    @GetMapping(value = "/ratings", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Ratings> list(Model model) { return ratingsService.listAllRatings(); }


    @GetMapping(value = "/ratings/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ratings getById(@PathVariable("id") Integer id) {
        return ratingsService.getRatingsById(id).orElseGet(null);
    }

    @GetMapping(value = "/bestRatings", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ratings> getBestRated() { return ratingsService.bestRated(); }

    @PostMapping(value = "/ratings")
    public ResponseEntity<Ratings> create(@RequestBody @NonNull @Valid Ratings ratings) {
        ratingsService.saveRatings(ratings);
        return ResponseEntity.ok().body(ratings);
    }

    @PutMapping(value = "/ratings")
    public ResponseEntity<Void> edit(@RequestBody Ratings ratings) {
        if(!ratingsService.checkIfExist(ratings.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            ratingsService.saveRatings(ratings);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/ratings/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        ratingsService.deleteRatings(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
