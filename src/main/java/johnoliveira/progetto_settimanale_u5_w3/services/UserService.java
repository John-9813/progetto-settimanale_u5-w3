package johnoliveira.progetto_settimanale_u5_w3.services;

import johnoliveira.progetto_settimanale_u5_w3.entities.User;
import johnoliveira.progetto_settimanale_u5_w3.exceptions.NotFoundException;
import johnoliveira.progetto_settimanale_u5_w3.repositories.UserRepos;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepos userRepos;
    // encoder per gestire le password
    private final BCryptPasswordEncoder passwordEncoder;

    // registra nuovo utente con controlli
    public User registerUser(User user) throws BadRequestException {
        // Verifica che l'email non sia già in uso
        if (userRepos.existsByEmail(user.getEmail())) {

            throw new BadRequestException("Email già in uso");
        }

        // utilizzo delll'encoder sulla password per tutelare l'utente
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Salva
        return userRepos.save(user);
    }

    // cerca tramite mail con possibile eccezione
    public User findByEmail(String email) {
        return userRepos.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con email: " + email));
    }

    // verifica le credenziali al momento del login
    public User authenticate(String email, String rawPassword) throws BadRequestException {
        User user = findByEmail(email);
        // Verifica la corrispondenza della password
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadRequestException("Credenziali non valide");
        }
        return user;
    }
}

