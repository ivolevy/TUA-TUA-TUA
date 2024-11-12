import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Compra {
    private static int contadorFactura = 1; // Contador para generar números únicos de factura
    private String numeroFactura; // Número de factura personalizado
    private List<Entrada> entradas;
    private List<Obra> obras;
    private PagoStrategy metodoPago;

    public Compra(PagoStrategy metodoPago) {
        this.entradas = new ArrayList<>();
        this.obras = new ArrayList<>();
        this.metodoPago = metodoPago;
        this.numeroFactura = generarNumeroFactura();
    }

    private String generarNumeroFactura() {
        // Obtener la fecha y hora actual en un formato específico
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String fechaHora = LocalDateTime.now().format(formatter);
        
        // Combinar el contador con la fecha y hora para generar un ID más largo
        String numeroFactura = "TCKT-" + fechaHora + "-" + String.format("%03d", contadorFactura++);
        
        return numeroFactura;
    }

    public void agregarEntrada(Entrada entrada, Obra obra) {
        entradas.add(entrada);
        if (!obras.contains(obra)) {
            obras.add(obra);
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (Entrada entrada : entradas) {
            total += entrada.calcularPrecioFinal();
        }
        return metodoPago.calcularMontoFinal(total);
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public List<Obra> getObras() {
        return obras;
    }
}
