package co.edu.poli.acmelibrary.services;

import co.edu.poli.acmelibrary.dtos.OrderDTO;
import co.edu.poli.acmelibrary.entities.Book;
import co.edu.poli.acmelibrary.entities.Order;
import co.edu.poli.acmelibrary.entities.User;
import co.edu.poli.acmelibrary.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDTO createNewOrder(OrderDTO orderDTO) {
        User userEntity = new User(
                orderDTO.getUser().getId(),
                orderDTO.getUser().getName(),
                orderDTO.getUser().getLastName(),
                orderDTO.getUser().getIdentification(),
                orderDTO.getUser().getEmail(),
                orderDTO.getUser().getAge()
        );

        List<Book> bookEntities = new ArrayList<>();

        orderDTO.getBooks().forEach(bookDTO -> bookEntities.add(
                new Book(
                        bookDTO.getId(),
                        bookDTO.getName(),
                        bookDTO.getAuthor(),
                        bookDTO.getIsbn(),
                        bookDTO.getPrice(),
                        bookDTO.getCategory(),
                        bookDTO.getPublicationYear()
                )
        ));

        Order orderEntity = new Order(
                orderDTO.getId(),
                userEntity,
                bookEntities,
                new Date()
        );

        orderRepository.save(orderEntity);

        return orderDTO;
    }
}
