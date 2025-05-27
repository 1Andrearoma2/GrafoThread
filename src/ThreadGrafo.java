import it.itisgalileiroma.models.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class ThreadGrafo extends Thread{
    @Override
    public void run(){
        ArrayList<Node> nodi = new ArrayList<>();
        int nNodi = 0;
        int nArchi = 0;
        Scanner s = new Scanner(System.in);
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
        Graph grafo = new Graph(nodi);

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
                System.out.println("Inserisci l'ID del nodo sorgente riguardo all'arco n." + (i+1));
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
                System.out.println("Inserisci l'ID del nodo destinatario riguardo all'arco n." + (i+1));
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
            grafo.addEdge(new Edge(grafo.getNode(idSorgente), grafo.getNode(idDestinatario), 0));
        }
        System.out.println("Grafo creato!");
        System.out.print("Nodi grafo: ");
        for (Node n : grafo.nodes()){
            System.out.print(n.id() + " ");
        }
        System.out.println();
        System.out.print("Archi grafo: ");
        for (Edge e : grafo.edges()){
            System.out.print(e.source().id() + "->" + e.target().id() + " ");
        }
    }
}
