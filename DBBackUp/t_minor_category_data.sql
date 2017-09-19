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
-- Data for Name: t_minor_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO t_minor_category VALUES (1, 1, '圆珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (2, 1, '随形', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (3, 1, '桶珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (4, 1, '枣珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (5, 1, '回纹珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (6, 1, '车轮珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (7, 1, '算盘珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (8, 1, '苹果珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (9, 1, '手排', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (10, 1, '角度珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (11, 2, '圆珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (12, 2, '随形', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (13, 2, '桶珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (14, 2, '108颗', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (15, 2, '塔链', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (16, 2, '锁骨链', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (17, 2, '配链', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (18, 2, '角度珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (19, 3, '苹果珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (20, 3, '圆珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (21, 3, '老型桶珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (22, 3, '天珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (23, 3, '桶珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (24, 3, '鼓珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (25, 3, '枣珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (26, 4, '鱼眼扣', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (27, 4, '公主扣', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (28, 4, '子母扣', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (29, 4, '平安扣', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (30, 4, '面包圈', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (31, 4, '藏式平安扣', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (32, 5, '水滴', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (33, 5, '随形', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (34, 5, '蛋面', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (35, 5, '镶嵌', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (36, 5, '柠檬片', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (37, 6, '阴雕', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (38, 6, '浮雕', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (39, 6, '立体雕', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (40, 7, '藏式', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (41, 7, '一体', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (42, 7, '圆珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (43, 7, '桶珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (44, 8, '戒托', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (45, 8, '耳托', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (46, 9, '背云', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (47, 9, '隔片', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (48, 10, '枣珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (49, 10, '桶珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (50, 10, '平安扣', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (51, 10, '圆珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (52, 10, '苹果珠', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (53, 11, '无事牌', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (54, 12, '手镯', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (55, 13, '车挂', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_minor_category VALUES (56, 14, '翡翠', NULL, NULL, NULL, NULL, NULL, 1);


--
-- Name: t_minor_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('t_minor_category_id_seq', 56, true);


--
-- PostgreSQL database dump complete
--

