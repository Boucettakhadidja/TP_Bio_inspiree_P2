package Partie2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.*;


public class BFSWithPriority {
    private Map<Character, List<Character>> graph;

    public BFSWithPriority() {
        this.graph = new HashMap<Character, List<Character>>();
    }

    public void addEdge(char from, char to) {
        graph.computeIfAbsent(from, k -> new ArrayList<Character>()).add(to);
    }
   

    public List<Character> bfs(char start, char target) {
        Map<Character, Character> parentMap = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            char current = queue.poll();

            List<Character> neighbors = graph.getOrDefault(current, Collections.emptyList());
            Collections.sort(neighbors);

            for (char neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);

                    if (neighbor == target) {
                        return constructPath(parentMap, start, target);
                    }
                }
            }
        }

        return Collections.emptyList(); // Return an empty list if target is not reachable
    }

    private List<Character> constructPath(Map<Character, Character> parentMap, char start, char target) {
        List<Character> path = new ArrayList<>();
        char current = target;

        while (current != start) {
            path.add(current);
            current = parentMap.get(current);
        }

        path.add(start);
        Collections.reverse(path);
        return path;
    }
 

    public static void main(String[] args) {
        BFSWithPriority graph = new BFSWithPriority();

        // Ajoutez vos arêtes ici
        graph.addEdge('S', 'A');
        graph.addEdge('S', 'G');
        graph.addEdge('A', 'B');
        graph.addEdge('A', 'C');
        graph.addEdge('B', 'D');
        graph.addEdge('C', 'D');
        graph.addEdge('C', 'G');
        graph.addEdge('D', 'G');

        char startNode = 'S';
        char targetNode = 'G';


        List<Character> bfsResult = graph.bfs(startNode, targetNode);

        if (!bfsResult.isEmpty()) {
            System.out.println("Le chemin BFS de " + startNode + " a " + targetNode + " est: " + bfsResult);
        } else {
            System.out.println("il y a pas de chemin  " + startNode + " to " + targetNode);
        }
    }
    private static String coloredResult(List<Character> result) {
        StringBuilder coloredStringBuilder = new StringBuilder();
        for (Character c : result) {
            coloredStringBuilder.append("\u001B[32m").append(c).append("\u001B[0m");
        }
        // Reset color after the loop
        coloredStringBuilder.append("\u001B[0m");
        return coloredStringBuilder.toString();
    }
}


  

	   


