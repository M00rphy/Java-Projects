/*     */ package contaes.informes.control;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.informes.model.RatiosObject;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import internationalization.Mensajes;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ 
/*     */ public class RatiosControl
/*     */ {
/*     */   ResultSet res;
/*     */   Double AT;
/*     */   Double AF;
/*     */   Double AC;
/*     */   Double ET;
/*     */   Double ECP;
/*     */   Double ELP;
/*     */   Double RP;
/*     */   Double VN;
/*     */   Double CN;
/*     */   Double GF;
/*     */   Double RE;
/*     */   Double BN;
/*     */   Double ATw;
/*     */   Double AFw;
/*     */   Double ACw;
/*     */   Double ETw;
/*     */   Double ECPw;
/*     */   Double ELPw;
/*     */   Double RPw;
/*     */   Double VNw;
/*     */   Double CNw;
/*     */   Double GFw;
/*     */   Double REw;
/*     */   Double BNw;
/*     */ 
/*     */   public ArrayList<RatiosObject> listado(Date fechaIni, Date fechaFin)
/*     */   {
/*  58 */     ArrayList array = new ArrayList();
/*  59 */     calcularVariables(fechaIni, fechaFin);
/*     */ 
/*  63 */     Double valor1 = Double.valueOf(this.AF.doubleValue() / this.AT.doubleValue() * 100.0D);
/*  64 */     Double valor2 = Double.valueOf(this.AFw.doubleValue() / this.ATw.doubleValue() * 100.0D);
/*  65 */     RatiosObject objeto = new RatiosObject(Mensajes.getString("ratio01"), valor1, valor2);
/*  66 */     array.add(objeto);
/*  67 */     valor1 = Double.valueOf(this.AC.doubleValue() / this.AT.doubleValue() * 100.0D);
/*  68 */     valor2 = Double.valueOf(this.ACw.doubleValue() / this.ATw.doubleValue() * 100.0D);
/*  69 */     objeto = new RatiosObject(Mensajes.getString("ratio02"), valor1, valor2);
/*  70 */     array.add(objeto);
/*  71 */     valor1 = Double.valueOf(this.AF.doubleValue() / this.AC.doubleValue() * 100.0D);
/*  72 */     valor2 = Double.valueOf(this.AFw.doubleValue() / this.ACw.doubleValue() * 100.0D);
/*  73 */     objeto = new RatiosObject(Mensajes.getString("ratio03"), valor1, valor2);
/*  74 */     array.add(objeto);
/*  75 */     valor1 = Double.valueOf(this.ET.doubleValue() / this.RP.doubleValue() * 100.0D);
/*  76 */     valor2 = Double.valueOf(this.ETw.doubleValue() / this.RPw.doubleValue() * 100.0D);
/*  77 */     objeto = new RatiosObject(Mensajes.getString("ratio04"), valor1, valor2);
/*  78 */     array.add(objeto);
/*  79 */     valor1 = Double.valueOf(this.ECP.doubleValue() / this.RP.doubleValue() * 100.0D);
/*  80 */     valor2 = Double.valueOf(this.ECPw.doubleValue() / this.RPw.doubleValue() * 100.0D);
/*  81 */     objeto = new RatiosObject(Mensajes.getString("ratio05"), valor1, valor2);
/*  82 */     array.add(objeto);
/*  83 */     valor1 = Double.valueOf(this.ELP.doubleValue() / this.RP.doubleValue() * 100.0D);
/*  84 */     valor2 = Double.valueOf(this.ELPw.doubleValue() / this.RPw.doubleValue() * 100.0D);
/*  85 */     objeto = new RatiosObject(Mensajes.getString("ratio06"), valor1, valor2);
/*  86 */     array.add(objeto);
/*  87 */     valor1 = Double.valueOf(this.RP.doubleValue() / this.ET.doubleValue() * 100.0D);
/*  88 */     valor2 = Double.valueOf(this.RPw.doubleValue() / this.ETw.doubleValue() * 100.0D);
/*  89 */     objeto = new RatiosObject(Mensajes.getString("ratio07"), valor1, valor2);
/*  90 */     array.add(objeto);
/*  91 */     valor1 = Double.valueOf(this.AC.doubleValue() / this.ECP.doubleValue() * 100.0D);
/*  92 */     valor2 = Double.valueOf(this.ACw.doubleValue() / this.ECPw.doubleValue() * 100.0D);
/*  93 */     objeto = new RatiosObject(Mensajes.getString("ratio08"), valor1, valor2);
/*  94 */     array.add(objeto);
/*  95 */     valor1 = Double.valueOf((this.RP.doubleValue() + this.ELP.doubleValue()) / this.ET.doubleValue() * 100.0D);
/*  96 */     valor2 = Double.valueOf((this.RPw.doubleValue() + this.ELPw.doubleValue()) / this.ETw.doubleValue() * 100.0D);
/*  97 */     objeto = new RatiosObject(Mensajes.getString("ratio09"), valor1, valor2);
/*  98 */     array.add(objeto);
/*  99 */     valor1 = Double.valueOf(this.AF.doubleValue() / this.ELP.doubleValue() * 100.0D);
/* 100 */     valor2 = Double.valueOf(this.AFw.doubleValue() / this.ELPw.doubleValue() * 100.0D);
/* 101 */     objeto = new RatiosObject(Mensajes.getString("ratio10"), valor1, valor2);
/* 102 */     array.add(objeto);
/* 103 */     valor1 = Double.valueOf(this.AF.doubleValue() / (this.RP.doubleValue() + this.ELP.doubleValue()) * 100.0D);
/* 104 */     valor2 = Double.valueOf(this.AFw.doubleValue() / (this.RPw.doubleValue() + this.ELPw.doubleValue()) * 100.0D);
/* 105 */     objeto = new RatiosObject(Mensajes.getString("ratio11"), valor1, valor2);
/* 106 */     array.add(objeto);
/* 107 */     valor1 = Double.valueOf((this.RP.doubleValue() + this.ELP.doubleValue()) / this.AF.doubleValue() * 100.0D);
/* 108 */     valor2 = Double.valueOf((this.RPw.doubleValue() + this.ELPw.doubleValue()) / this.AFw.doubleValue() * 100.0D);
/* 109 */     objeto = new RatiosObject(Mensajes.getString("ratio12"), valor1, valor2);
/* 110 */     array.add(objeto);
/* 111 */     valor1 = Double.valueOf(this.ECP.doubleValue() / this.ELP.doubleValue() * 100.0D);
/* 112 */     valor2 = Double.valueOf(this.ECPw.doubleValue() / this.ELPw.doubleValue() * 100.0D);
/* 113 */     objeto = new RatiosObject(Mensajes.getString("ratio13"), valor1, valor2);
/* 114 */     array.add(objeto);
/*     */ 
/* 116 */     valor1 = Double.valueOf(this.RP.doubleValue() / (this.RP.doubleValue() + this.ELP.doubleValue()) * 100.0D);
/* 117 */     valor2 = Double.valueOf(this.RPw.doubleValue() / (this.RPw.doubleValue() + this.ELPw.doubleValue()) * 100.0D);
/* 118 */     objeto = new RatiosObject(Mensajes.getString("ratio14"), valor1, valor2);
/* 119 */     array.add(objeto);
/* 120 */     valor1 = Double.valueOf(this.ET.doubleValue() / (this.RP.doubleValue() + this.ELP.doubleValue()) * 100.0D);
/* 121 */     valor2 = Double.valueOf(this.ETw.doubleValue() / (this.RPw.doubleValue() + this.ELPw.doubleValue()) * 100.0D);
/* 122 */     objeto = new RatiosObject(Mensajes.getString("ratio15"), valor1, valor2);
/* 123 */     array.add(objeto);
/* 124 */     valor1 = Double.valueOf(this.RE.doubleValue() / this.RP.doubleValue() * 100.0D);
/* 125 */     valor2 = Double.valueOf(this.REw.doubleValue() / this.RPw.doubleValue() * 100.0D);
/* 126 */     objeto = new RatiosObject(Mensajes.getString("ratio16"), valor1, valor2);
/* 127 */     array.add(objeto);
/* 128 */     valor1 = Double.valueOf(this.BN.doubleValue() / this.RP.doubleValue() * 100.0D);
/* 129 */     valor2 = Double.valueOf(this.BNw.doubleValue() / this.RPw.doubleValue() * 100.0D);
/* 130 */     objeto = new RatiosObject(Mensajes.getString("ratio17"), valor1, valor2);
/* 131 */     array.add(objeto);
/* 132 */     valor1 = Double.valueOf(this.RE.doubleValue() / this.VN.doubleValue() * 100.0D);
/* 133 */     valor2 = Double.valueOf(this.REw.doubleValue() / this.VNw.doubleValue() * 100.0D);
/* 134 */     objeto = new RatiosObject(Mensajes.getString("ratio18"), valor1, valor2);
/* 135 */     array.add(objeto);
/* 136 */     valor1 = Double.valueOf(this.VN.doubleValue() / this.RP.doubleValue() * 100.0D);
/* 137 */     valor2 = Double.valueOf(this.VNw.doubleValue() / this.RPw.doubleValue() * 100.0D);
/* 138 */     objeto = new RatiosObject(Mensajes.getString("ratio19"), valor1, valor2);
/* 139 */     array.add(objeto);
/* 140 */     valor1 = Double.valueOf(this.VN.doubleValue() / this.AF.doubleValue() * 100.0D);
/* 141 */     valor2 = Double.valueOf(this.VNw.doubleValue() / this.AFw.doubleValue() * 100.0D);
/* 142 */     objeto = new RatiosObject(Mensajes.getString("ratio20"), valor1, valor2);
/* 143 */     array.add(objeto);
/* 144 */     valor1 = Double.valueOf(this.VN.doubleValue() / this.AC.doubleValue() * 100.0D);
/* 145 */     valor2 = Double.valueOf(this.VNw.doubleValue() / this.ACw.doubleValue() * 100.0D);
/* 146 */     objeto = new RatiosObject(Mensajes.getString("ratio21"), valor1, valor2);
/* 147 */     array.add(objeto);
/* 148 */     valor1 = Double.valueOf(-1.0D * (this.GF.doubleValue() / this.ET.doubleValue()) * 100.0D);
/* 149 */     valor2 = Double.valueOf(-1.0D * (this.GFw.doubleValue() / this.ETw.doubleValue()) * 100.0D);
/* 150 */     objeto = new RatiosObject(Mensajes.getString("ratio22"), valor1, valor2);
/* 151 */     array.add(objeto);
/* 152 */     return array;
/*     */   }
/*     */ 
/*     */   private void calcularVariables(Date fechaIni, Date fechaFin) {
/* 156 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 157 */     String fechaI = sdf.format(fechaIni);
/* 158 */     String fechaF = sdf.format(fechaFin);
/* 159 */     String fechaIW = pastYear(fechaI);
/* 160 */     String fechaFW = pastYear(fechaF);
/*     */ 
/* 173 */     double[] ba = new double[14];
/* 174 */     double[] bp = new double[25];
/* 175 */     for (int i = 0; i < 14; i++) {
/* 176 */       ba[i] = 0.0D;
/*     */     }
/* 178 */     for (int i = 0; i < 25; i++) {
/* 179 */       bp[i] = 0.0D;
/*     */     }
/* 181 */     ba[0] = calculaGrupo("AAI", fechaI, fechaF, "C");
/* 182 */     ba[1] = calculaGrupo("AAII", fechaI, fechaF, "C");
/* 183 */     ba[2] = calculaGrupo("AAIII", fechaI, fechaF, "C");
/* 184 */     ba[3] = calculaGrupo("AAIV", fechaI, fechaF, "C");
/* 185 */     ba[4] = calculaGrupo("AAV", fechaI, fechaF, "C");
/* 186 */     ba[5] = calculaGrupo("AAVI", fechaI, fechaF, "C");
/* 187 */     ba[6] = calculaGrupo("ABI", fechaI, fechaF, "C");
/* 188 */     ba[7] = calculaGrupo("ABII1", fechaI, fechaF, "C");
/* 189 */     ba[8] = calculaGrupo("ABII2", fechaI, fechaF, "C");
/* 190 */     ba[9] = calculaGrupo("ABII3", fechaI, fechaF, "C");
/* 191 */     ba[10] = calculaGrupo("ABIII", fechaI, fechaF, "C");
/* 192 */     ba[11] = calculaGrupo("ABIV", fechaI, fechaF, "C");
/* 193 */     ba[12] = calculaGrupo("ABV", fechaI, fechaF, "C");
/* 194 */     ba[13] = calculaGrupo("ABVI", fechaI, fechaF, "C");
/*     */ 
/* 196 */     bp[0] = calculaGrupo("PA1I1", fechaI, fechaF, "C");
/* 197 */     bp[1] = calculaGrupo("PA1I2", fechaI, fechaF, "C");
/* 198 */     bp[2] = calculaGrupo("PA1II", fechaI, fechaF, "C");
/* 199 */     bp[3] = calculaGrupo("PA1III", fechaI, fechaF, "C");
/* 200 */     bp[4] = calculaGrupo("PA1IV", fechaI, fechaF, "C");
/* 201 */     bp[5] = calculaGrupo("PA1V", fechaI, fechaF, "C");
/* 202 */     bp[6] = calculaGrupo("PA1VI", fechaI, fechaF, "C");
/* 203 */     bp[7] = calculaGrupo("PA1VII", fechaI, fechaF, "C");
/* 204 */     bp[8] = calculaGrupo("PA1VIII", fechaI, fechaF, "C");
/* 205 */     bp[9] = calculaGrupo("PA2", fechaI, fechaF, "C");
/* 206 */     bp[10] = calculaGrupo("PBI", fechaI, fechaF, "C");
/* 207 */     bp[11] = calculaGrupo("PBII1", fechaI, fechaF, "C");
/* 208 */     bp[12] = calculaGrupo("PBII2", fechaI, fechaF, "C");
/* 209 */     bp[13] = calculaGrupo("PBIII", fechaI, fechaF, "C");
/* 210 */     bp[14] = calculaGrupo("PBIV", fechaI, fechaF, "C");
/* 211 */     bp[15] = calculaGrupo("PCI", fechaI, fechaF, "C");
/* 212 */     bp[16] = calculaGrupo("PCII1", fechaI, fechaF, "C");
/* 213 */     bp[17] = calculaGrupo("PCII2", fechaI, fechaF, "C");
/* 214 */     bp[18] = calculaGrupo("PCIII", fechaI, fechaF, "C");
/* 215 */     bp[19] = calculaGrupo("PCIV1", fechaI, fechaF, "C");
/* 216 */     bp[20] = calculaGrupo("PCIV2", fechaI, fechaF, "C");
/* 217 */     bp[21] = calculaGrupo("PCV", fechaI, fechaF, "C");
/* 218 */     bp[22] = calculaGrupo("PBII3", fechaI, fechaF, "C");
/* 219 */     bp[23] = calculaGrupo("PBV", fechaI, fechaF, "C");
/* 220 */     bp[24] = calculaGrupo("PCII3", fechaI, fechaF, "C");
/*     */ 
/* 222 */     double grupoEsp1 = calculaGrupo("ESP1", fechaI, fechaF, "C");
/* 223 */     double grupoEsp2 = calculaGrupo("ESP2", fechaI, fechaF, "C");
/* 224 */     if (grupoEsp1 >= 0.0D) ba[10] += grupoEsp1; else
/* 225 */       bp[24] -= grupoEsp1;
/* 226 */     if (grupoEsp2 >= 0.0D) ba[11] += grupoEsp2; else {
/* 227 */       bp[18] -= grupoEsp2;
/*     */     }
/* 229 */     double[] t = new double[13];
/*     */ 
/* 231 */     for (int i = 0; i < 13; i++) t[i] = 0.0D;
/* 232 */     for (int i = 0; i < 6; i++) t[0] += ba[i];
/* 233 */     for (int i = 7; i < 10; i++) t[2] += ba[i];
/* 234 */     for (int i = 10; i < 14; i++) t[1] += ba[i];
/* 235 */     t[1] = (t[1] + t[2] + ba[6]);
/* 236 */     t[3] = (t[0] + t[1]);
/*     */ 
/* 238 */     t[6] = (bp[0] + bp[1]);
/* 239 */     for (int i = 2; i < 9; i++) t[5] += bp[i];
/* 240 */     t[5] += t[6];
/* 241 */     t[4] = (t[5] + bp[9]);
/* 242 */     t[8] = (bp[11] + bp[12] + bp[22]);
/* 243 */     t[7] = (bp[10] + t[8] + bp[13] + bp[14] + bp[23]);
/* 244 */     t[10] = (bp[16] + bp[17] + bp[24]);
/* 245 */     t[11] = (bp[19] + bp[20]);
/* 246 */     t[9] = (bp[15] + t[10] + bp[18] + t[11] + bp[21]);
/* 247 */     t[12] = (t[4] + t[7] + t[9]);
/*     */ 
/* 250 */     double[] baW = new double[14];
/* 251 */     double[] bpW = new double[25];
/* 252 */     for (int i = 0; i < 14; i++) {
/* 253 */       baW[i] = 0.0D;
/*     */     }
/* 255 */     for (int i = 0; i < 25; i++) {
/* 256 */       bpW[i] = 0.0D;
/*     */     }
/* 258 */     baW[0] = calculaGrupo("AAI", fechaIW, fechaFW, "C");
/* 259 */     baW[1] = calculaGrupo("AAII", fechaIW, fechaFW, "C");
/* 260 */     baW[2] = calculaGrupo("AAIII", fechaIW, fechaFW, "C");
/* 261 */     baW[3] = calculaGrupo("AAIV", fechaIW, fechaFW, "C");
/* 262 */     baW[4] = calculaGrupo("AAV", fechaIW, fechaFW, "C");
/* 263 */     baW[5] = calculaGrupo("AAVI", fechaIW, fechaFW, "C");
/* 264 */     baW[6] = calculaGrupo("ABI", fechaIW, fechaFW, "C");
/* 265 */     baW[7] = calculaGrupo("ABII1", fechaIW, fechaFW, "C");
/* 266 */     baW[8] = calculaGrupo("ABII2", fechaIW, fechaFW, "C");
/* 267 */     baW[9] = calculaGrupo("ABII3", fechaIW, fechaFW, "C");
/* 268 */     baW[10] = calculaGrupo("ABIII", fechaIW, fechaFW, "C");
/* 269 */     baW[11] = calculaGrupo("ABIV", fechaIW, fechaFW, "C");
/* 270 */     baW[12] = calculaGrupo("ABV", fechaIW, fechaFW, "C");
/* 271 */     baW[13] = calculaGrupo("ABVI", fechaIW, fechaFW, "C");
/*     */ 
/* 273 */     bpW[0] = calculaGrupo("PA1I1", fechaIW, fechaFW, "C");
/* 274 */     bpW[1] = calculaGrupo("PA1I2", fechaIW, fechaFW, "C");
/* 275 */     bpW[2] = calculaGrupo("PA1II", fechaIW, fechaFW, "C");
/* 276 */     bpW[3] = calculaGrupo("PA1III", fechaIW, fechaFW, "C");
/* 277 */     bpW[4] = calculaGrupo("PA1IV", fechaIW, fechaFW, "C");
/* 278 */     bpW[5] = calculaGrupo("PA1V", fechaIW, fechaFW, "C");
/* 279 */     bpW[6] = calculaGrupo("PA1VI", fechaIW, fechaFW, "C");
/* 280 */     bpW[7] = calculaGrupo("PA1VII", fechaIW, fechaFW, "C");
/* 281 */     bpW[8] = calculaGrupo("PA1VIII", fechaIW, fechaFW, "C");
/* 282 */     bpW[9] = calculaGrupo("PA2", fechaIW, fechaFW, "C");
/* 283 */     bpW[10] = calculaGrupo("PBI", fechaIW, fechaFW, "C");
/* 284 */     bpW[11] = calculaGrupo("PBII1", fechaIW, fechaFW, "C");
/* 285 */     bpW[12] = calculaGrupo("PBII2", fechaIW, fechaFW, "C");
/* 286 */     bpW[13] = calculaGrupo("PBIII", fechaIW, fechaFW, "C");
/* 287 */     bpW[14] = calculaGrupo("PBIV", fechaIW, fechaFW, "C");
/* 288 */     bpW[15] = calculaGrupo("PCI", fechaIW, fechaFW, "C");
/* 289 */     bpW[16] = calculaGrupo("PCII1", fechaIW, fechaFW, "C");
/* 290 */     bpW[17] = calculaGrupo("PCII2", fechaIW, fechaFW, "C");
/* 291 */     bpW[18] = calculaGrupo("PCIII", fechaIW, fechaFW, "C");
/* 292 */     bpW[19] = calculaGrupo("PCIV1", fechaIW, fechaFW, "C");
/* 293 */     bpW[20] = calculaGrupo("PCIV2", fechaIW, fechaFW, "C");
/* 294 */     bpW[21] = calculaGrupo("PCV", fechaIW, fechaFW, "C");
/* 295 */     bpW[22] = calculaGrupo("PBII3", fechaIW, fechaFW, "C");
/* 296 */     bpW[23] = calculaGrupo("PBV", fechaIW, fechaFW, "C");
/* 297 */     bpW[24] = calculaGrupo("PCII3", fechaIW, fechaFW, "C");
/*     */ 
/* 299 */     double grupoEsp1W = calculaGrupo("ESP1", fechaIW, fechaFW, "C");
/* 300 */     double grupoEsp2W = calculaGrupo("ESP2", fechaIW, fechaFW, "C");
/* 301 */     if (grupoEsp1W >= 0.0D) baW[10] += grupoEsp1W; else
/* 302 */       bpW[24] -= grupoEsp1W;
/* 303 */     if (grupoEsp2W >= 0.0D) baW[11] += grupoEsp2W; else {
/* 304 */       bpW[18] -= grupoEsp2W;
/*     */     }
/* 306 */     double[] tW = new double[13];
/*     */ 
/* 308 */     for (int i = 0; i < 13; i++) tW[i] = 0.0D;
/* 309 */     for (int i = 0; i < 6; i++) tW[0] += baW[i];
/* 310 */     for (int i = 7; i < 10; i++) tW[2] += baW[i];
/* 311 */     for (int i = 10; i < 14; i++) tW[1] += baW[i];
/* 312 */     tW[1] = (tW[1] + tW[2] + baW[6]);
/* 313 */     tW[3] = (tW[0] + tW[1]);
/*     */ 
/* 315 */     tW[6] = (bpW[0] + bpW[1]);
/* 316 */     for (int i = 2; i < 9; i++) tW[5] += bpW[i];
/* 317 */     tW[5] += tW[6];
/* 318 */     tW[4] = (tW[5] + bpW[9]);
/* 319 */     tW[8] = (bpW[11] + bpW[12] + bpW[22]);
/* 320 */     tW[7] = (bpW[10] + tW[8] + bpW[13] + bpW[14] + bpW[23]);
/* 321 */     tW[10] = (bpW[16] + bpW[17] + bpW[24]);
/* 322 */     tW[11] = (bpW[19] + bpW[20]);
/* 323 */     tW[9] = (bpW[15] + tW[10] + bpW[18] + tW[11] + bpW[21]);
/* 324 */     tW[12] = (tW[4] + tW[7] + tW[9]);
/*     */ 
/* 328 */     double[] pg = new double[17];
/* 329 */     for (int i = 0; i < 17; i++) {
/* 330 */       pg[i] = 0.0D;
/*     */     }
/*     */ 
/* 333 */     pg[0] = calculaGrupo("G1", fechaI, fechaF, "G");
/* 334 */     pg[1] = calculaGrupo("G2", fechaI, fechaF, "G");
/* 335 */     pg[2] = calculaGrupo("G3", fechaI, fechaF, "G");
/* 336 */     pg[3] = calculaGrupo("G4", fechaI, fechaF, "G");
/* 337 */     pg[4] = calculaGrupo("G5", fechaI, fechaF, "G");
/* 338 */     pg[5] = calculaGrupo("G6", fechaI, fechaF, "G");
/* 339 */     pg[6] = calculaGrupo("G7", fechaI, fechaF, "G");
/* 340 */     pg[7] = calculaGrupo("G8", fechaI, fechaF, "G");
/* 341 */     pg[8] = calculaGrupo("G9", fechaI, fechaF, "G");
/* 342 */     pg[9] = calculaGrupo("G10", fechaI, fechaF, "G");
/* 343 */     pg[10] = calculaGrupo("G11", fechaI, fechaF, "G");
/* 344 */     pg[11] = calculaGrupo("G12", fechaI, fechaF, "G");
/* 345 */     pg[12] = calculaGrupo("G13", fechaI, fechaF, "G");
/* 346 */     pg[13] = calculaGrupo("G14", fechaI, fechaF, "G");
/* 347 */     pg[14] = calculaGrupo("G15", fechaI, fechaF, "G");
/* 348 */     pg[15] = calculaGrupo("G16", fechaI, fechaF, "G");
/* 349 */     pg[16] = calculaGrupo("G17", fechaI, fechaF, "G");
/* 350 */     double A = 0.0D; double B = 0.0D; double C = 0.0D; double D = 0.0D;
/* 351 */     for (int i = 0; i < 11; i++) A += pg[i];
/* 352 */     for (int i = 11; i < 16; i++) B += pg[i];
/* 353 */     C = A + B;
/* 354 */     D = C + pg[16];
/*     */ 
/* 357 */     double[] pgW = new double[17];
/* 358 */     for (int i = 0; i < 17; i++) {
/* 359 */       pgW[i] = 0.0D;
/*     */     }
/*     */ 
/* 362 */     pgW[0] = calculaGrupo("G1", fechaIW, fechaFW, "G");
/* 363 */     pgW[1] = calculaGrupo("G2", fechaIW, fechaFW, "G");
/* 364 */     pgW[2] = calculaGrupo("G3", fechaIW, fechaFW, "G");
/* 365 */     pgW[3] = calculaGrupo("G4", fechaIW, fechaFW, "G");
/* 366 */     pgW[4] = calculaGrupo("G5", fechaIW, fechaFW, "G");
/* 367 */     pgW[5] = calculaGrupo("G6", fechaIW, fechaFW, "G");
/* 368 */     pgW[6] = calculaGrupo("G7", fechaIW, fechaFW, "G");
/* 369 */     pgW[7] = calculaGrupo("G8", fechaIW, fechaFW, "G");
/* 370 */     pgW[8] = calculaGrupo("G9", fechaIW, fechaFW, "G");
/* 371 */     pgW[9] = calculaGrupo("G10", fechaIW, fechaFW, "G");
/* 372 */     pgW[10] = calculaGrupo("G11", fechaIW, fechaFW, "G");
/* 373 */     pgW[11] = calculaGrupo("G12", fechaIW, fechaFW, "G");
/* 374 */     pgW[12] = calculaGrupo("G13", fechaIW, fechaFW, "G");
/* 375 */     pgW[13] = calculaGrupo("G14", fechaIW, fechaFW, "G");
/* 376 */     pgW[14] = calculaGrupo("G15", fechaIW, fechaFW, "G");
/* 377 */     pgW[15] = calculaGrupo("G16", fechaIW, fechaFW, "G");
/* 378 */     pgW[16] = calculaGrupo("G17", fechaIW, fechaFW, "G");
/* 379 */     double AW = 0.0D; double BW = 0.0D; double CW = 0.0D; double DW = 0.0D;
/* 380 */     for (int i = 0; i < 11; i++) AW += pgW[i];
/* 381 */     for (int i = 11; i < 16; i++) BW += pgW[i];
/* 382 */     CW = AW + BW;
/* 383 */     DW = CW + pgW[16];
/*     */ 
/* 386 */     this.AF = Double.valueOf(t[0]);
/* 387 */     this.AFw = Double.valueOf(tW[0]);
/* 388 */     this.AC = Double.valueOf(t[1]);
/* 389 */     this.ACw = Double.valueOf(tW[1]);
/* 390 */     this.AT = Double.valueOf(this.AF.doubleValue() + this.AC.doubleValue());
/* 391 */     this.ATw = Double.valueOf(this.AFw.doubleValue() + this.ACw.doubleValue());
/*     */ 
/* 393 */     this.ECP = Double.valueOf(t[7]);
/* 394 */     this.ELP = Double.valueOf(t[9]);
/* 395 */     this.ET = Double.valueOf(this.ECP.doubleValue() + this.ELP.doubleValue());
/* 396 */     this.RP = Double.valueOf(t[4] + D);
/* 397 */     this.ECPw = Double.valueOf(tW[7]);
/* 398 */     this.ELPw = Double.valueOf(tW[9]);
/* 399 */     this.ETw = Double.valueOf(this.ECPw.doubleValue() + this.ELPw.doubleValue());
/* 400 */     this.RPw = Double.valueOf(tW[4] + DW);
/*     */ 
/* 402 */     this.VN = Double.valueOf(pg[0]);
/* 403 */     this.CN = Double.valueOf(pg[3]);
/* 404 */     this.GF = Double.valueOf(pg[12]);
/* 405 */     this.RE = Double.valueOf(A);
/* 406 */     this.BN = Double.valueOf(D);
/* 407 */     this.VNw = Double.valueOf(pgW[0]);
/* 408 */     this.CNw = Double.valueOf(pgW[3]);
/* 409 */     this.GFw = Double.valueOf(pgW[12]);
/* 410 */     this.REw = Double.valueOf(AW);
/* 411 */     this.BNw = Double.valueOf(DW);
/*     */   }
/*     */ 
/*     */   private double calculaGrupo(String grupo, String fechaI, String fechaF, String marca)
/*     */   {
/* 425 */     if ((grupo.equals("")) || (grupo.length() < 1)) return 0.0D;
/* 426 */     double importe = 0.0D; double importeDebe = 0.0D; double importeHaber = 0.0D;
/*     */     try {
/* 428 */       String ejercicio = fechaI.substring(0, 4);
/* 429 */       String cadenaSeleccion = "SELECT SUM(apt.importe) FROM apte" + ejercicio + " AS apt " + "JOIN scta" + ejercicio + " as scta ON apt.cuenta=scta.codigo " + "JOIN asto" + ejercicio + " as ast ON apt.id_asiento=ast.id_asiento " + "WHERE scta.gbalance = '" + grupo + "' AND " + "ast.fecha BETWEEN '" + fechaI + "' AND '" + fechaF + "' " + "AND ast.marca NOT LIKE 'C' AND ast.marca NOT LIKE 'G'";
/*     */ 
/* 436 */       this.res = Inicio.getCEmpresa().getRes(cadenaSeleccion + " AND apt.DH = 'D'");
/*     */ 
/* 438 */       if (this.res.next()) importeDebe = this.res.getDouble(1);
/* 439 */       this.res = Inicio.getCEmpresa().getRes(cadenaSeleccion + " AND apt.DH = 'H'");
/*     */ 
/* 441 */       if (this.res.next()) importeHaber = this.res.getDouble(1); 
/*     */     }
/* 443 */     catch (SQLException exc) { System.out.println(exc.getMessage()); }
/* 444 */     if (grupo.substring(0, 1).equals("E"))
/* 445 */       importe = importeDebe - importeHaber;
/*     */     else {
/* 447 */       importe = grupo.substring(0, 1).equals("A") ? importeDebe - importeHaber : importeHaber - importeDebe;
/*     */     }
/*     */ 
/* 450 */     if (!grupo.substring(grupo.length() - 1).equals("-"))
/* 451 */       importe += calculaGrupo(grupo + "-", fechaI, fechaF, marca);
/* 452 */     return importe;
/*     */   }
/*     */ 
/*     */   private String pastYear(String fecha) {
/* 456 */     int year = Integer.parseInt(fecha.substring(0, 4));
/* 457 */     int mes = Integer.parseInt(fecha.substring(5, 7));
/* 458 */     int dia = Integer.parseInt(fecha.substring(8));
/* 459 */     GregorianCalendar cal = new GregorianCalendar(year, mes - 1, dia);
/* 460 */     cal.add(1, -1);
/* 461 */     Date datTemp = new Date(cal.getTimeInMillis());
/* 462 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 463 */     return sdf.format(datTemp);
/*     */   }
/*     */ 
/*     */   public void cerrarRs() {
/* 467 */     if (this.res != null) {
/*     */       try {
/* 469 */         this.res.close();
/*     */       } catch (SQLException sqlEx) {
/*     */       }
/* 472 */       this.res = null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.informes.control.RatiosControl
 * JD-Core Version:    0.6.2
 */