package com.example.QuestionAnswerAPI.QuestionAnswerAPI.service;

import com.example.QuestionAnswerAPI.QuestionAnswerAPI.dto.AnswerDTO;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.dto.AnswerListDTO;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity.Answer;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity.AnswerList;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity.Question;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity.UpvoteList;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.repository.AnswerListRepository;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.repository.CommentListRepository;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.repository.QuestionRepository;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.repository.UpvoteListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService{
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerListRepository answerListRepository;

    @Autowired
    CommentListRepository commentListRepository;

    @Autowired
    UpvoteListRepository upvoteListRepository;

    @Override
    public List<Question> findByUserId(String userId) {
        return null;
    }

    @Override
    public AnswerList findByAnswerlistId(String answerlistId) {
        return answerListRepository.getByAnswerListId(answerlistId);
    }


    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public List<AnswerList> findAllAnsList() {
        return answerListRepository.findAll();
    }

    @Override
    public Integer countbyuserid(String userId) {
        return questionRepository.countByUserId(userId);
    }

    @Override
    public Integer countbyquestionid(String questionId) {
        return (answerListRepository.getByAnswerListId(questionId).getAnswerDTOList().size());
    }

    @Override
    public Integer countComments(String answerId) {
        return (commentListRepository.getByCommentListId(answerId).getCommentDTOList().size());
    }

    @Override
    public Integer countUpvotes(String answerId) {
        return (upvoteListRepository.getByUpvoteListId(answerId).getUserId().size());
    }

    @Override
    public Question save(Question question) {
        question.setTimestamp(Date.from(Instant.now()).toString());
        return questionRepository.save(question);
    }

    @Override
    public void save(Answer answer) {
        answer.setTimestamp(Date.from(Instant.now()).toString());
        AnswerDTO answerDTO  = new AnswerDTO();
        BeanUtils.copyProperties(answer, answerDTO);
        System.out.println(answerDTO);
        AnswerListDTO answerListDTO;
        AnswerList answerList;
        answerList=answerListRepository.getByAnswerListId(answerDTO.getAnswerId());
        if( answerList==null || answerList.getAnswerDTOList()==null) {
            answerList= new AnswerList();
            answerListDTO = new AnswerListDTO();
            List<AnswerDTO> answerDTOList = new ArrayList<>();
            answerDTOList.add(answerDTO);
            answerDTO.setAnswerId("1");
            answerListDTO.setAnswerDTOList(answerDTOList);
            answerListDTO.setAnswerListId(answer.getAnswerId());
            BeanUtils.copyProperties(answerListDTO, answerList);
        }
        else{
            answerList = answerListRepository.getByAnswerListId(answerDTO.getAnswerId());
            answerListDTO =new AnswerListDTO();
            BeanUtils.copyProperties(answerList, answerListDTO);
            answerListDTO.getAnswerDTOList().add(answerDTO);
            answerDTO.setAnswerId(String.valueOf(answerListDTO.getAnswerDTOList().size()));
            BeanUtils.copyProperties(answerListDTO, answerList);
        }
        answerList.setAnswerListId(answer.getAnswerId());
        answerListRepository.save(answerList);
        return;
    }
}
