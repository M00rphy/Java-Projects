/*    */ package pos.view.editors;
/*    */ 
/*    */ import pos.model.Formats;
/*    */ 
/*    */ public class JEditorDouble extends JEditorNumber
/*    */ {
/*    */   protected Formats getFormat()
/*    */   {
/* 32 */     return Formats.DOUBLE;
/*    */   }
/*    */ 
/*    */   protected int getMode() {
/* 36 */     return 1;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.editors.JEditorDouble
 * JD-Core Version:    0.6.2
 */