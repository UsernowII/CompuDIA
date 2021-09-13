package CompuDIA.modelo;

import CompuDIA.modelo.persistencia.Archivo;
import CompuDIA.modelo.persistencia.ComputadorDAO;

import java.io.File;
import java.util.ArrayList;

public class ComputadorDTO {
    private ArrayList<Computador> computadores;
    private ComputadorDAO computadorDAO;
    private File file = new File("datos_computadores.dat");
    private Archivo archivo;

    public ComputadorDTO() {
        computadores = new ArrayList<Computador>();
        archivo = new Archivo(file);
        computadorDAO = new ComputadorDAO(archivo);
        computadores = archivo.leerArchivo(file);
    }

    public ArrayList<Computador> getComputadores() {
        return computadores;
    }

    public void setComputadores(ArrayList<Computador> computadores) {
        this.computadores = computadores;
    }

    public ComputadorDAO getComputadorDAO() {
        return computadorDAO;
    }

    public void setComputadorDAO(ComputadorDAO computadorDAO) {
        this.computadorDAO = computadorDAO;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }


}

