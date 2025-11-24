package com.example.Quiz_App.model;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false)
    private String selectedOption;

    @Column(nullable = false)
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuizQuestions quizQuestion;

    @ManyToOne
    @JoinColumn(name = "user_session_id")
    private QuizSession quizSession;

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public QuizQuestions getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(QuizQuestions quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

    public QuizSession getQuizSession() {
        return quizSession;
    }

    public void setQuizSession(QuizSession quizSession) {
        this.quizSession = quizSession;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", selectedOption='" + selectedOption + '\'' +
                ", isCorrect=" + isCorrect +
                ", quizQuestion=" + quizQuestion +
                ", quizSession=" + quizSession +
                '}';
    }
}
