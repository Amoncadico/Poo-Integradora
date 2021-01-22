package modelo;

/**
 *
 * @author Maria Angelica Caro
 */

public class Casa extends Vivienda {
	// Atributos
    private Estilo estilo;

    public Casa(String direccion, int tamanio, byte nroHabitaciones, Estilo estilo) {
        super(direccion, tamanio, nroHabitaciones);
        this.estilo = estilo;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public String toString() {
        String estiloso = "";
        
        switch (estilo) {
            case MODERNO:
                estiloso = "Moderno";
                break;
            case COLONIAL:
                estiloso = "Colonial";
                break;
            case RUSTICO:
                estiloso = "Rustico";
                break;
        }
        
        return super.getDireccion() + ", " + super.getTamanio() + ", " + super.getNroHabitaciones() + ", " + estiloso;
    }

}
