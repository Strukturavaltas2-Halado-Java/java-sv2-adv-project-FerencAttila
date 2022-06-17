package com.training360.rollernestboxes.nestboxes;

import com.training360.rollernestboxes.nestboxes.model.Condition;
import com.training360.rollernestboxes.nestboxes.model.NestBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface NestBoxRepository extends JpaRepository<NestBox, Long> {

    Optional<NestBox> findByNestBoxIdIgnoreCase(String nestBoxId);

    List<NestBox> findAllByConditionIsNot(Condition condition);

    List<NestBox> findAllByConditionIsIn(Set<Condition> condition);

    boolean existsNestBoxByNestBoxId(String nestBoxId);

    @Query("select case when count(n) > 0 then true else false end from NestBox n where n.condition <> 'EXPIRED' and n.nestBoxId = :nestBoxId")
    boolean existsNestBoxByNestBoxIdAndIsLiving(String nestBoxId);
}
