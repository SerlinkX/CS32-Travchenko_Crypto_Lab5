import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int[] nodeCounts = {10, 50, 100, 500}; // Кількість вузлів
        for (int n : nodeCounts) {
            System.out.println("Кількість вузлів: " + n);
            runBFTProtocol(n);
        }
    }

    public static void runBFTProtocol(int numberOfNodes) {
        List<Node> nodes = new ArrayList<>();

        // Створюємо вузли і обираємо перший вузол лідером
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node("Node " + i, nodes, i == 0));
        }

        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfNodes);
        for (Node node : nodes) {
            executorService.submit(node);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Час виконання для " + numberOfNodes + " вузлів: " + (endTime - startTime) + " мс.");
    }
}
