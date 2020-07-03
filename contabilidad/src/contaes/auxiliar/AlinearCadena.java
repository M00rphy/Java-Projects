/*    */ package contaes.auxiliar;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class AlinearCadena
/*    */ {
/* 28 */   private String cadenaAlineada = "";
/* 29 */   private String espacios = "";
/*    */ 
/*    */   public String Derecha(String cadena, int largoTotal)
/*    */   {
/* 40 */     if (cadena == null) {
/* 41 */       return "";
/*    */     }
/* 43 */     if (cadena.equals("")) {
/* 44 */       cadena = " ";
/*    */     }
/* 46 */     this.espacios = "";
/* 47 */     for (int x = 0; x < largoTotal - 1; x++)
/* 48 */       this.espacios += " ";
/*    */     try
/*    */     {
/* 51 */       if (cadena.length() < largoTotal)
/* 52 */         this.cadenaAlineada = (this.espacios.substring(cadena.length() - 1) + cadena + " ");
/* 53 */       else if (cadena.length() > largoTotal)
/* 54 */         this.cadenaAlineada = (cadena.substring(0, largoTotal) + " ");
/*    */       else
/* 56 */         this.cadenaAlineada = (cadena + " ");
/*    */     }
/*    */     catch (IndexOutOfBoundsException exc) {
/* 59 */       System.out.println("Error: " + exc.getMessage() + " en:" + cadena);
/* 60 */       this.cadenaAlineada = "";
/*    */     }
/* 62 */     return this.cadenaAlineada;
/*    */   }
/*    */ 
/*    */   public String Izquierda(String cadena, int largoTotal)
/*    */   {
/* 74 */     if (cadena == null) {
/* 75 */       return "";
/*    */     }
/* 77 */     if (cadena.equals("")) {
/* 78 */       cadena = " ";
/*    */     }
/* 80 */     this.espacios = "";
/* 81 */     for (int x = 0; x < largoTotal; x++)
/* 82 */       this.espacios += " ";
/*    */     try
/*    */     {
/* 85 */       if (cadena.length() < largoTotal)
/* 86 */         this.cadenaAlineada = (cadena + this.espacios.substring(cadena.length()));
/* 87 */       else if (cadena.length() > largoTotal)
/* 88 */         this.cadenaAlineada = cadena.substring(0, largoTotal);
/*    */       else
/* 90 */         this.cadenaAlineada = cadena;
/*    */     }
/*    */     catch (IndexOutOfBoundsException exc) {
/* 93 */       System.out.println("Error: " + exc.getMessage() + " en:" + cadena);
/* 94 */       this.cadenaAlineada = "";
/*    */     }
/* 96 */     return this.cadenaAlineada;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.AlinearCadena
 * JD-Core Version:    0.6.2
 */