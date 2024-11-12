
public class PagoEfectivo implements PagoStrategy {
    @Override
    public double calcularMontoFinal(double monto) {
        return monto * 0.9; // Descuento del 10%
    }
}
