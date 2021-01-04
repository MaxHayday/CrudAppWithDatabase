package com.maxhayday.database.model;

import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class Post {
    private Long id;
    private String content;
    private Timestamp created;
    private Timestamp updated;
    private Long user_id;
}
