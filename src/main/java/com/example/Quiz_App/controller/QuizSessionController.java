package com.example.Quiz_App.controller;

import com.example.Quiz_App.dto.responsedto.QuizSessionResponse;
import com.example.Quiz_App.model.QuizSession;
import com.example.Quiz_App.service.QuizSessionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
public class QuizSessionController {
    @Autowired
    QuizSessionService quizSessionService;


    @PostMapping("/createSession")
    public ResponseEntity<QuizSessionResponse> saveSession(@RequestBody QuizSession quizSession){
        QuizSessionResponse quizSessionResponse = quizSessionService.createQuizSession(quizSession);
        return ResponseEntity.ok(quizSessionResponse);
    }
}
