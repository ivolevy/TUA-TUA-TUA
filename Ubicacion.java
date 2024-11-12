
public class Ubicacion {
    @SuppressWarnings("unused")
    private String nombre;
    private double precioBase;
    private int capacidadMaxima;
    private int entradasVendidas;
    
    public Ubicacion(String nombre, double precioBase, int capacidadMaxima) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.capacidadMaxima = capacidadMaxima;
        this.entradasVendidas = 0;
    }

    public double calcularPrecioFinal() {
        return precioBase;
    }

    public boolean hayDisponibilidad() {
        return entradasVendidas < capacidadMaxima;
    }

    public boolean reservarEntrada() {
        if (hayDisponibilidad()) {
            entradasVendidas++;
            return true;
        }
        return false;
    }
}
