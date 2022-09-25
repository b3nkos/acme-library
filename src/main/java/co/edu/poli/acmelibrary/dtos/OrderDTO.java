package co.edu.poli.acmelibrary.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrderDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private UserDTO user;

    @NotBlank
    private List<BookDTO> books;
}
