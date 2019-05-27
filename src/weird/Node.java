package weird;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Node{
    private final String name;

    private List<Arc> arcs = new ArrayList<>();
    private List<Node> neighbors = new ArrayList<>();

    public boolean isMainNeighbor(Node node){
        List list = neighbors;
        if(!neighbors.contains(node))
            return false;
        list.remove(node);
        return node.getNeighbors().containsAll(list);
    }

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Arc> getArcs() {
        return arcs;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void addArc(Arc arc) {
        if (arc.getStart() == this && !arcs.contains(arc)) {
            arcs.add(arc);
            neighbors.add(arc.getEnd());
        } else if (arc.getEnd() == this && !arcs.contains(arc)) {
            arcs.add(arc);
            neighbors.add(arc.getStart());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        if (name == null || node.name == null) return false;
        return name.equals(node.name);
    }

    public void remove(){
        Iterator<Node> iteratorNode = neighbors.iterator();
        while (iteratorNode.hasNext()){
            Node node = iteratorNode.next();
            node.getNeighbors().remove(this);
        }
        neighbors.clear();
        Iterator<Arc> iteratorArc = arcs.iterator();
        while(iteratorArc.hasNext()){
            Arc arc = iteratorArc.next();
            if(arc.getStart() == this)
                arc.getEnd().arcs.remove(arc);
            else
                arc.getStart().arcs.remove(arc);
            iteratorArc.remove();
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}