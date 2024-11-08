package johnoliveira.progetto_settimanale_u5_w3.repositories;

import johnoliveira.progetto_settimanale_u5_w3.entities.Reservation;
import johnoliveira.progetto_settimanale_u5_w3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepos extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
}
