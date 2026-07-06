package com.crio.starter.controller;

import com.crio.starter.exchange.MemeRequest;
import com.crio.starter.service.MemeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.crio.starter.data.Meme;

@RestController
@RequestMapping("/memes")
public class MemeController {

    private final MemeService memeService;

    public MemeController(MemeService memeService) {
        this.memeService = memeService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createMeme(
            @Valid @RequestBody MemeRequest request) {

        String id = memeService.createMeme(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Collections.singletonMap("id", id));
    }

    @GetMapping
    public ResponseEntity<List<Meme>> getLatestMemes() {

        List<Meme> memes = memeService.getLatestMemes();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meme> getMemeById(@PathVariable String id) {

        Meme meme = memeService.getMemeById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(meme);
    }
}