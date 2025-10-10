package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import User.*;

/**
 *
 * @author Admin
 */
public class Register extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());
    AuthService authService;
    private JWindow keyboardWindow;
    private boolean shiftOn = false;
    private boolean capsOn = false;
    private Login loginRef;
    

    /**
     * Creates new form Login
     */
    public Register(Login login) {
        initComponents();
         this.loginRef = login;

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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> setVisible(true));


        // สร้าง JPopupMenu สำหรับ Numpad
        JPopupMenu numpadPopup = new JPopupMenu();
        numpadPopup.setFocusable(false);

        JPanel numpadPanel = createNumpad(jTextFieldPW, () -> numpadPopup.setVisible(false));
        numpadPanel.setPreferredSize(new Dimension(200, 200)); // กำหนดขนาดแน่นอน
        numpadPopup.add(numpadPanel);

        // แสดง popup ด้านซ้ายของ textField
        jTextFieldPW .setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextFieldPW.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldPW.setText("Password");
        jTextFieldPW.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextFieldPW.getText().equals("Password")) {
                    jTextFieldPW.setText(""); // ลบข้อความเมื่อโฟกัส
                }
                int x = -numpadPanel.getPreferredSize().width; // ซ้ายของ textField
                int y = 0; // ให้ top เท่ากับ textField
                numpadPopup.show(jTextFieldPW, x, y);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (jTextFieldPW.getText().isEmpty()) {
                    jTextFieldPW.setText("Password"); // กลับมาเป็นข้อความเริ่มต้นถ้าว่าง
                }
            }
        });

        // สร้าง JPopupMenu สำหรับ Numpad
        JPopupMenu numpadPopup1 = new JPopupMenu();
        numpadPopup1.setFocusable(false);

        JPanel numpadPane2 = createNumpad(jTextFieldCPW, () -> numpadPopup1.setVisible(false));
        numpadPane2.setPreferredSize(new Dimension(200, 200)); // กำหนดขนาดแน่นอน
        numpadPopup1.add(numpadPane2);

        // แสดง popup ด้านซ้ายของ textField
        jTextFieldCPW .setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextFieldCPW.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldCPW.setText("Password");
        jTextFieldCPW.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextFieldCPW.getText().equals("Password")) {
                    jTextFieldCPW.setText(""); // ลบข้อความเมื่อโฟกัส
                }
                int x = -numpadPane2.getPreferredSize().width; // ซ้ายของ textField
                int y = 0; // ให้ top เท่ากับ textField
                numpadPopup1.show(jTextFieldCPW, x, y);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (jTextFieldCPW.getText().isEmpty()) {
                    jTextFieldCPW.setText("Password"); // กลับมาเป็นข้อความเริ่มต้นถ้าว่าง
                }
            }
        });
    /*
     createFloatingKeyboard(jTextFieldID);

jTextFieldID.addFocusListener(new FocusAdapter() {
    @Override
    public void focusGained(FocusEvent e) {
        if (!keyboardWindow.isVisible()) {
            keyboardWindow.setVisible(true);
        }
    }
});
     */
     // สร้าง Floating Keyboard ก่อน
createFloatingKeyboard(jTextFieldID);

