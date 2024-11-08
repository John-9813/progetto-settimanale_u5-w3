package johnoliveira.progetto_settimanale_u5_w3.repositories;


import johnoliveira.progetto_settimanale_u5_w3.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepos extends JpaRepository<Event, Long> {

}
