package com.qpibara.Qpibara.event.repository;

import com.qpibara.Qpibara.event.model.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
}
