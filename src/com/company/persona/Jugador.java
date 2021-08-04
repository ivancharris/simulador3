package com.company.persona;

import java.io.Serializable;
import java.time.LocalDate;

public class Jugador implements Serializable {

    private static final long serialVersionUID = 239329L;
    private String nombre;
    private int edad;
    private LocalDate fecha_nacimiento;
    private int altura;
    private int peso;
    private String nacionalidad;
    private String club;
    private int numero_camiseta;
    private int consecutivo;

    public Jugador() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getNumero_camiseta() {
        return numero_camiseta;
    }

    public void setNumero_camiseta(int numero_camiseta) {
        this.numero_camiseta = numero_camiseta;
    }

    public int getConsecutivo() { return consecutivo; }

    public void setConsecutivo(int consecutivo) { this.consecutivo = consecutivo; }

    public Jugador(String nombre, int age, LocalDate nacimiento,
                   int height_cm, int weight_kg, String nationality, String club, int team_jersey_number, int consecutivo) {
        super();
        this.nombre = nombre;
        this.edad = age;
        this.fecha_nacimiento = nacimiento;
        this.altura = height_cm;
        this.peso = weight_kg;
        this.nacionalidad = nationality;
        this.club = club;
        this.numero_camiseta = team_jersey_number;
        this.consecutivo = consecutivo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "consecutivo='" + getConsecutivo() + '\'' +
                "Nombre='" + getNombre() + '\'' +
                ", edad=" + getEdad() + '\'' +
                ", altura='" + getAltura() + '\'' +
                ", peso='" + getPeso() + '\'' +
                ", nacionalidad=" + getNacionalidad() + '\'' +
                ", club=" + getClub() + '\'' +
                ", numero camiseta=" + getNumero_camiseta() + '\'' +
                '}';
    }


}
