package com.nurgisa.demo.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    private String userId;
    private String name;
    private List<Result> results;
}
