import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Obra {
    private String dia;
    private LocalDate fecha;
    private LocalTime hora;
    private int duracion; 
    private List<String> grupoActores;
    private List<Ubicacion> ubicacionesDisponibles; 

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
        return 100 + duracion * 2; 
    }

    public boolean hayDisponibilidadEnUbicacion(Ubicacion ubicacion) {
        return ubicacion.hayDisponibilidad();
    }

    public boolean reservarEntradaEnUbicacion(Ubicacion ubicacion) {
        return ubicacion.reservarEntrada();
    }
}
