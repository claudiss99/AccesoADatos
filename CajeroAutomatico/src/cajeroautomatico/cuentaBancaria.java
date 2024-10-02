/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajeroautomatico;

/**
 *
 * @author Claudia
 */
public class cuentaBancaria {
    public int nCuenta;
    public String titular;
    public double saldo;

    public cuentaBancaria(int nCuenta, String titular, double saldo) {
        this.nCuenta = nCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public void setnCuenta(int nCuenta) {
        this.nCuenta = nCuenta;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getnCuenta() {
        return nCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
    
}
