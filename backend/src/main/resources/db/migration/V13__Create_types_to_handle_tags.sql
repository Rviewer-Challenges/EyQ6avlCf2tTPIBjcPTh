CREATE TYPE #[app_schema].tag_upsert
    AS (
        name varchar(255)
    );

CREATE TYPE #[app_schema].tag_secure
    AS (
        tag_uid uuid,
        name varchar(255)
    );