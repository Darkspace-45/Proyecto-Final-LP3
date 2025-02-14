package com.itsqmet.Denuncias.Controladores;

import com.itsqmet.Denuncias.Entidades.Denunciante;
import com.itsqmet.Denuncias.Servicios.DenuncianteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final DenuncianteServicio denuncianteServicio;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(Denunciante denunciante) {
        // Encriptar la contraseña
        denunciante.setPassword(passwordEncoder.encode(denunciante.getPassword()));
        denunciante.setEnabled(true);

        // Guardar el usuario
        denuncianteServicio.guardarDenunciante(denunciante);

        return "redirect:/auth/login";
    }
}