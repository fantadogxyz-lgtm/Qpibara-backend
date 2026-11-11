package com.qpibara.Qpibara.event.service;

import com.qpibara.Qpibara.config.exepitions.BusinessCode;
import com.qpibara.Qpibara.config.exepitions.BusinessException;
import com.qpibara.Qpibara.event.dto.EventRequest;
import com.qpibara.Qpibara.event.dto.EventResponse;
import com.qpibara.Qpibara.event.dto.EventShortInfoResponse;
import com.qpibara.Qpibara.event.mapper.EventMapper;
import com.qpibara.Qpibara.event.model.Event;
import com.qpibara.Qpibara.event.repository.EventRepository;
import com.qpibara.Qpibara.quiz.model.Quiz;
import com.qpibara.Qpibara.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final QuizRepository quizRepository;
    private final EventMapper eventMapper;

    @Transactional
    public EventResponse createEvent(EventRequest request, Long authorId) {
        Quiz quiz = quizRepository.findById(request.quizId())
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        Event event = eventMapper.toEntity(request);
        event.setAuthorId(authorId);

        Event saved = eventRepository.save(event);

        EventResponse response = eventMapper.toResponse(saved);
        return new EventResponse(
                response.id(), response.name(), response.description(), response.comment(),
                response.place(), response.eventDate(), response.balance(), response.quizId(),
                quiz.getName(),
                response.isTeamsEnabled(), response.plannedTeamCount(),
                response.isMembersEnabled(), response.plannedMemberCount(),
                response.isScoringEnabled(), response.isAttendanceEnabled(), response.createdAt()
        );
    }

    public List<EventShortInfoResponse> getMyEvents(Long authorId) {
        return eventRepository.findAllByAuthorId(authorId).stream()
                .map(eventMapper::toShortInfo)
                .toList();
    }

    public EventResponse getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        Quiz quiz = quizRepository.findById(event.getQuizId())
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        EventResponse response = eventMapper.toResponse(event);
        return new EventResponse(
                response.id(), response.name(), response.description(), response.comment(),
                response.place(), response.eventDate(), response.balance(), response.quizId(),
                quiz.getName(),
                response.isTeamsEnabled(), response.plannedTeamCount(),
                response.isMembersEnabled(), response.plannedMemberCount(),
                response.isScoringEnabled(), response.isAttendanceEnabled(), response.createdAt()
        );
    }

    @Transactional
    public EventResponse updateEvent(Long id, EventRequest request, Long authorId) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        if (!event.getAuthorId().equals(authorId)) {
            throw new BusinessException(BusinessCode.NOT_YOUR);
        }

        event.setName(request.name());
        event.setDescription(request.description());
        event.setComment(request.comment());
        event.setPlace(request.place());
        event.setEventDate(request.eventDate());
        event.setBalance(request.balance());
        event.setQuizId(request.quizId());

        event.setIsTeamsEnabled(request.isTeamsEnabled());
        event.setPlannedTeamCount(request.plannedTeamCount());
        event.setIsMembersEnabled(request.isMembersEnabled());
        event.setPlannedMemberCount(request.plannedMemberCount());
        event.setIsScoringEnabled(request.isScoringEnabled());
        event.setIsAttendanceEnabled(request.isAttendanceEnabled());

        Event updated = eventRepository.save(event);

        Quiz quiz = quizRepository.findById(updated.getQuizId()).orElseThrow();
        EventResponse response = eventMapper.toResponse(updated);

        return new EventResponse(
                response.id(), response.name(), response.description(), response.comment(),
                response.place(), response.eventDate(), response.balance(), response.quizId(),
                quiz.getName(),
                response.isTeamsEnabled(), response.plannedTeamCount(),
                response.isMembersEnabled(), response.plannedMemberCount(),
                response.isScoringEnabled(), response.isAttendanceEnabled(), response.createdAt()
        );
    }

    @Transactional
    public void deleteEvent(Long id, Long authorId) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        if (!event.getAuthorId().equals(authorId)) {
            throw new BusinessException(BusinessCode.NOT_YOUR);
        }

        eventRepository.delete(event);
    }
}