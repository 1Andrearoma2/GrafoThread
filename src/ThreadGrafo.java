import it.itisgalileiroma.models.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class ThreadGrafo extends Thread{

    @Override
    public void run(){
        ArrayList<Node> nodi = new ArrayList<>();
        int nNodi = 0;
        int nArchi = 0;
        Scanner s = new Scanner(System.in);
        HashMap<Node, ArrayList<Edge>> grafo = new HashMap<>();
        while (true){
            System.out.println("Quanti nodi vuoi?");
            try {
                nNodi = s.nextInt();
                break;
            } catch (InputMismatchException e){
                System.out.println("Input errato! Riprova");
                s.next();
            }
        }

        // Creazione nodi grafo
        for (int i = 0; i < nNodi; i++){
            while (true) {
                System.out.println("Inserisci ID nodo n." + (i+1));
                try {
                    int id = s.nextInt();
                    nodi.add(new Node(id));
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Input errato! Riprova");
                    s.next();
                }
            }
        }
        Graph g = new Graph(nodi);

        // Creazione archi grafo
        while (true) {
            System.out.println("Quanti archi vuoi?");
            try {
                nArchi = s.nextInt();
                break;
            } catch (InputMismatchException e){
                System.out.println("Input errato! Riprova");
                s.next();
            }
        }

        for (int i = 0; i < nArchi; i++){
            int idSorgente = 0;
            int idDestinatario = 0;
            while (true) {
                System.out.println((i+1) + ". " + "Inserisci l'ID del nodo sorgente");
                try {
                    idSorgente = s.nextInt();
                    boolean exists = false;
                    for (Node n : nodi){
                        if (n.id() == idSorgente){
                            exists = true;
                        }
                    }
                    if (!exists){
                        System.out.println("ID nodo errato! Riprova");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input errato! Riprova");
                    s.next();
                }
            }

            while (true) {
                System.out.println((i+1) + ". " + "Inserisci l'ID del nodo destinatario");
                try {
                    idDestinatario = s.nextInt();
                    boolean exists = false;
                    for (Node n : nodi){
                        if (n.id() == idDestinatario){
                            exists = true;
                        }
                    }
                    if (!exists){
                        System.out.println("ID nodo errato! Riprova");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input errato! Riprova");
                    s.next();
                }
            }
            g.addEdge(new Edge(g.getNode(idSorgente), g.getNode(idDestinatario), 0));
        }

        // Creazione Hashmap
        for (Node n : nodi){
            ArrayList<Edge> edgeLinked = new ArrayList<>();
            for (Edge e : g.edges()){
                if (e.source().equals(n) || e.target().equals(e)){
                    edgeLinked.add(e);
                }
            }
            grafo.put(n, edgeLinked);
        }

        // Output Hashmap
        System.out.println("Grafo Completo:");
        for (Node n : grafo.keySet()){
            ArrayList<Integer> LinkedNode = new ArrayList<>();
            System.out.print("Nodo n." + n.id() + " -> ");
            for (Edge e : grafo.get(n)){
                if (!LinkedNode.contains(e.source().id()) && e.source().id() != n.id()){
                    LinkedNode.add(e.source().id());
                }
                if (!LinkedNode.contains(e.target().id()) && e.target().id() != n.id()){
                    LinkedNode.add(e.target().id());
                }
            }
            for (int id : LinkedNode){
                System.out.print(id + " ");
            }
            System.out.println();
        }

    }
}
