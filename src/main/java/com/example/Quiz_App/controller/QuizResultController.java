package com.example.Quiz_App.controller;


import com.example.Quiz_App.dto.responsedto.QuizResultResponse;
import com.example.Quiz_App.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizResultController {
    @Autowired
    ResultService resultService;

    @GetMapping("/result/{sessionId}")
    public QuizResultResponse getResult(@PathVariable Long sessionId){
        return resultService.calculateAndSaveResult(sessionId);
    }
}
