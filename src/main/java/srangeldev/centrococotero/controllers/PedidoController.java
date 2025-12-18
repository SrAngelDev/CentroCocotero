package srangeldev.centrococotero.controllers;

import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import srangeldev.centrococotero.models.Pedido;
import srangeldev.centrococotero.models.Usuario;
import srangeldev.centrococotero.repositories.UserRepository;
import srangeldev.centrococotero.services.PdfService;
import srangeldev.centrococotero.services.PedidoService;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/app/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/{id}")
    public String verDetalle(@PathVariable String id,
                             Authentication authentication,
                             Model model) {

        Usuario usuario = userRepository.findFirstByEmail(authentication.getName());
        if (usuario == null) {
            return "redirect:/login";
        }

        Optional<Pedido> pedidoOpt = pedidoService.buscarPorIdYUsuario(id, usuario.getId());

        if (pedidoOpt.isEmpty()) {
            return "redirect:/app/perfil";
        }

        model.addAttribute("pedido", pedidoOpt.get());
        return "app/pedido/detalle";
    }

    @GetMapping("/{id}/factura")
    public ResponseEntity<byte[]> descargarFactura(@PathVariable String id,
                                                    Authentication authentication) {
        try {
            // Verificar que el usuario esté autenticado
            Usuario usuario = userRepository.findFirstByEmail(authentication.getName());
            if (usuario == null) {
                log.warn("Usuario no autenticado intentando descargar factura");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            // Buscar el pedido y verificar que pertenece al usuario
            Optional<Pedido> pedidoOpt = pedidoService.buscarPorIdYUsuario(id, usuario.getId());
            if (pedidoOpt.isEmpty()) {
                log.warn("Pedido {} no encontrado o no pertenece al usuario {}", id, usuario.getEmail());
                return ResponseEntity.notFound().build();
            }

            Pedido pedido = pedidoOpt.get();

            // Generar el PDF usando el servicio
            byte[] pdfBytes = pdfService.generarFacturaPdf(pedido);

            // Configurar headers para la descarga
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "Factura_" + pedido.getId() + ".pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            log.info("Factura del pedido {} descargada por usuario {}", id, usuario.getEmail());

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (DocumentException e) {
            log.error("Error al generar el PDF de la factura para el pedido {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            log.error("Error inesperado al descargar factura del pedido {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
