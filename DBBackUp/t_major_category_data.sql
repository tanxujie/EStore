--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.3
-- Dumped by pg_dump version 9.6.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- Data for Name: t_major_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO t_major_category VALUES (1, '手串', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_major_category VALUES (2, '项链', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_major_category VALUES (3, '圆珠 桶珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_major_category VALUES (4, '平安扣', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_major_category VALUES (5, '吊坠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_major_category VALUES (6, '雕刻件', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_major_category VALUES (7, '三通', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_major_category VALUES (8, '戒指耳钉', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_major_category VALUES (9, '佛珠配饰', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_major_category VALUES (10, '老蜜蜡', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_major_category VALUES (11, '无事牌', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_major_category VALUES (12, '手镯', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_major_category VALUES (13, '车挂', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_major_category VALUES (14, '翡翠', NULL, NULL, NULL, NULL, NULL, 1);


--
-- Name: t_major_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('t_major_category_id_seq', 14, true);


--
-- PostgreSQL database dump complete
--

