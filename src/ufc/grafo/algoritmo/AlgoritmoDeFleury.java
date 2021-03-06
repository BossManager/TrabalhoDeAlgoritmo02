package ufc.grafo.algoritmo;

import java.util.Iterator;
import java.util.LinkedList;
import ufc.grafo.Grafo;

/**
 * @author Daniel-BOSS
 * @author falexandremc
 * Implementação do algoritmo de Fleury
 * Está sendo usado para encontrar um caminho fechado euleriano (circuito euleriano) em grafos não direcionados
 */
public class AlgoritmoDeFleury {
    private Grafo grafo;
    /**
     * Método exibe uma mensagem de resultado depois de testar se o grafo é euleriano
     * @param grafo a ser testado
    */
    public void testeGrafo(Grafo grafo){
        this.grafo = grafo;
        int resultado = ehEuleriano();
        switch (resultado) {
            case 0:
                System.out.println("Não é um grafo euleriano");
                break;
            case 1:
                System.out.println("É um grafo semi-euleriano");
                printEulerTour();
                break;
            default:
                System.out.println("É um grafo euleriano");
                printEulerTour();
                break;
            
        }
        
    }
    /**
     * Método realiza uma busca em profundidade recursiva no grafo
     * @param visitados lista de vertices visitados
     * @param i vertice inicial a ser visitado
    */
    private void DFS(int i, boolean[] visitados) {

        visitados[i] = true;

        Iterator<Integer> car = grafo.arestas[i].iterator();

        while (car.hasNext()) {
            int n = car.next();
            if (!visitados[n])
                DFS(n, visitados);
        }
    }
    /**
     * Método verifica se o grafo é euleriano ou semi-euleriano ou nenhum dos dois
     * @return 0 se o grafo não é euleriano, 1 se o grafo é semi-euleriano e 2 se o grafo é euleriano
    */
    public int ehEuleriano() {
        if (!ehConectado())
            return 0;

        int impar = 0;

        for (LinkedList<Integer> aresta : grafo.arestas)
            if (aresta.size() % 2 == 1)
                impar++;

        if (impar > 2)
            return 0;
        
        return (impar==2)?1:2;
    }
    /**
     * Método verifica se o grafo é conexo
     * @return true caso o grafo seja conexo e caso contrario false
    */
    private boolean ehConectado() {
        boolean[] visitados = new boolean[grafo.vertice];

        int i;
        for (i = 0; i < grafo.vertice; i++)
            visitados[i] = false;

        for (i = 0; i < grafo.vertice; i++)
            if (!grafo.arestas[i].isEmpty())
                break;
        if (i == grafo.vertice)
            return true;

        DFS(i, visitados);

        for (i = 0; i < grafo.vertice; i++)
            if ((!visitados[i]) && grafo.arestas[i].size() > 0)
                return false;
        return true;
    }
    /**
     * Método escolhe uma aresta inicial e executa o método printEulerUtil que é responsavel por exibir um caminho ou circuito euleriano
    */
    public void printEulerTour() {
        int inicial = 0;
        for (int j = 0; j < grafo.vertice; j++){
            if (grafo.arestas[j].size() % 2 == 1) {
                inicial = j;
                break;
            }
        }
        printEulerUtil(inicial);
    }
    /**
     * Método verifica se o a aresta não representa uma ponte (aresta de corte) no grafo
     * @param u vertice do grafo que junto com o vertice v forma uma aresta
     * @param v vertice do grafo que junto com o vertice u forma uma aresta
     * @return true caso a aresta não represente uma ponte e caso contrario false
    */
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
        
        return sum(visitados2) < sum(visitados1)?false:true;
    }
    /**
     * Método conta a quantidade de vertices visitados
     * @param vetor lista de vertices
     * @return quantidade de vertices da lista que foram visitados
    */
    private int sum(boolean[] vetor){
        int count = 0;
        for(int i = 0; i<vetor.length; i++)
            if(vetor[i])
                count++;
        return count;
    }
    /**
     * Método exibe um caminho ou circuito euleriano
    */
    private void printEulerUtil(int inicial) {
        for (int i = 0; i < grafo.arestas[inicial].size(); i++) {
            Integer v = grafo.arestas[inicial].get(i);
            
            if (ehArestaValida(inicial,v)) {
                System.out.println(inicial + "<-->" + v);
                grafo.delAresta(inicial, v);
                printEulerUtil(v);
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
        
        Grafo g6 = new Grafo(10);
        g6.addAresta(0, 1);
        g6.addAresta(0, 3);
        g6.addAresta(0, 5);
        g6.addAresta(1, 2);
        g6.addAresta(1, 3);
        g6.addAresta(1, 4);
        g6.addAresta(2, 4);
        g6.addAresta(2, 5);
        g6.addAresta(3, 4);
        g6.addAresta(3, 6);
        g6.addAresta(3, 9);
        g6.addAresta(4, 5);
        g6.addAresta(4, 6);
        g6.addAresta(4, 7);
        g6.addAresta(4, 8);
        g6.addAresta(5, 7);
        g6.addAresta(5, 9);
        g6.addAresta(6, 7);
        g6.addAresta(6, 9);
        g6.addAresta(7, 8);
        g6.addAresta(7, 9);
        g6.addAresta(8, 9);
        euler.testeGrafo(g6);
        
        Grafo konisbergBr = new Grafo(3);
        konisbergBr.addAresta(0, 1);
        konisbergBr.addAresta(0, 1);
        konisbergBr.addAresta(0, 1);
        konisbergBr.addAresta(0, 1);
        konisbergBr.addAresta(0, 2);
        konisbergBr.addAresta(1, 2);
        konisbergBr.addAresta(1, 2);
        konisbergBr.addAresta(1, 2);
        euler.testeGrafo(konisbergBr);
        
        Grafo viagem =  new Grafo(8);
        viagem.addAresta(0, 1);
        viagem.addAresta(0, 2);
        viagem.addAresta(0, 6);
        viagem.addAresta(0, 7);
        viagem.addAresta(1, 2);
        viagem.addAresta(1, 4);
        viagem.addAresta(2, 3);
        viagem.addAresta(2, 6);
        viagem.addAresta(3, 4);
        viagem.addAresta(4, 5);
        viagem.addAresta(4, 7);
        viagem.addAresta(5, 6);
        viagem.addAresta(6, 7);
        euler.testeGrafo(viagem);
        
    }
}
