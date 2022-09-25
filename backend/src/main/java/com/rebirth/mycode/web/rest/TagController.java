package com.rebirth.mycode.web.rest;

import com.rebirth.mycode.domain.udt.pojos.ProglangVersionSecure;
import com.rebirth.mycode.domain.udt.pojos.ProgrammingLanguageSecure;
import com.rebirth.mycode.domain.udt.pojos.TagSecure;
import com.rebirth.mycode.domain.udt.pojos.TagUpsert;
import com.rebirth.mycode.services.TagService;
import com.rebirth.mycode.web.dto.ProgrammingLanguageUpsertWithValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
public class TagController extends BaseController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagSecure>> getTags() {
        List<TagSecure> tags = this.tagService.findAll();
        return ResponseEntity.ok(tags);
    }

    @PostMapping
    public ResponseEntity<TagSecure> postTag(@RequestBody @Valid TagUpsert programmingLanguage, Errors errors) {
        this.validateDto("Tag", errors);
        TagSecure tagSecure = this.tagService.insert(programmingLanguage);
        UUID tagUid = tagSecure.getTagUid();
        URI uriFromCreatedEntity = this.createUri4newEntity(tagUid);
        return ResponseEntity.created(uriFromCreatedEntity)
                .body(tagSecure);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TagSecure> getTag(@PathVariable("uuid") UUID uuid) {
        TagSecure tag = this.tagService.findById(uuid);
        return ResponseEntity.ok(tag);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<TagSecure> putTag(@PathVariable("uuid") UUID uuid,
                                                 @RequestBody @Valid TagUpsert tagUpsert,
                                                 Errors errors) {
        this.validateDto("Tag", errors);
        TagSecure tagSecure = this.tagService.update(tagUpsert, uuid);
        return ResponseEntity.ok(tagSecure);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteTag(@PathVariable("uuid") UUID uuid) {
        this.tagService.delete(uuid);
        return ResponseEntity
                .noContent()
                .build();
    }


}
