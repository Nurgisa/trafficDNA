package com.nurgisa.demo.service.impl;

import com.nurgisa.demo.model.Result;
import com.nurgisa.demo.model.User;
import com.nurgisa.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final List<User> users = new ArrayList<>();

    static {
        //Initialize Data
        users.add(User.builder().userId("100").name("Batur").build());
        users.add(User.builder().userId("101").name("Arman").build());
        users.add(User.builder().userId("102").name("Aya").build());
    }

    @Override
    public ResponseEntity getTopAllResults(String userId) {
        log.info("Got Start getTopAllResults. userId: {}", userId);
        User currUser = users.stream().filter(user -> user.getUserId().equals(userId))
                .findAny().orElse(null);

        if (currUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        if (currUser.getResults() == null) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(currUser.getResults().stream()
                .sorted(Comparator.comparingInt(Result::getResult).reversed())
                .limit(20)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity getTopResultsByLevel(int levelId) {
        log.info("Got Start getTopResultsByLevel. levelId: {}", levelId);
        val requiredStopFactors = users.stream()
                .filter(user -> user.getResults() != null)
                .flatMap(user ->
                        user.getResults().stream()
                                .map(res -> Result.builder()
                                        .result(res.getResult())
                                        .levelId(res.getLevelId())
                                        .userId(res.getUserId())
                                        .build()))
                .sorted(Comparator.comparing(Result::getResult).reversed())
                .limit(20)
                .collect(Collectors.toList());


        if (requiredStopFactors.isEmpty()) {
            log.error("Results not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Results not found");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(requiredStopFactors);
    }

    @Override
    public ResponseEntity setInfo(Result resultIn) {
        log.info("Got setInfo request. request: {}", resultIn);
        User currUser = users.stream().filter(user -> user.getUserId().equals(resultIn.getUserId()))
                .findAny().orElse(null);

        if (currUser == null) {
            log.error("User not found with request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        if (currUser.getResults() == null) {
            currUser.setResults(new ArrayList<>());
        }

        currUser.getResults().stream()
                .filter(s -> s.getLevelId() == resultIn.getLevelId() && s.getResult() < resultIn.getResult())
                .findFirst()
                .ifPresentOrElse(result1 -> result1.setResult(resultIn.getResult()),
                        () -> currUser.getResults().add(resultIn));

        return ResponseEntity.status(HttpStatus.CREATED).body("Result created");
    }
}
