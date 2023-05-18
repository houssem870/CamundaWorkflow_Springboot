package com.example.workflow.DTO;

import com.example.workflow.entities.Review;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCompletionRequest {
    private String bookId;
    private Review review;

    // Constructor, getters, and setters
}
