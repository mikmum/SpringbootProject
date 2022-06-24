package spring.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.model.Film;
import spring.model.Studio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudioRepository  extends CrudRepository<Studio, Integer> {
    @Query("select count(*) from studios s where s.id = ?1")
    Integer checkIfExists(Integer id);

    @Query("select f from films f join f.studio s where s.id = :id")
    List<Film> getStudioFilms(@Param("id")Integer id);
}
