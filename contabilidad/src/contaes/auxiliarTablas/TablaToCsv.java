/*    */ package contaes.auxiliarTablas;
/*    */ 
/*    */ import contaes.Inicio;
/*    */ import contaes.manejoDatos.auxiliar.AddExtension;
/*    */ import contaes.manejoDatos.auxiliar.FinLinea;
/*    */ import contaes.manejoDatos.auxiliar.GrabarFichero;
/*    */ import internationalization.Mensajes;
/*    */ import java.io.File;
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.DecimalFormatSymbols;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.GregorianCalendar;
/*    */ import javax.swing.JFileChooser;
/*    */ 
/*    */ public class TablaToCsv
/*    */ {
/*    */   ScrollableTableModel modelo;
/*    */   String titulo;
/*    */ 
/*    */   public TablaToCsv(ScrollableTableModel modelo, String titulo)
/*    */   {
/* 30 */     this.modelo = modelo;
/* 31 */     this.titulo = titulo;
/* 32 */     guardarTabla();
/*    */   }
/*    */ 
/*    */   private void guardarTabla() {
/* 36 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 37 */     formato.setDecimalSeparator(',');
/* 38 */     formato.setPerMill('.');
/* 39 */     DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/* 40 */     String EOL = FinLinea.get();
/* 41 */     JFileChooser fc = new JFileChooser();
/* 42 */     fc.setSelectedFile(new File(this.titulo + ".csv"));
/* 43 */     int retorno = fc.showSaveDialog(Inicio.frame);
/* 44 */     if (retorno == 0) {
/* 45 */       File archivo = fc.getSelectedFile();
/* 46 */       archivo = AddExtension.csv(archivo);
/* 47 */       GrabarFichero salida = new GrabarFichero(archivo);
/*    */ 
/* 49 */       int numColumns = this.modelo.getColumnCount();
/* 50 */       int numRows = this.modelo.getRowCount();
/* 51 */       String cabecera = "";
/* 52 */       for (int x = 0; x < numColumns; x++) {
/* 53 */         cabecera = cabecera + this.modelo.getColumnName(x) + ";";
/*    */       }
/* 55 */       cabecera = cabecera.substring(0, cabecera.lastIndexOf(";")) + EOL;
/* 56 */       salida.insertar(cabecera);
/*    */ 
/* 58 */       String linea = "";
/* 59 */       for (int i = 0; i < numRows; i++) {
/* 60 */         linea = "";
/* 61 */         for (int j = 0; j < numColumns; j++) {
/* 62 */           Object objeto = this.modelo.getValueAt(i, j);
/* 63 */           if ((objeto instanceof Double)) {
/* 64 */             linea = linea + fn.format(objeto) + ";";
/*    */           }
/* 66 */           else if ((objeto instanceof Date)) {
/* 67 */             String cad = objeto.toString();
/* 68 */             int year = Integer.parseInt(cad.substring(0, 4));
/* 69 */             int month = Integer.parseInt(cad.substring(5, 7)) - 1;
/* 70 */             int date = Integer.parseInt(cad.substring(8));
/* 71 */             GregorianCalendar calendario = new GregorianCalendar();
/* 72 */             calendario.set(year, month, date);
/* 73 */             SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
/* 74 */             linea = linea + sdf.format(calendario.getTime()) + ";";
/*    */           }
/* 76 */           else if ((objeto instanceof Integer)) {
/* 77 */             linea = linea + objeto.toString() + ";";
/*    */           }
/*    */           else {
/* 80 */             String str = objeto.toString();
/* 81 */             if (str.equals("false")) str = Mensajes.getString("no");
/* 82 */             if (str.equals("true")) str = Mensajes.getString("yes");
/* 83 */             linea = linea + '"' + str + '"' + ";";
/*    */           }
/*    */         }
/*    */ 
/* 87 */         linea = linea.substring(0, linea.lastIndexOf(";")) + EOL;
/* 88 */         salida.insertar(linea);
/*    */       }
/* 90 */       salida.cerrar();
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliarTablas.TablaToCsv
 * JD-Core Version:    0.6.2
 */