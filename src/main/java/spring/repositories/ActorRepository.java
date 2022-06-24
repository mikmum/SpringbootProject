package spring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.model.Actor;
import org.springframework.data.repository.CrudRepository;
import spring.model.Film;

import java.util.List;

public interface ActorRepository extends CrudRepository<Actor, String> {
    @Query("select count(*) from actors a where a.PESEL = ?1")
    Integer checkIfExists(String id);

    @Query("select f from films f join f.actors a where a.PESEL = :id")
    List<Film> lookUpAllAppearances(@Param("id")String id);

    @Query("select a from actors a where a.mainGenre is not null")
    List<Actor> lookUpSpecializedActors();
}
