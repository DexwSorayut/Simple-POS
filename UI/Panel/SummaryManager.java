package UI.Panel;

import ShoppingCart.CartItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * บันทึกยอดขายรวมลง File & Image/Summary.csv
 */
public class SummaryManager {

    // ใช้ Path เดียวให้ตรงกันทั้งคลาส
    private static final Path FILE = Paths.get("File & Image", "Summary.csv");

    /**
     * เรียกทุกครั้งเมื่อขายสำเร็จ (จาก Bill) จะอัปเดตจำนวนและยอดรวมใน Summary.csv
     */
    public static synchronized void updateSummary(java.util.List<CartItem> items) {
        Map<String, int[]> data = loadSummary();

        for (CartItem item : items) {
            // ชื่อคีย์: ชื่อสินค้า + (ขนาด)
            String sizeLabel = item.getSize().getLabel(); // Normal / Large / Largest
            String key = item.getProduct().getProductName() + "(" + sizeLabel + ")";
            int qty = item.getQuantity();

            // ราคาต่อหน่วย = base price + ราคาเพิ่มตามขนาด
            double unit = item.getProduct().getPrice() + item.getSizePrice();

            data.computeIfAbsent(key, k -> new int[]{0, 0});
            data.get(key)[0] += qty;                           // Quantity
            data.get(key)[1] += (int) Math.round(qty * unit);  // SummarySale (ปัดเป็น int)
        }

        saveSummary(data);
    }


    private static Map<String, int[]> loadSummary() {
        Map<String, int[]> map = new LinkedHashMap<>();

        if (!Files.exists(FILE)) return map;

        try (BufferedReader br = Files.newBufferedReader(FILE, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
               
                if (line.isEmpty() || line.startsWith("Product(Size)")) continue;
                if (line.startsWith("[")) continue;

                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                try {
                    String key = parts[0].trim();
                    int qty = Integer.parseInt(parts[1].trim());
                    int total = Integer.parseInt(parts[2].trim());
                    map.put(key, new int[]{qty, total});
                } catch (NumberFormatException ignore) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /** เขียนข้อมูลกลับลงไฟล์ Summary.csv */
    private static void saveSummary(Map<String, int[]> data) {
        try {
            // สร้างโฟลเดอร์ "File & Image" ถ้ายังไม่มี
            if (FILE.getParent() != null) {
                Files.createDirectories(FILE.getParent());
            }

            try (PrintWriter pw = new PrintWriter(
                    Files.newBufferedWriter(FILE, StandardCharsets.UTF_8))) {

                pw.println("Product(Size),Quantity,SummarySale");
                for (Map.Entry<String, int[]> e : data.entrySet()) {
                    pw.println(e.getKey() + "," + e.getValue()[0] + "," + e.getValue()[1]);
                }
            }

            System.out.println("Saved summary to: " + FILE.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}