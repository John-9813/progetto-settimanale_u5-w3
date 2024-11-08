package johnoliveira.progetto_settimanale_u5_w3.controllers;


import johnoliveira.progetto_settimanale_u5_w3.entities.Reservation;
import johnoliveira.progetto_settimanale_u5_w3.entities.User;
import johnoliveira.progetto_settimanale_u5_w3.payloads.ReservationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@RequestBody ReservationDTO reservationDTO, @RequestAttribute User user) {
        return reservationService.createReservation(reservationDTO, user);
    }

    @GetMapping
    public List<Reservation> getUserReservations(@RequestAttribute User user) {
        return reservationService.findReservationsByUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelReservation(@PathVariable Long id, @RequestAttribute User user) {
        reservationService.cancelReservation(id, user);
    }
}

