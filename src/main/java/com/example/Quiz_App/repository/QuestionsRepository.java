package com.example.Quiz_App.repository;

import com.example.Quiz_App.model.QuizQuestions;
import com.example.Quiz_App.model.QuizSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<QuizQuestions, Long> {
    @Query(value = "select * from quiz_questions ORDER BY RAND() LIMIT :count", nativeQuery = true)
    List<QuizQuestions> findRandomQuestions(@Param("count") int count);

    boolean existsByQuestion(String question);


}
