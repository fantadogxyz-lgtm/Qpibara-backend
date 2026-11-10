package com.qpibara.Qpibara.quiz.service;

import com.qpibara.Qpibara.config.exepitions.BusinessCode;
import com.qpibara.Qpibara.config.exepitions.BusinessException;
import com.qpibara.Qpibara.quiz.dto.QuizRequest;
import com.qpibara.Qpibara.quiz.dto.QuizResponse;
import com.qpibara.Qpibara.quiz.dto.QuizShortInfoResponse;
import com.qpibara.Qpibara.quiz.mapper.QuizMapper;
import com.qpibara.Qpibara.quiz.mapper.StageMapper;
import com.qpibara.Qpibara.quiz.model.Quiz;
import com.qpibara.Qpibara.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final StageMapper stageMapper;

    @Transactional
    public QuizResponse createQuiz(QuizRequest request, Long authorId) {
        Quiz quiz = quizMapper.toEntity(request);
        quiz.setAuthorId(authorId);

        Quiz saved = quizRepository.save(quiz);
        return quizMapper.toResponse(saved);
    }

    @Transactional
    public QuizResponse updateQuiz(Long id, QuizRequest request, Long authorId) {
        Quiz existing = quizRepository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        if (!existing.getAuthorId().equals(authorId)) {
            throw new BusinessException(BusinessCode.NOT_YOUR);
        }

        existing.setName(request.name());
        existing.setDescription(request.description());
        existing.setComment(request.comment());

        if (request.stages() != null) {
            var newStages = request.stages().stream()
                    .map(stageMapper::toEntity)
                    .toList();
            existing.setStages(newStages);
        }

        Quiz updated = quizRepository.save(existing);
        return quizMapper.toResponse(updated);
    }

    // Возвращаем легковесные DTO!
    public List<QuizShortInfoResponse> getMyQuizzes(Long authorId) {
        return quizRepository.findAllByAuthorId(authorId).stream()
                .map(quizMapper::toShortInfo)
                .toList();
    }

    public QuizResponse getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));
        return quizMapper.toResponse(quiz);
    }

    @Transactional
    public void deleteQuiz(Long id, Long authorId) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessCode.RESOURCE_NOT_FOUND));

        if (!quiz.getAuthorId().equals(authorId)) {
            throw new BusinessException(BusinessCode.NOT_YOUR);
        }
        quizRepository.delete(quiz);
    }
}