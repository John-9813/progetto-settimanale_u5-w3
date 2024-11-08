package johnoliveira.progetto_settimanale_u5_w3.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int totalSeats;

    @Column(nullable = false)
    private int availableSeats;


    // aggiornare i posti disponibili dopo una prenotazione
    public void reserveSeat() {
        if (availableSeats > 0) {
            this.availableSeats--;
        } else {
            throw new IllegalStateException("Nessun posto disponibile!");
        }
    }

    // annullare una prenotazione e liberare un posto
    public void cancelReservation() {
        if (availableSeats < totalSeats) {
            this.availableSeats++;
        } else {
            throw new IllegalStateException("Non ci sono prenotazioni da annullare!");
        }
    }
}

