package CompuDIA.modelo;

import java.io.Serializable;

public class Computador implements Serializable {
    private String codigo;
    private String memoria;
    private String marca;

    public Computador(String codigo, String memoria, String marca) {
        super();
        this.codigo = codigo;
        this.memoria = memoria;
        this.marca = marca;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Codigo:" + codigo +
                "\nMemoria:" + memoria +
                "\nMarca:" + marca + "\n";
    }
}
