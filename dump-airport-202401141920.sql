--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2024-01-14 19:20:02

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
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4842 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 16734)
-- Name: airlinecompanies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.airlinecompanies (
    companyid integer NOT NULL,
    name character varying(255) NOT NULL,
    headquarters character varying(255),
    contactinformation character varying(255)
);


ALTER TABLE public.airlinecompanies OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16733)
-- Name: airlinecompanies_companyid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.airlinecompanies_companyid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.airlinecompanies_companyid_seq OWNER TO postgres;

--
-- TOC entry 4843 (class 0 OID 0)
-- Dependencies: 215
-- Name: airlinecompanies_companyid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.airlinecompanies_companyid_seq OWNED BY public.airlinecompanies.companyid;


--
-- TOC entry 220 (class 1259 OID 16764)
-- Name: flights; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flights (
    flightid integer NOT NULL,
    departureairport character varying(255) NOT NULL,
    destinationairport character varying(255) NOT NULL,
    departuretime timestamp without time zone,
    arrivaltime timestamp without time zone,
    planeid integer,
    crewid integer,
    empty_seats integer
);


ALTER TABLE public.flights OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16763)
-- Name: flights_flightid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.flights_flightid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.flights_flightid_seq OWNER TO postgres;

--
-- TOC entry 4844 (class 0 OID 0)
-- Dependencies: 219
-- Name: flights_flightid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.flights_flightid_seq OWNED BY public.flights.flightid;


--
-- TOC entry 226 (class 1259 OID 16902)
-- Name: pilots; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pilots (
    pilotid integer NOT NULL,
    name character varying,
    contacts character varying,
    companyid integer
);


ALTER TABLE public.pilots OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16901)
-- Name: pilots_pilotid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pilots_pilotid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pilots_pilotid_seq OWNER TO postgres;

--
-- TOC entry 4845 (class 0 OID 0)
-- Dependencies: 225
-- Name: pilots_pilotid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pilots_pilotid_seq OWNED BY public.pilots.pilotid;


--
-- TOC entry 218 (class 1259 OID 16743)
-- Name: planes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.planes (
    planeid integer NOT NULL,
    model character varying(255) NOT NULL,
    capacity integer,
    airlineid integer
);


ALTER TABLE public.planes OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16742)
-- Name: planes_planeid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.planes_planeid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.planes_planeid_seq OWNER TO postgres;

--
-- TOC entry 4846 (class 0 OID 0)
-- Dependencies: 217
-- Name: planes_planeid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.planes_planeid_seq OWNED BY public.planes.planeid;


--
-- TOC entry 221 (class 1259 OID 16783)
-- Name: tickets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tickets (
    flightid integer,
    seatnumber character varying(10),
    ticketprice numeric(10,2),
    ticketid integer NOT NULL,
    userid integer NOT NULL
);


ALTER TABLE public.tickets OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16867)
-- Name: tickets_ticketid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tickets_ticketid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tickets_ticketid_seq OWNER TO postgres;

--
-- TOC entry 4847 (class 0 OID 0)
-- Dependencies: 223
-- Name: tickets_ticketid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tickets_ticketid_seq OWNED BY public.tickets.ticketid;


--
-- TOC entry 222 (class 1259 OID 16857)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    username character varying(50) NOT NULL,
    pass character varying NOT NULL,
    userid integer NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16876)
-- Name: users_userid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_userid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_userid_seq OWNER TO postgres;

--
-- TOC entry 4848 (class 0 OID 0)
-- Dependencies: 224
-- Name: users_userid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_userid_seq OWNED BY public.users.userid;


--
-- TOC entry 4659 (class 2604 OID 16737)
-- Name: airlinecompanies companyid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.airlinecompanies ALTER COLUMN companyid SET DEFAULT nextval('public.airlinecompanies_companyid_seq'::regclass);


--
-- TOC entry 4661 (class 2604 OID 16767)
-- Name: flights flightid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flights ALTER COLUMN flightid SET DEFAULT nextval('public.flights_flightid_seq'::regclass);


--
-- TOC entry 4664 (class 2604 OID 16905)
-- Name: pilots pilotid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pilots ALTER COLUMN pilotid SET DEFAULT nextval('public.pilots_pilotid_seq'::regclass);


--
-- TOC entry 4660 (class 2604 OID 16746)
-- Name: planes planeid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planes ALTER COLUMN planeid SET DEFAULT nextval('public.planes_planeid_seq'::regclass);


