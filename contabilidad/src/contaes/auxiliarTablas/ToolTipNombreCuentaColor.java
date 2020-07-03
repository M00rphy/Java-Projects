/*    */ package contaes.auxiliarTablas;
/*    */ 
/*    */ import contaes.Inicio;
/*    */ import contaes.Puente;
/*    */ import contaes.manejoDatos.ManejoSubcuentas;
/*    */ import contaes.manejoDatos.TipoSubcuenta;
/*    */ 
/*    */ public class ToolTipNombreCuentaColor extends ColumnaColorRenderer
/*    */ {
/*    */   protected String toolTip(String value)
/*    */   {
/* 41 */     if (!value.equals("")) {
/*    */       try {
/* 43 */         TipoSubcuenta cuenta = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio()).datos(Integer.parseInt(value));
/*    */ 
/* 46 */         if (cuenta != null)
/* 47 */           return cuenta.getNombre();
/*    */       } catch (NumberFormatException e) {
/* 49 */         e.printStackTrace();
/*    */       }
/*    */     }
/* 52 */     return "";
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliarTablas.ToolTipNombreCuentaColor
 * JD-Core Version:    0.6.2
 */