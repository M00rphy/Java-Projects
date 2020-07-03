/*     */ package contaes.listados;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.manejoDatos.TipoVencimiento;
/*     */ import contaes.manejoDatos.auxiliar.AddExtension;
/*     */ import contaes.manejoDatos.auxiliar.GrabarFichero;
/*     */ import java.io.File;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import javax.swing.JFileChooser;
/*     */ 
/*     */ public class Remesas58 extends RemesasBase
/*     */ {
/*     */   public Remesas58(Date fechaCargo, String nombrePresentador, String nifPresentador, String sufijo, String cccOrdenante, ArrayList<TipoVencimiento> vencimientos)
/*     */   {
/*  23 */     super(fechaCargo, nombrePresentador, nifPresentador, sufijo, cccOrdenante, vencimientos);
/*  24 */     guardarArchivo(crearString());
/*     */   }
/*     */ 
/*     */   private void guardarArchivo(StringBuffer sb) {
/*  28 */     JFileChooser fc = new JFileChooser();
/*  29 */     fc.setSelectedFile(new File("RE_" + this.formatter.format(new Date()) + ".q58"));
/*  30 */     int retorno = fc.showSaveDialog(Inicio.frame);
/*  31 */     if (retorno == 0) {
/*  32 */       File archivo = fc.getSelectedFile();
/*  33 */       archivo = AddExtension.q58(archivo);
/*  34 */       GrabarFichero salida = new GrabarFichero(archivo);
/*  35 */       salida.insertar(sb.toString());
/*  36 */       salida.cerrar();
/*     */     }
/*     */   }
/*     */ 
/*     */   private StringBuffer crearString() {
/*  41 */     int numRegistros = 0;
/*  42 */     Double totalImportes = Double.valueOf(0.0D);
/*  43 */     StringBuffer sb = new StringBuffer();
/*     */ 
/*  46 */     sb.append(CABECERA_DE_PRESENTADOR_58);
/*  47 */     sb.append(this.nifPresentador.toUpperCase());
/*  48 */     sb.append(this.sufijo.toUpperCase());
/*  49 */     sb.append(this.formatter.format(new Date()));
/*  50 */     addEspacios(sb, 6);
/*  51 */     sb.append(repasaAcentos(this.nombrePresentador).toUpperCase());
/*  52 */     addEspacios(sb, 40 - this.nombrePresentador.length());
/*  53 */     addEspacios(sb, 20);
/*  54 */     sb.append(getBanco());
/*  55 */     sb.append(getSucursal());
/*  56 */     addEspacios(sb, 12);
/*  57 */     addEspacios(sb, 40);
/*  58 */     addEspacios(sb, 14);
/*  59 */     sb.append("\n");
/*     */ 
/*  62 */     sb.append(CABECERA_DE_ORDENANTE_58);
/*  63 */     sb.append(this.nifOrdenante.toUpperCase());
/*  64 */     sb.append(this.sufijo.toUpperCase());
/*  65 */     sb.append(this.formatter.format(new Date()));
/*  66 */     addEspacios(sb, 6);
/*  67 */     sb.append(repasaAcentos(this.nombreOrdenante).toUpperCase());
/*  68 */     addEspacios(sb, 40 - this.nombreOrdenante.length());
/*  69 */     sb.append(this.cccOrdenante);
/*  70 */     addEspacios(sb, 8);
/*  71 */     sb.append("06");
/*  72 */     addEspacios(sb, 10);
/*  73 */     addEspacios(sb, 40);
/*  74 */     addEspacios(sb, 14);
/*  75 */     sb.append("\n");
/*     */ 
/*  77 */     for (TipoVencimiento v : this.vencimientos) {
/*  78 */       int codigo = v.getCuenta();
/*  79 */       String nombreCliente = getNombreCliente(codigo);
/*  80 */       String cccCliente = getCccCliente(codigo);
/*  81 */       String importe = decimalFormat2Decimals(doubleTwoDecimals(Double.valueOf(v.getImporte())));
/*  82 */       importe = importe.replace('.', ' ');
/*  83 */       importe = importe.replace(",", " ");
/*  84 */       importe = importe.replaceAll(" ", "");
/*  85 */       importe = addCerosIzquierda(importe, 10);
/*  86 */       String concepto = "FRA. " + v.getFactura();
/*     */ 
/*  88 */       String fecha = v.getFecha();
/*  89 */       String fechaOrigen = v.getFechaf();
/*     */ 
/*  91 */       ArrayList dirCl = getDireccionCliente(codigo);
/*     */ 
/*  93 */       sb.append(INDIVIDUAL_OBLIGATORIO_58);
/*  94 */       sb.append(this.nifOrdenante.toUpperCase());
/*  95 */       sb.append(this.sufijo.toUpperCase());
/*  96 */       String codRef = addCerosIzquierda(Integer.toString(codigo), 12);
/*  97 */       sb.append(codRef);
/*  98 */       sb.append(repasaAcentos(nombreCliente).toUpperCase());
/*  99 */       addEspacios(sb, 40 - nombreCliente.length());
/* 100 */       sb.append(cccCliente);
/* 101 */       sb.append(importe);
/* 102 */       addEspacios(sb, 6);
/* 103 */       sb.append(addCerosIzquierda(v.getFactura(), 10));
/* 104 */       sb.append(concepto);
/* 105 */       addEspacios(sb, 40 - concepto.length());
/* 106 */       sb.append(fecha);
/* 107 */       addEspacios(sb, 2);
/* 108 */       sb.append("\n");
/*     */ 
/* 111 */       sb.append(INDIVIDUAL_OBLIGATORIO_DOMICILIO_58);
/* 112 */       sb.append(this.nifOrdenante.toUpperCase());
/* 113 */       sb.append(this.sufijo.toUpperCase());
/* 114 */       sb.append(codRef);
/* 115 */       sb.append(espacioDerecha(repasaAcentos((String)dirCl.get(0)).toUpperCase(), 40));
/*     */ 
/* 118 */       addEspacios(sb, 35);
/* 119 */       sb.append((String)dirCl.get(2));
/* 120 */       sb.append(espacioDerecha(repasaAcentos((String)dirCl.get(1)).toUpperCase(), 38));
/* 121 */       sb.append(((String)dirCl.get(2)).substring(0, 2));
/* 122 */       sb.append(fechaOrigen);
/* 123 */       addEspacios(sb, 8);
/* 124 */       sb.append("\n");
/*     */ 
/* 126 */       numRegistros++;
/* 127 */       totalImportes = Double.valueOf(totalImportes.doubleValue() + v.getImporte());
/*     */     }
/*     */ 
/* 131 */     String importe = decimalFormat2Decimals(doubleTwoDecimals(totalImportes));
/* 132 */     importe = importe.replace('.', ' ');
/* 133 */     importe = importe.replace(",", " ");
/* 134 */     importe = importe.replaceAll(" ", "");
/* 135 */     importe = addCerosIzquierda(importe, 10);
/* 136 */     String domicOrd = Integer.toString(numRegistros);
/* 137 */     String registrosOrd = Integer.toString(numRegistros + 2);
/* 138 */     domicOrd = addCerosIzquierda(domicOrd, 10);
/* 139 */     registrosOrd = addCerosIzquierda(registrosOrd, 10);
/*     */ 
/* 141 */     sb.append(TOTAL_DE_ORDENANTE_58);
/* 142 */     sb.append(this.nifOrdenante.toUpperCase());
/* 143 */     sb.append(this.sufijo.toUpperCase());
/* 144 */     addEspacios(sb, 12);
/* 145 */     addEspacios(sb, 40);
/* 146 */     addEspacios(sb, 20);
/* 147 */     sb.append(importe);
/* 148 */     addEspacios(sb, 6);
/* 149 */     sb.append(domicOrd);
/* 150 */     sb.append(registrosOrd);
/* 151 */     addEspacios(sb, 20);
/* 152 */     addEspacios(sb, 18);
/* 153 */     sb.append("\n");
/*     */ 
/* 156 */     String registrosPre = Integer.toString(numRegistros + 4);
/* 157 */     registrosPre = addCerosIzquierda(registrosPre, 10);
/*     */ 
/* 159 */     sb.append(TOTAL_GENERAL_58);
/* 160 */     sb.append(this.nifPresentador.toUpperCase());
/* 161 */     sb.append(this.sufijo.toUpperCase());
/* 162 */     addEspacios(sb, 12);
/* 163 */     addEspacios(sb, 40);
/* 164 */     sb.append("0001");
/* 165 */     addEspacios(sb, 16);
/* 166 */     sb.append(importe);
/* 167 */     addEspacios(sb, 6);
/* 168 */     sb.append(domicOrd);
/* 169 */     sb.append(registrosPre);
/* 170 */     addEspacios(sb, 20);
/* 171 */     addEspacios(sb, 18);
/*     */ 
/* 173 */     return sb;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.Remesas58
 * JD-Core Version:    0.6.2
 */