package CompuDIA.Controlador;

import CompuDIA.modelo.Computador;
import CompuDIA.modelo.ComputadorDTO;
import CompuDIA.modelo.persistencia.Conector;
import CompuDIA.vista.VistaConsola;
import CompuDIA.vista.VistaGUI;

import java.awt.*;


public class Controlador {

    private VistaConsola vista;
    private ComputadorDTO computo;
    private Conector con = new Conector();
    private Frame v;

    public Controlador() {
        v = new VistaGUI("");
        computo = new ComputadorDTO();
        vista = new VistaConsola();
        funcionar();
    }

    public void funcionar() {
        int opcion = 0;

        String menu = "COMPUDIA" + "\n" +
                "Seleccione la tarea a realizar\n" +
                "1. Ingresar Computador\n" +
                "2. Buscar Computador\n" +
                "3. Modificar Computador\n" +
                "4. Eliminar Computador\n" +
                "5. Ver directorio de Computadores\n" +
                "6. Salir\n" +
                "Opción:\n";

        while (opcion != 6) {

            opcion = vista.leerDatoEntero(menu);


            switch (opcion) {
                case 1:
                    ingresarComputador();
                    break;
                case 2:
                    buscarComputador();
                    break;
                case 3:
                    modificarComputador();
                    break;
                case 4:
                    eliminarComputador();
                    break;
                case 5:
                    mostrarDirectorio();
                    break;
                case 6:
                    con.connect();
                    con.close();
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.println("Opción no valida");
            }

        }


    }

    public void ingresarComputador() {
        String c = "", m = "", mr = "";
        vista.imprimir("Ingresar Computador");
        c = vista.leerDatoString("Ingresar el Cógigo:");
        m = vista.leerDatoString("Ingrese la memoria:");
        mr = vista.leerDatoString("ingresar marca:");
        if (computo.getComputadorDAO().agregarComputador(c, m, mr, computo.getComputadores(), computo.getFile())) {
            vista.imprimir("Se agregó el computador\n");
        } else {
            vista.imprimir("No se agrego el computador\n");
        }
    }

    public void buscarComputador() {
        String c = "";
        vista.imprimir("\nBuscar Computador");
        c = vista.leerDatoString("Ingrese el codigo del computador a buscar:");
        Computador rta = computo.getComputadorDAO().buscarComputador(c, computo.getComputadores());
        if (rta != null) {
            vista.imprimir(rta.toString());
        } else {
            vista.imprimir("El Computador no se encuentra registrado\n");
        }
    }

    public void modificarComputador() {
        String c = "", m = "", mr = "";
        vista.imprimir("\n Modificar Computador");
        c = vista.leerDatoString("Ingrese codigo del registro a modificar");
        Computador rta = computo.getComputadorDAO().buscarComputador(c, computo.getComputadores());

        if (rta != null) {
            //c=vista.leerDatoString("Ingresar código");
            m = vista.leerDatoString("ingresar memoria");
            mr = vista.leerDatoString("Ingresar marca");
            computo.getComputadorDAO().eliminarComputador(c, computo.getComputadores(), computo.getFile());
            computo.getComputadorDAO().agregarComputador(c, m, mr, computo.getComputadores(), computo.getFile());
            vista.imprimir("Se modico el computador\n");
        } else {
            vista.imprimir("El computador no se encuentra registrado");
        }
    }

    public void eliminarComputador() {
        String c = "";
        vista.imprimir("\nEliminar Computador");
        c = vista.leerDatoString("Digite el codigo a eliminar:");
        if (computo.getComputadorDAO().eliminarComputador(c, computo.getComputadores(), computo.getFile())) {
            vista.imprimir("Se elimino el computador");
        } else {
            vista.imprimir("El computador no se encuentra registrado");
        }
    }


    public void mostrarDirectorio() {
        vista.imprimir("\n Directorio de Computadores");
        //vista.imprimir(computo.getComputadorDAO().verComputadores(computo.getFile()));
        con.connect();
        con.mostrarComputador();

    }

}
