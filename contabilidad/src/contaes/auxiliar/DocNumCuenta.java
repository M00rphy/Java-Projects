/*    */ package contaes.auxiliar;
/*    */ 
/*    */ import javax.swing.text.AttributeSet;
/*    */ import javax.swing.text.BadLocationException;
/*    */ import javax.swing.text.PlainDocument;
/*    */ 
/*    */ public class DocNumCuenta extends PlainDocument
/*    */ {
/*    */   public void insertString(int offset, String str, AttributeSet a)
/*    */     throws BadLocationException
/*    */   {
/* 11 */     char[] insertChars = str.toCharArray();
/* 12 */     char[] existChars = getText(0, getLength()).toCharArray();
/*    */ 
/* 14 */     boolean valid = true;
/* 15 */     boolean fit = true;
/*    */ 
/* 17 */     if (insertChars[0] == '.') {
/* 18 */       if (existChars.length == 0)
/* 19 */         valid = false;
/*    */       else {
/* 21 */         for (int i = 0; i < existChars.length; i++) {
/* 22 */           if (existChars[i] == '.') {
/* 23 */             valid = false;
/* 24 */             break;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/* 29 */     if ((insertChars.length + getLength() <= 8) && (valid)) {
/* 30 */       for (int i = 0; i < insertChars.length; i++) {
/* 31 */         if ((!Character.isDigit(insertChars[i])) && (insertChars[i] != '.')) {
/* 32 */           valid = false;
/* 33 */           break;
/*    */         }
/*    */       }
/*    */     }
/*    */     else {
/* 38 */       fit = false;
/*    */     }
/* 40 */     if ((fit) && (valid))
/* 41 */       super.insertString(offset, str, a);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.DocNumCuenta
 * JD-Core Version:    0.6.2
 */