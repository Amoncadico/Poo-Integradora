package persistencia;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelo.Vivienda;

/**
 * @author Maria Angelica Caro
 */

public class IOConstructora {
	// Atributos
    private static IOConstructora instance = null;

    // Constructor
    private IOConstructora() {}

    // Operaciones
    public static IOConstructora getInstance() {
        if (instance == null) {
            instance = new IOConstructora();
        }
        return instance;
    }

    public Vivienda[] readViviendas() throws FileNotFoundException, IOException, ClassNotFoundException {
    	ObjectInputStream archivo = null;
    	ArrayList<Vivienda> viviendas = new ArrayList<>();

    	archivo = new ObjectInputStream(new FileInputStream("Viviendas.obj"));
    	try {
    		while (true) {
    			viviendas.add((Vivienda) archivo.readObject());
    		}
    	}
    	catch (EOFException e) {
    		archivo.close();
    		return viviendas.toArray(new Vivienda[0]);
    	}
    }

    public void saveConstructora(Vivienda[] vivienda) throws FileNotFoundException, IOException {
        ObjectOutputStream archivo = null;

        archivo = new ObjectOutputStream(new FileOutputStream("Viviendas.obj"));
        for (int i = 0; i < vivienda.length; i++) {
        	archivo.writeObject(vivienda[i]);
        }
        archivo.close();
    }

}
