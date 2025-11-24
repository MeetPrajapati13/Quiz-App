package com.example.Quiz_App.controller;

import com.example.Quiz_App.dto.requestdto.AnswerRequest;
import com.example.Quiz_App.dto.responsedto.AnswerResponse;
import com.example.Quiz_App.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @PostMapping("/submit")
    public ResponseEntity<AnswerResponse> submitAnswer(@RequestBody AnswerRequest answerRequest){
       AnswerResponse answerResponse = answerService.saveAnswerDetail(answerRequest);
       return ResponseEntity.ok(answerResponse);
    }

}
