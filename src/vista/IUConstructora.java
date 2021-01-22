package vista;

import controlador.Controlador;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

/**
 * @author Maria Angelica Caro
 */
public class IUConstructora {
	// Atributos
    private static IUConstructora instance;

    // Relaciones
    private Controlador controlador;

    // Constructor
    private IUConstructora() {
        this.controlador = Controlador.getInstancia();
    }

    // Operaciones
    public static IUConstructora getInstance() {
        if (instance == null) {
            instance = new IUConstructora();
        }
        return instance;
    }

    public static void main(String[] args) throws DataFormatException {
        IUConstructora constructora = IUConstructora.getInstance();
        constructora.inicia();
    }

    public void inicia() throws DataFormatException {

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\\s*\n");
        int opcion = 0;
        String sector, rut;

        System.out.println("Inicializando el sistema Constructora...");
        try {
            controlador.leeViviendas();
            System.out.println("Operacion realizada exitosamente... Datos leidos desde archivo");
        } catch (DataFormatException e) {
            controlador.creaClientesYViviendas();
            System.out.println("Operacion realizada exitosamente... Datos creados");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IUConstructora.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Menú de la aplicación
        do {
            System.out.println("\n\n\nMENU Sistema Constructora\n");
            System.out.println("1: Obtiene listado de Viviendas Disponibles");
            System.out.println("2: Obtiene listado de Viviendas por Cliente");
            System.out.println("3: Salir");

            try {
                System.out.print("\n\tIngrese opcion: ");
                opcion = Integer.parseInt(sc.next());

                switch (opcion) {
                    case 1:	// Listado viviendas disponibles por sector 
                        System.out.print("\n\tIngrese sector: ");
                        sector = sc.next();

                        String[] disp = controlador.getViviendasDisponiblesEn(sector);
                        if (disp.length > 0) {
                        	System.out.println("\nViviendas disponibles en " + sector + ":");
                            for (int i = 0; i < disp.length; i++) {
                                System.out.println(disp[i]);
                            }
                        } else {
                            System.out.println("\nNo quedan viviendas disponibles");
                        }
                        break;

                    case 2:	// Listado viviendas por cliente
                        System.out.print("\n\tIngrese rut cliente: ");
                        rut = sc.next();

                        try {
                        	System.out.println("\nViviendas reservadas o compradas por el cliente:");
                            String[] vivCliente = controlador.getViviendasDeCliente(rut);
                            for (int i = 0; i < vivCliente.length; i++) {
                                System.out.println(vivCliente[i]);
                            }
                        } catch (DataFormatException e) {
                            System.out.println("\nError... " + e.getMessage());
                        }
                        break;

                    case 3:	// Saliendo del menu
                        controlador.grabaViviendas();
                        System.out.println("\nFin Sistema Constructora");
                        continue;

                    default:
                        System.out.println("\n\t\t Opcion erronea \n\n");
                }	// Fin switch
            } catch (NumberFormatException e) {
                System.out.println("\nError... El numero ingresado tiene caracteres no validos!");
            }

        } while (opcion != 3);
        
        sc.close();
    }
}
