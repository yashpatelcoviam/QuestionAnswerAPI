package com.example.QuestionAnswerAPI.QuestionAnswerAPI.dto;

import java.util.List;

public class UpvoteListDTO {
    private String upvoteId;    //id will be same as answer id
    private List<String> userId;

    public String getUpvoteId() {
        return upvoteId;
    }

    public void setUpvoteId(String upvoteId) {
        this.upvoteId = upvoteId;
    }

    public List<String> getUserId() {
        return userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }
}
