/*    */ package pos.view.editors;
/*    */ 
/*    */ import pos.model.Formats;
/*    */ 
/*    */ public class JEditorIntegerPositive extends JEditorNumber
/*    */ {
/*    */   protected Formats getFormat()
/*    */   {
/* 33 */     return Formats.INT;
/*    */   }
/*    */ 
/*    */   protected int getMode()
/*    */   {
/* 38 */     return 4;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.editors.JEditorIntegerPositive
 * JD-Core Version:    0.6.2
 */