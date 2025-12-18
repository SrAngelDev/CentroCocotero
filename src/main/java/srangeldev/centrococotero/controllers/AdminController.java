package srangeldev.centrococotero.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import srangeldev.centrococotero.repositories.*;

/**
 * Controlador para el panel de administración
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final ProductoRepository productoRepository;
    private final UserRepository userRepository;
    private final PedidoRepository pedidoRepository;
    private final PagoRepository pagoRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Estadísticas generales
        long totalProductos = productoRepository.count();
        long totalUsuarios = userRepository.count();
        long totalPedidos = pedidoRepository.count();
        long totalPagos = pagoRepository.count();

        model.addAttribute("totalProductos", totalProductos);
        model.addAttribute("totalUsuarios", totalUsuarios);
        model.addAttribute("totalPedidos", totalPedidos);
        model.addAttribute("totalPagos", totalPagos);

        // Últimos pedidos
        model.addAttribute("ultimosPedidos", pedidoRepository.findAll()
                .stream()
                .sorted((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()))
                .limit(5)
                .toList());

        return "admin/dashboard";
    }
}
