package johnoliveira.progetto_settimanale_u5_w3.services;


import johnoliveira.progetto_settimanale_u5_w3.entities.Event;
import johnoliveira.progetto_settimanale_u5_w3.entities.Reservation;
import johnoliveira.progetto_settimanale_u5_w3.entities.User;
import johnoliveira.progetto_settimanale_u5_w3.exceptions.BadRequestException;
import johnoliveira.progetto_settimanale_u5_w3.exceptions.NotFoundException;
import johnoliveira.progetto_settimanale_u5_w3.exceptions.UnauthorizedException;
import johnoliveira.progetto_settimanale_u5_w3.payloads.ReservationDTO;
import johnoliveira.progetto_settimanale_u5_w3.repositories.EventRepos;
import johnoliveira.progetto_settimanale_u5_w3.repositories.ReservationRepos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepos reservationRepos;
    private final EventRepos eventRepos;

    public Reservation createReservation(ReservationDTO reservationDTO, User user) {
        Event event = eventRepos.findById(reservationDTO.eventId())
                .orElseThrow(() -> new NotFoundException("Evento non trovato con ID: " + reservationDTO.eventId()));

        if (event.getAvailableSeats() < reservationDTO.numberOfSeats()) {
            throw new BadRequestException("Posti insufficienti disponibili per questo evento");
        }

        event.setAvailableSeats(event.getAvailableSeats() - reservationDTO.numberOfSeats());
        Reservation reservation = new Reservation();
        reservation.setEvent(event);
        reservation.setUser(user);
        reservation.setNumberOfSeats(reservationDTO.numberOfSeats());
        eventRepos.save(event);
        return reservationRepos.save(reservation);
    }

    public List<Reservation> findReservationsByUser(User user) {
        return reservationRepos.findByUser(user);
    }

    public void cancelReservation(Long id, User user) {
        Reservation reservation = reservationRepos.findById(id)
                .orElseThrow(() -> new NotFoundException("Prenotazione non trovata con ID: " + id));

        if (!reservation.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("Non sei autorizzato a cancellare questa prenotazione");
        }

        Event event = reservation.getEvent();
        event.setAvailableSeats(event.getAvailableSeats() + reservation.getNumberOfSeats());
        eventRepos.save(event);
        reservationRepos.delete(reservation);
    }
}

