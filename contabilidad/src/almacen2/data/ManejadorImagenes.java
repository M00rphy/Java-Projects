/*    */ package almacen2.data;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.sql.Blob;
/*    */ import java.sql.Connection;
/*    */ import java.sql.PreparedStatement;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ public class ManejadorImagenes
/*    */ {
/*    */   private MySQLConection conexion;
/*    */   private ResultSet res;
/*    */ 
/*    */   public ManejadorImagenes(MySQLConection conexion)
/*    */   {
/* 32 */     this.conexion = conexion;
/*    */   }
/*    */ 
/*    */   public BufferedImage getImage(String referencia) {
/* 36 */     BufferedImage img = null;
/*    */     try {
/* 38 */       this.res = this.conexion.getRes("SELECT imagen FROM Producto WHERE referencia = " + referencia);
/* 39 */       if (this.res.next()) {
/* 40 */         Blob imagenBD = this.res.getBlob(1);
/* 41 */         img = ImageIO.read(imagenBD.getBinaryStream());
/*    */       }
/*    */     }
/*    */     catch (IOException ex) {
/* 45 */       ex.printStackTrace();
/*    */     }
/*    */     catch (SQLException e) {
/* 48 */       e.printStackTrace();
/*    */     }
/* 50 */     return img;
/*    */   }
/*    */ 
/*    */   public void setImage(File archivo, String referencia) {
/* 54 */     if (archivo != null)
/*    */       try {
/* 56 */         FileInputStream flujoEntreda = new FileInputStream(archivo);
/* 57 */         String SqlCommand = "UPDATE Producto SET imagen = ? WHERE referencia = ?";
/* 58 */         PreparedStatement stmt = this.conexion.getCon().prepareStatement(SqlCommand);
/* 59 */         stmt.setBinaryStream(1, flujoEntreda, (int)archivo.length());
/* 60 */         stmt.setString(2, referencia);
/* 61 */         stmt.execute();
/*    */       }
/*    */       catch (FileNotFoundException ex) {
/* 64 */         Logger.getLogger(ManejadorImagenes.class.getName()).log(Level.SEVERE, null, ex);
/*    */       }
/*    */       catch (SQLException e) {
/* 67 */         e.printStackTrace();
/*    */       }
/*    */   }
/*    */ 
/*    */   public void cerrarRs()
/*    */   {
/* 73 */     if (this.res != null)
/*    */       try {
/* 75 */         this.res.close();
/*    */       } catch (SQLException ex) {
/* 77 */         Logger.getLogger(ManejadorImagenes.class.getName()).log(Level.SEVERE, null, ex);
/*    */       }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.data.ManejadorImagenes
 * JD-Core Version:    0.6.2
 */