/*     */ package applicationveggie;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class FinClient extends JFrame {
/*     */   public FinClient() {
/*  21 */     initComponents();
/*     */   }
/*     */ 
/*     */   
/*     */   private JButton jButton1;
/*     */   
/*     */   private JLabel jLabel2;
/*     */   
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   
/*     */   private void initComponents() {
/*  33 */     this.jPanel1 = new JPanel();
/*  34 */     this.jButton1 = new JButton();
/*  35 */     this.jLabel2 = new JLabel();
/*     */     
/*  37 */     setDefaultCloseOperation(3);
/*     */     
/*  39 */     this.jPanel1.setLayout((LayoutManager)null);
/*     */     
/*  41 */     this.jButton1.setBorderPainted(false);
/*  42 */     this.jButton1.setContentAreaFilled(false);
/*  43 */     this.jButton1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  45 */             FinClient.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/*  48 */     this.jPanel1.add(this.jButton1);
/*  49 */     this.jButton1.setBounds(10, 0, 780, 460);
/*     */     
/*  51 */     this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/applicationveggie/goodbye_screen.png")));
/*  52 */     this.jLabel2.setText("jLabel2");
/*  53 */     this.jPanel1.add(this.jLabel2);
/*  54 */     this.jLabel2.setBounds(0, 0, 810, 480);
/*     */     
/*  56 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  57 */     getContentPane().setLayout(layout);
/*  58 */     layout.setHorizontalGroup(layout
/*  59 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  60 */         .addComponent(this.jPanel1, -1, 800, 32767));
/*     */     
/*  62 */     layout.setVerticalGroup(layout
/*  63 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  64 */         .addComponent(this.jPanel1, -1, 480, 32767));
/*     */ 
/*     */     
/*  67 */     pack();
/*     */   }
/*     */   
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/*  71 */     System.out.println("WAITING 2 SEC TO NEXT SESSION");
/*  72 */     FileWriter myWriter = null;
/*     */     try {
/*  74 */       myWriter = new FileWriter("/home/pi/tensorflow1/models/research/object_detection/ordres/endfile.txt");
/*  75 */     } catch (IOException ex) {
/*  76 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     try {
/*  79 */       myWriter.write("1");
/*  80 */     } catch (IOException ex) {
/*  81 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     try {
/*  84 */       myWriter.close();
/*  85 */     } catch (IOException ex) {
/*  86 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*  88 */     System.out.println("Successfully wrote to the file.");
/*     */     try {
/*  90 */       TimeUnit.MILLISECONDS.sleep(200L);
/*  91 */     } catch (InterruptedException ex) {
/*  92 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*  94 */     System.out.println("WAIT 1 SEC DONE");
/*     */     try {
/*  96 */       TimeUnit.MILLISECONDS.sleep(1000L);
/*  97 */     } catch (InterruptedException ex) {
/*  98 */       Logger.getLogger(FinClient.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 100 */     System.out.println("WAITING DONE, NEW SESSION LAUNCHED");
/* 101 */     setVisible(false);
/* 102 */     (new EcranPrincipal()).setVisible(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 115 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 116 */         if ("Nimbus".equals(info.getName())) {
/* 117 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 121 */     } catch (ClassNotFoundException ex) {
/* 122 */       Logger.getLogger(FinClient.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 123 */     } catch (InstantiationException ex) {
/* 124 */       Logger.getLogger(FinClient.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 125 */     } catch (IllegalAccessException ex) {
/* 126 */       Logger.getLogger(FinClient.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 127 */     } catch (UnsupportedLookAndFeelException ex) {
/* 128 */       Logger.getLogger(FinClient.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 388 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 390 */             (new FinClient()).setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\hamza\Documents\NetBeansProjects\ApplicationVeggie\dist\ApplicationVeggie.jar!\applicationveggie\FinClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */