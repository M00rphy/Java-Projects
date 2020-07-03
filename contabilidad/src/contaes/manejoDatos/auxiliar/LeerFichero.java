/*    */ package contaes.manejoDatos.auxiliar;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.EOFException;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ 
/*    */ public class LeerFichero
/*    */ {
/*    */   private BufferedReader ent;
/*    */   boolean fin;
/*    */ 
/*    */   public LeerFichero(String nombre)
/*    */     throws IOException
/*    */   {
/* 16 */     this.fin = false;
/* 17 */     this.ent = new BufferedReader(new InputStreamReader(new FileInputStream(nombre)));
/*    */   }
/*    */   public boolean eof() {
/* 20 */     return this.fin;
/*    */   }
/*    */   public String leer() {
/* 23 */     String linea = null;
/*    */     try {
/* 25 */       linea = this.ent.readLine();
/*    */     } catch (EOFException eof) {
/* 27 */       this.fin = true; } catch (IOException e) {
/*    */     } catch (NullPointerException ex) {
/*    */     }
/* 30 */     return linea;
/*    */   }
/*    */ 
/*    */   public String leerTh() throws IOException, NullPointerException {
/* 34 */     String linea = null;
/*    */     try {
/* 36 */       linea = this.ent.readLine();
/*    */     } catch (EOFException eof) {
/* 38 */       this.fin = true;
/* 39 */     }return linea;
/*    */   }
/*    */   public void cerrar() {
/*    */     try {
/* 43 */       this.ent.close();
/*    */     }
/*    */     catch (IOException e)
/*    */     {
/*    */     }
/*    */     catch (NullPointerException ex)
/*    */     {
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.LeerFichero
 * JD-Core Version:    0.6.2
 */