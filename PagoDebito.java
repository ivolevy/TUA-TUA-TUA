
public class PagoDebito implements PagoStrategy {
    @Override
    public double calcularMontoFinal(double monto) {
        return monto; // Sin descuento
    }
}
