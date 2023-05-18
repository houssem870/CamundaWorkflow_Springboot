package com.example.workflow.controller;
import com.example.workflow.DTO.TaskCompletionRequest;
import com.example.workflow.entities.Review;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final RuntimeService runtimeService;

    @Autowired
    public TaskController(TaskService taskService, RuntimeService runtimeService) {
        this.taskService = taskService;
        this.runtimeService = runtimeService;
    }

    @PostMapping("/{taskId}/complete")
    public void completeTask(@RequestBody TaskCompletionRequest taskCompletionRequest) {
      TaskQuery taskop = taskService.createTaskQuery()
                .taskDefinitionKey("Activity_06flnxw")
                .orderByLastUpdated().asc();


      List<Task> taskList=taskop.list();
      for (int i=0 ; i<taskList.size() ;i++){System.out.println(taskList.get(i));}

      Task task = taskList.get(taskList.size()-1);
      String taskId = task.getId();


      if (task != null) {
          String bookId = taskCompletionRequest.getBookId();
          Review review = taskCompletionRequest.getReview();

            // Complete the task and set process variables
          Map<String, Object> variables = new HashMap<>();

          variables.put("bookId", bookId);
          variables.put("review", review);
          taskService.complete(taskId, variables);        }
      else {System.out.println("Null Task");}


          }


    @GetMapping("/tasks")
    public List<Task> getTasks() {
        List<Task> tasks = taskService.createTaskQuery()
                // Add filters if needed
                .list();
        System.out.println(tasks);
        return tasks;
    }
}
