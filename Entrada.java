
public abstract class Entrada {
    protected Ubicacion ubicacion;
    protected double precioBase;

    public Entrada(Ubicacion ubicacion, double precioBase) {
        this.ubicacion = ubicacion;
        this.precioBase = precioBase;
    }

    public abstract double calcularPrecioFinal();

    protected abstract Object getUbicacion();
}
