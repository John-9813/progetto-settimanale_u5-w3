package johnoliveira.progetto_settimanale_u5_w3.services;

import johnoliveira.progetto_settimanale_u5_w3.entities.Event;
import johnoliveira.progetto_settimanale_u5_w3.entities.User;
import johnoliveira.progetto_settimanale_u5_w3.exceptions.NotFoundException;
import johnoliveira.progetto_settimanale_u5_w3.repositories.EventRepos;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepos eventRepos;

    // crea nuovo evento
    public Event createEvent(Event event, User creator) {
        event.setCreator(creator); // Assegna il creatore all'evento
        return eventRepos.save(event);
    }

    // trova evendo da id
    public Event findById(Long id) {
        return eventRepos.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento non trovato con ID: " + id));
    }

    // aggiorna un evento
    public Event updateEvent(Long eventId, Event updatedEvent, User user) throws BadRequestException {
        Event event = findById(eventId);

        if (!event.getCreator().getId().equals(user.getId())) {
            throw new BadRequestException("Non sei autorizzato a modificare questo evento.");
        }

        event.setTitle(updatedEvent.getTitle());
        event.setDescription(updatedEvent.getDescription());
        event.setDate(updatedEvent.getDate());
        event.setLocation(updatedEvent.getLocation());
        event.setAvailableSeats(updatedEvent.getAvailableSeats());

        return eventRepos.save(event);
    }

    // cancella un evento
    public void deleteEvent(Long eventId, User user) throws BadRequestException {
        Event event = findById(eventId);

        if (!event.getCreator().getId().equals(user.getId())) {
            throw new BadRequestException("Non sei autorizzato a cancellare questo evento.");
        }

        eventRepos.delete(event);
    }

   // una lista per tutti gli eventi esistenti
    public List<Event> findAllEvents() {
        return eventRepos.findAll();
    }
}

