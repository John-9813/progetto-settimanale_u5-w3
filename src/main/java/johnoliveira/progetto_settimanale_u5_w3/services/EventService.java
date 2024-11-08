package johnoliveira.progetto_settimanale_u5_w3.services;

import johnoliveira.progetto_settimanale_u5_w3.entities.Event;
import johnoliveira.progetto_settimanale_u5_w3.entities.User;
import johnoliveira.progetto_settimanale_u5_w3.exceptions.NotFoundException;
import johnoliveira.progetto_settimanale_u5_w3.exceptions.UnauthorizedException;
import johnoliveira.progetto_settimanale_u5_w3.payloads.EventDTO;

import johnoliveira.progetto_settimanale_u5_w3.repositories.EventRepos;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EventService {
    @Autowired
    private final EventRepos eventRepos;

    public Event createEvent(EventDTO eventDTO, User creator) {
        Event event = new Event();
        event.setTitle(eventDTO.title());
        event.setDescription(eventDTO.description());
        event.setDate(eventDTO.date());
        event.setLocation(eventDTO.location());
        event.setAvailableSeats(eventDTO.availableSeats());
        event.setCreator(creator);
        return eventRepos.save(event);
    }

    public List<Event> findAllEvents() {
        return eventRepos.findAll();
    }

    public Event findById(Long id) {
        return eventRepos.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento non trovato con ID: " + id));
    }

    public Event updateEvent(Long id, EventDTO eventDTO, User user) {
        Event event = findById(id);
        if (!event.getCreator().getId().equals(user.getId())) {
            throw new UnauthorizedException("Non sei autorizzato a modificare questo evento");
        }
        event.setTitle(eventDTO.title());
        event.setDescription(eventDTO.description());
        event.setDate(eventDTO.date());
        event.setLocation(eventDTO.location());
        event.setAvailableSeats(eventDTO.availableSeats());
        return eventRepos.save(event);
    }

    public void deleteEvent(Long id, User user) {
        Event event = findById(id);
        if (!event.getCreator().getId().equals(user.getId())) {
            throw new UnauthorizedException("Non sei autorizzato a cancellare questo evento");
        }
        eventRepos.delete(event);
    }


}


