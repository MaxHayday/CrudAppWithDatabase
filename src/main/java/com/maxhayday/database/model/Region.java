package com.maxhayday.database.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Region {
    private Long id;
    private String name;
}
