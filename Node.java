import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Node implements Runnable {
    private static final int VALIDITY_THRESHOLD = 2;  // Наприклад, 2/3
    private String nodeName;
    private boolean isLeader;
    private Block candidateBlock;
    private List<Node> nodes;
    private AtomicInteger validCount;

    public Node(String nodeName, List<Node> nodes, boolean isLeader) {
        this.nodeName = nodeName;
        this.isLeader = isLeader;
        this.nodes = nodes;
        this.validCount = new AtomicInteger(0);
    }

    public void createAndBroadcastBlock() {
        if (!isLeader) return;
        candidateBlock = new Block("Transaction Data");
        System.out.println(nodeName + " створює блок і розсилає його всім вузлам...");
        for (Node node : nodes) {
            if (node != this) {
                node.receiveBlock(candidateBlock, this);
            }
        }
    }

    public void receiveBlock(Block block, Node sender) {
        System.out.println(nodeName + " отримав блок від " + sender.nodeName + " і перевіряє його валідність...");
        boolean isValid = validateBlock(block);

        if (isValid) {
            broadcastValidMessage();
        }
    }

    public void broadcastValidMessage() {
        System.out.println(nodeName + " розсилає повідомлення Valid всім вузлам...");
        for (Node node : nodes) {
            if (node != this) {
                node.receiveValidMessage(this.nodeName);
            }
        }
    }

    public void receiveValidMessage(String fromNodeName) {
        validCount.incrementAndGet();

        if (validCount.get() >= nodes.size() * 2 / 3) {
            System.out.println(nodeName + " отримав >= 2/3 Valid повідомлень. Додає блок до свого блокчейну...");
            // додавання до локального блокчейну
        }
    }

    private boolean validateBlock(Block block) {
        // Імітація перевірки валідності блока
        try {
            Thread.sleep(50); // Імітація перевірки
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void run() {
        if (isLeader) {
            createAndBroadcastBlock();
        }
    }
}
