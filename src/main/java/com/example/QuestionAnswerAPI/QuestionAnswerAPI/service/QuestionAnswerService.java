package com.example.QuestionAnswerAPI.QuestionAnswerAPI.service;

import com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity.Answer;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity.AnswerList;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity.Question;

import java.util.List;

public interface QuestionAnswerService {
    List<Question> findByUserId(String userId);

    List<Question> findAll();
    List<AnswerList> findAllAnsList();
//
//    void deletebyId(String cartId);
    Integer countbyuserid(String userId);
    Integer countComments(String answerId);
    Integer countbyquestionid(String questionId);
    Integer countUpvotes(String answerId);
//    void deleteAll();
    Question save(Question question);
    void save(Answer answer);
    AnswerList findByAnswerlistId(String answerlistId);
}
