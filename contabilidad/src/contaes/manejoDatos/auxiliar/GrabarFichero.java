/*    */ package contaes.manejoDatos.auxiliar;
/*    */ 
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class GrabarFichero
/*    */ {
/*    */   private DataOutputStream salida;
/*    */ 
/*    */   public GrabarFichero(String nombreFichero)
/*    */   {
/*    */     try
/*    */     {
/* 37 */       this.salida = new DataOutputStream(new FileOutputStream(nombreFichero));
/*    */     } catch (IOException e) {
/*    */     }
/*    */   }
/*    */ 
/*    */   public GrabarFichero(File nombreFichero) {
/*    */     try {
/* 44 */       this.salida = new DataOutputStream(new FileOutputStream(nombreFichero));
/*    */     } catch (IOException e) {
/*    */     }
/*    */   }
/*    */ 
/*    */   public void insertar(String cadena) {
/*    */     try {
/* 51 */       this.salida.writeBytes(cadena); } catch (IOException e) {
/*    */     }
/*    */   }
/*    */ 
/*    */   public void cerrar() {
/*    */     try { this.salida.flush(); this.salida.close();
/*    */     }
/*    */     catch (IOException e)
/*    */     {
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.GrabarFichero
 * JD-Core Version:    0.6.2
 */