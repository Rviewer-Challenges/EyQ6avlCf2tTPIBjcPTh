CREATE OR REPLACE FUNCTION #[app_schema].fetch_snippet_code_secure_jooqadapt(
    snippetcode_uid_in uuid,
    out snippet_code_uid uuid,
    out title varchar(100),
    out description varchar(300),
    out code text,
    out language_name varchar,
    out lang_version varchar,
    out tags varchar[],
    out create_at timestamp
)
 RETURNS record
 LANGUAGE plpgsql
AS $function$
declare
	elemento #[app_schema].snippet_code_secure;
	BEGIN
        elemento = #[app_schema].fetch_snippet_code_secure(snippetcode_uid_in);
        snippet_code_uid = elemento.snippet_code_uid;
        title = elemento.title;
        description = elemento.description;
        code = elemento.code;
        language_name = elemento.language_name;
        lang_version = elemento.lang_version;
        tags = elemento.tags;
        create_at = elemento.create_at;
    END;
$function$
;