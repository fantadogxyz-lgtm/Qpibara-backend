package com.qpibara.Qpibara.event.service;

import com.qpibara.Qpibara.config.exepitions.BusinessCode;
import com.qpibara.Qpibara.config.exepitions.BusinessException;
import com.qpibara.Qpibara.event.dto.*;
import com.qpibara.Qpibara.event.mapper.SessionMapper;
import com.qpibara.Qpibara.event.model.*;
import com.qpibara.Qpibara.event.repository.EventRepository;
import com.qpibara.Qpibara.event.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final EventRepository eventRepository;
    private final SessionMapper sessionMapper;

    @Transactional
    public SessionResponse startSession(Long eventId, Long authorId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        if (!event.getAuthorId().equals(authorId)) {
            throw new BusinessException(BusinessCode.NOT_YOUR);
        }

        Session session = new Session();
        session.setEventId(eventId);

        if (Boolean.TRUE.equals(event.getIsTeamsEnabled()) && event.getPlannedTeamCount() != null) {
            List<Team> teams = new ArrayList<>();
            for (int i = 1; i <= event.getPlannedTeamCount(); i++) {
                teams.add(Team.builder()
                        .name("Команда " + i)
                        .points(0)
                        .isPresent(true)
                        .session(session)
                        .build());
            }
            session.getTeams().addAll(teams);
        }

        if (Boolean.TRUE.equals(event.getIsMembersEnabled()) && event.getPlannedMemberCount() != null) {
            List<Member> members = new ArrayList<>();
            for (int i = 1; i <= event.getPlannedMemberCount(); i++) {
                members.add(Member.builder()
                        .name("Участник " + i)
                        .points(0)
                        .isPresent(true)
                        .session(session)
                        .build());
            }
            session.getMembers().addAll(members);
        }

        Session saved = sessionRepository.save(session);
        return sessionMapper.toResponse(saved);
    }

    public SessionResponse getSessionById(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));
        return sessionMapper.toResponse(session);
    }

    @Transactional
    public SessionResponse updateSession(Long id, SessionUpdateRequest request) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        session.setNotes(request.notes());
        session.setCurrentStageId(request.currentStageId());
        session.setCurrentQuestionId(request.currentQuestionId());

        return sessionMapper.toResponse(sessionRepository.save(session));
    }

    @Transactional
    public SessionResponse updateTeam(Long sessionId, Long teamId, TeamRequest request) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        Team team = session.getTeams().stream()
                .filter(t -> t.getId().equals(teamId)).findFirst()
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        team.setName(request.name());
        team.setPoints(request.points());
        team.setIsPresent(request.isPresent());

        sessionRepository.save(session);
        return sessionMapper.toResponse(session);
    }

    @Transactional
    public SessionResponse updateMember(Long sessionId, Long memberId, MemberRequest request) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        Member member = session.getMembers().stream()
                .filter(m -> m.getId().equals(memberId)).findFirst()
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        member.setName(request.name());
        member.setPoints(request.points());
        member.setIsPresent(request.isPresent());

        sessionRepository.save(session);
        return sessionMapper.toResponse(session);
    }

    @Transactional
    public void saveAnswer(Long sessionId, SessionAnswerRequest request) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        SessionAnswer answer = new SessionAnswer();
        answer.setQuestionId(request.questionId());
        answer.setAnswerText(request.answerText());
        answer.setIsCorrect(request.isCorrect());
        answer.setPointsAwarded(request.pointsAwarded());
        answer.setSession(session);

        if (request.teamId() != null) {
            Team team = session.getTeams().stream()
                    .filter(t -> t.getId().equals(request.teamId())).findFirst()
                    .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));
            answer.setTeam(team);
        }
        if (request.memberId() != null) {
            Member member = session.getMembers().stream()
                    .filter(m -> m.getId().equals(request.memberId())).findFirst()
                    .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));
            answer.setMember(member);
        }

        if (request.pointsAwarded() != null && request.pointsAwarded() > 0) {
            if (answer.getTeam() != null) {
                answer.getTeam().setPoints(answer.getTeam().getPoints() + request.pointsAwarded());
            } else if (answer.getMember() != null) {
                answer.getMember().setPoints(answer.getMember().getPoints() + request.pointsAwarded());
            }
        }

        session.getAnswers().add(answer);
        sessionRepository.save(session);
    }

    @Transactional
    public SessionResponse updateTimer(Long sessionId, TimerRequest request) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        if (session.getTotalTimeSeconds() == null) {
            session.setTotalTimeSeconds(0L);
        }

        if (request.absoluteSeconds() != null) {
            session.setTotalTimeSeconds(request.absoluteSeconds());
        } else if (request.deltaSeconds() != null) {
            session.setTotalTimeSeconds(session.getTotalTimeSeconds() + request.deltaSeconds());
        }

        Session saved = sessionRepository.save(session);
        return sessionMapper.toResponse(saved);
    }
}