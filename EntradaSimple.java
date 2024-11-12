
public class EntradaSimple extends Entrada {
    public EntradaSimple(Ubicacion ubicacion, double precioBase) {
        super(ubicacion, precioBase);
    }

    @Override
    public double calcularPrecioFinal() {
        return precioBase + ubicacion.calcularPrecioFinal();
    }

    @Override
    protected Object getUbicacion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUbicacion'");
    }
}
