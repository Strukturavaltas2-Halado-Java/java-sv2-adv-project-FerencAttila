package com.training360.rollernestboxes.nesting;

import com.training360.rollernestboxes.nesting.model.Nesting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NestingRepository extends JpaRepository<Nesting, Long> {

    @Query(value = "select n from Nesting n where year(n.nestingParameters.dateOfSurvey) = :year")
    List<Nesting> findAllByYear(int year);

    @Query(value = "select n from Nesting n where n.nestingParameters.nestingSpecies = :species")
    List<Nesting> findAllBySpecies(String species);

    @Query(value = "select n from Nesting n where year(n.nestingParameters.dateOfSurvey) = :year and n.nestingParameters.nestingSpecies = :species")
    List<Nesting> findAllByYearAndSpecies(int year, String species);

    @Query(value = "select n from Nesting n where n.nestBox.nestBoxId = :nestBoxId")
    List<Nesting> findAllByNestBoxId(String nestBoxId);
}
