/*    */ package contaes.auxiliar;
/*    */ 
/*    */ import java.awt.Container;
/*    */ import java.beans.PropertyVetoException;
/*    */ import javax.swing.DefaultDesktopManager;
/*    */ import javax.swing.JDesktopPane;
/*    */ import javax.swing.JInternalFrame;
/*    */ import javax.swing.JInternalFrame.JDesktopIcon;
/*    */ 
/*    */ public class MyDefaultDesktopManager extends DefaultDesktopManager
/*    */ {
/*    */   public void activateFrame(JInternalFrame f)
/*    */   {
/* 28 */     if (f != null) {
/* 29 */       Container p = f.getParent();
/*    */ 
/* 31 */       JDesktopPane d = f.getDesktopPane();
/* 32 */       JInternalFrame currentlyActiveFrame = d == null ? null : d.getSelectedFrame();
/*    */ 
/* 35 */       if (p == null)
/*    */       {
/* 37 */         p = f.getDesktopIcon().getParent();
/* 38 */         if (p == null) {
/* 39 */           return;
/*    */         }
/*    */       }
/* 42 */       if (currentlyActiveFrame == null) {
/* 43 */         if (d != null) d.setSelectedFrame(f); 
/*    */       }
/* 44 */       else if (currentlyActiveFrame != f)
/*    */       {
/* 47 */         if (currentlyActiveFrame.isSelected())
/*    */           try {
/* 49 */             currentlyActiveFrame.setSelected(false);
/*    */           }
/*    */           catch (PropertyVetoException e2) {
/*    */           }
/* 53 */         if (d != null) d.setSelectedFrame(f);
/*    */       }
/* 55 */       f.moveToFront();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.MyDefaultDesktopManager
 * JD-Core Version:    0.6.2
 */