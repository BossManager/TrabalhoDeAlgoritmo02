/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.grafo.algoritmo;

import java.util.Stack;
import java.util.Vector;
import ufc.grafo.Grafo;

/**
 *
 * @author danen
 */
public class AlgoritmoDeHierholzer {
    private Grafo g;
    public void testeGrafo(Grafo g){
        this.g = g;
        encontrarCaminhoEuleriano();
    }
    public void encontrarCaminhoEuleriano(){
        if(g.arestas.length==0){
            return;
        }
        Stack<Integer> caminho_atual = new Stack<>();
        Vector<Integer> circuito =  new Vector<>();
        caminho_atual.push(0);
        int curr_v = 0;
        while(!caminho_atual.empty()){
            if(g.arestas[curr_v].size()>0){
                caminho_atual.push(curr_v);
                int next_v = g.arestas[curr_v].getLast();
                g.delArestaD(curr_v,next_v);
                curr_v = next_v;
            }else{
                circuito.addElement(curr_v);
                curr_v = caminho_atual.peek();
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
        
    }
}