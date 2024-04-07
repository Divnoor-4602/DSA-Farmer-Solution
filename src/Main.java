import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of nodes: ");
        int numNodes = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Node<String>> nodes = new ArrayList<>();

        // Input nodes
        for (int i = 1; i <= numNodes; i++) {
            System.out.print("Enter name of node " + i + ": ");
            String nodeName = scanner.nextLine();
            Node<String> node = new Node<String>(nodeName);
            nodes.add(node);
        }

        // Input adjacent nodes and weights
        for (Node<String> node : nodes) {
            System.out.print("Enter the number of adjacent nodes for " + node.getName() + ": ");
            int numAdjacentNodes = scanner.nextInt();
            scanner.nextLine();

            for (int i = 1; i <= numAdjacentNodes; i++) {
                System.out.print("Enter name of adjacent node " + i + " and its weight separated by space: ");
                String[] input = scanner.nextLine().split(" ");
                String adjacentNodeName = input[0];
                int weight = Integer.parseInt(input[1]);
                Node<String> adjacentNode = nodes.stream().filter(n -> n.getName().equals(adjacentNodeName)).findFirst().orElse(null);
                if (adjacentNode != null) {
                    node.addAdjacentNode(adjacentNode, weight);
                } else {
                    System.out.println("Node " + adjacentNodeName + " not found!");
                }
            }
        }

        System.out.print("Enter the name of the source node: ");
        String sourceNodeName = scanner.nextLine();

        Node<String> sourceNode = nodes.stream().filter(n -> n.getName().equals(sourceNodeName)).findFirst().orElse(null);
        if (sourceNode != null) {
            Dijkstra<String> algorithm = new Dijkstra<String>();
            algorithm.calculateShortestPath(sourceNode);
            algorithm.printPaths(nodes);
        } else {
            System.out.println("Source node " + sourceNodeName + " not found!");
        }

        scanner.close();
    }
}
