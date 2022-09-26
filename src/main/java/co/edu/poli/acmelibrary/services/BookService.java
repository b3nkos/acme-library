package co.edu.poli.acmelibrary.services;

import co.edu.poli.acmelibrary.dtos.BookDTO;
import co.edu.poli.acmelibrary.entities.Book;
import co.edu.poli.acmelibrary.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO createNewBook(BookDTO bookDto) {
        Book bookEntity = new Book(
                bookDto.getId(),
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getIsbn(),
                bookDto.getPrice(),
                bookDto.getCategory(),
                bookDto.getPublicationYear()
        );

        bookRepository.save(bookEntity);

        return bookDto;

    }

    public BookDTO updateBook(BookDTO bookDto) throws Exception {
        Optional<Book> optionalBook = bookRepository.findById(bookDto.getId());

        isANullBook(optionalBook);

        Book bookEntity = new Book(
                bookDto.getId(),
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getIsbn(),
                bookDto.getPrice(),
                bookDto.getCategory(),
                bookDto.getPublicationYear()
        );

        bookRepository.save(bookEntity);

        return bookDto;

    }

    private void isANullBook(Optional<Book> book) throws Exception {

        if (book.isEmpty()) {
            throw new Exception("Book not found");
        }
    }

    public List<BookDTO> getBookList() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOs = new ArrayList<>();

        books.forEach(book -> bookDTOs.add(new BookDTO(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPrice(),
                book.getCategory(),
                book.getPublicationYear()
        )));

        return bookDTOs;
    }

    public Optional<BookDTO> findBookById(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isEmpty()) {
            return Optional.empty();
        }

        Book bookFound = optionalBook.get();

        BookDTO bookDTO = new BookDTO(
                bookFound.getId(),
                bookFound.getName(),
                bookFound.getAuthor(),
                bookFound.getIsbn(),
                bookFound.getPrice(),
                bookFound.getCategory(),
                bookFound.getPublicationYear()
        );

        return Optional.of(bookDTO);
    }
}
