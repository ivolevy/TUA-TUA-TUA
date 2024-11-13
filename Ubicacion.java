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

    // Calcula el precio final de la entrada en base al precio base
    public double calcularPrecioFinal() {
        return precioBase;
    }

    // Verifica si hay disponibilidad según la capacidad máxima
    public boolean hayDisponibilidad() {
        return entradasVendidas < capacidadMaxima;
    }

    // Reserva una entrada si hay disponibilidad
    public boolean reservarEntrada() {
        if (hayDisponibilidad()) {
            entradasVendidas++; // Incrementa el contador de entradas vendidas
            return true;
        }
        return false;
    }
    
    // Getters opcionales para acceder a capacidad y entradas vendidas en caso de ser necesario
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public int getEntradasVendidas() {
        return entradasVendidas;
    }
}
