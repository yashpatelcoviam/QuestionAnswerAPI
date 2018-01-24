package com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = Answer.COLLECTION_NAME)
public class Answer {

    public static final String COLLECTION_NAME = "answer";

    @Id
    private String answerId;
    private String answer;
    private String userId;
    private String timestamp;

    public static String getCollectionName() {
        return COLLECTION_NAME;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }




}
