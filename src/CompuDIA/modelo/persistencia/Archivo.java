package CompuDIA.modelo.persistencia;

import CompuDIA.modelo.Computador;

import java.io.*;
import java.util.ArrayList;

public class Archivo {

    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public Archivo(File archivo) {
        if (archivo.exists()) {
        } else {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void escribirEnArchivo(ArrayList<Computador> computadores, File archivo) {

        try {
            salida = new ObjectOutputStream(new FileOutputStream(archivo));
            salida.writeObject(computadores);
            salida.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Computador> leerArchivo(File archivo) {

        ArrayList<Computador> computadores = new ArrayList<Computador>();
        if (archivo.length() != 0) {
            try {
                entrada = new ObjectInputStream(new FileInputStream(archivo));
                computadores = (ArrayList<Computador>) entrada.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return computadores;
    }

    public ObjectInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(ObjectInputStream entrada) {
        this.entrada = entrada;
    }

    public ObjectOutputStream getSalida() {
        return salida;
    }

    public void setSalida(ObjectOutputStream salida) {
        this.salida = salida;
    }


}