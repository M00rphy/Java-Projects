/*    */ package contaes.manejoDatos.auxiliar;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ public class AddExtension
/*    */ {
/*    */   public static File pdf(File file)
/*    */   {
/* 17 */     String ruta = file.getPath();
/* 18 */     if (!ruta.substring(ruta.length() - 4).equals(".pdf")) {
/* 19 */       ruta = ruta + ".pdf";
/* 20 */       file = new File(ruta);
/*    */     }
/* 22 */     return file;
/*    */   }
/*    */ 
/*    */   public static File txt(File file) {
/* 26 */     String ruta = file.getPath();
/* 27 */     if (!ruta.substring(ruta.length() - 4).equals(".txt")) {
/* 28 */       ruta = ruta + ".txt";
/* 29 */       file = new File(ruta);
/*    */     }
/* 31 */     return file;
/*    */   }
/*    */ 
/*    */   public static File csv(File file) {
/* 35 */     String ruta = file.getPath();
/* 36 */     if (!ruta.substring(ruta.length() - 4).equals(".csv")) {
/* 37 */       ruta = ruta + ".csv";
/* 38 */       file = new File(ruta);
/*    */     }
/* 40 */     return file;
/*    */   }
/*    */ 
/*    */   public static File png(File file) {
/* 44 */     String ruta = file.getPath();
/* 45 */     if (!ruta.substring(ruta.length() - 4).equals(".png")) {
/* 46 */       ruta = ruta + ".png";
/* 47 */       file = new File(ruta);
/*    */     }
/* 49 */     return file;
/*    */   }
/*    */ 
/*    */   public static File sql(File file) {
/* 53 */     String ruta = file.getPath();
/* 54 */     if (!ruta.substring(ruta.length() - 4).equals(".sql")) {
/* 55 */       ruta = ruta + ".sql";
/* 56 */       file = new File(ruta);
/*    */     }
/* 58 */     return file;
/*    */   }
/*    */ 
/*    */   public static File q19(File file) {
/* 62 */     String ruta = file.getPath();
/* 63 */     if (!ruta.substring(ruta.length() - 4).equals(".q19")) {
/* 64 */       ruta = ruta + ".q19";
/* 65 */       file = new File(ruta);
/*    */     }
/* 67 */     return file;
/*    */   }
/*    */ 
/*    */   public static File q58(File file) {
/* 71 */     String ruta = file.getPath();
/* 72 */     if (!ruta.substring(ruta.length() - 4).equals(".q58")) {
/* 73 */       ruta = ruta + ".q58";
/* 74 */       file = new File(ruta);
/*    */     }
/* 76 */     return file;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.AddExtension
 * JD-Core Version:    0.6.2
 */