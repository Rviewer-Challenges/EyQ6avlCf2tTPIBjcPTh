CREATE TABLE #[app_schema].programming_language(
	programming_language_uid uuid default public.uuid_generate_v4(),
	programming_language_name varchar(30) NOT NULL,
	CONSTRAINT languages_pkey PRIMARY KEY (programming_language_uid),
	CONSTRAINT uq_language_name UNIQUE (programming_language_name)
)INHERITS(#[app_schema].auditor);