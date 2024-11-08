package johnoliveira.progetto_settimanale_u5_w3.payloads;


import jakarta.validation.constraints.*;


public record NewUserDTO(
        @NotBlank(message = "Il nome è obbligatorio")
        String name,

        @NotBlank(message = "Il cognome è obbligatorio")
        String surname,

        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Email(message = "Formato email non valido")
        @Pattern(regexp = "\"\\\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,4}\\\\b\"")
        String email,

        @NotEmpty(message = "La password è un campo obbligatorio!")
        @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")
        @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
        String password
) {}

