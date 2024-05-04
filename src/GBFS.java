/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;

/**
 *
 * @author FRANCESCO
 */
public class GBFS extends Graph {
    public GBFS(ArrayList<String> wordList) {
        super(wordList);
    }

    @Override
    public ArrayList<String> shortestPath(String start, String end) {
        String currentWord = start;
        ArrayList<String> visited = new ArrayList<>();

        while(!visited.contains(currentWord)) {
            visited.add(currentWord);
            if(currentWord.equals(end)) {
                // ada path
                setNodeVisited(visited.size());
                return visited;
            }
            ArrayList<String> neighbors = getNeighbors(currentWord);
            currentWord = neighbors.getFirst();
            int currentScore = heuristic(currentWord, end);
//            for (String neighbor : neighbors) {
//                if(currentScore > heuristic(neighbor, end)) {}
//            }
            for(int i = 1; i < neighbors.size(); i++) {
                if(currentScore > heuristic(neighbors.get(i), end)) {
                    currentScore = heuristic(neighbors.get(i), end);
                    currentWord = neighbors.get(i);
                }
            }
        }
        setNodeVisited(visited.size());
        return null;
    }
}
