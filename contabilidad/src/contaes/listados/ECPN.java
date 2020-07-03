/*     */ package contaes.listados;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import contaes.manejoDatos.auxiliar.AddExtension;
/*     */ import contaes.manejoDatos.auxiliar.ECPNcalc;
/*     */ import contaes.manejoDatos.auxiliar.FinLinea;
/*     */ import contaes.manejoDatos.auxiliar.GrabarFichero;
/*     */ import internationalization.Mensajes;
/*     */ import java.io.File;
/*     */ import java.util.Formatter;
/*     */ import javax.swing.JFileChooser;
/*     */ 
/*     */ public class ECPN
/*     */ {
/*     */   public void ejecutar()
/*     */   {
/*  30 */     crearECPN();
/*     */   }
/*     */ 
/*     */   private void crearECPN() {
/*  34 */     String ejercicio = Inicio.p.getEjercicio();
/*     */ 
/*  36 */     double[] CE = new double[21];
/*  37 */     double[] CN = new double[21];
/*  38 */     double[] PE = new double[21];
/*  39 */     double[] RE = new double[21];
/*  40 */     double[] AP = new double[21];
/*  41 */     double[] RA = new double[21];
/*  42 */     double[] OA = new double[21];
/*  43 */     double[] R = new double[21];
/*  44 */     double[] DC = new double[21];
/*  45 */     double[] OI = new double[21];
/*  46 */     double[] AC = new double[21];
/*  47 */     double[] SD = new double[21];
/*  48 */     double[] T = new double[21];
/*     */ 
/*  50 */     String CEs = "Capital escriturado";
/*  51 */     String CNs = "(Capital no exigido)";
/*  52 */     String PEs = "Prima de emisión";
/*  53 */     String REs = "Reservas";
/*  54 */     String APs = "(Acciones y participaciones en patrimonio propias)";
/*  55 */     String RAs = "Resultados de ejercicios anteriores";
/*  56 */     String OAs = "Otras aportaciones de socios";
/*  57 */     String Rs = "Resultado del ejercicio";
/*  58 */     String DCs = "(Dividendo a cuenta)";
/*  59 */     String OIs = "Otros instrumentos de patrimonio neto";
/*  60 */     String ACs = "Ajustes por cambios de valor";
/*  61 */     String SDs = "Subvenciones, donaciones y legados recibidos";
/*  62 */     String Ts = "Total";
/*     */ 
/*  66 */     String ejercicioAnterior = Integer.toString(Integer.parseInt(ejercicio) - 1);
/*  67 */     CE[0] = ECPNcalc.saldoApertura("100", ejercicioAnterior);
/*  68 */     CE[0] += ECPNcalc.saldoApertura("101", ejercicioAnterior);
/*  69 */     CE[0] += ECPNcalc.saldoApertura("102", ejercicioAnterior);
/*  70 */     CN[0] = ECPNcalc.saldoApertura("103", ejercicioAnterior);
/*  71 */     CN[0] += ECPNcalc.saldoApertura("104", ejercicioAnterior);
/*  72 */     CN[0] = (-1.0D * CN[0]);
/*  73 */     PE[0] = ECPNcalc.saldoApertura("110", ejercicioAnterior);
/*  74 */     RE[0] = ECPNcalc.saldoApertura("112", ejercicioAnterior);
/*  75 */     RE[0] += ECPNcalc.saldoApertura("113", ejercicioAnterior);
/*  76 */     RE[0] += ECPNcalc.saldoApertura("114", ejercicioAnterior);
/*  77 */     RE[0] += ECPNcalc.saldoApertura("119", ejercicioAnterior);
/*  78 */     AP[0] = ECPNcalc.saldoApertura("108", ejercicioAnterior);
/*  79 */     AP[0] += ECPNcalc.saldoApertura("109", ejercicioAnterior);
/*  80 */     AP[0] = (-1.0D * AP[0]);
/*  81 */     RA[0] = ECPNcalc.saldoApertura("120", ejercicioAnterior);
/*  82 */     R[0] = ECPNcalc.saldoApertura("129", ejercicioAnterior);
/*  83 */     RA[0] = (-1.0D * RA[0]);
/*  84 */     OA[0] = ECPNcalc.saldoApertura("118", ejercicioAnterior);
/*  85 */     DC[0] = ECPNcalc.saldoApertura("557", ejercicioAnterior);
/*  86 */     DC[0] = (-1.0D * DC[0]);
/*  87 */     OI[0] = ECPNcalc.saldoApertura("137", ejercicioAnterior);
/*  88 */     SD[0] = ECPNcalc.saldoApertura("130", ejercicioAnterior);
/*  89 */     SD[0] += ECPNcalc.saldoApertura("131", ejercicioAnterior);
/*  90 */     SD[0] += ECPNcalc.saldoApertura("132", ejercicioAnterior);
/*  91 */     T[0] = (CE[0] + CN[0] + PE[0] + RE[0] + AP[0] + RA[0] + OA[0] + R[0] + DC[0] + OI[0] + AC[0] + SD[0]);
/*     */ 
/*  94 */     CE[3] = (CE[0] + CE[1] + CE[2]);
/*  95 */     CN[3] = (CN[0] + CN[1] + CN[2]);
/*  96 */     PE[3] = (PE[0] + PE[1] + PE[2]);
/*  97 */     RE[3] = (RE[0] + RE[1] + RE[2]);
/*  98 */     AP[3] = (AP[0] + AP[1] + AP[2]);
/*  99 */     RA[3] = (RA[0] + RA[1] + RA[2]);
/* 100 */     OA[3] = (OA[0] + OA[1] + OA[2]);
/* 101 */     R[3] = (R[0] + R[1] + R[2]);
/* 102 */     DC[3] = (DC[0] + DC[1] + DC[2]);
/* 103 */     OI[3] = (OI[0] + OI[1] + OI[2]);
/* 104 */     AC[3] = (AC[0] + AC[1] + AC[2]);
/* 105 */     SD[3] = (SD[0] + SD[1] + SD[2]);
/* 106 */     T[3] = (CE[3] + CN[3] + PE[3] + RE[3] + AP[3] + RA[3] + OA[3] + R[3] + DC[3] + OI[3] + AC[3] + SD[3]);
/*     */ 
/* 109 */     OI[4] = (ECPNcalc.sumaHaber("137", ejercicioAnterior) - ECPNcalc.sumaDebe("137", ejercicioAnterior));
/* 110 */     SD[4] = (ECPNcalc.sumaHaber("130", ejercicioAnterior) - ECPNcalc.sumaDebe("130", ejercicioAnterior));
/* 111 */     SD[4] += ECPNcalc.sumaHaber("130", ejercicioAnterior) - ECPNcalc.sumaDebe("130", ejercicioAnterior);
/* 112 */     SD[4] += ECPNcalc.sumaHaber("130", ejercicioAnterior) - ECPNcalc.sumaDebe("130", ejercicioAnterior);
/* 113 */     T[4] = (CE[4] + CN[4] + PE[4] + RE[4] + AP[4] + RA[4] + OA[4] + R[4] + DC[4] + OI[4] + AC[4] + SD[4]);
/*     */ 
/* 116 */     CE[6] = ECPNcalc.sumaHaber("100", ejercicioAnterior);
/* 117 */     CE[6] += ECPNcalc.sumaHaber("101", ejercicioAnterior);
/* 118 */     CE[6] += ECPNcalc.sumaHaber("102", ejercicioAnterior);
/* 119 */     CN[6] = ECPNcalc.sumaHaber("103", ejercicioAnterior);
/* 120 */     CN[6] += ECPNcalc.sumaHaber("104", ejercicioAnterior);
/* 121 */     PE[6] = ECPNcalc.sumaHaber("110", ejercicioAnterior);
/* 122 */     T[6] = (CE[6] + CN[6] + PE[6] + RE[6] + AP[6] + RA[6] + OA[6] + R[6] + DC[6] + OI[6] + AC[6] + SD[6]);
/*     */ 
/* 125 */     CE[7] = ECPNcalc.sumaHaber("100", ejercicioAnterior);
/* 126 */     CE[7] += ECPNcalc.sumaDebe("101", ejercicioAnterior);
/* 127 */     CE[7] += ECPNcalc.sumaDebe("102", ejercicioAnterior);
/* 128 */     CE[7] = (-1.0D * CE[7]);
/* 129 */     T[7] = (CE[7] + CN[7] + PE[7] + RE[7] + AP[7] + RA[7] + OA[7] + R[7] + DC[7] + OI[7] + AC[7] + SD[7]);
/*     */ 
/* 132 */     AP[8] = (ECPNcalc.sumaHaber("108", ejercicioAnterior) - ECPNcalc.sumaDebe("108", ejercicioAnterior));
/* 133 */     AP[8] += ECPNcalc.sumaHaber("109", ejercicioAnterior) - ECPNcalc.sumaDebe("109", ejercicioAnterior);
/* 134 */     AP[8] = (-1.0D * AP[8]);
/* 135 */     RA[8] = ECPNcalc.sumaHaber("526", ejercicioAnterior);
/* 136 */     OA[8] = (ECPNcalc.sumaHaber("118", ejercicioAnterior) - ECPNcalc.sumaDebe("118", ejercicioAnterior));
/* 137 */     DC[8] = (ECPNcalc.sumaHaber("557", ejercicioAnterior) - ECPNcalc.sumaDebe("557", ejercicioAnterior));
/* 138 */     T[8] = (CE[8] + CN[8] + PE[8] + RE[8] + AP[8] + RA[8] + OA[8] + R[8] + DC[8] + OI[8] + AC[8] + SD[8]);
/*     */ 
/* 141 */     CE[5] = (CE[6] + CE[7] + CE[8]);
/* 142 */     CN[5] = (CN[6] + CN[7] + CN[8]);
/* 143 */     PE[5] = (PE[6] + PE[7] + PE[8]);
/* 144 */     RE[5] = (RE[6] + RE[7] + RE[8]);
/* 145 */     AP[5] = (AP[6] + AP[7] + AP[8]);
/* 146 */     RA[5] = (RA[6] + RA[7] + RA[8]);
/* 147 */     OA[5] = (OA[6] + OA[7] + OA[8]);
/* 148 */     R[5] = (R[6] + R[7] + R[8]);
/* 149 */     DC[5] = (DC[6] + DC[7] + DC[8]);
/* 150 */     OI[5] = (OI[6] + OI[7] + OI[8]);
/* 151 */     AC[5] = (AC[6] + AC[7] + AC[8]);
/* 152 */     SD[5] = (SD[6] + SD[7] + SD[8]);
/* 153 */     T[5] = (CE[5] + CN[5] + PE[5] + RE[5] + AP[5] + RA[5] + OA[5] + R[5] + DC[5] + OI[5] + AC[5] + SD[5]);
/*     */ 
/* 156 */     PE[9] = ECPNcalc.sumaDebe("110", ejercicioAnterior);
/* 157 */     RE[9] = (ECPNcalc.sumaHaber("112", ejercicioAnterior) - ECPNcalc.sumaDebe("112", ejercicioAnterior));
/* 158 */     RE[9] += ECPNcalc.sumaHaber("113", ejercicioAnterior) - ECPNcalc.sumaDebe("113", ejercicioAnterior);
/* 159 */     RE[9] += ECPNcalc.sumaHaber("114", ejercicioAnterior) - ECPNcalc.sumaDebe("114", ejercicioAnterior);
/* 160 */     RE[9] += ECPNcalc.sumaHaber("119", ejercicioAnterior) - ECPNcalc.sumaDebe("119", ejercicioAnterior);
/* 161 */     RA[9] = (ECPNcalc.sumaDebe("112", ejercicioAnterior) - ECPNcalc.sumaHaber("112", ejercicioAnterior));
/* 162 */     RA[9] += ECPNcalc.sumaDebe("113", ejercicioAnterior) - ECPNcalc.sumaHaber("113", ejercicioAnterior);
/* 163 */     RA[9] += ECPNcalc.sumaDebe("114", ejercicioAnterior) - ECPNcalc.sumaHaber("114", ejercicioAnterior);
/* 164 */     RA[9] += ECPNcalc.sumaHaber("121", ejercicioAnterior) - ECPNcalc.sumaDebe("121", ejercicioAnterior);
/* 165 */     RA[9] += ECPNcalc.sumaDebe("119", ejercicioAnterior) - ECPNcalc.sumaHaber("119", ejercicioAnterior);
/* 166 */     T[9] = (CE[9] + CN[9] + PE[9] + RE[9] + AP[9] + RA[9] + OA[9] + R[9] + DC[9] + OI[9] + AC[9] + SD[9]);
/*     */ 
/* 169 */     CE[10] = (CE[3] + CE[4] + CE[5] + CE[9]);
/* 170 */     CN[10] = (CN[3] + CN[4] + CN[5] + CN[9]);
/* 171 */     PE[10] = (PE[3] + PE[4] + PE[5] + PE[9]);
/* 172 */     RE[10] = (RE[3] + RE[4] + RE[5] + RE[9]);
/* 173 */     AP[10] = (AP[3] + AP[4] + AP[5] + AP[9]);
/* 174 */     RA[10] = (RA[3] + RA[4] + RA[5] + RA[9] + R[3]);
/* 175 */     OA[10] = (OA[3] + OA[4] + OA[5] + OA[9]);
/* 176 */     R[10] = ECPNcalc.saldoApertura("129", ejercicio);
/* 177 */     DC[10] = (DC[3] + DC[4] + DC[5] + DC[9]);
/* 178 */     OI[10] = (OI[3] + OI[4] + OI[5] + OI[9]);
/* 179 */     AC[10] = (AC[3] + AC[4] + AC[5] + AC[9]);
/* 180 */     SD[10] = (SD[3] + SD[4] + SD[5] + SD[9]);
/* 181 */     T[10] = (CE[10] + CN[10] + PE[10] + RE[10] + AP[10] + RA[10] + OA[10] + R[10] + DC[10] + OI[10] + AC[10] + SD[10]);
/*     */ 
/* 184 */     CE[13] = (CE[10] + CE[11] + CE[12]);
/* 185 */     CN[13] = (CN[10] + CN[11] + CN[12]);
/* 186 */     PE[13] = (PE[10] + PE[11] + PE[12]);
/* 187 */     RE[13] = (RE[10] + RE[11] + RE[12]);
/* 188 */     AP[13] = (AP[10] + AP[11] + AP[12]);
/* 189 */     RA[13] = (RA[10] + RA[11] + RA[12]);
/* 190 */     OA[13] = (OA[10] + OA[11] + OA[12]);
/* 191 */     R[13] = (R[10] + R[11] + R[12]);
/* 192 */     DC[13] = (DC[10] + DC[11] + DC[12]);
/* 193 */     OI[13] = (OI[10] + OI[11] + OI[12]);
/* 194 */     AC[13] = (AC[10] + AC[11] + AC[12]);
/* 195 */     SD[13] = (SD[10] + SD[11] + SD[12]);
/* 196 */     T[13] = (CE[13] + CN[13] + PE[13] + RE[13] + AP[13] + RA[13] + OA[13] + R[13] + DC[13] + OI[13] + AC[13] + SD[13]);
/*     */ 
/* 199 */     OI[14] = (ECPNcalc.sumaHaber("137", ejercicio) - ECPNcalc.sumaDebe("137", ejercicio));
/* 200 */     SD[14] = (ECPNcalc.sumaHaber("130", ejercicio) - ECPNcalc.sumaDebe("130", ejercicio));
/* 201 */     SD[14] += ECPNcalc.sumaHaber("130", ejercicio) - ECPNcalc.sumaDebe("130", ejercicio);
/* 202 */     SD[14] += ECPNcalc.sumaHaber("130", ejercicio) - ECPNcalc.sumaDebe("130", ejercicio);
/* 203 */     T[14] = (CE[14] + CN[14] + PE[14] + RE[14] + AP[14] + RA[14] + OA[14] + R[14] + DC[14] + OI[14] + AC[14] + SD[14]);
/*     */ 
/* 206 */     CE[16] = ECPNcalc.sumaHaber("100", ejercicio);
/* 207 */     CE[16] += ECPNcalc.sumaHaber("101", ejercicio);
/* 208 */     CE[16] += ECPNcalc.sumaHaber("102", ejercicio);
/* 209 */     CN[16] = ECPNcalc.sumaHaber("103", ejercicio);
/* 210 */     CN[16] += ECPNcalc.sumaHaber("104", ejercicio);
/* 211 */     PE[16] = ECPNcalc.sumaHaber("110", ejercicio);
/* 212 */     T[16] = (CE[16] + CN[16] + PE[16] + RE[16] + AP[16] + RA[16] + OA[16] + R[16] + DC[16] + OI[16] + AC[16] + SD[16]);
/*     */ 
/* 215 */     CE[17] = ECPNcalc.sumaHaber("100", ejercicio);
/* 216 */     CE[17] += ECPNcalc.sumaDebe("101", ejercicio);
/* 217 */     CE[17] += ECPNcalc.sumaDebe("102", ejercicio);
/* 218 */     CE[17] = (-1.0D * CE[17]);
/* 219 */     T[17] = (CE[17] + CN[17] + PE[17] + RE[17] + AP[17] + RA[17] + OA[17] + R[17] + DC[17] + OI[17] + AC[17] + SD[17]);
/*     */ 
/* 222 */     AP[18] = (ECPNcalc.sumaHaber("108", ejercicio) - ECPNcalc.sumaDebe("108", ejercicio));
/* 223 */     AP[18] += ECPNcalc.sumaHaber("109", ejercicio) - ECPNcalc.sumaDebe("109", ejercicio);
/* 224 */     AP[18] = (-1.0D * AP[18]);
/* 225 */     RA[18] = ECPNcalc.sumaHaber("526", ejercicio);
/* 226 */     OA[18] = (ECPNcalc.sumaHaber("118", ejercicio) - ECPNcalc.sumaDebe("118", ejercicio));
/* 227 */     DC[18] = (ECPNcalc.sumaHaber("557", ejercicio) - ECPNcalc.sumaDebe("557", ejercicio));
/* 228 */     T[18] = (CE[18] + CN[18] + PE[18] + RE[18] + AP[18] + RA[18] + OA[18] + R[18] + DC[18] + OI[18] + AC[18] + SD[18]);
/*     */ 
/* 231 */     CE[15] = (CE[16] + CE[17] + CE[18]);
/* 232 */     CN[15] = (CN[16] + CN[17] + CN[18]);
/* 233 */     PE[15] = (PE[16] + PE[17] + PE[18]);
/* 234 */     RE[15] = (RE[16] + RE[17] + RE[18]);
/* 235 */     AP[15] = (AP[16] + AP[17] + AP[18]);
/* 236 */     RA[15] = (RA[16] + RA[17] + RA[18]);
/* 237 */     OA[15] = (OA[16] + OA[17] + OA[18]);
/* 238 */     R[15] = (R[16] + R[17] + R[18]);
/* 239 */     DC[15] = (DC[16] + DC[17] + DC[18]);
/* 240 */     OI[15] = (OI[16] + OI[17] + OI[18]);
/* 241 */     AC[15] = (AC[16] + AC[17] + AC[18]);
/* 242 */     SD[15] = (SD[16] + SD[17] + SD[18]);
/* 243 */     T[15] = (CE[15] + CN[15] + PE[15] + RE[15] + AP[15] + RA[15] + OA[15] + R[15] + DC[15] + OI[15] + AC[15] + SD[15]);
/*     */ 
/* 246 */     PE[19] = ECPNcalc.sumaDebe("110", ejercicio);
/* 247 */     RE[19] = (ECPNcalc.sumaHaber("112", ejercicio) - ECPNcalc.sumaDebe("112", ejercicio));
/* 248 */     RE[19] += ECPNcalc.sumaHaber("113", ejercicio) - ECPNcalc.sumaDebe("113", ejercicio);
/* 249 */     RE[19] += ECPNcalc.sumaHaber("114", ejercicio) - ECPNcalc.sumaDebe("114", ejercicio);
/* 250 */     RE[19] += ECPNcalc.sumaHaber("119", ejercicio) - ECPNcalc.sumaDebe("119", ejercicio);
/* 251 */     RA[19] = (ECPNcalc.sumaDebe("112", ejercicio) - ECPNcalc.sumaHaber("112", ejercicio));
/* 252 */     RA[19] += ECPNcalc.sumaDebe("113", ejercicio) - ECPNcalc.sumaHaber("113", ejercicio);
/* 253 */     RA[19] += ECPNcalc.sumaDebe("114", ejercicio) - ECPNcalc.sumaHaber("114", ejercicio);
/* 254 */     RA[19] += ECPNcalc.sumaHaber("121", ejercicio) - ECPNcalc.sumaDebe("121", ejercicio);
/* 255 */     RA[19] += ECPNcalc.sumaDebe("119", ejercicio) - ECPNcalc.sumaHaber("119", ejercicio);
/* 256 */     T[19] = (CE[19] + CN[19] + PE[19] + RE[19] + AP[19] + RA[19] + OA[19] + R[19] + DC[19] + OI[19] + AC[19] + SD[19]);
/*     */ 
/* 259 */     CE[20] = (CE[13] + CE[14] + CE[15] + CE[19]);
/* 260 */     CN[20] = (CN[13] + CN[14] + CN[15] + CN[19]);
/* 261 */     PE[20] = (PE[13] + PE[14] + PE[15] + PE[19]);
/* 262 */     RE[20] = (RE[13] + RE[14] + RE[15] + RE[19]);
/* 263 */     AP[20] = (AP[13] + AP[14] + AP[15] + AP[19]);
/* 264 */     RA[20] = (RA[13] + RA[14] + RA[15] + RA[19] + R[13]);
/* 265 */     OA[20] = (OA[13] + OA[14] + OA[15] + OA[19]);
/* 266 */     R[20] = (ECPNcalc.sumaDebe("6", ejercicio) - ECPNcalc.sumaHaber("6", ejercicio));
/* 267 */     R[20] += ECPNcalc.sumaDebe("7", ejercicio) - ECPNcalc.sumaHaber("7", ejercicio);
/* 268 */     R[20] = (-1.0D * R[20]);
/* 269 */     DC[20] = (DC[13] + DC[14] + DC[15] + DC[19]);
/* 270 */     OI[20] = (OI[13] + OI[14] + OI[15] + OI[19]);
/* 271 */     AC[20] = (AC[13] + AC[14] + AC[15] + AC[19]);
/* 272 */     SD[20] = (SD[13] + SD[14] + SD[15] + SD[19]);
/* 273 */     T[20] = (CE[20] + CN[20] + PE[20] + RE[20] + AP[20] + RA[20] + OA[20] + R[20] + DC[20] + OI[20] + AC[20] + SD[20]);
/*     */ 
/* 277 */     String EOL = FinLinea.get();
/* 278 */     String nombreEmpresa = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa()).getNombre(Inicio.p.getEmpresa());
/* 279 */     StringBuilder sb = new StringBuilder();
/* 280 */     Formatter formater = new Formatter(sb);
/* 281 */     formater.format(new StringBuilder().append("%s %s").append(EOL).toString(), new Object[] { "Estado de cambios en el patrimonio neto para", nombreEmpresa });
/*     */ 
/* 283 */     formater.format(new StringBuilder().append("%s %s").append(EOL).toString(), new Object[] { Mensajes.getString("ejercicio"), ejercicio });
/* 284 */     formater.format(new StringBuilder().append(";;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s").append(EOL).toString(), new Object[] { CEs, CNs, PEs, REs, APs, RAs, OAs, Rs, DCs, OIs, ACs, SDs, Ts });
/*     */ 
/* 287 */     int ej = Integer.parseInt(ejercicio);
/*     */ 
/* 289 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { new StringBuilder().append("A) Saldo, final ejercicio ").append(Integer.toString(ej - 2)).toString(), "511", Double.valueOf(CE[0]), Double.valueOf(CN[0]), Double.valueOf(PE[0]), Double.valueOf(RE[0]), Double.valueOf(AP[0]), Double.valueOf(RA[0]), Double.valueOf(OA[0]), Double.valueOf(R[0]), Double.valueOf(DC[0]), Double.valueOf(OI[0]), Double.valueOf(AC[0]), Double.valueOf(SD[0]), Double.valueOf(T[0]) });
/*     */ 
/* 292 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { new StringBuilder().append("I. Ajustes por cambios de criterio del ejercicio ").append(Integer.toString(ej - 2)).append(" y anteriores").toString(), "512", Double.valueOf(CE[1]), Double.valueOf(CN[1]), Double.valueOf(PE[1]), Double.valueOf(RE[1]), Double.valueOf(AP[1]), Double.valueOf(RA[1]), Double.valueOf(OA[1]), Double.valueOf(R[1]), Double.valueOf(DC[1]), Double.valueOf(OI[1]), Double.valueOf(AC[1]), Double.valueOf(SD[1]), Double.valueOf(T[1]) });
/*     */ 
/* 295 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { new StringBuilder().append("I. Ajustes por errores del ejercicio ").append(Integer.toString(ej - 2)).append(" y anteriores").toString(), "513", Double.valueOf(CE[2]), Double.valueOf(CN[2]), Double.valueOf(PE[2]), Double.valueOf(RE[2]), Double.valueOf(AP[2]), Double.valueOf(RA[2]), Double.valueOf(OA[2]), Double.valueOf(R[2]), Double.valueOf(DC[2]), Double.valueOf(OI[2]), Double.valueOf(AC[2]), Double.valueOf(SD[2]), Double.valueOf(T[2]) });
/*     */ 
/* 298 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { new StringBuilder().append("B) Saldo ajustado inicio del ejercicio ").append(Integer.toString(ej - 1)).toString(), "514", Double.valueOf(CE[3]), Double.valueOf(CN[3]), Double.valueOf(PE[3]), Double.valueOf(RE[3]), Double.valueOf(AP[3]), Double.valueOf(RA[3]), Double.valueOf(OA[3]), Double.valueOf(R[3]), Double.valueOf(DC[3]), Double.valueOf(OI[3]), Double.valueOf(AC[3]), Double.valueOf(SD[3]), Double.valueOf(T[3]) });
/*     */ 
/* 301 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { "I. Total ingresos y gastos reconocidos", "515", Double.valueOf(CE[4]), Double.valueOf(CN[4]), Double.valueOf(PE[4]), Double.valueOf(RE[4]), Double.valueOf(AP[4]), Double.valueOf(RA[4]), Double.valueOf(OA[4]), Double.valueOf(R[4]), Double.valueOf(DC[4]), Double.valueOf(OI[4]), Double.valueOf(AC[4]), Double.valueOf(SD[4]), Double.valueOf(T[4]) });
/*     */ 
/* 304 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { "II. Operaciones con socios o propietarios", "516", Double.valueOf(CE[5]), Double.valueOf(CN[5]), Double.valueOf(PE[5]), Double.valueOf(RE[5]), Double.valueOf(AP[5]), Double.valueOf(RA[5]), Double.valueOf(OA[5]), Double.valueOf(R[5]), Double.valueOf(DC[5]), Double.valueOf(OI[5]), Double.valueOf(AC[5]), Double.valueOf(SD[5]), Double.valueOf(T[5]) });
/*     */ 
/* 307 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { "1. Aumentos de capital", "517", Double.valueOf(CE[6]), Double.valueOf(CN[6]), Double.valueOf(PE[6]), Double.valueOf(RE[6]), Double.valueOf(AP[6]), Double.valueOf(RA[6]), Double.valueOf(OA[6]), Double.valueOf(R[6]), Double.valueOf(DC[6]), Double.valueOf(OI[6]), Double.valueOf(AC[6]), Double.valueOf(SD[6]), Double.valueOf(T[6]) });
/*     */ 
/* 310 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { "2. (-) Reducciones de capital", "518", Double.valueOf(CE[7]), Double.valueOf(CN[7]), Double.valueOf(PE[7]), Double.valueOf(RE[7]), Double.valueOf(AP[7]), Double.valueOf(RA[7]), Double.valueOf(OA[7]), Double.valueOf(R[7]), Double.valueOf(DC[7]), Double.valueOf(OI[7]), Double.valueOf(AC[7]), Double.valueOf(SD[7]), Double.valueOf(T[7]) });
/*     */ 
/* 313 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { "3. Otras operaciones con socios o propietarios", "526", Double.valueOf(CE[8]), Double.valueOf(CN[8]), Double.valueOf(PE[8]), Double.valueOf(RE[8]), Double.valueOf(AP[8]), Double.valueOf(RA[8]), Double.valueOf(OA[8]), Double.valueOf(R[8]), Double.valueOf(DC[8]), Double.valueOf(OI[8]), Double.valueOf(AC[8]), Double.valueOf(SD[8]), Double.valueOf(T[8]) });
/*     */ 
/* 316 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { "III. Otras variaciones del patrimonio neto", "524", Double.valueOf(CE[9]), Double.valueOf(CN[9]), Double.valueOf(PE[9]), Double.valueOf(RE[9]), Double.valueOf(AP[9]), Double.valueOf(RA[9]), Double.valueOf(OA[9]), Double.valueOf(R[9]), Double.valueOf(DC[9]), Double.valueOf(OI[9]), Double.valueOf(AC[9]), Double.valueOf(SD[9]), Double.valueOf(T[9]) });
/*     */ 
/* 319 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { new StringBuilder().append("C) Saldo, final ejercicio ").append(Integer.toString(ej - 1)).toString(), "511", Double.valueOf(CE[10]), Double.valueOf(CN[10]), Double.valueOf(PE[10]), Double.valueOf(RE[10]), Double.valueOf(AP[10]), Double.valueOf(RA[10]), Double.valueOf(OA[10]), Double.valueOf(R[10]), Double.valueOf(DC[10]), Double.valueOf(OI[10]), Double.valueOf(AC[10]), Double.valueOf(SD[10]), Double.valueOf(T[10]) });
/*     */ 
/* 322 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { new StringBuilder().append("I. Ajustes por cambios de criterio del ejercicio ").append(Integer.toString(ej - 1)).toString(), "512", Double.valueOf(CE[11]), Double.valueOf(CN[11]), Double.valueOf(PE[11]), Double.valueOf(RE[11]), Double.valueOf(AP[11]), Double.valueOf(RA[11]), Double.valueOf(OA[11]), Double.valueOf(R[11]), Double.valueOf(DC[11]), Double.valueOf(OI[11]), Double.valueOf(AC[11]), Double.valueOf(SD[11]), Double.valueOf(T[11]) });
/*     */ 
/* 325 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { new StringBuilder().append("I. Ajustes por errores del ejercicio ").append(Integer.toString(ej - 1)).toString(), "513", Double.valueOf(CE[12]), Double.valueOf(CN[12]), Double.valueOf(PE[12]), Double.valueOf(RE[12]), Double.valueOf(AP[12]), Double.valueOf(RA[12]), Double.valueOf(OA[12]), Double.valueOf(R[12]), Double.valueOf(DC[12]), Double.valueOf(OI[12]), Double.valueOf(AC[12]), Double.valueOf(SD[12]), Double.valueOf(T[12]) });
/*     */ 
/* 328 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { new StringBuilder().append("D) Saldo ajustado inicio del ejercicio ").append(Integer.toString(ej)).toString(), "514", Double.valueOf(CE[13]), Double.valueOf(CN[13]), Double.valueOf(PE[13]), Double.valueOf(RE[13]), Double.valueOf(AP[13]), Double.valueOf(RA[13]), Double.valueOf(OA[13]), Double.valueOf(R[13]), Double.valueOf(DC[13]), Double.valueOf(OI[13]), Double.valueOf(AC[13]), Double.valueOf(SD[13]), Double.valueOf(T[13]) });
/*     */ 
/* 331 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { "I. Total ingresos y gastos reconocidos", "515", Double.valueOf(CE[14]), Double.valueOf(CN[14]), Double.valueOf(PE[14]), Double.valueOf(RE[14]), Double.valueOf(AP[14]), Double.valueOf(RA[14]), Double.valueOf(OA[14]), Double.valueOf(R[14]), Double.valueOf(DC[14]), Double.valueOf(OI[14]), Double.valueOf(AC[14]), Double.valueOf(SD[14]), Double.valueOf(T[14]) });
/*     */ 
/* 334 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { "II. Operaciones con socios o propietarios", "516", Double.valueOf(CE[15]), Double.valueOf(CN[15]), Double.valueOf(PE[15]), Double.valueOf(RE[15]), Double.valueOf(AP[15]), Double.valueOf(RA[15]), Double.valueOf(OA[15]), Double.valueOf(R[15]), Double.valueOf(DC[15]), Double.valueOf(OI[15]), Double.valueOf(AC[15]), Double.valueOf(SD[15]), Double.valueOf(T[15]) });
/*     */ 
/* 337 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { "1. Aumentos de capital", "517", Double.valueOf(CE[16]), Double.valueOf(CN[16]), Double.valueOf(PE[16]), Double.valueOf(RE[16]), Double.valueOf(AP[16]), Double.valueOf(RA[16]), Double.valueOf(OA[16]), Double.valueOf(R[16]), Double.valueOf(DC[16]), Double.valueOf(OI[16]), Double.valueOf(AC[16]), Double.valueOf(SD[16]), Double.valueOf(T[16]) });
/*     */ 
/* 340 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { "2. (-) Reducciones de capital", "518", Double.valueOf(CE[17]), Double.valueOf(CN[17]), Double.valueOf(PE[17]), Double.valueOf(RE[17]), Double.valueOf(AP[17]), Double.valueOf(RA[17]), Double.valueOf(OA[17]), Double.valueOf(R[17]), Double.valueOf(DC[17]), Double.valueOf(OI[17]), Double.valueOf(AC[17]), Double.valueOf(SD[17]), Double.valueOf(T[17]) });
/*     */ 
/* 343 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { "3. Otras operaciones con socios o propietarios", "526", Double.valueOf(CE[18]), Double.valueOf(CN[18]), Double.valueOf(PE[18]), Double.valueOf(RE[18]), Double.valueOf(AP[18]), Double.valueOf(RA[18]), Double.valueOf(OA[18]), Double.valueOf(R[18]), Double.valueOf(DC[18]), Double.valueOf(OI[18]), Double.valueOf(AC[18]), Double.valueOf(SD[18]), Double.valueOf(T[18]) });
/*     */ 
/* 346 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { "III. Otras variaciones del patrimonio neto", "524", Double.valueOf(CE[19]), Double.valueOf(CN[19]), Double.valueOf(PE[19]), Double.valueOf(RE[19]), Double.valueOf(AP[19]), Double.valueOf(RA[19]), Double.valueOf(OA[19]), Double.valueOf(R[19]), Double.valueOf(DC[19]), Double.valueOf(OI[19]), Double.valueOf(AC[19]), Double.valueOf(SD[19]), Double.valueOf(T[19]) });
/*     */ 
/* 349 */     formater.format(new StringBuilder().append("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f").append(EOL).toString(), new Object[] { new StringBuilder().append("C) Saldo, final ejercicio ").append(Integer.toString(ej)).toString(), "525", Double.valueOf(CE[20]), Double.valueOf(CN[20]), Double.valueOf(PE[20]), Double.valueOf(RE[20]), Double.valueOf(AP[20]), Double.valueOf(RA[20]), Double.valueOf(OA[20]), Double.valueOf(R[20]), Double.valueOf(DC[20]), Double.valueOf(OI[20]), Double.valueOf(AC[20]), Double.valueOf(SD[20]), Double.valueOf(T[20]) });
/*     */ 
/* 353 */     JFileChooser fc = new JFileChooser();
/* 354 */     fc.setSelectedFile(new File(new StringBuilder().append("ECPN").append(ejercicio).append(".csv").toString()));
/* 355 */     int retorno = fc.showSaveDialog(Inicio.frame);
/* 356 */     if (retorno == 0) {
/* 357 */       File archivo = fc.getSelectedFile();
/* 358 */       archivo = AddExtension.csv(archivo);
/* 359 */       GrabarFichero salida = new GrabarFichero(archivo);
/* 360 */       salida.insertar(sb.toString());
/* 361 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.ECPN
 * JD-Core Version:    0.6.2
 */