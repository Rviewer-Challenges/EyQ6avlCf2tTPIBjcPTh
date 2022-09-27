CREATE TABLE #[app_schema].tag (
	tag_uid uuid default public.uuid_generate_v4(),
	tag_name varchar(255) NOT NULL,
	CONSTRAINT tags_pkey PRIMARY KEY (tag_uid),
	CONSTRAINT uq_tagname UNIQUE (tag_name)
)INHERITS(#[app_schema].auditor);