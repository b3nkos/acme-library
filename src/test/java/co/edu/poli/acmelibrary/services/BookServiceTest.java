package co.edu.poli.acmelibrary.services;

import co.edu.poli.acmelibrary.dtos.BookDTO;
import co.edu.poli.acmelibrary.entities.Book;
import co.edu.poli.acmelibrary.repositories.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepositoryMock;

    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("Should save a new book")
    public void saveNewBook() {
        final BookDTO bookDto = new BookDTO(
                12345L,
                "The Lord Of The Rings",
                "J.R.R Tolkien",
                "9780544003415",
                150_000.00D,
                "Fantasy",
                1850
        );

        Book expectedBook = new Book(
                12345L,
                "The Lord Of The Rings",
                "J.R.R Tolkien",
                "9780544003415",
                150_000.00D,
                "Fantasy",
                1850
        );

        when(bookRepositoryMock.save(any(Book.class))).thenReturn(expectedBook);

        BookDTO bookCreated = bookService.createNewBook(bookDto);

        assertThat(bookCreated).isNotNull();
        assertThat(bookCreated.getId()).isPositive();
        assertThat(bookCreated.getId()).isEqualTo(12345L);
    }

    @Test
    @DisplayName("Should update a book")
    public void updateBook() throws Exception {

        final BookDTO bookDto = new BookDTO(
                12345L,
                "The Lord Of The Rings II",
                "J.R.R Tolkien",
                "9780544003415",
                160_000.00D,
                "Fantasy",
                1900
        );

        Book bookFound = new Book(
                12345L,
                "The Lord Of The Rings",
                "J.R.R Tolkien",
                "9780544003415",
                150_000.00D,
                "Fantasy",
                1850
        );

        Book expectedBook = new Book(
                12345L,
                "The Lord Of The Rings II",
                "J.R.R Tolkien",
                "9780544003415",
                160_000.00D,
                "Fantasy",
                1900
        );

        when(bookRepositoryMock.findById(12345L)).thenReturn(Optional.of(bookFound));
        when(bookRepositoryMock.save(any(Book.class))).thenReturn(expectedBook);

        BookDTO actualBookDto = bookService.updateBook(bookDto);

        assertThat(actualBookDto).isNotNull();
        assertThat(actualBookDto.getId()).isPositive();
        assertThat(actualBookDto.getId()).isEqualTo(12345L);
        assertThat(actualBookDto.getName()).isEqualTo(expectedBook.getName());
        assertThat(actualBookDto.getAuthor()).isEqualTo(expectedBook.getAuthor());
        assertThat(actualBookDto.getIsbn()).isEqualTo(expectedBook.getIsbn());
        assertThat(actualBookDto.getPrice()).isEqualTo(expectedBook.getPrice());
        assertThat(actualBookDto.getCategory()).isEqualTo(expectedBook.getCategory());
        assertThat(actualBookDto.getPublicationYear()).isEqualTo(expectedBook.getPublicationYear());
    }

    @Test
    @DisplayName("Should get a book list")
    public void testGetListOfBooks() {

        List<Book> expectedBooks = List.of(
                new Book(
                        12345L,
                        "The Lord Of The Rings",
                        "J.R.R Tolkien",
                        "9780544003415",
                        150_000.00D,
                        "Fantasy",
                        1850
                ),
                new Book(
                        1111L,
                        "A Dance with Dragons (A Song of Ice and Fire)",
                        "Gorge R.R Martin",
                        "9780553582017",
                        120_000.00D,
                        "Fantasy",
                        1970
                )
        );

        when(bookRepositoryMock.findAll()).thenReturn(expectedBooks);

        List<BookDTO> bookDTOList = bookService.getBookList();
        assertThat(bookDTOList.size()).isPositive();
        assertThat(bookDTOList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should get a book by its id")
    public void testFindBookById() {
        Book expectedBook = new Book(
                12345L,
                "The Lord Of The Rings",
                "J.R.R Tolkien",
                "9780544003415",
                150_000.00D,
                "Fantasy",
                1850
        );

        when(bookRepositoryMock.findById(12345L)).thenReturn(Optional.of(expectedBook));

        Optional<BookDTO> actualOptionalBook = bookService.findBookById(12345L);

        assertThat(actualOptionalBook.get()).isNotNull();
    }

    @Test
    @DisplayName("Should get a not found book when search by its id")
    public void testNotFoundBookById() {

        when(bookRepositoryMock.findById(12345L)).thenReturn(Optional.empty());

        Optional<BookDTO> actualOptionalBook = bookService.findBookById(12345L);

        assertThat(actualOptionalBook.isPresent()).isFalse();
    }

}