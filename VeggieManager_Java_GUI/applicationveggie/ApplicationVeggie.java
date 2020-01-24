/*    */ package applicationveggie;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ApplicationVeggie
/*    */ {
/*    */   public static void main(String[] args) {
/*    */     try {
/* 27 */       Process process = Runtime.getRuntime().exec("sudo python /home/pi/Pictures/led_final.py");
/* 28 */     } catch (IOException iOException) {}
/*    */ 
/*    */     
/* 31 */     String ledRouge = "/home/pi/Pictures/ordresLed/led_red.txt";
/* 32 */     File f1 = new File(ledRouge);
/* 33 */     String ledOrange = "/home/pi/Pictures/ordresLed/led_orange.txt";
/* 34 */     File f2 = new File(ledOrange);
/*    */ 
/*    */     
/* 37 */     if (f1.exists()) {
/* 38 */       f1.delete();
/*    */       try {
/* 40 */         TimeUnit.MILLISECONDS.sleep(200L);
/* 41 */       } catch (InterruptedException ex) {
/* 42 */         Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*    */       } 
/*    */     } 
/* 45 */     if (f2.exists()) {
/* 46 */       f2.delete();
/*    */       try {
/* 48 */         TimeUnit.MILLISECONDS.sleep(200L);
/* 49 */       } catch (InterruptedException ex) {
/* 50 */         Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*    */       } 
/*    */     } 
/*    */     
/* 54 */     FileWriter myWriter = null;
/*    */     try {
/* 56 */       myWriter = new FileWriter("/home/pi/Pictures/ordresLed/led_green.txt");
/* 57 */     } catch (IOException ex) {
/* 58 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*    */     } 
/*    */     try {
/* 61 */       myWriter.write("1");
/* 62 */     } catch (IOException ex) {
/* 63 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*    */     } 
/*    */     try {
/* 66 */       myWriter.close();
/* 67 */     } catch (IOException ex) {
/* 68 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*    */     } 
/* 70 */     System.out.println("Successfully wrote to the file.");
/*    */     try {
/* 72 */       TimeUnit.MILLISECONDS.sleep(200L);
/* 73 */     } catch (InterruptedException ex) {
/* 74 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*    */     } 
/* 76 */     System.out.println("Successfully wrote to the file.");
/*    */     try {
/* 78 */       TimeUnit.MILLISECONDS.sleep(200L);
/* 79 */     } catch (InterruptedException ex) {
/* 80 */       Logger.getLogger(AffichageListe.class.getName()).log(Level.SEVERE, (String)null, ex);
/*    */     } 
/* 82 */     (new EcranPrincipal()).setVisible(true);
/* 83 */     System.out.println("Code lance !");
/*    */   }
/*    */ }


/* Location:              C:\Users\hamza\Documents\NetBeansProjects\ApplicationVeggie\dist\ApplicationVeggie.jar!\applicationveggie\ApplicationVeggie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */