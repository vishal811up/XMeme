package com.crio.starter.service;

import com.crio.starter.data.Meme;
import com.crio.starter.exchange.MemeRequest;
import com.crio.starter.repository.MemeRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemeService {

    private final MemeRepository memeRepository;

    public MemeService(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    public String createMeme(MemeRequest request) {

        Optional<Meme> existing =
                memeRepository.findByNameAndCaptionAndUrl(
                        request.getName(),
                        request.getCaption(),
                        request.getUrl()
                );

        // Duplicate meme -> 409
        if (existing.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Duplicate meme already exists"
            );
        }

        Meme meme = Meme.builder()
                .name(request.getName())
                .caption(request.getCaption())
                .url(request.getUrl())
                .createdAt(LocalDateTime.now())
                .build();

        Meme saved = memeRepository.save(meme);

        return saved.getId();
    }

    public List<Meme> getLatestMemes() {
        return memeRepository.findTop100ByOrderByCreatedAtDesc();
    }

    public Meme getMemeById(String id) {

        // Invalid id -> 404
        return memeRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Meme not found"
                        ));
    }
}