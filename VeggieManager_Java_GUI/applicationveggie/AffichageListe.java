/*     */ package applicationveggie;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ 
/*     */ public class AffichageListe extends JFrame {
/*  30 */   public static String FilePath = "/home/pi/tensorflow1/models/research/object_detection/factures/facture_affichage.txt";
/*  31 */   public File file = new File(FilePath);
/*  32 */   public double sousTotal = 0.0D; private JButton jButton1; private JButton jButton2;
/*     */   private JButton jButton3;
/*     */   private JButton jButton4;
/*     */   
/*     */   public AffichageListe() throws InterruptedException {
/*  37 */     String fileError = "/home/pi/tensorflow1/models/research/object_detection/erreur/erreur.txt";
/*  38 */     File f = new File(fileError);
/*  39 */     if (f.exists()) {
/*     */       
/*     */       try {
/*  42 */         TimeUnit.MILLISECONDS.sleep(200L);
/*  43 */       } catch (InterruptedException ex) {
/*  44 */         Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*  46 */       f.delete();
/*     */       try {
/*  48 */         TimeUnit.MILLISECONDS.sleep(200L);
/*  49 */       } catch (InterruptedException ex) {
/*  50 */         Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */       try {
/*  53 */         TimeUnit.MILLISECONDS.sleep(750L);
/*  54 */       } catch (InterruptedException ex) {
/*  55 */         Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*  57 */       setVisible(false);
/*  58 */       (new EcranWarning()).setVisible(true);
/*     */     }
/*     */     else {
/*     */       
/*  62 */       initComponents();
/*     */       try {
	//                    LIRE LE NOUVEAU TABLEAU ET GENERER UN TOTAL
/*  64 */         String chemin = "/home/pi/tensorflow1/models/research/object_detection/factures/facture_affichage.txt";
/*  65 */         TimeUnit.MILLISECONDS.sleep(400L);
/*  66 */         int i = 0;
/*  67 */         BufferedReader br = new BufferedReader(new FileReader(chemin));
/*  68 */         String firstLine = br.readLine().trim();
/*  69 */         String[] columnsName = firstLine.split("\t");
/*     */         
/*  71 */         DefaultTableModel model = (DefaultTableModel)this.jTable1.getModel();
/*  72 */         model.setColumnIdentifiers((Object[])columnsName);
/*     */         
/*  74 */         Object[] tableLines = br.lines().toArray();
/*     */         
/*  76 */         for (Object tableLine : tableLines) {
/*  77 */           String line = tableLine.toString().trim();
/*  78 */           String[] dataRow = line.split("\t");
/*  79 */           model.addRow((Object[])dataRow);
/*  80 */           i++;
/*     */         } 
/*     */         
/*  83 */         DecimalFormat df = new DecimalFormat("0.00");
/*  84 */         for (int j = 0; j < i; j++) {
/*  85 */           if (this.jTable1.getModel().getValueAt(j, 4) != null) {
/*  86 */             double tmp = Double.parseDouble((String)this.jTable1.getModel().getValueAt(j, 4));
/*  87 */             this.sousTotal += tmp;
/*  88 */             this.jLabel1.setText(String.valueOf(df.format(this.sousTotal)) + "€");
/*     */           }
/*     */         
/*     */         }
/*     */       
/*  93 */       } catch (IOException ex) {
/*  94 */         Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private JLabel jLabel1;
/*     */   
/*     */   private JLabel jLabel2;
/*     */   private JPanel jPanel1;
/*     */   private JScrollPane jScrollPane2;
/*     */   private JTable jTable1;
/*     */   
/*     */   private void initComponents() {
/* 108 */     this.jPanel1 = new JPanel();
/* 109 */     this.jButton2 = new JButton();
/* 110 */     this.jButton3 = new JButton();
/* 111 */     this.jButton1 = new JButton();
/* 112 */     this.jButton4 = new JButton();
/* 113 */     this.jLabel1 = new JLabel();
/* 114 */     this.jScrollPane2 = new JScrollPane();
/* 115 */     this.jTable1 = new JTable();
/* 116 */     this.jLabel2 = new JLabel();
/*     */     
/* 118 */     setDefaultCloseOperation(3);
/*     */     
/* 120 */     this.jPanel1.setLayout((LayoutManager)null);
/*     */     
/* 122 */     this.jButton2.setBorderPainted(false);
/* 123 */     this.jButton2.setContentAreaFilled(false);
/* 124 */     this.jButton2.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 126 */             AffichageListe.this.jButton2ActionPerformed(evt);
/*     */           }
/*     */         });
/* 129 */     this.jPanel1.add(this.jButton2);
/* 130 */     this.jButton2.setBounds(503, 330, 230, 110);
/*     */     
/* 132 */     this.jButton3.setBorderPainted(false);
/* 133 */     this.jButton3.setContentAreaFilled(false);
/* 134 */     this.jButton3.setFocusPainted(false);
/* 135 */     this.jButton3.setFocusable(false);
/* 136 */     this.jButton3.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 138 */             AffichageListe.this.jButton3ActionPerformed(evt);
/*     */           }
/*     */         });
/* 141 */     this.jPanel1.add(this.jButton3);
/* 142 */     this.jButton3.setBounds(80, 330, 220, 110);
/*     */     
/* 144 */     this.jButton1.setBorderPainted(false);
/* 145 */     this.jButton1.setContentAreaFilled(false);
/* 146 */     this.jButton1.setFocusable(false);
/* 147 */     this.jButton1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 149 */             AffichageListe.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/* 152 */     this.jPanel1.add(this.jButton1);
/* 153 */     this.jButton1.setBounds(70, 90, 240, 100);
/*     */     
/* 155 */     this.jButton4.setBorderPainted(false);
/* 156 */     this.jButton4.setContentAreaFilled(false);
/* 157 */     this.jButton4.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 159 */             AffichageListe.this.jButton4ActionPerformed(evt);
/*     */           }
/*     */         });
/* 162 */     this.jPanel1.add(this.jButton4);
/* 163 */     this.jButton4.setBounds(670, 30, 70, 80);
/*     */     
/* 165 */     this.jLabel1.setFont(new Font("Franklin Gothic Book", 1, 28));
/* 166 */     this.jPanel1.add(this.jLabel1);
/* 167 */     this.jLabel1.setBounds(630, 234, 140, 70);
/*     */     
/* 169 */     this.jTable1.setFont(new Font("Candara Light", 0, 14));
/* 170 */     this.jTable1.setModel(new DefaultTableModel(new Object[0][], (Object[])new String[0]));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     this.jTable1.setAutoResizeMode(4);
/* 179 */     this.jTable1.setGridColor(new Color(255, 255, 255));
/* 180 */     this.jTable1.setOpaque(false);
/* 181 */     this.jTable1.setSelectionBackground(new Color(240, 240, 240));
/* 182 */     this.jScrollPane2.setViewportView(this.jTable1);
/* 183 */     this.jTable1.getColumnModel().getSelectionModel().setSelectionMode(1);
/*     */     
/* 185 */     this.jPanel1.add(this.jScrollPane2);
/* 186 */     this.jScrollPane2.setBounds(330, 110, 400, 120);
/*     */     
/* 188 */     this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/applicationveggie/ecran_select.png")));
/* 189 */     this.jLabel2.setText("jLabel2");
/* 190 */     this.jPanel1.add(this.jLabel2);
/* 191 */     this.jLabel2.setBounds(0, 0, 800, 480);
/*     */     
/* 193 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 194 */     getContentPane().setLayout(layout);
/* 195 */     layout.setHorizontalGroup(layout
/* 196 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 197 */         .addComponent(this.jPanel1, -1, 800, 32767));
/*     */     
/* 199 */     layout.setVerticalGroup(layout
/* 200 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 201 */         .addComponent(this.jPanel1, -1, 480, 32767));
/*     */ 
/*     */     
/* 204 */     pack();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */       // GENERER LA FACTURE DATE DU CLIENT
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 211 */     SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
/* 212 */     Date date = new Date();
/* 213 */     System.out.println(formatter.format(date));
/*     */     
/* 215 */     File afile = new File(FilePath);
/* 216 */     if (afile.renameTo(new File("/home/pi/tensorflow1/models/research/object_detection/factures/archives/facture_of " + date))) {
/* 217 */       System.out.println("File is moved successful!");
/*     */     } else {
/*     */       
/* 220 */       System.out.println("File is failed to move!");
/*     */     } 
/*     */     
/* 223 */     setVisible(false);
/* 224 */     (new FinClient()).setVisible(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ //     ECRIRE DANS UN FICHIER QUAND LE CLIENT VALIDE => FLAG PYTHON
/*     */   
/*     */   private void jButton3ActionPerformed(ActionEvent evt) {
/* 232 */     FileWriter myWriter = null;
/*     */     try {
/* 234 */       myWriter = new FileWriter("/home/pi/tensorflow1/models/research/object_detection/ordres/filename.txt");
/* 235 */     } catch (IOException ex) {
/* 236 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     try {
/* 239 */       myWriter.write("1");
/* 240 */     } catch (IOException ex) {
/* 241 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     try {
/* 244 */       myWriter.close();
/* 245 */     } catch (IOException ex) {
/* 246 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 248 */     System.out.println("Successfully wrote to the file.");
/*     */     try {
/* 250 */       TimeUnit.MILLISECONDS.sleep(750L);
/* 251 */     } catch (InterruptedException ex) {
/* 252 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 254 */     System.out.println("WAIT 1 SEC DONE");
/*     */     try {
/* 256 */       (new AffichageListe()).setVisible(true);
/* 257 */     } catch (InterruptedException ex) {
/* 258 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 260 */     setVisible(false);
/*     */   }
/*     */ 
/*     */   // PASSAGE EN MODE ASSISTANCE OPERATEUR.
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 265 */     String fileError = "/home/pi/Pictures/ordresLed/led_orange.txt";
/* 266 */     File f = new File(fileError);
/*     */     
/* 268 */     if (f.exists()) {
/* 269 */       f.delete();
/*     */       try {
/* 271 */         TimeUnit.MILLISECONDS.sleep(500L);
/* 272 */       } catch (InterruptedException ex) {
/* 273 */         Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */     } 
/* 276 */     FileWriter myWriter = null;
/*     */     try {
/* 278 */       myWriter = new FileWriter("/home/pi/Pictures/ordresLed/led_red.txt");
/* 279 */     } catch (IOException ex) {
/* 280 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     try {
/* 283 */       myWriter.write("1");
/* 284 */     } catch (IOException ex) {
/* 285 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     try {
/* 288 */       myWriter.close();
/* 289 */     } catch (IOException ex) {
/* 290 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 292 */     System.out.println("Successfully wrote to the file.");
/*     */     try {
/* 294 */       TimeUnit.MILLISECONDS.sleep(500L);
/* 295 */     } catch (InterruptedException ex) {
/* 296 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 298 */     (new GestionErreur()).setVisible(true);
/* 299 */     setVisible(false);
/*     */   }
/*     */ 
/*     */ 
//              
/*     */   private void jButton4ActionPerformed(ActionEvent evt) {
/* 304 */     FileWriter myWriter = null;
/*     */     try {
/* 306 */       myWriter = new FileWriter("/home/pi/tensorflow1/models/research/object_detection/ordres/endfile.txt");
/* 307 */     } catch (IOException ex) {
/* 308 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     try {
/* 311 */       myWriter.write("1");
/* 312 */     } catch (IOException ex) {
/* 313 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     try {
/* 316 */       myWriter.close();
/* 317 */     } catch (IOException ex) {
/* 318 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 320 */     System.out.println("Successfully wrote to the file.");
/*     */     try {
/* 322 */       TimeUnit.SECONDS.sleep(1L);
/* 323 */     } catch (InterruptedException ex) {
/* 324 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     try {
/* 327 */       (new AffichageListe()).setVisible(true);
/* 328 */     } catch (InterruptedException ex) {
/* 329 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 331 */     setVisible(false);
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
/* 344 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 345 */         if ("Nimbus".equals(info.getName())) {
/* 346 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 350 */     } catch (ClassNotFoundException ex) {
/* 351 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 352 */     } catch (InstantiationException ex) {
/* 353 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 354 */     } catch (IllegalAccessException ex) {
/* 355 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 356 */     } catch (UnsupportedLookAndFeelException ex) {
/* 357 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
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
/* 393 */     EventQueue.invokeLater(() -> {
/*     */           try {
/*     */             (new AffichageListe()).setVisible(true);
/* 396 */           } catch (InterruptedException ex) {
/*     */             Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */           } 
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\hamza\Documents\NetBeansProjects\ApplicationVeggie\dist\ApplicationVeggie.jar!\applicationveggie\AffichageListe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */