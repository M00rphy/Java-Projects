/*    */ package almacen2.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.imageio.ImageIO;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class Imagen extends JPanel
/*    */ {
/* 15 */   static final Color bg = Color.white;
/* 16 */   private String referencia = "";
/* 17 */   private BufferedImage img = null;
/* 18 */   private boolean error = false;
/*    */ 
/*    */   public Imagen()
/*    */   {
/* 25 */     initialize();
/*    */   }
/*    */ 
/*    */   private void initialize()
/*    */   {
/* 34 */     setBackground(bg);
/* 35 */     setLayout(null);
/* 36 */     setBounds(new Rectangle(0, 0, 130, 130));
/* 37 */     actualizar();
/*    */   }
/*    */ 
/*    */   public void setReferencia(String referencia) {
/* 41 */     this.referencia = referencia;
/* 42 */     actualizar();
/*    */   }
/*    */ 
/*    */   public void setImage(BufferedImage img) {
/* 46 */     if (img != null) {
/* 47 */       this.img = img;
/*    */     }
/*    */     else {
/* 50 */       cargarImagen();
/*    */     }
/* 52 */     repaint();
/*    */   }
/*    */ 
/*    */   private void actualizar() {
/* 56 */     cargarImagen();
/* 57 */     repaint();
/*    */   }
/*    */ 
/*    */   private void cargarImagen() {
/* 61 */     File archivo = new File("images/" + this.referencia + ".png");
/* 62 */     if (!archivo.exists())
/* 63 */       archivo = new File("images/imagenNoDisponible.jpg");
/*    */     try
/*    */     {
/* 66 */       this.img = ImageIO.read(archivo);
/* 67 */       this.error = false;
/*    */     } catch (IOException e) {
/* 69 */       this.error = true;
/* 70 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void paint(Graphics g)
/*    */   {
/* 76 */     super.paint(g);
/* 77 */     if (!this.error)
/* 78 */       g.drawImage(this.img, 0, 0, 130, 130, null);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.Imagen
 * JD-Core Version:    0.6.2
 */