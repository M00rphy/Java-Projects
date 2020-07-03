/*    */ package pos.view.editors;
/*    */ 
/*    */ import pos.model.Formats;
/*    */ 
/*    */ public class JEditorCurrency extends JEditorNumber
/*    */ {
/*    */   private static final long serialVersionUID = 5096754100573262803L;
/*    */ 
/*    */   protected Formats getFormat()
/*    */   {
/* 34 */     return Formats.CURRENCY;
/*    */   }
/*    */   protected int getMode() {
/* 37 */     return 1;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.editors.JEditorCurrency
 * JD-Core Version:    0.6.2
 */