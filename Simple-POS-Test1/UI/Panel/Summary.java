package UI.Panel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Summary extends javax.swing.JPanel {

    // ====== CONFIG ======
    private static final String CSV_PATH = "Summary.csv"; // ชื่อไฟล์ตามที่ขอ

    // === Additional enums / models ===
    public enum Size {
        NORMAL("Normal (+0)", 0),
        LARGE("Large (+10)", 10),
        LARGEST("Largest (+20)", 20);

        public final String label;
        public final int addPrice;

        Size(String label, int addPrice) {
            this.label = label;
            this.addPrice = addPrice;
        }
    }

    /** รายการสินค้าอ่านจาก CSV */
    static class Product {
        final String id;
        final String name;
        final BigDecimal basePrice;
        final String catalog;

        Product(String id, String name, BigDecimal basePrice, String catalog) {
            this.id = id; this.name = name; this.basePrice = basePrice; this.catalog = catalog;
        }
    }

    /** ตัวอ่าน/เก็บแคตตาล็อกจาก CSV (อ่านจากไฟล์; fallback เป็นสตริงเดิม) */
    static class ProductCatalog {
        private final Map<String, Product> byId = new LinkedHashMap<>();
        private final Map<String, List<Product>> byCatalog = new LinkedHashMap<>();

        private static final String DEFAULT_CSV = String.join("\n",
            "ProductID,ProductName,Price,Catalog",
            "Normal(+0),Large(+10),Largest(+20)",
            "",
            "[Milk]",
            "M001,Milk,15.0,Milk",
            "M002,Pink Milk,20.0,Milk",
            "M003,Cocoa,20.0,Milk",
            "M004,Strawberry Milk,30.0,Milk",
            "M005,Ovaltine,20.0,Milk",
            "",
            "[Coffee]",
            "C001,Latte,40.0,Coffee",
            "C002,Mocha,40.0,Coffee",
            "C003,Americano,35.0,Coffee",
            "C004,Espresso,40.0,Coffee",
            "C005,Cappuccino,40.0,Coffee",
            "",
            "[Juice]",
            "J001,Orange juice,20.0,Juice",
            "J002,Pineapple juice,20.0,Juice",
            "J003,Lemon juice,20.0,Juice",
            "J004,Watermelon juice,20.0,Juice",
            "J005,Apple juice,20.0,Juice",
            "J006,Passion fruit juice,25.0,Juice",
            "J007,Kiwi juice,25.0,Juice",
            "J008,Blueberry Juice,25.0,Juice",
            "J009,Strawberry juice,25.0,Juice",
            "J010,Tomato juice,20.0,Juice",
            "",
            "[Soda]",
            "S001,Coca-Cola,10.0,Soda",
            "S002,Pepsi,15.0,Soda",
            "S003,Est-Cola,10.0,Soda",
            "S004,Fanta,15.0,Soda",
            "S005,Sprite,15.0,Soda",
            "S006,7-UP,15.0,Soda",
            "S007,Singha,15.0,Soda",
            "S008,Schwepppes,15.0,Soda",
            "S009,Mirinda,12.0,Soda",
            "",
            "[Water]",
            "W001,Crystal drinking water,10.0,Water",
            "W002,Namthip drinking water,8.0,Water",
            "W003,Chang drinking water,9.0,Water",
            "W004,Mineral water,12.0,Water",
            "W005,Aura Water,12.0,Water"
        );

        ProductCatalog(String csvPath) {
            String csv = DEFAULT_CSV;
            try {
                Path p = Paths.get(csvPath);
                if (Files.exists(p)) {
                    csv = Files.readString(p, StandardCharsets.UTF_8);
                }
            } catch (IOException ignored) {}
            parse(csv);
        }

        private void parse(String csvText) {
            for (String raw : csvText.split("\\R")) {
                String line = raw.trim();
                if (line.isEmpty()) continue;
                if (line.startsWith("[") && line.endsWith("]")) continue;
                if (line.startsWith("ProductID") || line.startsWith("Normal(")) continue;

                String[] p = line.split("\\s*,\\s*");
                if (p.length < 4) continue;

                Product product = new Product(
                    p[0], p[1], new BigDecimal(p[2]), p[3]
                );
                byId.put(product.id, product);
                byCatalog.computeIfAbsent(product.catalog, k -> new ArrayList<>()).add(product);
            }
        }

        Product getById(String id) { return byId.get(id); }
        List<Product> getByCatalog(String catalog) { return byCatalog.getOrDefault(catalog, Collections.emptyList()); }
        Set<String> catalogs() { return byCatalog.keySet(); }
    }

    /** เก็บยอดขายสะสม (หน่วยความจำ) */
    static class SalesStore {
        private static final SalesStore INSTANCE = new SalesStore();
        private final Map<String, EnumMap<Size, Integer>> data = new LinkedHashMap<>();

        static SalesStore get() { return INSTANCE; }

        synchronized void record(String productId, Size size, int qty) {
            if (qty <= 0) return;
            EnumMap<Size, Integer> sizes = data.computeIfAbsent(productId, k -> new EnumMap<>(Size.class));
            sizes.put(size, sizes.getOrDefault(size, 0) + qty);
        }

        synchronized int getQty(String productId, Size size) {
            EnumMap<Size, Integer> sizes = data.get(productId);
            return sizes == null ? 0 : sizes.getOrDefault(size, 0);
        }
    }

    // ================== NetBeans generated + minor additions ==================

    public Summary() {
        initComponents();

        // ---- พื้นหลังให้ขาวทั้งหมด ----
        setBackground(Color.WHITE);
        jPanel1.setBackground(Color.WHITE);
        summaryPanel.setBackground(Color.WHITE);
        summaryScrollPane.getViewport().setBackground(Color.WHITE);
        jList1.setBackground(Color.WHITE);

        // โหลด catalog จากไฟล์
        this.catalog = new ProductCatalog(CSV_PATH);

        // ตั้งค่า jList1 ให้แสดงหมวดจากไฟล์ (ไม่เปลี่ยนตำแหน่งใดๆ)
        DefaultListModel<String> lm = new DefaultListModel<>();
        for (String cat : catalog.catalogs()) lm.addElement(cat);
        jList1.setModel(lm);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // ตั้งค่าการเลือกหมวด: เปลี่ยนมุมมองเมื่อเลือก
        jList1.addListSelectionListener(listSelectionListener);

        if (lm.getSize() > 0) {
            jList1.setSelectedIndex(0);
        }
    }

    // สาธารณะให้ภายนอกบันทึกยอดได้ (สะสม ไม่เขียนไฟล์)
    public static void recordSale(String productId, Size size, int qty) {
        SalesStore.get().record(productId, size, qty);
    }

    /** รีเฟรชตามหมวดที่เลือกใน jList1 */
    public void refresh() {
        String cat = jList1.getSelectedValue();
        if (cat == null) return;
        buildSummaryUI(cat);
    }

    private ProductCatalog catalog; // ← สร้างหลัง initComponents()
    private final ListSelectionListener listSelectionListener = e -> {
        if (!e.getValueIsAdjusting()) refresh();
    };

    // ---------- UI BUILDER: วาดแถวสรุป ----------
    private void buildSummaryUI(String category) {
        summaryPanel.removeAll();
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 18, 4, 18);
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridy = 0; gc.gridx = 0; gc.weightx = 1.0;

        BigDecimal total = BigDecimal.ZERO;

        for (Product p : catalog.getByCatalog(category)) {
            for (Size s : Size.values()) {
                int qty = SalesStore.get().getQty(p.id, s);
                if (qty <= 0) continue;

                BigDecimal unit = p.basePrice.add(new BigDecimal(s.addPrice));
                BigDecimal line = unit.multiply(new BigDecimal(qty));

                String productCol =
                        "• " + p.name + " (" + s.label.replace(" (+", " +").replace(")", "") + ")  …  " +
                        unit.stripTrailingZeros().toPlainString() + " ฿";

                addRow(summaryPanel, gc,
                        normal(productCol),
                        center(normal(String.valueOf(qty))),
                        right(normal(line.stripTrailingZeros().toPlainString() + " ฿")));

                total = total.add(line);
            }
        }

        totalValueLabel.setText("Total  " + total.stripTrailingZeros().toPlainString() + "฿");

        summaryPanel.revalidate();
        summaryPanel.repaint();
    }

    // Helper สร้าง label style
    private JLabel normal(String s) {
        JLabel l = new JLabel(s);
        l.setFont(new Font("TH Niramit AS", Font.PLAIN, 18));
        return l;
    }

    private JLabel bold(String s) {
        JLabel l = new JLabel(s);
        l.setFont(new Font("TH Niramit AS", Font.BOLD, 18));
        return l;
    }

    private JLabel center(JLabel l) { l.setHorizontalAlignment(SwingConstants.CENTER); return l; }
    private JLabel right(JLabel l) { l.setHorizontalAlignment(SwingConstants.RIGHT); return l; }

    private void addRow(JPanel panel, GridBagConstraints gc, JComponent c1, JComponent c2, JComponent c3) {
        gc.gridx = 0; gc.weightx = 0.60;
        panel.add(c1, (GridBagConstraints) gc.clone());
        gc.gridx = 1; gc.weightx = 0.20;
        panel.add(c2, (GridBagConstraints) gc.clone());
        gc.gridx = 2; gc.weightx = 0.20;
        panel.add(c3, (GridBagConstraints) gc.clone());
        gc.gridy++;
    }

    // ================= NetBeans generated code (ตำแหน่งเดิม) =================

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelSummary = new javax.swing.JLabel();
        jLabelSaleSummary = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        // === Additions for summary area ===
        summaryScrollPane = new javax.swing.JScrollPane();
        summaryPanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        totalValueLabel = new javax.swing.JLabel("Total 0฿");

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1604, 1049));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1604, 1049));

        jLabelSummary.setFont(new java.awt.Font("TH Niramit AS", 1, 60)); // NOI18N
        jLabelSummary.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSummary.setText("Summary");

        jLabelSaleSummary.setFont(new java.awt.Font("TH Niramit AS", 0, 18)); // NOI18N
        jLabelSaleSummary.setText("Sale Summary");
        jLabelSaleSummary.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel2.setFont(new java.awt.Font("TH Niramit AS", 0, 18)); // NOI18N
        jLabel2.setText("Date :");

        jLabel3.setFont(new java.awt.Font("TH Niramit AS", 0, 18)); // NOI18N
        jLabel3.setText("Time :");

        jLabel4.setFont(new java.awt.Font("TH Niramit AS", 1, 18)); // NOI18N
        jLabel4.setText("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel6.setFont(new java.awt.Font("TH Niramit AS", 0, 18)); // NOI18N
        jLabel6.setText("Product(size)");

        jList1.setFont(new java.awt.Font("TH Niramit AS", 0, 18)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Loading..." };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel7.setFont(new java.awt.Font("TH Niramit AS", 0, 18)); // NOI18N
        jLabel7.setText("Quantity");

        jLabel8.setFont(new java.awt.Font("TH Niramit AS", 0, 18)); // NOI18N
        jLabel8.setText("price summary");

        // === configure summary area (ใต้เส้นคั่น) ===
        summaryPanel.setBackground(new java.awt.Color(255, 255, 255));
        summaryScrollPane.setViewportView(summaryPanel);
        summaryScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        summaryScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        totalValueLabel.setFont(new java.awt.Font("TH Niramit AS", 1, 22)); // NOI18N
        totalValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 1610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 524, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSaleSummary)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)
                        .addGap(497, 497, 497)
                        .addComponent(jLabel7)
                        .addGap(393, 393, 393)
                        .addComponent(jLabel8))
                    .addComponent(summaryScrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(totalValueLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelSummary)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelSaleSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(summaryScrollPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalValueLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelSaleSummary;
    private javax.swing.JLabel jLabelSummary;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration

    // === NEW variables (ถูกเพิ่ม แต่ไม่แตะบล็อก "do not modify" ด้านบน) ===
    private javax.swing.JScrollPane summaryScrollPane;
    private javax.swing.JPanel summaryPanel;
    private javax.swing.JLabel totalValueLabel;
}