package co.edu.poli.acmelibrary.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class UserDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private final String name;

    @NotBlank
    private final String lastName;

    @NotBlank
    private final String identification;

    @NotBlank
    private final String email;

    @NotBlank
    private final Integer age;
}

