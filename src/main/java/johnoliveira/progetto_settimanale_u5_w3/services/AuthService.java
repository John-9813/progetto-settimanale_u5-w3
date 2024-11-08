package johnoliveira.progetto_settimanale_u5_w3.services;


import johnoliveira.progetto_settimanale_u5_w3.entities.User;

import johnoliveira.progetto_settimanale_u5_w3.exceptions.UnauthorizedException;
import johnoliveira.progetto_settimanale_u5_w3.payloads.AuthRequestDTO;
import johnoliveira.progetto_settimanale_u5_w3.tools.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private final UserService userService;
    @Autowired
    private final JWT jwt;
    @Autowired
    private PasswordEncoder bcrypt;

    // vrifica credenziali e genera token
    public String checkCredentialsAndGenerateToken(AuthRequestDTO body) {
        User found = this.userService.findByEmail(body.email());
        // 1.2 Verifico che la password di quell'utente corrisponda a quella fornita
        if (bcrypt.matches(body.password(), found.getPassword())) {
            // 2. Se sono OK --> Genero il token
            String accessToken = jwt.generateToken(found.getEmail());
            // 3. Ritorno il token
            return accessToken;
        } else {
            // 4. Se le credenziali sono errate --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali errate!");
        }

    }
}

