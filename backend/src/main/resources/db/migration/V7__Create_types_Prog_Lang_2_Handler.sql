CREATE TYPE #[app_schema].programming_language_upsert
    AS (programming_language_name varchar(30));

CREATE TYPE #[app_schema].programming_language_secure
    AS (
        programming_language_uid uuid,
        programming_language_name varchar(30),
        create_at timestamp
    );