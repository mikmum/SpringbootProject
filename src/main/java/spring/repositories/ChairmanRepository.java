package spring.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import spring.model.Chairman;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChairmanRepository extends CrudRepository<Chairman, String>, PagingAndSortingRepository<Chairman, String> {
    @Query("select count(*) from chairmen c where c.PESEL = ?1")
    Integer checkIfExists(String id);

    Page<Chairman> findAllByForename(String name, Pageable pageable);

    @Query("select c from chairmen c where c.salary > 8000")
    List<Chairman> listEarningOver8000();
}
