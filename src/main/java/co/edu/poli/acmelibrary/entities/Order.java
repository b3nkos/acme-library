package co.edu.poli.acmelibrary.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Order {
    @Id
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Book> books;

    private Date date;
}
