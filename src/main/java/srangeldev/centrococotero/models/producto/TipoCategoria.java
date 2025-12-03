package srangeldev.centrococotero.models.producto;

public enum TipoCategoria {
    ELECTRONICA("Electrónica"),
    ROPA("Ropa y Moda"),
    HOGAR("Hogar y Jardín"),
    DEPORTES("Deportes y Fitness"),
    LIBROS("Libros y Papelería"),
    JUGUETES("Juguetes y Juegos"),
    BELLEZA("Belleza y Cuidado Personal"),
    ALIMENTACION("Alimentación"),
    MASCOTAS("Mascotas"),
    OTROS("Otros");

    private final String descripcion;

    TipoCategoria(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
