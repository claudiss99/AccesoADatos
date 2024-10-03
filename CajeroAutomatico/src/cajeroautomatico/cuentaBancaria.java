/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajeroautomatico;

/**
 *
 * @author Claudia
 */
public class CuentaBancaria {
    public int nCuenta;
    public String titular;
    public double saldo;

    public CuentaBancaria(String titular, String juan_Pérez, double saldo) {
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
    
    // Métodos para las operaciones bancarias

    // Consultar saldo
    public double consultarSaldo() {
        return saldo;
    }

    // Depositar dinero
    public void depositar(double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("No se puede depositar una cantidad negativa o cero.");
        }
        saldo += cantidad;
        System.out.println("Depósito exitoso. Saldo actual: " + saldo);
    }

    // Retirar dinero
    public void retirar(double cantidad) throws SaldoInsuficienteException {
        if (cantidad > saldo) {
            throw new SaldoInsuficienteException();
        }
        saldo -= cantidad;
        System.out.println("Retiro exitoso. Saldo actual: " + saldo);
    }
    
}
