package com.example.workflow.delegate;

import com.example.workflow.entities.Review;
import com.example.workflow.services.BookService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import static javax.xml.bind.DatatypeConverter.parseInt;

@ComponentScan
@Component("CreateBookReviewDelegate")
public class CreateBookReviewDelegate implements JavaDelegate {

    @Autowired
    BookService bookService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        int bookId = parseInt(delegateExecution.getVariable("bookId").toString());
        Review review = (Review) delegateExecution.getVariable("review");

        bookService.createReview(bookId, review);
        System.out.println("ReviewCreated");
    }
}
