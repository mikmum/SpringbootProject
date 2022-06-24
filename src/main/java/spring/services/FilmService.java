package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.model.Film;
import spring.repositories.FilmRepository;

import java.util.Optional;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    public Iterable<Film> listAllFilms() { return filmRepository.findAll(); }

    public Optional<Film> getFilmById(Integer id) { return filmRepository.findById(id); }

    public Film saveFilm(Film film) { return filmRepository.save(film); }

    public void deleteFilm(Integer id) { filmRepository.deleteById(id); }

    public Boolean checkIfExist(Integer id) {
        if (filmRepository.checkIfExists(id) > 0)
            return true;
        else
            return false;
    }
}
