/*     */ package applicationveggie;
/*     */ 
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
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
/*     */ public class EcranAllezy
/*     */   extends JFrame {
/*     */   public EcranAllezy() {
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
/*  45 */             EcranAllezy.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/*  48 */     this.jPanel1.add(this.jButton1);
/*  49 */     this.jButton1.setBounds(10, 10, 780, 460);
/*     */     
/*  51 */     this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/applicationveggie/posez_dans_panier.png")));
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
/*     */     try {
/*  72 */       (new AffichageListe()).setVisible(true);
/*  73 */     } catch (InterruptedException ex) {
/*  74 */       Logger.getLogger(EcranAllezy.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*  76 */     setVisible(false);
/*  77 */     System.out.println("ECRAN D'AMORCAGE AVANT LISTE");
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
/*  91 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/*  92 */         if ("Nimbus".equals(info.getName())) {
/*  93 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/*  97 */     } catch (ClassNotFoundException ex) {
/*  98 */       Logger.getLogger(EcranAllezy.class.getName()).log(Level.SEVERE, (String)null, ex);
/*  99 */     } catch (InstantiationException ex) {
/* 100 */       Logger.getLogger(EcranAllezy.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 101 */     } catch (IllegalAccessException ex) {
/* 102 */       Logger.getLogger(EcranAllezy.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 103 */     } catch (UnsupportedLookAndFeelException ex) {
/* 104 */       Logger.getLogger(EcranAllezy.class.getName()).log(Level.SEVERE, (String)null, ex);
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
/* 124 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 126 */             (new EcranAllezy()).setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\hamza\Documents\NetBeansProjects\ApplicationVeggie\dist\ApplicationVeggie.jar!\applicationveggie\EcranAllezy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */