package com.qpibara.Qpibara.quiz.service;

import com.qpibara.Qpibara.config.exepitions.BusinessCode;
import com.qpibara.Qpibara.config.exepitions.BusinessException;
import com.qpibara.Qpibara.quiz.dto.*;
import com.qpibara.Qpibara.quiz.mapper.*;
import com.qpibara.Qpibara.quiz.model.*;
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

    // Внедряем все остальные мапперы для ручной сборки
    private final StageMapper stageMapper;
    private final QuestionMapper questionMapper;
    private final AnswerOptionMapper answerOptionMapper;
    private final MatchingPairMapper matchingPairMapper;
    private final AttachmentMapper attachmentMapper;

    @Transactional
    public QuizResponse createQuiz(QuizRequest request, Long authorId) {
        Quiz quiz = quizMapper.toEntity(request);
        quiz.setAuthorId(authorId);

        if (request.stages() != null) {
            // Мапим этапы через наш хелпер
            var newStages = request.stages().stream()
                    .map(this::mapStage)
                    .toList();
            quiz.setStages(newStages);
        }

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
                    .map(this::mapStage) // Используем хелпер
                    .toList();
            existing.setStages(newStages);
        }

        Quiz updated = quizRepository.save(existing);
        return quizMapper.toResponse(updated);
    }

    // --- ХЕЛПЕРЫ ДЛЯ ГЛУБОКОГО МАППИНГА ---

    private Stage mapStage(StageRequest stageReq) {
        Stage stage = stageMapper.toEntity(stageReq);
        if (stageReq.questions() != null) {
            List<Question> questions = stageReq.questions().stream()
                    .map(this::mapQuestion)
                    .toList();
            stage.setQuestions(questions); // Сработает кастомный сеттер и проставит stage_id
        }
        return stage;
    }

    private Question mapQuestion(QuestionRequest qReq) {
        Question question = questionMapper.toEntity(qReq);

        if (qReq.answerOptions() != null) {
            List<AnswerOption> options = qReq.answerOptions().stream()
                    .map(answerOptionMapper::toEntity).toList();
            question.setAnswerOptions(options);
        }
        if (qReq.matchingPairs() != null) {
            List<MatchingPair> pairs = qReq.matchingPairs().stream()
                    .map(matchingPairMapper::toEntity).toList();
            question.setMatchingPairs(pairs);
        }
        if (qReq.attachments() != null) {
            List<Attachment> attachments = qReq.attachments().stream()
                    .map(attachmentMapper::toEntity).toList();
            question.setAttachments(attachments);
        }
        return question;
    }

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