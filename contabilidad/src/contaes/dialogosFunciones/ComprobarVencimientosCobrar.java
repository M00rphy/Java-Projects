/*    */ package contaes.dialogosFunciones;
/*    */ 
/*    */ import java.awt.Frame;
/*    */ 
/*    */ public class ComprobarVencimientosCobrar extends ComprobarVencimientos
/*    */ {
/*    */   public ComprobarVencimientosCobrar()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ComprobarVencimientosCobrar(Frame owner, boolean modal)
/*    */   {
/* 12 */     super(owner, modal);
/*    */   }
/*    */ 
/*    */   protected boolean esRecibida()
/*    */   {
/* 19 */     return false;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.ComprobarVencimientosCobrar
 * JD-Core Version:    0.6.2
 */