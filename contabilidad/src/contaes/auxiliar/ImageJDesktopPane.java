/*    */ package contaes.auxiliar;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JDesktopPane;
/*    */ 
/*    */ public class ImageJDesktopPane extends JDesktopPane
/*    */ {
/*  9 */   ImageIcon image = null;
/* 10 */   boolean fillEntireArea = false;
/* 11 */   boolean tileImage = false;
/*    */ 
/*    */   public void setFillEntireArea(boolean b)
/*    */   {
/* 18 */     this.fillEntireArea = b;
/* 19 */     repaint();
/*    */   }
/*    */ 
/*    */   public boolean getFillEntireArea() {
/* 23 */     return this.fillEntireArea;
/*    */   }
/*    */ 
/*    */   public void setTileImage(boolean b) {
/* 27 */     this.tileImage = b;
/* 28 */     repaint();
/*    */   }
/*    */ 
/*    */   public boolean getTileImage() {
/* 32 */     return this.tileImage;
/*    */   }
/*    */ 
/*    */   public void setImage(ImageIcon i) {
/* 36 */     this.image = i;
/* 37 */     repaint();
/*    */   }
/*    */ 
/*    */   public ImageIcon getImage() {
/* 41 */     return this.image;
/*    */   }
/*    */ 
/*    */   public void paintComponent(Graphics g) {
/* 45 */     if (isOpaque()) {
/* 46 */       super.paintComponent(g);
/* 47 */       if (this.image != null) {
/* 48 */         int width = getWidth(); int height = getHeight();
/*    */ 
/* 50 */         g.setColor(getBackground());
/* 51 */         g.fillRect(0, 0, width, height);
/* 52 */         if (this.fillEntireArea) {
/* 53 */           g.drawImage(this.image.getImage(), 0, 0, width, height, this);
/*    */         }
/* 55 */         else if (!this.tileImage) {
/* 56 */           g.drawImage(this.image.getImage(), (width - this.image.getIconWidth()) / 2, (height - this.image.getIconHeight()) / 2, this);
/*    */         }
/*    */         else
/*    */         {
/* 61 */           int tileW = this.image.getIconWidth();
/* 62 */           int tileH = this.image.getIconHeight();
/* 63 */           for (int ypos = 0; height - ypos > 0; ypos += tileH)
/* 64 */             for (int xpos = 0; width - xpos > 0; xpos += tileW)
/* 65 */               this.image.paintIcon(null, g, xpos, ypos);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.ImageJDesktopPane
 * JD-Core Version:    0.6.2
 */