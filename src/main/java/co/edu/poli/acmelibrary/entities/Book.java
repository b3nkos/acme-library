package co.edu.poli.acmelibrary.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private Long id;
    private String name;
    private String author;
    private String isbn;
    private Double price;
    private String category;
    private Integer publicationYear;
}
