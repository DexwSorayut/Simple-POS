package UI.Panel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;

import java.util.List;
import java.util.ArrayList;

public class Summary extends javax.swing.JPanel {

    private static final String SUMMARY_PATH = "File & Image/Summary.csv";
    private static final String ALL_SUMMARY_FOLDER = "File & Image/AllSummary/";
    private JPanel productListPanel;

    public Summary() {
        initComponents();
        loadSummaryData();
    }

    private List<String[]> readSummary() {
        List<String[]> data = new ArrayList<>();
        try {
            File file = new File(SUMMARY_PATH);
            if (!file.exists()) {
                System.out.println("Not Found Summary.csv");
                return data;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) data.add(parts);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error Reading Summary File: " + e.getMessage());
        }
        return data;
    }

    public static void addLineToSummary(String line) {
        try (FileWriter fw = new FileWriter(SUMMARY_PATH, true)) {
            fw.write(line + "\n");
        } catch (Exception e) {
            System.out.println("Error Writing Summary: " + e.getMessage());
        }
    }

    private void closeDay() {
        try {
            File folder = new File(ALL_SUMMARY_FOLDER);
            if (!folder.exists()) folder.mkdirs();

            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            File oldFile = new File(SUMMARY_PATH);
            File newFile = new File(ALL_SUMMARY_FOLDER + "Summary_" + today + ".csv");

            // Copy data
            try (BufferedReader br = new BufferedReader(new FileReader(oldFile));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(newFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    bw.write(line);
                    bw.newLine();
                }
            }

            // Clear old file
            try (BufferedWriter clear = new BufferedWriter(new FileWriter(SUMMARY_PATH))) {
                clear.write("Date,Time,Product,Size,Quantity,TotalPrice\n");
            }

            JOptionPane.showMessageDialog(this,
                    "Done!.",
                    "Summary Saved", JOptionPane.INFORMATION_MESSAGE);

            loadSummaryData();

        } catch (Exception e) {
            System.out.println("Error Closing Day: " + e.getMessage());
        }
    }

    // โหลดข้อมูลมาโชว์ใน UI
    private void loadSummaryData() {
    productListPanel = new JPanel(null);
    productListPanel.setBackground(Color.WHITE);

    List<String[]> data = readSummary();
    productListPanel.removeAll();

    double totalAmount = 0.0;
    Font font = new Font("TH Niramit AS", Font.PLAIN, 20);

    int startY = 10;      // ระยะจากด้านบน
    int rowHeight = 35;   // ความสูงแต่ละแถว

    for (int i = 0; i < data.size(); i++) {
        String[] line = data.get(i);
        String product = line[2] + " (" + line[3] + ")";
        String qty = line[4];
        String price = line[5];

        int y = startY + (i * rowHeight);

        //Product
        JLabel lblProduct = new JLabel(product);
        lblProduct.setFont(font);
        lblProduct.setBounds(80, y, 450, 30); 
        productListPanel.add(lblProduct);

        //Quantity
        JLabel lblQty = new JLabel(qty, SwingConstants.CENTER);
        lblQty.setFont(font);
        lblQty.setBounds(750, y, 100, 30); 
        productListPanel.add(lblQty);

        //Price
        JLabel lblPrice = new JLabel(price, SwingConstants.RIGHT);
        lblPrice.setFont(font);
        lblPrice.setBounds(1200, y, 120, 30); 
        productListPanel.add(lblPrice);

        try {
            totalAmount += Double.parseDouble(price);
        } catch (NumberFormatException ignored) {}
    }

    jLabel12.setText(String.format("%.2f Baht", totalAmount));

    jPanel2.removeAll();
    jPanel2.setLayout(new BorderLayout());
    JScrollPane scrollPane = new JScrollPane(productListPanel);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    scrollPane.getViewport().setBackground(Color.WHITE);
    jPanel2.add(scrollPane, BorderLayout.CENTER);
    jPanel2.setBackground(Color.WHITE);
    jPanel2.revalidate();
    jPanel2.repaint();
}

    //ปุ่ม Closing Day
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Sure?",
                "Closing Day", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            closeDay();
        }
    }

    //UI
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelSummary = new javax.swing.JLabel();
        jLabelSaleSummary = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        Time = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollBar1 = new javax.swing.JScrollBar();

        setBackground(Color.WHITE);
        setPreferredSize(new java.awt.Dimension(1604, 1049));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(Color.WHITE);
        jPanel1.setPreferredSize(new java.awt.Dimension(1604, 1049));

        jLabelSummary.setFont(new java.awt.Font("TH Niramit AS", 1, 60)); 
        jLabelSummary.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSummary.setText("Summary");

        jLabelSaleSummary.setFont(new java.awt.Font("TH Niramit AS", 0, 18)); 
        jLabelSaleSummary.setText("Sale Summary");

        Date.setFont(new java.awt.Font("TH Niramit AS", 0, 18)); 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Timer date = new Timer(1000, e -> {
            LocalDateTime now = LocalDateTime.now();
            Date.setText("Date: " + dtf.format(now));
        });
        date.start();

        Time.setFont(new java.awt.Font("TH Niramit AS", 0, 18)); 
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
        Timer timer = new Timer(1000, e -> {
            LocalDateTime now = LocalDateTime.now();
            Time.setText("Time: " + dtf2.format(now));
        });
        timer.start();

        jLabel4.setFont(new java.awt.Font("TH Niramit AS", 1, 18)); 
        jLabel4.setText("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel6.setFont(new java.awt.Font("TH Niramit AS", 0, 24)); 
        jLabel6.setText("Product(Size)");

        jLabel7.setFont(new java.awt.Font("TH Niramit AS", 0, 24)); 
        jLabel7.setText("Quantity");

        jLabel8.setFont(new java.awt.Font("TH Niramit AS", 0, 24)); 
        jLabel8.setText("Price");

        jLabel5.setFont(new java.awt.Font("TH Niramit AS", 1, 18)); 
        jLabel5.setText("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel9.setFont(new java.awt.Font("TH Niramit AS", 1, 18)); 
        jLabel9.setText("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel10.setFont(new java.awt.Font("TH Niramit AS", 0, 24)); 
        jLabel10.setText("Amount");

        jLabel11.setFont(new java.awt.Font("TH Niramit AS", 1, 18)); 
        jLabel11.setText("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel12.setFont(new java.awt.Font("TH Niramit AS", 0, 24)); 
        jLabel12.setText("Baht");

        jButton1.setBackground(new java.awt.Color(255, 189, 89));
        jButton1.setFont(new java.awt.Font("TH Niramit AS", 1, 48)); 
        jButton1.setText("Closing Day");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSummary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelSaleSummary)
                    .addComponent(Date)
                    .addComponent(Time)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(497, 497, 497)
                                .addComponent(jLabel7)
                                .addGap(393, 393, 393)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addGap(427, 427, 427))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelSummary)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSaleSummary)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Date)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Time)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(60, 60, 60)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }

    // Variables
    private javax.swing.JLabel Date;
    private javax.swing.JLabel Time;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10, jLabel11, jLabel12;
    private javax.swing.JLabel jLabelSaleSummary, jLabelSummary;
    private javax.swing.JPanel jPanel1, jPanel2;
    private javax.swing.JScrollBar jScrollBar1;
}