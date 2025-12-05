package srangeldev.centrococotero.data;

import srangeldev.centrococotero.producto.models.Categoria;
import srangeldev.centrococotero.producto.models.Producto;
import srangeldev.centrococotero.producto.models.TipoCategoria;
import srangeldev.centrococotero.usuario.models.Roles;
import srangeldev.centrococotero.usuario.models.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class DataFactory {

    public static List<Usuario> createTestUsers() {
        return Arrays.asList(
                Usuario.builder()
                        .username("admin")
                        .password("admin123") // En producción esto debe estar encriptado
                        .nombre("Admin")
                        .apellidos("Administrador")
                        .avatarUrl("https://robohash.org/admin?size=200x200&bgset=bg1")
                        .roles(Set.of(Roles.ADMIN))
                        .activo(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                Usuario.builder()
                        .username("prueba")
                        .password("prueba123")
                        .nombre("Prueba")
                        .apellidos("Probando Mucho")
                        .avatarUrl("https://robohash.org/prueba?size=200x200&bgset=bg2")
                        .roles(Set.of(Roles.USER))
                        .activo(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                Usuario.builder()
                        .username("moderador")
                        .password("moderador123")
                        .nombre("Moderador")
                        .apellidos("User")
                        .avatarUrl("https://api.dicebear.com/7.x/avataaars/svg?seed=moderador&backgroundColor=b6e3f4")
                        .roles(Set.of(Roles.USER))
                        .activo(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                Usuario.builder()
                        .username("otro")
                        .password("otro1234")
                        .nombre("Otro")
                        .apellidos("User")
                        .avatarUrl("https://api.dicebear.com/7.x/personas/svg?seed=otro&backgroundColor=c0aede")
                        .roles(Set.of(Roles.USER))
                        .activo(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                Usuario.builder()
                        .username("maria123")
                        .password("maria123")
                        .nombre("María")
                        .apellidos("García López")
                        .avatarUrl("https://api.dicebear.com/7.x/avataaars/svg?seed=maria&backgroundColor=ffd5dc")
                        .roles(Set.of(Roles.USER))
                        .activo(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                Usuario.builder()
                        .username("carlos123")
                        .password("carlos123")
                        .nombre("Carlos")
                        .apellidos("Rodríguez Pérez")
                        .avatarUrl("https://robohash.org/carlos?size=200x200&bgset=any&set=set1")
                        .roles(Set.of(Roles.USER))
                        .activo(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                Usuario.builder()
                        .username("ana123")
                        .password("ana12345")
                        .nombre("Ana")
                        .apellidos("Martín Sánchez")
                        .avatarUrl("https://api.dicebear.com/7.x/adventurer/svg?seed=ana&backgroundColor=ffdfbf")
                        .roles(Set.of(Roles.USER))
                        .activo(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                Usuario.builder()
                        .username("david123")
                        .password("david123")
                        .nombre("David")
                        .apellidos("López Torres")
                        .avatarUrl("https://robohash.org/david?size=200x200&bgset=bg1&set=set4")
                        .roles(Set.of(Roles.USER))
                        .activo(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                Usuario.builder()
                        .username("laura123")
                        .password("laura123")
                        .nombre("Laura")
                        .apellidos("Fernández Ruiz")
                        .avatarUrl("https://api.dicebear.com/7.x/big-smile/svg?seed=laura&backgroundColor=d1f2eb")
                        .roles(Set.of(Roles.USER))
                        .activo(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                Usuario.builder()
                        .username("javier123")
                        .password("javier123")
                        .nombre("Javier")
                        .apellidos("Moreno Silva")
                        .avatarUrl("https://robohash.org/javier?size=200x200&bgset=any&set=set3")
                        .roles(Set.of(Roles.USER))
                        .activo(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );
    }

    public static List<Producto> createTestProducts(Map<TipoCategoria, Categoria> categorias) {
        // Mapeo de categorías tropicales
        Categoria alimentos = categorias.get(TipoCategoria.ALIMENTOS_TROPICALES);
        Categoria bebidas = categorias.get(TipoCategoria.BEBIDAS);
        Categoria cosmetica = categorias.get(TipoCategoria.COSMETICA_NATURAL);
        Categoria aceites = categorias.get(TipoCategoria.ACEITES_DERIVADOS);
        Categoria accesorios = categorias.get(TipoCategoria.ACCESORIOS_PLAYA);
        Categoria decoracion = categorias.get(TipoCategoria.DECORACION_TROPICAL);
        Categoria textil = categorias.get(TipoCategoria.TEXTIL_VERANO);
        Categoria bienestar = categorias.get(TipoCategoria.BIENESTAR);
        Categoria artesania = categorias.get(TipoCategoria.ARTESANIA);
        Categoria otros = categorias.get(TipoCategoria.OTROS);
        
        // Si no existen, usamos una por defecto
        if (alimentos == null) alimentos = Categoria.builder().tipo(TipoCategoria.ALIMENTOS_TROPICALES).build();
        if (bebidas == null) bebidas = Categoria.builder().tipo(TipoCategoria.BEBIDAS).build();
        if (cosmetica == null) cosmetica = Categoria.builder().tipo(TipoCategoria.COSMETICA_NATURAL).build();
        if (aceites == null) aceites = Categoria.builder().tipo(TipoCategoria.ACEITES_DERIVADOS).build();
        if (accesorios == null) accesorios = Categoria.builder().tipo(TipoCategoria.ACCESORIOS_PLAYA).build();
        if (decoracion == null) decoracion = Categoria.builder().tipo(TipoCategoria.DECORACION_TROPICAL).build();
        if (textil == null) textil = Categoria.builder().tipo(TipoCategoria.TEXTIL_VERANO).build();
        if (bienestar == null) bienestar = Categoria.builder().tipo(TipoCategoria.BIENESTAR).build();
        if (artesania == null) artesania = Categoria.builder().tipo(TipoCategoria.ARTESANIA).build();
        if (otros == null) otros = Categoria.builder().tipo(TipoCategoria.OTROS).build();

        return Arrays.asList(
                // ACEITES Y DERIVADOS DE COCO
                Producto.builder()
                        .nombre("Aceite de Coco Virgen Orgánico 500ml")
                        .precio(new BigDecimal("12.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1474623809196-26c1d33457cc?w=400",
                            "https://images.unsplash.com/photo-1505751172876-fa1923c5c528?w=400",
                            "https://images.unsplash.com/photo-1598662957477-64b2e86d9421?w=400"))
                        .descripcion("Aceite de coco 100% virgen prensado en frío. Ideal para cocinar, cuidado de la piel y cabello. Certificado orgánico.")
                        .categoria(aceites)
                        .stock(50)
                        .build(),
                Producto.builder()
                        .nombre("Aceite de Coco Extra Virgen 1L")
                        .precio(new BigDecimal("19.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1505751172876-fa1923c5c528?w=400",
                            "https://images.unsplash.com/photo-1515694346937-94d85e41e6f0?w=400",
                            "https://images.unsplash.com/photo-1556228994-1a6c8c846819?w=400"))
                        .descripcion("Aceite de coco prensado en frío de primera calidad. Perfecto para cocina gourmet y tratamientos capilares.")
                        .categoria(aceites)
                        .stock(40)
                        .build(),
                Producto.builder()
                        .nombre("Manteca de Coco Orgánica 300g")
                        .precio(new BigDecimal("8.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1576426863848-c21f53c60b19?w=400",
                            "https://images.unsplash.com/photo-1587735243615-c03f25aaff15?w=400",
                            "https://images.unsplash.com/photo-1556228578-8c89e6adf883?w=400"))
                        .descripcion("Manteca de coco pura para repostería y cuidado corporal. Textura cremosa y aroma natural.")
                        .categoria(aceites)
                        .stock(60)
                        .build(),
                Producto.builder()
                        .nombre("Leche de Coco Premium 400ml")
                        .precio(new BigDecimal("3.49"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1563636619-e9143da7973b?w=400",
                            "https://images.unsplash.com/photo-1600271886742-f049cd451bba?w=400",
                            "https://images.unsplash.com/photo-1523049673857-eb18f1d7b578?w=400"))
                        .descripcion("Leche de coco cremosa sin aditivos. Ideal para curries, batidos y postres tropicales.")
                        .categoria(alimentos)
                        .stock(100)
                        .build(),
                Producto.builder()
                        .nombre("Coco Rallado Natural 250g")
                        .precio(new BigDecimal("4.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1589520465925-6c0feee87bf4?w=400",
                            "https://images.unsplash.com/photo-1599599810769-bcde5a160d32?w=400",
                            "https://images.unsplash.com/photo-1587735243615-c03f25aaff15?w=400"))
                        .descripcion("Coco rallado sin azúcar añadido. Perfecto para repostería, smoothies y decoración de postres.")
                        .categoria(alimentos)
                        .stock(80)
                        .build(),
                Producto.builder()
                        .nombre("Harina de Coco Sin Gluten 500g")
                        .precio(new BigDecimal("6.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1571064119049-8bedf4ec1da1?w=400",
                            "https://images.unsplash.com/photo-1628289000530-89f7bf662313?w=400",
                            "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=400"))
                        .descripcion("Harina de coco 100% natural, alta en fibra y proteína. Alternativa saludable para repostería sin gluten.")
                        .categoria(alimentos)
                        .stock(45)
                        .build(),
                Producto.builder()
                        .nombre("Azúcar de Coco Orgánico 400g")
                        .precio(new BigDecimal("7.49"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1514517521153-1be72277b32f?w=400",
                            "https://images.unsplash.com/photo-1587735243615-c03f25aaff15?w=400",
                            "https://images.unsplash.com/photo-1556228852-80f66bb1ba6b?w=400"))
                        .descripcion("Azúcar de coco con bajo índice glucémico. Endulzante natural rico en minerales para café y repostería.")
                        .categoria(alimentos)
                        .stock(55)
                        .build(),
                Producto.builder()
                        .nombre("Chips de Coco Tostado 150g")
                        .precio(new BigDecimal("5.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1599599810769-bcde5a160d32?w=400",
                            "https://images.unsplash.com/photo-1589520465925-6c0feee87bf4?w=400",
                            "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?w=400"))
                        .descripcion("Snack crujiente de coco natural tostado. Perfecto para picar entre horas o añadir a yogures.")
                        .categoria(alimentos)
                        .stock(70)
                        .build(),
                Producto.builder()
                        .nombre("Agua de Coco Natural 330ml")
                        .precio(new BigDecimal("2.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1556881286-fc6915169721?w=400",
                            "https://images.unsplash.com/photo-1600271886742-f049cd451bba?w=400",
                            "https://images.unsplash.com/photo-1608181689009-d0d3e4ab0de2?w=400"))
                        .descripcion("Agua de coco 100% pura, sin azúcares añadidos. Hidratación natural y refrescante.")
                        .categoria(bebidas)
                        .stock(120)
                        .build(),
                Producto.builder()
                        .nombre("Agua de Coco con Piña 1L")
                        .precio(new BigDecimal("4.49"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1600271886742-f049cd451bba?w=400",
                            "https://images.unsplash.com/photo-1595475207225-428b62bda831?w=400",
                            "https://images.unsplash.com/photo-1546173159-315724a31696?w=400"))
                        .descripcion("Deliciosa mezcla de agua de coco con zumo de piña tropical. Refrescante y energizante.")
                        .categoria(bebidas)
                        .stock(90)
                        .build(),

                // COSMÉTICA NATURAL
                Producto.builder()
                        .nombre("Jabón Artesanal de Coco 100g")
                        .precio(new BigDecimal("5.49"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1585933646398-43a092936b34?w=400",
                            "https://images.unsplash.com/photo-1608571423902-eed4a5ad8108?w=400",
                            "https://images.unsplash.com/photo-1600857062241-98e5de7f9144?w=400"))
                        .descripcion("Jabón natural hecho a mano con aceite de coco virgen. Hidratante y suave para todo tipo de pieles.")
                        .categoria(cosmetica)
                        .stock(85)
                        .build(),
                Producto.builder()
                        .nombre("Crema Hidratante Coco & Mango 200ml")
                        .precio(new BigDecimal("12.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1556228578-8c89e6adf883?w=400",
                            "https://images.unsplash.com/photo-1620916566398-39f1143ab7be?w=400",
                            "https://images.unsplash.com/photo-1612817288484-6f916006741a?w=400"))
                        .descripcion("Crema corporal hidratante con extracto de coco y mango. Absorción rápida, piel suave y aromática.")
                        .categoria(cosmetica)
                        .stock(60)
                        .build(),
                Producto.builder()
                        .nombre("Protector Solar Natural SPF 50")
                        .precio(new BigDecimal("16.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1556228453-efd6c1ff04f6?w=400",
                            "https://images.unsplash.com/photo-1608248543803-ba4f8c70ae0b?w=400",
                            "https://images.unsplash.com/photo-1605289982774-9a6fef564df8?w=400"))
                        .descripcion("Protector solar con aceite de coco y filtros naturales. Resistente al agua, no deja residuo blanco.")
                        .categoria(cosmetica)
                        .stock(75)
                        .build(),
                Producto.builder()
                        .nombre("Bálsamo Labial Coco & Vainilla")
                        .precio(new BigDecimal("3.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1596755389378-c31d21fd1273?w=400",
                            "https://images.unsplash.com/photo-1608571423902-eed4a5ad8108?w=400",
                            "https://images.unsplash.com/photo-1611930022073-b7a4ba5fcccd?w=400"))
                        .descripcion("Bálsamo labial natural con aceite de coco y esencia de vainilla. Hidratación duradera.")
                        .categoria(cosmetica)
                        .stock(100)
                        .build(),
                Producto.builder()
                        .nombre("Exfoliante Corporal Coco & Café")
                        .precio(new BigDecimal("14.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1608571423902-eed4a5ad8108?w=400",
                            "https://images.unsplash.com/photo-1629198726657-8c04e885ff56?w=400",
                            "https://images.unsplash.com/photo-1616401784845-180882ba9ba8?w=400"))
                        .descripcion("Exfoliante natural con coco rallado y café molido. Elimina células muertas y suaviza la piel.")
                        .categoria(cosmetica)
                        .stock(55)
                        .build(),
                Producto.builder()
                        .nombre("Mascarilla Capilar Coco Intensivo")
                        .precio(new BigDecimal("11.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1608248543803-ba4f8c70ae0b?w=400",
                            "https://images.unsplash.com/photo-1562322140-8baeececf3df?w=400",
                            "https://images.unsplash.com/photo-1516975080664-ed2fc6a32937?w=400"))
                        .descripcion("Tratamiento reparador para cabello dañado con aceite de coco virgen. Brillo y suavidad extremos.")
                        .categoria(cosmetica)
                        .stock(65)
                        .build(),

                // ACCESORIOS DE PLAYA
                Producto.builder()
                        .nombre("Bolsa de Playa Tropical XXL")
                        .precio(new BigDecimal("24.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1590739225017-e513ea9eac27?w=400",
                            "https://images.unsplash.com/photo-1581655353564-df123a1eb820?w=400",
                            "https://images.unsplash.com/photo-1590739225017-e513ea9eac27?w=400"))
                        .descripcion("Bolsa grande de yute con estampado de palmeras y cocos. Resistente al agua y arena.")
                        .categoria(accesorios)
                        .stock(50)
                        .build(),
                Producto.builder()
                        .nombre("Toalla de Playa Paradise 180x100cm")
                        .precio(new BigDecimal("29.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1581511919791-f76c2d86ebea?w=400",
                            "https://images.unsplash.com/photo-1621369746-4d93e7bce70e?w=400",
                            "https://images.unsplash.com/photo-1602810318383-e386cc2a3ccf?w=400"))
                        .descripcion("Toalla de microfibra con diseño tropical de cocos y palmeras. Secado rápido y compacta.")
                        .categoria(accesorios)
                        .stock(70)
                        .build(),
                Producto.builder()
                        .nombre("Sombrero de Paja Tropical")
                        .precio(new BigDecimal("18.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1588850561407-ed78c282e89b?w=400",
                            "https://images.unsplash.com/photo-1521369909029-2afed882baee?w=400",
                            "https://images.unsplash.com/photo-1529958030586-3aae4ca485ff?w=400"))
                        .descripcion("Sombrero artesanal de paja natural con cinta decorativa. Protección solar con estilo.")
                        .categoria(accesorios)
                        .stock(45)
                        .build(),
                Producto.builder()
                        .nombre("Chanclas Tropical Edition")
                        .precio(new BigDecimal("15.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1603487742131-4160ec999306?w=400",
                            "https://images.unsplash.com/photo-1560769680-ba4c197c6a1c?w=400",
                            "https://images.unsplash.com/photo-1515886657613-9f3515b0c78f?w=400"))
                        .descripcion("Chanclas cómodas con suela de caucho y diseño de cocos. Perfectas para playa y piscina.")
                        .categoria(accesorios)
                        .stock(90)
                        .build(),
                Producto.builder()
                        .nombre("Termo Tropical 500ml")
                        .precio(new BigDecimal("22.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1602143407151-7111542de6e8?w=400",
                            "https://images.unsplash.com/photo-1523362628745-0c100150b504?w=400",
                            "https://images.unsplash.com/photo-1534056958841-6f01dda98caa?w=400"))
                        .descripcion("Botella térmica de acero inoxidable con estampado de palmeras. Mantiene frío 24h.")
                        .categoria(accesorios)
                        .stock(60)
                        .build(),
                Producto.builder()
                        .nombre("Gafas de Sol Coconut Style")
                        .precio(new BigDecimal("34.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1511499767150-a48a237f0083?w=400",
                            "https://images.unsplash.com/photo-1508296695146-257a814070b4?w=400",
                            "https://images.unsplash.com/photo-1572635196237-14b3f281503f?w=400"))
                        .descripcion("Gafas de sol con protección UV400 y montura inspirada en la naturaleza tropical.")
                        .categoria(accesorios)
                        .stock(55)
                        .build(),

                // DECORACIÓN TROPICAL
                Producto.builder()
                        .nombre("Vela Aromática Coco & Lima 300g")
                        .precio(new BigDecimal("16.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1602874801006-597c7d10f3b7?w=400",
                            "https://images.unsplash.com/photo-1603006905003-be475563bc59?w=400",
                            "https://images.unsplash.com/photo-1602874801006-597c7d10f3b7?w=400"))
                        .descripcion("Vela de cera de soja con aroma tropical de coco y lima. 50 horas de combustión.")
                        .categoria(bienestar)
                        .stock(75)
                        .build(),
                Producto.builder()
                        .nombre("Difusor de Aromas Tropical Breeze")
                        .precio(new BigDecimal("19.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1585933646398-43a092936b34?w=400",
                            "https://images.unsplash.com/photo-1610383504713-412d6b0cd8ae?w=400",
                            "https://images.unsplash.com/photo-1603006905003-be475563bc59?w=400"))
                        .descripcion("Difusor con varillas de ratán y aceite esencial de coco. Ambientación durante 3 meses.")
                        .categoria(bienestar)
                        .stock(50)
                        .build(),
                Producto.builder()
                        .nombre("Cuenco Artesanal de Coco Natural")
                        .precio(new BigDecimal("12.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1610701596007-11502861dcfa?w=400",
                            "https://images.unsplash.com/photo-1505252585461-04db1eb84625?w=400",
                            "https://images.unsplash.com/photo-1533910534207-90f31029a78e?w=400"))
                        .descripcion("Bowl hecho de cáscara de coco reciclada. Ideal para smoothies, ensaladas o decoración.")
                        .categoria(artesania)
                        .stock(40)
                        .build(),
                Producto.builder()
                        .nombre("Set de Posavasos Coco (4 unidades)")
                        .precio(new BigDecimal("14.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1595429678249-67f8c4f9ed68?w=400",
                            "https://images.unsplash.com/photo-1556228578-dd6a8f067c55?w=400",
                            "https://images.unsplash.com/photo-1610701596007-11502861dcfa?w=400"))
                        .descripcion("Posavasos naturales de fibra de coco con diseños tropicales grabados. Sostenibles y decorativos.")
                        .categoria(artesania)
                        .stock(65)
                        .build(),
                Producto.builder()
                        .nombre("Maceta Colgante Tropical con Planta")
                        .precio(new BigDecimal("27.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1463936575829-25148e1db1b8?w=400",
                            "https://images.unsplash.com/photo-1485955900006-10f4d324d411?w=400",
                            "https://images.unsplash.com/photo-1466692476868-aef1dfb1e735?w=400"))
                        .descripcion("Maceta de fibra de coco con planta tropical incluida. Perfecta para interiores.")
                        .categoria(decoracion)
                        .stock(35)
                        .build(),
                Producto.builder()
                        .nombre("Cuadro Canvas Palmeras Sunset 60x40cm")
                        .precio(new BigDecimal("39.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1502933691298-84fc14542831?w=400",
                            "https://images.unsplash.com/photo-1473496169904-658ba7c44d8a?w=400",
                            "https://images.unsplash.com/photo-1519046904884-53103b34b206?w=400"))
                        .descripcion("Lienzo decorativo con imagen de palmeras al atardecer. Impresión de alta calidad.")
                        .categoria(decoracion)
                        .stock(30)
                        .build(),
                Producto.builder()
                        .nombre("Guirnalda LED Cocos Decorativa")
                        .precio(new BigDecimal("21.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1513885535751-8b9238bd345a?w=400",
                            "https://images.unsplash.com/photo-1543589077-47d81606c1bf?w=400",
                            "https://images.unsplash.com/photo-1511578194003-00c80e42dc9b?w=400"))
                        .descripcion("Guirnalda luminosa con cocos decorativos. 20 luces LED, perfecta para fiestas tropicales.")
                        .categoria(decoracion)
                        .stock(45)
                        .build(),

                // BEBIDAS Y MÁS
                Producto.builder()
                        .nombre("Ron Coco Premium 700ml")
                        .precio(new BigDecimal("24.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1514362545857-3bc16c4c7d1b?w=400",
                            "https://images.unsplash.com/photo-1560508139-7d1b6a53a2e4?w=400",
                            "https://images.unsplash.com/photo-1556881286-fc6915169721?w=400"))
                        .descripcion("Ron caribeño con infusión natural de coco. Perfecto para piñas coladas y cócteles tropicales.")
                        .categoria(bebidas)
                        .stock(50)
                        .build(),
                Producto.builder()
                        .nombre("Sirope de Coco para Cócteles 500ml")
                        .precio(new BigDecimal("9.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1575452879841-88650baaa37c?w=400",
                            "https://images.unsplash.com/photo-1556881286-fc6915169721?w=400",
                            "https://images.unsplash.com/photo-1600271886742-f049cd451bba?w=400"))
                        .descripcion("Sirope premium de coco para preparar mojitos, daiquiris y bebidas tropicales en casa.")
                        .categoria(bebidas)
                        .stock(70)
                        .build(),
                Producto.builder()
                        .nombre("Té Verde con Coco 20 bolsitas")
                        .precio(new BigDecimal("6.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1564890369478-c89ca6d9cde9?w=400",
                            "https://images.unsplash.com/photo-1544787219-7f47ccb76574?w=400",
                            "https://images.unsplash.com/photo-1597318110120-0977813e0656?w=400"))
                        .descripcion("Infusión de té verde con trozos de coco natural. Antioxidante y refrescante.")
                        .categoria(bebidas)
                        .stock(85)
                        .build(),
                Producto.builder()
                        .nombre("Mix Smoothie Tropical 250g")
                        .precio(new BigDecimal("8.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1505252585461-04db1eb84625?w=400",
                            "https://images.unsplash.com/photo-1546173159-315724a31696?w=400",
                            "https://images.unsplash.com/photo-1610701596007-11502861dcfa?w=400"))
                        .descripcion("Mezcla deshidratada de mango, piña y coco para smoothies instantáneos. Solo añade agua.")
                        .categoria(alimentos)
                        .stock(60)
                        .build(),
                Producto.builder()
                        .nombre("Chocolate con Coco Gourmet 100g")
                        .precio(new BigDecimal("7.49"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1481391319762-47dff72954d9?w=400",
                            "https://images.unsplash.com/photo-1511381939415-e44015466834?w=400",
                            "https://images.unsplash.com/photo-1549007994-cb92caebd54b?w=400"))
                        .descripcion("Chocolate negro 70% con trozos de coco tostado. Dulce tropical para sibarita.")
                        .categoria(alimentos)
                        .stock(80)
                        .build(),
                Producto.builder()
                        .nombre("Mermelada de Coco & Piña 300g")
                        .precio(new BigDecimal("5.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1484723091739-30a097e8f929?w=400",
                            "https://images.unsplash.com/photo-1472476443507-c7a5948772fc?w=400",
                            "https://images.unsplash.com/photo-1599599810769-bcde5a160d32?w=400"))
                        .descripcion("Mermelada artesanal de coco y piña sin conservantes. Sabor intenso y tropical.")
                        .categoria(alimentos)
                        .stock(50)
                        .build(),
                Producto.builder()
                        .nombre("Incienso Natural Aroma Coco 50 varillas")
                        .precio(new BigDecimal("9.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1603006905003-be475563bc59?w=400",
                            "https://images.unsplash.com/photo-1610383504713-412d6b0cd8ae?w=400",
                            "https://images.unsplash.com/photo-1602874801006-597c7d10f3b7?w=400"))
                        .descripcion("Varillas de incienso con fragancia natural de coco. Relajación y ambiente tropical.")
                        .categoria(bienestar)
                        .stock(70)
                        .build(),
                Producto.builder()
                        .nombre("Alfombra de Baño Fibra de Coco")
                        .precio(new BigDecimal("19.99"))
                        .imagenes(List.of(
                            "https://images.unsplash.com/photo-1556228578-dd6a8f067c55?w=400",
                            "https://images.unsplash.com/photo-1595429678249-67f8c4f9ed68?w=400",
                            "https://images.unsplash.com/photo-1610701596007-11502861dcfa?w=400"))
                        .descripcion("Alfombrilla natural de fibra de coco. Absorbente, antideslizante y ecológica.")
                        .categoria(artesania)
                        .stock(40)
                        .build()
        );
    }
}
