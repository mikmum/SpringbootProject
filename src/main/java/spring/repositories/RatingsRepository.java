package spring.repositories;


import org.springframework.data.jpa.repository.Query;
import spring.model.Ratings;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingsRepository  extends CrudRepository<Ratings, Integer> {

    @Query("select count(*) from ratings r where r.id = ?1")
    Integer checkIfExists(Integer id);

    @Query("select r from ratings r where (r.userRatings + r.reviewerRatings)/2 > 8.0 AND r.film is not null")
    List<Ratings> listBestRated();
}
