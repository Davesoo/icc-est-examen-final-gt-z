package models;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Maquina implements Comparable<Maquina> {
    public String nombre;
    public String ip;
    public List<Integer> codigos;
    public int subred;
    public int riesgo;

    public int getSubred() {
        return subred;
    }

    public void setSubred(int subred) {
        this.subred = subred;
    }

    public int getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(int riesgo) {
        this.riesgo = riesgo;
    }

    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
        this.subred = calcularSubred();
        this.riesgo = calcularRiesgo();
    }

    private int calcularSubred() {
        String[] partes = ip.split("\\.");
        return Integer.parseInt(partes[3]);
    }

    private int calcularRiesgo() {
        int suma = codigos.stream()
                          .filter(c -> c % 5 == 0)
                          .mapToInt(Integer::intValue)
                          .sum();
                          
        Set<Character> unicos = new HashSet<>();
        for (char c : nombre.replaceAll("\\s+", "").toCharArray()) {
            unicos.add(c);
        }

        return suma * unicos.size();
    }

    @Override
    public int compareTo(Maquina o) {
        int cmp = Integer.compare(this.subred, o.subred);
        if (cmp == 0) {
            cmp = this.nombre.compareTo(o.nombre);
        }
        return cmp;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Maquina m = (Maquina) obj;
        return subred == m.subred && nombre.equals(m.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode() + subred;
    }

    @Override
    public String toString() {
        return nombre + " | IP: " + ip + " | Subred: " + subred + " | Riesgo: " + riesgo;
    }
}
