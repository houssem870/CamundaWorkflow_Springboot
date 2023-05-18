package com.example.workflow.controller;


import com.example.workflow.entities.Book;
import com.example.workflow.entities.Review;
import com.example.workflow.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{bookId}/reviews")
    public ResponseEntity<Review> createReview(@PathVariable int bookId, @RequestBody Review review) {
        Review createdReview = bookService.createReview(bookId, review);
        if (createdReview != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{bookId}/reviews/{reviewId}/publish")
    public ResponseEntity<Void> publishReview(@PathVariable int bookId, @PathVariable int reviewId) {
        bookService.publishReview(bookId, reviewId);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/reviews/reviewBook")
    public ResponseEntity<Void> bookReview() {
       System.out.println("Still under Review");
        return ResponseEntity.ok().build();
    }
}
