
public class PagoCredito implements PagoStrategy {
    private int cuotas;

    public PagoCredito(int cuotas) {
        this.cuotas = cuotas;
    }

    @Override
    public double calcularMontoFinal(double monto) {
        switch (cuotas) {
            case 2:
                return monto * 1.06; // Recargo del 6%
            case 3:
                return monto * 1.12; // Recargo del 12%
            case 6:
                return monto * 1.20; // Recargo del 20%
            default:
                return monto;
        }
    }
}
