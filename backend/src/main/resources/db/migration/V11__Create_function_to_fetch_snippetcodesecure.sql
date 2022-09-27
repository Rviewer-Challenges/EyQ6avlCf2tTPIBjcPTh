CREATE OR REPLACE FUNCTION #[app_schema].fetch_page_snippet_code_secure(page_number integer, page_size integer, OUT total_items integer, OUT total_page integer, OUT prev_page integer, OUT next_page integer, OUT payload json)
 RETURNS record
 LANGUAGE plpgsql
AS $function$
declare
	total integer;
	__total_page integer;
	contenido #[app_schema].snippet_code_secure[] = '{}';
	elemento #[app_schema].snippet_code_secure;
	snippet_code_uid uuid;
	BEGIN

		select count(*) into total
		from #[app_schema].snippet_code sc inner join #[app_schema].proglang_version pv using(proglang_version_uid)
            							   inner join #[app_schema].programming_language pl using(programming_language_uid);

        __total_page = total / page_size;
        if (total % page_size) > 0 then
        	__total_page = __total_page + 1;
        end if;

       	total_items = total;
       	total_page = __total_page;
   		prev_page = page_number - 1;
   		next_page = page_number + 1;

       	if page_number = 0 then
       		prev_page = null;
       	end if;

       	if page_number = (total_page-1) then
       		next_page = null;
       	end if;

		for snippet_code_uid in select sc.snippet_code_uid
		            from #[app_schema].snippet_code sc
					order by sc.create_at
					limit page_size offset ( page_number * page_size)
		loop
			elemento = #[app_schema].fetch_snippet_code_secure(snippet_code_uid);
			contenido = array_append(contenido, elemento);
		end loop;
		payload = to_json(contenido);
    END;


$function$
;