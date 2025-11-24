package com.example.Quiz_App.controller;

import com.example.Quiz_App.dto.requestdto.QuestionDTO;
import com.example.Quiz_App.dto.requestdto.QuizSessionRequest;
import com.example.Quiz_App.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/start")
    public ResponseEntity<?> startQuiz(@RequestBody QuizSessionRequest quizSessionRequest){
        boolean isStarted = quizService.startQuiz(quizSessionRequest);

        if(!isStarted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Session Id !!");
        }

        return ResponseEntity.ok("Quiz Start ! Ready to get First Question.");
    }

    @GetMapping("/next")
    public ResponseEntity<QuestionDTO> getNextQuestion(){
        QuestionDTO questions = quizService.getNextQuestion();

        if(questions != null){
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
