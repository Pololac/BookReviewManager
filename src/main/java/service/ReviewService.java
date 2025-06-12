package service;

import model.Review;

import java.util.List;
import java.util.stream.Collectors;


public class ReviewService {
    private List<Review> allReviews;

    public ReviewService(List<Review> allReviews) {
        this.allReviews = allReviews;
    }

/*    public Map<Integer, List<Review>> groupReviewsByBookId() {
        return allReviews.stream()
                .collect(Collectors.groupingBy(Review::getBookId));
    }*/

    public double getAverageRatingForBook(int bookId) {
        return allReviews.stream()
                .filter(r -> r.getBookId() == bookId)
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
    }

    public List<Review> getReviewsForBook(int bookId) {
        return allReviews.stream()
                .filter(r -> r.getBookId() == bookId)
                .collect(Collectors.toList());
    }
}
