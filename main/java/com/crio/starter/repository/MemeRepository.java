package com.crio.starter.repository;

import com.crio.starter.data.Meme;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MemeRepository extends MongoRepository<Meme, String> {

    Optional<Meme> findByNameAndCaptionAndUrl(String name, String caption, String url);

    List<Meme> findTop100ByOrderByCreatedAtDesc();
}