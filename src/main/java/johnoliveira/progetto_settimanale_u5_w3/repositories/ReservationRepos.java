package johnoliveira.progetto_settimanale_u5_w3.repositories;

import johnoliveira.progetto_settimanale_u5_w3.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepos extends JpaRepository<Reservation, Long> {
}
