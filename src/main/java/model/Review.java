package model;

import java.time.LocalDate;
import java.util.Objects;

public class Review {
    private int bookId;
    private String pseudo;
    private LocalDate date;
    private String comment;
    private Double rating;     // Note entre 0 et 5

    public Review(int bookId, String pseudo, Double rating, String comment, LocalDate date) {
        if (rating < 0.0 || rating > 5.0) {
            throw new IllegalArgumentException("La note doit être comprise entre 0 et 5.");
        }
        this.bookId = bookId;
        this.pseudo = pseudo;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return rating == review.rating && Objects.equals(bookId, review.bookId) && Objects.equals(pseudo, review.pseudo) && Objects.equals(date, review.date) && Objects.equals(comment, review.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, pseudo, date, comment, rating);
    }

    @Override
    public String toString() {
        return "Critique du livre avec l'ID" + bookId
                + ", pseudo : " + pseudo + '\'' +
                ", laissée le " + date +
                ", commentaire :'" + comment + '\'' +
                ", note : " + rating +
                '}';
    }
}
