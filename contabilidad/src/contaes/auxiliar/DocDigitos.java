/*    */ package contaes.auxiliar;
/*    */ 
/*    */ import javax.swing.text.AttributeSet;
/*    */ import javax.swing.text.BadLocationException;
/*    */ import javax.swing.text.PlainDocument;
/*    */ 
/*    */ public class DocDigitos extends PlainDocument
/*    */ {
/*    */   public void insertString(int arg0, String arg1, AttributeSet arg2)
/*    */     throws BadLocationException
/*    */   {
/* 34 */     char[] insertado = arg1.toCharArray();
/* 35 */     boolean valido = true;
/* 36 */     for (int x = 0; x < insertado.length; x++) {
/* 37 */       if (!Character.isDigit(insertado[x]))
/* 38 */         valido = false;
/*    */     }
/* 40 */     if (valido)
/* 41 */       super.insertString(arg0, arg1, arg2);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.DocDigitos
 * JD-Core Version:    0.6.2
 */