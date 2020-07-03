/*     */ package contaes.listados;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoAgenda;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.TipoSubcuenta;
/*     */ import contaes.manejoDatos.TipoVencimiento;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class RemesasBase
/*     */ {
/*  23 */   public static String B40 = "                                        ";
/*     */ 
/*  26 */   public static String CABECERA_DE_ORDENANTE_58 = "5370";
/*     */ 
/*  29 */   public static String CABECERA_DE_PRESENTADOR_58 = "5170";
/*     */ 
/*  32 */   public static String INDIVIDUAL_OBLIGATORIO_58 = "5670";
/*     */ 
/*  35 */   public static String INDIVIDUAL_OBLIGATORIO_DOMICILIO_58 = "5676";
/*     */ 
/*  37 */   public static String TOTAL_DE_ORDENANTE_58 = "5870";
/*  38 */   public static String TOTAL_GENERAL_58 = "5970";
/*     */ 
/*  41 */   public static String CABECERA_DE_ORDENANTE_19 = "5380";
/*     */ 
/*  44 */   public static String CABECERA_DE_PRESENTADOR_19 = "5180";
/*     */ 
/*  47 */   public static String INDIVIDUAL_OBLIGATORIO_19 = "5680";
/*     */ 
/*  49 */   public static String TOTAL_DE_ORDENANTE_19 = "5880";
/*  50 */   public static String TOTAL_GENERAL_19 = "5980";
/*     */   String cccOrdenante;
/*  53 */   String nombreOrdenante = null;
/*  54 */   String nifOrdenante = null;
/*     */   Date fechaCargo;
/*  56 */   Date fechaFichero = new Date();
/*  57 */   SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
/*     */   String nombrePresentador;
/*     */   String nifPresentador;
/*     */   String sufijo;
/*     */   ArrayList<TipoVencimiento> vencimientos;
/*     */ 
/*     */   public RemesasBase(Date fechaCargo, String nombrePresentador, String nifPresentador, String sufijo, String cccOrdenante, ArrayList<TipoVencimiento> vencimientos)
/*     */   {
/*  68 */     this.fechaCargo = fechaCargo;
/*  69 */     this.nombrePresentador = nombrePresentador;
/*  70 */     this.nifPresentador = nifPresentador;
/*  71 */     this.sufijo = sufijo;
/*  72 */     this.cccOrdenante = cccOrdenante;
/*  73 */     this.vencimientos = vencimientos;
/*  74 */     getDatosOrdenante();
/*     */   }
/*     */ 
/*     */   private void getDatosOrdenante() {
/*  78 */     ManejoEmpresas mE = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa());
/*  79 */     this.nombreOrdenante = mE.getNombre(Inicio.p.getEmpresa());
/*  80 */     this.nifOrdenante = mE.getNif(Inicio.p.getEmpresa());
/*     */   }
/*     */ 
/*     */   protected String decimalFormat2Decimals(Double d)
/*     */   {
/*  91 */     DecimalFormat df = new DecimalFormat("0.00");
/*     */ 
/*  93 */     d = doubleTwoDecimals(d);
/*     */ 
/*  95 */     df.setMinimumFractionDigits(2);
/*     */ 
/*  97 */     return df.format(d);
/*     */   }
/*     */ 
/*     */   protected static void addEspacios(StringBuffer sb, int quants)
/*     */   {
/* 108 */     for (int i = 0; i < quants; i++)
/* 109 */       sb.append(' ');
/*     */   }
/*     */ 
/*     */   protected static String repasaAcentos(String s)
/*     */   {
/* 120 */     if (s == null) {
/* 121 */       return s;
/*     */     }
/* 123 */     s = s.toUpperCase();
/* 124 */     s = s.replace('Á', 'A');
/* 125 */     s = s.replace('É', 'E');
/* 126 */     s = s.replace('Í', 'I');
/* 127 */     s = s.replace('Ó', 'O');
/* 128 */     s = s.replace('Ú', 'U');
/* 129 */     s = s.replace('À', 'A');
/* 130 */     s = s.replace('È', 'E');
/* 131 */     s = s.replace('Ì', 'I');
/* 132 */     s = s.replace('Ò', 'O');
/* 133 */     s = s.replace('Ù', 'U');
/* 134 */     s = s.replace('Ï', 'I');
/* 135 */     s = s.replace('Ü', 'U');
/* 136 */     s = s.replace('Ñ', 'N');
/* 137 */     return s;
/*     */   }
/*     */ 
/*     */   protected static String addCerosIzquierda(String s, int nlen)
/*     */   {
/* 148 */     return caracterIzquierda(s, nlen, '0');
/*     */   }
/*     */ 
/*     */   protected static String addEspaciosIzquierda(String s, int nlen)
/*     */   {
/* 159 */     return caracterIzquierda(s, nlen, ' ');
/*     */   }
/*     */ 
/*     */   protected static String caracterIzquierda(String s, int nlen, char c)
/*     */   {
/* 171 */     if (s.length() > nlen) {
/* 172 */       return s.substring(0, nlen);
/*     */     }
/* 174 */     if (s.length() == nlen) {
/* 175 */       return s;
/*     */     }
/* 177 */     StringBuffer sb = new StringBuffer();
/* 178 */     int i = s.length();
/*     */ 
/* 180 */     while (i < nlen) {
/* 181 */       sb.append(c);
/* 182 */       i++;
/*     */     }
/* 184 */     sb.append(s);
/* 185 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   protected static String espacioDerecha(String s, int nlen)
/*     */   {
/* 197 */     if (s.length() > nlen) {
/* 198 */       return s.substring(0, nlen);
/*     */     }
/* 200 */     if (s.length() == nlen) {
/* 201 */       return s;
/*     */     }
/* 203 */     StringBuffer sb = new StringBuffer();
/* 204 */     int i = s.length();
/*     */ 
/* 206 */     sb.append(s);
/* 207 */     while (i < nlen) {
/* 208 */       sb.append(' ');
/* 209 */       i++;
/*     */     }
/* 211 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   protected String getNombreCliente(int codigo)
/*     */   {
/* 216 */     String nombre = "";
/* 217 */     ManejoSubcuentas mS = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 218 */     nombre = mS.datos(codigo).getNombre();
/* 219 */     mS.cerrarRs();
/* 220 */     return nombre;
/*     */   }
/*     */ 
/*     */   protected String getCccCliente(int codigo) {
/* 224 */     ManejoAgenda mA = new ManejoAgenda(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*     */ 
/* 226 */     String ccc = mA.getCuentaBanco(Integer.toString(codigo));
/* 227 */     if (ccc.equals("NO")) {
/* 228 */       ccc = "00000000000000000000";
/*     */     }
/* 230 */     mA.cerrarRs();
/* 231 */     return ccc;
/*     */   }
/*     */ 
/*     */   protected ArrayList<String> getDireccionCliente(int codigo) {
/* 235 */     ArrayList direccion = new ArrayList();
/*     */ 
/* 239 */     ManejoAgenda mA = new ManejoAgenda(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 240 */     String[] datos = mA.datos(Integer.toString(codigo));
/* 241 */     mA.cerrarRs();
/* 242 */     direccion.add(datos[3]);
/* 243 */     direccion.add(datos[5]);
/* 244 */     String cp = datos[4];
/* 245 */     if (cp.length() > 5) {
/* 246 */       cp = cp.substring(0, 5);
/*     */     }
/* 248 */     else if (cp.length() < 5) {
/* 249 */       int i = cp.length();
/* 250 */       while (i < 5) {
/* 251 */         cp = cp + "0";
/* 252 */         i++;
/*     */       }
/*     */     }
/* 255 */     direccion.add(cp);
/* 256 */     return direccion;
/*     */   }
/*     */ 
/*     */   public Double doubleTwoDecimals(Double number) {
/* 260 */     if (number != null) {
/* 261 */       BigDecimal dec = new BigDecimal(number.doubleValue());
/*     */ 
/* 263 */       return Double.valueOf(dec.setScale(2, 4).doubleValue());
/*     */     }
/* 265 */     return Double.valueOf(-1.0D);
/*     */   }
/*     */ 
/*     */   protected String getBanco() {
/* 269 */     String banco = "";
/* 270 */     if (this.cccOrdenante.length() > 4) {
/* 271 */       banco = this.cccOrdenante.substring(0, 4);
/*     */     }
/* 273 */     return banco;
/*     */   }
/*     */ 
/*     */   protected String getSucursal() {
/* 277 */     String sucursal = "";
/* 278 */     if (this.cccOrdenante.length() > 8) {
/* 279 */       sucursal = this.cccOrdenante.substring(4, 8);
/*     */     }
/* 281 */     return sucursal;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.RemesasBase
 * JD-Core Version:    0.6.2
 */