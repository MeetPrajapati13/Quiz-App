package com.example.Quiz_App.dto.requestdto;

public class QuizSessionRequest {
    private Long sessionId;
    private int count;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
