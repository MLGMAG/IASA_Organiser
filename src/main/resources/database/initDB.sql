CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE public."Human"
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    "firstName" text COLLATE pg_catalog."default" NOT NULL,
    "lastName" text COLLATE pg_catalog."default" NOT NULL,
    "phoneNumber" text COLLATE pg_catalog."default" NOT NULL,
    email text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Human_pkey" PRIMARY KEY (id)
)

CREATE TABLE public."Link"
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    url text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Link_pkey" PRIMARY KEY (id)
)

CREATE TABLE public."Place"
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    country text COLLATE pg_catalog."default" NOT NULL,
    city text COLLATE pg_catalog."default" NOT NULL,
    street text COLLATE pg_catalog."default" NOT NULL,
    "number" bigint,
    letter text COLLATE pg_catalog."default",
    CONSTRAINT "Place_pkey" PRIMARY KEY (id)
)

CREATE TABLE public."Tag"
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Tag_pkey" PRIMARY KEY (id)
)

CREATE TABLE public."Type"
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Type_pkey" PRIMARY KEY (id)
)

CREATE TABLE public."Event"
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    place_id uuid NOT NULL,
    date date NOT NULL,
    "time" time without time zone NOT NULL,
    duration time without time zone NOT NULL,
    priority text COLLATE pg_catalog."default" NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Event_pkey" PRIMARY KEY (id),
    CONSTRAINT place_fk FOREIGN KEY (place_id)
        REFERENCES public."Place" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

CREATE TABLE public.human_event
(
    human_id uuid NOT NULL,
    event_id uuid NOT NULL,
    CONSTRAINT human_event_pkey PRIMARY KEY (human_id, event_id),
    CONSTRAINT event_fk FOREIGN KEY (event_id)
        REFERENCES public."Event" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT human_fk FOREIGN KEY (human_id)
        REFERENCES public."Human" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE public.tag_event
(
    tag_id uuid NOT NULL,
    event_id uuid NOT NULL,
    CONSTRAINT tag_event_pkey PRIMARY KEY (tag_id, event_id),
    CONSTRAINT event_fk FOREIGN KEY (event_id)
        REFERENCES public."Event" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT tag_fk FOREIGN KEY (tag_id)
        REFERENCES public."Tag" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE public.link_event
(
    link_id uuid NOT NULL,
    event_id uuid NOT NULL,
    CONSTRAINT link_event_pkey PRIMARY KEY (link_id, event_id),
    CONSTRAINT event_fk FOREIGN KEY (event_id)
        REFERENCES public."Event" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT link_fk FOREIGN KEY (link_id)
        REFERENCES public."Link" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE public.type_event
(
    type_id uuid NOT NULL,
    event_id uuid NOT NULL,
    CONSTRAINT type_event_pkey PRIMARY KEY (type_id, event_id),
    CONSTRAINT event_fk FOREIGN KEY (event_id)
        REFERENCES public."Event" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT type_fk FOREIGN KEY (type_id)
        REFERENCES public."Type" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
