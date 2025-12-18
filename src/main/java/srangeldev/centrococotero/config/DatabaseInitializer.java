package srangeldev.centrococotero.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import srangeldev.centrococotero.models.*;
import srangeldev.centrococotero.repositories.*;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private ItemCarritoRepository itemCarritoRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // ===== LIMPIEZA DE DATOS =====
        System.out.println("🧹 Limpiando datos antiguos...");
        
        // IMPORTANTE: Borrar en orden para respetar las claves foráneas
        System.out.println("   - Borrando items del carrito...");
        itemCarritoRepository.deleteAll();
        
        System.out.println("   - Borrando favoritos...");
        favoritoRepository.deleteAll();
        
        System.out.println("   - Borrando pagos...");
        pagoRepository.deleteAll();
        
        System.out.println("   - Borrando pedidos (incluye líneas)...");
        pedidoRepository.deleteAll();
        
        System.out.println("   - Borrando usuarios...");
        userRepository.deleteAll();

        System.out.println("👥 Creando usuarios de prueba...");
        
        // Usuario ADMIN
        Usuario admin = Usuario.builder()
                .nombre("Admin")
                .apellidos("Sistema")
                .email("admin@centrococotero.com")
                .password(passwordEncoder.encode("admin123"))
                .rol(TipoRol.ADMIN)
                .avatar("/images/logo.png")
                .deleted(false)
                .build();

        // Usuario normal
        Usuario moderador = Usuario.builder()
                .nombre("María")
                .apellidos("López")
                .email("maria@centrococotero.com")
                .password(passwordEncoder.encode("user123"))
                .rol(TipoRol.USER)
                .avatar("/images/logo.png")
                .deleted(false)
                .build();

        // Usuario normal 1
        Usuario user1 = Usuario.builder()
                .nombre("Juan")
                .apellidos("Pérez")
                .email("juan@email.com")
                .password(passwordEncoder.encode("user123"))
                .rol(TipoRol.USER)
                .avatar("/images/logo.png")
                .deleted(false)
                .build();

        // Usuario normal 2
        Usuario user2 = Usuario.builder()
                .nombre("Ana")
                .apellidos("García")
                .email("ana@email.com")
                .password(passwordEncoder.encode("user123"))
                .rol(TipoRol.USER)
                .avatar("/images/logo.png")
                .deleted(false)
                .build();

        // Usuario normal 3
        Usuario user3 = Usuario.builder()
                .nombre("Carlos")
                .apellidos("López")
                .email("carlos@email.com")
                .password(passwordEncoder.encode("user123"))
                .rol(TipoRol.USER)
                .avatar("/images/logo.png")
                .deleted(false)
                .build();

        userRepository.saveAll(List.of(admin, moderador, user1, user2, user3));
        
        System.out.println("✅ Usuarios creados:");
        System.out.println("   👑 Admin: admin@centrococotero.com / admin123");
        System.out.println("   🛡️  Moderador: moderador@centrococotero.com / mod123");
        System.out.println("   👤 Usuario 1: juan@email.com / user123");
        System.out.println("   👤 Usuario 2: ana@email.com / user123");
        System.out.println("   👤 Usuario 3: carlos@email.com / user123");

        // ===== PRODUCTOS =====
        System.out.println("🧹 Borrando productos antiguos...");
        productoRepository.deleteAll();

        System.out.println("🥥 Iniciando carga masiva de productos (20 items)...");

        productoRepository.saveAll(List.of(
                // --- BEBIDAS ---
                Producto.builder()
                        .nombre("Coco Fresco Premium")
                        .descripcion("Coco recién caído de la palmera, lleno de agua refrescante y electrolitos naturales.")
                        .precio(new BigDecimal("4.50"))
                        .stock(50)
                        .categoria(TipoCategoria.BEBIDAS)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Leche de Coco Orgánica")
                        .descripcion("Leche vegetal cremosa, ideal para currys, batidos o café. Sin azúcares añadidos.")
                        .precio(new BigDecimal("3.20"))
                        .stock(40)
                        .categoria(TipoCategoria.BEBIDAS)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Agua de Coco con Piña")
                        .descripcion("La combinación perfecta de hidratación y sabor tropical. Pack de 1 Litro.")
                        .precio(new BigDecimal("2.99"))
                        .stock(60)
                        .categoria(TipoCategoria.BEBIDAS)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Licor de Coco Suave")
                        .descripcion("Bebida espirituosa dulce con esencia de coco caribeño. Perfecto para cócteles.")
                        .precio(new BigDecimal("14.50"))
                        .stock(15)
                        .categoria(TipoCategoria.BEBIDAS)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),

                // --- ACEITES Y DERIVADOS ---
                Producto.builder()
                        .nombre("Aceite de Coco Virgen Extra")
                        .descripcion("Ideal para cocinar o hidratar la piel. 100% orgánico y prensado en frío.")
                        .precio(new BigDecimal("12.99"))
                        .stock(30)
                        .categoria(TipoCategoria.ACEITES_DERIVADOS)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Harina de Coco Keto")
                        .descripcion("Alternativa sin gluten rica en fibra. Perfecta para repostería saludable.")
                        .precio(new BigDecimal("6.50"))
                        .stock(25)
                        .categoria(TipoCategoria.ACEITES_DERIVADOS)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Azúcar de Flor de Coco")
                        .descripcion("Endulzante natural de bajo índice glucémico con sabor a caramelo.")
                        .precio(new BigDecimal("5.80"))
                        .stock(20)
                        .categoria(TipoCategoria.ACEITES_DERIVADOS)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Chips de Coco Tostados")
                        .descripcion("Snack crujiente y saludable. Ideal para picar entre horas o añadir al yogur.")
                        .precio(new BigDecimal("2.50"))
                        .stock(80)
                        .categoria(TipoCategoria.ACEITES_DERIVADOS)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),

                // --- ALIMENTOS TROPICALES ---
                Producto.builder()
                        .nombre("Pack Piña Gold")
                        .descripcion("Tres piñas dulces y jugosas traídas directamente de islas volcánicas.")
                        .precio(new BigDecimal("8.75"))
                        .stock(20)
                        .categoria(TipoCategoria.ALIMENTOS_TROPICALES)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Mango Maduro Importado")
                        .descripcion("Mango de gran calibre, sin hebras y con una dulzura excepcional.")
                        .precio(new BigDecimal("3.99"))
                        .stock(35)
                        .categoria(TipoCategoria.ALIMENTOS_TROPICALES)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Caja de Papayas")
                        .descripcion("Caja de 2kg de papayas frescas. Ricas en vitaminas y digestivas.")
                        .precio(new BigDecimal("11.00"))
                        .stock(10)
                        .categoria(TipoCategoria.ALIMENTOS_TROPICALES)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Plátano Macho (Kg)")
                        .descripcion("El rey de la cocina tropical. Ideal para freír o hacer tostones.")
                        .precio(new BigDecimal("1.80"))
                        .stock(50)
                        .categoria(TipoCategoria.ALIMENTOS_TROPICALES)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),

                // --- COSMÉTICA NATURAL ---
                Producto.builder()
                        .nombre("Jabón Artesanal de Coco")
                        .descripcion("Suavidad extrema para tu piel con aroma relajante. Hecho a mano.")
                        .precio(new BigDecimal("6.20"))
                        .stock(100)
                        .categoria(TipoCategoria.COSMETICA_NATURAL)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Exfoliante Corporal Café y Coco")
                        .descripcion("Elimina células muertas y deja la piel radiante y suave.")
                        .precio(new BigDecimal("15.00"))
                        .stock(25)
                        .categoria(TipoCategoria.COSMETICA_NATURAL)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Mascarilla Capilar Reparadora")
                        .descripcion("Tratamiento intensivo a base de aceite de coco para cabello dañado.")
                        .precio(new BigDecimal("18.90"))
                        .stock(15)
                        .categoria(TipoCategoria.COSMETICA_NATURAL)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Bálsamo Labial Tropical")
                        .descripcion("Hidratación profunda para tus labios con sabor a coco y vainilla.")
                        .precio(new BigDecimal("3.50"))
                        .stock(200)
                        .categoria(TipoCategoria.COSMETICA_NATURAL)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),

                // --- TEXTIL VERANO ---
                Producto.builder()
                        .nombre("Camisa Hawaiana 'Sunset'")
                        .descripcion("Estilo y frescura. Tela transpirable y diseño único de palmeras.")
                        .precio(new BigDecimal("25.00"))
                        .stock(15)
                        .categoria(TipoCategoria.TEXTIL_VERANO)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Sombrero de Paja")
                        .descripcion("Protégete del sol con estilo. Ala ancha y cinta decorativa.")
                        .precio(new BigDecimal("12.00"))
                        .stock(40)
                        .categoria(TipoCategoria.TEXTIL_VERANO)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Pareo Playa Palmeras")
                        .descripcion("Pareo ligero y versátil, sirve como vestido o toalla de playa.")
                        .precio(new BigDecimal("9.99"))
                        .stock(60)
                        .categoria(TipoCategoria.TEXTIL_VERANO)
                        .imagenes(List.of("/images/logo.png"))
                        .build(),
                Producto.builder()
                        .nombre("Bañador Hombre Tropical")
                        .descripcion("Secado rápido y estampado colorido de cocos y piñas.")
                        .precio(new BigDecimal("19.50"))
                        .stock(20)
                        .categoria(TipoCategoria.TEXTIL_VERANO)
                        .imagenes(List.of("/images/logo.png"))
                        .build()
        ));

        System.out.println("✅ ¡CARGA COMPLETADA! 20 Productos disponibles.");

        // ===== PEDIDOS DE EJEMPLO =====
        System.out.println("📦 Creando pedidos de ejemplo para pruebas de admin...");
        
        List<Producto> productos = productoRepository.findAll();
        
        // Pedido 1 - Usuario juan (PENDIENTE)
        Pedido pedido1 = Pedido.builder()
                .id("DEMO-001")
                .usuario(user1)
                .estado(EstadoPedido.PENDIENTE)
                .total(new BigDecimal("23.49"))
                .direccionEnvio("Calle Mayor 15, 3ºB - Madrid, 28013 - España")
                .notas("Por favor, entregar por la mañana")
                .createdAt(java.time.LocalDateTime.now().minusDays(2))
                .build();
        
        pedidoRepository.save(pedido1);
        
        // Líneas del pedido 1
        LineaPedido linea1_1 = LineaPedido.builder()
                .id("LIN-001-1")
                .pedido(pedido1)
                .producto(productos.get(0)) // Coco Fresco Premium
                .precioUnitario(new BigDecimal("4.50"))
                .cantidad(2)
                .subtotal(new BigDecimal("9.00"))
                .build();
        
        LineaPedido linea1_2 = LineaPedido.builder()
                .id("LIN-001-2")
                .pedido(pedido1)
                .producto(productos.get(4)) // Aceite de Coco Virgen Extra
                .precioUnitario(new BigDecimal("12.99"))
                .cantidad(1)
                .subtotal(new BigDecimal("12.99"))
                .build();
        
        LineaPedido linea1_3 = LineaPedido.builder()
                .id("LIN-001-3")
                .pedido(pedido1)
                .producto(productos.get(12)) // Jabón Artesanal de Coco
                .precioUnitario(new BigDecimal("6.20"))
                .cantidad(1)
                .subtotal(new BigDecimal("6.20"))
                .build();
        
        pedido1.getLineas().addAll(List.of(linea1_1, linea1_2, linea1_3));
        pedidoRepository.save(pedido1);
        
        // Pedido 2 - Usuario ana (PAGADO)
        Pedido pedido2 = Pedido.builder()
                .id("DEMO-002")
                .usuario(user2)
                .estado(EstadoPedido.PAGADO)
                .total(new BigDecimal("45.90"))
                .direccionEnvio("Av. Diagonal 123, Ático - Barcelona, 08029 - España")
                .notas("Llamar al timbre 2 veces")
                .createdAt(java.time.LocalDateTime.now().minusDays(5))
                .build();
        
        pedidoRepository.save(pedido2);
        
        LineaPedido linea2_1 = LineaPedido.builder()
                .id("LIN-002-1")
                .pedido(pedido2)
                .producto(productos.get(3)) // Licor de Coco Suave
                .precioUnitario(new BigDecimal("14.50"))
                .cantidad(2)
                .subtotal(new BigDecimal("29.00"))
                .build();
        
        LineaPedido linea2_2 = LineaPedido.builder()
                .id("LIN-002-2")
                .pedido(pedido2)
                .producto(productos.get(16)) // Camisa Hawaiana 'Sunset'
                .precioUnitario(new BigDecimal("25.00"))
                .cantidad(1)
                .subtotal(new BigDecimal("25.00"))
                .build();
        
        pedido2.getLineas().addAll(List.of(linea2_1, linea2_2));
        pedidoRepository.save(pedido2);
        
        // Pedido 3 - Usuario carlos (ENVIADO)
        Pedido pedido3 = Pedido.builder()
                .id("DEMO-003")
                .usuario(user3)
                .estado(EstadoPedido.ENVIADO)
                .total(new BigDecimal("15.80"))
                .direccionEnvio("Plaza España 7, 1º - Valencia, 46001 - España")
                .notas(null)
                .createdAt(java.time.LocalDateTime.now().minusDays(8))
                .build();
        
        pedidoRepository.save(pedido3);
        
        LineaPedido linea3_1 = LineaPedido.builder()
                .id("LIN-003-1")
                .pedido(pedido3)
                .producto(productos.get(7)) // Chips de Coco Tostados
                .precioUnitario(new BigDecimal("2.50"))
                .cantidad(3)
                .subtotal(new BigDecimal("7.50"))
                .build();
        
        LineaPedido linea3_2 = LineaPedido.builder()
                .id("LIN-003-2")
                .pedido(pedido3)
                .producto(productos.get(8)) // Pack Piña Gold
                .precioUnitario(new BigDecimal("8.75"))
                .cantidad(1)
                .subtotal(new BigDecimal("8.75"))
                .build();
        
        pedido3.getLineas().addAll(List.of(linea3_1, linea3_2));
        pedidoRepository.save(pedido3);
        
        System.out.println("✅ Pedidos de ejemplo creados:");
        System.out.println("   📦 DEMO-001 (PENDIENTE) - Juan - 23.49€");
        System.out.println("   📦 DEMO-002 (PAGADO) - Ana - 45.90€");
        System.out.println("   📦 DEMO-003 (ENVIADO) - Carlos - 15.80€");
    }
}