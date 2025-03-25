public class HuffmanDecoder {

    public String decode(String encodedData, HuffmanNode root) {
        StringBuilder decodedData = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedData.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                decodedData.append(current.character);
                current = root;
            }
        }
        return decodedData.toString();
    }
}