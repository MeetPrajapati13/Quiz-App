package com.example.Quiz_App.controller;

import com.example.Quiz_App.dto.responsedto.QuestionResponse;
import com.example.Quiz_App.model.QuizQuestions;
import com.example.Quiz_App.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping("/setQuestion")
    public ResponseEntity<QuestionResponse> saveAllQuestion(@RequestBody List<QuizQuestions> quizQuestions){
        QuestionResponse responseDto = questionService.saveAllQuestions(quizQuestions);

        HttpStatus httpStatus = responseDto.getFailedQuestions().isEmpty() ? HttpStatus.OK : HttpStatus.CONFLICT;
        return ResponseEntity.status(httpStatus).body(responseDto);
    }
}
