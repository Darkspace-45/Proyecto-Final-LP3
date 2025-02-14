package com.itsqmet.Denuncias.Controladores;

import com.itsqmet.Denuncias.Servicios.DenunciaServicio;
import com.itsqmet.Denuncias.Servicios.DenuncianteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DenunciaServicio denunciaServicio;

    private final DenuncianteServicio denuncianteServicio;

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        // Obtener el rol del usuario
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("");

        // Redirigir según el rol
        switch (role) {
            case "ROLE_ADMIN":
                model.addAttribute("totalDenuncias", denunciaServicio.mostrarDenuncias().size());
                model.addAttribute("ultimasDenuncias", denunciaServicio.mostrarDenuncias());
                return "dashboard/admin";

            case "ROLE_DENUNCIANTE":
                String username = authentication.getName();
                model.addAttribute("misDenuncias",
                        denunciaServicio.findByDenuncianteId(username));
                return "dashboard/denunciante";

            case "ROLE_AUTORIDAD":
                String autoridadId = authentication.getName();
                model.addAttribute("denunciasPendientes",
                        denunciaServicio.contarDenunciasPorEstado("PENDIENTE"));
                model.addAttribute("denunciasEnProceso",
                        denunciaServicio.contarDenunciasPorEstado("EN_PROCESO"));
                model.addAttribute("denunciasResueltas",
                        denunciaServicio.contarDenunciasPorEstado("RESUELTO"));
                model.addAttribute("totalDenuncias",
                        denunciaServicio.contarDenunciasPorAutoridad(autoridadId));
                return "dashboard/autoridad";

            default:
                return "redirect:/auth/login";
        }
    }

}
