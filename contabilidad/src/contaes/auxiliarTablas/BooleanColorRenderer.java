/*    */ package contaes.auxiliarTablas;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import javax.swing.JCheckBox;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.table.TableCellRenderer;
/*    */ 
/*    */ public class BooleanColorRenderer extends JCheckBox
/*    */   implements TableCellRenderer
/*    */ {
/*    */   public BooleanColorRenderer()
/*    */   {
/* 15 */     setHorizontalAlignment(0);
/*    */   }
/*    */ 
/*    */   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
/*    */   {
/* 20 */     setOpaque(true);
/*    */     Color background;
/*    */     Color foreground;
/*    */     
/* 22 */     if (isSelected) {
/* 23 */        foreground = Color.white;
/* 24 */       background = new Color(52, 89, 244);
/*    */     } else {
/* 26 */       foreground = Color.black;
/*    */       
/* 27 */       if (row % 2 == 0)
/* 28 */         background = Color.white;
/*    */       else {
/* 30 */         background = new Color(215, 240, 255);
/*    */       }
/*    */     }
/* 33 */     setForeground(foreground);
/* 34 */     setBackground(background);
/* 35 */     if (((Boolean)value).booleanValue())
/* 36 */       setSelected(true);
/* 37 */     else setSelected(false);
/* 38 */     return this;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliarTablas.BooleanColorRenderer
 * JD-Core Version:    0.6.2
 */