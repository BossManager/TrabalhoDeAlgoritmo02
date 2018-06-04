/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.grafo.algoritmo;

import java.util.Iterator;
import ufc.grafo.Grafo;

/**
 *
 * @author Daniel-BOSS
 */
public class GrafoEuleriano {
    private Grafo grafo;
    public void testeGrafo(Grafo grafo){
        this.grafo = grafo;
        encontrarCaminhoEuleriano();
    }
    private void DFS(int i, boolean[] visitados) {

        visitados[i] = true;

        Iterator<Integer> car = grafo.arestas[i].iterator();

        while (car.hasNext()) {
            int n = car.next();
            if (!visitados[n]) {
                DFS(n, visitados);
            }
        }
    }
    public int ehEuleriano() {
        //0 = não é euleriano
        //1 = existe um caminho euleriano(semi Euleriano)
        //2 = existe um ciclo euleriano(Euleriano)
        if (!ehConectado()) {
            return 0;
        }

        int impar = 0;

        for (int i = 0; i < grafo.arestas.length; i++) {
            if (grafo.arestas[i].size() % 2 == 1) {
                impar++;
            }
        }

        if (impar > 2) {
            return 0;
        }
        
        return (impar==2)?1:2;
    }

    private boolean ehConectado() {
        boolean[] visitados = new boolean[grafo.vertice];

        int i;
        for (i = 0; i < grafo.vertice; i++) {
            visitados[i] = false;
        }

        for (i = 0; i < grafo.vertice; i++) {
            if (grafo.arestas[i].size() != 0) {
                break;
            }
        }
        if (i == grafo.vertice) {
            return true;
        }

        DFS(i, visitados);

        for (i = 0; i < grafo.vertice; i++) {
            if ((!visitados[i]) && grafo.arestas[i].size() > 0) {
                return false;
            }
        }
        return true;
    }
    public void encontrarCaminhoEuleriano() {
        System.out.println("Caminho euleriano");
        int inicial = 0;
        for (int j = 0; j < grafo.vertice; j++) {
            if (grafo.arestas[j].size() % 2 == 1) {
                inicial = j;
                break;
            }
        }
        encontrarCaminhoEulerianoSub(inicial);
    }
    private boolean ehArestaValida(Integer u,Integer v){
        if(grafo.arestas[u].size()==1){
            return true;
        }
        
        boolean[] visitados1 = new boolean[grafo.vertice];
        boolean[] visitados2 = new boolean[grafo.vertice];
        DFS(u,visitados1);
        
        grafo.delAresta(u, v);
        
        DFS(u,visitados2);
        
        grafo.addAresta(u, v);
        
        return (sum(visitados1)>sum(visitados2))?false:true;
    }
    private int sum(boolean[] vetor){
        int count = 0;
        for(int i = 0; i<vetor.length; i++){
            if(vetor[i])
                count++;
        }
        return count;
    }
    private void encontrarCaminhoEulerianoSub(int inicial) {
        for (int i = 0; i < grafo.arestas[inicial].size(); i++) {
            Integer v = grafo.arestas[inicial].get(i);

            if (ehArestaValida(inicial,v)) {
                System.out.println(inicial + "<-->" + v);
                grafo.delAresta(inicial, v);
                encontrarCaminhoEulerianoSub(v);
            }
        }
    }
    public static void main(String[] args) {
        GrafoEuleriano euler = new GrafoEuleriano();
        Grafo g1 = new Grafo(5);
        g1.addAresta(1, 0);
        g1.addAresta(0, 2);
        g1.addAresta(2, 1);
        g1.addAresta(0, 3);
        g1.addAresta(3, 4);
        euler.testeGrafo(g1);
        
    }
}
