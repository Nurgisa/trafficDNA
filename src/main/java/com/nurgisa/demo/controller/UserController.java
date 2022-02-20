package com.nurgisa.demo.controller;

import com.nurgisa.demo.model.Result;
import com.nurgisa.demo.model.User;
import com.nurgisa.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/userinfo/{user_id}")
    public ResponseEntity getTopAllResults(@PathVariable("user_id") String userId) {
        return userService.getTopAllResults(userId);
    }

    @GetMapping("/levelinfo/{level_id}")
    public ResponseEntity getTopResultsByLevel(@PathVariable("level_id") int levelId) {
        return userService.getTopResultsByLevel(levelId);
    }

    @PutMapping("/setinfo")
    public ResponseEntity setInfo(@RequestBody Result result) {
        return userService.setInfo(result);
    }
}
