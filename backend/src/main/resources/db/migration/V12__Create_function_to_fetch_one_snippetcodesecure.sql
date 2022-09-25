CREATE OR REPLACE FUNCTION #[app_schema].fetch_snippet_code_secure(snippetcode_uid uuid)
 RETURNS #[app_schema].snippet_code_secure
 LANGUAGE plpgsql
AS $function$
declare
    __element  #[app_schema].snippet_code_secure;
	BEGIN
        select sc.snippet_code_uid,
	        sc.title,
	        sc.description,
	        sc.code,
            pl.programming_language_name as language_name,
            pv.version_code as lang_version,
            '{"FOO","BARR"}',
            sc.create_at
            into __element
        from #[app_schema].snippet_code sc  inner join #[app_schema].proglang_version pv using(proglang_version_uid)
                                            inner join #[app_schema].programming_language pl using(programming_language_uid)
        where sc.snippet_code_uid = snippetcode_uid;
        return __element;
    END;
$function$
;