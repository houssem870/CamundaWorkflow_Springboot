package com.example.workflow.delegate;

import com.example.workflow.repository.ReviewRepository;
import com.example.workflow.services.BookService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static javax.xml.bind.DatatypeConverter.parseInt;

@Component("PublishReviewDelegate")
public class PublishReviewDelegate implements JavaDelegate {


    @Autowired
    BookService bookService ;
    @Autowired
    ReviewRepository reviewRepository;

    private final TaskService taskService;

    @Autowired
    public PublishReviewDelegate(TaskService taskService) {
        this.taskService = taskService;
    }


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {


        System.out.println("im here !!!!");

        int bookId = parseInt(delegateExecution.getVariable("bookId").toString());
        reviewRepository.findAll().size();
        bookService.publishReview(bookId, reviewRepository.findAll().size());
    }
}
