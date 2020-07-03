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
/*     */ public class Remesas19 extends RemesasBase
/*     */ {
/*     */   public Remesas19(Date fechaCargo, String nombrePresentador, String nifPresentador, String sufijo, String cccOrdenante, ArrayList<TipoVencimiento> vencimientos)
/*     */   {
/*  23 */     super(fechaCargo, nombrePresentador, nifPresentador, sufijo, cccOrdenante, vencimientos);
/*  24 */     guardarArchivo(crearString());
/*     */   }
/*     */ 
/*     */   private void guardarArchivo(StringBuffer sb) {
/*  28 */     JFileChooser fc = new JFileChooser();
/*  29 */     fc.setSelectedFile(new File("RE_" + this.formatter.format(new Date()) + ".q19"));
/*  30 */     int retorno = fc.showSaveDialog(Inicio.frame);
/*  31 */     if (retorno == 0) {
/*  32 */       File archivo = fc.getSelectedFile();
/*  33 */       archivo = AddExtension.q19(archivo);
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
/*  46 */     sb.append(CABECERA_DE_PRESENTADOR_19);
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
/*  62 */     sb.append(CABECERA_DE_ORDENANTE_19);
/*  63 */     sb.append(this.nifOrdenante.toUpperCase());
/*  64 */     sb.append(this.sufijo.toUpperCase());
/*  65 */     sb.append(this.formatter.format(new Date()));
/*  66 */     sb.append(this.formatter.format(this.fechaCargo));
/*  67 */     sb.append(repasaAcentos(this.nombreOrdenante).toUpperCase());
/*  68 */     addEspacios(sb, 40 - this.nombreOrdenante.length());
/*  69 */     sb.append(this.cccOrdenante);
/*  70 */     addEspacios(sb, 8);
/*  71 */     sb.append("01");
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
/*  88 */       sb.append(INDIVIDUAL_OBLIGATORIO_19);
/*  89 */       sb.append(this.nifOrdenante.toUpperCase());
/*  90 */       sb.append(this.sufijo.toUpperCase());
/*  91 */       String codRef = addCerosIzquierda(Integer.toString(codigo), 12);
/*  92 */       sb.append(codRef);
/*  93 */       sb.append(repasaAcentos(nombreCliente).toUpperCase());
/*  94 */       addEspacios(sb, 40 - nombreCliente.length());
/*  95 */       sb.append(cccCliente);
/*  96 */       sb.append(importe);
/*  97 */       addEspacios(sb, 6);
/*  98 */       sb.append(addCerosIzquierda(v.getFactura(), 10));
/*  99 */       sb.append(concepto);
/* 100 */       addEspacios(sb, 40 - concepto.length());
/* 101 */       addEspacios(sb, 8);
/* 102 */       sb.append("\n");
/* 103 */       numRegistros++;
/* 104 */       totalImportes = Double.valueOf(totalImportes.doubleValue() + v.getImporte());
/*     */     }
/*     */ 
/* 108 */     String importe = decimalFormat2Decimals(doubleTwoDecimals(totalImportes));
/* 109 */     importe = importe.replace('.', ' ');
/* 110 */     importe = importe.replace(",", " ");
/* 111 */     importe = importe.replaceAll(" ", "");
/* 112 */     importe = addCerosIzquierda(importe, 10);
/* 113 */     String domicOrd = Integer.toString(numRegistros);
/* 114 */     String registrosOrd = Integer.toString(numRegistros + 2);
/* 115 */     domicOrd = addCerosIzquierda(domicOrd, 10);
/* 116 */     registrosOrd = addCerosIzquierda(registrosOrd, 10);
/*     */ 
/* 118 */     sb.append(TOTAL_DE_ORDENANTE_19);
/* 119 */     sb.append(this.nifOrdenante.toUpperCase());
/* 120 */     sb.append(this.sufijo.toUpperCase());
/* 121 */     addEspacios(sb, 12);
/* 122 */     addEspacios(sb, 40);
/* 123 */     addEspacios(sb, 20);
/* 124 */     sb.append(importe);
/* 125 */     addEspacios(sb, 6);
/* 126 */     sb.append(domicOrd);
/* 127 */     sb.append(registrosOrd);
/* 128 */     addEspacios(sb, 20);
/* 129 */     addEspacios(sb, 18);
/* 130 */     sb.append("\n");
/*     */ 
/* 133 */     String registrosPre = Integer.toString(numRegistros + 4);
/* 134 */     registrosPre = addCerosIzquierda(registrosPre, 10);
/*     */ 
/* 136 */     sb.append(TOTAL_GENERAL_19);
/* 137 */     sb.append(this.nifPresentador.toUpperCase());
/* 138 */     sb.append(this.sufijo.toUpperCase());
/* 139 */     addEspacios(sb, 12);
/* 140 */     addEspacios(sb, 40);
/* 141 */     sb.append("0001");
/* 142 */     addEspacios(sb, 16);
/* 143 */     sb.append(importe);
/* 144 */     addEspacios(sb, 6);
/* 145 */     sb.append(domicOrd);
/* 146 */     sb.append(registrosPre);
/* 147 */     addEspacios(sb, 20);
/* 148 */     addEspacios(sb, 18);
/*     */ 
/* 150 */     return sb;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.listados.Remesas19
 * JD-Core Version:    0.6.2
 */