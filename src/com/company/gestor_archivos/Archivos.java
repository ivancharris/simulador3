package com.company.gestor_archivos;

import com.company.persona.Jugador;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Archivos {

    private HashMap<Integer, ArrayList<Integer>> _hashMapTableEdad = new HashMap<>();
    private HashMap<Integer, ArrayList<Integer>> _hashMapTableEstatura = new HashMap<>();
    private HashMap<Integer, ArrayList<Integer>> _hashMapTablePeso = new HashMap<>();
    private HashMap<String, ArrayList<Integer>> _hashMapTablePais = new HashMap<>();

    public Jugador dataPersona(String currentData, int i) throws IOException {
        Jugador newPersona = new Jugador();
        String[] data = currentData.split(",");
        newPersona.setConsecutivo(i);
        newPersona.setNombre(data[0]);
        newPersona.setEdad(Integer.parseInt(data[1]));
        newPersona.setAltura(Integer.parseInt(data[3]));
        newPersona.setPeso(Integer.parseInt(data[4]));
        newPersona.setNacionalidad(data[5]);
        newPersona.setClub(data[6]);

        if (data.length > 7){
            newPersona.setNumero_camiseta(Integer.parseInt(data[7]));
        }

        return newPersona;
    }



    private void guardarIndices(HashMap indices, String name) throws IOException {
        FileOutputStream f = new FileOutputStream(new File("C:/Users/ivanc/Desktop/Simulacion2.SistemasOperativos/src/com/company/" + name + ".idx"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(indices);
        o.close();
        f.close();
    }

    public ArrayList<Jugador> cargarDatos(Scanner reader) throws IOException {

        Scanner fileReader = new Scanner(new File("C:\\Users\\ivanc\\Desktop\\Simulacion2.SistemasOperativos\\src\\com\\company\\players_15.csv"));

        ArrayList<Jugador> result = new ArrayList<Jugador>();
        boolean skip = true;
        int i = 0;
        while (reader.hasNextLine()) {
            i++;
            String persona = reader.nextLine();
            if (!skip) {
                Jugador currentPersona = dataPersona(persona.toUpperCase(), i);
                this.getPersonDataFromStringInHashTable(persona, i);
                result.add(currentPersona);
            } else {
                skip = false;
            }
        }
        this.guardarIndices(this._hashMapTableEstatura, "estatura");
        this.guardarIndices(this._hashMapTableEdad, "edad");
        this.guardarIndices(this._hashMapTablePeso, "peso");
        this.guardarIndices(this._hashMapTablePais, "pais");
        fileReader.close();
        return result;
    }


    public ArrayList<Jugador> leerDatosDat() throws IOException {
        FileInputStream fi = new FileInputStream(new File("C:/Users/ivanc/Desktop/Simulacion2.SistemasOperativos/src/com/company/archivo.dat"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        ArrayList<Jugador> player = new ArrayList<Jugador>();
        try {
            Jugador obj = null;
            while ((obj = (Jugador) oi.readObject()) != null) {
                player.add(obj);
            }
        }catch (Exception e){
            oi.close();
            fi.close();
        }
        oi.close();
        fi.close();
        return player;
    }

    public HashMap leerDatosInx(String name) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File("C:/Users/ivanc/Desktop/Simulacion2.SistemasOperativos/src/com/company/" + name + ".idx"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        HashMap hashMap = new HashMap();

        try {
            hashMap = (HashMap)oi.readObject();
        } catch (Exception var6) {
            oi.close();
            fi.close();
        }

        oi.close();
        fi.close();
        return hashMap;
    }

    private void getPersonDataFromStringInHashTable(String currentData, int i) {
        String[] data = currentData.split(",");
        ArrayList<Integer> arrayList;
        if (!this._hashMapTableEdad.containsKey(Integer.parseInt(data[1]))) {
            arrayList = new ArrayList<>();
            arrayList.add(i);
            this._hashMapTableEdad.put(Integer.parseInt(data[1]), arrayList);
        } else {
            ((ArrayList)this._hashMapTableEdad.get(Integer.parseInt(data[1]))).add(i);
        }

        if (!this._hashMapTableEstatura.containsKey(Integer.parseInt(data[3]))) {
            arrayList = new ArrayList<>();
            arrayList.add(i);
            this._hashMapTableEstatura.put(Integer.parseInt(data[3]), arrayList);
        } else {
            ((ArrayList)this._hashMapTableEstatura.get(Integer.parseInt(data[3]))).add(i);
        }

        if (!this._hashMapTablePeso.containsKey(Integer.parseInt(data[4]))) {
            arrayList = new ArrayList<>();
            arrayList.add(i);
            this._hashMapTablePeso.put(Integer.parseInt(data[4]), arrayList);
        } else {
            ((ArrayList)this._hashMapTablePeso.get(Integer.parseInt(data[4]))).add(i);
        }

        if (!this._hashMapTablePais.containsKey(data[5])) {
            arrayList = new ArrayList<Integer>();
            arrayList.add(i);
            this._hashMapTablePais.put(data[5], arrayList);
        } else {
            ((ArrayList)this._hashMapTablePais.get(data[5])).add(i);
        }

    }
}