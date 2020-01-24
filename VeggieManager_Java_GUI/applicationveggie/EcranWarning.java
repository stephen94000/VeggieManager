/*     */ package applicationveggie;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
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
/*     */ public class EcranWarning extends JFrame {
/*     */   public EcranWarning() {
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
/*  45 */             EcranWarning.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/*  48 */     this.jPanel1.add(this.jButton1);
/*  49 */     this.jButton1.setBounds(10, 0, 780, 460);
/*     */     
/*  51 */     this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/applicationveggie/ecran_erreur.png")));
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
/*  71 */     String fileError = "/home/pi/Pictures/ordresLed/led_orange.txt";
/*  72 */     File f = new File(fileError);
/*     */     
/*  74 */     if (f.exists()) {
/*  75 */       f.delete();
/*     */       try {
/*  77 */         TimeUnit.MILLISECONDS.sleep(200L);
/*  78 */       } catch (InterruptedException ex) {
/*  79 */         Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */     } 
/*  82 */     FileWriter myWriter = null;
/*     */     try {
/*  84 */       myWriter = new FileWriter("/home/pi/Pictures/ordresLed/led_red.txt");
/*  85 */     } catch (IOException ex) {
/*  86 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     try {
/*  89 */       myWriter.write("1");
/*  90 */     } catch (IOException ex) {
/*  91 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     try {
/*  94 */       myWriter.close();
/*  95 */     } catch (IOException ex) {
/*  96 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*  98 */     System.out.println("Successfully wrote to the file.");
/*     */     try {
/* 100 */       TimeUnit.MILLISECONDS.sleep(200L);
/* 101 */     } catch (InterruptedException ex) {
/* 102 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     
/* 105 */     setVisible(false);
/* 106 */     (new GestionErreur()).setVisible(true);
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
/* 119 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 120 */         if ("Nimbus".equals(info.getName())) {
/* 121 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 125 */     } catch (ClassNotFoundException ex) {
/* 126 */       Logger.getLogger(EcranWarning.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 127 */     } catch (InstantiationException ex) {
/* 128 */       Logger.getLogger(EcranWarning.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 129 */     } catch (IllegalAccessException ex) {
/* 130 */       Logger.getLogger(EcranWarning.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 131 */     } catch (UnsupportedLookAndFeelException ex) {
/* 132 */       Logger.getLogger(EcranWarning.class.getName()).log(Level.SEVERE, (String)null, ex);
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
/* 264 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 266 */             (new EcranWarning()).setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\hamza\Documents\NetBeansProjects\ApplicationVeggie\dist\ApplicationVeggie.jar!\applicationveggie\EcranWarning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */