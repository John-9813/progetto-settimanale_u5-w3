package johnoliveira.progetto_settimanale_u5_w3.controllers;


import johnoliveira.progetto_settimanale_u5_w3.entities.Event;
import johnoliveira.progetto_settimanale_u5_w3.entities.User;
import johnoliveira.progetto_settimanale_u5_w3.payloads.EventDTO;
import johnoliveira.progetto_settimanale_u5_w3.services.EventService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    @Autowired
    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody EventDTO eventDTO, @RequestAttribute User user) {
        return eventService.createEvent(eventDTO, user);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.findAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO, @RequestAttribute User user) throws BadRequestException {
        Event updatedEvent = eventService.updateEvent(id, eventDTO, user);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long id, @RequestAttribute User user)  {
        eventService.deleteEvent(id, user);
    }
}

