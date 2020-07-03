/*    */ package pos.view;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ 
/*    */ public class JNumberEvent extends EventObject
/*    */ {
/*    */   private char m_cKey;
/*    */ 
/*    */   public JNumberEvent(Object source, char cKey)
/*    */   {
/* 34 */     super(source);
/* 35 */     this.m_cKey = cKey;
/*    */   }
/*    */ 
/*    */   public char getKey() {
/* 39 */     return this.m_cKey;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.JNumberEvent
 * JD-Core Version:    0.6.2
 */