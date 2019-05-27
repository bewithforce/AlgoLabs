package weird;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Net {
    private List<Node> nodes = new ArrayList<>();
    private List<Arc> arcs = new ArrayList<>();

    private void createAndAddArcWithNodes(Node start, Node end) {
        if (!nodes.contains(start))
            nodes.add(start);
        else
            start = nodes.get(nodes.indexOf(start));
        if (!nodes.contains(end))
            nodes.add(end);
        else
            end = nodes.get(nodes.indexOf(end));
        if (start == end) {
            return;
        }
        Arc arc = new Arc(start, end);
        start.addArc(arc);
        end.addArc(arc);
        if (!arcs.contains(arc))
            arcs.add(arc);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void removeNode(int j) {
        Node node = nodes.get(j);
        arcs.removeIf(e -> e.getStart() == node || e.getEnd() == node);
        node.remove();
        nodes.remove(j);
    }

    public void readData(String filePath) {
        File dataFile = new File(filePath);
        Scanner scanner;
        if (!dataFile.exists()) {
            System.err.println("bad file path");
            return;
        }
        try {
            scanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
            System.err.println(dataFile);
            return;
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.matches("\\S+ \\S+")) {
                System.err.println("bad file");
                return;
            }
            String[] arr = line.split(" ");
            Node first, second;
            first = new Node(arr[0]);
            second = new Node(arr[1]);
            createAndAddArcWithNodes(first, second);
        }
    }

    public void weird() {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (i != j) {
                    if (nodes.get(i).isMainNeighbor(nodes.get(j))) {
                        removeNode(j);
                        if (j < i) {
                            i--;
                        }
                        j--;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Net net = new Net();
        net.readData("src\\road.txt");
        net.weird();
        System.out.println(net.nodes.size());
        System.out.println();
        for(Node node : net.nodes){
            System.out.println(node);
        }
    }
}
