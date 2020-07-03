/*    */ package contaes.auxiliarTablas;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import javax.swing.table.DefaultTableCellRenderer;
/*    */ 
/*    */ public class NegritaRenderer extends DefaultTableCellRenderer
/*    */ {
/*    */   public NegritaRenderer()
/*    */   {
/* 36 */     setBackground(Color.WHITE);
/*    */   }
/*    */ 
/*    */   protected void setValue(Object arg0)
/*    */   {
/* 45 */     setFont(getFont().deriveFont(1));
/* 46 */     super.setValue(arg0);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliarTablas.NegritaRenderer
 * JD-Core Version:    0.6.2
 */