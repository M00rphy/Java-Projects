/*     */ package contaes.manejoDatos.auxiliar;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.filechooser.FileNameExtensionFilter;
/*     */ 
/*     */ public class DataBasesBackup
/*     */ {
/*  28 */   private String backup = "mysqldump";
/*  29 */   private String restore = "mysql";
/*     */   private String path;
/*  32 */   private boolean isPosible = false;
/*     */   private String sisOp;
/*     */ 
/*     */   public DataBasesBackup()
/*     */   {
/*  40 */     ConfiguracionBean c = new ConfiguracionBean();
/*  41 */     ArrayList lista = c.getConfig("<backprogrampath>");
/*  42 */     Properties sistema = System.getProperties();
/*  43 */     this.sisOp = sistema.getProperty("os.name").substring(0, 3);
/*  44 */     if ((lista.isEmpty()) && (!this.sisOp.equals("Lin"))) {
/*  45 */       JOptionPane.showMessageDialog(Inicio.frame, "No se ha especificado la ruta de instalación de MySQL.", "Error", 0);
/*     */     }
/*     */     else
/*     */     {
/*  50 */       if (this.sisOp.equals("Lin")) {
/*  51 */         this.path = "";
/*     */       }
/*     */       else {
/*  54 */         this.path = ((String)lista.get(0));
/*     */       }
/*     */ 
/*  57 */       if (this.sisOp.equals("Win"))
/*     */       {
/*  59 */         this.path = ("\"" + this.path + "\\");
/*  60 */         this.backup += "\"";
/*  61 */         this.restore += "\"";
/*     */       }
/*     */ 
/*  67 */       this.isPosible = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void make()
/*     */   {
/*  73 */     if ((this.isPosible) && (this.path != null)) {
/*  74 */       SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
/*  75 */       Date hoy = new Date();
/*  76 */       ManejoEmpresas mE = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa());
/*  77 */       LinkedList listaId = mE.listaIdEmpresas();
/*  78 */       String mandato = this.path + this.backup;
/*  79 */       List winCommands = new ArrayList();
/*  80 */       String opciones = " --databases --opt --user=" + Inicio.p.getUsuario() + " --password=" + Inicio.p.getPassword() + " almacen2 contaes";
/*     */ 
/*  83 */       winCommands.add("cmd");
/*  84 */       winCommands.add("/c");
/*  85 */       winCommands.add(mandato);
/*  86 */       winCommands.add("--databases");
/*  87 */       winCommands.add("--opt");
/*  88 */       winCommands.add("--user=" + Inicio.p.getUsuario());
/*  89 */       winCommands.add("--password=" + Inicio.p.getPassword());
/*  90 */       winCommands.add("almacen2");
/*  91 */       winCommands.add("contaes");
/*  92 */       for (Integer id : (List<Integer>)listaId) {
/*  93 */         opciones = opciones + " contaes" + id.toString();
/*  94 */         winCommands.add("contaes" + id.toString());
/*     */       }
/*  96 */       mandato = mandato + opciones;
/*  97 */       JFileChooser fc = new JFileChooser();
/*  98 */       fc.setSelectedFile(new File("contaes" + sdf.format(hoy) + ".sql"));
/*  99 */       int retorno = fc.showSaveDialog(Inicio.frame);
/* 100 */       if (retorno == 0) {
/* 101 */         File archivo = fc.getSelectedFile();
/* 102 */         archivo = AddExtension.sql(archivo);
/*     */ 
/* 104 */         mandato = mandato + " >" + archivo.getName() + "";
/*     */         ProcessBuilder pb;
/*     */         
/* 106 */         if (this.sisOp.equals("Win")) {
/* 107 */           winCommands.add(">" + archivo.getName());
/* 108 */           pb = new ProcessBuilder(winCommands);
/*     */         }
/*     */         else
/*     */         {
/* 112 */           pb = new ProcessBuilder(new String[] { "/bin/sh", "-c", mandato });
/* 113 */         }pb.directory(archivo.getParentFile());
/*     */         try {
/* 115 */           Process p = pb.start();
/* 116 */           p.waitFor();
/* 117 */           BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
/*     */ 
/* 119 */           String line = null;
/* 120 */           while ((line = in.readLine()) != null) {
/* 121 */             System.out.println(line);
/*     */           }
/* 123 */           if (p.exitValue() == 0) {
/* 124 */             JOptionPane.showMessageDialog(Inicio.frame, "Respaldo realizado con éxito", "Información", 1);
/*     */           }
/*     */           else
/*     */           {
/* 128 */             JOptionPane.showMessageDialog(Inicio.frame, "¡Error al realizar el respaldo!", "Error", 0);
/*     */           }
/*     */         }
/*     */         catch (IOException ex)
/*     */         {
/* 133 */           ex.printStackTrace();
/*     */         } catch (InterruptedException e) {
/* 135 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void restore()
/*     */   {
/* 143 */     if ((this.isPosible) && (this.path != null)) {
/* 144 */       JFileChooser chooser = new JFileChooser();
/* 145 */       FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos SQL", new String[] { "sql" });
/*     */ 
/* 147 */       chooser.setFileFilter(filter);
/* 148 */       int returnVal = chooser.showOpenDialog(Inicio.frame);
/* 149 */       if (returnVal == 0) {
/* 150 */         File archivo = chooser.getSelectedFile();
/* 151 */         String mandato = this.path + this.restore;
/* 152 */         List winCommands = new ArrayList();
/* 153 */         winCommands.add("cmd");
/* 154 */         winCommands.add("/c");
/* 155 */         winCommands.add(mandato);
/* 156 */         winCommands.add("--user=" + Inicio.p.getUsuario());
/* 157 */         winCommands.add("--password=" + Inicio.p.getPassword());
/* 158 */         winCommands.add("<" + archivo.getName());
/* 159 */         String opciones = " --user=" + Inicio.p.getUsuario() + " --password=" + Inicio.p.getPassword() + " <\"" + archivo.getName() + "\"";
/*     */ 
/* 161 */         mandato = mandato + opciones;
/*     */         try
/*     */         {
/*     */           ProcessBuilder pb;
/*     */           
/* 165 */           if (this.sisOp.equals("Win")) {
/* 166 */             pb = new ProcessBuilder(winCommands);
/*     */           }
/*     */           else
/* 169 */             pb = new ProcessBuilder(new String[] { "/bin/sh", "-c", mandato });
/* 170 */           pb.directory(archivo.getParentFile());
/* 171 */           Process p = pb.start();
/* 172 */           p.waitFor();
/* 173 */           if (p.exitValue() == 0) {
/* 174 */             JOptionPane.showMessageDialog(Inicio.frame, "Restauración realizada con éxito", "Información", 1);
/*     */           }
/*     */           else
/* 177 */             JOptionPane.showMessageDialog(Inicio.frame, "¡Error al realizar la restauración!", "Error", 0);
/*     */         }
/*     */         catch (IOException ex)
/*     */         {
/* 181 */           ex.printStackTrace();
/*     */         } catch (InterruptedException e) {
/* 183 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.DataBasesBackup
 * JD-Core Version:    0.6.2
 */