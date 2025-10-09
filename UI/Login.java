package UI;

import java.awt.*;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
import User.*;

public class Login extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());
    UserRepository userRepo = new UserRepository();
    AuthService authService = new AuthService(userRepo);

    // ===== Swing variables =====
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldPW;
    private javax.swing.JPanel panelUsers;
    private javax.swing.JLabel pwErrorLabel; 

    public Login() {
        initComponents();
        createUserButtons();

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> setVisible(true));

        // ===== Numpad popup =====
        JPopupMenu numpadPopup = new JPopupMenu();
        numpadPopup.setFocusable(false);
        JPanel numpadPanel = createNumpad(jTextFieldPW, () -> numpadPopup.setVisible(false));
        numpadPanel.setPreferredSize(new Dimension(200, 200));
        numpadPopup.add(numpadPanel);

        jTextFieldPW.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jTextFieldPW.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldPW.setText("Password");
        jTextFieldPW.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                if (jTextFieldPW.getText().equals("Password")) jTextFieldPW.setText("");
                int x = -numpadPanel.getPreferredSize().width, y = 0;
                numpadPopup.show(jTextFieldPW, x, y);
            }
            @Override public void focusLost(FocusEvent e) {
                if (jTextFieldPW.getText().isEmpty()) jTextFieldPW.setText("Password");
            }
        });

        // <<< FIX #2: อย่าซ่อน label ตอน removeUpdate (เคลียร์ด้วยโค้ด)
        jTextFieldPW.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void hideErr() {
                pwErrorLabel.setVisible(false);
                jTextFieldPW.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { hideErr(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { /* do nothing */ }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { /* do nothing */ }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        UserRepository userRepo = new UserRepository();
        List<User> users = userRepo.getAllUsers();

        jPanelMain = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldID = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldPW = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelUsers = new javax.swing.JPanel();

        // ===== label error ใต้ช่องรหัสผ่าน =====
        pwErrorLabel = new javax.swing.JLabel();
        pwErrorLabel.setFont(new java.awt.Font("Segoe UI", 0, 14));
        pwErrorLabel.setForeground(new java.awt.Color(204, 0, 0));
        pwErrorLabel.setText("Error: Password is incorrect.");
        pwErrorLabel.setVisible(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);
        setUndecorated(true);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        jPanelMain.setBackground(new java.awt.Color(0, 0, 0));
        jPanelMain.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanelMain.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) { jPanelMain.requestFocusInWindow(); }
        });

        jPanel1.setBackground(new java.awt.Color(255, 145, 77));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel1.setPreferredSize(new java.awt.Dimension(1880, 250));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 72));
        jLabel1.setText("Welcome to Simple POS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(587, 587, 587)
                .addComponent(jLabel1)
                .addContainerGap(450, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel2.setPreferredSize(new java.awt.Dimension(750, 750));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 24));
        jLabel3.setText("--------------------------- OR ---------------------------");

        jButton2.setBackground(new java.awt.Color(255, 189, 89));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jButton2.setText("Register");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jButton3.setText("Login");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton3.addActionListener(evt -> jButton3ActionPerformed(evt));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup().addGap(73,73,73).addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup().addGap(167,167,167).addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup().addGap(166,166,166).addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(78, 78, 78))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel3)
                .addGap(50, 50, 50)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 66));
        jLabel2.setText("Login");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 18));
        jLabel4.setText("User ID :");

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.setPreferredSize(new java.awt.Dimension(60, 60));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,56,Short.MAX_VALUE));
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,56,Short.MAX_VALUE));

        jTextFieldID.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jTextFieldID.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldID.setText("User ID");
        jTextFieldID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) { jTextFieldIDFocusGained(evt); }
            public void focusLost(java.awt.event.FocusEvent evt) { jTextFieldIDFocusLost(evt); }
        });

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel5.setPreferredSize(new java.awt.Dimension(60, 60));
        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,56,Short.MAX_VALUE));
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,56,Short.MAX_VALUE));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 18));
        jLabel5.setText("Password :");

        jButton5.setBackground(new java.awt.Color(255, 189, 89));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jButton5.setText("Login");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton5.addActionListener(evt -> jButton5ActionPerformed(evt));

        int Height = users.size() * 40;
        panelUsers.setPreferredSize(new java.awt.Dimension(604, Height));
        panelUsers.setLayout(new java.awt.GridLayout());
        jScrollPane2.setViewportView(panelUsers);
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // ===== Layout ของ jPanel3 =====
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup().addGap(471,471,471).addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup().addGap(241,241,241).addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(241,241,241)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6,6,6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup().addGap(1,1,1).addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup().addGap(241,241,241).addComponent(jLabel5))
                    // ช่อง PW + ป้าย error ใต้ PW
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(241,241,241)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6,6,6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldPW, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pwErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup().addGap(455,455,455).addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(71,71,71)
                .addComponent(jLabel2)
                .addGap(36,36,36)
                .addComponent(jLabel4)
                .addGap(6,6,6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup().addGap(58,58,58).addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jLabel5)
                .addGap(5,5,5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPW, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pwErrorLabel)
                .addGap(60,60,60)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(18,18,18)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3,3,3)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(18,18,18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }

    // ===== Handlers =====
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) { }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) { new Register(null).setVisible(true); }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        UserRepository repo = new UserRepository();
        authService = new AuthService(repo);

        String userID = jTextFieldID.getText().trim();
        String password = jTextFieldPW.getText().trim();

        if (authService.login(userID, password)) {
            new MainUI(authService).setVisible(true);
            this.dispose();
        } else {
            //  ล้างก่อน แล้วค่อยโชว์ label
            jTextFieldPW.setText("");
            pwErrorLabel.setVisible(true);
            jTextFieldPW.requestFocus();
            jTextFieldPW.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        }
    }

    private void jTextFieldIDFocusGained(java.awt.event.FocusEvent evt) { if (jTextFieldID.getText().equals("User ID")) jTextFieldID.setText(""); }
    private void jTextFieldIDFocusLost(java.awt.event.FocusEvent evt) { if (jTextFieldID.getText().isEmpty()) jTextFieldID.setText("User ID"); }
    private void jTextFieldIDActionPerformed(java.awt.event.ActionEvent evt) { }
    private void jTextFieldPWActionPerformed(java.awt.event.ActionEvent evt) { }

    
    public void createUserButtons() {
        UserRepository userRepo = new UserRepository();
        List<User> users = userRepo.getAllUsers();

        panelUsers.removeAll();
        panelUsers.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int i = 1, columns = 1;
        for (User u : users) {
            JButton userButton = new JButton("User " + i++ + " : " + u.getUserID() + " (" + u.getUserName() + ")");
            userButton.setFont(new java.awt.Font("Segoe UI", 0, 20));
            userButton.setHorizontalAlignment(SwingConstants.LEFT);
            userButton.setFocusPainted(false);
            userButton.setContentAreaFilled(true);
            userButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            userButton.setOpaque(true);
            userButton.setBorderPainted(true);
            userButton.setPreferredSize(new Dimension(600, 40));
            userButton.setMinimumSize(new Dimension(600, 40));
            userButton.setMaximumSize(new Dimension(600, 40));
            userButton.addActionListener(e -> jTextFieldID.setText(u.getUserID()));

            gbc.gridx++;
            if (gbc.gridx == columns) { gbc.gridx = 0; gbc.gridy++; }
            panelUsers.add(userButton, gbc);
            panelUsers.add(Box.createVerticalGlue(), gbc);
        }

        panelUsers.revalidate();
        panelUsers.repaint();
    }

    public static JPanel createNumpad(JTextField textField, Runnable closeAction) {
        JPanel panel = new JPanel(new GridLayout(4, 3, 0, 0));
        String[] buttons = { "1","2","3","4","5","6","7","8","9","Del","0","Enter" };

        for (String s : buttons) {
            JButton button = new JButton(s);
            button.setFont(new Font("Segoe UI", 1, 15));
            button.setPreferredSize(new Dimension(50, 40));
            button.addActionListener(e -> {
                switch (s) {
                    case "Del" -> {
                        String current = textField.getText();
                        if (!current.isEmpty()) textField.setText(current.substring(0, current.length() - 1));
                    }
                    case "Enter" -> { if (closeAction != null) closeAction.run(); }
                    default -> {
                        if ("Password".equals(textField.getText())) textField.setText("");
                        textField.setText(textField.getText() + s);
                    }
                }
            });
            panel.add(button);
        }
        panel.setPreferredSize(new Dimension(300, 170));
        return panel;
    }

    public JPanel getPanelUsers() { return panelUsers; }
}