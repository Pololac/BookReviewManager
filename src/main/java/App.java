import data.FakeDatabase;
import model.Book;
import model.Genre;
import model.Review;
import service.BookService;
import service.ReviewService;

import java.util.*;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);

        BookService bookService = new BookService(FakeDatabase.getBooks());
        ReviewService reviewService = new ReviewService(FakeDatabase.getReviews());

        // Afficher la liste des livres triés par moyenne des notes
            // Récupérer tous les livres
        List<Book> allBooks = bookService.getAllBooks();

            // Construire la map livre-moyenne
        Map<Book, Double> averageByBook = new HashMap<>();
        for (Book book : allBooks) {
            double average = reviewService.getAverageRatingForBook(book.getId());
            averageByBook.put(book, average);
        }

            // Trier et afficher
        System.out.println("Liste des livres classés par note décroissante :");
        averageByBook.entrySet().stream()
                .sorted(Map.Entry.<Book, Double>comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(
                        "Livre : " + entry.getKey().getTitle() + " - Moyenne : " + entry.getValue())
                );
        System.out.println("=".repeat(50));


        // Afficher les critiques d’un auteur donné, triées par date décroissante
            // Récupérer le nom de l'auteur recherché :
        System.out.println("Rentre le nom de l'auteur recherché :");
        String search = scanner.nextLine().trim().toLowerCase();

            // Récupérer tous les livres de l'auteur
        List<Book> booksFromAuthor = bookService.getBooksFromAuthor(search);
        if (booksFromAuthor.isEmpty()) {
            System.out.println("Aucun livre trouvé pour cet auteur.");
        } else {
            // Récupérer les critiques de tous ces livres
            List<Review> reviewsForBooksFromAuthor = new ArrayList<>();
            for (Book book : booksFromAuthor) {
                reviewsForBooksFromAuthor.addAll(reviewService.getReviewsForBook(book.getId()));
            }

            // Les trier par date décroissante et les afficher
            System.out.println("Critiques des livres de " + search + ", triés par date décroissante :");
            reviewsForBooksFromAuthor.stream()
                    .sorted(Comparator.comparing(Review::getDate).reversed())
                    .forEach(review -> {
                        System.out.println("Date : " + review.getDate()
                                + " | ID Livre : " + review.getBookId()
                                + " | Commentaire : " + review.getComment());
                    });
        }
        System.out.println("=".repeat(50));


        // Rechercher les meilleurs livres par genre, avec la meilleure moyenne.
            // Classer les livres par genre
        Map<Genre, List<Book>> booksByGenre = bookService.getBooksByGenre();

            // Afficher les 3 meilleurs livres de chaque genre (en réutilisant "averageByBook" utilisée plus haut)
        for (Map.Entry<Genre, List<Book>> entry : booksByGenre.entrySet()) {
            Genre genre = entry.getKey();
            List<Book> booksOfGenre = entry.getValue();

            // Filtrer averageByBook pour garder seulement les livres de ce genre
            System.out.println("\nTop 3 des livres du genre : " + genre.getDisplayName());
            averageByBook.entrySet().stream()
                    .filter(e -> booksOfGenre.contains(e.getKey()))
                    .sorted(Map.Entry.<Book, Double>comparingByValue(Comparator.reverseOrder()))
                    .limit(3)
                    .forEach(e -> System.out.println("- " + e.getKey().getTitle() + " | Moyenne : " + e.getValue()));
        }
        System.out.println("=".repeat(50));


        // Filtrer les livres publiés avant une certaine année et afficher leurs critiques
            // Récupérer la date saisie par l'utilisateur
        System.out.println("Quelle est la date avant laquelle les livres doivent avoir été publiés ? :");
        int input = (scanner.nextInt());
        scanner.nextLine();

            // Afficher les livres publiés avant une certaine année
        List<Book> booksBeforeDate = bookService.getBooksPublishedBefore(input);

            //Afficher leurs critiques
        if (booksBeforeDate.isEmpty()) {
            System.out.println("Aucun livre publié avant cette date.");
        } else {
            System.out.println("Critiques des livres publiés avant " + input + " :");
            for (Book book : booksBeforeDate) {
                List<Review> reviews = reviewService.getReviewsForBook(book.getId());
                for (Review review : reviews) {
                    System.out.println(
                        "Titre : " + book.getTitle() +
                                " | Année : " + book.getPublishYear() +
                                " | Commentaire : " + review.getComment() +
                                " | Note : " + review.getRating());
                }
            }
        }
        System.out.println("=".repeat(50));


        // Grouper les livres par genre et afficher, pour chaque groupe, le nombre de livres et la note moyenne globale.
            //Grouper les livres par genre (fait plus haut)
        System.out.println("Nombre de livres par genre et leur note moyenne");

        for (Map.Entry<Genre, List<Book>> entry : booksByGenre.entrySet()) {
            Genre genre = entry.getKey();
            List<Book> booksOfGenre = entry.getValue();

            // Afficher le nombre de livres par genre
            int nbLivres = booksOfGenre.size();

            // Afficher la note moyenne par genre (moyenne des moyennes)
            double moyenneGenre = booksOfGenre.stream()
                    .mapToDouble(book -> reviewService.getAverageRatingForBook(book.getId()))
                    .average()
                    .orElse(0.0);

            System.out.printf("Genre : %s | Livres : %d | Note moyenne : %.2f\n",
                    genre, nbLivres, moyenneGenre);
        }

        scanner.close();
    }
}
