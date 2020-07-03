/*    */ package contaes.auxiliarTablas;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.table.DefaultTableCellRenderer;
/*    */ import javax.swing.table.TableCellRenderer;
/*    */ 
/*    */ public class GeneralColorRenderer
/*    */   implements TableCellRenderer
/*    */ {
/* 37 */   public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
/*    */ 
/*    */   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
/*    */   {
/* 41 */     Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
/*    */ 
/* 43 */     ((JLabel)renderer).setOpaque(true);
/*    */     Color background;
/*    */     Color foreground;
/*    */   
/* 45 */     if (isSelected) {
/* 46 */       foreground = Color.white;
/* 47 */       background = new Color(52, 89, 244);
/*    */     } else {
/* 49 */       foreground = Color.black;
/*    */     
/* 50 */       if (row % 2 == 0)
/* 51 */         background = Color.white;
/*    */       else {
/* 53 */         background = new Color(215, 240, 255);
/*    */       }
/*    */     }
/* 56 */     renderer.setForeground(foreground);
/* 57 */     renderer.setBackground(background);
/* 58 */     return renderer;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliarTablas.GeneralColorRenderer
 * JD-Core Version:    0.6.2
 */