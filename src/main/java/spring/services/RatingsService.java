package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.model.Ratings;
import spring.repositories.RatingsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RatingsService {
    @Autowired
    private RatingsRepository ratingsRepository;

    public Iterable<Ratings> listAllRatings() { return ratingsRepository.findAll(); }

    public Optional<Ratings> getRatingsById(Integer id) { return ratingsRepository.findById(id); }

    public Ratings saveRatings(Ratings ratings) { return ratingsRepository.save(ratings); }

    public void deleteRatings(Integer id) { ratingsRepository.deleteById(id); }

    public List<Ratings> bestRated() { return ratingsRepository.listBestRated(); }

    public Boolean checkIfExist(Integer id) {
        if (ratingsRepository.checkIfExists(id) > 0)
            return true;
        else
            return false;
    }
}
