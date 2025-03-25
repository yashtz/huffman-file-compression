import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncoder {
    private Map<Character, String> huffmanCode = new HashMap<>();
    private HuffmanNode root;

    // Build Huffman Tree
    public void buildTree(String data) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Count character frequency
        for (char ch : data.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (var entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            priorityQueue.add(parent);
        }

        root = priorityQueue.poll();

        // Generate codes
        generateCodes(root, "");
    }

    private void generateCodes(HuffmanNode node, String code) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            huffmanCode.put(node.character, code);
        }
        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    // Encode data
    public String encode(String data) {
        StringBuilder encodedData = new StringBuilder();
        for (char ch : data.toCharArray()) {
            encodedData.append(huffmanCode.get(ch));
        }
        return encodedData.toString();
    }

    public Map<Character, String> getHuffmanCode() {
        return huffmanCode;
    }

    public HuffmanNode getRoot() {
        return root;
    }
}