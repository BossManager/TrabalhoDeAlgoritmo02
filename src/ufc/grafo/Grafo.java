package ufc.grafo;

import java.util.LinkedList;

/**
 * @author Daniel-BOSS
 * @author falexandremc
 */
public class Grafo {
    public LinkedList<Integer> arestas[];
    public int vertice;
    public int in[];
    
    /**
     * @param v quantidade de vertices do grafo
     */
    public Grafo(int v) {
        vertice = v;
        arestas = new LinkedList[v];
        in = new int[v];
        for (int i = 0; i < vertice; i++){
            arestas[i] = new LinkedList();
            in[i] = 0;
        }

    }
    /**
     * Método inclui uma aresta não direcionada no grafo
     * @param u vertice do grafo que junto com o vertice v forma uma aresta
     * @param v vertice do grafo que junto com o vertice u forma uma aresta
    */
    public void addAresta(int u, int v) {
        arestas[u].add(v);
        arestas[v].add(u);
    }
    /**
     * Método exclui uma aresta não direcionada que pertence ao grafo
     * @param u vertice do grafo que junto com o vertice v forma uma aresta
     * @param v vertice do grafo que junto com o vertice u forma uma aresta
    */
    public void delAresta(Integer u, Integer v) {
        arestas[u].remove(v);
        arestas[v].remove(u);
    }
    /**
     * Método inclui uma aresta direcionada no grafo
     * @param u vertice do grafo que junto com o vertice v forma uma aresta
     * @param v vertice do grafo que junto com o vertice u forma uma aresta
    */
    public void addArestaD(int u,int v){
        arestas[u].add(v);
        in[v]++;
    }
    /**
     * Método exclui uma aresta direcionada que pertence ao grafo
     * @param curr_v vertice do grafo que junto com o vertice next_v forma uma aresta direcionada
     * @param next_v vertice do grafo que junto com o vertice curr_v forma uma aresta direcionada
    */
    public void delArestaD(int curr_v, int next_v) {
        arestas[curr_v].remove(new Integer(next_v));
    }
}
