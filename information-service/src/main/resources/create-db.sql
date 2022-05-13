CREATE DATABASE infodb
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'C'
    LC_CTYPE = 'C'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE IF NOT EXISTS public.course
(
    id bigint NOT NULL,
    name VARCHAR ( 50 ) NOT NULL,
    department_id bigint NOT NULL,
    credits bigint NOT NULL,
    CONSTRAINT course_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.course
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.department
(
    id bigint NOT NULL,
    name VARCHAR ( 50 ) NOT NULL,
    CONSTRAINT department_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.department
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.professor
(
    id bigint NOT NULL,
    name VARCHAR ( 50 ) NOT NULL,
	department_id bigint NOT NULL,
    CONSTRAINT professor_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.professor
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.schedule
(
    professor_id bigint NOT NULL,
    course_id bigint NOT NULL,
    semester bigint NOT NULL,
    year bigint NOT NULL,
    CONSTRAINT schedule_pkey PRIMARY KEY (professor_id, course_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.schedule
    OWNER to postgres;