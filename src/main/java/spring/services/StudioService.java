package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.model.Film;
import spring.model.Studio;
import spring.repositories.StudioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudioService {
    @Autowired
    private StudioRepository studioRepository;

    public Iterable<Studio> listAllStudios() { return studioRepository.findAll(); }

    public Optional<Studio> getStudioById(Integer id) { return studioRepository.findById(id); }

    public Studio saveStudio(Studio studio) { return studioRepository.save(studio); }

    public List<Film> getFilmByStudio(Integer id) {return studioRepository.getStudioFilms(id); }

    public void deleteStudio(Integer id) { studioRepository.deleteById(id); }

    public Boolean checkIfExist(Integer id) {
        if (studioRepository.checkIfExists(id) > 0)
            return true;
        else
            return false;
    }
}
