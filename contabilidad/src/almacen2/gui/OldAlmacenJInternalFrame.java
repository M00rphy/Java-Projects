/*     */ package almacen2.gui;
/*     */ 
/*     */ import almacen2.data.MySQLConectionOldAlmacen;
/*     */ import contaes.auxiliar.DocCuarentaCarac;
/*     */ import contaes.auxiliar.DocDiezyOchoCarac;
/*     */ import contaes.auxiliar.DocDigitos;
/*     */ import contaes.auxiliar.DocNumPositivos;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import java.awt.Container;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import javax.swing.event.InternalFrameEvent;
/*     */ import javax.swing.event.InternalFrameListener;
/*     */ 
/*     */ public class OldAlmacenJInternalFrame extends JInternalFrame
/*     */ {
/*     */   MySQLConectionOldAlmacen con;
/*     */   ResultSet res;
/*     */   String[] ficha;
/*     */   DecimalFormat fn;
/*     */   private JButton botonAlta;
/*     */   private JButton botonAnterior;
/*     */   private JButton botonLimpiar;
/*     */   private JButton botonSiguiente;
/*     */   private JTextField campoCodigoProveedor;
/*     */   private JTextField campoCoste;
/*     */   private JTextField campoDescripcion;
/*     */   private JLabel campoFamilia;
/*     */   private ICalendarTextField campoFechaCompra;
/*     */   private ICalendarTextField campoFechaVenta;
/*     */   private JTextField campoMargen;
/*     */   private JLabel campoNombreProveedor;
/*     */   private JTextField campoPvp;
/*     */   private JTextField campoRefProv;
/*     */   private JTextField campoReferencia;
/*     */   private JTextField campoUnidades;
/*     */   private JTextField campopvr;
/*     */   private JButton jButton1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel10;
/*     */   private JLabel jLabel11;
/*     */   private JLabel jLabel12;
/*     */   private JLabel jLabel13;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JLabel jLabel7;
/*     */   private JLabel jLabel8;
/*     */   private JLabel jLabel9;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JPanel jPanel3;
/*     */   private JPanel jPanel4;
/*     */   private JPanel jPanel5;
/*     */ 
/*     */   public OldAlmacenJInternalFrame()
/*     */   {
/*  45 */     initComponents();
/*  46 */     this.con = new MySQLConectionOldAlmacen();
/*     */ 
/*  48 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  49 */     formato.setDecimalSeparator('.');
/*  50 */     this.fn = new DecimalFormat("0.00", formato);
/*     */   }
/*     */ 
/*     */   private void limpiarForm() {
/*  54 */     this.campoReferencia.setText("");
/*  55 */     this.campoFamilia.setText("");
/*  56 */     this.campoCodigoProveedor.setText("");
/*  57 */     this.campoNombreProveedor.setText("");
/*  58 */     this.campoRefProv.setText("");
/*  59 */     this.campoDescripcion.setText("");
/*  60 */     this.campoCoste.setText("");
/*  61 */     this.campopvr.setText("");
/*  62 */     this.campoPvp.setText("");
/*  63 */     this.campoUnidades.setText("");
/*  64 */     this.campoFechaVenta.setDate(null);
/*     */   }
/*     */ 
/*     */   private void cargarProducto() {
/*  68 */     long ref_temp = Long.parseLong(this.campoReferencia.getText());
/*  69 */     if (existeReferencia(ref_temp, true)) {
/*  70 */       this.campopvr.setEnabled(true);
/*  71 */       this.campoFechaVenta.setEnabled(true);
/*  72 */       this.jButton1.setEnabled(true);
/*  73 */       this.campoUnidades.setEnabled(false);
/*  74 */       this.botonAlta.setEnabled(false);
/*  75 */       this.campoFamilia.setText(this.ficha[0]);
/*  76 */       this.campoRefProv.setText(this.ficha[1]);
/*  77 */       this.campoDescripcion.setText(this.ficha[2]);
/*  78 */       this.campoFechaCompra.setDate(getDateFromString(this.ficha[4]));
/*  79 */       if (this.ficha[5].equals("")) {
/*  80 */         this.campoFechaVenta.setDate(null);
/*     */       }
/*     */       else {
/*  83 */         this.campoFechaVenta.setDate(getDateFromString(this.ficha[5]));
/*     */       }
/*  85 */       this.campoCoste.setText(this.ficha[6]);
/*  86 */       this.campoPvp.setText(this.ficha[7]);
/*  87 */       this.campopvr.setText(this.ficha[8]);
/*     */ 
/*  89 */       this.campoCodigoProveedor.setText(this.ficha[3]);
/*  90 */       setNombreProveedor();
/*  91 */       this.campoMargen.setText("");
/*  92 */       this.campoRefProv.requestFocus();
/*     */     }
/*     */     else {
/*  95 */       mostrarError("La referencia no existe");
/*  96 */       limpiarForm();
/*     */     }
/*     */   }
/*     */ 
/*     */   private Date getDateFromString(String fecha) {
/* 101 */     String year = fecha.substring(0, 4);
/* 102 */     String mounth = fecha.substring(5, 7);
/* 103 */     String day = fecha.substring(8);
/* 104 */     int yearI = Integer.parseInt(year);
/* 105 */     int mounthI = Integer.parseInt(mounth);
/* 106 */     int dayI = Integer.parseInt(day);
/* 107 */     GregorianCalendar g = new GregorianCalendar(yearI, mounthI - 1, dayI);
/* 108 */     return new Date(g.getTimeInMillis());
/*     */   }
/*     */ 
/*     */   private void altaProducto() {
/* 112 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 113 */     Date fecha = this.campoFechaCompra.getDate();
/* 114 */     String ref1 = this.campoCoste.getText();
/* 115 */     String ref2 = this.campoPvp.getText();
/* 116 */     if (fecha == null) {
/* 117 */       mostrarError("Error en fecha de compra");
/* 118 */     } else if (!esDoble(ref1)) {
/* 119 */       mostrarError("Error en coste");
/* 120 */     } else if (!esDoble(ref2)) {
/* 121 */       mostrarError("Error en pvp");
/* 122 */     } else if (this.campoNombreProveedor.getText().equals("")) {
/* 123 */       mostrarError("No hay proveedor");
/* 124 */       this.campoCodigoProveedor.requestFocus();
/*     */     } else {
/* 126 */       String ref = sdf.format(fecha);
/*     */ 
/* 128 */       long ref_temp = Long.parseLong(this.campoReferencia.getText());
/* 129 */       if (!existeReferencia(ref_temp, false)) {
/* 130 */         int x = Integer.parseInt(this.campoUnidades.getText());
/* 131 */         double c = new Double(this.campoCoste.getText()).doubleValue();
/* 132 */         double p = new Double(this.campoPvp.getText()).doubleValue();
/*     */         try {
/* 134 */           if (this.campoRefProv.getText().length() > 18) {
/* 135 */             this.campoRefProv.setText(this.campoRefProv.getText().substring(0, 18));
/*     */           }
/* 137 */           if (this.campoDescripcion.getText().length() > 40) {
/* 138 */             this.campoDescripcion.setText(this.campoDescripcion.getText().substring(0, 40));
/*     */           }
/* 140 */           for (int y = 0; y < x; y++) {
/* 141 */             this.con.getSentencia().executeUpdate("INSERT INTO productos (Referencia,RefProv,Descripcion,Proveedor,f_compra,coste,pvp)VALUES (" + ref_temp + ",'" + this.campoRefProv.getText() + "'," + "'" + this.campoDescripcion.getText() + "'," + Integer.parseInt(this.campoCodigoProveedor.getText()) + ",'" + sdf.format(fecha) + "'," + c + "," + p + ")");
/*     */ 
/* 148 */             ref_temp += 1L;
/*     */           }
/* 150 */           String a = this.campoReferencia.getText().substring(0, 2);
/*     */ 
/* 152 */           this.res = this.con.getRes("SELECT acumulado FROM familias WHERE (numero=" + a + ")");
/* 153 */           this.res.next();
/* 154 */           ref_temp = this.res.getLong(1) + x;
/* 155 */           this.con.getSentencia().executeUpdate("UPDATE familias SET acumulado=" + ref_temp + " WHERE (numero=" + a + ")");
/*     */ 
/* 157 */           ref_temp = Long.parseLong(this.campoReferencia.getText());
/* 158 */           String Mensaje = "";
/* 159 */           for (int y = 0; y < x; y++) {
/* 160 */             String refs = String.valueOf(ref_temp);
/* 161 */             String relleno = "                      ";
/* 162 */             if (refs.length() == 7) {
/* 163 */               refs = "0" + refs;
/*     */             }
/* 165 */             String linea = refs + relleno.substring(0, 20 - this.fn.format(p).length()) + this.fn.format(p) + "\n";
/* 166 */             Mensaje = Mensaje + linea;
/* 167 */             ref_temp += 1L;
/*     */           }
/* 169 */           mostrarError(Mensaje);
/* 170 */           limpiarForm();
/*     */         } catch (SQLException exc) {
/* 172 */           mostrarError("Altas:\nFallo SQL al intentar dar de alta:\n" + exc.getMessage());
/*     */         }
/*     */       } else {
/* 175 */         mostrarError("Altas:\nLa referencia ya existe");
/* 176 */         this.campoReferencia.requestFocus();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void modificarProducto() {
/* 182 */     long refTemp = Long.parseLong(this.campoReferencia.getText());
/* 183 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 184 */     if (existeReferencia(refTemp, false)) {
/* 185 */       String ref = sdf.format(this.campoFechaCompra.getDate());
/* 186 */       Date fechaVenta = this.campoFechaVenta.getDate();
/* 187 */       String ref0 = "1960-01-01";
/* 188 */       if (fechaVenta != null) {
/* 189 */         ref0 = sdf.format(fechaVenta);
/*     */       }
/* 191 */       String ref1 = this.campoCoste.getText();
/* 192 */       String ref2 = this.campoPvp.getText();
/* 193 */       String ref3 = this.campopvr.getText();
/* 194 */       if (ref3.equals("")) {
/* 195 */         ref3 = "0";
/*     */       }
/* 197 */       String ref4 = this.campoCodigoProveedor.getText();
/*     */ 
/* 199 */       if (!esDoble(ref1)) {
/* 200 */         mostrarError("Error en el coste");
/* 201 */         this.campoCoste.requestFocus();
/* 202 */       } else if (!esDoble(ref2)) {
/* 203 */         mostrarError("Error en pvp");
/* 204 */         this.campoPvp.requestFocus();
/* 205 */       } else if (!esDoble(ref3)) {
/* 206 */         mostrarError("Error en pvr");
/* 207 */         this.campopvr.requestFocus();
/* 208 */       } else if (this.campoNombreProveedor.getText().equals("")) {
/* 209 */         mostrarError("No hay proveedor");
/* 210 */         this.campoCodigoProveedor.requestFocus();
/*     */       }
/*     */       else {
/* 213 */         double c = new Double(ref1).doubleValue();
/* 214 */         double p = new Double(ref2).doubleValue();
/* 215 */         double pr = new Double(ref3).doubleValue();
/*     */         try {
/* 217 */           if (this.campoRefProv.getText().length() > 18) {
/* 218 */             this.campoRefProv.setText(this.campoRefProv.getText().substring(0, 18));
/*     */           }
/* 220 */           if (this.campoDescripcion.getText().length() > 40) {
/* 221 */             this.campoDescripcion.setText(this.campoDescripcion.getText().substring(0, 40));
/*     */           }
/* 223 */           this.con.getSentencia().executeUpdate("UPDATE productos SET RefProv='" + this.campoRefProv.getText() + "', Descripcion='" + this.campoDescripcion.getText() + "', Proveedor=" + Integer.parseInt(ref4) + ", f_compra='" + ref + "', f_venta='" + ref0 + "', coste=" + c + ",pvp=" + p + ", pvr=" + pr + " WHERE Referencia=" + refTemp);
/*     */         }
/*     */         catch (SQLException exc)
/*     */         {
/* 228 */           mostrarError("Actualizar datos.\nError en ejecución SQL:\n" + exc.getMessage());
/*     */         }
/*     */       }
/*     */     } else {
/* 232 */       mostrarError("La referencia parece no existir.\nEsta opción sólo es válida para referencias existentes.");
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean existeReferencia(long ref, boolean obtenerDatos) {
/*     */     try {
/* 238 */       this.res = this.con.getRes("SELECT * FROM productos WHERE referencia=" + ref);
/* 239 */       if (this.res.next()) {
/* 240 */         if (obtenerDatos)
/*     */         {
/* 242 */           this.ficha = new String[9];
/* 243 */           this.ficha[1] = this.res.getString(2);
/* 244 */           this.ficha[2] = this.res.getString(3);
/* 245 */           this.ficha[3] = this.res.getString(4);
/* 246 */           this.ficha[4] = this.res.getString(5);
/* 247 */           this.ficha[5] = this.res.getString(6);
/* 248 */           if (this.ficha[5].equals("1960-01-01")) {
/* 249 */             this.ficha[5] = "";
/*     */           }
/* 251 */           this.ficha[6] = this.fn.format(this.res.getDouble(7));
/* 252 */           this.ficha[7] = this.fn.format(this.res.getDouble(8));
/* 253 */           this.ficha[8] = this.fn.format(this.res.getDouble(9));
/* 254 */           if (this.ficha[8].equals("0")) {
/* 255 */             this.ficha[8] = "";
/*     */           }
/* 257 */           String temp = Long.toString(ref);
/* 258 */           if (temp.length() == 7) {
/* 259 */             temp = "0" + temp;
/*     */           }
/* 261 */           temp = temp.substring(0, 2);
/* 262 */           this.res = this.con.getRes("SELECT nombre FROM familias WHERE numero=" + Integer.parseInt(temp));
/* 263 */           if (this.res.next()) {
/* 264 */             this.ficha[0] = this.res.getString(1);
/*     */           }
/*     */         }
/* 267 */         return true;
/*     */       }
/*     */     }
/*     */     catch (SQLException exc) {
/* 271 */       exc.printStackTrace();
/* 272 */       return false;
/*     */     }
/* 274 */     return false;
/*     */   }
/*     */ 
/*     */   private void setNewReferencia() {
/* 278 */     String ref = this.campoReferencia.getText();
/* 279 */     if (esEntero(ref)) {
/* 280 */       this.campoFechaVenta.setDate(null);
/* 281 */       this.campopvr.setText("");
/* 282 */       ref = construirReferencia(ref);
/* 283 */       if (!ref.equals("error")) {
/* 284 */         this.campopvr.setEnabled(false);
/* 285 */         this.campoFechaVenta.setEnabled(false);
/* 286 */         this.jButton1.setEnabled(false);
/* 287 */         this.campoUnidades.setEnabled(true);
/* 288 */         this.botonAlta.setEnabled(true);
/*     */ 
/* 291 */         this.campoUnidades.setText("1");
/* 292 */         this.campoCodigoProveedor.requestFocus();
/* 293 */         this.campoReferencia.setText(ref);
/*     */       } else {
/* 295 */         this.campoReferencia.setText("");
/* 296 */         this.campoReferencia.requestFocus();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private String construirReferencia(String ref) {
/*     */     try {
/* 303 */       int fam = Integer.parseInt(ref);
/* 304 */       this.res = this.con.getRes("SELECT * FROM familias WHERE numero=" + fam);
/* 305 */       if (!this.res.next()) {
/* 306 */         mostrarError("La familia no existe");
/* 307 */         return "error";
/*     */       }
/* 309 */       long refer = fam * 1000000 + this.res.getLong(3);
/* 310 */       this.campoFamilia.setText(this.res.getString(2));
/* 311 */       if (String.valueOf(refer).length() == 7)
/* 312 */         ref = "0" + String.valueOf(refer);
/*     */       else
/* 314 */         ref = String.valueOf(refer);
/*     */     }
/*     */     catch (SQLException exc) {
/* 317 */       mostrarError("Construir referencia\nProblema en la conexión:\n" + exc.getMessage());
/* 318 */       return "error";
/*     */     }
/* 320 */     return ref;
/*     */   }
/*     */ 
/*     */   private void setNombreProveedor() {
/* 324 */     String codProv = this.campoCodigoProveedor.getText();
/* 325 */     boolean correcto = false;
/* 326 */     if (codProv.length() == 3) {
/*     */       try {
/* 328 */         int cP = Integer.parseInt(codProv);
/* 329 */         this.res = this.con.getRes("SELECT nombre FROM proveedores WHERE numero=" + cP);
/* 330 */         if (this.res.next()) {
/* 331 */           this.campoNombreProveedor.setText(this.res.getString(1));
/* 332 */           correcto = true;
/*     */         }
/*     */       } catch (SQLException exc) {
/*     */       } catch (NumberFormatException exc) {
/*     */       }
/* 337 */       if (!correcto)
/* 338 */         this.campoNombreProveedor.setText("");
/*     */     }
/*     */   }
/*     */ 
/*     */   private void setRefAnteriorSiguiente(boolean atras)
/*     */   {
/* 344 */     String ref = this.campoReferencia.getText();
/*     */ 
/* 346 */     if (atras) {
/* 347 */       long ref_temp = Long.parseLong(ref);
/* 348 */       ref_temp -= 1L;
/* 349 */       ref = Long.toString(ref_temp);
/* 350 */       if (ref.length() == 7) ref = "0" + ref; 
/*     */     }
/*     */     else
/*     */     {
/* 353 */       long ref_temp = Long.parseLong(ref);
/* 354 */       ref_temp += 1L;
/* 355 */       ref = Long.toString(ref_temp);
/* 356 */       if (ref.length() == 7) ref = "0" + ref;
/*     */     }
/* 358 */     this.campoReferencia.setText(ref);
/* 359 */     cargarProducto();
/*     */   }
/*     */ 
/*     */   private Double doubleTwoDecimals(Double number) {
/* 363 */     if (number != null) {
/* 364 */       BigDecimal dec = new BigDecimal(number.doubleValue());
/* 365 */       return Double.valueOf(dec.setScale(2, 4).doubleValue());
/*     */     }
/* 367 */     return Double.valueOf(-1.0D);
/*     */   }
/*     */ 
/*     */   private boolean esDoble(String number) {
/* 371 */     boolean es = false;
/*     */     try {
/* 373 */       double d = Double.parseDouble(number);
/* 374 */       es = true;
/*     */     }
/*     */     catch (NumberFormatException e) {
/*     */     }
/* 378 */     return es;
/*     */   }
/*     */ 
/*     */   private boolean esEntero(String number) {
/* 382 */     boolean es = false;
/*     */     try {
/* 384 */       int d = Integer.parseInt(number);
/* 385 */       es = true;
/*     */     }
/*     */     catch (NumberFormatException e) {
/*     */     }
/* 389 */     return es;
/*     */   }
/*     */ 
/*     */   private void mostrarError(String error) {
/* 393 */     JOptionPane.showMessageDialog(getContentPane(), error, "Error", 0);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 405 */     this.jPanel1 = new JPanel();
/* 406 */     this.jPanel2 = new JPanel();
/* 407 */     this.jLabel1 = new JLabel();
/* 408 */     this.campoReferencia = new JTextField();
/* 409 */     this.jLabel2 = new JLabel();
/* 410 */     this.campoFamilia = new JLabel();
/* 411 */     this.jLabel3 = new JLabel();
/* 412 */     this.campoCodigoProveedor = new JTextField();
/* 413 */     this.campoNombreProveedor = new JLabel();
/* 414 */     this.jLabel4 = new JLabel();
/* 415 */     this.campoRefProv = new JTextField();
/* 416 */     this.jLabel5 = new JLabel();
/* 417 */     this.campoDescripcion = new JTextField();
/* 418 */     this.jPanel3 = new JPanel();
/* 419 */     this.jLabel6 = new JLabel();
/* 420 */     this.jLabel7 = new JLabel();
/* 421 */     this.campoFechaCompra = new ICalendarTextField();
/* 422 */     this.jLabel8 = new JLabel();
/* 423 */     this.campoFechaVenta = new ICalendarTextField();
/* 424 */     this.campoCoste = new JTextField();
/* 425 */     this.campopvr = new JTextField();
/* 426 */     this.jLabel9 = new JLabel();
/* 427 */     this.jPanel4 = new JPanel();
/* 428 */     this.jLabel10 = new JLabel();
/* 429 */     this.campoMargen = new JTextField();
/* 430 */     this.jLabel11 = new JLabel();
/* 431 */     this.campoPvp = new JTextField();
/* 432 */     this.jPanel5 = new JPanel();
/* 433 */     this.botonAnterior = new JButton();
/* 434 */     this.botonSiguiente = new JButton();
/* 435 */     this.botonLimpiar = new JButton();
/* 436 */     this.jButton1 = new JButton();
/* 437 */     this.jLabel12 = new JLabel();
/* 438 */     this.jLabel13 = new JLabel();
/* 439 */     this.campoUnidades = new JTextField();
/* 440 */     this.botonAlta = new JButton();
/*     */ 
/* 442 */     setClosable(true);
/* 443 */     setIconifiable(true);
/* 444 */     setMaximizable(true);
/* 445 */     setResizable(true);
/* 446 */     setTitle("Almacén");
/* 447 */     addInternalFrameListener(new InternalFrameListener() {
/*     */       public void internalFrameOpened(InternalFrameEvent evt) {
/*     */       }
/*     */       public void internalFrameClosing(InternalFrameEvent evt) {
/*     */       }
/*     */       public void internalFrameClosed(InternalFrameEvent evt) {
/* 453 */         OldAlmacenJInternalFrame.this.formInternalFrameClosed(evt);
/*     */       }
/*     */ 
/*     */       public void internalFrameIconified(InternalFrameEvent evt)
/*     */       {
/*     */       }
/*     */ 
/*     */       public void internalFrameDeiconified(InternalFrameEvent evt)
/*     */       {
/*     */       }
/*     */ 
/*     */       public void internalFrameActivated(InternalFrameEvent evt)
/*     */       {
/*     */       }
/*     */ 
/*     */       public void internalFrameDeactivated(InternalFrameEvent evt)
/*     */       {
/*     */       }
/*     */     });
/* 465 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 467 */     this.jLabel1.setText("Referencia");
/*     */ 
/* 469 */     this.campoReferencia.setDocument(new DocDigitos());
/* 470 */     this.campoReferencia.addFocusListener(new FocusAdapter() {
/*     */       public void focusLost(FocusEvent evt) {
/* 472 */         OldAlmacenJInternalFrame.this.campoReferenciaFocusLost(evt);
/*     */       }
/*     */     });
/* 476 */     this.jLabel2.setText("Familia");
/*     */ 
/* 478 */     this.campoFamilia.setText("familia");
/*     */ 
/* 480 */     this.jLabel3.setText("Proveedor");
/*     */ 
/* 482 */     this.campoCodigoProveedor.setDocument(new DocDigitos());
/* 483 */     this.campoCodigoProveedor.addFocusListener(new FocusAdapter() {
/*     */       public void focusLost(FocusEvent evt) {
/* 485 */         OldAlmacenJInternalFrame.this.campoCodigoProveedorFocusLost(evt);
/*     */       }
/*     */     });
/* 489 */     this.campoNombreProveedor.setText("proveedor");
/*     */ 
/* 491 */     this.jLabel4.setText("Referencia del proveedor");
/*     */ 
/* 493 */     this.campoRefProv.setDocument(new DocDiezyOchoCarac());
/*     */ 
/* 495 */     this.jLabel5.setText("Descripción");
/*     */ 
/* 497 */     this.campoDescripcion.setDocument(new DocCuarentaCarac());
/*     */ 
/* 499 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 500 */     this.jPanel2.setLayout(jPanel2Layout);
/* 501 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campoReferencia, -2, 121, -2)).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.campoCodigoProveedor, -2, 79, -2))).addGap(18, 18, 18).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.campoFamilia, -2, 178, -2)).addComponent(this.campoNombreProveedor, -1, -1, 32767))).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jLabel4).addGap(47, 47, 47).addComponent(this.campoRefProv, -2, 154, -2)).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campoDescripcion))).addContainerGap(31, 32767)));
/*     */ 
/* 533 */     jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.campoReferencia, -2, -1, -2).addComponent(this.jLabel2).addComponent(this.campoFamilia)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.campoCodigoProveedor, -2, -1, -2).addComponent(this.campoNombreProveedor)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.campoRefProv, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel5).addComponent(this.campoDescripcion, -2, -1, -2)).addContainerGap(-1, 32767)));
/*     */ 
/* 558 */     this.jLabel6.setText("Compra");
/*     */ 
/* 560 */     this.jLabel7.setText("Fecha");
/*     */ 
/* 562 */     this.jLabel8.setText("Venta");
/*     */ 
/* 564 */     this.campoCoste.setDocument(new DocNumPositivos());
/*     */ 
/* 566 */     this.campopvr.setDocument(new DocNumPositivos());
/*     */ 
/* 568 */     this.jLabel9.setText("Importe");
/*     */ 
/* 570 */     this.jPanel4.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 572 */     this.jLabel10.setText("Margen");
/*     */ 
/* 574 */     this.campoMargen.setDocument(new DocNumPositivos());
/* 575 */     this.campoMargen.addFocusListener(new FocusAdapter() {
/*     */       public void focusLost(FocusEvent evt) {
/* 577 */         OldAlmacenJInternalFrame.this.campoMargenFocusLost(evt);
/*     */       }
/*     */     });
/* 581 */     this.jLabel11.setText("P.V.P.");
/*     */ 
/* 583 */     this.campoPvp.setDocument(new DocNumPositivos());
/*     */ 
/* 585 */     GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
/* 586 */     this.jPanel4.setLayout(jPanel4Layout);
/* 587 */     jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jPanel4Layout.createSequentialGroup().addComponent(this.jLabel10).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campoMargen, -2, 61, -2)).addComponent(this.jLabel11).addComponent(this.campoPvp)).addContainerGap(-1, 32767)));
/*     */ 
/* 600 */     jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel10).addComponent(this.campoMargen, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel11).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campoPvp, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 614 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 615 */     this.jPanel3.setLayout(jPanel3Layout);
/* 616 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campoFechaCompra, -2, 138, -2)).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.jLabel8).addGap(18, 18, 18).addComponent(this.campoFechaVenta, -1, -1, 32767))).addGap(18, 18, 18).addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.campopvr).addComponent(this.campoCoste, -1, 118, 32767))).addGroup(jPanel3Layout.createSequentialGroup().addGap(96, 96, 96).addComponent(this.jLabel7).addGap(111, 111, 111).addComponent(this.jLabel9))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jPanel4, -2, -1, -2).addContainerGap(7, 32767)));
/*     */ 
/* 644 */     jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel4, -1, -1, 32767).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel7).addComponent(this.jLabel9)).addGap(12, 12, 12).addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.campoCoste, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campopvr, -2, -1, -2)).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.campoFechaCompra, GroupLayout.Alignment.TRAILING, -2, -1, -2).addComponent(this.jLabel6, GroupLayout.Alignment.TRAILING)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jLabel8).addComponent(this.campoFechaVenta, -2, -1, -2)))))).addContainerGap()));
/*     */ 
/* 671 */     this.botonAnterior.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/1leftarrow.png")));
/* 672 */     this.botonAnterior.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 674 */         OldAlmacenJInternalFrame.this.botonAnteriorActionPerformed(evt);
/*     */       }
/*     */     });
/* 678 */     this.botonSiguiente.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/1rightarrow.png")));
/* 679 */     this.botonSiguiente.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 681 */         OldAlmacenJInternalFrame.this.botonSiguienteActionPerformed(evt);
/*     */       }
/*     */     });
/* 685 */     this.botonLimpiar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 686 */     this.botonLimpiar.setText("Limpiar");
/* 687 */     this.botonLimpiar.setHorizontalTextPosition(2);
/* 688 */     this.botonLimpiar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 690 */         OldAlmacenJInternalFrame.this.botonLimpiarActionPerformed(evt);
/*     */       }
/*     */     });
/* 694 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit.png")));
/* 695 */     this.jButton1.setText("Modificar");
/* 696 */     this.jButton1.setHorizontalTextPosition(2);
/* 697 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 699 */         OldAlmacenJInternalFrame.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 703 */     this.jLabel12.setText("Alta");
/*     */ 
/* 705 */     this.jLabel13.setText("Unidades");
/*     */ 
/* 707 */     this.campoUnidades.setDocument(new DocDigitos());
/*     */ 
/* 709 */     this.botonAlta.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/* 710 */     this.botonAlta.setText("Alta");
/* 711 */     this.botonAlta.setHorizontalTextPosition(2);
/* 712 */     this.botonAlta.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 714 */         OldAlmacenJInternalFrame.this.botonAltaActionPerformed(evt);
/*     */       }
/*     */     });
/* 718 */     GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
/* 719 */     this.jPanel5.setLayout(jPanel5Layout);
/* 720 */     jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addComponent(this.botonAnterior, -2, 35, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.botonSiguiente, -2, 35, -2).addGap(37, 37, 37).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.botonLimpiar).addComponent(this.jButton1)).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addGap(91, 91, 91).addComponent(this.jLabel13).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.campoUnidades, -2, 84, -2)).addGroup(jPanel5Layout.createSequentialGroup().addGap(60, 60, 60).addComponent(this.jLabel12).addGap(51, 51, 51).addComponent(this.botonAlta))).addContainerGap(39, 32767)));
/*     */ 
/* 744 */     jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.botonLimpiar).addComponent(this.jLabel13).addComponent(this.campoUnidades, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.botonAlta))).addGroup(jPanel5Layout.createSequentialGroup().addGap(19, 19, 19).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.botonAnterior, -2, 35, -2).addComponent(this.botonSiguiente, -2, 35, -2)))).addContainerGap(7, 32767)).addGroup(GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup().addContainerGap(31, 32767).addComponent(this.jLabel12).addGap(29, 29, 29)));
/*     */ 
/* 770 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 771 */     this.jPanel1.setLayout(jPanel1Layout);
/* 772 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jPanel3, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jPanel5, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jPanel2, GroupLayout.Alignment.LEADING, -2, -1, -2)).addContainerGap(-1, 32767)));
/*     */ 
/* 782 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel3, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel5, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 794 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 795 */     getContentPane().setLayout(layout);
/* 796 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 803 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 811 */     pack();
/*     */   }
/*     */ 
/*     */   private void formInternalFrameClosed(InternalFrameEvent evt) {
/* 815 */     this.con.cierraConexion();
/*     */   }
/*     */ 
/*     */   private void campoReferenciaFocusLost(FocusEvent evt) {
/* 819 */     String ref = this.campoReferencia.getText();
/* 820 */     if (ref.length() == 2) {
/* 821 */       setNewReferencia();
/*     */     }
/* 823 */     else if (ref.length() == 8)
/* 824 */       cargarProducto();
/*     */   }
/*     */ 
/*     */   private void campoCodigoProveedorFocusLost(FocusEvent evt)
/*     */   {
/* 829 */     setNombreProveedor();
/*     */   }
/*     */ 
/*     */   private void campoMargenFocusLost(FocusEvent evt) {
/* 833 */     String margen = this.campoMargen.getText();
/* 834 */     String coste = this.campoCoste.getText();
/* 835 */     if (coste.equals("")) {
/* 836 */       coste = "0";
/*     */     }
/* 838 */     if (!margen.equals("")) {
/* 839 */       double margenD = Double.parseDouble(margen);
/* 840 */       double costeD = Double.parseDouble(coste);
/* 841 */       double pvpD = costeD * (1.0D + margenD / 100.0D);
/* 842 */       pvpD = doubleTwoDecimals(Double.valueOf(pvpD)).doubleValue();
/* 843 */       this.campoPvp.setText(Double.toString(pvpD));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void botonAnteriorActionPerformed(ActionEvent evt) {
/* 848 */     setRefAnteriorSiguiente(true);
/*     */   }
/*     */ 
/*     */   private void botonSiguienteActionPerformed(ActionEvent evt) {
/* 852 */     setRefAnteriorSiguiente(false);
/*     */   }
/*     */ 
/*     */   private void botonLimpiarActionPerformed(ActionEvent evt) {
/* 856 */     limpiarForm();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 860 */     modificarProducto();
/*     */   }
/*     */ 
/*     */   private void botonAltaActionPerformed(ActionEvent evt) {
/* 864 */     altaProducto();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.OldAlmacenJInternalFrame
 * JD-Core Version:    0.6.2
 */