package srangeldev.centrococotero.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import srangeldev.centrococotero.models.ItemCarrito;
import srangeldev.centrococotero.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, String> {
    
    @Query("SELECT i FROM ItemCarrito i WHERE i.usuario.id = :usuarioId")
    List<ItemCarrito> findByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT i FROM ItemCarrito i WHERE i.usuario.id = :usuarioId AND i.producto.id = :productoId")
    Optional<ItemCarrito> findByUsuarioIdAndProductoId(@Param("usuarioId") Long usuarioId, @Param("productoId") String productoId);
    
    @Query("SELECT COUNT(i) FROM ItemCarrito i WHERE i.usuario.id = :usuarioId")
    long countByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    void deleteByUsuario(Usuario usuario);
}
