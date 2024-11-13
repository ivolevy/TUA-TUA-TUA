import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Ubicacion platea = new Platea();
        Ubicacion palcoAlto = new PalcoAlto();
        Ubicacion palcoBajo = new PalcoBajo();
        Ubicacion cazuela = new Cazuela();
        Ubicacion tertulia = new Tertulia();
        Ubicacion paraiso = new Paraiso();

        List<Obra> obras = new ArrayList<>();
        obras.add(new Obra("Miércoles", LocalDate.of(2024, 11, 13), LocalTime.of(19, 0), 120, 
            List.of("David Tua", "Palentin Veñalva (tasty)"), List.of(platea, palcoAlto, cazuela, tertulia, paraiso)));
        obras.add(new Obra("Jueves", LocalDate.of(2024, 11, 14), LocalTime.of(20, 0), 90, 
            List.of("Santi Tomasini (policia)", "Marosek Linyera"), List.of(platea, palcoBajo, cazuela, paraiso)));
        obras.add(new Obra("Viernes", LocalDate.of(2024, 11, 15), LocalTime.of(21, 0), 110, 
            List.of("Artu Egui", "Jaz Martínez de Stella"), List.of(platea, palcoAlto, tertulia, paraiso)));
        obras.add(new Obra("Sábado", LocalDate.of(2024, 11, 16), LocalTime.of(18, 30), 130, 
            List.of("Guada Navos de Faure", "Dembele al arcoooooo GOL"), List.of(palcoBajo, cazuela, tertulia, paraiso)));
        obras.add(new Obra("Domingo", LocalDate.of(2024, 11, 17), LocalTime.of(17, 0), 95, 
            List.of("Bambino Pons", "Freijo (al final no renuncié)"), List.of(platea, tertulia, cazuela, paraiso)));

        System.err.println("---------------------------");
        System.out.println("Seleccione el método de pago:");
        System.out.println("1. Efectivo (10% descuento)");
        System.out.println("2. Débito (Sin descuento)");
        System.out.println("3. Crédito (con recargo: (2=6%) - (3=12%) - (6=20%) )");
        System.err.println("---------------------------");
        int metodoPago = scanner.nextInt();
        PagoStrategy pagoStrategy = obtenerMetodoPago(metodoPago);
        String metodoPagoSeleccionado = obtenerMetodoPagoNombre(metodoPago);

        Compra compra = new Compra(pagoStrategy);

        while (true) {
            System.err.println("---------------------------");
            System.out.println("Seleccione las obras de teatro deseada:");
            for (int i = 0; i < obras.size(); i++) {
                Obra obra = obras.get(i);
                System.out.println((i + 1) + ". " + obra.getDia() + " - " + obra.getFecha() + " - " + obra.getHora() +
                        " - Duración: " + obra.getDuracion() + " min - Actores: " + obra.getGrupoActores());
            }
            int obraSeleccionada = scanner.nextInt();
            Obra obraElegida = obras.get(obraSeleccionada - 1);
            System.err.println("---------------------------");
            System.out.println("Has seleccionado: " + obraElegida.getDia() + " - " + obraElegida.getFecha());

            System.err.println("---------------------------");
            System.out.println("Seleccione la ubicación:");
            System.out.println("1. Platea");
            System.out.println("2. Palco Alto");
            System.out.println("3. Palco Bajo");
            System.out.println("4. Cazuela");
            System.out.println("5. Tertulia");
            System.out.println("6. Paraíso");
            System.err.println("---------------------------");
            int ubicacionSeleccionada = scanner.nextInt();
            Ubicacion ubicacion = null;
            switch (ubicacionSeleccionada) {
                case 1: ubicacion = platea; break;
                case 2: ubicacion = palcoAlto; break;
                case 3: ubicacion = palcoBajo; break;
                case 4: ubicacion = cazuela; break;
                case 5: ubicacion = tertulia; break;
                case 6: ubicacion = paraiso; break;
                default: System.out.println("Opción inválida"); return;
            }

            if (obraElegida.hayDisponibilidadEnUbicacion(ubicacion)) {
                double precioEntrada = ubicacion.calcularPrecioFinal() + obraElegida.calcularPrecio();
                Entrada entrada = new EntradaSimple(ubicacion, precioEntrada);
                compra.agregarEntrada(entrada, obraElegida);

                if (obraElegida.reservarEntradaEnUbicacion(ubicacion)) {
                    System.out.println("Entrada agregada con éxito.");
                } else {
                    System.out.println("No se pudo reservar la entrada.");
                }
            } else {
                System.err.println("---------------------------");
                System.out.println("No hay disponibilidad para la ubicación seleccionada.");
            }

            System.out.println("¿Desea agregar otra entrada? (s/n)");
            System.err.println("---------------------------");
            String respuesta = scanner.next();
            if (respuesta.equalsIgnoreCase("n")) {
                break;
            }
        }

        double total = compra.calcularTotal();
        System.err.println("----- Ticket generado -----");
        System.err.println("Gracias por su compra en obras Tua-Faure & asociados");
        System.out.println("Método de pago seleccionado: " + metodoPagoSeleccionado);
        System.out.println("Su total a pagar es: $" + total);
        scanner.close();
    }

    private static PagoStrategy obtenerMetodoPago(int metodoPago) {
        if (metodoPago == 1) {
            return new PagoEfectivo();
        } else if (metodoPago == 2) {
            return new PagoDebito();
        } else {
            System.out.println("Seleccione la cantidad de cuotas (2, 3 o 6):");
            @SuppressWarnings("resource")
            int cuotas = new Scanner(System.in).nextInt();
            return new PagoCredito(cuotas);
        }
    }

    private static String obtenerMetodoPagoNombre(int metodoPago) {
        switch (metodoPago) {
            case 1: return "Efectivo (10% descuento)";
            case 2: return "Débito (Sin descuento)";
            case 3: return "Crédito";
            default: return "Método de pago desconocido";
        }
    }
}
