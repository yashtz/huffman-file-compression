import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HuffmanEncoder encoder = new HuffmanEncoder();
        HuffmanDecoder decoder = new HuffmanDecoder();

        try {
            System.out.println("Huffman File Compression");
            System.out.print("Enter file path to compress: ");
            String filePath = scanner.nextLine();

            String data = FileUtil.readFile(filePath);

            encoder.buildTree(data);
            String encodedData = encoder.encode(data);
            FileUtil.writeFile("output/compressed.txt", encodedData);
            System.out.println("✅ File compressed and saved as 'output/compressed.txt'");

            double compressionRatio = (double) encodedData.length() / (data.length() * 8);
            System.out.printf("📊 Compression Ratio: %.2f%%\n", compressionRatio * 100);

            System.out.print("\nDo you want to decompress the file? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("y")) {
                String decodedData = decoder.decode(encodedData, encoder.getRoot());
                FileUtil.writeFile("output/decompressed.txt", decodedData);
                System.out.println("✅ File decompressed and saved as 'output/decompressed.txt'");
            } else {
                System.out.println("👌 Skipping decompression.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}