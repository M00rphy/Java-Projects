/*     */ package contaes.asientosModelo;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliar.DocNumPositivos;
/*     */ import contaes.auxiliar.DocTreintaCarac;
/*     */ import contaes.auxiliar.DocVeinteCarac;
/*     */ import contaes.auxiliarTablas.CentroColorRenderer;
/*     */ import contaes.auxiliarTablas.ImporteColorRenderer;
/*     */ import contaes.auxiliarTablas.ToolTipNombreCuentaColor;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.dialogosAuxiliares.MostrarConceptos;
/*     */ import contaes.dialogosFunciones.Calculadora;
/*     */ import contaes.manejoDatos.ManejoApuntes;
/*     */ import contaes.manejoDatos.ManejoAsientos;
/*     */ import contaes.manejoDatos.ManejoAsientosModelo;
/*     */ import contaes.manejoDatos.TipoApunteModelo;
/*     */ import contaes.manejoDatos.TipoAsientoModelo;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.io.PrintStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.LinkedList;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ public class Utilizar extends JDialog
/*     */   implements KeyListener
/*     */ {
/*  68 */   private JPanel jContentPane = null;
/*  69 */   private JLabel fechaE = null;
/*  70 */   private JLabel conceptoE = null;
/*  71 */   private JLabel importeE = null;
/*  72 */   private ICalendarTextField cFecha = null;
/*  73 */   private JTextField cConcepto = null;
/*  74 */   private JTextField cImporte = null;
/*  75 */   private JLabel docuE = null;
/*  76 */   private JTextField cDocu = null;
/*  77 */   private JLabel marcaE = null;
/*  78 */   private JTextField cMarca = null;
/*  79 */   private JButton bCrear = null;
/*  80 */   private JButton bCancelar = null;
/*  81 */   private JScrollPane scrollPane1 = null;
/*  82 */   private JTable tabla = null;
/*     */   private int id;
/*     */   private Object[][] datos;
/*     */   private double[] porcentaje;
/*  86 */   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
/*     */ 
/*     */   public Utilizar(Frame owner, int i)
/*     */   {
/*  92 */     super(owner, false);
/*  93 */     this.id = i;
/*     */     try {
/*  95 */       setDefaultCloseOperation(2);
/*  96 */       initialize();
/*     */ 
/*  99 */       this.cFecha.requestFocus();
/*     */     } catch (Exception exception) {
/* 101 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/* 111 */     setSize(410, 360);
/* 112 */     setTitle(Mensajes.getString("astoModel"));
/* 113 */     setContentPane(getJContentPane());
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/* 122 */     if (this.jContentPane == null) {
/* 123 */       GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
/* 124 */       gridBagConstraints33.fill = 2;
/* 125 */       gridBagConstraints33.gridy = 2;
/* 126 */       gridBagConstraints33.weightx = 1.0D;
/* 127 */       gridBagConstraints33.insets = new Insets(5, 5, 5, 60);
/* 128 */       gridBagConstraints33.gridx = 3;
/* 129 */       GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
/* 130 */       gridBagConstraints32.gridx = 2;
/* 131 */       gridBagConstraints32.anchor = 10;
/* 132 */       gridBagConstraints32.insets = new Insets(5, 5, 5, 5);
/* 133 */       gridBagConstraints32.gridy = 2;
/* 134 */       this.marcaE = new JLabel();
/* 135 */       this.marcaE.setText(Mensajes.getString("marca"));
/* 136 */       GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
/* 137 */       gridBagConstraints31.fill = 2;
/* 138 */       gridBagConstraints31.gridy = 3;
/* 139 */       gridBagConstraints31.weightx = 1.0D;
/* 140 */       gridBagConstraints31.weighty = 1.0D;
/* 141 */       gridBagConstraints31.insets = new Insets(5, 5, 5, 5);
/* 142 */       gridBagConstraints31.gridwidth = 4;
/* 143 */       gridBagConstraints31.gridx = 0;
/* 144 */       gridBagConstraints31.ipady = 100;
/* 145 */       GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
/* 146 */       gridBagConstraints9.gridx = 2;
/* 147 */       gridBagConstraints9.insets = new Insets(5, 5, 15, 0);
/* 148 */       gridBagConstraints9.gridy = 4;
/* 149 */       GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
/* 150 */       gridBagConstraints8.gridx = 1;
/* 151 */       gridBagConstraints8.insets = new Insets(5, 5, 15, 5);
/* 152 */       gridBagConstraints8.anchor = 17;
/* 153 */       gridBagConstraints8.gridy = 4;
/* 154 */       GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
/* 155 */       gridBagConstraints6.fill = 2;
/* 156 */       gridBagConstraints6.gridy = 0;
/* 157 */       gridBagConstraints6.weightx = 1.0D;
/* 158 */       gridBagConstraints6.insets = new Insets(15, 5, 5, 15);
/* 159 */       gridBagConstraints6.ipadx = 60;
/* 160 */       gridBagConstraints6.gridx = 3;
/* 161 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/* 162 */       gridBagConstraints5.gridx = 2;
/* 163 */       gridBagConstraints5.insets = new Insets(15, 5, 5, 5);
/* 164 */       gridBagConstraints5.gridy = 0;
/* 165 */       this.docuE = new JLabel();
/* 166 */       this.docuE.setText(Mensajes.getString("documento"));
/* 167 */       GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
/* 168 */       gridBagConstraints4.fill = 2;
/* 169 */       gridBagConstraints4.gridy = 2;
/* 170 */       gridBagConstraints4.weightx = 1.0D;
/* 171 */       gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
/* 172 */       gridBagConstraints4.gridx = 1;
/* 173 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/* 174 */       gridBagConstraints3.fill = 2;
/* 175 */       gridBagConstraints3.gridy = 1;
/* 176 */       gridBagConstraints3.weightx = 1.0D;
/* 177 */       gridBagConstraints3.insets = new Insets(5, 5, 5, 5);
/* 178 */       gridBagConstraints3.gridwidth = 2;
/* 179 */       gridBagConstraints3.gridx = 1;
/* 180 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 181 */       gridBagConstraints2.fill = 2;
/* 182 */       gridBagConstraints2.gridy = 0;
/* 183 */       gridBagConstraints2.weightx = 1.0D;
/* 184 */       gridBagConstraints2.insets = new Insets(15, 5, 5, 5);
/* 185 */       gridBagConstraints2.gridx = 1;
/* 186 */       gridBagConstraints2.ipady = 21;
/* 187 */       GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
/* 188 */       gridBagConstraints11.gridx = 0;
/* 189 */       gridBagConstraints11.anchor = 17;
/* 190 */       gridBagConstraints11.insets = new Insets(5, 15, 5, 5);
/* 191 */       gridBagConstraints11.gridy = 2;
/* 192 */       this.importeE = new JLabel();
/* 193 */       this.importeE.setText(Mensajes.getString("importe"));
/* 194 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 195 */       gridBagConstraints1.gridx = 0;
/* 196 */       gridBagConstraints1.anchor = 17;
/* 197 */       gridBagConstraints1.insets = new Insets(5, 15, 5, 5);
/* 198 */       gridBagConstraints1.gridy = 1;
/* 199 */       this.conceptoE = new JLabel();
/* 200 */       this.conceptoE.setText(Mensajes.getString("concepto"));
/* 201 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 202 */       gridBagConstraints.gridx = 0;
/* 203 */       gridBagConstraints.anchor = 17;
/* 204 */       gridBagConstraints.insets = new Insets(15, 15, 5, 5);
/* 205 */       gridBagConstraints.gridy = 0;
/* 206 */       this.fechaE = new JLabel();
/* 207 */       this.fechaE.setText(Mensajes.getString("fecha"));
/* 208 */       this.jContentPane = new JPanel();
/* 209 */       this.jContentPane.setLayout(new GridBagLayout());
/* 210 */       this.jContentPane.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createEtchedBorder(1)));
/*     */ 
/* 212 */       this.jContentPane.add(this.fechaE, gridBagConstraints);
/* 213 */       this.jContentPane.add(this.conceptoE, gridBagConstraints1);
/* 214 */       this.jContentPane.add(this.importeE, gridBagConstraints11);
/* 215 */       this.jContentPane.add(this.marcaE, gridBagConstraints32);
/* 216 */       this.jContentPane.add(getCDocu(), gridBagConstraints6);
/* 217 */       this.jContentPane.add(getCFecha(), gridBagConstraints2);
/* 218 */       this.jContentPane.add(getCConcepto(), gridBagConstraints3);
/* 219 */       this.jContentPane.add(getCImporte(), gridBagConstraints4);
/* 220 */       this.jContentPane.add(getCMarca(), gridBagConstraints33);
/* 221 */       this.jContentPane.add(this.docuE, gridBagConstraints5);
/* 222 */       this.jContentPane.add(getBCrear(), gridBagConstraints8);
/* 223 */       this.jContentPane.add(getBCancelar(), gridBagConstraints9);
/* 224 */       this.jContentPane.add(getScrollPane1(), gridBagConstraints31);
/*     */     }
/* 226 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private ICalendarTextField getCFecha()
/*     */   {
/* 235 */     if (this.cFecha == null) {
/* 236 */       this.cFecha = new ICalendarTextField();
/* 237 */       this.cFecha.setToolTipText(Mensajes.getString("formatoFecha"));
/* 238 */       this.cFecha.setComponente(this.cDocu);
/*     */     }
/* 240 */     return this.cFecha;
/*     */   }
/*     */ 
/*     */   private JTextField getCConcepto()
/*     */   {
/* 249 */     if (this.cConcepto == null) {
/* 250 */       this.cConcepto = new JTextField();
/* 251 */       this.cConcepto.setDocument(new DocTreintaCarac());
/* 252 */       this.cConcepto.addKeyListener(this);
/*     */     }
/* 254 */     return this.cConcepto;
/*     */   }
/*     */ 
/*     */   private JTextField getCImporte()
/*     */   {
/* 263 */     if (this.cImporte == null) {
/* 264 */       this.cImporte = new JTextField();
/* 265 */       this.cImporte.setDocument(new DocNumPositivos());
/* 266 */       this.cImporte.addKeyListener(this);
/* 267 */       this.cImporte.addFocusListener(new FocusAdapter()
/*     */       {
/*     */         public void focusLost(FocusEvent e)
/*     */         {
/* 271 */           if (!Utilizar.this.cImporte.getText().equals("")) {
/*     */             try {
/* 273 */               double importe = Double.parseDouble(Utilizar.this.cImporte.getText());
/* 274 */               for (int x = 0; x < Utilizar.this.tabla.getRowCount(); x++)
/* 275 */                 Utilizar.this.tabla.setValueAt(new Double(importe / 100.0D * Utilizar.this.porcentaje[x]), x, 2);
/*     */             }
/*     */             catch (NumberFormatException exc) {
/* 278 */               System.out.println(exc.getMessage());
/*     */             }
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 284 */     return this.cImporte;
/*     */   }
/*     */ 
/*     */   private JTextField getCDocu()
/*     */   {
/* 293 */     if (this.cDocu == null) {
/* 294 */       this.cDocu = new JTextField();
/* 295 */       this.cDocu.addKeyListener(this);
/* 296 */       this.cDocu.setDocument(new DocVeinteCarac());
/*     */     }
/* 298 */     return this.cDocu;
/*     */   }
/*     */ 
/*     */   private JTextField getCMarca()
/*     */   {
/* 307 */     if (this.cMarca == null) {
/* 308 */       this.cMarca = new JTextField();
/* 309 */       this.cMarca.setToolTipText(Mensajes.getString("marcaTT"));
/* 310 */       this.cMarca.addKeyListener(this);
/* 311 */       this.cMarca.setHorizontalAlignment(0);
/*     */     }
/* 313 */     return this.cMarca;
/*     */   }
/*     */ 
/*     */   private JButton getBCrear()
/*     */   {
/* 322 */     if (this.bCrear == null) {
/* 323 */       this.bCrear = new JButton();
/* 324 */       this.bCrear.setText(Mensajes.getString("crear"));
/* 325 */       this.bCrear.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/*     */ 
/* 327 */       this.bCrear.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 330 */           if (Utilizar.this.crear_asiento()) {
/* 331 */             Utilizar.this.dispose();
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 336 */     return this.bCrear;
/*     */   }
/*     */ 
/*     */   private JButton getBCancelar()
/*     */   {
/* 345 */     if (this.bCancelar == null) {
/* 346 */       this.bCancelar = new JButton();
/* 347 */       this.bCancelar.setText(Mensajes.getString("cancelar"));
/* 348 */       this.bCancelar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 349 */       this.bCancelar.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 352 */           Utilizar.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 356 */     return this.bCancelar;
/*     */   }
/*     */ 
/*     */   private JScrollPane getScrollPane1()
/*     */   {
/* 365 */     if (this.scrollPane1 == null) {
/* 366 */       this.scrollPane1 = new JScrollPane();
/* 367 */       this.scrollPane1.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
/*     */ 
/* 369 */       this.scrollPane1.getViewport().add(getTabla());
/*     */     }
/* 371 */     return this.scrollPane1;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 380 */     if (this.tabla == null) {
/* 381 */       String[] columnas = { Mensajes.getString("cuenta"), Mensajes.getString("debeHaberA"), Mensajes.getString("importe") };
/* 382 */       fijaDatos();
/* 383 */       this.tabla = new JTable(this.datos, columnas);
/* 384 */       TableColumn columna = null;
/* 385 */       columna = this.tabla.getColumnModel().getColumn(0);
/* 386 */       columna.setPreferredWidth(100);
/* 387 */       columna.setCellRenderer(new ToolTipNombreCuentaColor());
/* 388 */       columna = this.tabla.getColumnModel().getColumn(1);
/* 389 */       columna.setPreferredWidth(10);
/* 390 */       columna.setCellRenderer(new CentroColorRenderer());
/* 391 */       columna = this.tabla.getColumnModel().getColumn(2);
/* 392 */       columna.setPreferredWidth(100);
/* 393 */       columna.setCellRenderer(new ImporteColorRenderer());
/*     */     }
/* 395 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   private void fijaDatos()
/*     */   {
/* 403 */     ManejoAsientosModelo manejoAM = new ManejoAsientosModelo(Inicio.getCGeneral());
/* 404 */     this.cConcepto.setText(manejoAM.datosAsiento(this.id).getConcepto());
/* 405 */     this.cMarca.setText(manejoAM.datosAsiento(this.id).getMarca());
/* 406 */     LinkedList listaAp = new LinkedList();
/* 407 */     listaAp.addAll(manejoAM.listadoApuntes(this.id));
/*     */ 
/* 409 */     if (listaAp.size() > 0) {
/* 410 */       this.datos = new Object[listaAp.size()][3];
/* 411 */       this.porcentaje = new double[listaAp.size()];
/* 412 */       for (int x = 0; x < listaAp.size(); x++) {
/* 413 */         TipoApunteModelo apunte = manejoAM.datosApunte(((Integer)listaAp.get(x)).intValue());
/* 414 */         this.datos[x][0] = new Integer(apunte.getCuenta());
/* 415 */         this.datos[x][1] = apunte.getCA();
/* 416 */         this.datos[x][2] = new Double(0.0D);
/* 417 */         this.porcentaje[x] = apunte.getImporte();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean crear_asiento()
/*     */   {
/* 427 */     Date fecha_n = this.cFecha.getDate();
/* 428 */     String fecha_bd = this.sdf.format(fecha_n);
/* 429 */     if (!fecha_bd.substring(0, 4).equals(Inicio.p.getEjercicio())) {
/* 430 */       JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("ejercicioNoCorrecto"));
/* 431 */       return false;
/*     */     }
/* 433 */     String marca1 = "";
/* 434 */     if (this.cMarca.getText().length() > 0)
/* 435 */       marca1 = this.cMarca.getText().substring(0, 1).toUpperCase();
/*     */     else {
/* 437 */       marca1 = this.cMarca.getText().toUpperCase();
/*     */     }
/* 439 */     ManejoAsientos asientoM = new ManejoAsientos(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 440 */     int idAsiento = asientoM.crear(asientoM.nuevoNumero(), fecha_bd, this.cDocu.getText(), marca1);
/* 441 */     if (idAsiento != -1) {
/* 442 */       ManejoApuntes apunteM = new ManejoApuntes(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 443 */       for (int x = 0; x < this.tabla.getRowCount(); x++) {
/* 444 */         apunteM.crear(idAsiento, Integer.parseInt(this.tabla.getValueAt(x, 0).toString()), this.cConcepto.getText(), this.tabla.getValueAt(x, 1).toString(), Double.parseDouble(this.tabla.getValueAt(x, 2).toString()));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 453 */     Inicio.frame.renovarTabla(0);
/* 454 */     return true;
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent e) {
/* 458 */     int tecla = e.getKeyCode();
/* 459 */     Object campo = e.getSource();
/* 460 */     if (e.isAltDown()) {
/* 461 */       if (tecla == 67) {
/* 462 */         if (campo == this.cConcepto) {
/* 463 */           MostrarConceptos dlg2 = new MostrarConceptos(this, Mensajes.getString("conceptos"), true);
/* 464 */           Dimension dlgSize = dlg2.getPreferredSize();
/* 465 */           Dimension frmSize = getSize();
/* 466 */           Point loc = getLocation();
/* 467 */           dlg2.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*     */ 
/* 469 */           dlg2.setVisible(true);
/* 470 */           if (!dlg2.Seleccion().equals(""))
/* 471 */             this.cConcepto.setText(dlg2.Seleccion());
/*     */         }
/* 473 */         else if (campo == this.cImporte) {
/* 474 */           Inicio.calculadora.colocaOrigen(this.cImporte);
/* 475 */           Inicio.calculadora.setVisible(true);
/*     */         }
/* 477 */       } else if ((tecla == 80) && (campo == this.cImporte)) {
/* 478 */         this.cImporte.setText(Inicio.calculadora.getResultado());
/*     */       }
/*     */     }
/* 481 */     if (tecla == 10)
/* 482 */       if (campo == this.cDocu)
/* 483 */         this.cConcepto.requestFocus();
/* 484 */       else if (campo == this.cConcepto)
/* 485 */         this.cImporte.requestFocus();
/* 486 */       else if (campo == this.cImporte)
/* 487 */         this.cMarca.requestFocus();
/* 488 */       else if (campo == this.cMarca)
/* 489 */         this.bCrear.requestFocus();
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent arg0)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent arg0)
/*     */   {
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.asientosModelo.Utilizar
 * JD-Core Version:    0.6.2
 */