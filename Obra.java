import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Obra {
    private String dia;
    private LocalDate fecha;
    private LocalTime hora;
    private int duracion; // En minutos
    private List<String> grupoActores;
    private List<Ubicacion> ubicacionesDisponibles; // Lista de ubicaciones para cada obra

    public Obra(String dia, LocalDate fecha, LocalTime hora, int duracion, List<String> grupoActores, List<Ubicacion> ubicacionesDisponibles) {
        this.dia = dia;
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
        this.grupoActores = grupoActores;
        this.ubicacionesDisponibles = ubicacionesDisponibles;
    }

    public String getDia() { return dia; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public int getDuracion() { return duracion; }
    public List<String> getGrupoActores() { return grupoActores; }
    public List<Ubicacion> getUbicacionesDisponibles() { return ubicacionesDisponibles; }

    public double calcularPrecio() {
        return 100 + duracion * 2; // Ejemplo de cálculo
    }

    // Verifica si la obra tiene disponibilidad en una ubicación específica
    public boolean hayDisponibilidadEnUbicacion(Ubicacion ubicacion) {
        return ubicacion.hayDisponibilidad();
    }

    // Reserva una entrada en la ubicación de la obra
    public boolean reservarEntradaEnUbicacion(Ubicacion ubicacion) {
        return ubicacion.reservarEntrada();
    }
}
