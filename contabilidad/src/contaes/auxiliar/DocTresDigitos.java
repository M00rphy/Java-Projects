/*    */ package contaes.auxiliar;
/*    */ 
/*    */ import javax.swing.text.AttributeSet;
/*    */ import javax.swing.text.BadLocationException;
/*    */ import javax.swing.text.PlainDocument;
/*    */ 
/*    */ public class DocTresDigitos extends PlainDocument
/*    */ {
/*    */   public void insertString(int arg0, String arg1, AttributeSet arg2)
/*    */     throws BadLocationException
/*    */   {
/* 19 */     char[] insertado = arg1.toCharArray();
/* 20 */     boolean valido = true;
/* 21 */     boolean isDigits = true;
/* 22 */     for (int x = 0; x < insertado.length; x++) {
/* 23 */       if (!Character.isDigit(insertado[x])) {
/* 24 */         valido = false;
/* 25 */         isDigits = false;
/*    */       }
/*    */     }
/* 28 */     if (insertado.length + getLength() > 3) {
/* 29 */       valido = false;
/*    */     }
/* 31 */     if (valido)
/* 32 */       super.insertString(arg0, arg1, arg2);
/* 33 */     else if ((isDigits) && 
/* 34 */       (insertado.length > 3))
/* 35 */       super.insertString(arg0, arg1.substring(0, 3), arg2);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.DocTresDigitos
 * JD-Core Version:    0.6.2
 */