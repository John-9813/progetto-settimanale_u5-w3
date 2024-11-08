package johnoliveira.progetto_settimanale_u5_w3.tools;

import org.springframework.stereotype.Component;

@Component
public class JWT {

    // Metodo per verificare la validità del token (temporaneo=
    public void verifyToken(String token) {
    }

    // Metodo per estrarre l'ID utente dal token (temporaneo)
    public String getIdFromToken(String token) {
        return "decoded-user-id";
    }
}

