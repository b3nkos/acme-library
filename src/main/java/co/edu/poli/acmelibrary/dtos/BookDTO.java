package co.edu.poli.acmelibrary.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String author;

    @NotBlank
    private String isbn;

    @NotBlank
    private Double price;

    @NotBlank
    private String category;

    @NotBlank
    private Integer publicationYear;
}
