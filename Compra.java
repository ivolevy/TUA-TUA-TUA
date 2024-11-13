import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Compra {
    private static int contadorFactura = 1;
    private String numeroFactura;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String fechaHora = LocalDateTime.now().format(formatter);
        
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
