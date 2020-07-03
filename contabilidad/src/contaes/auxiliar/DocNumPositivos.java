/*    */ package contaes.auxiliar;
/*    */ 
/*    */ import javax.swing.text.AttributeSet;
/*    */ import javax.swing.text.BadLocationException;
/*    */ import javax.swing.text.PlainDocument;
/*    */ 
/*    */ public class DocNumPositivos extends PlainDocument
/*    */ {
/*    */   public void insertString(int arg0, String arg1, AttributeSet arg2)
/*    */     throws BadLocationException
/*    */   {
/* 37 */     char[] insertado = arg1.toCharArray();
/* 38 */     boolean valido = true;
/* 39 */     for (int x = 0; x < insertado.length; x++)
/* 40 */       if ((!Character.isDigit(insertado[x])) && (insertado[x] != '.') && (insertado[x] != ','))
/*    */       {
/* 42 */         valido = false;
/*    */       }
/* 44 */     if (valido)
/* 45 */       super.insertString(arg0, arg1, arg2);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.DocNumPositivos
 * JD-Core Version:    0.6.2
 */