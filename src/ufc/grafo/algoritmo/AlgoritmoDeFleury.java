package ufc.grafo.algoritmo;

import java.util.Iterator;
import ufc.grafo.Grafo;

/**
 *
 * @author Daniel-BOSS
 */
public class AlgoritmoDeFleury {
    private Grafo grafo;
    public void testeGrafo(Grafo grafo){
        this.grafo = grafo;
        int resultado = ehEuleriano();
        switch (resultado) {
            case 0:
                System.out.println("Não é um grafo euleriano");
                break;
            case 1:
                System.out.println("É um grafo semi-euleriano");
                encontrarCaminhoEuleriano();
                break;
            default:
                System.out.println("É um grafo euleriano");
                encontrarCaminhoEuleriano();
                break;
            
        }
        
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
        if (!ehConectado())
            return 0;

        int impar = 0;

        for (int i = 0; i < grafo.arestas.length; i++)
            if (grafo.arestas[i].size() % 2 == 1)
                impar++;

        if (impar > 2)
            return 0;
        
        return (impar==2)?1:2;
    }

    private boolean ehConectado() {
        boolean[] visitados = new boolean[grafo.vertice];

        int i;
        for (i = 0; i < grafo.vertice; i++)
            visitados[i] = false;

        for (i = 0; i < grafo.vertice; i++)
            if (grafo.arestas[i].size() != 0)
                break;
        if (i == grafo.vertice)
            return true;

        DFS(i, visitados);

        for (i = 0; i < grafo.vertice; i++)
            if ((!visitados[i]) && grafo.arestas[i].size() > 0)
                return false;
        return true;
    }
    public void encontrarCaminhoEuleriano() {
        int inicial = 0;
        for (int j = 0; j < grafo.vertice; j++)
            if (grafo.arestas[j].size() % 2 == 1) {
                inicial = j;
                break;
            }
        encontrarCaminhoEulerianoSub(inicial);
    }
    private boolean ehArestaValida(Integer u,Integer v){
        if(grafo.arestas[u].size()==1)
            return true;
        
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
        AlgoritmoDeFleury euler = new AlgoritmoDeFleury();
        Grafo g1 = new Grafo(5);
        g1.addAresta(1, 0);
        g1.addAresta(0, 2);
        g1.addAresta(2, 1);
        g1.addAresta(0, 3);
        g1.addAresta(3, 4);
        euler.testeGrafo(g1);
        
        Grafo g2 = new Grafo(5);
        g2.addAresta(1, 0);
        g2.addAresta(0, 2);
        g2.addAresta(2, 1);
        g2.addAresta(0, 3);
        g2.addAresta(3, 4);
        g2.addAresta(4, 0);
        euler.testeGrafo(g2);
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
        euler.testeGrafo(g3);
        //_ _ _ _ _
        //|       |
        //1---0---3
        //|  /    |
        //| /     |
        // 2     4
        Grafo g4 = new Grafo(3);
        g4.addAresta(0, 1);
        g4.addAresta(1, 2);
        g4.addAresta(2, 0);
        euler.testeGrafo(g4);

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
        euler.testeGrafo(g5);
    }
}
