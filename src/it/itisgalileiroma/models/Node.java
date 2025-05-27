package it.itisgalileiroma.models;

import java.util.ArrayList;
import java.util.List;

/***
 * Node of a graph, identified by an integer id
 */
public class Node {

    private int id;
    private List<Integer> neighbors;

    public Node(int id, List<Integer> neighbors) {
        this.id = id;
        this.neighbors = neighbors;
    }

    public Node(int id) {
        this.id = id;
        this.neighbors = new ArrayList<Integer>();
    }

    public Node() {

    }

    /***
     *
     * @return node id
     */
    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /***
     * Made easier than before
     * We set node IDs, not node objects
     * @return list of node's neighbors
     */
    public List<Integer> neighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Integer> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbor(int id) {
        this.neighbors.add(id);
    }
}
