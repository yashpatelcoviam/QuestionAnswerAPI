package com.example.QuestionAnswerAPI.QuestionAnswerAPI.repository;

import com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity.Answer;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity.AnswerList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerListRepository extends MongoRepository<AnswerList, String> {
    public AnswerList getByAnswerListId(String answerListId);
}