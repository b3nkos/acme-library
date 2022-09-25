package co.edu.poli.acmelibrary.repositories;

import co.edu.poli.acmelibrary.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
