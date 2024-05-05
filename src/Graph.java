import java.util.ArrayList;

abstract class Graph {
    protected int nodeVisited;
    protected ArrayList<String> word;

    private boolean connected(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int dif = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                dif++;
                if (dif > 1) {
                    return false;
                }
            }
        }
        return dif == 1;
    }

    public Graph(ArrayList<String> wordList) {
        this.word = new ArrayList<>();
        this.nodeVisited = 0;
        this.word.addAll(wordList);
    }

    protected ArrayList<String> getNeighbors(String current) {
        ArrayList<String> neighbors = new ArrayList<>();
        for (String kata : word) {
            if (kata.length() == current.length() && !kata.equals(current) && connected(kata, current)) {
                neighbors.add(kata);
            }
        }
        return neighbors;
    }

    public int getNodeVisited() {
        return this.nodeVisited;
    }

    public void setNodeVisited(int nodeVisited) {
        this.nodeVisited = nodeVisited;
    }

    protected int heuristic(String current, String end) {
        int count = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != end.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    abstract ArrayList<String> shortestPath(String start, String end);
}
