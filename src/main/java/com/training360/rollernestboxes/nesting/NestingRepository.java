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
}
