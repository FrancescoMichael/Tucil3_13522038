import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar extends Graph {
    public AStar(ArrayList<String> wordList) {
        super(wordList);
    }

    @Override
    public ArrayList<String> shortestPath(String start, String end) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.totalEstimatedCost));
        pq.add(new Node(start, 0, heuristic(start, end), heuristic(start, end), null));

        ArrayList<String> visited = new ArrayList<>();

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            String currentWord = currentNode.word;

            if (currentWord.equals(end)) {
                visited.add(currentWord);
                setNodeVisited(visited.size());
                ArrayList<String> path = new ArrayList<>();
                while (currentNode != null) {
                    path.addFirst(currentNode.word);
                    currentNode = currentNode.parent;
                }
                return path;
            }

            if (!visited.contains(currentWord)) {
                visited.add(currentWord);
                ArrayList<String> neighbors = getNeighbors(currentWord);
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        int cost = currentNode.cost + 1;
                        int heuristicValue = heuristic(neighbor, end);
                        int totalEstimatedCost = cost + heuristicValue;
                        pq.add(new Node(neighbor, cost, heuristicValue, totalEstimatedCost, currentNode));
                    }
                }
            }
        }
        setNodeVisited(visited.size());
        return null;
    }

    private static class Node {
        String word;
        int cost;
        int heuristic;
        int totalEstimatedCost;
        Node parent;

        Node(String word, int cost, int heuristic, int totalEstimatedCost, Node parent) {
            this.word = word;
            this.cost = cost;
            this.heuristic = heuristic;
            this.totalEstimatedCost = totalEstimatedCost;
            this.parent = parent;
        }
    }
}
