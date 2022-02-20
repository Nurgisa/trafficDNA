package com.nurgisa.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("level_id")
    private int levelId;
    private int result;
}
