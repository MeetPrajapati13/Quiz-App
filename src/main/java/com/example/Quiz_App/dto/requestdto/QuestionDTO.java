package com.example.Quiz_App.dto.requestdto;

import com.example.Quiz_App.model.QuizQuestions;

public class QuestionDTO {
    private Long questionId;

    private String question;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    public QuestionDTO(QuizQuestions q) {
        this.questionId = q.getQuestionId();
        this.question = q.getQuestion();
        this.option1 = q.getOption1();
        this.option2 = q.getOption2();
        this.option3 = q.getOption3();
        this.option4 = q.getOption4();
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }
}
