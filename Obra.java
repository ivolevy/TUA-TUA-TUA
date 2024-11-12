import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Obra {
    private String dia;
    private LocalDate fecha;
    private LocalTime hora;
    private int duracion; // en minutos
    private List<String> grupoActores;

    public Obra(String dia, LocalDate fecha, LocalTime hora, int duracion, List<String> grupoActores) {
        this.dia = dia;
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
        this.grupoActores = grupoActores;
    }

    // Getters
    public String getDia() { return dia; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public int getDuracion() { return duracion; }
    public List<String> getGrupoActores() { return grupoActores; }

    public double calcularPrecio() {
        return 100 + duracion * 2; // Ejemplo de cálculo
    }
}
