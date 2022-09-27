CREATE TABLE #[app_schema].proglang_version(
	proglang_version_uid uuid default public.uuid_generate_v4(),
	version_code varchar(50) NOT NULL,
    programming_language_uid uuid NOT NULL,
	CONSTRAINT prog_lang_version_pkey PRIMARY KEY (proglang_version_uid),
    CONSTRAINT fk_language_owner FOREIGN KEY (programming_language_uid) REFERENCES #[app_schema].programming_language(programming_language_uid)
)INHERITS(#[app_schema].auditor);