package ufc.grafo.algoritmo;

import java.util.Stack;
import java.util.Vector;
import ufc.grafo.Grafo;

/**
 * @author Daniel-BOSS
 * @author falexandremc
 * Implementação do algoritmo de Hierholzer
 * Está sendo usado para encontrar um caminho fechado euleriano (circuito euleriano) em grafos direcionados
 */
public class AlgoritmoDeHierholzer {
    private Grafo g;
    
    /*Método preenche a variavel g com um grafo e inicia o metodo para encontrar um caminho no grafo
    
    */
    public void testeGrafo(Grafo g){
        this.g = g;
        
        encontrarCaminhoEuleriano();
        
    }
    /*Método começa de um vertice qualquer, percorre arestas até retornar ao vertice inicial.
    * enquanto houver um vertice que possuir arestas não exploradas inicie um caminho e tente voltar a ele
    *
    */
    public void encontrarCaminhoEuleriano(){
        if(g.arestas.length==0)
            return;
        Stack<Integer> caminho_atual = new Stack<>();
        Vector<Integer> circuito =  new Vector<>();
        caminho_atual.push(0);
        int vertice_atual=0;
        //Procura um vertice com arestas
        for(int i = 0;i<g.vertice;i++){
            if(g.arestas[i].size()!=0){
                vertice_atual = i;
                break;
            }
        }
        while(!caminho_atual.empty()){
            if(g.arestas[vertice_atual].size()>0){
                caminho_atual.push(vertice_atual);
                int proximo_vertice = g.arestas[vertice_atual].getLast();
                g.delArestaD(vertice_atual,proximo_vertice);
                vertice_atual = proximo_vertice;
            }else{
                circuito.addElement(vertice_atual);
                vertice_atual = caminho_atual.peek();
                caminho_atual.pop();
            }
        }
        for(int i = circuito.size()-1 ; i>=0 ; i--){
            System.out.print(circuito.get(i));
            if(i>0)
                System.out.print("-->");
            
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        AlgoritmoDeHierholzer euler = new AlgoritmoDeHierholzer();
        Grafo g1 = new Grafo(3);
        g1.addArestaD(0, 1);
        g1.addArestaD(1, 2);
        g1.addArestaD(2, 0);
        
        euler.testeGrafo(g1);
        
        Grafo g2 = new Grafo(7);
        g2.addArestaD(0, 1);
        g2.addArestaD(0, 6);
        g2.addArestaD(1, 2);
        g2.addArestaD(2, 0);
        g2.addArestaD(2, 3);
        g2.addArestaD(3, 4);
        g2.addArestaD(4, 2);
        g2.addArestaD(4, 5);
        g2.addArestaD(5, 0);
        g2.addArestaD(6, 4);
        
        euler.testeGrafo(g2);
        
        Grafo g3 = new Grafo(4);
        g3.addArestaD(0, 1);
        g3.addArestaD(1, 2);
        g3.addArestaD(2, 3);
        g3.addArestaD(3, 1);
        
        euler.testeGrafo(g3);
        
        Grafo g4 =  new Grafo(3);
        g4.addArestaD(1, 2);
        
        
        euler.testeGrafo(g4);
    }
}
