package com.example.workflow.services;


import com.example.workflow.entities.Book;
import com.example.workflow.entities.Review;
import com.example.workflow.repository.BookRepository;
import com.example.workflow.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    public BookService(BookRepository bookRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    public Review createReview(int bookId, Review review) {
        Book book = getBookById(bookId);
        if (book != null) {
            review.setBook(book);
            return reviewRepository.save(review);
        }
        return null;
    }

    public void publishReview(int bookId, int reviewId) {
        Book book = getBookById(bookId);
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (book != null && review != null && review.getBook().equals(book)) {
            review.setPublished(true);
            reviewRepository.save(review);
        }
    }
}

