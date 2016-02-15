/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

/**
 *
 * @author Tobi
 */
public class AutoDTO {
    
    private int id;
    private String marca;
    private String modelo;    
    private int iniPerComercial;
    private int finPerComercial;
    private int cilindrada;
    private int numCilindros;
    private String combustible;
    private String potenciaKW;
    private double potenciaFiscal;
    private double potenciaCv;
    private String emisiones;
    private int valor;

    public AutoDTO() {
    }

    public AutoDTO(int id, String marca, String modelo, int iniPerComercial, int finPerComercial, int cilindrada, int numCilindros, String combustible, String potenciaKW, double potenciaFiscal, double potenciaCv, String emisiones, int valor) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.iniPerComercial = iniPerComercial;
        this.finPerComercial = finPerComercial;
        this.cilindrada = cilindrada;
        this.numCilindros = numCilindros;
        this.combustible = combustible;
        this.potenciaKW = potenciaKW;
        this.potenciaFiscal = potenciaFiscal;
        this.potenciaCv = potenciaCv;
        this.emisiones = emisiones;
        this.valor = valor;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getIniPerComercial() {
        return iniPerComercial;
    }

    public void setIniPerComercial(int iniPerComercial) {
        this.iniPerComercial = iniPerComercial;
    }

    public int getFinPerComercial() {
        return finPerComercial;
    }

    public void setFinPerComercial(int finPerComercial) {
        this.finPerComercial = finPerComercial;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getNumCilindros() {
        return numCilindros;
    }

    public void setNumCilindros(int numCilindros) {
        this.numCilindros = numCilindros;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getPotenciaKW() {
        return potenciaKW;
    }

    public void setPotenciaKW(String potenciaKW) {
        this.potenciaKW = potenciaKW;
    }

    public double getPotenciaFiscal() {
        return potenciaFiscal;
    }

    public void setPotenciaFiscal(double potenciaFiscal) {
        this.potenciaFiscal = potenciaFiscal;
    }

    public double getPotenciaCv() {
        return potenciaCv;
    }

    public void setPotenciaCv(double potenciaCv) {
        this.potenciaCv = potenciaCv;
    }

    public String getEmisiones() {
        return emisiones;
    }

    public void setEmisiones(String emisiones) {
        this.emisiones = emisiones;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    
    
}
