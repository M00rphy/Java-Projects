/*     */ package contaes.manejoDatos.funciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoApuntes;
/*     */ import contaes.manejoDatos.ManejoAsientos;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import contaes.manejoDatos.auxiliar.ImportesCuentaEntreFechas;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import contaes.manejoDatos.auxiliar.TablaParaSaldos;
/*     */ import internationalization.Mensajes;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.LinkedList;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ public class OperacionesCierre
/*     */ {
/*     */   private static ResultSet res;
/*     */ 
/*     */   public static void apertura()
/*     */   {
/*  29 */     String ejAnterior = String.valueOf(Integer.parseInt(Inicio.p.getEjercicio()) - 1);
/*     */     try {
/*  31 */       res = Inicio.getCEmpresa().getRes("SHOW TABLES LIKE 'scta" + ejAnterior + "'");
/*  32 */       if (res.next()) {
/*  33 */         ManejoSubcuentas cuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), ejAnterior);
/*  34 */         LinkedList listaCuentas = cuentaM.listadoSubcuentas(10000000, 59999999);
/*  35 */         int nCuentas = listaCuentas.size();
/*  36 */         if (nCuentas > 0) {
/*  37 */           ImportesCuentaEntreFechas[] subcuenta = new ImportesCuentaEntreFechas[nCuentas];
/*  38 */           for (int i = 0; i < nCuentas; i++) {
/*  39 */             subcuenta[i] = new ImportesCuentaEntreFechas(String.valueOf(listaCuentas.get(i)), ejAnterior + "-01-01", ejAnterior + "-12-31", "C", ejAnterior);
/*     */           }
/*     */ 
/*  43 */           ManejoAsientos asientoM = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  44 */           ManejoApuntes apunteM = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  45 */           int idAsiento = asientoM.crear(asientoM.nuevoNumero(), Inicio.p.getEjercicio() + "-01-01", "", "A");
/*  46 */           if (idAsiento != -1)
/*  47 */             for (int i = 0; i < nCuentas; i++) {
/*  48 */               String DH = Mensajes.getString("debeA");
/*  49 */               double saldo = subcuenta[i].saldo();
/*  50 */               if (saldo < 0.0D) {
/*  51 */                 saldo = -saldo;
/*  52 */                 DH = Mensajes.getString("haberA");
/*     */               }
/*  54 */               apunteM.crear(idAsiento, Integer.parseInt(subcuenta[i].codigo()), Mensajes.getString("astoApertura"), DH, saldo);
/*     */             }
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/*  60 */         JOptionPane.showMessageDialog(Inicio.frame, Mensajes.getString("noEjAnterior"));
/*     */       }
/*     */     } catch (SQLException e) {
/*  63 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void cierre() {
/*  68 */     ManejoSubcuentas cuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  69 */     LinkedList listaCuentas = cuentaM.listadoSubcuentas(10000000, 59999999);
/*  70 */     int nCuentas = listaCuentas.size();
/*  71 */     if (nCuentas > 0) {
/*  72 */       TablaParaSaldos[] cuenta = new TablaParaSaldos[nCuentas];
/*  73 */       for (int x = 0; x < nCuentas; x++) {
/*  74 */         cuenta[x] = new TablaParaSaldos();
/*  75 */         cuenta[x].setCuenta(((Integer)listaCuentas.get(x)).intValue());
/*  76 */         double saldo = cuentaM.datos(((Integer)listaCuentas.get(x)).intValue()).getSaldo();
/*  77 */         if (saldo < 0.0D) {
/*  78 */           cuenta[x].setDH(Mensajes.getString("debeA"));
/*  79 */           cuenta[x].setImporte(-saldo);
/*     */         } else {
/*  81 */           cuenta[x].setDH(Mensajes.getString("haberA"));
/*  82 */           cuenta[x].setImporte(saldo);
/*     */         }
/*     */       }
/*  85 */       ManejoAsientos asientoM = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  86 */       ManejoApuntes apunteM = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  87 */       int id_asiento = asientoM.crear(asientoM.nuevoNumero(), Inicio.p.getEjercicio() + "-12-31", "", "C");
/*     */ 
/*  89 */       if (id_asiento != -1)
/*  90 */         for (int i = 0; i < nCuentas; i++)
/*  91 */           apunteM.crear(id_asiento, cuenta[i].getCuenta(), Mensajes.getString("astoCierre"), cuenta[i].getDH(), cuenta[i].getImporte());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void perdidasGanancias()
/*     */   {
/*  98 */     ManejoSubcuentas cuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  99 */     if (cuentaM.datos(12900000) == null) {
/* 100 */       cuentaM.crear(12900000, "Pérdidas y Ganancias", "PA1VII");
/*     */     }
/* 102 */     LinkedList listaCta = cuentaM.listadoSubcuentas(60000000, 79999999);
/* 103 */     int nCuentas = listaCta.size();
/* 104 */     if (nCuentas > 0) {
/* 105 */       TablaParaSaldos[] cuenta = new TablaParaSaldos[nCuentas];
/* 106 */       double saldoPyG = 0.0D;
/* 107 */       for (int x = 0; x < nCuentas; x++) {
/* 108 */         cuenta[x] = new TablaParaSaldos();
/* 109 */         cuenta[x].setCuenta(((Integer)listaCta.get(x)).intValue());
/* 110 */         double saldo = cuentaM.datos(((Integer)listaCta.get(x)).intValue()).getSaldo();
/* 111 */         if (saldo < 0.0D) {
/* 112 */           cuenta[x].setDH(Mensajes.getString("debeA"));
/* 113 */           cuenta[x].setImporte(-saldo);
/*     */         } else {
/* 115 */           cuenta[x].setDH(Mensajes.getString("haberA"));
/* 116 */           cuenta[x].setImporte(saldo);
/*     */         }
/* 118 */         saldoPyG += saldo;
/*     */       }
/* 120 */       ManejoAsientos asientoM = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 121 */       ManejoApuntes apunteM = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 122 */       int idAsiento = asientoM.crear(asientoM.nuevoNumero(), Inicio.p.getEjercicio() + "-12-31", "", "G");
/* 123 */       if (saldoPyG < 0.0D)
/* 124 */         apunteM.crear(idAsiento, 12900000, Mensajes.getString("regPerYgan"), Mensajes.getString("haberA"), -saldoPyG);
/*     */       else {
/* 126 */         apunteM.crear(idAsiento, 12900000, Mensajes.getString("regPerYgan"), Mensajes.getString("debeA"), saldoPyG);
/*     */       }
/* 128 */       for (int x = 0; x < nCuentas; x++)
/* 129 */         apunteM.crear(idAsiento, cuenta[x].getCuenta(), Mensajes.getString("regPerYgan"), cuenta[x].getDH(), cuenta[x].getImporte());
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.manejoDatos.funciones.OperacionesCierre
 * JD-Core Version:    0.6.2
 */