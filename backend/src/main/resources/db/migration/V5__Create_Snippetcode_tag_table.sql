CREATE TABLE #[app_schema].snippet_code_tag (
	snippet_code_uid uuid NOT NULL,
	tag_uid uuid NOT NULL,
	CONSTRAINT fk_snippet_code FOREIGN KEY (snippet_code_uid) REFERENCES #[app_schema].snippet_code(snippet_code_uid),
	CONSTRAINT fk_tag FOREIGN KEY (tag_uid) REFERENCES #[app_schema].tag(tag_uid)
);