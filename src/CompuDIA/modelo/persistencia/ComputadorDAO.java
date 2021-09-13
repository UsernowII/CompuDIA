package CompuDIA.modelo.persistencia;

import CompuDIA.modelo.Computador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ComputadorDAO {

    Conector con = new Conector();
    private Archivo archivo;
    private File file;

    public ComputadorDAO(Archivo arch) {
        archivo = arch;
    }

    public String verComputadores(File file) {
        ArrayList<Computador> computadores = archivo.leerArchivo(file);
        String texto = "";
        for (int i = 0; i < computadores.size(); i++) {
            texto = texto.concat(computadores.get(i) + "\n");
        }
        return texto;
    }

    public Computador buscarComputador(String codigo, ArrayList<Computador> computadores) {
        Computador encontrado = null;
        if (!computadores.isEmpty()) {
            for (int i = 0; i < computadores.size(); i++) {
                if (computadores.get(i).getCodigo().equals(codigo)) {
                    encontrado = computadores.get(i);
                }
            }
        }
        return encontrado;
    }

    public boolean agregarComputador(String codigo, String memoria, String marca, ArrayList<Computador> computadores, File file) {
        Computador nuevo = new Computador(codigo, memoria, marca);
        if (buscarComputador(codigo, computadores) == null) {
            computadores.add(nuevo);
            archivo.escribirEnArchivo(computadores, file);
            con.connect();
            con.saveComputador(nuevo);
            return true;
        } else {
            return false;
        }
    }


    public boolean eliminarComputador(String codigo, ArrayList<Computador> computadores, File file) {
        boolean resp = false;
        try {
            Computador e = buscarComputador(codigo, computadores);
            if (e != null) {
                computadores.remove(e);
                file.delete();
                file.createNewFile();
                archivo.escribirEnArchivo(computadores, file);
                con.connect();
                con.delComputador(e);
                resp = true;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return resp;
    }

}
