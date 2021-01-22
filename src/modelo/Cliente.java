package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Maria Angelica Caro
 */

public class Cliente {
    // Atributos
	private String rut;
	private String nombre;
	
	// Relaciones
	private ArrayList <Vivienda> misViviendasCompradas;
	private ArrayList <Vivienda> misViviendasReservadas;
	
	// Constructor
    public Cliente(String rut, String nom) {
        this.rut = rut;
        this.nombre = nom;
        misViviendasCompradas= new ArrayList <Vivienda>();
    	misViviendasReservadas= new ArrayList <Vivienda>();
    }

    // Operaciones
    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public Vivienda[] getViviendasCompradas() {
        Vivienda[] viviendasCompradas = new Vivienda[misViviendasCompradas.size()];
        int i = 0;
        
        for (Vivienda v : misViviendasCompradas){
            viviendasCompradas[i] = v;
            i++;
        }
        
        return viviendasCompradas;
    }

    public Vivienda[] getViviendasReservadas() {
        Vivienda[] viviendasReservadas = new Vivienda[misViviendasReservadas.size()];
        int i = 0;
        
        for (Vivienda v : misViviendasReservadas){
            viviendasReservadas[i] = v;
            i++;
        }
        
        return viviendasReservadas;
    }

    public void compra(Vivienda vivienda) {
        misViviendasCompradas.add(vivienda);
    }

    public void reserva(Vivienda vivienda) {
        misViviendasReservadas.add(vivienda);
    }

}
