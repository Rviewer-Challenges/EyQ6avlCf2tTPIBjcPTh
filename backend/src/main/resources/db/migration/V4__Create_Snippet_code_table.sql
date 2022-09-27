CREATE TABLE #[app_schema].snippet_code (
	snippet_code_uid uuid default public.uuid_generate_v4(),
	title varchar(100) NOT NULL,
	description varchar(300) NOT NULL,
	code text NOT NULL,
	proglang_uid uuid NOT NULL REFERENCES #[app_schema].programming_language,
	proglang_version_uid uuid NOT NULL REFERENCES #[app_schema].proglang_version,
	CONSTRAINT snippet_code_pkey PRIMARY KEY (snippet_code_uid),
	CONSTRAINT uq_title UNIQUE (title)
)INHERITS(#[app_schema].auditor);