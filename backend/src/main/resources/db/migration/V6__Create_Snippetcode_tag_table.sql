CREATE TABLE #[app_schema].snippet_code_tag (
	snippet_code_uid uuid NOT NULL REFERENCES #[app_schema].snippet_code,
	tag_uid uuid NOT NULL REFERENCES #[app_schema].tag,
    create_by varchar(80) NOT NULL,
	create_at timestamp NOT NULL,
    PRIMARY KEY (snippet_code_uid, tag_uid)
);