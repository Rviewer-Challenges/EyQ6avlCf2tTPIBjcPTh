CREATE OR REPLACE FUNCTION #[app_schema].upsert_snippetcode(
    snippet_code_uid_in uuid,
    modify_by_in varchar,
	description_in varchar(300),
	code_in text,
	proglang_uid_in uuid,
	proglang_version_uid_in uuid,
	tags_uuids uuid[],
--Outputs
    out code_uid_out uuid,
    out title_out varchar(100),
    out description_out varchar(300),
    out code_out text,
    out language_name_out varchar,
    out lang_version_out varchar,
    out tags_out varchar[],
    out create_at_out timestamp
)
 RETURNS record
 LANGUAGE plpgsql
AS $function$
declare
    _snippetcode #[app_schema].snippet_code%ROWTYPE;
	_current_timestamp timestamp = CURRENT_TIMESTAMP;
	_language_uid uuid;
	_tag_uid uuid;
	BEGIN

        SELECT * INTO _snippetcode FROM #[app_schema].snippet_code WHERE snippet_code_uid=snippet_code_uid_in;
	    IF FOUND THEN

            DELETE FROM #[app_schema].snippet_code_tag
            WHERE snippet_code_uid=_snippetcode.snippet_code_uid;

            UPDATE #[app_schema].snippet_code
            SET modify_by=modify_by_in,
                modify_at=_current_timestamp,
                "version"=_snippetcode.version + 1,
                is_enable=true,
                code=COALESCE(code_in, _snippetcode.code),
                description=COALESCE(description_in, _snippetcode.description),
                proglang_uid=COALESCE(proglang_uid_in, _snippetcode.proglang_uid),
                proglang_version_uid=COALESCE(proglang_version_uid_in, _snippetcode.proglang_version_uid)
            WHERE snippet_code_uid=_snippetcode.snippet_code_uid;

            SELECT * INTO _snippetcode FROM #[app_schema].snippet_code WHERE snippet_code_uid=snippet_code_uid_in;

            code_uid_out=_snippetcode.snippet_code_uid;
            title_out=_snippetcode.title;
            description_out=_snippetcode.description;
            code_out=_snippetcode.code;
            create_at_out=_snippetcode.create_at;

        ELSE
            INSERT INTO #[app_schema].snippet_code(create_by, modify_by, create_at, modify_at, "version", is_enable, snippet_code_uid, code, description, title, proglang_uid, proglang_version_uid)
            VALUES(modify_by_in, modify_by_in, _current_timestamp, _current_timestamp, 1, true, snippet_code_uid_in, code_in, description_in, title_in, proglang_uid_in, proglang_version_uid_in)
            RETURNING * INTO _snippetcode;
	    END IF;

        select pl1.programming_language_name into language_name_out
            from #[app_schema].programming_language pl1
            where pl1.language_uid = proglang_uid_in;

        select pv1.version_code into lang_version_out
            from #[app_schema].proglang_version pv1
            where pv1.proglang_version_uid = proglang_version_uid_in;


        if tags_uuids is not null then
            FOREACH _tag_uid IN ARRAY tags_uuids
            LOOP
                INSERT INTO #[app_schema].snippet_code_tag(snippet_code_uid, tag_uid)
                    VALUES(snippet_code_uid_in, _tag_uid);
            END LOOP;

            SELECT array_agg(t.tag_name) INTO tags_out
            FROM #[app_schema].tag t
            WHERE t.tag_uid = ANY(tags_uuids);
        end if;
    END;
$function$
;

