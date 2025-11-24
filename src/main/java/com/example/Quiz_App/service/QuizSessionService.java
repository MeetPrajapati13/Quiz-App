package com.example.Quiz_App.service;

import com.example.Quiz_App.dto.responsedto.QuizSessionResponse;
import com.example.Quiz_App.handler.DuplicateMobileNumberException;
import com.example.Quiz_App.model.QuizSession;
import com.example.Quiz_App.repository.QuizSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class QuizSessionService {
    @Autowired
    QuizSessionRepository quizSessionRepository;


    public QuizSessionResponse createQuizSession(QuizSession quizSession){
        QuizSession session = new QuizSession();
        session.setUserName(quizSession.getUserName());
        session.setMobileNo(quizSession.getMobileNo());

        try {
            QuizSession save = quizSessionRepository.save(session);
            return new QuizSessionResponse(save.getSessionId(), save.getUserName(), save.getMobileNo(), "Quiz session started successfully");
        }catch (DataIntegrityViolationException ex){
            throw new DuplicateMobileNumberException("Mobile Number Already Exist : " + quizSession.getMobileNo());
        }
    }

}
