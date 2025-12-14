package srangeldev.centrococotero.producto.services;

import srangeldev.centrococotero.producto.models.Comentario;
import srangeldev.centrococotero.producto.models.Producto;
import srangeldev.centrococotero.producto.models.TipoCategoria;

import java.util.List;

public interface ProductoService {
    // Productos
    List<Producto> findAll();
    Producto findById(String id);
    List<Producto> findByCategoria(TipoCategoria categoria);
    List<Producto> buscarPorNombre(String nombre);
    Producto save(Producto producto);
    void deleteById(String id);

    // Comentarios
    List<Comentario> obtenerComentarios(String productoId);
    Comentario guardarComentario(Comentario comentario);
}