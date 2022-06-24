package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.model.Chairman;
import spring.services.ChairmanService;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ChairmanController {
    @Autowired
    private ChairmanService chairmanService;

    @GetMapping(value = "/chairmen", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Chairman> list(Model model) { return chairmanService.listAllChairmans(); }


    @GetMapping(value = "/chairmen/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Chairman getById(@PathVariable("id") String id) {
        return chairmanService.getChairmanById(id).orElseGet(null);
    }

    @GetMapping(value = "/bobs/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Chairman> pagedBobs(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer>
                                        howManyOnPage)
    { return chairmanService.findBobsPaged(pageNr, howManyOnPage.orElse(3)); }

    @GetMapping(value = "/chairmenBestPaid", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Chairman> bestPaid() { return chairmanService.listBestEarners(); }

    @PostMapping(value = "/chairmen")
    public ResponseEntity<Chairman> create(@RequestBody @NonNull @Valid Chairman chairman) {
        chairmanService.saveChairman(chairman);
        return ResponseEntity.ok().body(chairman);
    }

    @PutMapping(value = "/chairmen")
    public ResponseEntity<Void> edit(@RequestBody Chairman chairman) {
        if(!chairmanService.checkIfExist(chairman.getPESEL()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            chairmanService.saveChairman(chairman);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/chairmen/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        chairmanService.deleteChairman(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
