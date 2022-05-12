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