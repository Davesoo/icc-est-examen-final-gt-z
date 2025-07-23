package controllers;

import java.util.*;
import models.*;

public class MaquinaController {

    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral){
        Stack<Maquina> pila = new Stack<>();
        for (Maquina m : maquinas) {
            if (m.subred < umbral) {
                pila.push(m);
            }
        }
        return pila;
    }

    public Set<Maquina> ordenarPorSubred(Stack<Maquina> pila){
        return new TreeSet<>(pila);
    }

    public Map<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas){
        Map<Integer, Queue<Maquina>> mapa = new TreeMap<>();
        for (Maquina m : maquinas) {
            mapa.putIfAbsent(m.riesgo, new LinkedList<>());
            mapa.get(m.riesgo).add(m);
        }
        return mapa;
    } 

    public Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa){
        int maxSize = -1;
        int riesgoSeleccionado = -1;

        for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
            int size = entry.getValue().size();
            int riesgo = entry.getKey();
            if (size > maxSize || (size == maxSize && riesgo > riesgoSeleccionado)) {
                maxSize = size;
                riesgoSeleccionado = riesgo;
            }
        }

        Queue<Maquina> grupo = mapa.get(riesgoSeleccionado);
        Stack<Maquina> pila = new Stack<>();
        while (!grupo.isEmpty()) {
            pila.push(grupo.poll());
        }
        return pila;
    }
}
