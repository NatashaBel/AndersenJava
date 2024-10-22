CREATE TYPE public.ticket_type AS ENUM (
    'DAY',
    'WEEK',
    'MONTH',
    'YEAR'
);

CREATE TABLE public."user_data" (
    id uuid PRIMARY KEY NOT NULL UNIQUE,
    name character varying NOT NULL,
    creation_date timestamp with time zone NOT NULL
);

CREATE TABLE public."ticket_data" (
    id uuid PRIMARY KEY NOT NULL UNIQUE,
    user_id uuid REFERENCES user_data(id) ON DELETE CASCADE NOT NULL,
    ticket_type public.ticket_type NOT NULL,
    creation_date timestamp with time zone NOT NULL
);