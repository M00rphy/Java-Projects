/*    */ package contaes.manejoDatos.funciones;
/*    */ 
/*    */ import contaes.Inicio;
/*    */ import contaes.MarcoInicio;
/*    */ import contaes.Puente;
/*    */ import contaes.manejoDatos.ManejoSubcuentas;
/*    */ import contaes.manejoDatos.auxiliar.LeerFichero;
/*    */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*    */ import java.awt.Cursor;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import javax.swing.JFileChooser;
/*    */ import javax.swing.JOptionPane;
/*    */ 
/*    */ public class ImportarSubcuentas
/*    */ {
    ResultSet rs ;
/*    */   public void importar()
/*    */   {
/* 26 */     boolean cambios = false;
/* 27 */     JFileChooser fc = new JFileChooser();
/* 28 */     int retorno = fc.showOpenDialog(Inicio.frame);
/* 29 */     if (retorno == 0) {
/* 30 */       File archivo = fc.getSelectedFile();
/* 31 */       ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*    */       try {
/* 33 */         LeerFichero entrada = new LeerFichero(archivo.getCanonicalPath());
/*    */ 
/* 35 */         Inicio.frame.setCursor(Cursor.getPredefinedCursor(3));
/*    */         String linea;
/* 36 */         while ((linea = entrada.leer()) != null) {
/* 37 */           String codigo = linea.substring(0, linea.indexOf(';'));
/* 38 */           String nombre = linea.substring(linea.indexOf(';') + 1);
/* 39 */           nombre = nombre.replaceAll("\"", "");
/* 40 */           nombre = nombre.replaceAll(";", "");
/* 41 */           if (codigo.length() == 8)
/*    */             try {
/* 43 */               int codigoSub = Integer.parseInt(codigo);
/* 44 */               String codBal = getCodBal(codigo.substring(0, 4));
/* 45 */               if (!mS.crear(codigoSub, nombre, codBal)) {
/* 46 */                 showMensaje("Subcuenta: " + codigo + ": " + nombre + "\nNO creada. Es posible que ya exista");
/*    */               }
/*    */               else
/*    */               {
/* 50 */                 cambios = true;
/*    */               }
/*    */             } catch (NumberFormatException ex) {
/* 53 */               showMensaje("El código de subcuenta:" + codigo + "\nno es un número.");
/*    */             }
/*    */           else {
/* 56 */             showMensaje("Longitud de código de subcuenta no válida para:\n" + codigo + ": " + nombre);
/*    */           }
/*    */         }
/* 59 */         if (cambios) {
/* 60 */           Inicio.frame.renovarTabla(3);
/* 61 */           RegeneraSaldos.regenera(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 62 */           System.gc();
/* 63 */           JOptionPane.showMessageDialog(Inicio.frame, "Importación finalizada", "Información", 1);
/*    */         }
/* 65 */         Inicio.frame.setCursor(Cursor.getPredefinedCursor(0));
/*    */       } catch (IOException ex) {
/* 67 */         showMensaje("Hubo algún error al importar los datos:\n" + ex.getMessage());
/*    */       }
/* 69 */       mS.cerrarRs();
/*    */     }
/*    */   }
/*    */ 
/*    */   private String getCodBal(String codigo) {
/* 74 */     String codBal = "";
/*    */     try
/*    */     {
/* 77 */       rs = Inicio.getCGeneral().getRes("SELECT grupo_bal FROM plan_contable WHERE codigo = " + codigo);
/* 78 */       if (rs.next()) {
/* 79 */         codBal = rs.getString(1);
/*    */       }
/* 81 */       rs.close();
/*    */     } catch (SQLException ex) {
/* 83 */       Logger.getLogger(ImportarSubcuentas.class.getName()).log(Level.SEVERE, null, ex);
/*    */     }
/* 85 */     ResultSet rs = null;
/* 86 */     if (!codBal.equals("")) {
/* 87 */       return codBal;
/*    */     }
/* 89 */     if (codigo.length() > 1) {
/* 90 */       return getCodBal(codigo.substring(0, codigo.length() - 1));
/*    */     }
/*    */ 
/* 93 */     return "";
/*    */   }
/*    */ 
/*    */   private void showMensaje(String mensaje)
/*    */   {
/* 98 */     JOptionPane.showMessageDialog(Inicio.frame, mensaje, "Error", 0);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.ImportarSubcuentas
 * JD-Core Version:    0.6.2
 */