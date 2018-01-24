package com.example.QuestionAnswerAPI.QuestionAnswerAPI.controller;

import com.example.QuestionAnswerAPI.QuestionAnswerAPI.dto.AnswerDTO;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.dto.QuestionDTO;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity.Answer;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity.AnswerList;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.entity.Question;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.response.CountDTO;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.response.SuccessDTO;
import com.example.QuestionAnswerAPI.QuestionAnswerAPI.service.QuestionAnswerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionAnswerController {
    @Autowired
    QuestionAnswerService questionAnswerService;

/*//  For getting all the questions stored in database of a particular user
//   input : userId
//   output : countDTO will be returned and count of questions of particular user*/
    @RequestMapping(method= RequestMethod.GET, value="/countByUserId/{userId}")
    public ResponseEntity<?> getCount(@PathVariable("userId") String userId ){

        Integer questionCount = questionAnswerService.countbyuserid(userId);
        CountDTO countDTO = new CountDTO();
        countDTO.setCountByUserId(questionCount);
        return  new ResponseEntity<>(countDTO, HttpStatus.OK);
    }

/*//  For getting all the questions stored in database
//    input - Get method
//    output - list of DTO of questions*/
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public ResponseEntity<?> getAll(){
        List<Question> questions = questionAnswerService.findAll();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question que: questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(que, questionDTO);
            questionDTOList.add(questionDTO);
        }
        return new ResponseEntity<>(questionDTOList, HttpStatus.OK);
    }


/*//  For adding a question int database API
//    input - post method and details of question to add into question database
//    output - question will be added into database and status will be returns*/
    @RequestMapping(method = RequestMethod.POST, value = "/addQuestion")
    public ResponseEntity<?> addOrUpdate(@RequestBody QuestionDTO questionDTO){
        System.out.println(questionDTO);
        Question question = new Question();
        BeanUtils.copyProperties(questionDTO, question);
        Question questionCreated = questionAnswerService.save(question);
        SuccessDTO successDTO = new SuccessDTO();
        successDTO.setStatus(true);
        return new ResponseEntity<SuccessDTO>(successDTO,HttpStatus.CREATED);
    }


/*//  For adding an answer int database API
//    input - post method and details of answer to add into answer database
//    output - answer will be added into database and status will be returns*/
    @RequestMapping(method = RequestMethod.POST, value = "/addAnswer")
    public ResponseEntity<?> addOrUpdate(@RequestBody AnswerDTO answerDTO){ //answerid of postdto will be same as questionid
        System.out.println(answerDTO);
        Answer answer = new Answer();
        BeanUtils.copyProperties(answerDTO, answer);
        questionAnswerService.save(answer);
        SuccessDTO successDTO = new SuccessDTO();
        successDTO.setStatus(true);
        return new ResponseEntity<SuccessDTO>(successDTO, HttpStatus.CREATED);
    }

/*//  For getting all the answer of a question stored in database
//    input - Get method
//    output - answerlist contains list of answers of questions*/
    @RequestMapping(method = RequestMethod.GET, value = "/getAnswersByQuestionId/{questionId}")
    public ResponseEntity<?> getAnswers(@PathVariable("questionId") String questionId){
        AnswerList answerList = questionAnswerService.findByAnswerlistId(questionId);
        return new ResponseEntity<>(answerList, HttpStatus.OK);
    }


/*//  For getting count of the answers stored in database of a particular questions
//   input : questionId
//   output : countDTO will be returned and count of answer of particular question*/
    @RequestMapping(method= RequestMethod.GET, value="/countAnswers/{questionId}")
    public ResponseEntity<?> getCountAnswers(@PathVariable("questionId") String questionId ){

        Integer answersCount = questionAnswerService.countbyquestionid(questionId);
        CountDTO countDTO = new CountDTO();
        countDTO.setCountByUserId(answersCount);
        return  new ResponseEntity<>(countDTO, HttpStatus.OK);
    }

/*//  For getting count of the comments stored in database of a particular answer
//   input : userId
//   output : countDTO will be returned and count of questions of particular user*/
    @RequestMapping(method= RequestMethod.GET, value="/countComments/{answerId}")
    public ResponseEntity<?> getCountComments(@PathVariable("answerId") String answerId ){

        Integer commentCount = questionAnswerService.countComments(answerId);
        CountDTO countDTO = new CountDTO();
        countDTO.setCountByUserId(commentCount);
        return  new ResponseEntity<>(countDTO, HttpStatus.OK);
    }


/*//  For getting count of the upvotes stored in database of a particular answer
//   input : userId
//   output : countDTO will be returned and count of questions of particular user*/
    @RequestMapping(method= RequestMethod.GET, value="/countUpvotes/{answerId}")
    public ResponseEntity<?> getCountUpvotes(@PathVariable("answerId") String answerId ){

        Integer commentCount = questionAnswerService.countUpvotes(answerId);
        CountDTO countDTO = new CountDTO();
        countDTO.setCountByUserId(commentCount);
        return  new ResponseEntity<>(countDTO, HttpStatus.OK);
    }

   /* /addquestion - done
    /addanswer - done
    /getquestions(byuserid) - done
    /getanswers(byquestionid) - done
    /addcomment -
    /addupvote -
    /getbycategory -
    /removeupvote -
    /countquestion(byuserid) - done
    /countanswers(byquestionid) - done
    /countcomments(byanswerid) - done
    /countupvotes(byanswerid) - done */


}
