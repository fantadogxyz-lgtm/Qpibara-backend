CREATE SCHEMA IF NOT EXISTS event;

CREATE TABLE IF NOT EXISTS event.events (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    comment TEXT,
    place VARCHAR(255),
    event_date TIMESTAMP WITH TIME ZONE,
    balance DOUBLE PRECISION DEFAULT 0,
    author_id BIGINT NOT NULL,
    quiz_id BIGINT NOT NULL,
    is_teams_enabled BOOLEAN DEFAULT TRUE,
    planned_team_count INT DEFAULT 0,
    is_members_enabled BOOLEAN DEFAULT FALSE,
    planned_member_count INT DEFAULT 0,
    is_scoring_enabled BOOLEAN DEFAULT TRUE,
    is_attendance_enabled BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
    );

CREATE TABLE IF NOT EXISTS event.sessions (
    id BIGSERIAL PRIMARY KEY,
    event_id BIGINT NOT NULL REFERENCES event.events(id) ON DELETE CASCADE,
    notes TEXT,
    current_stage_id BIGINT,
    current_question_id BIGINT,
    started_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
    );

CREATE TABLE IF NOT EXISTS event.teams (
    id BIGSERIAL PRIMARY KEY,
    session_id BIGINT NOT NULL REFERENCES event.sessions(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    points INT DEFAULT 0,
    is_present BOOLEAN DEFAULT TRUE
    );

CREATE TABLE IF NOT EXISTS event.members (
    id BIGSERIAL PRIMARY KEY,
    session_id BIGINT NOT NULL REFERENCES event.sessions(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    points INT DEFAULT 0,
    is_present BOOLEAN DEFAULT TRUE
    );

CREATE TABLE IF NOT EXISTS event.session_answers (
    id BIGSERIAL PRIMARY KEY,
session_id BIGINT NOT NULL REFERENCES event.sessions(id) ON DELETE CASCADE,
    question_id BIGINT NOT NULL,
    answer_text TEXT,
    is_correct BOOLEAN,
    points_awarded INT DEFAULT 0,
    team_id BIGINT REFERENCES event.teams(id) ON DELETE CASCADE,
    member_id BIGINT REFERENCES event.members(id) ON DELETE CASCADE
    );

ALTER TABLE event.sessions ADD COLUMN total_time_seconds BIGINT DEFAULT 0;