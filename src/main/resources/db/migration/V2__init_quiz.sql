CREATE SCHEMA IF NOT EXISTS quiz;

CREATE TABLE IF NOT EXISTS quiz.attachment_type (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS quiz.quizzes (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    comment TEXT,
    author_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
    );

CREATE TABLE IF NOT EXISTS quiz.stages (
    id BIGSERIAL PRIMARY KEY,
    quiz_id BIGINT NOT NULL REFERENCES quiz.quizzes(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50),
    serial_number INT,
    settings JSONB
    );

CREATE TABLE IF NOT EXISTS quiz.questions (
    id BIGSERIAL PRIMARY KEY,
    stage_id BIGINT NOT NULL REFERENCES quiz.stages(id) ON DELETE CASCADE,
    text TEXT NOT NULL,
    type BIGINT NOT NULL,
    points INT DEFAULT 1,
    explanation TEXT
    );

CREATE TABLE IF NOT EXISTS quiz.answer_options (
    id BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL REFERENCES quiz.questions(id) ON DELETE CASCADE,
    text VARCHAR(255),
    is_correct BOOLEAN DEFAULT FALSE
    );

CREATE TABLE IF NOT EXISTS quiz.matching_pairs (
    id BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL REFERENCES quiz.questions(id) ON DELETE CASCADE,
    left_element VARCHAR(255),
    right_element VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS quiz.attachments (
    id UUID PRIMARY KEY,
    question_id BIGINT REFERENCES quiz.questions(id) ON DELETE CASCADE,
    answer_option_id BIGINT REFERENCES quiz.answer_options(id) ON DELETE CASCADE,
    type_id BIGINT REFERENCES quiz.attachment_type(id),
    uri VARCHAR(500) NOT NULL
    );

INSERT INTO quiz.attachment_type (title) VALUES ('Фото'), ('Аудио'), ('Видео') ON CONFLICT DO NOTHING;