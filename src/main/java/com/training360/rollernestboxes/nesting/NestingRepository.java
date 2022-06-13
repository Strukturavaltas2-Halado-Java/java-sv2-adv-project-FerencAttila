package com.training360.rollernestboxes.nesting;

import com.training360.rollernestboxes.nesting.model.Nesting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NestingRepository extends JpaRepository<Nesting, Long> {

    @Query(value = "select n from Nesting n where (:year is null or year(n.nestingParameters.dateOfSurvey) = :year)" +
            "and (:species is null or n.nestingParameters.nestingSpecies = :species)")
    List<Nesting> findAllByYearAndSpecies(Optional<Integer> year, Optional<String> species);

    @Query(value = "select n from Nesting n where n.nestBox.nestBoxId = :nestBoxId")
    List<Nesting> findAllByNestBoxId(String nestBoxId);
}
