package com.example.Quiz_App.repository;

import com.example.Quiz_App.model.Answer;
import com.example.Quiz_App.model.QuizSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(value = "select * from answers where user_session_id = :id",nativeQuery = true)
    List<Answer> findBySessionId(@Param("id") Long id);

    boolean existsByQuizSession_SessionId(Long sessionId);

    List<Answer> findByQuizSession(QuizSession quizSession);

}
