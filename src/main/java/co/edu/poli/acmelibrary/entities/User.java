package co.edu.poli.acmelibrary.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    private Long id;
    private String name;
    private String lastName;
    private String identification;
    private String email;
    private Integer age;
}
