package johnoliveira.progetto_settimanale_u5_w3.controllers;

import johnoliveira.progetto_settimanale_u5_w3.entities.User;
import johnoliveira.progetto_settimanale_u5_w3.exceptions.BadRequestException;
import johnoliveira.progetto_settimanale_u5_w3.payloads.AuthRequestDTO;
import johnoliveira.progetto_settimanale_u5_w3.payloads.AuthResponseDTO;
import johnoliveira.progetto_settimanale_u5_w3.payloads.NewUserDTO;
import johnoliveira.progetto_settimanale_u5_w3.services.AuthService;
import johnoliveira.progetto_settimanale_u5_w3.services.UserService;
import johnoliveira.progetto_settimanale_u5_w3.tools.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private  UserService userService;
    @Autowired
    private  JWT jwt;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO body) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(authService.checkCredentialsAndGenerateToken(body));
        return authResponseDTO;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@RequestBody @Validated NewUserDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.userService.registerUser(body);
    }
}
