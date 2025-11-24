package com.example.Quiz_App.dto.responsedto;



import java.util.ArrayList;
import java.util.List;

public class QuestionResponse {
    private List<String> savedQuestions = new ArrayList<>();
    private List<String> skippedQuestions = new ArrayList<>();
    private List<String> failedQuestions = new ArrayList<>();
    private String message;

    public List<String> getSavedQuestions() {
        return savedQuestions;
    }

    public void setSavedQuestions(List<String> savedQuestions) {
        this.savedQuestions = savedQuestions;
    }

    public List<String> getSkippedQuestions() {
        return skippedQuestions;
    }

    public void setSkippedQuestions(List<String> skippedQuestions) {
        this.skippedQuestions = skippedQuestions;
    }

    public List<String> getFailedQuestions() {
        return failedQuestions;
    }

    public void setFailedQuestions(List<String> failedQuestions) {
        this.failedQuestions = failedQuestions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
