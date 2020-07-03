/*    */ package pos.view.editors;
/*    */ 
/*    */ import pos.model.BasicException;
/*    */ 
/*    */ public class JEditorPassword extends JEditorText
/*    */ {
/*    */   private static final char ECHO_CHAR = '*';
/*    */ 
/*    */   protected final int getMode()
/*    */   {
/* 34 */     return 0;
/*    */   }
/*    */ 
/*    */   protected int getStartMode() {
/* 38 */     return 0;
/*    */   }
/*    */ 
/*    */   protected String getTextEdit()
/*    */   {
/* 44 */     StringBuffer s = new StringBuffer();
/* 45 */     s.append("<html>");
/* 46 */     s.append(getEcho(this.m_svalue));
/* 47 */     if (this.m_cLastChar != 0) {
/* 48 */       s.append("<font color=\"#a0a0a0\">");
/* 49 */       s.append('*');
/* 50 */       s.append("</font>");
/*    */     }
/* 52 */     s.append("<font color=\"#a0a0a0\">_</font>");
/*    */ 
/* 54 */     return s.toString();
/*    */   }
/*    */ 
/*    */   public final String getPassword()
/*    */   {
/* 60 */     String sPassword = getText();
/* 61 */     return sPassword == null ? "" : sPassword;
/*    */   }
/*    */ 
/*    */   protected String getTextFormat() throws BasicException {
/* 65 */     return "<html>" + getEcho(this.m_svalue);
/*    */   }
/*    */ 
/*    */   private String getEcho(String sValue)
/*    */   {
/* 70 */     if (sValue == null) {
/* 71 */       return "";
/*    */     }
/* 73 */     char[] c = new char[sValue.length()];
/* 74 */     for (int i = 0; i < sValue.length(); i++) {
/* 75 */       c[i] = '*';
/*    */     }
/* 77 */     return new String(c);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.editors.JEditorPassword
 * JD-Core Version:    0.6.2
 */