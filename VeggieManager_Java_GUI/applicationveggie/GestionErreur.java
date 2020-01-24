/*     */ package applicationveggie;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Font;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ 
/*     */ public class GestionErreur
/*     */   extends JFrame {
/*  38 */   public static String FilePath = "/home/pi/tensorflow1/models/research/object_detection/factures/facture_affichage.txt";
/*  39 */   public File file = new File(FilePath);
/*  40 */   public int Avocats = 0;
/*     */   public int Broccoli;
/*  42 */   public int Carottes = 0;
/*  43 */   public int Pomme = 0;
/*  44 */   public double Bananes = 0.0D; public int AvocatsIndex; public int BananesIndex; public int PommeIndex;
/*     */   public int BroccoliIndex;
/*  46 */   public int CarottesIndex = 0;
/*  47 */   public double AvocatsPrice = 1.49D;
/*  48 */   public double BananesPrice = 1.67D;
/*  49 */   public double PommePrice = 1.05D;
/*  50 */   public double BroccoliPrice = 0.99D;
/*  51 */   public double CarottesPrice = 2.0D; public double AvocatsPriceTTC; public double BananesPriceTTC;
/*  52 */   public double CarottesPriceTTC = 0.0D; public double PommePriceTTC; public double BroccoliPriceTTC;
/*  53 */   public String AvocatsType = "piece";
/*  54 */   public String BananesType = "g";
/*  55 */   public String PommeType = "piece";
/*  56 */   public String BroccoliType = "piece";
/*  57 */   public String CarottesType = "g";
/*  58 */   public double PrixTotal = 0.0D;
/*  59 */   public int pesees = 0;
/*  60 */   public double kgpesees = 0.0D; private JButton ajoutPomme; private JButton jButton6; private JButton jButton7; private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   
/*     */   public GestionErreur() {
/*  66 */     initComponents();
/*     */ 
/*     */     
/*  69 */     int i = 0;
/*     */     // CHARGER LE TABLEAU AVANT ERREUR
/*     */     try {
/*  72 */       BufferedReader br = new BufferedReader(new FileReader(FilePath));
/*  73 */       String firstLine = br.readLine().trim();
/*  74 */       String[] columnsName = firstLine.split("\t");
/*     */       
/*  76 */       DefaultTableModel model = (DefaultTableModel)this.jTable1.getModel();
/*  77 */       model.setColumnIdentifiers((Object[])columnsName);
/*     */       
/*  79 */       Object[] tableLines = br.lines().toArray();
/*     */       
/*  81 */       for (Object tableLine : tableLines) {
/*  82 */         String line = tableLine.toString().trim();
/*  83 */         String[] dataRow = line.split("\t");
/*  84 */         model.addRow((Object[])dataRow);
/*  85 */         i++;
/*     */       } 
/*  87 */     } catch (IOException ex) {
/*  88 */       Logger.getLogger(GestionErreur.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */     
/*  91 */     System.out.println("Nb lignes dans le tableau : " + i);
/*     */     
/*  93 */     for (int j = 0; j < i; j++) {
/*  94 */       System.out.println("STEP : " + j);
/*  95 */       System.out.println("DETECTED : " + this.jTable1.getModel().getValueAt(j, 2));
/*  96 */       if (this.jTable1.getModel().getValueAt(j, 2).equals("Avocat")) {
/*  97 */         this.Avocats = Integer.parseInt((String)this.jTable1.getModel().getValueAt(j, 0));
/*  98 */         this.AvocatsIndex = j;
/*  99 */         System.out.println("GET AVOCAT");
/*     */         
/* 101 */         System.out.println("Type de vente : " + this.AvocatsType);
/*     */       } 
/* 103 */       if (this.jTable1.getModel().getValueAt(j, 2).equals("brocoli")) {
/* 104 */         this.Broccoli = Integer.parseInt((String)this.jTable1.getModel().getValueAt(j, 0));
/* 105 */         this.BroccoliIndex = j;
/* 106 */         System.out.println("GET BROCCOLI");
/*     */         
/* 108 */         System.out.println("Type de vente : " + this.BroccoliType);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 113 */       if (this.jTable1.getModel().getValueAt(j, 2).equals("pomme")) {
/* 114 */         this.Pomme = Integer.parseInt((String)this.jTable1.getModel().getValueAt(j, 0));
/* 115 */         this.PommeIndex = j;
/* 116 */         System.out.println("GET POMME");
/*     */         
/* 118 */         System.out.println("Type de vente : " + this.PommeType);
/*     */       } 
/* 120 */       if (this.jTable1.getModel().getValueAt(j, 2).equals("carotte")) {
/* 121 */         this.Carottes = Integer.parseInt((String)this.jTable1.getModel().getValueAt(j, 0));
/* 122 */         this.CarottesIndex = j;
/* 123 */         System.out.println("GET CARROTES");
/*     */         
/* 125 */         System.out.println("Type de vente : " + this.CarottesType);
/*     */       } 
/*     */     } 
/*     */     
/* 129 */     this.jLabel3.setText(String.valueOf(this.Broccoli));
/* 130 */     this.jLabel1.setText(String.valueOf(this.Bananes));
/* 131 */     this.jLabel4.setText(String.valueOf(this.Pomme));
/*     */     
/* 133 */     this.AvocatsPriceTTC = this.Avocats * this.AvocatsPrice;
/* 134 */     this.BananesPriceTTC = this.Bananes * this.BananesPrice;
/* 135 */     this.PommePriceTTC = this.Pomme * this.PommePrice;
/* 136 */     this.BroccoliPriceTTC = this.Broccoli * this.BroccoliPrice;
/*     */     
/* 138 */     this.PrixTotal = this.PrixTotal + this.BananesPriceTTC + this.PommePriceTTC + this.BroccoliPriceTTC;
/* 139 */     if (this.PrixTotal <= 0.0D) {
/* 140 */       this.PrixTotal = 0.0D;
/*     */     }
/*     */     
/* 143 */     DecimalFormat pricetotal = new DecimalFormat("0.00");
/* 144 */     this.jLabel6.setText(String.valueOf(pricetotal.format(this.PrixTotal)) + "€");
/*     */     
/* 146 */     System.out.println("Nb avocats : " + this.Avocats);
/* 147 */     System.out.println("Nb Bananes : " + this.Bananes);
/* 148 */     System.out.println("Nb Pomme: " + this.Pomme);
/*     */   }
/*     */ 
/*     */   
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JPanel jPanel1;
/*     */   private JScrollPane jScrollPane2;
/*     */   private JTable jTable1;
/*     */   private JButton moinsBrocoli;
/*     */   private JButton moinsPomme;
/*     */   private JButton plusBrocoli;
/*     */   
/*     */   private void initComponents() {
/* 162 */     this.jPanel1 = new JPanel();
/* 163 */     this.jButton6 = new JButton();
/* 164 */     this.jButton7 = new JButton();
/* 165 */     this.jLabel1 = new JLabel();
/* 166 */     this.moinsPomme = new JButton();
/* 167 */     this.ajoutPomme = new JButton();
/* 168 */     this.jLabel4 = new JLabel();
/* 169 */     this.jLabel3 = new JLabel();
/* 170 */     this.jLabel6 = new JLabel();
/* 171 */     this.jLabel5 = new JLabel();
/* 172 */     this.plusBrocoli = new JButton();
/* 173 */     this.moinsBrocoli = new JButton();
/* 174 */     this.jScrollPane2 = new JScrollPane();
/* 175 */     this.jTable1 = new JTable();
/* 176 */     this.jLabel2 = new JLabel();
/*     */     
/* 178 */     setDefaultCloseOperation(3);
/* 179 */     setLocation(new Point(0, 0));
/*     */     
/* 181 */     this.jPanel1.setLayout((LayoutManager)null);
/*     */     
/* 183 */     this.jButton6.setBorderPainted(false);
/* 184 */     this.jButton6.setContentAreaFilled(false);
/* 185 */     this.jButton6.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 187 */             GestionErreur.this.jButton6ActionPerformed(evt);
/*     */           }
/*     */         });
/* 190 */     this.jPanel1.add(this.jButton6);
/* 191 */     this.jButton6.setBounds(30, 403, 140, 30);
/*     */     
/* 193 */     this.jButton7.setBorderPainted(false);
/* 194 */     this.jButton7.setContentAreaFilled(false);
/* 195 */     this.jButton7.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 197 */             GestionErreur.this.jButton7ActionPerformed(evt);
/*     */           }
/*     */         });
/* 200 */     this.jPanel1.add(this.jButton7);
/* 201 */     this.jButton7.setBounds(580, 330, 210, 80);
/*     */     
/* 203 */     this.jLabel1.setFont(new Font("Arial", 0, 24));
/* 204 */     this.jPanel1.add(this.jLabel1);
/* 205 */     this.jLabel1.setBounds(60, 350, 100, 60);
/*     */     
/* 207 */     this.moinsPomme.setBorderPainted(false);
/* 208 */     this.moinsPomme.setContentAreaFilled(false);
/* 209 */     this.moinsPomme.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 211 */             GestionErreur.this.moinsPommeActionPerformed(evt);
/*     */           }
/*     */         });
/* 214 */     this.jPanel1.add(this.moinsPomme);
/* 215 */     this.moinsPomme.setBounds(230, 380, 40, 30);
/*     */     
/* 217 */     this.ajoutPomme.setBorderPainted(false);
/* 218 */     this.ajoutPomme.setContentAreaFilled(false);
/* 219 */     this.ajoutPomme.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 221 */             GestionErreur.this.ajoutPommeActionPerformed(evt);
/*     */           }
/*     */         });
/* 224 */     this.jPanel1.add(this.ajoutPomme);
/* 225 */     this.ajoutPomme.setBounds(320, 383, 40, 30);
/*     */     
/* 227 */     this.jLabel4.setFont(new Font("Arial", 0, 24));
/* 228 */     this.jPanel1.add(this.jLabel4);
/* 229 */     this.jLabel4.setBounds(280, 370, 80, 50);
/*     */     
/* 231 */     this.jLabel3.setFont(new Font("Arial", 0, 24));
/* 232 */     this.jPanel1.add(this.jLabel3);
/* 233 */     this.jLabel3.setBounds(470, 380, 90, 30);
/*     */     
/* 235 */     this.jLabel6.setFont(new Font("Arial", 0, 24));
/* 236 */     this.jPanel1.add(this.jLabel6);
/* 237 */     this.jLabel6.setBounds(700, 270, 100, 80);
/*     */     
/* 239 */     this.jLabel5.setFont(new Font("Arial", 0, 14));
/* 240 */     this.jLabel5.setText("Poids total :");
/* 241 */     this.jPanel1.add(this.jLabel5);
/* 242 */     this.jLabel5.setBounds(20, 280, 170, 40);
/*     */     
/* 244 */     this.plusBrocoli.setBorderPainted(false);
/* 245 */     this.plusBrocoli.setContentAreaFilled(false);
/* 246 */     this.plusBrocoli.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 248 */             GestionErreur.this.plusBrocoliActionPerformed(evt);
/*     */           }
/*     */         });
/* 251 */     this.jPanel1.add(this.plusBrocoli);
/* 252 */     this.plusBrocoli.setBounds(503, 383, 40, 30);
/*     */     
/* 254 */     this.moinsBrocoli.setBorderPainted(false);
/* 255 */     this.moinsBrocoli.setContentAreaFilled(false);
/* 256 */     this.moinsBrocoli.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 258 */             GestionErreur.this.moinsBrocoliActionPerformed(evt);
/*     */           }
/*     */         });
/* 261 */     this.jPanel1.add(this.moinsBrocoli);
/* 262 */     this.moinsBrocoli.setBounds(430, 383, 30, 30);
/*     */     
/* 264 */     this.jScrollPane2.setBackground(new Color(255, 255, 255));
/* 265 */     this.jScrollPane2.setBorder((Border)null);
/* 266 */     this.jScrollPane2.setForeground(new Color(255, 255, 255));
/* 267 */     this.jScrollPane2.setFocusable(false);
/* 268 */     this.jScrollPane2.setOpaque(false);
/* 269 */     this.jScrollPane2.setRequestFocusEnabled(false);
/*     */     
/* 271 */     this.jTable1.setFont(new Font("Calibri", 0, 14));
/* 272 */     this.jTable1.setModel(new DefaultTableModel(new Object[0][], (Object[])new String[0]));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 280 */     this.jTable1.setAutoResizeMode(4);
/* 281 */     this.jTable1.setColumnSelectionAllowed(true);
/* 282 */     this.jTable1.setCursor(new Cursor(0));
/* 283 */     this.jTable1.setGridColor(new Color(255, 255, 255));
/* 284 */     this.jTable1.setOpaque(false);
/* 285 */     this.jTable1.setSelectionBackground(new Color(255, 255, 255));
/* 286 */     this.jScrollPane2.setViewportView(this.jTable1);
/* 287 */     this.jTable1.getColumnModel().getSelectionModel().setSelectionMode(1);
/*     */     
/* 289 */     this.jPanel1.add(this.jScrollPane2);
/* 290 */     this.jScrollPane2.setBounds(250, 120, 350, 80);
/*     */     
/* 292 */     this.jLabel2.setIcon(new ImageIcon(getClass().getResource("/applicationveggie/saisie erreur.png")));
/* 293 */     this.jLabel2.setText("jLabel2");
/* 294 */     this.jPanel1.add(this.jLabel2);
/* 295 */     this.jLabel2.setBounds(0, 0, 800, 480);
/*     */     
/* 297 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 298 */     getContentPane().setLayout(layout);
/* 299 */     layout.setHorizontalGroup(layout
/* 300 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 301 */         .addComponent(this.jPanel1, -1, 800, 32767));
/*     */     
/* 303 */     layout.setVerticalGroup(layout
/* 304 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 305 */         .addComponent(this.jPanel1, -1, 480, 32767));
/*     */ 
/*     */     
/* 308 */     pack();
/*     */   }
/*     */ 
/*     */ 
/*     */ 	// ACCEDER A LA PESEE  
/*     */   private void jButton6ActionPerformed(ActionEvent evt) {
/* 314 */     File f = new File("/home/pi/tensorflow1/models/research/object_detection/hx711/temp/pesee_java.txt");
/*     */     
/* 316 */     while (!f.isFile()) {
/* 317 */       System.out.println("entree dans la boucle de verif du fichier 2");
/*     */       
/*     */       try {
/* 320 */         TimeUnit.MILLISECONDS.sleep(200L);
/* 321 */         System.out.println("delai de 1 s");
/* 322 */       } catch (InterruptedException ex) {
/* 323 */         Logger.getLogger(GestionErreur.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/*     */     } 
/* 326 */     String df = null;
/* 327 */     BufferedReader br1 = null;
/* 328 */     StringBuilder sb = new StringBuilder();
/* 329 */     String line = null;
/*     */     
/*     */     try {
/* 332 */       while (df == null) {
/* 333 */         br1 = new BufferedReader(new FileReader(f));
/* 334 */         System.out.println("toujours pas eu de pesee");
/* 335 */         line = br1.readLine();
/* 336 */         df = line;
/* 337 */         TimeUnit.MILLISECONDS.sleep(200L);
/* 338 */         System.out.println(line);
/* 339 */         this.jLabel6.setText("Pesee en cours");
/*     */       } 
/* 341 */     } catch (IOException ex) {
/* 342 */       Logger.getLogger(GestionErreur.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 343 */     } catch (InterruptedException ex) {
/* 344 */       Logger.getLogger(GestionErreur.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 346 */     this.jLabel1.setText(df + "g");
/* 347 */     this.Bananes = Integer.parseInt(df.trim());
/* 348 */     this.BananesPriceTTC = this.Bananes * 0.001D * this.BananesPrice;
/* 349 */     DecimalFormat pricetotal = new DecimalFormat("0.00");
/* 350 */     this.PrixTotal += this.BananesPriceTTC;
/* 351 */     if (this.PrixTotal <= 0.0D) {
/* 352 */       this.PrixTotal = 0.0D;
/*     */     }
/* 354 */     this.kgpesees += this.Bananes;
/* 355 */     this.jLabel6.setText(String.valueOf(pricetotal.format(this.PrixTotal)) + "€");
/* 356 */     this.pesees++;
/* 357 */     if (this.pesees > 1) {
/* 358 */       this.jLabel5.setText("Poids total :" + String.valueOf(this.kgpesees) + "g");
/*     */     }
/*     */   }
/*     */ 
/*     */   // GENERER LA FACTURE
/*     */   private void jButton7ActionPerformed(ActionEvent evt) {
/* 364 */     String newFileFacture = "/home/pi/tensorflow1/models/research/object_detection/factures/facture_affichage.txt";
/*     */     
/* 366 */     File f = new File(FilePath);
/* 367 */     if (f.exists() && !f.isDirectory()) {
/* 368 */       f.delete();
/*     */     }
/*     */ 
/*     */     
/* 372 */     try (PrintWriter writer = new PrintWriter(new File(newFileFacture))) {
/* 373 */       StringBuilder header = new StringBuilder();
/* 374 */       DecimalFormat pricetotal = new DecimalFormat("0.00");
/* 375 */       header.append("Qte");
/* 376 */       header.append("\t");
/* 377 */       header.append("Type");
/* 378 */       header.append("\t");
/* 379 */       header.append("Design");
/* 380 */       header.append("\t");
/* 381 */       header.append("Prix/u");
/* 382 */       header.append("\t");
/* 383 */       header.append("Prix HT");
/* 384 */       header.append("\n");
/* 385 */       if (this.Avocats != 0) {
/* 386 */         header.append(String.valueOf(pricetotal.format(this.Avocats)));
/* 387 */         header.append("\t");
/* 388 */         header.append(this.AvocatsType);
/* 389 */         header.append("\t");
/* 390 */         header.append("avocat");
/* 391 */         header.append("\t");
/* 392 */         header.append(String.valueOf(this.AvocatsPrice));
/* 393 */         header.append("\t");
/* 394 */         header.append(String.valueOf(this.AvocatsPriceTTC));
/* 395 */         header.append("\n");
/*     */       } 
/* 397 */       if (this.Bananes != 0.0D) {
/* 398 */         header.append(String.valueOf(this.Bananes));
/* 399 */         header.append("\t");
/* 400 */         header.append(this.BananesType);
/* 401 */         header.append("\t");
/* 402 */         header.append("banane");
/* 403 */         header.append("\t");
/* 404 */         header.append(String.valueOf(this.BananesPrice));
/* 405 */         header.append("\t");
/* 406 */         header.append(String.valueOf(pricetotal.format(this.BananesPriceTTC)));
/* 407 */         header.append("\n");
/*     */       } 
/* 409 */       if (this.Pomme != 0) {
/* 410 */         header.append(String.valueOf(this.Pomme));
/* 411 */         header.append("\t");
/* 412 */         header.append(this.PommeType);
/* 413 */         header.append("\t");
/* 414 */         header.append("pomme");
/* 415 */         header.append("\t");
/* 416 */         header.append(String.valueOf(this.PommePrice));
/* 417 */         header.append("\t");
/* 418 */         header.append(String.valueOf(pricetotal.format(this.PommePriceTTC)));
/* 419 */         header.append("\n");
/*     */       } 
/* 421 */       if (this.Broccoli != 0) {
/* 422 */         header.append(String.valueOf(this.Broccoli));
/* 423 */         header.append("\t");
/* 424 */         header.append(this.BroccoliType);
/* 425 */         header.append("\t");
/* 426 */         header.append("brocoli");
/* 427 */         header.append("\t");
/* 428 */         header.append(String.valueOf(this.BroccoliPrice));
/* 429 */         header.append("\t");
/* 430 */         header.append(String.valueOf(pricetotal.format(this.BroccoliPriceTTC)));
/* 431 */         header.append("\n");
/*     */       } 
/* 433 */       writer.write(header.toString());
/* 434 */       writer.close();
/* 435 */       System.out.println("WRITE CORRECTION DONE");
/* 436 */       TimeUnit.MILLISECONDS.sleep(200L);
/*     */       
/* 438 */       SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
/* 439 */       Date date = new Date();
/*     */       
/* 441 */       System.out.println(formatter.format(date));
/*     */       
/* 443 */       File afile = new File(FilePath);
/*     */       
/* 445 */       if (afile.renameTo(new File("/home/pi/tensorflow1/models/research/object_detection/factures/archives/facture_of " + date))) {
/* 446 */         System.out.println("File is moved successful!");
/*     */       } else {
/*     */         
/* 449 */         System.out.println("File is failed to move!");
/*     */       } 
/*     */       
/* 452 */       setVisible(false);
/* 453 */       (new FinClient()).setVisible(true);
/*     */     }
/* 455 */     catch (FileNotFoundException|InterruptedException ex) {
/* 456 */       Logger.getLogger(GestionErreur.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   // AJOUTER DES POMMES  
/*     */   private void ajoutPommeActionPerformed(ActionEvent evt) {
/* 463 */     this.Pomme++;
/* 464 */     this.PommePriceTTC = this.PommePrice * this.Pomme;
/* 465 */     this.jLabel4.setText(String.valueOf(this.Pomme));
/* 466 */     System.out.println("Pomme : " + this.Pomme);
/* 467 */     System.out.println("Prix Pomme: " + this.PommePriceTTC);
/* 468 */     DecimalFormat df = new DecimalFormat("0.00");
/* 469 */     this.PrixTotal += this.PommePrice;
/* 470 */     if (this.PrixTotal <= 0.0D) {
/* 471 */       this.PrixTotal = 0.0D;
/*     */     }
/* 473 */     this.jLabel6.setText(String.valueOf(df.format(this.PrixTotal)) + "€");
/*     */   }
/*     */ 
/*     */   // ENLEVER DES POMMES
/*     */   private void moinsPommeActionPerformed(ActionEvent evt) {
/* 478 */     this.Pomme--;
/* 479 */     if (this.Pomme < 0) {
/* 480 */       this.Pomme = 0;
/*     */     }
/* 482 */     this.PommePriceTTC = this.PommePrice * this.Pomme;
/* 483 */     this.jLabel4.setText(String.valueOf(this.Pomme));
/* 484 */     System.out.println("Pomme : " + this.Pomme);
/* 485 */     System.out.println("Prix Pomme : " + this.PommePriceTTC);
/* 486 */     DecimalFormat df = new DecimalFormat("0.00");
/* 487 */     this.PrixTotal -= this.PommePrice;
/* 488 */     if (this.PrixTotal <= 0.0D) {
/* 489 */       this.PrixTotal = 0.0D;
/*     */     }
/* 491 */     this.jLabel6.setText(String.valueOf(df.format(this.PrixTotal)) + "€");
/*     */   }
/*     */ 
/*     */   // ENLEVER DES BROCOLIS

/*     */   private void moinsBrocoliActionPerformed(ActionEvent evt) {
/* 496 */     this.Broccoli--;
/* 497 */     if (this.Broccoli < 0) {
/* 498 */       this.Broccoli = 0;
/*     */     }
/* 500 */     this.BroccoliPriceTTC = this.BroccoliPrice * this.Broccoli;
/* 501 */     this.jLabel3.setText(String.valueOf(this.Broccoli));
/* 502 */     System.out.println("Pomme : " + this.Broccoli);
/* 503 */     System.out.println("Prix Pomme : " + this.BroccoliPriceTTC);
/* 504 */     DecimalFormat df = new DecimalFormat("0.00");
/* 505 */     this.PrixTotal -= this.BroccoliPrice;
/* 506 */     if (this.PrixTotal <= 0.0D) {
/* 507 */       this.PrixTotal = 0.0D;
/*     */     }
/* 509 */     this.jLabel6.setText(String.valueOf(df.format(this.PrixTotal)) + "€");
/*     */   }
/*     */ 
/*     */   // AJOUTER DES BROCOLIS
/*     */   private void plusBrocoliActionPerformed(ActionEvent evt) {
/* 514 */     this.Broccoli++;
/* 515 */     this.BroccoliPriceTTC = this.BroccoliPrice * this.Broccoli;
/* 516 */     this.jLabel3.setText(String.valueOf(this.Broccoli));
/* 517 */     System.out.println("Pomme : " + this.Broccoli);
/* 518 */     System.out.println("Prix Pomme: " + this.BroccoliPriceTTC);
/* 519 */     DecimalFormat df = new DecimalFormat("0.00");
/* 520 */     this.PrixTotal += this.BroccoliPrice;
/* 521 */     if (this.PrixTotal <= 0.0D) {
/* 522 */       this.PrixTotal = 0.0D;
/*     */     }
/* 524 */     this.jLabel6.setText(String.valueOf(df.format(this.PrixTotal)) + "€");
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
/* 537 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 538 */         if ("Nimbus".equals(info.getName())) {
/* 539 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 543 */     } catch (ClassNotFoundException ex) {
/* 544 */       Logger.getLogger(GestionErreur.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 545 */     } catch (InstantiationException ex) {
/* 546 */       Logger.getLogger(GestionErreur.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 547 */     } catch (IllegalAccessException ex) {
/* 548 */       Logger.getLogger(GestionErreur.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 549 */     } catch (UnsupportedLookAndFeelException ex) {
/* 550 */       Logger.getLogger(GestionErreur.class.getName()).log(Level.SEVERE, (String)null, ex);
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
/* 682 */     EventQueue.invokeLater(() -> (new GestionErreur()).setVisible(true));
/*     */   }
/*     */ }


/* Location:              C:\Users\hamza\Documents\NetBeansProjects\ApplicationVeggie\dist\ApplicationVeggie.jar!\applicationveggie\GestionErreur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */