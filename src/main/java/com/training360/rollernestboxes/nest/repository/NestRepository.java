package com.training360.rollernestboxes.nest.repository;

import com.training360.rollernestboxes.nest.model.Nest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NestRepository extends JpaRepository<Nest, Long> {

    @Query("select n from Nest n where (:nestBoxNumber is null or n.nestBox.nestBoxNumber = :nestBoxNumber) and (:species is null or n.species = :species)")
    List<Nest> findAllNestsByNestBoxNumberAndSpecies(@Param("nestBoxNumber") Optional<String> nestBoxNumber, @Param("species") Optional<String> species);

    List<Nest> findNestByNestBox_NestBoxNumber(String nestBoxNumber);
}
