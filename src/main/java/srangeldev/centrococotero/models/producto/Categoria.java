package srangeldev.centrococotero.models.producto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import srangeldev.centrococotero.utils.Utils;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categoria {
    @Id
    @Column(length = 11)
    private String id;

    @NotNull(message = "El tipo de categoría es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TipoCategoria tipo;

    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = Utils.generadorId();
        }
    }
}
