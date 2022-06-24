package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import spring.model.Chairman;
import spring.repositories.ChairmanRepository;

import java.util.Optional;

@Service
public class ChairmanService {
    @Autowired
    private ChairmanRepository chairmanRepository;

    public Iterable<Chairman> listAllChairmans() { return chairmanRepository.findAll(); }

    public Optional<Chairman> getChairmanById(String id) { return chairmanRepository.findById(id); }

    public Chairman saveChairman(Chairman chairman) { return chairmanRepository.save(chairman); }

    public void deleteChairman(String id) { chairmanRepository.deleteById(id); }

    public Iterable<Chairman> findBobsPaged(Integer pageNr, Integer howManyOnPage) {
        return chairmanRepository.findAllByForename("Bob", PageRequest.of(pageNr, howManyOnPage));
    }

    public Iterable<Chairman> listBestEarners(){ return chairmanRepository.listEarningOver8000(); }


    public Boolean checkIfExist(String id) {
        if (chairmanRepository.checkIfExists(id) > 0)
            return true;
        else
            return false;
    }
}
