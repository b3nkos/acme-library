package co.edu.poli.acmelibrary;

import co.edu.poli.acmelibrary.entities.Book;
import co.edu.poli.acmelibrary.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AcmeLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcmeLibraryApplication.class, args);
    }

    @Bean
    public CommandLineRunner initBooks(BookRepository bookRepository) {
        return (args) -> {
            List<Book> books = List.of(
                    new Book(
                            56834L,
                            "The Lord Of The Rings",
                            "J.R.R Tolkien",
                            "9780544003415",
                            150_000.00D,
                            "Fantasy",
                            1850
                    ),
                    new Book(
                            345345435L,
                            "The Lord Of The Rings II",
                            "J.R.R Tolkien",
                            "9780544003415",
                            160_000.00D,
                            "Fantasy",
                            1900
                    ),
                    new Book(
                            9123L,
                            "A Dance with Dragons (A Song of Ice and Fire)",
                            "Gorge R.R Martin",
                            "9780553582017",
                            120_000.00D,
                            "Fantasy",
                            1970
                    )
            );

            bookRepository.saveAll(books);
        };
    }

}
