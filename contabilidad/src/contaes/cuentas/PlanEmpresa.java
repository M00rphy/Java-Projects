/*     */ package contaes.cuentas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.auxiliarTablas.DerechaColorRenderer;
/*     */ import contaes.auxiliarTablas.GeneralColorRenderer;
/*     */ import contaes.auxiliarTablas.ScrollableTableModel;
/*     */ import contaes.auxiliarTablas.TableSorter;
/*     */ import contaes.listados.Listado;
/*     */ import contaes.listados.ListadoMayor;
/*     */ import contaes.manejoDatos.ManejoSubcuentas;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ public class PlanEmpresa extends JPanel
/*     */ {
/*  68 */   private JPanel jPanel = null;
/*  69 */   private JButton bModificar = null;
/*  70 */   private JButton bBorrar = null;
/*  71 */   private JButton bMayor = null;
/*  72 */   private JButton bBuscar = null;
/*  73 */   private JButton bRenovar = null;
/*  74 */   private JTextField cTexto = null;
/*  75 */   private JScrollPane jScrollPane = null;
/*  76 */   private JTable tabla = null;
/*  77 */   private TableSorter tablaOrd = null;
/*  78 */   private ScrollableTableModel modeloTabla = null;
/*  79 */   private Acciones escuchaAccion = null;
/*  80 */   private JPopupMenu menuEmergente = new JPopupMenu();
/*  81 */   private JMenuItem jMenuItem = null;
/*  82 */   private JMenuItem jMenuItem1 = null;
/*  83 */   private JMenuItem jMenuItem2 = null;
/*  84 */   int inset = 124;
/*  85 */   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  86 */   int ancho_padre = this.screenSize.width - this.inset * 2;
/*     */ 
/*     */   public PlanEmpresa()
/*     */   {
/*  93 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/* 102 */     this.escuchaAccion = new Acciones();
/* 103 */     this.menuEmergente.add(getJMenuItem());
/* 104 */     this.menuEmergente.add(getJMenuItem1());
/* 105 */     this.menuEmergente.addSeparator();
/* 106 */     this.menuEmergente.add(getJMenuItem2());
/* 107 */     setLayout(new BorderLayout());
/* 108 */     setSize(595, 373);
/* 109 */     add(getJPanel(), "South");
/* 110 */     add(getJScrollPane(), "Center");
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/* 119 */     if (this.jPanel == null) {
/* 120 */       GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
/* 121 */       gridBagConstraints11.gridx = 1;
/* 122 */       gridBagConstraints11.gridy = 1;
/* 123 */       gridBagConstraints11.insets = new Insets(5, 0, 0, 0);
/* 124 */       GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
/* 125 */       gridBagConstraints5.fill = 2;
/* 126 */       gridBagConstraints5.gridy = 1;
/* 127 */       gridBagConstraints5.weightx = 1.0D;
/* 128 */       gridBagConstraints5.gridwidth = 1;
/* 129 */       gridBagConstraints5.insets = new Insets(5, 60, 0, 60);
/* 130 */       gridBagConstraints5.gridx = 2;
/* 131 */       GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
/* 132 */       gridBagConstraints3.insets = new Insets(0, 10, 0, 10);
/* 133 */       GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
/* 134 */       gridBagConstraints2.gridx = 2;
/* 135 */       gridBagConstraints2.insets = new Insets(0, 10, 0, 10);
/* 136 */       gridBagConstraints2.gridy = 0;
/* 137 */       GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
/* 138 */       gridBagConstraints1.gridx = 1;
/* 139 */       gridBagConstraints1.insets = new Insets(0, 10, 0, 10);
/* 140 */       gridBagConstraints1.gridy = 0;
/* 141 */       GridBagConstraints gridBagConstraints = new GridBagConstraints();
/* 142 */       gridBagConstraints.gridx = 0;
/* 143 */       gridBagConstraints.insets = new Insets(5, 0, 0, 0);
/* 144 */       gridBagConstraints.gridy = 1;
/* 145 */       this.jPanel = new JPanel();
/* 146 */       this.jPanel.setLayout(new GridBagLayout());
/* 147 */       this.jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
/* 148 */       this.jPanel.add(getBModificar(), gridBagConstraints3);
/* 149 */       this.jPanel.add(getBBorrar(), gridBagConstraints);
/* 150 */       this.jPanel.add(getBMayor(), gridBagConstraints1);
/* 151 */       this.jPanel.add(getBBuscar(), gridBagConstraints2);
/* 152 */       this.jPanel.add(getCTexto(), gridBagConstraints5);
/* 153 */       this.jPanel.add(getBRenovar(), gridBagConstraints11);
/*     */     }
/* 155 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JButton getBRenovar()
/*     */   {
/* 164 */     if (this.bRenovar == null) {
/* 165 */       this.bRenovar = new JButton();
/* 166 */       this.bRenovar.setHorizontalTextPosition(2);
/* 167 */       this.bRenovar.setText(Mensajes.getString("renovarTabla"));
/* 168 */       this.bRenovar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/retable16.png")));
/* 169 */       this.bRenovar.addActionListener(this.escuchaAccion);
/*     */     }
/* 171 */     return this.bRenovar;
/*     */   }
/*     */ 
/*     */   private JButton getBModificar()
/*     */   {
/* 180 */     if (this.bModificar == null) {
/* 181 */       this.bModificar = new JButton();
/* 182 */       this.bModificar.setHorizontalTextPosition(2);
/* 183 */       this.bModificar.setText(Mensajes.getString("modificar"));
/* 184 */       this.bModificar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit.png")));
/* 185 */       this.bModificar.addActionListener(this.escuchaAccion);
/*     */     }
/* 187 */     return this.bModificar;
/*     */   }
/*     */ 
/*     */   private JButton getBBorrar()
/*     */   {
/* 196 */     if (this.bBorrar == null) {
/* 197 */       this.bBorrar = new JButton();
/* 198 */       this.bBorrar.setText(Mensajes.getString("borrar"));
/* 199 */       this.bBorrar.setHorizontalTextPosition(2);
/* 200 */       this.bBorrar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/delete.png")));
/* 201 */       this.bBorrar.addActionListener(this.escuchaAccion);
/*     */     }
/* 203 */     return this.bBorrar;
/*     */   }
/*     */ 
/*     */   private JButton getBMayor()
/*     */   {
/* 212 */     if (this.bMayor == null) {
/* 213 */       this.bMayor = new JButton();
/* 214 */       this.bMayor.setHorizontalTextPosition(2);
/* 215 */       this.bMayor.setText(Mensajes.getString("listMayor"));
/* 216 */       this.bMayor.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/mayor.png")));
/* 217 */       this.bMayor.addActionListener(this.escuchaAccion);
/*     */     }
/* 219 */     return this.bMayor;
/*     */   }
/*     */ 
/*     */   private JButton getBBuscar()
/*     */   {
/* 228 */     if (this.bBuscar == null) {
/* 229 */       this.bBuscar = new JButton();
/* 230 */       this.bBuscar.setText(Mensajes.getString("buscar"));
/* 231 */       this.bBuscar.setHorizontalTextPosition(2);
/* 232 */       this.bBuscar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/find.png")));
/* 233 */       this.bBuscar.addActionListener(this.escuchaAccion);
/*     */     }
/* 235 */     return this.bBuscar;
/*     */   }
/*     */ 
/*     */   private JTextField getCTexto()
/*     */   {
/* 244 */     if (this.cTexto == null) {
/* 245 */       this.cTexto = new JTextField();
/* 246 */       this.cTexto.addActionListener(this.escuchaAccion);
/*     */     }
/* 248 */     return this.cTexto;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 257 */     if (this.jScrollPane == null) {
/* 258 */       this.jScrollPane = new JScrollPane();
/* 259 */       this.jScrollPane.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createMatteBorder(10, 10, 10, 10, Color.white)));
/*     */ 
/* 261 */       this.jScrollPane.setViewportView(getTabla());
/*     */     }
/* 263 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JTable getTabla()
/*     */   {
/* 272 */     if (this.tabla == null) {
/* 273 */       this.tabla = new JTable(getTablaOrd());
/* 274 */       this.tablaOrd.setTableHeader(this.tabla.getTableHeader());
/* 275 */       this.tabla.setSelectionMode(0);
/* 276 */       this.tabla.getTableHeader().setToolTipText(Mensajes.getString("tableHeadertt"));
/* 277 */       this.tabla.setDefaultRenderer(Object.class, new GeneralColorRenderer());
/* 278 */       this.tabla.addMouseListener(new MouseAdapter()
/*     */       {
/*     */         public void mouseClicked(MouseEvent e)
/*     */         {
/* 282 */           if (e.getButton() == 3) {
/* 283 */             PlanEmpresa.this.tabla.changeSelection(PlanEmpresa.this.tabla.rowAtPoint(new Point(e.getX(), e.getY())), PlanEmpresa.this.tabla.columnAtPoint(new Point(e.getX(), e.getY())), false, false);
/*     */ 
/* 287 */             PlanEmpresa.this.menuEmergente.show(e.getComponent(), e.getX(), e.getY());
/*     */           }
/*     */         }
/*     */       });
/* 292 */       TableColumn columna = null;
/* 293 */       columna = this.tabla.getColumnModel().getColumn(0);
/* 294 */       columna.setPreferredWidth((int)((this.ancho_padre - 88) * 0.15D));
/* 295 */       columna.setCellRenderer(new DerechaColorRenderer());
/* 296 */       columna = this.tabla.getColumnModel().getColumn(1);
/* 297 */       columna.setPreferredWidth((int)((this.ancho_padre - 88) * 0.75D));
/* 298 */       columna = this.tabla.getColumnModel().getColumn(2);
/* 299 */       columna.setPreferredWidth((int)((this.ancho_padre - 88) * 0.1D));
/*     */     }
/* 301 */     return this.tabla;
/*     */   }
/*     */ 
/*     */   private TableSorter getTablaOrd()
/*     */   {
/* 309 */     if (this.tablaOrd == null) {
/* 310 */       this.tablaOrd = new TableSorter(getModeloTabla());
/*     */     }
/* 312 */     return this.tablaOrd;
/*     */   }
/*     */ 
/*     */   private ScrollableTableModel getModeloTabla()
/*     */   {
/* 320 */     if (this.modeloTabla == null) {
/* 321 */       this.modeloTabla = new ScrollableTableModel(getCon(), getSel(), getColNames());
/*     */     }
/*     */ 
/* 324 */     return this.modeloTabla;
/*     */   }
/*     */ 
/*     */   private Connection getCon()
/*     */   {
/* 332 */     return Inicio.getCEmpresa().getCon();
/*     */   }
/*     */ 
/*     */   private String getSel()
/*     */   {
/* 340 */     return "SELECT codigo,nombre,gbalance FROM scta" + Inicio.p.getEjercicio() + " ORDER BY codigo";
/*     */   }
/*     */ 
/*     */   private List<String> getColNames()
/*     */   {
/* 348 */     List columnas = new ArrayList();
/* 349 */     columnas.add(Mensajes.getString("codigo"));
/* 350 */     columnas.add(Mensajes.getString("nombre"));
/* 351 */     columnas.add(Mensajes.getString("codBalanceA"));
/* 352 */     return columnas;
/*     */   }
/*     */ 
/*     */   private JMenuItem getJMenuItem() {
/* 356 */     if (this.jMenuItem == null) {
/* 357 */       this.jMenuItem = new JMenuItem();
/* 358 */       this.jMenuItem.setText(Mensajes.getString("modificar"));
/* 359 */       this.jMenuItem.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/edit.png")));
/* 360 */       this.jMenuItem.addActionListener(this.escuchaAccion);
/*     */     }
/* 362 */     return this.jMenuItem;
/*     */   }
/*     */ 
/*     */   private JMenuItem getJMenuItem1() {
/* 366 */     if (this.jMenuItem1 == null) {
/* 367 */       this.jMenuItem1 = new JMenuItem();
/* 368 */       this.jMenuItem1.setText(Mensajes.getString("borrar"));
/* 369 */       this.jMenuItem1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/delete.png")));
/* 370 */       this.jMenuItem1.addActionListener(this.escuchaAccion);
/*     */     }
/* 372 */     return this.jMenuItem1;
/*     */   }
/*     */ 
/*     */   private JMenuItem getJMenuItem2() {
/* 376 */     if (this.jMenuItem2 == null) {
/* 377 */       this.jMenuItem2 = new JMenuItem();
/* 378 */       this.jMenuItem2.setText(Mensajes.getString("listMayor"));
/* 379 */       this.jMenuItem2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/mayor.png")));
/* 380 */       this.jMenuItem2.addActionListener(this.escuchaAccion);
/*     */     }
/* 382 */     return this.jMenuItem2;
/*     */   }
/*     */ 
/*     */   public void renovarTablaEmpresa()
/*     */   {
/* 389 */     this.tabla.removeAll();
/* 390 */     this.tabla = null;
/* 391 */     this.tablaOrd = null;
/* 392 */     this.modeloTabla = null;
/* 393 */     this.jScrollPane.setViewportView(getTabla());
/*     */   }
/*     */ 
/*     */   private void modificaCuenta()
/*     */   {
/* 402 */     int filaSeleccionada = this.tabla.getSelectedRow();
/* 403 */     if (filaSeleccionada != -1) {
/* 404 */       String codigo = ""; String nombre = ""; String gBal = "";
/* 405 */       codigo = this.tabla.getValueAt(this.tabla.getSelectedRow(), 0).toString();
/* 406 */       nombre = this.tabla.getValueAt(this.tabla.getSelectedRow(), 1).toString();
/* 407 */       gBal = this.tabla.getValueAt(this.tabla.getSelectedRow(), 2).toString();
/* 408 */       CrearSubcuenta dlg = new CrearSubcuenta(Inicio.frame, Mensajes.getString("modificar") + " " + Mensajes.getString("subcuenta"), true, false);
/* 409 */       dlg.datos(codigo, nombre, gBal);
/* 410 */       Dimension dlgSize = dlg.getPreferredSize();
/* 411 */       Dimension frmSize = getSize();
/* 412 */       Point loc = getLocation();
/* 413 */       dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*     */ 
/* 415 */       dlg.setModal(true);
/* 416 */       dlg.setVisible(true);
/* 417 */       if (dlg.isCambios()) {
/* 418 */         String cuenta = dlg.getSubcuenta();
/* 419 */         if ((cuenta != null) && (cuenta.length() == 8)) {
/* 420 */           String grupo = cuenta.substring(0, 2);
/* 421 */           if (grupo.equals("57")) {
/* 422 */             Inicio.frame.renovarModeloTesoreria();
/*     */           }
/* 424 */           else if (grupo.equals("40")) {
/* 425 */             Inicio.frame.renovarModeloProveedoresFacturacion();
/*     */           }
/* 427 */           else if (grupo.equals("43")) {
/* 428 */             Inicio.frame.renovarModeloClientesFacturacion();
/* 429 */             Inicio.frame.renovarModeloClientesPedidos();
/*     */           }
/*     */         }
/*     */       }
/* 433 */       this.tabla.changeSelection(filaSeleccionada, 0, false, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void buscarColumnas()
/*     */   {
/* 442 */     String patron = this.cTexto.getText().toUpperCase();
/* 443 */     int numFilas = this.tabla.getRowCount(); int numColumns = this.tabla.getColumnCount(); int x = 0; int y = 0;
/*     */ 
/* 445 */     boolean find = false;
/* 446 */     for (y = 0; y < numColumns; y++) {
/* 447 */       for (x = this.tabla.getSelectedRow() + 1; x < numFilas; x++) {
/* 448 */         if (this.tabla.getValueAt(x, y).toString().toUpperCase().indexOf(patron) != -1) {
/* 449 */           this.tabla.changeSelection(x, 0, false, false);
/* 450 */           find = true;
/* 451 */           break;
/*     */         }
/*     */       }
/* 454 */       if (find) {
/*     */         break;
/*     */       }
/*     */     }
/* 458 */     if (x == numFilas) {
/* 459 */       if (y >= numColumns) {
/* 460 */         y = numColumns - 1;
/*     */       }
/* 462 */       int volver = JOptionPane.showConfirmDialog(getParent(), Mensajes.getString("finTabla"), Mensajes.getString("confirma"), 0);
/*     */ 
/* 465 */       if (volver == 0) {
/* 466 */         this.tabla.changeSelection(0, 0, false, false);
/* 467 */         if (this.tabla.getValueAt(0, y).toString().toUpperCase().indexOf(patron) != -1) {
/* 468 */           return;
/*     */         }
/* 470 */         buscar();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void buscar() {
/* 476 */     String patron = this.cTexto.getText().toUpperCase();
/* 477 */     int numFilas = this.tabla.getRowCount(); int numColumns = this.tabla.getColumnCount();
/* 478 */     for (int fila = this.tabla.getSelectedRow() + 1; fila < numFilas; fila++) {
/* 479 */       for (int columna = 0; columna < numColumns; columna++) {
/* 480 */         String objeto = this.tabla.getValueAt(fila, columna).toString();
/* 481 */         objeto = objeto.toUpperCase();
/* 482 */         if (objeto.indexOf(patron) != -1) {
/* 483 */           this.tabla.changeSelection(fila, columna, false, false);
/* 484 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 489 */     for (int columna = 0; columna < numColumns; columna++) {
/* 490 */       String objeto = this.tabla.getValueAt(0, columna).toString();
/* 491 */       objeto = objeto.toUpperCase();
/* 492 */       if (objeto.indexOf(patron) != -1) {
/* 493 */         this.tabla.changeSelection(0, columna, false, false);
/* 494 */         return;
/*     */       }
/*     */     }
/*     */ 
/* 498 */     int volver = JOptionPane.showConfirmDialog(getParent(), Mensajes.getString("finTabla"), Mensajes.getString("confirma"), 0);
/*     */ 
/* 501 */     if (volver == 0) {
/* 502 */       this.tabla.changeSelection(0, 0, false, false);
/* 503 */       buscar();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void buscar(String texto) {
/* 508 */     this.cTexto.setText(texto);
/* 509 */     this.tabla.changeSelection(0, 0, false, false);
/* 510 */     buscar();
/* 511 */     this.cTexto.setText("");
/*     */   }
/*     */ 
/*     */   private void listadoMayor()
/*     */   {
/* 519 */     ListadoMayor dlg = null;
/*     */     try {
/* 521 */       dlg = new ListadoMayor(Integer.parseInt(cuentaSeleccionada()));
/*     */     } catch (NumberFormatException e) {
/* 523 */       System.out.println(e.getMessage());
/*     */     }
/* 525 */     if (dlg != null) {
/* 526 */       Dimension dlgSize = dlg.getPreferredSize();
/* 527 */       Dimension frmSize = getSize();
/* 528 */       Point loc = getLocation();
/* 529 */       dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*     */ 
/* 531 */       dlg.setModal(true);
/* 532 */       dlg.pack();
/* 533 */       dlg.setVisible(true);
/* 534 */       if (dlg.Listar()) {
/* 535 */         List listado = dlg.textoListado();
/* 536 */         Listado listaMayor = new Listado(Mensajes.getString("mayor") + " " + Mensajes.getString("de") + ": " + nombreSeleccionado(), listado);
/* 537 */         listaMayor.validate();
/* 538 */         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 539 */         frmSize = listaMayor.getSize();
/* 540 */         if (frmSize.height > screenSize.height) {
/* 541 */           frmSize.height = screenSize.height;
/*     */         }
/* 543 */         if (frmSize.width > screenSize.width) {
/* 544 */           frmSize.width = screenSize.width;
/*     */         }
/* 546 */         listaMayor.setLocation((screenSize.width - frmSize.width) / 2, (screenSize.height - frmSize.height) / 2);
/*     */ 
/* 548 */         listaMayor.setVisible(true);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void borraCuenta()
/*     */   {
/* 558 */     String cuenta = cuentaSeleccionada();
/* 559 */     if (!cuenta.equals("Error")) {
/* 560 */       int volver = JOptionPane.showConfirmDialog(getParent(), Mensajes.getString("dlgConfElimCta") + cuenta + ": " + nombreSeleccionado() + "?", Mensajes.getString("confirma"), 0);
/*     */ 
/* 564 */       if (volver == 0) {
/* 565 */         ManejoSubcuentas cuentaM = new ManejoSubcuentas(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/* 566 */         int isSubcuentaEnUso = cuentaM.borrar(Integer.parseInt(cuenta));
/* 567 */         if (isSubcuentaEnUso == 0) {
/* 568 */           renovarTablaEmpresa();
/* 569 */           String grupo = cuenta.substring(0, 2);
/* 570 */           if (grupo.equals("57")) {
/* 571 */             Inicio.frame.renovarModeloTesoreria();
/*     */           }
/* 573 */           else if (grupo.equals("40")) {
/* 574 */             Inicio.frame.renovarModeloProveedoresFacturacion();
/*     */           }
/* 576 */           else if (grupo.equals("43")) {
/* 577 */             Inicio.frame.renovarModeloClientesFacturacion();
/* 578 */             Inicio.frame.renovarModeloClientesPedidos();
/*     */           }
/*     */         } else {
/* 581 */           String mensaje = Mensajes.getString("cuentaNoEliminada") + " ";
/* 582 */           switch (isSubcuentaEnUso) {
/*     */           case 1:
/* 584 */             mensaje = mensaje + Mensajes.getString("cne1");
/* 585 */             break;
/*     */           case 2:
/* 587 */             mensaje = mensaje + Mensajes.getString("cne2");
/* 588 */             break;
/*     */           case 3:
/* 590 */             mensaje = mensaje + Mensajes.getString("cne3");
/* 591 */             break;
/*     */           case 4:
/* 593 */             mensaje = mensaje + Mensajes.getString("cne4");
/* 594 */             break;
/*     */           case 5:
/* 596 */             mensaje = mensaje + Mensajes.getString("cne5");
/* 597 */             break;
/*     */           case 6:
/* 599 */             mensaje = mensaje + Mensajes.getString("cne6");
/* 600 */             break;
/*     */           case 7:
/* 602 */             mensaje = mensaje + Mensajes.getString("cne7");
/*     */           }
/*     */ 
/* 605 */           JOptionPane.showMessageDialog(Inicio.frame, mensaje, "Error", 0);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String cuentaSeleccionada()
/*     */   {
/* 634 */     if (this.tabla.getSelectedRow() != -1) {
/* 635 */       return this.tabla.getValueAt(this.tabla.getSelectedRow(), 0).toString();
/*     */     }
/* 637 */     return "Error";
/*     */   }
/*     */ 
/*     */   public String nombreSeleccionado()
/*     */   {
/* 645 */     if (this.tabla.getSelectedRow() != -1) {
/* 646 */       return this.tabla.getValueAt(this.tabla.getSelectedRow(), 1).toString();
/*     */     }
/* 648 */     return "Error";
/*     */   }
/*     */ 
/*     */   public void focoABuscar() {
/* 652 */     this.cTexto.requestFocus();
/*     */   }
/*     */ 
/*     */   private class Acciones
/*     */     implements ActionListener
/*     */   {
/*     */     private Acciones()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent e)
/*     */     {
/* 614 */       Object campo = e.getSource();
/* 615 */       if (campo == PlanEmpresa.this.bRenovar)
/* 616 */         PlanEmpresa.this.renovarTablaEmpresa();
/* 617 */       else if ((campo == PlanEmpresa.this.bBuscar) || (campo == PlanEmpresa.this.cTexto))
/* 618 */         PlanEmpresa.this.buscar();
/* 619 */       else if ((campo == PlanEmpresa.this.bBorrar) || (campo == PlanEmpresa.this.jMenuItem1))
/* 620 */         PlanEmpresa.this.borraCuenta();
/* 621 */       else if ((campo == PlanEmpresa.this.bMayor) || (campo == PlanEmpresa.this.jMenuItem2))
/* 622 */         PlanEmpresa.this.listadoMayor();
/* 623 */       else if ((campo == PlanEmpresa.this.bModificar) || (campo == PlanEmpresa.this.jMenuItem))
/* 624 */         PlanEmpresa.this.modificaCuenta();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.cuentas.PlanEmpresa
 * JD-Core Version:    0.6.2
 */