package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import modelo.*;
import persistencia.IOConstructora;

/**
 *
 * @author Maria Angelica Caro
 */
public class Controlador {

    // Atributos
    private static Controlador instancia = null;

    // Relaciones
    private ArrayList<Vivienda> misViviendas;
    private ArrayList<Cliente> misClientes;
    private IOConstructora ioConstructora;

    //Constructor
    private Controlador() {
        misViviendas = new ArrayList<Vivienda>();
        misClientes = new ArrayList<Cliente>();
        this.ioConstructora = IOConstructora.getInstance();
    }

    public static Controlador getInstancia() {
        if (instancia == null) {
            instancia = new Controlador();
        }
        return instancia;
    }

    public void addCliente(String rut, String nombre) throws DataFormatException {

        if (findCliente(rut) == null) {
            Cliente cliente = new Cliente(rut, nombre);
            misClientes.add(cliente);
        } else {
            throw new DataFormatException("Este cliente ya existe");
        }
    }

    public void construyeCasa(String direc, int tam, byte nroHab, Estilo estilo) throws DataFormatException {

        if (findVivienda(direc) == null) {
            Casa casa = new Casa(direc, tam, nroHab, estilo);
            misViviendas.add(casa);
        } else {
            throw new DataFormatException("Esta vivienda ya existe");
        }
    }

    public void construyeCasaRecreo(String direc, int tam, byte nroHab, Estilo estilo, Entorno entorno) throws DataFormatException {

        if (findVivienda(direc) == null) {
            CasaRecreo casaRecreo = new CasaRecreo(direc, tam, nroHab, estilo, entorno);
            misViviendas.add(casaRecreo);
        } else {
            throw new DataFormatException("Esta vivienda ya existe");
        }
    }

    public void reservaVivienda(String rut, String direccion) throws DataFormatException {

        Cliente cliente = findCliente(rut);

        if (cliente != null) {
            Vivienda vivienda = findVivienda(direccion);

            if (vivienda != null) {
                vivienda.dejaEnReserva(cliente);
            } else {
                throw new DataFormatException("Este cliente no existe");
            }
        } else {
            throw new DataFormatException("Este cliente no existe");
        }

    }

    public void vendeViviendaReservada(String rut, String direccion) throws DataFormatException {

        Cliente cliente = findCliente(rut);
        Vivienda vivienda = findVivienda(direccion);

        if (cliente == null) {
            throw new DataFormatException("El cliente no existe");
        }

        if (vivienda == null) {
            throw new DataFormatException("La vivienda no existe");
        }

        Vivienda[] viviendas = cliente.getViviendasReservadas();

        for (Vivienda v : viviendas) {
            if (v.getDireccion().equalsIgnoreCase(vivienda.getDireccion())) {
                v.liberaReserva();
                v.asociaClienteQueCompra(cliente);
                return;
            }
        }

        if (vivienda.estaDisponible() == false) {
            throw new DataFormatException("No esta disponible");
        }
        vivienda.asociaClienteQueCompra(cliente);

    }

    public String[] getViviendasDisponiblesEn(String sector) throws DataFormatException {

        int contador = 0;

        for (Vivienda v : misViviendas) {
            if (v.getDireccion().contains(sector)) {
                contador++;
            }
        }

        String[] viviendas2 = new String[contador];

        int i = 0;
        for (Vivienda v : misViviendas) {
            if (v.getDireccion().contains(sector)) {
                viviendas2[i] = v.toString();
                i++;
            }
        }

        return viviendas2;
    }

    public String[] getViviendasDeCliente(String rut) throws DataFormatException {

        Cliente cliente = findCliente(rut);

        if (cliente == null) {
            throw new DataFormatException("Cliente no existe");
        }

        int i = cliente.getViviendasCompradas().length + cliente.getViviendasReservadas().length;
        String[] total = new String[i];

        if (i > 0) {

            int r = 0;
            for (Vivienda v : cliente.getViviendasCompradas()) {
                total[r] = v.toString() + " - Comprada";
                r++;
            }

            for (Vivienda v : cliente.getViviendasReservadas()) {
                total[r] = v.toString() + " - Reservada";
                r++;
            }

            return total;
        } 
        
        throw new DataFormatException("Este cliente no tiene viviendas");
    }

    public void leeViviendas() throws DataFormatException, FileNotFoundException {
        ioConstructora = IOConstructora.getInstance();
        try {
            ioConstructora.readViviendas();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void grabaViviendas() throws DataFormatException {
        ioConstructora = IOConstructora.getInstance();
        try {
            Vivienda[] vivi = new Vivienda[misViviendas.size()];
            int i = 0;
            
            for (Vivienda v : misViviendas){
                vivi[i] = v;
                i++;
            } 
            
            ioConstructora.saveConstructora(vivi);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void creaClientesYViviendas() throws DataFormatException {

        misViviendas.add(new Casa("Vega de Saldias 450, Chillan", 84, (byte) 3, Estilo.MODERNO));
        misViviendas.add(new Casa("Vicente Mendez 130, Chillan", 75, (byte) 3, Estilo.COLONIAL));
        misViviendas.add(new Casa("Libertad 945, Bulnes", 110, (byte) 3, Estilo.MODERNO));
        misViviendas.add(new Casa("Libertad 955, Bulnes", 110, (byte) 3, Estilo.MODERNO));
        misViviendas.add(new CasaRecreo("Los Jazmines 780, J. de Nuble, Termas de Chillan", 76, (byte) 3, Estilo.MODERNO, Entorno.CORDILLERA));
        misViviendas.add(new CasaRecreo("Los Jazmines 740, J. de Nuble, Termas de Chillan", 76, (byte) 3, Estilo.MODERNO, Entorno.CORDILLERA));
        misViviendas.add(new CasaRecreo("Los Lirios 130, Dichato", 76, (byte) 3, Estilo.MODERNO, Entorno.MAR));

        misClientes.add(new Cliente("9234123-6", "Julio Carrasco"));
        misClientes.add(new Cliente("11273372-0", "Claudia Lopez"));

        reservaVivienda("9234123-6", "Vega de Saldias 450, Chillan");
        reservaVivienda("9234123-6", "Los Lirios 130, Dichato");

        vendeViviendaReservada("9234123-6", "Los Lirios 130, Dichato");
        vendeViviendaReservada("11273372-0", "Libertad 945, Bulnes");

    }

    private Cliente findCliente(String rut) {
        for (Cliente cliente : misClientes) {
            if (cliente.getRut().equalsIgnoreCase(rut)) {
                return cliente;
            }
        }
        return null;
    }

    private Vivienda findVivienda(String direccion) {
        for (Vivienda vivienda : misViviendas) {
            if (vivienda.getDireccion().equalsIgnoreCase(direccion)) {
                return vivienda;
            }
        }
        return null;
    }

}
