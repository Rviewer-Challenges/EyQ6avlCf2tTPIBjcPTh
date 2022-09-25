package com.rebirth.mycode.web.rest;

import com.rebirth.mycode.domain.udt.pojos.ProglangVersionSecure;
import com.rebirth.mycode.services.ProgLangVersionService;
import com.rebirth.mycode.web.dto.ProglangVersionUpsertWithValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/proglangversion")
public class ProglangVersionController extends BaseController {

    private final ProgLangVersionService progLangVersionService;

    public ProglangVersionController(ProgLangVersionService progLangVersionService) {
        this.progLangVersionService = progLangVersionService;
    }

    @GetMapping
    public ResponseEntity<List<ProglangVersionSecure>> getProgLangVersion() {
        List<ProglangVersionSecure> programmingLanguages = progLangVersionService.findAll();
        return ResponseEntity.ok(programmingLanguages);
    }

    @PostMapping
    public ResponseEntity<ProglangVersionSecure> postProgLangVersion(
            @RequestBody @Valid ProglangVersionUpsertWithValidation proglangVersionUpsert,
            Errors errors) {
        validateDto("ProglangVersion", errors);
        ProglangVersionSecure newProglangVersionSecure = progLangVersionService.insert(proglangVersionUpsert);
        UUID proglangVersionUUID = newProglangVersionSecure.getProgrammingLanguageUid();
        URI uriFromCreatedEntity = createUri4newEntity(proglangVersionUUID);
        return ResponseEntity.created(uriFromCreatedEntity)
                .body(newProglangVersionSecure);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProglangVersionSecure> getProgLangVersion(@PathVariable("uuid") UUID uuid) {
        ProglangVersionSecure programmingLanguage = progLangVersionService.findById(uuid);
        return ResponseEntity.ok(programmingLanguage);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProglangVersionSecure> putProgLangVersion(@PathVariable("uuid") UUID uuid,
                                                                    @RequestBody @Valid ProglangVersionUpsertWithValidation proglangVersionUpsert,
                                                                    Errors errors) {
        validateDto("ProgrammingLanguage", errors);
        ProglangVersionSecure proglangVersionSecure = progLangVersionService.update(proglangVersionUpsert, uuid);
        return ResponseEntity.ok(proglangVersionSecure);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteProgLangVersion(@PathVariable("uuid") UUID uuid) {
        progLangVersionService.delete(uuid);
        return ResponseEntity
                .noContent()
                .build();
    }


}
