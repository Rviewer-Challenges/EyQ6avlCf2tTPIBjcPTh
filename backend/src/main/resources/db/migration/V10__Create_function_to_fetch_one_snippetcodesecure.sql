CREATE OR REPLACE FUNCTION #[app_schema].fetch_snippet_code_secure(snippetcode_uid_in uuid)
 RETURNS #[app_schema].snippet_code_secure
 LANGUAGE plpgsql
AS $function$
declare
    __element  #[app_schema].snippet_code_secure;
	BEGIN
        select sc.snippet_code_uid,
            sc0.title,
            sc0.description,
            sc0.code,
            (select pl1.programming_language_name from #[app_schema].programming_language pl1 where pl1.language_uid = sc0.language_uid ) as language_name,
            (select pv1.version_code from #[app_schema].proglang_version pv1 where pv1.proglang_version_uid = sc0.proglang_version_uid) as lang_version,
            (select array_agg(t0.tag_name) from #[app_schema].tag t0 inner join #[app_schema].snippet_code_tag sct0 using(tag_uid) where sct0.snippet_code_uid = sc.snippet_code_uid),
            sc.create_at
            into __element
        from #[app_schema].snippet_code sc0
        where sc0.snippet_code_uid = snippetcode_uid_in;
        return __element;
    END;
$function$
;