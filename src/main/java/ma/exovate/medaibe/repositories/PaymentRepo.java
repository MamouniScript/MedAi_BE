package ma.exovate.medaibe.repositories;

import ma.exovate.medaibe.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
