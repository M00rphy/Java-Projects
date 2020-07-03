/*    */ package contaes.auxiliarTablas;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.table.DefaultTableCellRenderer;
/*    */ import javax.swing.table.TableCellRenderer;
/*    */ 
/*    */ public class ColumnaColorRenderer
/*    */   implements TableCellRenderer
/*    */ {
/* 13 */   public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
/*    */ 
/*    */   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
/*    */   {
/* 17 */     Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
/*    */ 
/* 19 */     ((JLabel)renderer).setOpaque(true);
/*    */     Color background;
/*    */     Color foreground;
/*    */     
/* 21 */     if (isSelected) {
/* 22 */        foreground = Color.white;
/* 23 */       background = new Color(52, 89, 244);
/*    */     } else {
/* 25 */       foreground = Color.black;
/*    */       
/* 26 */       if (row % 2 == 0)
/* 27 */         background = Color.white;
/*    */       else {
/* 29 */         background = new Color(215, 240, 255);
/*    */       }
/*    */     }
/* 32 */     ((JLabel)renderer).setForeground(foreground);
/* 33 */     ((JLabel)renderer).setBackground(background);
/* 34 */     String texto = toolTip(value.toString());
/* 35 */     if (!texto.equals("")) {
/* 36 */       ((JLabel)renderer).setToolTipText(texto);
/*    */     }
/* 38 */     ((JLabel)renderer).setHorizontalAlignment(alineacion());
/* 39 */     ((JLabel)renderer).setText(valor(value).toString());
/* 40 */     return renderer;
/*    */   }
/*    */ 
/*    */   protected Object valor(Object value) {
/* 44 */     return value;
/*    */   }
/*    */ 
/*    */   protected String toolTip(String value) {
/* 48 */     return "";
/*    */   }
/*    */ 
/*    */   protected int alineacion() {
/* 52 */     return 2;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliarTablas.ColumnaColorRenderer
 * JD-Core Version:    0.6.2
 */