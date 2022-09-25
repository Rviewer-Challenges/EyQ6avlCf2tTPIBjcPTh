package com.rebirth.mycode.web.rest;

import com.rebirth.mycode.domain.udt.pojos.SnippetCodePage;
import com.rebirth.mycode.domain.udt.pojos.SnippetCodeSecure;
import com.rebirth.mycode.services.SnippetCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/{codeuuid}")
    public ResponseEntity<SnippetCodeSecure> getSnippetCode(@PathVariable("codeuuid") UUID uuid) {
        SnippetCodeSecure codePage = this.snippetCodeService.findById(uuid);
        return ResponseEntity.ok(codePage);
    }


}
