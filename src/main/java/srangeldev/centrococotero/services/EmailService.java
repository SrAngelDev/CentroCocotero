package srangeldev.centrococotero.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import srangeldev.centrococotero.models.Pedido;

import java.io.UnsupportedEncodingException;

/**
 * Servicio para envío de correos electrónicos
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.from}")
    private String fromEmail;

    @Value("${app.mail.name}")
    private String fromName;

    /**
     * Envía un email de confirmación de pedido con factura en PDF
     * 
     * @param pedido Pedido realizado
     * @param pdfBytes Bytes del PDF de la factura
     * @throws MessagingException Si hay error al enviar el email
     */
    public void enviarConfirmacionPedido(Pedido pedido, byte[] pdfBytes) throws MessagingException, UnsupportedEncodingException {
        log.info("Enviando email de confirmación para pedido: {}", pedido.getId());

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        // Configurar destinatario
        helper.setTo(pedido.getUsuario().getEmail());
        helper.setFrom(fromEmail, fromName);
        helper.setSubject("Confirmación de pedido #" + pedido.getId());

        // Cuerpo del email en HTML
        String htmlContent = construirEmailHtml(pedido);
        helper.setText(htmlContent, true);

        // Adjuntar PDF
        helper.addAttachment(
                "Factura_" + pedido.getId() + ".pdf",
                new ByteArrayResource(pdfBytes)
        );

        // Enviar
        mailSender.send(message);

        log.info("Email enviado exitosamente a: {}", pedido.getUsuario().getEmail());
    }

    /**
     * Construye el contenido HTML del email de confirmación
     * 
     * @param pedido Pedido
     * @return HTML del email
     */
    private String construirEmailHtml(Pedido pedido) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<link href='https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Nunito:wght@400;600;700&display=swap' rel='stylesheet'>");
        html.append("<style>");
        html.append("* { box-sizing: border-box; margin: 0; padding: 0; }");
        html.append("body { font-family: 'Nunito', sans-serif; line-height: 1.6; color: #422A21; background-color: #F4E4BA; padding: 20px; }");
        html.append(".container { max-width: 650px; margin: 0 auto; background: white; border-radius: 15px; overflow: hidden; box-shadow: 0 10px 30px rgba(0,0,0,0.15); }");
        html.append(".header { background: linear-gradient(135deg, #16BABD 0%, #488B49 100%); color: white; padding: 40px 30px; text-align: center; position: relative; }");
        html.append(".header::before { content: '🥥'; font-size: 60px; display: block; margin-bottom: 15px; }");
        html.append(".header h1 { font-family: 'Luckiest Guy', cursive; font-size: 2.5em; margin: 0 0 10px 0; text-shadow: 2px 2px 4px rgba(0,0,0,0.2); letter-spacing: 1px; }");
        html.append(".header p { font-size: 1.1em; margin: 0; opacity: 0.95; }");
        html.append(".content { padding: 40px 30px; background: #FFFDF5; }");
        html.append(".greeting { font-size: 1.2em; margin-bottom: 20px; color: #422A21; }");
        html.append(".greeting strong { color: #16BABD; font-weight: 700; }");
        html.append(".intro { color: #666; margin-bottom: 25px; line-height: 1.8; }");
        html.append(".pedido-box { background: white; border: 3px solid #16BABD; border-radius: 12px; padding: 25px; margin: 25px 0; box-shadow: 0 4px 15px rgba(22, 186, 189, 0.1); }");
        html.append(".pedido-header { display: flex; align-items: center; justify-content: center; background: #16BABD; color: white; padding: 15px; border-radius: 8px; margin: -25px -25px 20px -25px; }");
        html.append(".pedido-header h3 { font-family: 'Luckiest Guy', cursive; margin: 0; font-size: 1.5em; letter-spacing: 0.5px; }");
        html.append(".pedido-numero { background: linear-gradient(135deg, #FF9F1C 0%, #FF7F1C 100%); color: white; padding: 20px; border-radius: 10px; text-align: center; margin: 20px 0; border: 3px dashed #422A21; }");
        html.append(".pedido-numero strong { display: block; font-size: 0.9em; margin-bottom: 8px; opacity: 0.9; }");
        html.append(".pedido-numero span { font-family: 'Luckiest Guy', cursive; font-size: 1.8em; display: block; letter-spacing: 1px; }");
        html.append(".info-row { display: flex; justify-content: space-between; padding: 15px 0; border-bottom: 2px solid #F4E4BA; align-items: center; }");
        html.append(".info-row:last-of-type { border-bottom: none; }");
        html.append(".info-row strong { color: #16BABD; font-weight: 700; }");
        html.append(".info-row .value { color: #422A21; font-weight: 600; text-align: right; }");
        html.append(".total-box { background: linear-gradient(135deg, #488B49 0%, #16BABD 100%); color: white; padding: 20px; border-radius: 10px; text-align: center; margin-top: 25px; }");
        html.append(".total-box .label { font-size: 0.9em; opacity: 0.9; margin-bottom: 5px; }");
        html.append(".total-box .amount { font-family: 'Luckiest Guy', cursive; font-size: 2.5em; letter-spacing: 1px; }");
        html.append(".steps-section { background: #F4E4BA; padding: 25px; border-radius: 10px; margin: 25px 0; }");
        html.append(".steps-section h4 { font-family: 'Luckiest Guy', cursive; color: #16BABD; font-size: 1.3em; margin-bottom: 15px; text-align: center; }");
        html.append(".step { display: flex; align-items: flex-start; margin: 12px 0; padding-left: 10px; }");
        html.append(".step::before { content: '✓'; color: #488B49; font-weight: bold; font-size: 1.3em; margin-right: 12px; }");
        html.append(".step span { color: #422A21; line-height: 1.6; }");
        html.append(".cta-box { text-align: center; margin: 25px 0; }");
        html.append(".cta-button { display: inline-block; background: #FF9F1C; color: white; padding: 15px 35px; border-radius: 25px; text-decoration: none; font-weight: 700; font-size: 1.1em; box-shadow: 0 4px 15px rgba(255, 159, 28, 0.3); transition: all 0.3s; }");
        html.append(".note { background: #FFF9E6; border-left: 4px solid #FF9F1C; padding: 15px 20px; margin: 20px 0; border-radius: 5px; color: #666; }");
        html.append(".footer { background: #422A21; color: white; padding: 30px; text-align: center; }");
        html.append(".footer-emoji { font-size: 40px; margin-bottom: 15px; }");
        html.append(".footer p { margin: 8px 0; opacity: 0.9; }");
        html.append(".footer .brand { font-family: 'Luckiest Guy', cursive; color: #16BABD; font-size: 1.3em; margin-bottom: 5px; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        html.append("<div class='container'>");
        
        html.append("<div class='header'>");
        html.append("<h1>¡PEDIDO CONFIRMADO!</h1>");
        html.append("<p>Gracias por confiar en Centro Cocotero</p>");
        html.append("</div>");
        
        html.append("<div class='content'>");
        html.append("<p class='greeting'>¡Hola <strong>").append(pedido.getUsuario().getNombre()).append("</strong>! 🎉</p>");
        html.append("<p class='intro'>Tu pedido ha sido confirmado y procesado exitosamente. Estamos preparando tus productos naturales con mucho cariño.</p>");
        
        html.append("<div class='pedido-numero'>");
        html.append("<strong>Número de Pedido</strong>");
        html.append("<span>#").append(pedido.getId()).append("</span>");
        html.append("</div>");
        
        html.append("<div class='pedido-box'>");
        html.append("<div class='pedido-header'>");
        html.append("<h3>📦 Detalles del Pedido</h3>");
        html.append("</div>");
        
        html.append("<div class='info-row'>");
        html.append("<strong>📅 Fecha del Pedido:</strong>");
        html.append("<span class='value'>").append(pedido.getCreatedAt().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("</span>");
        html.append("</div>");
        
        html.append("<div class='info-row'>");
        html.append("<strong>📦 Productos:</strong>");
        html.append("<span class='value'>").append(pedido.getLineas().size()).append(" artículos</span>");
        html.append("</div>");
        
        html.append("<div class='info-row'>");
        html.append("<strong>🚀 Estado:</strong>");
        html.append("<span class='value'>").append(pedido.getEstado().toString()).append("</span>");
        html.append("</div>");
        
        if (pedido.getDireccionEnvio() != null && !pedido.getDireccionEnvio().isEmpty()) {
            html.append("<div class='info-row'>");
            html.append("<strong>🏠 Dirección de Envío:</strong>");
            html.append("<span class='value'>").append(pedido.getDireccionEnvio()).append("</span>");
            html.append("</div>");
        }
        
        html.append("<div class='total-box'>");
        html.append("<div class='label'>TOTAL PAGADO</div>");
        html.append("<div class='amount'>").append(String.format("%.2f", pedido.getTotal())).append(" €</div>");
        html.append("</div>");
        html.append("</div>");
        
        html.append("<div class='note'>");
        html.append("<strong>📧 Factura Adjunta:</strong> Encontrarás tu factura en PDF adjunta a este correo.");
        html.append("</div>");
        
        html.append("<div class='steps-section'>");
        html.append("<h4>¿Qué sigue ahora?</h4>");
        html.append("<div class='step'><span>Recibirás actualizaciones sobre el estado de tu pedido</span></div>");
        html.append("<div class='step'><span>Prepararemos tu pedido en las próximas 24-48 horas</span></div>");
        html.append("<div class='step'><span>Te notificaremos cuando tu pedido sea enviado</span></div>");
        html.append("<div class='step'><span>Puedes seguir tu pedido desde tu perfil</span></div>");
        html.append("</div>");
        
        html.append("<p style='text-align: center; color: #666; margin-top: 20px;'>Si tienes alguna duda, no dudes en contactarnos. 💬</p>");
        html.append("</div>");
        
        html.append("<div class='footer'>");
        html.append("<div class='footer-emoji'>🥥🌴</div>");
        html.append("<p class='brand'>CENTRO COCOTERO</p>");
        html.append("<p>Productos naturales de la mejor calidad</p>");
        html.append("<p style='font-size: 0.85em; margin-top: 15px; opacity: 0.7;'>Este es un correo automático, por favor no respondas a este mensaje.</p>");
        html.append("</div>");
        
        html.append("</div>");
        html.append("</body>");
        html.append("</html>");
        
        return html.toString();
    }

    /**
     * Envía un email simple de texto
     * 
     * @param to Destinatario
     * @param subject Asunto
     * @param body Cuerpo del mensaje
     * @throws MessagingException Si hay error al enviar
     */
    public void enviarEmailSimple(String to, String subject, String body) throws MessagingException, UnsupportedEncodingException {
        log.info("Enviando email simple a: {}", to);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

        helper.setTo(to);
        helper.setFrom(fromEmail, fromName);
        helper.setSubject(subject);
        helper.setText(body, false);

        mailSender.send(message);

        log.info("Email simple enviado exitosamente");
    }

    /**
     * Envía un email de notificación de cambio de estado del pedido
     * 
     * @param pedido Pedido actualizado
     * @throws MessagingException Si hay error al enviar el email
     */
    public void enviarNotificacionCambioEstado(Pedido pedido) throws MessagingException, UnsupportedEncodingException {
        log.info("Enviando notificación de cambio de estado para pedido: {} - Nuevo estado: {}", 
                 pedido.getId(), pedido.getEstado());

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

        helper.setTo(pedido.getUsuario().getEmail());
        helper.setFrom(fromEmail, fromName);
        helper.setSubject("Actualización de tu pedido #" + pedido.getId());

        String htmlContent = construirEmailCambioEstado(pedido);
        helper.setText(htmlContent, true);

        mailSender.send(message);

        log.info("Email de cambio de estado enviado exitosamente a: {}", pedido.getUsuario().getEmail());
    }

    /**
     * Construye el contenido HTML del email de cambio de estado
     * 
     * @param pedido Pedido con estado actualizado
     * @return HTML del email
     */
    private String construirEmailCambioEstado(Pedido pedido) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<link href='https://fonts.googleapis.com/css2?family=Luckiest+Guy&family=Nunito:wght@400;600;700&display=swap' rel='stylesheet'>");
        html.append("<style>");
        html.append("* { box-sizing: border-box; margin: 0; padding: 0; }");
        html.append("body { font-family: 'Nunito', sans-serif; line-height: 1.6; color: #422A21; background-color: #F4E4BA; padding: 20px; }");
        html.append(".container { max-width: 650px; margin: 0 auto; background: white; border-radius: 15px; overflow: hidden; box-shadow: 0 10px 30px rgba(0,0,0,0.15); }");
        html.append(".header { background: linear-gradient(135deg, #FF9F1C 0%, #FF7F1C 100%); color: white; padding: 40px 30px; text-align: center; position: relative; }");
        html.append(".header::before { content: '🔔'; font-size: 60px; display: block; margin-bottom: 15px; }");
        html.append(".header h1 { font-family: 'Luckiest Guy', cursive; font-size: 2.2em; margin: 0 0 10px 0; text-shadow: 2px 2px 4px rgba(0,0,0,0.2); letter-spacing: 1px; }");
        html.append(".header p { font-size: 1.1em; margin: 0; opacity: 0.95; }");
        html.append(".content { padding: 40px 30px; background: #FFFDF5; }");
        html.append(".greeting { font-size: 1.2em; margin-bottom: 20px; color: #422A21; }");
        html.append(".greeting strong { color: #FF9F1C; font-weight: 700; }");
        html.append(".intro { color: #666; margin-bottom: 25px; line-height: 1.8; }");
        html.append(".estado-box { background: white; border: 3px solid #FF9F1C; border-radius: 12px; padding: 25px; margin: 25px 0; box-shadow: 0 4px 15px rgba(255, 159, 28, 0.1); text-align: center; }");
        html.append(".estado-icon { font-size: 80px; margin-bottom: 20px; }");
        html.append(".estado-label { font-size: 0.9em; color: #666; margin-bottom: 10px; text-transform: uppercase; letter-spacing: 1px; }");
        html.append(".estado-valor { font-family: 'Luckiest Guy', cursive; font-size: 2.2em; color: #FF9F1C; letter-spacing: 1px; }");
        html.append(".pedido-info { background: #F4E4BA; border-radius: 10px; padding: 20px; margin: 20px 0; }");
        html.append(".info-row { display: flex; justify-content: space-between; padding: 12px 0; border-bottom: 2px dashed rgba(66, 42, 33, 0.2); }");
        html.append(".info-row:last-child { border-bottom: none; }");
        html.append(".info-row strong { color: #422A21; font-weight: 700; }");
        html.append(".info-row .value { color: #16BABD; font-weight: 600; }");
        html.append(".message-box { background: linear-gradient(135deg, #16BABD 0%, #488B49 100%); color: white; padding: 25px; border-radius: 10px; margin: 25px 0; text-align: center; }");
        html.append(".message-box p { font-size: 1.05em; line-height: 1.7; margin: 0; }");
        html.append(".footer { background: #422A21; color: white; padding: 30px; text-align: center; }");
        html.append(".footer-emoji { font-size: 40px; margin-bottom: 15px; }");
        html.append(".footer p { margin: 8px 0; opacity: 0.9; }");
        html.append(".footer .brand { font-family: 'Luckiest Guy', cursive; color: #16BABD; font-size: 1.3em; margin-bottom: 5px; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        html.append("<div class='container'>");
        
        html.append("<div class='header'>");
        html.append("<h1>ACTUALIZACIÓN DE PEDIDO</h1>");
        html.append("<p>Tu pedido ha cambiado de estado</p>");
        html.append("</div>");
        
        html.append("<div class='content'>");
        html.append("<p class='greeting'>¡Hola <strong>").append(pedido.getUsuario().getNombre()).append("</strong>! 👋</p>");
        html.append("<p class='intro'>Queremos informarte que el estado de tu pedido ha sido actualizado. A continuación te mostramos los detalles:</p>");
        
        html.append("<div class='estado-box'>");
        html.append("<div class='estado-icon'>");
        
        // Icono según el estado
        switch (pedido.getEstado()) {
            case PENDIENTE:
                html.append("⏳");
                break;
            case PAGADO:
                html.append("✅");
                break;
            case CONFIRMADO:
                html.append("🎉");
                break;
            case ENVIADO:
                html.append("🚚");
                break;
            case ENTREGADO:
                html.append("📦");
                break;
            case CANCELADO:
                html.append("❌");
                break;
        }
        
        html.append("</div>");
        html.append("<div class='estado-label'>Nuevo Estado</div>");
        html.append("<div class='estado-valor'>").append(pedido.getEstado().name()).append("</div>");
        html.append("</div>");
        
        html.append("<div class='pedido-info'>");
        html.append("<div class='info-row'>");
        html.append("<strong>📦 Número de Pedido:</strong>");
        html.append("<span class='value'>#").append(pedido.getId()).append("</span>");
        html.append("</div>");
        
        html.append("<div class='info-row'>");
        html.append("<strong>📅 Fecha del Pedido:</strong>");
        html.append("<span class='value'>").append(pedido.getCreatedAt().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("</span>");
        html.append("</div>");
        
        html.append("<div class='info-row'>");
        html.append("<strong>💰 Total:</strong>");
        html.append("<span class='value'>").append(String.format("%.2f", pedido.getTotal())).append(" €</span>");
        html.append("</div>");
        html.append("</div>");
        
        // Mensaje personalizado según el estado
        html.append("<div class='message-box'>");
        switch (pedido.getEstado()) {
            case PENDIENTE:
                html.append("<p>Tu pedido está pendiente de confirmación. Te notificaremos cuando se procese.</p>");
                break;
            case PAGADO:
                html.append("<p>¡Pago recibido! Tu pedido será procesado pronto.</p>");
                break;
            case CONFIRMADO:
                html.append("<p>¡Excelente! Tu pedido ha sido confirmado y está siendo preparado.</p>");
                break;
            case ENVIADO:
                html.append("<p>🎊 ¡Tu pedido está en camino! Recibirás tus productos muy pronto.</p>");
                break;
            case ENTREGADO:
                html.append("<p>🎉 ¡Tu pedido ha sido entregado! Esperamos que disfrutes tus productos. ¡Gracias por tu compra!</p>");
                break;
            case CANCELADO:
                html.append("<p>Tu pedido ha sido cancelado. Si tienes dudas, contáctanos.</p>");
                break;
        }
        html.append("</div>");
        
        html.append("<p style='text-align: center; color: #666; margin-top: 25px;'>Gracias por confiar en Centro Cocotero 🥥</p>");
        html.append("</div>");
        
        html.append("<div class='footer'>");
        html.append("<div class='footer-emoji'>🥥🌴</div>");
        html.append("<p class='brand'>CENTRO COCOTERO</p>");
        html.append("<p>Productos naturales de la mejor calidad</p>");
        html.append("<p style='font-size: 0.85em; margin-top: 15px; opacity: 0.7;'>Este es un correo automático, por favor no respondas a este mensaje.</p>");
        html.append("</div>");
        
        html.append("</div>");
        html.append("</body>");
        html.append("</html>");
        
        return html.toString();
    }
}
