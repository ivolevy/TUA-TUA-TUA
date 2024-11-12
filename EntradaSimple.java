
public class EntradaSimple extends Entrada {    public EntradaSimple(Ubicacion ubicacion, double precioBase) {
        super(ubicacion, precioBase, obra);
    }

    @Override
    public double calcularPrecioFinal() {
        return precioBase + ubicacion.calcularPrecioFinal();
    }
}
