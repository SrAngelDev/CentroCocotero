package srangeldev.centrococotero.producto.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import srangeldev.centrococotero.producto.models.Comentario;

import java.util.List;

@Repository
public interface ComentarioRepository extends MongoRepository<Comentario, String> {
    List<Comentario> findByProductoId(String productoId);
}