package com.rebirth.mycode.web.rest;

import com.rebirth.mycode.domain.udt.pojos.ProglangVersionSecure;
import com.rebirth.mycode.domain.udt.pojos.ProgrammingLanguageSecure;
import com.rebirth.mycode.domain.udt.pojos.ProgrammingLanguageUpsert;
import com.rebirth.mycode.services.ProgLangVersionService;
import com.rebirth.mycode.services.ProgrammingLanguageService;
import com.rebirth.mycode.web.dto.ProgrammingLanguageUpsertWithValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/languages")
public class ProgrammingLanguageController extends BaseController {

    private final ProgrammingLanguageService programmingLanguageService;
    private final ProgLangVersionService progLangVersionService;

    public ProgrammingLanguageController(ProgrammingLanguageService programmingLanguageService, ProgLangVersionService progLangVersionService) {
        this.programmingLanguageService = programmingLanguageService;
        this.progLangVersionService = progLangVersionService;
    }

    @GetMapping
    public ResponseEntity<List<ProgrammingLanguageSecure>> getLanguages() {
        List<ProgrammingLanguageSecure> programmingLanguages = this.programmingLanguageService.findAll();
        return ResponseEntity.ok(programmingLanguages);
    }

    @PostMapping
    public ResponseEntity<ProgrammingLanguageSecure> postLanguage(@RequestBody @Valid ProgrammingLanguageUpsertWithValidation programmingLanguage, Errors errors) {
        this.validateDto("ProgrammingLanguage", errors);
        ProgrammingLanguageSecure newProgrammingLanguage = this.programmingLanguageService.insert(programmingLanguage);
        UUID programmingLanguageUUID = newProgrammingLanguage.getProgrammingLanguageUid();
        URI uriFromCreatedEntity = this.createUri4newEntity(programmingLanguageUUID);
        return ResponseEntity.created(uriFromCreatedEntity)
                .body(newProgrammingLanguage);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProgrammingLanguageSecure> getLanguage(@PathVariable("uuid") UUID uuid) {
        ProgrammingLanguageSecure programmingLanguage = this.programmingLanguageService.findById(uuid);
        return ResponseEntity.ok(programmingLanguage);
    }

    @GetMapping("/{uuid}/versions")
    public ResponseEntity<List<ProglangVersionSecure>> getVersionsFromLanguage(@PathVariable("uuid") UUID uuid) {
        List<ProglangVersionSecure> programmingLanguage = this.progLangVersionService.findByLanguage(uuid);
        return ResponseEntity.ok(programmingLanguage);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProgrammingLanguageSecure> putLanguage(@PathVariable("uuid") UUID uuid,
                                                                 @RequestBody @Valid ProgrammingLanguageUpsert programmingLanguage,
                                                                 Errors errors) {
        this.validateDto("ProgrammingLanguage", errors);
        ProgrammingLanguageSecure programmingLanguageUpdated = this.programmingLanguageService.update(programmingLanguage, uuid);
        return ResponseEntity.ok(programmingLanguageUpdated);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteLanguage(@PathVariable("uuid") UUID uuid) {
        this.programmingLanguageService.delete(uuid);
        return ResponseEntity
                .noContent()
                .build();
    }
}
