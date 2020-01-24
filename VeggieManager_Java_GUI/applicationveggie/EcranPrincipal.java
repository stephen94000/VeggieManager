/*     */ package applicationveggie;
/*     */ 
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class EcranPrincipal
/*     */   extends JFrame {
/*     */   public EcranPrincipal() {
/*  25 */     initComponents();
/*     */   }
/*     */ 
/*     */   
/*     */   private JButton jButton1;
/*     */   
/*     */   private JLabel jLabel1;
/*     */   
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   
/*     */   private void initComponents() {
/*  37 */     this.jPanel1 = new JPanel();
/*  38 */     this.jButton1 = new JButton();
/*  39 */     this.jLabel1 = new JLabel();
/*     */     
/*  41 */     setDefaultCloseOperation(3);
/*     */     
/*  43 */     this.jPanel1.setLayout((LayoutManager)null);
/*     */     
/*  45 */     this.jButton1.setBorderPainted(false);
/*  46 */     this.jButton1.setContentAreaFilled(false);
/*  47 */     this.jButton1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  49 */             EcranPrincipal.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/*  52 */     this.jPanel1.add(this.jButton1);
/*  53 */     this.jButton1.setBounds(10, 10, 780, 460);
/*     */     
/*  55 */     this.jLabel1.setIcon(new ImageIcon(getClass().getResource("/applicationveggie/hello_screen.png")));
/*  56 */     this.jLabel1.setText("jLabel1");
/*  57 */     this.jPanel1.add(this.jLabel1);
/*  58 */     this.jLabel1.setBounds(0, 0, 830, 480);
/*     */     
/*  60 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  61 */     getContentPane().setLayout(layout);
/*  62 */     layout.setHorizontalGroup(layout
/*  63 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  64 */         .addComponent(this.jPanel1, -1, 800, 32767));
/*     */     
/*  66 */     layout.setVerticalGroup(layout
/*  67 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  68 */         .addComponent(this.jPanel1, -1, 480, 32767));
/*     */ 
/*     */     
/*  71 */     pack();
/*     */   }
/*     */   
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/*  75 */     String fileError = "/home/pi/Pictures/ordresLed/led_green.txt";
/*  76 */     File f = new File(fileError);
/*     */     
/*  78 */     if (f.exists()) {
/*  79 */       f.delete();
/*     */       try {
/*  81 */         TimeUnit.MILLISECONDS.sleep(200L);
/*  82 */       } catch (InterruptedException ex) {
/*  83 */         Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */     } 
/*     */     
/*  87 */     FileWriter myWriter = null;
/*     */     try {
/*  89 */       myWriter = new FileWriter("/home/pi/Pictures/ordresLed/led_orange.txt");
/*  90 */     } catch (IOException ex) {
/*  91 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     try {
/*  94 */       myWriter.write("1");
/*  95 */     } catch (IOException ex) {
/*  96 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     try {
/*  99 */       myWriter.close();
/* 100 */     } catch (IOException ex) {
/* 101 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 103 */     System.out.println("Successfully wrote to the file.");
/*     */     try {
/* 105 */       TimeUnit.MILLISECONDS.sleep(200L);
/* 106 */     } catch (InterruptedException ex) {
/* 107 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 109 */     System.out.println("Successfully wrote to the file.");
/*     */     try {
/* 111 */       TimeUnit.MILLISECONDS.sleep(200L);
/* 112 */     } catch (InterruptedException ex) {
/* 113 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 115 */     (new GuideUtilisation()).setVisible(true);
/* 116 */     System.out.println("SCRIPT NEURAL NET LAUNCHED");
/* 117 */     System.out.println("ECRAN PRINCIPAL");
/* 118 */     setVisible(false);
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
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 132 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 133 */         if ("Nimbus".equals(info.getName())) {
/* 134 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 138 */     } catch (ClassNotFoundException ex) {
/* 139 */       Logger.getLogger(EcranPrincipal.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 140 */     } catch (InstantiationException ex) {
/* 141 */       Logger.getLogger(EcranPrincipal.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 142 */     } catch (IllegalAccessException ex) {
/* 143 */       Logger.getLogger(EcranPrincipal.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 144 */     } catch (UnsupportedLookAndFeelException ex) {
/* 145 */       Logger.getLogger(EcranPrincipal.class.getName()).log(Level.SEVERE, (String)null, ex);
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
/* 157 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 159 */             (new EcranPrincipal()).setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\hamza\Documents\NetBeansProjects\ApplicationVeggie\dist\ApplicationVeggie.jar!\applicationveggie\EcranPrincipal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */