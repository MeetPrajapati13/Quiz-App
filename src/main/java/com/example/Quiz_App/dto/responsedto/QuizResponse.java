package com.example.Quiz_App.dto.responsedto;

import jakarta.persistence.Column;


public class QuizResponse {
    @Column(nullable = false)
    private int correctAnswers;

    @Column(nullable = false)
    private int incorrectAnswers;

    @Column(nullable = false)
    private int totalQuestions;

    @Column(nullable = false)
    private double score;

    @Column(nullable = false)
    private String result;

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(int incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
