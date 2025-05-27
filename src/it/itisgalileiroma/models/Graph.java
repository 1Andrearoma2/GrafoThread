package it.itisgalileiroma.models;

import java.util.ArrayList;
import java.util.List;

/***
 * A graph is characterized by
 * a list of nodes and a list of edges
 */
public class Graph {

    private List<Node> nodes;
    private List<Edge> edges;

    public Graph() {}

    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
        this.edges = new ArrayList<Edge>();
    }

    public List<Node> nodes() {
        return nodes;
    }

    /**
     * Add a node to a graph, if it has not already been inserted
     * @param node to add
     */
    public int addNode(Node node) {
        boolean notEquals = true;
        int indexNode = -1; // we need to check the index of the node to insert (or alreay inserted)

        for(int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).id() == node.id()) {
                notEquals = false;
                indexNode = i;
                break;
            }
        }
        if(notEquals) {
            nodes.add(node);
            indexNode = nodes.size() - 1;
        }
        return indexNode;
    }

    /**
     * Add an edge to a graph, if it has not already been inserted.
     * If the edge nodes have not yet been inserted,
     * add also them to the graph
     * @param edge to add
     */
    public void addEdge(Edge edge) {
        boolean exists = false;
        for(int i = 0; i < edges.size(); i++) {
            // todo add a comment here!
            if((edge.target().id() == edges.get(i).target().id()) && (edge.source().id() == edges.get(i).source().id())) {
                exists = true;
                break;
            }
        }

        if(!exists) {
            // first we add  nodes if they are not already inserted
            // get the indices of the nodes because we
            // we need to set the neighbors of each node in the next statements
            // for each edge, target node is the neighbor of  source node
            // and, since we assume that tha graph is not directed,
            // for eache edge, source node is the neighbor of the target node
            int indexSourceNode = this.addNode(edge.source());
            int indexTargetNode = this.addNode(edge.target());

            this.nodes().get(indexSourceNode).addNeighbor(edge.target().id());
            this.nodes().get(indexTargetNode).addNeighbor(edge.source().id());

            Edge e = new Edge(edge.target(), edge.source(), edge.weight());
            edges.add(edge);
            edges.add(e);
        }
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> edges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    /**
     * Return an object Node comparing the parameter id with all the node ids in the node list
     * If id doesn't exist, return null
     * @param id
     * @return null if the passed id is not contained in the list of nodes of the graph; otherwise, the Node object whose id is equal to the passed parameter
     */
    public Node getNode(int id) {
        for(Node node : nodes) {
            if(node.id() == id) {
                return node;
            }
        }
        return null;
    }
}
