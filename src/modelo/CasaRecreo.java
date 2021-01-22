package modelo;

/**
 *
 * @author Maria Angelica Caro
 */

public class CasaRecreo extends Casa {
    // Atributos
    private Entorno entorno;

    public CasaRecreo(String direccion, int tamanio, byte nroHabitaciones, Estilo estilo, Entorno entorno) {
        super(direccion, tamanio, nroHabitaciones, estilo);
        this.entorno = entorno;
        
    }
    
    // Operaciones
    public Entorno getEntorno() {
        return entorno;
    }

    public void setEntorno(Entorno entorno) {
        this.entorno = entorno;
    }
    
    public String toString() {
        String estiloso = "";
        
        switch (super.getEstilo()) {
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
        
        String ento = "";
        
        switch (entorno){
            case LAGO:
                ento = "Lago";
                break;
            case CORDILLERA:
                ento = "Cordillera";
                break;
            case MAR:
                ento = "Mar";
                break;
            case CAMPO:
                ento = "Campo";
                break;
        }
        
        return super.getDireccion() + ", " + super.getTamanio() + ", " + super.getNroHabitaciones() +
               ", " + estiloso + ", " + ento;
    }
    
}
