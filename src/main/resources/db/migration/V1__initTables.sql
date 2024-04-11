CREATE TABLE IF NOT EXISTS public.users (
    id SERIAL NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT user_pk PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.token (
    id SERIAL NOT NULL,
    token character varying(255) NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT token_pk PRIMARY KEY (id),
    CONSTRAINT "token_fk" FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS public.todo (
    id SERIAL PRIMARY KEY NOT NULL,
    description CHARACTER VARYING(255) NOT NULL,
    completed BOOLEAN NOT NULL,
    version INTEGER NOT NULL,
    userId INTEGER NOT NULL
);