import java.util.UUID;

public class Block {
    private String id;
    private long timestamp;
    private String data;

    public Block(String data) {
        this.id = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }

    @Override
    public String toString() {
        return "Block{" + "id='" + id + '\'' + ", timestamp=" + timestamp + ", data='" + data + '\'' + '}';
    }
}
