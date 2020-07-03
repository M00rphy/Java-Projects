/*    */ package contaes.manejoDatos;
/*    */ 
/*    */ import contaes.manejoDatos.auxiliar.ConfiguracionBean;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.PrintStream;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class CheckVersionInWeb
/*    */ {
/* 20 */   private boolean isUpdate = true;
/*    */ 
/*    */   public boolean isIsUpdate() {
/* 23 */     return this.isUpdate;
/*    */   }
/*    */ 
/*    */   public CheckVersionInWeb() {
/* 27 */     String versionInstalada = getVersionInstalada();
/* 28 */     String versionMasReciente = getVersionMasReciente();
/* 29 */     System.out.println("Versión instalada: " + versionInstalada + "\nVersión más reciente: " + versionMasReciente);
/* 30 */     if (!versionInstalada.equals(versionMasReciente))
/* 31 */       this.isUpdate = false;
/*    */   }
/*    */ 
/*    */   private String getVersionInstalada()
/*    */   {
/* 36 */     ArrayList array = new ArrayList();
/* 37 */     ConfiguracionBean config = new ConfiguracionBean();
/* 38 */     array = config.getConfig("<version>");
/* 39 */     if (!array.isEmpty()) {
/* 40 */       return (String)array.get(0);
/*    */     }
/* 42 */     return "";
/*    */   }
/*    */ 
/*    */   private String getVersionMasReciente() {
/* 46 */     String str = "";
/*    */     try
/*    */     {
/* 49 */       URL url = new URL("http://www.contaes.es/version.html");
/*    */ 
/* 52 */       BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
/* 53 */       str = in.readLine();
/* 54 */       in.close();
/*    */     } catch (MalformedURLException e) {
/*    */     } catch (IOException e) {
/*    */     }
/* 58 */     return str;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.CheckVersionInWeb
 * JD-Core Version:    0.6.2
 */