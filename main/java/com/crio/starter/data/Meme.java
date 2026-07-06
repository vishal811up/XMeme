package com.crio.starter.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "memes")
public class Meme {

    @Id
    private String id;

    private String name;
    private String caption;
    private String url;

    private LocalDateTime createdAt;
}