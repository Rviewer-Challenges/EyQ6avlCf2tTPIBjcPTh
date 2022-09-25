CREATE TABLE #[app_schema].auditor(
    create_by varchar(80) NOT NULL,
	modify_by varchar(80) NOT NULL,
	create_at timestamp NOT NULL,
	modify_at timestamp NOT NULL,
    version integer default 1,
    is_enable boolean default true
);