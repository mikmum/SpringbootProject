package spring.repositories;

import org.springframework.data.jpa.repository.Query;
import spring.model.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository  extends CrudRepository<Film, Integer> {
    @Query("select count(*) from films f where f.id = ?1")
    Integer checkIfExists(Integer id);
}
