package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.model.Actor;
import spring.model.Film;
import spring.repositories.ActorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public Iterable<Actor> listAllActors() { return actorRepository.findAll(); }

    public Optional<Actor> getActorById(String id) { return actorRepository.findById(id); }

    public Actor saveActor(Actor actor) { return actorRepository.save(actor); }

    public void deleteActor(String id) { actorRepository.deleteById(id); }

    public List<Film> lookupAppearances(String id) { return actorRepository.lookUpAllAppearances(id); }

    public List<Actor> lookUpSpecializedActors() { return actorRepository.lookUpSpecializedActors(); }

    public Boolean checkIfExist(String id) {
        if (actorRepository.checkIfExists(id) > 0)
            return true;
        else
            return false;
    }


}
