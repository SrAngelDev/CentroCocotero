package srangeldev.centrococotero.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import srangeldev.centrococotero.models.TipoRol;
import srangeldev.centrococotero.repositories.UserRepository;

/**
 * Controlador para gestión de usuarios desde el panel de administración
 */
@Controller
@RequestMapping("/admin/usuarios")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequiredArgsConstructor
public class AdminUsuarioController {

    private final UserRepository userRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", userRepository.findAll());
        model.addAttribute("roles", TipoRol.values());
        return "admin/usuarios";
    }

    @PostMapping("/cambiar-rol/{id}")
    public String cambiarRol(@PathVariable Long id, @RequestParam TipoRol nuevoRol) {
        userRepository.findById(id).ifPresent(usuario -> {
            usuario.setRol(nuevoRol);
            userRepository.save(usuario);
        });
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/usuarios";
    }
}
