package com.training360.rollernestboxes.nestboxes;

import com.training360.rollernestboxes.nestboxes.model.NestBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NestBoxRepository extends JpaRepository<NestBox, Long> {

    Optional<NestBox> findByNestBoxIdIgnoreCase(String nestBoxId);

    List<NestBox> findAllByConditionIsNot(Condition condition);

    List<NestBox> findAllByConditionIs(Condition condition);
}
