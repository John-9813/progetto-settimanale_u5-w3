package johnoliveira.progetto_settimanale_u5_w3.payloads;

import java.time.LocalDate;

public record EventDTO(String title,
                       String description,
                       LocalDate date,
                       String location,
                       Integer availableSeats) {
}
