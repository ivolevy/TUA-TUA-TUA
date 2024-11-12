public abstract class Entrada {
    protected Ubicacion ubicacion;
    protected double precioBase;
    protected static Obra obra;

    @SuppressWarnings("static-access")
    public Entrada(Ubicacion ubicacion, double precioBase, Obra obra) {
        this.ubicacion = ubicacion;
        this.precioBase = precioBase;
        this.obra = obra;
    }

    public Obra getObra() {
        return obra;
    }

    public abstract double calcularPrecioFinal();
}
