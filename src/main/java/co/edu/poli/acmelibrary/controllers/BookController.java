package co.edu.poli.acmelibrary.controllers;

import co.edu.poli.acmelibrary.dtos.BookDTO;
import co.edu.poli.acmelibrary.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDTO saveNewBook(@RequestBody BookDTO bookDTO) {
        return bookService.createNewBook(bookDTO);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable(name = "id") Long bookId, @RequestBody BookDTO bookDTO) throws Exception {

        return bookService.updateBook(new BookDTO(
                bookId,
                bookDTO.getName(),
                bookDTO.getAuthor(),
                bookDTO.getIsbn(),
                bookDTO.getPrice(),
                bookDTO.getCategory(),
                bookDTO.getPublicationYear()
        ));
    }

    @GetMapping("/{id}")
    public BookDTO findBookById(@PathVariable(name = "id") Long bookId) {
        Optional<BookDTO> optionalBookDTO = bookService.findBookById(bookId);
        return optionalBookDTO.orElse(null);
    }

    @GetMapping
    public List<BookDTO> findAllBooks() {
        return bookService.getBookList();
    }
}
