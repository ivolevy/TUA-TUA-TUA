import java.util.ArrayList;
import java.util.List;

public class Compra {
    private List<Entrada> entradas;
    private List<Obra> obras; // Lista de obras
    private PagoStrategy metodoPago;

    public Compra(PagoStrategy metodoPago) {
        this.entradas = new ArrayList<>();
        this.obras = new ArrayList<>();
        this.metodoPago = metodoPago;
    }

    public void agregarEntrada(Entrada entrada, Obra obra) {
        entradas.add(entrada);
        if (!obras.contains(obra)) {
            obras.add(obra); // Agregar la obra solo si no está ya en la lista
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (Entrada entrada : entradas) {
            total += entrada.calcularPrecioFinal();
        }
        return metodoPago.calcularMontoFinal(total);
    }

    public List<Obra> getObras() {
        return obras;
    }
}
