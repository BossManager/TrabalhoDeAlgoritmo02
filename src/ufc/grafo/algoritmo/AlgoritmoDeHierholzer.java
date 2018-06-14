package ufc.grafo.algoritmo;

import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import ufc.grafo.Grafo;

/**
 *
 * @author danen
 */
public class AlgoritmoDeHierholzer {
    private Grafo g;
    
    /*Método preenche a variavel g com um grafo e inicia o metodo para encontrar um caminho no grafo
    
    */
    public void testeGrafo(Grafo g){
        this.g = g;
        
        encontrarCaminhoEuleriano();
        
    }
    /*
    Método que escolhe o vertice para iniciar o caminho
    */
    private int escolheVertice(){
        int []contador_arestaE = new int[g.vertice];
        for(int i=0;i<g.vertice;i++){
            for(Integer o :g.arestas[i]){
                contador_arestaE[o]++;
            }
        }
        int num1,num2,grau;
        num1=num2=0;
        
        for(int i=0;i<g.vertice;i++){
            grau = (g.arestas[i].size()>contador_arestaE[i])?g.arestas[i].size()-contador_arestaE[i]:contador_arestaE[i]-g.arestas[i].size();
            if(num1==0 && grau==1)
                num1 = i;
            else if(grau==1)
                num2 = i;
        }
        return (g.arestas[num1].size()>g.arestas[num2].size())?num1:num2;
    }
    /*Método começa de um vertice qualquer, percorre arestas até retornar ao vertice inicial.
    * enquanto houver um vertice que possuir arestas não exploradas inicie um caminho e tente voltar a ele
    *
    */
    public void encontrarCaminhoEuleriano(){
        
        int []contador_arestas = new int[g.vertice];
        for(int i =0 ;i<g.arestas.length;i++)
            contador_arestas[i] = g.arestas[i].size();
        if(g.arestas.length==0)
            return;
        Stack<Integer> caminho_atual = new Stack<>();
        Vector<Integer> circuito =  new Vector<>();
        caminho_atual.push(0);
        int atual_v=0;
        atual_v = escolheVertice();
        
        while(!caminho_atual.empty()){
            if(contador_arestas[atual_v]>0){
                caminho_atual.push(atual_v);
                int proxima_v = g.arestas[atual_v].getLast();
                contador_arestas[atual_v]--;
                g.delArestaD(atual_v,proxima_v);
                atual_v = proxima_v;
            }else{
                circuito.addElement(atual_v);
                atual_v = caminho_atual.peek();
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
        g2.addArestaD(2, 3);
        g2.addArestaD(2, 0);
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
        
        Grafo g5 =  new Grafo(5);
        g5.addArestaD(0, 2);
        g5.addArestaD(1, 0);
        g5.addArestaD(1, 4);
        g5.addArestaD(2, 1);
        g5.addArestaD(2, 3);
        g5.addArestaD(3, 1);
        g5.addArestaD(3, 4);
        g5.addArestaD(4, 2);
        euler.testeGrafo(g5);
    }
}
