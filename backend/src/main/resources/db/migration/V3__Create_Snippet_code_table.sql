CREATE TABLE #[app_schema].snippet_code (
	snippet_code_uid uuid default public.uuid_generate_v4(),
	code text NOT NULL,
	description varchar(300) NOT NULL,
	rating real NOT NULL,
	title varchar(100) NOT NULL,
	programming_language_uid uuid NOT NULL,
	CONSTRAINT snippet_code_pkey PRIMARY KEY (snippet_code_uid),
	CONSTRAINT uq_title UNIQUE (title),
	CONSTRAINT fk_language_to_run FOREIGN KEY (programming_language_uid) REFERENCES #[app_schema].programming_language(programming_language_uid)
)INHERITS(#[app_schema].auditor);