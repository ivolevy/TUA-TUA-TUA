import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear las cinco obras disponibles
        List<Obra> obras = generarObras();

        // Configuración de compra: primero el método de pago
        System.err.println("----------------------------");
        System.out.println("Seleccione el método de pago:");
        System.out.println("1. Efectivo (10% descuento)");
        System.out.println("2. Débito (Sin descuento)");
        System.out.println("3. Crédito (con recargo: (2=6%) - (3=12%) - (6=20%) )");
        System.err.println("----------------------------");

        int metodoPago = scanner.nextInt();
        String metodoPagoTexto = obtenerMetodoPagoTexto(metodoPago);
        PagoStrategy pagoStrategy;

        if (metodoPago == 1) {
            pagoStrategy = new PagoEfectivo();
        } else if (metodoPago == 2) {
            pagoStrategy = new PagoDebito();
        } else {
            System.err.println("----------------------------");
            System.out.println("Seleccione la cantidad de cuotas (2, 3 o 6):");
            System.err.println("----------------------------");
            int cuotas = scanner.nextInt();
            pagoStrategy = new PagoCredito(cuotas);
        }

        Compra compra = new Compra(pagoStrategy);

        // Mostrar opciones de obras después de seleccionar el método de pago
        while (true) {
            System.err.println("----------------------------");
            System.out.println("Seleccione las obras de teatro deseada: ");
            for (int i = 0; i < obras.size(); i++) {
                Obra obra = obras.get(i);
                System.out.println((i + 1) + ". " + obra.getDia() + " - " + obra.getFecha() + " - " + obra.getHora() +
                        " - Duración: " + obra.getDuracion() + " min - Actores: " + obra.getGrupoActores());
            }
            System.err.println("----------------------------");
            int obraSeleccionada = scanner.nextInt();
            if (obraSeleccionada < 1 || obraSeleccionada > obras.size()) {
                System.out.println("Selección inválida. Intente nuevamente.");
                continue;
            }

            Obra obraElegida = obras.get(obraSeleccionada - 1);
            System.out.println("Has seleccionado: " + obraElegida.getDia() + " - " + obraElegida.getFecha());

            // Crear ubicaciones para las obras
            Ubicacion platea = new Platea();
            Ubicacion palcoAlto = new PalcoAlto();
            Ubicacion palcoBajo = new PalcoBajo();
            Ubicacion cazuela = new Cazuela();
            Ubicacion tertulia = new Tertulia();
            Ubicacion paraiso = new Paraiso();

            // Seleccionar ubicación
            System.err.println("----------------------------");
            System.out.println("Seleccione la ubicación:");
            System.out.println("1. Platea");
            System.out.println("2. Palco Alto");
            System.out.println("3. Palco Bajo");
            System.out.println("4. Cazuela");
            System.out.println("5. Tertulia");
            System.out.println("6. Paraíso");
            System.err.println("----------------------------");

            int ubicacionSeleccionada = scanner.nextInt();
            Ubicacion ubicacion = null;
            switch (ubicacionSeleccionada) {
                case 1:
                    ubicacion = platea;
                    break;
                case 2:
                    ubicacion = palcoAlto;
                    break;
                case 3:
                    ubicacion = palcoBajo;
                    break;
                case 4:
                    ubicacion = cazuela;
                    break;
                case 5:
                    ubicacion = tertulia;
                    break;
                case 6:
                    ubicacion = paraiso;
                    break;
                default:
                    System.out.println("Selección inválida");
                    continue;
            }

            // Verificar disponibilidad y agregar entrada
            if (ubicacion.hayDisponibilidad()) {
                double precioEntrada = ubicacion.calcularPrecioFinal() + obraElegida.calcularPrecio();
                Entrada entrada = new EntradaSimple(ubicacion, precioEntrada);
                compra.agregarEntrada(entrada, obraElegida);
                ubicacion.reservarEntrada();
                System.out.println("Entrada agregada con éxito.");
            } else {
                System.out.println("No hay disponibilidad para la ubicación seleccionada.");
            }

            // Preguntar si se desea agregar otra entrada
            System.out.println("¿Desea agregar otra entrada? (s/n)");
            System.err.println("----------------------------");
            String respuesta = scanner.next();
            if (respuesta.equalsIgnoreCase("n")) {
                break;
            }
        }

        // Mostrar total y finalizar compra
        double total = compra.calcularTotal();
        System.err.println("----------------------------");
        System.out.println("Gracias por su compra en Obras Tua. Su ticket ha sido generado.");
        System.out.println("Número de ticket: " + compra.getNumeroFactura());
        System.out.println("Medio de pago: " + metodoPagoTexto);
        System.out.println("Total a pagar: $" + total);
        System.err.println("----------------------------");
        scanner.close();
    }

    // Método para convertir el número de método de pago en texto
    private static String obtenerMetodoPagoTexto(int metodoPago) {
        switch (metodoPago) {
            case 1:
                return "Efectivo";
            case 2:
                return "Débito";
            case 3:
                return "Crédito";
            default:
                return "Desconocido";
        }
    }

    // Método para generar cinco obras
    private static List<Obra> generarObras() {
        List<Obra> obras = new ArrayList<>();
        obras.add(new Obra("Miércoles", LocalDate.of(2024, 11, 13), LocalTime.of(19, 0), 120, List.of("David Tua", "Palentin Veñalva (tasty)")));
        obras.add(new Obra("Jueves", LocalDate.of(2024, 11, 14), LocalTime.of(20, 0), 90, List.of("Santi Tomasini (policia)", "Marosek Linyera")));
        obras.add(new Obra("Viernes", LocalDate.of(2024, 11, 15), LocalTime.of(21, 0), 110, List.of("Artu Egui", "Moli Luñez")));
        obras.add(new Obra("Sábado", LocalDate.of(2024, 11, 16), LocalTime.of(18, 30), 130, List.of("Tomi Faure", "Dembele al arcoooooo GOL")));
        obras.add(new Obra("Domingo", LocalDate.of(2024, 11, 17), LocalTime.of(17, 0), 95, List.of("Bambino Pons", "Pipper Perri")));
        return obras;
    }
}