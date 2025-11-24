package com.example.Quiz_App.dto.responsedto;

public class QuizSessionResponse {
    private Long sessionId;
    private String userName;
    private String mobileNo;
    private String message;

    public QuizSessionResponse(Long sessionId, String userName, String mobileNo, String message) {
        this.sessionId = sessionId;
        this.userName = userName;
        this.mobileNo = mobileNo;
        this.message = message;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public String getUserName() {
        return userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getMessage() {
        return message;
    }
}
