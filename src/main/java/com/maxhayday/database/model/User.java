package com.maxhayday.database.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class User {
    private Long id;
    private String name;
    private String lastName;
    private List<Post> posts;
    private Region region;
    private Role role;

}
