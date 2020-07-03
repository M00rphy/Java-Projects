/*    */ package pos.model;
/*    */ 
/*    */ public class BasicException extends Exception
/*    */ {
/*    */   public BasicException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BasicException(String msg)
/*    */   {
/* 31 */     super(msg);
/*    */   }
/*    */ 
/*    */   public BasicException(String msg, Throwable cause) {
/* 35 */     super(msg, cause);
/*    */   }
/*    */ 
/*    */   public BasicException(Throwable cause) {
/* 39 */     super(cause);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.model.BasicException
 * JD-Core Version:    0.6.2
 */