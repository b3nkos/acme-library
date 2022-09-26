package co.edu.poli.acmelibrary.controllers;

import co.edu.poli.acmelibrary.dtos.OrderDTO;
import co.edu.poli.acmelibrary.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDTO saveNewOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createNewOrder(orderDTO);
    }

    @GetMapping("/{id}")
    public OrderDTO findOrderById(@PathVariable(name = "id") Long orderId) {
        Optional<OrderDTO> optionalOrderDTO = orderService.findOrderById(orderId);
        return optionalOrderDTO.orElse(null);
    }

    @GetMapping
    public List<OrderDTO> findAllOrders() {
        return orderService.getAllOrders();
    }
}
