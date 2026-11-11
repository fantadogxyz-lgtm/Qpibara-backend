package com.qpibara.Qpibara.event.repository;

import com.qpibara.Qpibara.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByAuthorId(Long authorId);
}
