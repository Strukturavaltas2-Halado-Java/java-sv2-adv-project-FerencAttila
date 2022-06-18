package com.training360.rollernestboxes.nestboxes.repository;

import com.training360.rollernestboxes.nestboxes.model.NestBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NestBoxRepository extends JpaRepository<NestBox, Long> {

    boolean existsNestBoxByNestBoxNumber(String nestBoxNumber);

    Optional<NestBox> findNestBoxByNestBoxNumber(String nestBoxNumber);
}
