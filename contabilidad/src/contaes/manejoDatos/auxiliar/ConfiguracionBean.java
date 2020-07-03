/*     */ package contaes.manejoDatos.auxiliar;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class ConfiguracionBean
/*     */ {
/*  15 */   ArrayList<String> texto = new ArrayList();
/*     */ 
/*     */   private void saveFile()
/*     */   {
/*     */     try
/*     */     {
/*  32 */       FileWriter archivo = new FileWriter("configdir/userprefs.ini");
/*  33 */       for (String string : this.texto) {
/*  34 */         archivo.write(new StringBuilder().append(string).append("\n").toString());
/*     */       }
/*  36 */       archivo.close();
/*     */     } catch (IOException ex) {
/*  38 */       Logger.getLogger(ConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void readFile() {
/*  43 */     this.texto.clear();
/*     */     try {
/*  45 */       BufferedReader archivo = new BufferedReader(new InputStreamReader(new FileInputStream("configdir/userprefs.ini")));
/*     */       String linea;
/*  47 */       while ((linea = archivo.readLine()) != null) {
/*  48 */         this.texto.add(linea);
/*     */       }
/*  50 */       archivo.close();
/*     */     } catch (IOException ex) {
/*  52 */       Logger.getLogger(ConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private String finishConfigName(String configName) {
/*  57 */     String finishConfig = configName.replace("<", "<!");
/*  58 */     return finishConfig;
/*     */   }
/*     */ 
/*     */   public ArrayList<String> getConfig(String configName) {
/*  62 */     ArrayList config = new ArrayList();
/*  63 */     readFile();
/*  64 */     if (!this.texto.isEmpty()) {
/*  65 */       int inicio = this.texto.indexOf(configName);
/*  66 */       int fin = this.texto.indexOf(finishConfigName(configName));
/*  67 */       if (inicio != -1) {
/*  68 */         for (int x = inicio + 1; x < fin; x++) {
/*  69 */           config.add(this.texto.get(x));
/*     */         }
/*     */       }
/*     */     }
/*  73 */     return config;
/*     */   }
/*     */ 
/*     */   public String getConfigStr(String configName) {
/*  77 */     StringBuilder sb = new StringBuilder();
/*  78 */     readFile();
/*  79 */     if (!this.texto.isEmpty()) {
/*  80 */       int inicio = this.texto.indexOf(configName);
/*  81 */       int fin = this.texto.indexOf(finishConfigName(configName));
/*  82 */       if (inicio != -1) {
/*  83 */         for (int x = inicio + 1; x < fin; x++) {
/*  84 */           sb.append(new StringBuilder().append((String)this.texto.get(x)).append("\n").toString());
/*     */         }
/*     */       }
/*     */     }
/*  88 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public void saveConfig(String configName, ArrayList<String> config) {
/*  92 */     readFile();
/*  93 */     if (!this.texto.isEmpty()) {
/*  94 */       int inicio = this.texto.indexOf(configName);
/*  95 */       int fin = this.texto.indexOf(finishConfigName(configName));
/*  96 */       if (inicio != -1) {
/*  97 */         for (int x = inicio; x <= fin; x++) {
/*  98 */           this.texto.remove(inicio);
/*     */         }
/*     */       }
/*     */     }
/* 102 */     this.texto.add(configName);
/* 103 */     for (String string : config) {
/* 104 */       this.texto.add(string);
/*     */     }
/* 106 */     this.texto.add(finishConfigName(configName));
/* 107 */     saveFile();
/*     */   }
/*     */ 
/*     */   public void saveConfigStr(String configName, String config) {
/* 111 */     readFile();
/* 112 */     if (!this.texto.isEmpty()) {
/* 113 */       int inicio = this.texto.indexOf(configName);
/* 114 */       int fin = this.texto.indexOf(finishConfigName(configName));
/* 115 */       if (inicio != -1) {
/* 116 */         for (int x = inicio; x <= fin; x++) {
/* 117 */           this.texto.remove(x);
/*     */         }
/*     */       }
/*     */     }
/* 121 */     this.texto.add(configName);
/* 122 */     this.texto.add(config);
/* 123 */     this.texto.add(finishConfigName(configName));
/* 124 */     saveFile();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.auxiliar.ConfiguracionBean
 * JD-Core Version:    0.6.2
 */