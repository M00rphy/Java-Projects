/*     */ package contaes;
/*     */ 
/*     */ import com.nilo.plaf.nimrod.NimRODLookAndFeel;
/*     */ import com.nilo.plaf.nimrod.NimRODTheme;
/*     */ import contaes.dialogosAuxiliares.DonacionesJDialog;
/*     */ import contaes.dialogosFunciones.Calculadora;
/*     */ import contaes.empresas.UsuariosJDialog;
/*     */ import contaes.manejoDatos.auxiliar.ConfiguracionBean;
/*     */ import contaes.manejoDatos.auxiliar.Registro;
/*     */ import contaes.manejoDatos.funciones.BuildPlanTree;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Color;
/*     */ import java.awt.Frame;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Properties;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class Inicio
/*     */ {
/*  52 */   public static Puente p = new Puente();
/*     */   public static MarcoInicio frame;
/*     */   public static Calculadora calculadora;
/*     */   public static JTree arbolPC;
/*  56 */   private static contaes.manejoDatos.auxiliar.MySQLConection cEmpresa = null;
/*  57 */   private static contaes.manejoDatos.auxiliar.MySQLConection cFacturacion = null;
/*  58 */   private static contaes.manejoDatos.auxiliar.MySQLConection cGeneral = null;
/*  59 */   private static almacen2.data.MySQLConection cAlmacen = null;
/*     */ 
/*     */   public Inicio(boolean POS)
/*     */   {
/*     */     try
/*     */     {
/*  67 */       Class.forName("com.mysql.jdbc.Driver");
/*     */     } catch (ClassNotFoundException exc) {
/*  69 */       JOptionPane.showMessageDialog(new JFrame(), "¡Error al registrar el controlador para MySQL!", "Error", 0);
/*     */ 
/*  71 */       System.exit(1);
/*     */     }
/*     */ 
/*  74 */     p.setModoPOS(POS);
/*     */ 
/*  76 */     System.setProperty("apple.laf.useScreenMenuBar", "true");
/*     */ 
/*  78 */     UsuariosJDialog.seleccionDeUsuario(POS);
/*  79 */     comprobarInstalacion();
/*     */ 
/*  81 */     cGeneral = new contaes.manejoDatos.auxiliar.MySQLConection();
/*  82 */     cAlmacen = new almacen2.data.MySQLConection();
/*  83 */     crearArbolPGC();
/*  84 */     checkVersion();
/*  85 */     p.setRegistro(Registro.isRegistrado());
/*  86 */     frame = new MarcoInicio();
/*  87 */     calculadora = new Calculadora();
/*  88 */     calculadora.validate();
/*  89 */     frame.validate();
/*  90 */     frame.setVisible(true);
/*  91 */     frame.colocarInfo();
/*     */   }
/*     */ 
/*     */   private void comprobarInstalacion()
/*     */   {
/* 101 */     Instalar instalacion = new Instalar();
/* 102 */     int retorno = instalacion.yaInstalado();
/* 103 */     if ((retorno != 1) && (retorno != -2)) {
/* 104 */       int confirma = JOptionPane.showConfirmDialog(new Frame(), Mensajes.getString("instala1"), Mensajes.getString("instala2"), 0);
/*     */ 
/* 107 */       if (confirma == 0) {
/* 108 */         if (!instalacion.instalacion()) {
/* 109 */           JOptionPane.showMessageDialog(new Frame(), Mensajes.getString("instala4"));
/*     */ 
/* 111 */           System.exit(0);
/*     */         }
/* 113 */         DonacionesJDialog.recordarDonaciones();
/*     */       }
/* 115 */     } else if (retorno == -2) {
/* 116 */       JOptionPane.showMessageDialog(new Frame(), Mensajes.getString("errorSQLGeneral") + "\n" + instalacion.getError());
/*     */ 
/* 118 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkVersion()
/*     */   {
/* 124 */     String actualVersion = "2.5";
/* 125 */     ArrayList array = new ArrayList();
/* 126 */     ConfiguracionBean config = new ConfiguracionBean();
/* 127 */     array = config.getConfig("<version>");
/* 128 */     if (array.isEmpty()) {
/* 129 */       array.add(actualVersion);
/* 130 */       config.saveConfig("<version>", array);
/*     */     }
/*     */     else {
/* 133 */       String anteriorVersion = (String)array.get(0);
/* 134 */       if (!((String)array.get(0)).equals(actualVersion))
/*     */       {
/* 136 */         array.clear();
/* 137 */         array.add(actualVersion);
/* 138 */         config.saveConfig("<version>", array);
/*     */       }
/*     */ 
/* 142 */       if (anteriorVersion.equals("2.1")) {
/* 143 */         Instalar instalacion = new Instalar();
/* 144 */         instalacion.de21a22();
/*     */       }
/* 146 */       if ((anteriorVersion.equals("2.0")) || (anteriorVersion.equals("2.1")) || (anteriorVersion.equals("2.2")) || (anteriorVersion.equals("2.3")) || (anteriorVersion.equals("2.4")))
/*     */       {
/* 149 */         Instalar instalacion = new Instalar();
/* 150 */         instalacion.de24a241();
/*     */       }
/* 152 */       if ((anteriorVersion.equals("2.0")) || (anteriorVersion.equals("2.1")) || (anteriorVersion.equals("2.2")) || (anteriorVersion.equals("2.3")) || (anteriorVersion.equals("2.4")) || (anteriorVersion.equals("2.4.1")))
/*     */       {
/* 155 */         Instalar instalacion = new Instalar();
/* 156 */         instalacion.de241a242();
/*     */       }
/* 158 */       if ((anteriorVersion.equals("2.0")) || (anteriorVersion.equals("2.1")) || (anteriorVersion.equals("2.2")) || (anteriorVersion.equals("2.3")) || (anteriorVersion.equals("2.4")) || (anteriorVersion.equals("2.4.1")) || (anteriorVersion.equals("2.4.2")) || (anteriorVersion.equals("2.4.3")) || (anteriorVersion.equals("2.4.4")))
/*     */       {
/* 163 */         Instalar instalacion = new Instalar();
/* 164 */         instalacion.de244a245();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static contaes.manejoDatos.auxiliar.MySQLConection getCEmpresa()
/*     */   {
/* 171 */     return cEmpresa;
/*     */   }
/*     */ 
/*     */   public static void setCEmpresa(int empresa) {
/* 175 */     if (cEmpresa != null) {
/* 176 */       cEmpresa.cierraConexion();
/*     */     }
/* 178 */     cEmpresa = new contaes.manejoDatos.auxiliar.MySQLConection(empresa);
/*     */   }
/*     */ 
/*     */   public static contaes.manejoDatos.auxiliar.MySQLConection getCFacturacion() {
/* 182 */     return cFacturacion;
/*     */   }
/*     */ 
/*     */   public static void setCFacturacion(int empresa) {
/* 186 */     if (cFacturacion != null) {
/* 187 */       cFacturacion.cierraConexion();
/*     */     }
/* 189 */     cFacturacion = new contaes.manejoDatos.auxiliar.MySQLConection(empresa);
/*     */   }
/*     */ 
/*     */   public static contaes.manejoDatos.auxiliar.MySQLConection getCGeneral() {
/* 193 */     return cGeneral;
/*     */   }
/*     */ 
/*     */   public static almacen2.data.MySQLConection getcAlmacen() {
/* 197 */     return cAlmacen;
/*     */   }
/*     */ 
/*     */   public static JTree getArbolPGC() {
/* 201 */     return arbolPC;
/*     */   }
/*     */ 
/*     */   private void crearArbolPGC() {
/* 205 */     ExecutorService exec = Executors.newSingleThreadExecutor();
/* 206 */     exec.execute(new TreeCreator());
/* 207 */     exec.shutdown();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 218 */     final String[] opcion = args;
/* 219 */     SwingUtilities.invokeLater(new Runnable()
/*     */     {
/*     */       public void run() {
/* 222 */         boolean POS = false;
/* 223 */         int n = opcion.length;
/* 224 */         if ((n > 0) && 
/* 225 */           (opcion[0].equals("POS"))) {
/* 226 */           POS = true;
/*     */         }
/*     */         try
/*     */         {
/* 230 */           Properties sistema = System.getProperties();
/* 231 */           String sisOp = sistema.getProperty("os.name").substring(0, 3);
/* 232 */           if (sisOp.equals("Lin")) {
/* 233 */             NimRODTheme nt = new NimRODTheme();
/* 234 */             nt.setSecondary3(new Color(226, 216, 207));
/*     */ 
/* 236 */             nt.setPrimary2(new Color(156, 100, 77));
/* 237 */             nt.setPrimary3(new Color(189, 189, 189));
/* 238 */             NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
/* 239 */             NimRODLookAndFeel.setCurrentTheme(nt);
 //JFrame.setDefaultLookAndFeelDecorated(true);
 
 //aki iba NimRODLF
/* 240 */        //     UIManager.setLookAndFeel(NimRODLF);
UIManager.setLookAndFeel(UIManager.getDefaults().toString());
  // UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
/*     */           }
/*     */           else {
/* 243 */           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*     */           }
/*     */         } catch (ClassNotFoundException exc1) {
/* 246 */           System.out.println(exc1.getMessage());
/*     */         } catch (InstantiationException exc2) {
/* 248 */           System.out.println(exc2.getMessage());
/*     */         } catch (IllegalAccessException exc3) {
/* 250 */           System.out.println(exc3.getMessage());
/*     */         } catch (UnsupportedLookAndFeelException exc4) {
/* 252 */           System.out.println(exc4.getMessage());
/*     */         }
/* 254 */         new Inicio(POS);
/*     */       } } );
/*     */   }
/*     */ 
/*     */   class TreeCreator implements Runnable {
/*     */     TreeCreator() {
/*     */     }
/*     */ 
/* 262 */     public void run() { Inicio.arbolPC = new BuildPlanTree().getArbol(); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.Inicio
 * JD-Core Version:    0.6.2
 */