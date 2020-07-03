/*    */ package contaes.auxiliar;
/*    */ 
/*    */ import javax.swing.text.AttributeSet;
/*    */ import javax.swing.text.BadLocationException;
/*    */ import javax.swing.text.PlainDocument;
/*    */ 
/*    */ public class DocOchoCarac extends PlainDocument
/*    */ {
/*    */   public void insertString(int offset, String str, AttributeSet a)
/*    */     throws BadLocationException
/*    */   {
/* 36 */     char[] insertChars = str.toCharArray();
/*    */ 
/* 38 */     boolean fit = true;
/* 39 */     if (insertChars.length + getLength() > longitud()) {
/* 40 */       fit = false;
/*    */     }
/* 42 */     if (fit) {
/* 43 */       super.insertString(offset, str, a);
/*    */     }
/* 45 */     else if (str.length() > longitud())
/* 46 */       super.insertString(offset, str.substring(0, longitud()), a);
/*    */   }
/*    */ 
/*    */   protected int longitud()
/*    */   {
/* 51 */     return 8;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.DocOchoCarac
 * JD-Core Version:    0.6.2
 */