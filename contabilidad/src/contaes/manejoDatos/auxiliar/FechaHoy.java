/*    */ package contaes.manejoDatos.auxiliar;
/*    */ 
/*    */ import java.text.DateFormat;
/*    */ import java.util.GregorianCalendar;
/*    */ 
/*    */ public class FechaHoy
/*    */ {
/*    */   private GregorianCalendar hoy;
/*    */   private DateFormat df;
/*    */ 
/*    */   public FechaHoy()
/*    */   {
/* 15 */     this.hoy = new GregorianCalendar();
/* 16 */     this.df = DateFormat.getDateInstance(2);
/*    */   }
/*    */ 
/*    */   public String getFecha() {
/* 20 */     return this.df.format(this.hoy.getTime());
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.FechaHoy
 * JD-Core Version:    0.6.2
 */