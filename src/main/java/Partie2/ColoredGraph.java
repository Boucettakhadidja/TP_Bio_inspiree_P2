package Partie2;



import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ColoredGraph extends JPanel {

    private List<Character> bfsResult;

    public ColoredGraph(List<Character> bfsResult) {
        this.bfsResult = bfsResult;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int cellWidth = panelWidth / bfsResult.size();
        for (int i = 0; i < bfsResult.size(); i++) {
            int x = i * cellWidth;
            int y = panelHeight / 2;
            int width = cellWidth;
            int height = panelHeight / 2;
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(bfsResult.get(i)), x + width / 2 - 5, y + height / 2 + 10);
        }
    }

    public void updateGraph(List<Character> bfsResult) {
        this.bfsResult = bfsResult;
        repaint();
    }

    // Méthodes supplémentaires pour la classe BFSWithPriority
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
            System.out.println("Le chemin BFS de " + startNode + " a " + targetNode + " est: " + coloredResult(bfsResult));
        } else {
            System.out.println("il y a pas de chemin " + startNode + " to " + targetNode);
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);
        ColoredGraph coloredGraph = new ColoredGraph(bfsResult);
        frame.getContentPane().add(coloredGraph);
        frame.setVisible(true);

        // Si vous souhaitez mettre à jour le graphe
        List<Character> newBfsResult = graph.bfs('A', 'D');
        coloredGraph.updateGraph(newBfsResult);
    }

    private static String coloredResult(List<Character> result) {
        StringBuilder coloredResult = new StringBuilder();
        for (Character c : result) {
            coloredResult.append("\u001B[32m").append(c).append("\u001B[0m");
        }
        return coloredResult.toString();
    }
}
