/*     */ package contaes;
/*     */ 
/*     */ import contaes.dialogosFunciones.Calculadora;
/*     */ import contaes.empresas.UsuariosJDialog;
/*     */ import contaes.manejoDatos.auxiliar.ConfiguracionBean;
/*     */ import contaes.manejoDatos.funciones.BuildPlanTree;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Toolkit;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class OldInicio
/*     */ {
/*  50 */   public static Puente p = new Puente();
/*     */   public static MarcoInicio frame;
/*     */   public static Calculadora calculadora;
/*     */   public static JTree arbolPC;
/*  54 */   private static contaes.manejoDatos.auxiliar.MySQLConection cEmpresa = null;
/*  55 */   private static contaes.manejoDatos.auxiliar.MySQLConection cFacturacion = null;
/*  56 */   private static contaes.manejoDatos.auxiliar.MySQLConection cGeneral = null;
/*  57 */   private static almacen2.data.MySQLConection cAlmacen = null;
/*     */ 
/*     */   public OldInicio(boolean POS)
/*     */   {
/*     */     try
/*     */     {
/*  65 */       Class.forName("com.mysql.jdbc.Driver");
/*     */     } catch (ClassNotFoundException exc) {
/*  67 */       System.out.println("Error al registrar driver: " + exc.toString());
/*     */ 
/*  69 */       System.exit(1);
/*     */     }
/*     */ 
/*  78 */     p.setModoPOS(POS);
/*  79 */     System.setProperty("apple.laf.useScreenMenuBar", "true");
/*  80 */     UsuariosJDialog dlg = new UsuariosJDialog(POS);
/*  81 */     mostrarDialog(dlg);
/*  82 */     comprobarInstalacion();
/*  83 */     setCGeneral();
/*  84 */     setcAlmacen();
/*  85 */     setArbolPC();
/*  86 */     checkVersion();
/*  87 */     frame = new MarcoInicio();
/*  88 */     calculadora = new Calculadora();
/*  89 */     colocarMarco(calculadora);
/*  90 */     frame.validate();
/*  91 */     frame.setVisible(true);
/*     */   }
/*     */ 
/*     */   private void comprobarInstalacion()
/*     */   {
/* 101 */     Instalar instalacion = new Instalar();
/* 102 */     int retorno = instalacion.yaInstalado();
/* 103 */     if ((retorno != 1) && (retorno != -2)) {
/* 104 */       int confirma = JOptionPane.showConfirmDialog(new Frame(), Mensajes.getString("instala1"), Mensajes.getString("instala2"), 0);
/*     */ 
/* 107 */       if ((confirma == 0) && 
/* 108 */         (!instalacion.instalacion())) {
/* 109 */         JOptionPane.showMessageDialog(new Frame(), Mensajes.getString("instala4"));
/*     */ 
/* 111 */         System.exit(0);
/*     */       }
/*     */     }
/* 114 */     else if (retorno == -2) {
/* 115 */       JOptionPane.showMessageDialog(new Frame(), Mensajes.getString("errorSQLGeneral"));
/*     */ 
/* 117 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkVersion() {
/* 122 */     String actualVersion = "2.1";
/* 123 */     ArrayList array = new ArrayList();
/* 124 */     ConfiguracionBean config = new ConfiguracionBean();
/* 125 */     array = config.getConfig("<version>");
/* 126 */     if (array.isEmpty()) {
/* 127 */       array.add(actualVersion);
/* 128 */       config.saveConfig("<version>", array);
/*     */     }
/* 130 */     else if (!((String)array.get(0)).equals(actualVersion))
/*     */     {
/* 132 */       array.clear();
/* 133 */       array.add(actualVersion);
/* 134 */       config.saveConfig("<version>", array);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void colocarMarco(JFrame marco) {
/* 139 */     marco.validate();
/* 140 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 141 */     Dimension frameSize = marco.getSize();
/* 142 */     if (frameSize.height > screenSize.height) {
/* 143 */       frameSize.height = screenSize.height;
/*     */     }
/* 145 */     if (frameSize.width > screenSize.width) {
/* 146 */       frameSize.width = screenSize.width;
/*     */     }
/* 148 */     marco.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */   }
/*     */ 
/*     */   private void mostrarDialog(JDialog dlg)
/*     */   {
/* 153 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 154 */     Dimension frameSize = dlg.getSize();
/* 155 */     if (frameSize.height > screenSize.height) {
/* 156 */       frameSize.height = screenSize.height;
/*     */     }
/* 158 */     if (frameSize.width > screenSize.width) {
/* 159 */       frameSize.width = screenSize.width;
/*     */     }
/* 161 */     dlg.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/*     */ 
/* 163 */     dlg.pack();
/* 164 */     dlg.setVisible(true);
/*     */   }
/*     */ 
/*     */   public static contaes.manejoDatos.auxiliar.MySQLConection getCEmpresa() {
/* 168 */     return cEmpresa;
/*     */   }
/*     */ 
/*     */   public static void setCEmpresa(int empresa) {
/* 172 */     if (cEmpresa != null) {
/* 173 */       cEmpresa.cierraConexion();
/*     */     }
/* 175 */     cEmpresa = new contaes.manejoDatos.auxiliar.MySQLConection(empresa);
/*     */   }
/*     */ 
/*     */   public static contaes.manejoDatos.auxiliar.MySQLConection getCFacturacion() {
/* 179 */     return cFacturacion;
/*     */   }
/*     */ 
/*     */   public static void setCFacturacion(int empresa) {
/* 183 */     if (cFacturacion != null) {
/* 184 */       cFacturacion.cierraConexion();
/*     */     }
/* 186 */     cFacturacion = new contaes.manejoDatos.auxiliar.MySQLConection(empresa);
/*     */   }
/*     */ 
/*     */   public static contaes.manejoDatos.auxiliar.MySQLConection getCGeneral() {
/* 190 */     return cGeneral;
/*     */   }
/*     */ 
/*     */   public static void setCGeneral() {
/* 194 */     cGeneral = new contaes.manejoDatos.auxiliar.MySQLConection();
/*     */   }
/*     */ 
/*     */   public static almacen2.data.MySQLConection getcAlmacen() {
/* 198 */     return cAlmacen;
/*     */   }
/*     */ 
/*     */   public static void setcAlmacen() {
/* 202 */     cAlmacen = new almacen2.data.MySQLConection();
/*     */   }
/*     */ 
/*     */   public static JTree getArbolPC() {
/* 206 */     return arbolPC;
/*     */   }
/*     */ 
/*     */   private void setArbolPC()
/*     */   {
/* 212 */     ExecutorService exec = Executors.newSingleThreadExecutor();
/* 213 */     exec.execute(new TreeCreator());
/* 214 */     exec.shutdown();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 225 */     final String[] opcion = args;
/* 226 */     SwingUtilities.invokeLater(new Runnable()
/*     */     {
/*     */       public void run() {
/* 229 */         boolean POS = false;
/* 230 */         int n = opcion.length;
/* 231 */         int opcionSelected = 0;
/* 232 */         if (n > 0) {
/* 233 */           if (opcion[0].equals("POS"))
/* 234 */             POS = true;
/*     */           else {
/*     */             try
/*     */             {
/* 238 */               opcionSelected = Integer.parseInt(opcion[0]);
/*     */             }
/*     */             catch (NumberFormatException e)
/*     */             {
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */         try
/*     */         {
/* 252 */           switch (opcionSelected) {
/*     */           case 0:
/* 254 */             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/* 255 */             break;
/*     */           case 1:
/* 257 */             UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
/* 258 */             break;
/*     */           case 2:
/* 260 */             UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
/*     */           }
/*     */ 
/*     */         }
/*     */         catch (ClassNotFoundException exc1)
/*     */         {
/* 268 */           System.out.println(exc1.getMessage());
/*     */         } catch (InstantiationException exc2) {
/* 270 */           System.out.println(exc2.getMessage());
/*     */         } catch (IllegalAccessException exc3) {
/* 272 */           System.out.println(exc3.getMessage());
/*     */         } catch (UnsupportedLookAndFeelException exc4) {
/* 274 */           System.out.println(exc4.getMessage());
/*     */         }
/*     */ 
/* 277 */         new OldInicio(POS);
/*     */       } } );
/*     */   }
/*     */ 
/*     */   class TreeCreator implements Runnable {
/*     */     TreeCreator() {
/*     */     }
/*     */ 
/* 285 */     public void run() { OldInicio.arbolPC = new BuildPlanTree().getArbol(); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.OldInicio
 * JD-Core Version:    0.6.2
 */