--
-- TOC entry 4662 (class 2604 OID 16868)
-- Name: tickets ticketid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets ALTER COLUMN ticketid SET DEFAULT nextval('public.tickets_ticketid_seq'::regclass);


--
-- TOC entry 4663 (class 2604 OID 16877)
-- Name: users userid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN userid SET DEFAULT nextval('public.users_userid_seq'::regclass);


--
-- TOC entry 4826 (class 0 OID 16734)
-- Dependencies: 216
-- Data for Name: airlinecompanies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.airlinecompanies (companyid, name, headquarters, contactinformation) FROM stdin;
1	Delta Air Lines	Atlanta, Georgia	contact@delta.com
2	Singapore Airlines	Singapore	contact@singaporeair.com
3	Emirates	Dubai, UAE	contact@emirates.com
4	Lufthansa	Cologne, Germany	contact@lufthansa.com
5	Qantas	Sydney, Australia	contact@qantas.com
6	British Airways	London, UK	contact@britishairways.com
7	Air Canada	Montreal, Canada	contact@aircanada.com
8	Cathay Pacific	Hong Kong	contact@cathaypacific.com
9	United Airlines	Chicago, Illinois	contact@united.com
10	ANA All Nippon Airways	Tokyo, Japan	contact@ana.co.jp
\.


--
-- TOC entry 4830 (class 0 OID 16764)
-- Dependencies: 220
-- Data for Name: flights; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flights (flightid, departureairport, destinationairport, departuretime, arrivaltime, planeid, crewid, empty_seats) FROM stdin;
91	JFK	LAX	2024-01-07 08:00:00	2024-01-07 11:00:00	1	1	\N
92	LAX	ORD	2024-01-08 10:00:00	2024-01-08 15:00:00	2	2	\N
93	ATL	CDG	2024-01-09 12:00:00	2024-01-09 18:00:00	3	3	\N
94	SFO	JFK	2024-01-10 14:00:00	2024-01-10 20:00:00	4	4	\N
95	LHR	SYD	2024-01-11 16:00:00	2024-01-12 08:00:00	5	5	\N
96	ORD	SFO	2024-01-13 10:00:00	2024-01-13 15:00:00	6	6	\N
97	SFO	ATL	2024-01-14 12:00:00	2024-01-14 18:00:00	7	7	\N
98	SYD	JFK	2024-01-15 14:00:00	2024-01-15 20:00:00	8	8	\N
99	LAX	CDG	2024-01-16 16:00:00	2024-01-16 22:00:00	9	9	\N
100	CDG	ORD	2024-01-17 18:00:00	2024-01-17 23:00:00	10	1	\N
101	ATL	LHR	2024-01-18 20:00:00	2024-01-19 01:00:00	11	2	\N
102	JFK	SYD	2024-01-19 22:00:00	2024-01-20 08:00:00	12	2	\N
103	SYD	ORD	2024-01-20 00:00:00	2024-01-20 03:00:00	5	3	\N
104	ORD	ATL	2024-01-21 02:00:00	2024-01-21 07:00:00	4	4	\N
105	LHR	JFK	2024-01-21 04:00:00	2024-01-21 10:00:00	7	5	\N
\.


--
-- TOC entry 4836 (class 0 OID 16902)
-- Dependencies: 226
-- Data for Name: pilots; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pilots (pilotid, name, contacts, companyid) FROM stdin;
1	John Smith	john.smith@example.com	1
2	Alice Johnson	alice.johnson@example.com	2
3	Michael Davis	michael.davis@example.com	3
4	Emily Brown	emily.brown@example.com	4
5	Daniel Martinez	daniel.martinez@example.com	5
6	Olivia Wilson	olivia.wilson@example.com	1
7	James Lee	james.lee@example.com	2
8	Sophia Taylor	sophia.taylor@example.com	3
9	Liam Anderson	liam.anderson@example.com	4
10	Emma Garcia	emma.garcia@example.com	5
11	Noah Miller	noah.miller@example.com	1
12	Ava Rodriguez	ava.rodriguez@example.com	2
13	Isabella Jackson	isabella.jackson@example.com	3
14	Ethan Thomas	ethan.thomas@example.com	4
15	Mia White	mia.white@example.com	5
\.


