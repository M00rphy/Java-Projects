/*    */ package contaes.auxiliarTablas;
/*    */ 
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.GregorianCalendar;
/*    */ 
/*    */ public class FechaColorRenderer extends ColumnaColorRenderer
/*    */ {
/*    */   protected Object valor(Object value)
/*    */   {
/* 41 */     if (value != null) {
/* 42 */       String cad = value.toString();
/* 43 */       int year = Integer.parseInt(cad.substring(0, 4));
/* 44 */       int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/* 45 */       int date = Integer.parseInt(cad.substring(8));
/* 46 */       GregorianCalendar calendario = new GregorianCalendar();
/* 47 */       calendario.set(year, month, date);
/* 48 */       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 49 */       value = sdf.format(calendario.getTime());
/*    */     }
/* 51 */     return value;
/*    */   }
/*    */ 
/*    */   protected int alineacion() {
/* 55 */     return 4;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliarTablas.FechaColorRenderer
 * JD-Core Version:    0.6.2
 */