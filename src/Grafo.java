
import java.util.Iterator;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Daniel-BOSS
 */
public class Grafo {

    private LinkedList<Integer> arestas[];
    private int vertice;

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

    public int ehEuleriano() {
        //0 = não é euleriano
        //1 = existe um caminho euleriano(semi Euleriano)
        //2 = existe um ciclo euleriano(Euleriano)
        if (!ehConectado()) {
            return 0;
        }

        int impar = 0;

        for (int i = 0; i < arestas.length; i++) {
            if (arestas[i].size() % 2 == 1) {
                impar++;
            }
        }

        if (impar > 2) {
            return 0;
        }
        
        return (impar==2)?1:2;
    }

    private boolean ehConectado() {
        boolean[] visitados = new boolean[this.vertice];

        int i;
        for (i = 0; i < vertice; i++) {
            visitados[i] = false;
        }

        for (i = 0; i < vertice; i++) {
            if (arestas[i].size() != 0) {
                break;
            }
        }
        if (i == vertice) {
            return true;
        }

        DFS(i, visitados);

        for (i = 0; i < vertice; i++) {
            if ((!visitados[i]) && arestas[i].size() > 0) {
                return false;
            }
        }
        return true;
    }

    private void DFS(int i, boolean[] visitados) {

        visitados[i] = true;

        Iterator<Integer> car = arestas[i].iterator();

        while (car.hasNext()) {
            int n = car.next();
            if (!visitados[n]) {
                DFS(n, visitados);
            }
        }
    }

    private void delAresta(Integer u, Integer v) {
        arestas[u].remove(v);
        arestas[v].remove(u);
    }

   

    public void encontrarCaminhoEuleriano() {
        System.out.println("Caminho euleriano");
        int inicial = 0;
        for (int j = 0; j < vertice; j++) {
            if (arestas[j].size() % 2 == 1) {
                inicial = j;
                break;
            }
        }
        encontrarCaminhoEulerianoSub(inicial);
    }
    private boolean ehArestaValida(Integer u,Integer v){
        if(arestas[u].size()==1){
            return true;
        }
        
        boolean[] visitados1 = new boolean[vertice];
        boolean[] visitados2 = new boolean[vertice];
        DFS(u,visitados1);
        
        delAresta(u, v);
        
        DFS(u,visitados2);
        
        addAresta(u, v);
        
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
        for (int i = 0; i < arestas[inicial].size(); i++) {
            Integer v = arestas[inicial].get(i);

            if (ehArestaValida(inicial,v)) {
                System.out.println(inicial + "<-->" + v);
                delAresta(inicial, v);
                encontrarCaminhoEulerianoSub(v);
            }
        }
    }

    public static void main(String[] args) {
        //grafo que tem um caminho euleriano
        Grafo g1 = new Grafo(5);
        g1.addAresta(1, 0);
        g1.addAresta(0, 2);
        g1.addAresta(2, 1);
        g1.addAresta(0, 3);
        g1.addAresta(3, 4);
        g1.encontrarCaminhoEuleriano();
        // 1 --- 0 --- 3
        // |    /      |
        // |  /        |
        // 2           4
        //Grafo tem um ciclo euleriano
        Grafo g2 = new Grafo(5);
        g2.addAresta(1, 0);
        g2.addAresta(0, 2);
        g2.addAresta(2, 1);
        g2.addAresta(0, 3);
        g2.addAresta(3, 4);
        g2.addAresta(4, 0);
        g2.encontrarCaminhoEuleriano();
        //1---0---3
        //|  / \  |
        //| /   \ |
        // 2     4
        Grafo g3 = new Grafo(5);
        g3.addAresta(1, 0);
        g3.addAresta(0, 2);
        g3.addAresta(2, 1);
        g3.addAresta(0, 3);
        g3.addAresta(3, 4);
        g3.addAresta(1, 3);
        g3.ehEuleriano();
        
        Grafo g4 = new Grafo(3);
        g4.addAresta(0, 1);
        g4.addAresta(1, 2);
        g4.addAresta(2, 0);
        g4.ehEuleriano();

        Grafo g5 = new Grafo(6);
        g5.addAresta(0, 1);
        g5.addAresta(0, 1);
        g5.addAresta(0, 1);
        g5.addAresta(0, 2);
        g5.addAresta(1, 3);
        g5.addAresta(2, 3);
        g5.addAresta(4, 5);
        g5.addAresta(4, 5);
        g5.addAresta(4, 5);
        g5.addAresta(2, 4);
        g5.addAresta(3, 5);
        g5.encontrarCaminhoEuleriano();
        
    }
}
