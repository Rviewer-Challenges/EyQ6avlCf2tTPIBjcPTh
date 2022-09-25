

alter table #[app_schema].snippet_code drop column if exists rating;

CREATE TYPE #[app_schema].snippet_code_upsert
    AS (
        title varchar(100),
        description varchar(300),
        code text,
        proglang_version_uid uuid
    );

CREATE TYPE #[app_schema].snippet_code_secure
    AS (
        snippet_code_uid uuid,
        title varchar(100),
        description varchar(300),
        code text,
        language_name varchar,
        lang_version varchar,
        tags varchar[],
        create_at timestamp
    );


CREATE TYPE app.snippet_code_page
    AS (
        total_items int4,
        total_page int4,
        prev_page int4,
        next_page int4,
        payload #[app_schema].snippet_code_secure[]
	);