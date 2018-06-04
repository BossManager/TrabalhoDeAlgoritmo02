/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.grafo;

import java.util.LinkedList;

/**
 *
 * @author Daniel-BOSS
 */
public class Grafo {
    public LinkedList<Integer> arestas[];
    public int vertice;

    public Grafo(int v) {
        vertice = v;
        arestas = new LinkedList[v];

        for (int i = 0; i < vertice; i++) {
            arestas[i] = new LinkedList();
        }

    }

    public void addAresta(int u, int v) {
        arestas[u].add(v);
        arestas[v].add(u);
    }
    public void delAresta(Integer u, Integer v) {
        arestas[u].remove(v);
        arestas[v].remove(u);
    }
}
