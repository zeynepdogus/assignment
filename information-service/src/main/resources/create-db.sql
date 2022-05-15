--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.0

-- Started on 2022-05-15 11:27:39 +03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3594 (class 1262 OID 16413)
-- Name: informationdb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE informationdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C';


ALTER DATABASE informationdb OWNER TO postgres;

\connect informationdb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 16421)
-- Name: course; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.course (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    department_id bigint NOT NULL,
    credits bigint NOT NULL
);


ALTER TABLE public.course OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16426)
-- Name: department; Type: TABLE; Schema: public; Owner: postgres
--

--
-- TOC entry 3589 (class 0 OID 16421)
-- Dependencies: 209
-- Data for Name: course; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.course (id, name, department_id, credits) VALUES (9, 'Clinical Biochemistry', 2, 3);
INSERT INTO public.course (id, name, department_id, credits) VALUES (4, 'Astronomy', 1, 6);
INSERT INTO public.course (id, name, department_id, credits) VALUES (10, 'Clinical Neuroscience', 2, 5);
INSERT INTO public.course (id, name, department_id, credits) VALUES (1, 'Pure Mathematics and Mathematical Statistics', 1, 3);
INSERT INTO public.course (id, name, department_id, credits) VALUES (6, 'Geography', 1, 7);
INSERT INTO public.course (id, name, department_id, credits) VALUES (8, 'Chemistry', 1, 1);
INSERT INTO public.course (id, name, department_id, credits) VALUES (5, 'Physics', 1, 8);
INSERT INTO public.course (id, name, department_id, credits) VALUES (3, 'Earth Science', 1, 7);
INSERT INTO public.course (id, name, department_id, credits) VALUES (7, 'Materials Science and Metallurgy', 1, 5);
INSERT INTO public.course (id, name, department_id, credits) VALUES (2, 'Applied Mathematics and Theoretical Physics', 1, 5);
INSERT INTO public.course (id, name, department_id, credits) VALUES (0, 'string', 0, 0);


CREATE TABLE public.department (
    id bigint NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.department OWNER TO postgres;


--
-- TOC entry 3590 (class 0 OID 16426)
-- Dependencies: 210
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.department (id, name) VALUES (3, 'Biological Sciences');
INSERT INTO public.department (id, name) VALUES (5, 'Technology');
INSERT INTO public.department (id, name) VALUES (6, 'Humanities & Social Sciences');
INSERT INTO public.department (id, name) VALUES (2, 'Clinical Medicine');
INSERT INTO public.department (id, name) VALUES (4, 'Arts and Humanities');
INSERT INTO public.department (id, name) VALUES (1, 'Physical Sciences');


--
-- TOC entry 211 (class 1259 OID 16431)
-- Name: professor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.professor (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    department_id bigint NOT NULL
);


ALTER TABLE public.professor OWNER TO postgres;


--
-- TOC entry 3591 (class 0 OID 16431)
-- Dependencies: 211
-- Data for Name: professor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.professor (id, name, department_id) VALUES (1, 'John Doe', 5);
INSERT INTO public.professor (id, name, department_id) VALUES (8, 'Camden Lin', 1);
INSERT INTO public.professor (id, name, department_id) VALUES (9, 'Daniel Hicks', 2);
INSERT INTO public.professor (id, name, department_id) VALUES (2, 'Frida Mcintosh', 2);
INSERT INTO public.professor (id, name, department_id) VALUES (10, 'Timothy Hickman', 4);
INSERT INTO public.professor (id, name, department_id) VALUES (3, 'Grace Avery', 1);
INSERT INTO public.professor (id, name, department_id) VALUES (4, 'Ada Osborne', 3);
INSERT INTO public.professor (id, name, department_id) VALUES (7, 'Sarahi Barry', 2);
INSERT INTO public.professor (id, name, department_id) VALUES (5, 'Rowan Graves', 1);
INSERT INTO public.professor (id, name, department_id) VALUES (6, 'Selena Owen', 5);


--
-- TOC entry 212 (class 1259 OID 16436)
-- Name: schedule; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.schedule (
    professor_id bigint NOT NULL,
    course_id bigint NOT NULL,
    semester bigint NOT NULL,
    year bigint NOT NULL
);


ALTER TABLE public.schedule OWNER TO postgres;


--
-- TOC entry 3592 (class 0 OID 16436)
-- Dependencies: 212
-- Data for Name: schedule; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.schedule (professor_id, course_id, semester, year) VALUES (5, 3, 6, 2012);
INSERT INTO public.schedule (professor_id, course_id, semester, year) VALUES (7, 3, 1, 2013);
INSERT INTO public.schedule (professor_id, course_id, semester, year) VALUES (5, 7, 6, 2010);
INSERT INTO public.schedule (professor_id, course_id, semester, year) VALUES (2, 10, 2, 2004);
INSERT INTO public.schedule (professor_id, course_id, semester, year) VALUES (5, 1, 1, 2011);
INSERT INTO public.schedule (professor_id, course_id, semester, year) VALUES (2, 9, 4, 2005);
INSERT INTO public.schedule (professor_id, course_id, semester, year) VALUES (7, 10, 6, 2009);
INSERT INTO public.schedule (professor_id, course_id, semester, year) VALUES (5, 6, 4, 2007);
INSERT INTO public.schedule (professor_id, course_id, semester, year) VALUES (7, 9, 1, 2014);
INSERT INTO public.schedule (professor_id, course_id, semester, year) VALUES (9, 9, 5, 2011);


--
-- TOC entry 3443 (class 2606 OID 16425)
-- Name: course course_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT course_pkey PRIMARY KEY (id);


--
-- TOC entry 3445 (class 2606 OID 16430)
-- Name: department department_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (id);


--
-- TOC entry 3447 (class 2606 OID 16435)
-- Name: professor professor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT professor_pkey PRIMARY KEY (id);


--
-- TOC entry 3449 (class 2606 OID 16440)
-- Name: schedule schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (professor_id, course_id);
