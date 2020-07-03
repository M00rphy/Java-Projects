/*    */ package internationalization;
/*    */ 
/*    */ import java.util.MissingResourceException;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public class Mensajes
/*    */ {
/*  8 */   private static ResourceBundle recursos = ResourceBundle.getBundle("internationalization.Mensajes");
/*    */ 
/*    */   public static String getString(String key)
/*    */   {
/*    */     try
/*    */     {
/* 17 */       return recursos.getString(key);
/*    */     } catch (MissingResourceException e) {
/*    */     }
/* 20 */     return '!' + key + '!';
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     internationalization.Mensajes
 * JD-Core Version:    0.6.2
 */