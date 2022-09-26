package com.rebirth.mycode.web.rest;

import com.rebirth.mycode.domain.udt.pojos.SnippetCodePage;
import com.rebirth.mycode.domain.udt.pojos.SnippetCodeSecure;
import com.rebirth.mycode.domain.udt.pojos.SnippetCodeUpsert;
import com.rebirth.mycode.services.SnippetCodeService;
import com.rebirth.mycode.web.dto.SnippetCodeUpsertWithValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/snippetcode")
public class SnippetCodeController extends BaseController {

    private final SnippetCodeService snippetCodeService;

    public SnippetCodeController(SnippetCodeService snippetCodeService) {
        this.snippetCodeService = snippetCodeService;
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<SnippetCodePage> getSnippetCodePage(
            @RequestParam("size") int size,
            @RequestParam("page") int page) {
        SnippetCodePage codePage = this.snippetCodeService.getByPage(page, size);
        return ResponseEntity.ok(codePage);
    }

    @PostMapping
    public ResponseEntity<SnippetCodeSecure> postSnippetCode(@Valid @RequestBody SnippetCodeUpsertWithValidation snippetCodeUpsert, Errors errors) {
        this.validateDto("SnippetCode", errors);
        SnippetCodeSecure snippetCodeSecure = this.snippetCodeService.insert(snippetCodeUpsert);
        UUID programmingLanguageUUID = snippetCodeSecure.getSnippetCodeUid();
        URI uriFromCreatedEntity = this.createUri4newEntity(programmingLanguageUUID);
        return ResponseEntity.created(uriFromCreatedEntity)
                .body(snippetCodeSecure);
    }

    @GetMapping(path = "/{codeuuid}")
    public ResponseEntity<SnippetCodeSecure> getSnippetCode(@PathVariable("codeuuid") UUID uuid) {
        SnippetCodeSecure codePage = this.snippetCodeService.findById(uuid);
        return ResponseEntity.ok(codePage);
    }

    @PutMapping(path = "/{codeuuid}")
    public ResponseEntity<SnippetCodeSecure> putSnippetCode(@PathVariable("codeuuid") UUID uuid, @RequestBody @Valid SnippetCodeUpsert snippetCodeUpsert, Errors errors) {
        this.validateDto("SnippetCode", errors);
        SnippetCodeSecure snippetCode = this.snippetCodeService.update(snippetCodeUpsert, uuid);
        return ResponseEntity.ok(snippetCode);
    }

    @DeleteMapping(path = "/{codeuuid}")
    public ResponseEntity<SnippetCodeSecure> deleteSnippetCode(@PathVariable("codeuuid") UUID uuid) {
        this.snippetCodeService.delete(uuid);
        return ResponseEntity.noContent().build();
    }


}