--
-- TOC entry 4828 (class 0 OID 16743)
-- Dependencies: 218
-- Data for Name: planes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.planes (planeid, model, capacity, airlineid) FROM stdin;
1	Boeing 737	150	1
2	Airbus A320	180	2
3	Boeing 777	300	1
4	Airbus A380	500	2
5	Embraer E190	100	3
6	Boeing 747	400	1
7	Airbus A330	250	2
8	Bombardier CRJ900	70	3
9	Boeing 787	250	1
10	Airbus A350	300	2
11	Boeing 737	150	1
12	Airbus A320	180	2
\.


--
-- TOC entry 4831 (class 0 OID 16783)
-- Dependencies: 221
-- Data for Name: tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tickets (flightid, seatnumber, ticketprice, ticketid, userid) FROM stdin;
91	1	100.00	85	2
91	2	100.00	86	2
91	3	100.00	87	2
91	4	100.00	88	2
96	1	100.00	91	55
96	2	100.00	92	55
96	3	100.00	93	55
96	4	100.00	94	55
93	1	100.00	95	55
93	2	100.00	96	55
\.


--
-- TOC entry 4832 (class 0 OID 16857)
-- Dependencies: 222
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (username, pass, userid) FROM stdin;
Smith	qwerty	4
Michael	jordan	5
hello	world	55
hi	hi	56
h	h	80
pp	pp	82
Smiley	yee	37
smiley	yee	38
poo	p	39
q	q	40
l	l	41
PPP	PP	42
Ultimate admin	rrr	1
LORENA	lorena	83
john	hello	36
John	hello	2
p	p	84
-905399549	00	87
123	123	44
419332420	00	88
oo	oo	46
cc	cc	47
calin	1	51
Andrew	0000	3
\.


--
-- TOC entry 4849 (class 0 OID 0)
-- Dependencies: 215
-- Name: airlinecompanies_companyid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.airlinecompanies_companyid_seq', 10, true);


--
-- TOC entry 4850 (class 0 OID 0)
-- Dependencies: 219
-- Name: flights_flightid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.flights_flightid_seq', 105, true);


--
-- TOC entry 4851 (class 0 OID 0)
-- Dependencies: 225
-- Name: pilots_pilotid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pilots_pilotid_seq', 15, true);


--
-- TOC entry 4852 (class 0 OID 0)
-- Dependencies: 217
-- Name: planes_planeid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.planes_planeid_seq', 30, true);


--
-- TOC entry 4853 (class 0 OID 0)
-- Dependencies: 223
-- Name: tickets_ticketid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tickets_ticketid_seq', 96, true);


--
-- TOC entry 4854 (class 0 OID 0)
-- Dependencies: 224
-- Name: users_userid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_userid_seq', 88, true);


--
-- TOC entry 4666 (class 2606 OID 16741)
-- Name: airlinecompanies airlinecompanies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.airlinecompanies
    ADD CONSTRAINT airlinecompanies_pkey PRIMARY KEY (companyid);


--
-- TOC entry 4670 (class 2606 OID 16771)
-- Name: flights flights_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flights
    ADD CONSTRAINT flights_pkey PRIMARY KEY (flightid);


--
-- TOC entry 4678 (class 2606 OID 16909)
-- Name: pilots pilots_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pilots
    ADD CONSTRAINT pilots_pk PRIMARY KEY (pilotid);


--
-- TOC entry 4668 (class 2606 OID 16748)
-- Name: planes planes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planes
    ADD CONSTRAINT planes_pkey PRIMARY KEY (planeid);


--
-- TOC entry 4672 (class 2606 OID 16875)
-- Name: tickets tickets_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pk PRIMARY KEY (ticketid);


--
-- TOC entry 4674 (class 2606 OID 16884)
-- Name: users users_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (userid);


--
-- TOC entry 4676 (class 2606 OID 16911)
-- Name: users users_un; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_un UNIQUE (username);


--
-- TOC entry 4679 (class 2606 OID 16749)
-- Name: planes fk_airline; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planes
    ADD CONSTRAINT fk_airline FOREIGN KEY (airlineid) REFERENCES public.airlinecompanies(companyid);


--
-- TOC entry 4681 (class 2606 OID 16789)
-- Name: tickets fk_flight; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT fk_flight FOREIGN KEY (flightid) REFERENCES public.flights(flightid);


--
-- TOC entry 4680 (class 2606 OID 16772)
-- Name: flights fk_plane; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flights
    ADD CONSTRAINT fk_plane FOREIGN KEY (planeid) REFERENCES public.planes(planeid);


-- Completed on 2024-01-14 19:20:02

--
-- PostgreSQL database dump complete
--

