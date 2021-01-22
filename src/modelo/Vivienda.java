package modelo;

import java.util.ArrayList;

/**
 * @author Maria Angelica Caro
 */

public abstract class Vivienda {
    // Atributos
    private String direccion;
    private int tamanio;
    private byte nroHabitaciones;

    // Relaciones
    private Cliente miClienteCompra;
    private Cliente miClienteReserva;

    // Constructor
    public Vivienda(String direccion, int tamanio, byte nroHabitaciones) {
        this.direccion = direccion;
        this.tamanio = tamanio;
        this.nroHabitaciones = nroHabitaciones;
    }

    // Operaciones
    public String getDireccion() {
        return direccion;
    }

    public int getTamanio() {
        return tamanio;
    }

    public byte getNroHabitaciones() {
        return nroHabitaciones;
    }

    public Cliente getClienteQueCompra() {
        return this.miClienteCompra;
    }
    public void asociaClienteQueCompra(Cliente cliente) {
        miClienteCompra = cliente;
        miClienteCompra.compra(this);
    }

    public void dejaEnReserva(Cliente cliente) {
        cliente.reserva(this);
        miClienteReserva = cliente;
    }

    public Cliente getClienteDeReserva() {
        return this.miClienteReserva;
    }
        
    public void liberaReserva() {
    	
        Vivienda[] viviendas = miClienteReserva.getViviendasReservadas();
        Vivienda[] viviendas2 = new Vivienda[viviendas.length - 1];
        
        int r = 0;
        for (int i = 0; i < viviendas.length; i++){
            if (viviendas[i] != this){
                viviendas2[r] = viviendas[i];
                r++;
            }
        }
        
        miClienteReserva = null;
      
    }

    public Cliente getMiClienteReserva() {
        return miClienteReserva;
    }
    
    

    public boolean estaDisponible() {
        
    	if(miClienteCompra == null && miClienteReserva == null){
            return true;
        }
    	return false;
    }

    public String toString() {
        
        return direccion + "," + tamanio +", " + nroHabitaciones;
    }

}
