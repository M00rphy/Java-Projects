/*    */ package contaes.manejoDatos.funciones;
/*    */ 
/*    */ import java.sql.Date;
/*    */ 
/*    */ public class ComprobarDato
/*    */ {
/*    */   String dato;
/*    */ 
/*    */   public ComprobarDato()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ComprobarDato(String dato)
/*    */   {
/* 47 */     this.dato = dato;
/*    */   }
/*    */ 
/*    */   public void setDato(String dato) {
/* 51 */     this.dato = dato;
/*    */   }
/*    */ 
/*    */   public String esFecha() {
/*    */     try {
/* 56 */       Date b = Date.valueOf(this.dato);
/* 57 */       this.dato = b.toString();
/*    */     }
/*    */     catch (Exception exc) {
/* 60 */       return exc.toString();
/*    */     }
/* 62 */     return this.dato;
/*    */   }
/*    */ 
/*    */   public boolean esDoble() {
/*    */     try {
/* 67 */       new Double(this.dato).doubleValue();
/*    */     }
/*    */     catch (NumberFormatException exc) {
/* 70 */       return false;
/*    */     }
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean esEntero() {
/*    */     try {
/* 77 */       Integer.parseInt(this.dato);
/*    */     }
/*    */     catch (NumberFormatException exc) {
/* 80 */       return false;
/*    */     }
/* 82 */     return true;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.ComprobarDato
 * JD-Core Version:    0.6.2
 */