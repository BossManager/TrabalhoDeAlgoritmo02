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
    public void testeGrafo(Grafo g){
        this.g = g;
        if(isEulerianCycle())
            encontrarCaminhoEuleriano();
        else
            System.out.println("Não é euleriano");
    }
    private Grafo getTranspose()
    {
        Grafo t = new Grafo(g.vertice);
        for (int v = 0; v < g.vertice; v++)
        {
            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i = g.arestas[v].listIterator();
            while (i.hasNext())
            {
                t.arestas[i.next()].add(v);
                (t.in[v])++;
            }
        }
        return t;
    }
    Boolean isEulerianCycle()
    {
        // Check if all non-zero degree vertices are connected
        if (isSC() == false)
            return false;
 
        // Check if in degree and out degree of every vertex is same
        for (int i = 0; i < g.vertice; i++)
            if (g.arestas[i].size() != g.in[i])
                return false;
 
        return true;
    }
    private Boolean isSC()
    {
        // Step 1: Mark all the vertices as not visited (For
        // first DFS)
        boolean visited[] = new boolean[g.vertice];
        for (int i = 0; i < g.vertice; i++)
            visited[i] = false;
 
        // Step 2: Do DFS traversal starting from first vertex.
        DFS(0, visited,g);
 
        // If DFS traversal doesn't visit all vertices, then return false.
        for (int i = 0; i < g.vertice; i++)
            if (visited[i] == false)
                return false;
 
        // Step 3: Create a reversed graph
        Grafo gr = getTranspose();
 
        // Step 4: Mark all the vertices as not visited (For second DFS)
        for (int i = 0; i < g.vertice; i++)
            visited[i] = false;
 
        // Step 5: Do DFS for reversed graph starting from first vertex.
        // Staring Vertex must be same starting point of first DFS
        DFS(0, visited,gr);
 
        // If all vertices are not visited in second DFS, then
        // return false
        for (int i = 0; i < g.vertice; i++)
            if (visited[i] == false)
                return false;
 
        return true;
    }
    private void DFS(int i, boolean[] visitados,Grafo g) {

        visitados[i] = true;

        Iterator<Integer> car = g.arestas[i].iterator();

        while (car.hasNext()) {
            int n = car.next();
            if (!visitados[n])
                DFS(n, visitados,g);
        }
    }
    public void encontrarCaminhoEuleriano(){
        if(g.arestas.length==0)
            return;
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
        
        Grafo g4 =  new Grafo(4);
        g4.addArestaD(0, 1);
        g4.addArestaD(2, 3);
        
        euler.testeGrafo(g4);
    }
}
