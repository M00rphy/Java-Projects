/*     */ package contaes.empresas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import contaes.manejoDatos.auxiliar.ConfiguracionBean;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
import java.util.List;
/*     */ 
/*     */ public class LastSelectedEmpresa
/*     */ {
/*     */   private ManejoEmpresas mE;
/*  21 */   private String nombreEmpresa = "";
/*     */ 
/*     */   public LastSelectedEmpresa() {
/*  24 */     this.mE = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa());
/*     */   }
/*     */ 
/*     */   public boolean isCreated() {
/*  28 */     boolean retorno = false;
/*  29 */     ArrayList array = new ArrayList();
/*  30 */     ConfiguracionBean config = new ConfiguracionBean();
/*  31 */     array = config.getConfig("<lastuse>");
/*  32 */     if (!array.isEmpty()) {
/*  33 */       Integer idEmpresa = Integer.valueOf(Integer.parseInt((String)array.get(0)));
/*  34 */       String ejercicio = (String)array.get(1);
/*  35 */       if (idEmpresa != null) {
/*  36 */         this.nombreEmpresa = this.mE.getNombre(idEmpresa.intValue());
/*  37 */         String prefijo = this.mE.getPrejifo(idEmpresa.intValue());
/*  38 */         if ((empresaCreada(this.nombreEmpresa)) && 
/*  39 */           (ejercicioCreado(idEmpresa.intValue(), ejercicio))) {
/*  40 */           Inicio.p.setEmpresa(idEmpresa.intValue());
/*  41 */           Inicio.p.setPrefijo(prefijo);
/*  42 */           Inicio.p.setEjercicio(ejercicio);
/*  43 */           Inicio.setCEmpresa(idEmpresa.intValue());
/*  44 */           Inicio.setCFacturacion(idEmpresa.intValue());
/*  45 */           retorno = true;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  75 */     return retorno;
/*     */   }
/*     */ 
/*     */   private boolean empresaCreada(String nombreEmpresa) {
/*  79 */     boolean retorno = false;
/*  80 */     LinkedList lista = this.mE.listaEmpresas();
/*  81 */     for (String string :(List<String>)  lista) {
/*  82 */       if (string.equals(nombreEmpresa)) {
/*  83 */         retorno = true;
/*  84 */         break;
/*     */       }
/*     */     }
/*  87 */     return retorno;
/*     */   }
/*     */ 
/*     */   private boolean ejercicioCreado(int idEmpresa, String ejercicio) {
/*  91 */     boolean retorno = false;
/*  92 */     LinkedList lista = this.mE.listaEjercicios(idEmpresa);
/*  93 */     for (String string :(List<String>)  lista) {
/*  94 */       if (string.equals(ejercicio)) {
/*  95 */         retorno = true;
/*  96 */         break;
/*     */       }
/*     */     }
/*  99 */     return retorno;
/*     */   }
/*     */ 
/*     */   public String getNombreEmpresa() {
/* 103 */     return this.nombreEmpresa;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.empresas.LastSelectedEmpresa
 * JD-Core Version:    0.6.2
 */