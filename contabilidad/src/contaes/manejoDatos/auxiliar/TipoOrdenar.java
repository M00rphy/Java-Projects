/*    */ package contaes.manejoDatos.auxiliar;
/*    */ 
/*    */ import java.util.GregorianCalendar;
/*    */ 
/*    */ public class TipoOrdenar
/*    */   implements Comparable<TipoOrdenar>
/*    */ {
/*    */   private int id_asiento;
/*    */   private GregorianCalendar fecha;
/*    */ 
/*    */   public TipoOrdenar(int i, String f)
/*    */   {
/* 34 */     this.id_asiento = i;
/* 35 */     int year = Integer.parseInt(f.substring(0, 4));
/* 36 */     int month = Integer.parseInt(f.substring(5, 7)) - 1;
/* 37 */     int date = Integer.parseInt(f.substring(8));
/* 38 */     this.fecha = new GregorianCalendar(year, month, date);
/*    */   }
/*    */ 
/*    */   public GregorianCalendar Fecha() {
/* 42 */     return this.fecha;
/*    */   }
/*    */ 
/*    */   public int ID() {
/* 46 */     return this.id_asiento;
/*    */   }
/*    */ 
/*    */   public void datos(int i, String f) {
/* 50 */     this.id_asiento = i;
/* 51 */     int year = Integer.parseInt(f.substring(0, 4));
/* 52 */     int month = Integer.parseInt(f.substring(5, 7)) - 1;
/* 53 */     int date = Integer.parseInt(f.substring(8));
/* 54 */     this.fecha = new GregorianCalendar(year, month, date);
/*    */   }
/*    */ 
/*    */   public int compareTo(TipoOrdenar o) {
/* 58 */     return this.fecha.after(o.fecha) ? 1 : this.fecha.before(o.fecha) ? -1 : 0;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.TipoOrdenar
 * JD-Core Version:    0.6.2
 */