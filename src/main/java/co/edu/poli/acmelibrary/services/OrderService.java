package co.edu.poli.acmelibrary.services;

import co.edu.poli.acmelibrary.dtos.BookDTO;
import co.edu.poli.acmelibrary.dtos.OrderDTO;
import co.edu.poli.acmelibrary.dtos.UserDTO;
import co.edu.poli.acmelibrary.entities.Book;
import co.edu.poli.acmelibrary.entities.Order;
import co.edu.poli.acmelibrary.entities.User;
import co.edu.poli.acmelibrary.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();

        orders.forEach(order -> {
            UserDTO userDTO = new UserDTO(
                    order.getUser().getId(),
                    order.getUser().getName(),
                    order.getUser().getLastName(),
                    order.getUser().getIdentification(),
                    order.getUser().getEmail(),
                    order.getUser().getAge()
            );

            List<BookDTO> bookDTOs = new ArrayList<>();

            order.getBooks().forEach(book -> bookDTOs.add(new BookDTO(
                    book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getIsbn(),
                    book.getPrice(),
                    book.getCategory(),
                    book.getPublicationYear()
            )));

            orderDTOs.add(new OrderDTO(
                    order.getId(),
                    userDTO,
                    bookDTOs
            ));
        });

        return orderDTOs;
    }

    public Optional<OrderDTO> findOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) {
            return Optional.empty();
        }

        Order order = optionalOrder.get();

        UserDTO userDTO = new UserDTO(
                order.getUser().getId(),
                order.getUser().getName(),
                order.getUser().getLastName(),
                order.getUser().getIdentification(),
                order.getUser().getEmail(),
                order.getUser().getAge()
        );

        List<BookDTO> bookDTOs = new ArrayList<>();

        order.getBooks().forEach(book -> bookDTOs.add(new BookDTO(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPrice(),
                book.getCategory(),
                book.getPublicationYear()
        )));

        return Optional.of(new OrderDTO(
                order.getId(),
                userDTO,
                bookDTOs
        ));
    }
}
