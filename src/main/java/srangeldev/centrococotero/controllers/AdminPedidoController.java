package srangeldev.centrococotero.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import srangeldev.centrococotero.models.EstadoPedido;
import srangeldev.centrococotero.repositories.PedidoRepository;
import srangeldev.centrococotero.services.EmailService;

/**
 * Controlador para gestión de pedidos desde el panel de administración
 */
@Slf4j
@Controller
@RequestMapping("/admin/pedidos")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequiredArgsConstructor
public class AdminPedidoController {

    private final PedidoRepository pedidoRepository;
    private final EmailService emailService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll()
                .stream()
                .sorted((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()))
                .toList());
        model.addAttribute("estados", EstadoPedido.values());
        return "admin/pedidos";
    }

    @PostMapping("/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable String id, 
                                @RequestParam EstadoPedido nuevoEstado,
                                @RequestParam(required = false, defaultValue = "false") boolean fromDetail,
                                RedirectAttributes redirectAttributes) {
        try {
            pedidoRepository.findById(id).ifPresent(pedido -> {
                EstadoPedido estadoAnterior = pedido.getEstado();
                pedido.setEstado(nuevoEstado);
                pedidoRepository.save(pedido);
                
                // Enviar email de notificación al cliente
                try {
                    emailService.enviarNotificacionCambioEstado(pedido);
                    log.info("Email de cambio de estado enviado para pedido {} de {} a {}", 
                             id, estadoAnterior, nuevoEstado);
                    redirectAttributes.addFlashAttribute("mensajeExito", 
                        "Estado actualizado a " + nuevoEstado + " y email enviado al cliente");
                } catch (Exception e) {
                    log.error("Error al enviar email de cambio de estado para pedido {}: {}", id, e.getMessage());
                    redirectAttributes.addFlashAttribute("mensajeAdvertencia", 
                        "Estado actualizado pero hubo un error al enviar el email");
                }
            });
        } catch (Exception e) {
            log.error("Error al cambiar estado del pedido {}: {}", id, e.getMessage());
            redirectAttributes.addFlashAttribute("mensajeError", 
                "Error al actualizar el estado del pedido");
        }
        
        // Redirigir según el origen de la petición
        if (fromDetail) {
            return "redirect:/admin/pedidos/" + id;
        }
        return "redirect:/admin/pedidos";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable String id, Model model) {
        model.addAttribute("pedido", pedidoRepository.findById(id).orElse(null));
        model.addAttribute("estados", EstadoPedido.values());
        return "admin/detalle-pedido";
    }
}
