package com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = UpvoteList.COLLECTION_NAME)
public class UpvoteList {

    public static final String COLLECTION_NAME = "upvote";

    @Id
    private String upvoteId;    //id will be same as answer id
    private List<String> userId;

    public static String getCollectionName() {
        return COLLECTION_NAME;
    }

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
