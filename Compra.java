import java.util.ArrayList;
import java.util.List;

public class Compra {
    private List<Entrada> entradas;
    private PagoStrategy metodoPago;

    public Compra(PagoStrategy metodoPago) {
        this.entradas = new ArrayList<>();
        this.metodoPago = metodoPago;
    }

    public void agregarEntrada(Entrada entrada) {
        entradas.add(entrada);
    }

    public double calcularTotal() {
        double total = 0;
        for (Entrada entrada : entradas) {
            total += entrada.calcularPrecioFinal();
        }
        return metodoPago.calcularMontoFinal(total);
    }
}
