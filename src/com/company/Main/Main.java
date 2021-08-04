package com.company.Main;

import com.company.UI.UI;
import com.company.gestor_archivos.Archivos;
import com.company.persona.Jugador;

import java.util.ArrayList;
import java.io .*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Archivos unArchivo = new Archivos();
        UI menu = new UI();
        leerDatos();
        boolean encontrado = false;
        Integer contador = 0;
        Integer opcion = null;
        ArrayList<Jugador> playerDT = leerDatos();

        Archivos datosGuardados = new Archivos();
        ArrayList<Jugador> player = datosGuardados.leerDatosDat();


        HashMap edad = datosGuardados.leerDatosInx("edad");
        HashMap pais = datosGuardados.leerDatosInx("pais");
        HashMap peso = datosGuardados.leerDatosInx("peso");
        HashMap estatura = datosGuardados.leerDatosInx("estatura");


        do {
            menu.menu();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre a consultar: ");
                    String nombre = scanner.next();

                    for(int i=0; i < player.size();i++){
                        if (player.get(1).getNombre().contains(nombre)){
                            System.out.println(player.get(i).getNombre());
                        }
                    }
                    hacerBusqueda(player, s -> s.getNombre().toUpperCase().contains(nombre));
                    break;
                case 2:
                    System.out.println("Ingrese pais a consultar: ");
                    String nacionalidadpesoConsultada= scanner.next();
                    hacerBusqueda(player,  s -> s.getNacionalidad().toUpperCase().contains(nacionalidadpesoConsultada));
                    hacerBusquedaPorIndex(pais, nacionalidadpesoConsultada, player);
                    break;
                case 3:
                    System.out.println("Ingrese numero de camisa a consultar: ");
                    Integer camiseta= scanner.nextInt();
                    hacerBusqueda(player, s -> s.getNumero_camiseta() == camiseta);
                    break;
                case 4:
                    System.out.println("Ingrese el peso a consultar: : ");
                    Integer pesoConsultado= scanner.nextInt();
                    hacerBusqueda(player,  s -> s.getPeso() == pesoConsultado);
                    hacerBusquedaPorIndex(peso, pesoConsultado, player);
                    break;
                case 5:
                    System.out.println("Ingrese la estatura  a consultar: : ");
                    Integer alturaConsultada= scanner.nextInt();
                    hacerBusqueda(player,  s -> s.getAltura() == alturaConsultada);
                    hacerBusquedaPorIndex(estatura, alturaConsultada, player);
                    break;
                case 6:
                    System.out.println("Ingrese la edad  a consultar: : ");
                    Integer edad1= scanner.nextInt();
                    hacerBusqueda(player,  s -> s.getEdad() == edad1);
                    hacerBusquedaPorIndex(edad, edad1, player);
                    break;
                case 7:
                    System.out.println("Consulta terminada");
                    break;
                default:
                    System.out.println("Ingrese una opción correcta");
                    break;
            }
        } while (opcion != 7);
    }

    public static ArrayList<Jugador> leerDatos() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File("C:\\Users\\ivanc\\Desktop\\Simulacion2.SistemasOperativos\\src\\com\\company\\archivo.dat"));
        ObjectInputStream oi = new ObjectInputStream(fi);

        ArrayList<Jugador> personas = new ArrayList<Jugador>();
        try {
            Jugador obj = null;
            while ((obj = (Jugador) oi.readObject()) != null) {
                personas.add(obj);
            }
        } catch (Exception e) {
            oi.close();
            fi.close();
        }
        oi.close();
        fi.close();

        return personas;
    }

    public static void guardarDatos(ArrayList<Jugador> personas) throws IOException {
        FileOutputStream f = new FileOutputStream(new File("C:\\Users\\ivanc\\Desktop\\Simulacion2.SistemasOperativos\\src\\com\\company\\archivo.dat"));
        ObjectOutputStream o = new ObjectOutputStream(f);

        personas.forEach(x -> {
            try {
                o.writeObject(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        o.close();
        f.close();
    }

    public static void hacerBusqueda(ArrayList<Jugador> personas, Predicate<Jugador> predicate) {
        long start = System.currentTimeMillis();

        List<Jugador> personasFiltradoDorsal = personas.stream()
                .filter(predicate)
                .collect(Collectors.toList());

        personasFiltradoDorsal.forEach(x -> System.out.println(x.toString()));

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("TECNICA SIN INDICES: Tiempo de ejecucion: " + timeElapsed + " mili segundos");
    }


    public static void hacerBusquedaPorIndex(HashMap hashMap, Object term, ArrayList<Jugador> personas) {
        long start = System.currentTimeMillis();

        ArrayList<Integer> result = (ArrayList) hashMap.get(term);

        if(result==null){
            System.out.println("Valor no encontrado");
        } else {
            for (Integer consecutivo : result) {
                Jugador persona = personas.get(consecutivo - 2);
                System.out.println(persona);
            }
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("TECNICA CON INDICES : Tiempo de ejecucion: " + timeElapsed + " mili segundos");
    }


}
