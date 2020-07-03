/*    */ package contaes.auxiliar;
/*    */ 
/*    */ import contaes.MarcoInternoAsientosFacturas;
/*    */ import java.awt.Component;
/*    */ import java.awt.Font;
/*    */ import java.io.PrintStream;
/*    */ import java.net.URL;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JTree;
/*    */ import javax.swing.tree.DefaultTreeCellRenderer;
/*    */ 
/*    */ public class ArbolRenderer extends DefaultTreeCellRenderer
/*    */ {
/*    */   public ArbolRenderer()
/*    */   {
/* 40 */     setFont(new Font("Monospaced", 0, 12));
/*    */   }
/*    */ 
/*    */   public Component getTreeCellRendererComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5, boolean arg6)
/*    */   {
/* 45 */     super.getTreeCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
/*    */ 
/* 47 */     ImageIcon carpetaCerrada = createImageIcon("/contaes/iconos/folderClosed.png");
/* 48 */     ImageIcon carpetaAbierta = createImageIcon("/contaes/iconos/folderOpen.png");
/* 49 */     ImageIcon nodoFinal = createImageIcon("/contaes/iconos/treeLeaf.png");
/* 50 */     if (carpetaCerrada != null)
/* 51 */       setClosedIcon(carpetaCerrada);
/* 52 */     if (carpetaAbierta != null)
/* 53 */       setOpenIcon(carpetaAbierta);
/* 54 */     if (nodoFinal != null)
/* 55 */       setLeafIcon(nodoFinal);
/* 56 */     return this;
/*    */   }
/*    */ 
/*    */   private static ImageIcon createImageIcon(String path) {
/* 60 */     URL imgURL = MarcoInternoAsientosFacturas.class.getResource(path);
/* 61 */     if (imgURL != null) {
/* 62 */       return new ImageIcon(imgURL);
/*    */     }
/* 64 */     System.err.println("Couldn't find file: " + path);
/* 65 */     return null;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.ArbolRenderer
 * JD-Core Version:    0.6.2
 */