// แสดง Keyboard ตอน TextField ได้ focus
jTextFieldID.addFocusListener(new FocusAdapter() {
    @Override
    public void focusGained(FocusEvent e) {
        if (keyboardWindow != null && !keyboardWindow.isVisible()) {
            keyboardWindow.setVisible(true);
        }
    }
});


     
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanelMain = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTextFieldCPW = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldPW = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        jButton3.setBackground(new java.awt.Color(255, 189, 89));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jButton3.setText("Login");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);
        setUndecorated(true);

        jPanelMain.setBackground(new java.awt.Color(0, 0, 0));
        jPanelMain.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanelMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                jPanelMain.requestFocusInWindow(); // ให้ panel รับ focus → TextField หลุด focus
            }
        });

        //pack();
        //setExtendedState(JFrame.MAXIMIZED_BOTH);

        jPanel1.setBackground(new java.awt.Color(255, 145, 77));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel1.setPreferredSize(new java.awt.Dimension(1880, 250));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 72)); // NOI18N
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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel3.setText("--------------------------- OR ---------------------------");

        jButton2.setBackground(new java.awt.Color(255, 189, 89));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jButton2.setText("Register");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jButton1.setText("Login");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel3)
                .addGap(50, 50, 50)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel3.setPreferredSize(new java.awt.Dimension(1130, 698));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 66)); // NOI18N
        jLabel2.setText("Register");

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.setPreferredSize(new java.awt.Dimension(60, 60));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );

        jTextFieldID.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextFieldID.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldID.setText("User ID");
        jTextFieldID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIDFocusLost(evt);
            }
        });
        jTextFieldID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel4.setText("User ID :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel5.setText("Password :");

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel5.setPreferredSize(new java.awt.Dimension(60, 60));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );

        jTextFieldCPW.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextFieldCPW.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldCPW.setText("Password");
        jTextFieldCPW.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCPWFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCPWFocusLost(evt);
            }
        });
        jTextFieldCPW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCPWActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel6.setPreferredSize(new java.awt.Dimension(60, 60));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel6.setText("Comfirm Password :");

        jTextFieldPW.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextFieldPW.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldPW.setText("Password");
        jTextFieldPW.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPWFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPWFocusLost(evt);
            }
        });
        jTextFieldPW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPWActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 189, 89));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jButton4.setText("Register");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPW, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCPW, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(jLabel2)))
                .addContainerGap(205, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(421, 421, 421))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldCPW, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextFieldPW, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                        .addGap(3, 3, 3)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>  

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Login Main
        this.dispose();
    }   

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Register Main
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }      
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Register pass go to login

        UserRepository repo = new UserRepository();
        authService = new AuthService(repo);

        String userID = jTextFieldID.getText();
        String password = new String(jTextFieldPW.getText());
        String Cpassword = new String(jTextFieldCPW.getText());

        if (authService.register(userID, password , Cpassword)) {
            this.setVisible(false);
            this.dispose(); // ปิดหน้าต่าง Register
        } else {
            jTextFieldID.setText("");
            jTextFieldPW.setText("");
            jTextFieldCPW.setText("");
            jTextFieldID.requestFocus();
        }                     
    }  

    private void jTextFieldIDActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextFieldCPWActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void jTextFieldPWActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                                                                

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void jTextFieldIDFocusGained(java.awt.event.FocusEvent evt) {                                         
        if (jTextFieldID.getText().equals("UserName")) {
            jTextFieldID.setText(""); // ลบข้อความเมื่อโฟกัส
        }
        keyboardWindow.setVisible(true);
    }                                        

    private void jTextFieldIDFocusLost(java.awt.event.FocusEvent evt) {                                       
        if (jTextFieldID.getText().isEmpty()) {
            jTextFieldID.setText("UserName"); // กลับมาเป็นข้อความเริ่มต้นถ้าว่าง
        }
        keyboardWindow.setVisible(false);
    }                                      

    private void jTextFieldPWFocusGained(java.awt.event.FocusEvent evt) {                                         
        if (jTextFieldPW.getText().equals("Password")) {
            jTextFieldPW.setText(""); // ลบข้อความเมื่อโฟกัส
        }
    }                                        

    private void jTextFieldPWFocusLost(java.awt.event.FocusEvent evt) {                                       
        if (jTextFieldPW.getText().isEmpty()) {
            jTextFieldPW.setText("Password"); // กลับมาเป็นข้อความเริ่มต้นถ้าว่าง
        }
    }                                      

    private void jTextFieldCPWFocusGained(java.awt.event.FocusEvent evt) {                                          
        if (jTextFieldCPW.getText().equals("Password")) {
            jTextFieldCPW.setText(""); // ลบข้อความเมื่อโฟกัส
        }
    }                                         

    private void jTextFieldCPWFocusLost(java.awt.event.FocusEvent evt) {                                        
        if (jTextFieldCPW.getText().isEmpty()) {
            jTextFieldCPW.setText("Password"); // กลับมาเป็นข้อความเริ่มต้นถ้าว่าง
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JTextField jTextFieldCPW;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldPW;
    // End of variables declaration    
    
    
    public static JPanel createNumpad(JTextField textField , Runnable closeAction) {
        JPanel panel = new JPanel(new GridLayout(4,3,0,0)); // 4 แถว 3 คอลัมน์
        String[] buttons = {
            "1","2","3",
            "4","5","6",
            "7","8","9",
            "Del","0","Enter"
        };

        for (String s : buttons) {
            JButton button = new JButton(s);
            button.setFont(new Font("Segoe UI", 1, 15));
            button.setPreferredSize(new Dimension(50, 40));
            button.addActionListener(e -> {
                switch(s) {
                    case "Del":
                        String current = textField.getText();
                        if (!current.isEmpty()) {
                            textField.setText(current.substring(0, current.length() - 1));
                        }
                        break;
                    case "Enter":
                        if (closeAction != null) {
                            closeAction.run(); // เรียก Runnable ปิด popup
                        }
                        break;
                    default: // 0-9
                        if ("Password".equals(textField.getText())) {
                            textField.setText(""); // ล้าง Password ก่อน
                        }
                        textField.setText(textField.getText() + s);
                        break;
                }
            });
            panel.add(button);
        }
        panel.setPreferredSize(new Dimension(300, 170));

        return panel;
    }

   private void createFloatingKeyboard(JTextField target) {
    keyboardWindow = new JWindow(this);
    keyboardWindow.setSize(700, 280);
    keyboardWindow.setLocation(
        getX() + (getWidth() - 700) / 2,
        (getY() + getHeight() - 280) - 30
    );

    JPanel keyboardPanel = createKeyboard(target, () -> keyboardWindow.setVisible(false));
    keyboardWindow.getContentPane().add(keyboardPanel);
    keyboardWindow.pack(); // 💡 บังคับให้จัด layout และขนาดใหม่

    keyboardWindow.setVisible(false); // เริ่มต้นซ่อน

    target.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (keyboardWindow != null) {
            keyboardWindow.setVisible(true); // 💡 แสดง keyboard ตอนคลิก
            keyboardWindow.toFront();         // ให้ลอยบนสุด
        }
    }
});

}



    public JPanel createKeyboard(JTextField target, Runnable closeAction) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(700, 280));
        panel.setBackground(new Color(40, 40, 40));

        String[] row1 = {"1","2","3","4","5","6","7","8","9","0","Backspace"};
        String[] row2 = {"Tab","Q","W","E","R","T","Y","U","I","O","P", "/"};
        String[] row3 = {"Caps","A","S","D","F","G","H","J","K","L","Enter"};
        String[] row4 = {"Shift","Z","X","C","V","B","N","M","-","_","Shift"};
        String[] row5 = {"  "}; // Space

        panel.add(createRow(row1, target, closeAction));
        panel.add(createRow(row2, target, closeAction));
        panel.add(createRow(row3, target, closeAction));
        panel.add(createRow(row4, target, closeAction));
        panel.add(createRow(row5, target, closeAction));

        return panel;
    }

    private JPanel createRow(String[] keys, JTextField target, Runnable closeAction) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        row.setBackground(new Color(40, 40, 40));

        for (String k : keys) {
            JButton button = new JButton(k);
            button.setFont(new Font("Segoe UI", Font.BOLD, 16));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(70, 70, 70));
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
            button.setPreferredSize(getKeySize(k));

            // เอฟเฟกต์ hover
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(100, 100, 100));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(70, 70, 70));
                }
            });

            // ✅ ใช้โค้ดที่คุณให้มา
            button.addActionListener(e -> {
                String text = target.getText();
                switch (k) {
                    case "Backspace":
                        if (!text.isEmpty()) {
                            target.setText(text.substring(0, text.length() - 1));
                        }
                        break;
                    case "Enter":
                        closeAction.run();
                        break;
                    case "Shift":
                        shiftOn = !shiftOn;
                        button.setBackground(shiftOn ? new Color(120, 120, 120) : new Color(70, 70, 70));
                        break;
                    case "Caps":
                        capsOn = !capsOn;
                        button.setBackground(capsOn ? new Color(120, 120, 120) : new Color(70, 70, 70));
                        break;
                    case "  ":
                        target.setText(text + " ");
                        break;
                    default:
                        String ch = k;
                        if (ch.length() == 1 && Character.isLetter(ch.charAt(0))) {
                            if (shiftOn ^ capsOn) {
                                ch = ch.toUpperCase();
                            } else {
                                ch = ch.toLowerCase();
                            }
                        }
                        target.setText(text + ch);
                        if (shiftOn) {
                            shiftOn = false;
                            // ปิดสี Shift หลังใช้
                            // หา Shift ปุ่มอื่นแล้วรีเซ็ตได้ด้วยถ้ามีหลายปุ่ม
                            button.setBackground(new Color(70, 70, 70));
                        }
                        break;
                }
            });

            row.add(button);
        }

        return row;
    }

    private Dimension getKeySize(String key) {
        switch (key) {
            case "Backspace": return new Dimension(100, 50);
            case "Enter": return new Dimension(100, 50);
            case "Shift": return new Dimension(80, 50);
            case "  ": return new Dimension(400, 50); // Space
            case "Caps": return new Dimension(80, 50);
            default: return new Dimension(50, 50);
        }
    }
    
    
    
     
    
    
}