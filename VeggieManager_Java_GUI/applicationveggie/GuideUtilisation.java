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
/*     */ public class GuideUtilisation extends JFrame {
/*     */   private JButton jButton1;
/*     */   
/*     */   public GuideUtilisation() {
/*  22 */     initComponents();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private JLabel jLabel2;
/*     */ 
/*     */   
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   
/*     */   private void initComponents() {
/*  34 */     this.jPanel1 = new JPanel();
/*  35 */     this.jButton1 = new JButton();
/*  36 */     this.jLabel2 = new JLabel();
/*     */     
/*  38 */     setDefaultCloseOperation(3);
/*     */     
/*  40 */     this.jPanel1.setLayout((LayoutManager)null);
/*     */     
/*  42 */     this.jButton1.setBorderPainted(false);
/*  43 */     this.jButton1.setContentAreaFilled(false);
/*  44 */     this.jButton1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/*  46 */             GuideUtilisation.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/*  49 */     this.jPanel1.add(this.jButton1);
/*  50 */     this.jButton1.setBounds(3, 3, 790, 470);
/*     */     
/*  52 */     this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/applicationveggie/guide.png")));
/*  53 */     this.jLabel2.setText("jLabel2");
/*  54 */     this.jPanel1.add(this.jLabel2);
/*  55 */     this.jLabel2.setBounds(0, 0, 800, 480);
/*     */     
/*  57 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  58 */     getContentPane().setLayout(layout);
/*  59 */     layout.setHorizontalGroup(layout
/*  60 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  61 */         .addComponent(this.jPanel1, -1, 800, 32767));
/*     */     
/*  63 */     layout.setVerticalGroup(layout
/*  64 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  65 */         .addComponent(this.jPanel1, -1, 480, 32767));
/*     */ 
/*     */     
/*  68 */     pack();
/*     */   }
/*     */   
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/*  72 */     (new EcranAllezy()).setVisible(true);
/*  73 */     System.out.println("GUIDE D'UTILISATION");
/*  74 */     setVisible(false);
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
/*  87 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/*  88 */         if ("Nimbus".equals(info.getName())) {
/*  89 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/*  93 */     } catch (ClassNotFoundException ex) {
/*  94 */       Logger.getLogger(GuideUtilisation.class.getName()).log(Level.SEVERE, (String)null, ex);
/*  95 */     } catch (InstantiationException ex) {
/*  96 */       Logger.getLogger(GuideUtilisation.class.getName()).log(Level.SEVERE, (String)null, ex);
/*  97 */     } catch (IllegalAccessException ex) {
/*  98 */       Logger.getLogger(GuideUtilisation.class.getName()).log(Level.SEVERE, (String)null, ex);
/*  99 */     } catch (UnsupportedLookAndFeelException ex) {
/* 100 */       Logger.getLogger(GuideUtilisation.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 110 */             (new GuideUtilisation()).setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\hamza\Documents\NetBeansProjects\ApplicationVeggie\dist\ApplicationVeggie.jar!\applicationveggie\GuideUtilisation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */