CREATE TYPE #[app_schema].proglang_version_upsert AS (version_code varchar(50));

CREATE TYPE #[app_schema].proglang_version_secure AS (
    proglang_version_uid uuid,
    programming_language_uid uuid,
    version_code varchar(50),
    create_at timestamp
);