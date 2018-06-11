package ufc.grafo;

import java.util.LinkedList;

/**
 *
 * @author Daniel-BOSS
 */
public class Grafo {
    public LinkedList<Integer> arestas[];
    public int vertice;
    public int in[];
    public Grafo(int v) {
        vertice = v;
        arestas = new LinkedList[v];
        in = new int[v];
        for (int i = 0; i < vertice; i++){
            arestas[i] = new LinkedList();
            in[i] = 0;
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
    public void addArestaD(int u,int v){
        arestas[u].add(v);
        in[v]++;
    }

    public void delArestaD(int curr_v, int next_v) {
        arestas[curr_v].remove(new Integer(next_v));
    }
}
