package com.kev.Learner.repository;

import com.kev.Learner.entities.IsoMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IsoMessageRepo extends JpaRepository<IsoMessage, Long> {
    @Query(value = "SELECT msg FROM IsoMessage msg WHERE msg.id = :id")
    IsoMessage getIsoMessageByMsgId(long id);
}
