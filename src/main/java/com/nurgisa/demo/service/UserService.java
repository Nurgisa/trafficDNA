package com.nurgisa.demo.service;

import com.nurgisa.demo.model.Result;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity getTopAllResults(String userId);

    ResponseEntity getTopResultsByLevel(int levelId);

    ResponseEntity setInfo(Result response);
}
