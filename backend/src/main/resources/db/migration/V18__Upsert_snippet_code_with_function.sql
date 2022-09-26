CREATE OR REPLACE FUNCTION #[app_schema].insert_snippetcode(
    create_by_in varchar,
	title_in varchar(100),
	description_in varchar(300),
	code_in text,
	proglang_version_uid_in uuid,
	tags_uuids uuid[],

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
	_new_tag_uuid uuid = uuid_generate_v4();
	_current_timestamp timestamp = CURRENT_TIMESTAMP;
	_language_uid uuid;
	_tag_uid uuid;
	_tag_names varchar[] = '{}';
	_tag_name varchar = '';
	BEGIN
        INSERT INTO #[app_schema].snippet_code(create_by, modify_by, create_at, modify_at, "version", is_enable, snippet_code_uid, code, description, title, proglang_version_uid)
        VALUES(create_by_in, create_by_in, _current_timestamp, _current_timestamp, 1, true, _new_tag_uuid, code_in, description_in, title_in, proglang_version_uid_in)
        RETURNING snippet_code_uid,
            title,
            description,
            code,
            create_at
        INTO code_uid_out,
            title_out,
            description_out,
            code_out,
            create_at_out;

        SELECT version_code, programming_language_uid into lang_version_out, _language_uid
        FROM #[app_schema].proglang_version pv0
        WHERE pv0.proglang_version_uid = proglang_version_uid_in;

        SELECT programming_language_name into language_name_out
        FROM #[app_schema].programming_language pl0
        WHERE pl0.programming_language_uid = _language_uid;

        FOREACH _tag_uid IN ARRAY tags_uuids
        LOOP
            INSERT INTO #[app_schema].snippet_code_tag(snippet_code_uid, tag_uid)
                VALUES(_new_tag_uuid, _tag_uid);
        END LOOP;

        SELECT array_agg(t.tag_name) INTO tags_out
        FROM #[app_schema].tag t
        WHERE t.tag_uid = ANY(tags_uuids);

    END;
$function$
;

