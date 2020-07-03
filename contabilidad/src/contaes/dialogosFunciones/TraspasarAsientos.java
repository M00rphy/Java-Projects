/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoApuntes;
/*     */ import contaes.manejoDatos.ManejoAsientos;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import contaes.manejoDatos.ManejoFacturas;
/*     */ import contaes.manejoDatos.TipoApunte;
/*     */ import contaes.manejoDatos.TipoAsiento;
/*     */ import contaes.manejoDatos.TipoAsientoEx;
/*     */ import contaes.manejoDatos.TipoFactura;
/*     */ import contaes.manejoDatos.TipoFacturaExtended;
/*     */ import contaes.manejoDatos.auxiliar.MySQLConection;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class TraspasarAsientos extends JDialog
/*     */ {
/*     */   ManejoEmpresas mE;
/*     */   String ejercicio;
/*     */   int empresa;
/*     */   private JButton botonCopiarBorrar;
/*     */   private JTextField campoFin;
/*     */   private JTextField campoInicio;
/*     */   private JComboBox comboEmpresas;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public TraspasarAsientos(Frame parent, boolean modal)
/*     */   {
/*  42 */     super(parent, modal);
/*  43 */     initComponents();
/*  44 */     this.botonCopiarBorrar.setHorizontalTextPosition(2);
/*     */ 
/*  46 */     this.mE = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa());
/*     */ 
/*  48 */     this.comboEmpresas.setModel(modeloListaEmpresas());
/*     */ 
/*  50 */     this.ejercicio = Inicio.p.getEjercicio();
/*  51 */     if (this.comboEmpresas.getItemCount() > 0) {
/*  52 */       String temp = (String)this.comboEmpresas.getItemAt(0);
/*  53 */       this.empresa = this.mE.getIdEmpresa(temp);
/*     */     }
/*     */     else {
/*  56 */       this.empresa = Inicio.p.getEmpresa();
/*     */     }
/*     */   }
/*     */ 
/*     */   private DefaultComboBoxModel modeloListaEmpresas() {
/*  61 */     DefaultComboBoxModel modelo = new DefaultComboBoxModel();
/*  62 */     LinkedList lista = this.mE.listaEmpresas();
/*  63 */     if (lista.size() > 0) {
/*  64 */       for (String emp :(List<String>) lista) {
/*  65 */         modelo.addElement(emp);
/*     */       }
/*     */     }
/*  68 */     return modelo;
/*     */   }
/*     */ 
/*     */   private boolean copiarAsientos(boolean borrar) {
/*  72 */     if (this.empresa != Inicio.p.getEmpresa()) {
/*  73 */       int ej = Integer.parseInt(this.ejercicio);
/*  74 */       String situacionEj = this.mE.crearEjercicio1(this.empresa, ej);
/*  75 */       if (situacionEj.equals("existe")) {
/*     */         try {
/*  77 */           int astoInicial = Integer.parseInt(this.campoInicio.getText());
/*  78 */           int astoFinal = Integer.parseInt(this.campoFin.getText());
/*  79 */           if (astoInicial <= astoFinal) {
/*  80 */             ArrayList asientos = recopilarAsientos(astoInicial, astoFinal);
/*  81 */             if (asientos.size() > 0) {
/*  82 */               if (crearAsientos(this.empresa, this.ejercicio, asientos)) {
/*  83 */                 if (borrar) {
/*  84 */                   borrarAsientos(asientos);
/*     */                 }
/*  86 */                 return true;
/*     */               }
/*     */ 
/*  89 */               showMensaje("Hubo un error al crear los asientos");
/*  90 */               return false;
/*     */             }
/*     */ 
/*  94 */             showMensaje("No existen asientos en el rango introducido");
/*  95 */             return false;
/*     */           }
/*     */ 
/*  99 */           showMensaje("El asiento inicial no puede ser mayor que el final");
/* 100 */           return false;
/*     */         }
/*     */         catch (NumberFormatException exc)
/*     */         {
/* 104 */           exc.printStackTrace();
/* 105 */           showMensaje("Números de asiento no válidos");
/* 106 */           return false;
/*     */         }
/*     */       }
/*     */ 
/* 110 */       showMensaje("No existe el ejercicio para la empresa seleccionada");
/* 111 */       return false;
/*     */     }
/*     */ 
/* 115 */     showMensaje("Debe seleccionar una empresa\ndistinta a la que está en uso.");
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   private ArrayList<TipoAsientoEx> recopilarAsientos(int ini, int fin)
/*     */   {
/* 121 */     ManejoAsientos mA = new ManejoAsientos(Inicio.getCEmpresa(), this.ejercicio);
/* 122 */     ManejoFacturas mF = new ManejoFacturas(Inicio.getCEmpresa(), this.ejercicio);
/* 123 */     ArrayList asientos = new ArrayList();
/* 124 */     for (int numAs = ini; numAs <= fin; numAs++) {
/* 125 */       TipoAsiento asSimple = mA.datos(true, numAs);
/* 126 */       if (asSimple != null) {
/* 127 */         ArrayList apuntes = mA.getApuntes(asSimple.getId_asiento());
/* 128 */         TipoFacturaExtended factura = null;
/* 129 */         if (asSimple.getMarca().equals("R")) {
/* 130 */           factura = mF.datos(asSimple.getDocumento());
/*     */         }
/* 132 */         if (asSimple.getMarca().equals("E")) {
/* 133 */           TipoFactura fS = mF.datos(true, asSimple.getDocumento());
/* 134 */           if (fS != null) {
/* 135 */             factura = new TipoFacturaExtended(fS.getNumero(), fS.getFecha(), fS.getCuenta(), fS.getId_asiento(), fS.getConcepto(), fS.getBase(), fS.getIva(), fS.getTotal(), 0);
/* 136 */             factura.setRe(fS.getRe());
/*     */           }
/*     */         }
/* 139 */         TipoAsientoEx asiento = new TipoAsientoEx(asSimple.getId_asiento(), asSimple.getNumero(), asSimple.getFecha(), asSimple.getDocumento(), asSimple.getMarca(), apuntes, factura);
/* 140 */         asientos.add(asiento);
/*     */       }
/*     */     }
/* 143 */     mA.cerrarRs();
/* 144 */     mF.cerrarRs();
/* 145 */     return asientos;
/*     */   }
/*     */ 
/*     */   private void borrarAsientos(ArrayList<TipoAsientoEx> asientos) {
/* 149 */     ManejoAsientos mA = new ManejoAsientos(Inicio.getCEmpresa(), this.ejercicio);
/* 150 */     ManejoFacturas mF = new ManejoFacturas(Inicio.getCEmpresa(), this.ejercicio);
/* 151 */     for (TipoAsientoEx asiento : asientos) {
/* 152 */       if (asiento.getMarca().equals("E")) {
/* 153 */         mF.borrar(true, asiento.getFactura().getNumero());
/*     */       }
/* 155 */       else if (asiento.getMarca().equals("R")) {
/* 156 */         mF.borrar(false, asiento.getFactura().getNumero());
/*     */       }
/*     */       else {
/* 159 */         mA.borrar(asiento.getId_asiento());
/*     */       }
/*     */     }
/* 162 */     mA.cerrarRs();
/* 163 */     mF.cerrarRs();
/*     */   }
/*     */ 
/*     */   private boolean crearAsientos(int empresa, String ejercicio, ArrayList<TipoAsientoEx> asientos) {
/* 167 */     MySQLConection conex = new MySQLConection(empresa);
/* 168 */     ManejoAsientos mA = new ManejoAsientos(conex, ejercicio);
/* 169 */     ManejoApuntes mAp = new ManejoApuntes(conex, ejercicio);
/* 170 */     ManejoFacturas mF = new ManejoFacturas(conex, ejercicio);
/* 171 */     for (TipoAsientoEx asiento : asientos) {
/* 172 */       int numAs = mA.nuevoNumero();
/* 173 */       if (numAs != -1) {
/* 174 */         boolean emitida = asiento.getMarca().equals("E");
/* 175 */         if ((asiento.getFactura() != null) && (mF.existeFactura(emitida, asiento.getFactura().getNumero()) != -1))
/*     */         {
/* 177 */           Object[] options = { "Copiar", "Reemplazar", "Cancelar" };
/*     */ 
/* 180 */           int n = JOptionPane.showOptionDialog(Inicio.frame, "Ya existe una factura de número " + asiento.getFactura().getNumero() + "\n¿Qué desea hacer?", "Factura existente", 1, 3, null, options, options[2]);
/*     */ 
/* 190 */           if ((n == -1) || (n == 2)) {
/*     */             break;
/*     */           }
/* 193 */           if (n == 1)
/*     */           {
/* 195 */             mF.borrar(emitida, asiento.getFactura().getNumero());
/*     */           }
/* 197 */           else if (n != 0);
/*     */         }
/*     */ 
/* 201 */         int idAsiento = mA.crear(numAs, asiento.getFecha(), asiento.getDocumento(), asiento.getMarca());
/* 202 */         if (idAsiento != -1) {
/* 203 */           boolean error = false;
/*     */ 
/* 205 */           for (TipoApunte apunte : asiento.getApuntes()) {
/* 206 */             apunte.setId_asiento(idAsiento);
/* 207 */             if (!mAp.crear(apunte)) {
/* 208 */               showMensaje("Error al crear apunte: " + apunte + "\nPara asiento: " + asiento);
/* 209 */               error = true;
/*     */             }
/*     */           }
/* 212 */           if (asiento.getFactura() != null) {
/* 213 */             asiento.getFactura().setId_asiento(idAsiento);
/* 214 */             if (!mF.crear(emitida, asiento.getFactura())) {
/* 215 */               showMensaje("Error al crear factura: " + asiento.getDocumento() + "\nPara el asiento: " + asiento);
/* 216 */               error = true;
/*     */             }
/*     */           }
/* 219 */           if (error) {
/* 220 */             mA.borrar(idAsiento);
/* 221 */             mF.borrar(emitida, asiento.getFactura().getNumero());
/*     */           }
/*     */         }
/*     */         else {
/* 225 */           showMensaje("Error al crear asiento: " + asiento);
/*     */         }
/*     */       }
/*     */       else {
/* 229 */         showMensaje("Error al obtener número de asiento para: " + asiento);
/*     */       }
/*     */     }
/* 232 */     mA.cerrarRs();
/* 233 */     mAp.cerrarRs();
/* 234 */     mF.cerrarRs();
/* 235 */     conex.cierraConexion();
/* 236 */     return true;
/*     */   }
/*     */ 
/*     */   private void showMensaje(String mensaje) {
/* 240 */     JOptionPane.showMessageDialog(getContentPane(), mensaje, "Error", 0);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 253 */     this.jPanel1 = new JPanel();
/* 254 */     this.jLabel2 = new JLabel();
/* 255 */     this.comboEmpresas = new JComboBox();
/* 256 */     this.jLabel3 = new JLabel();
/* 257 */     this.jLabel4 = new JLabel();
/* 258 */     this.campoInicio = new JTextField();
/* 259 */     this.campoFin = new JTextField();
/* 260 */     this.jLabel5 = new JLabel();
/* 261 */     this.jButton2 = new JButton();
/* 262 */     this.jButton1 = new JButton();
/* 263 */     this.botonCopiarBorrar = new JButton();
/*     */ 
/* 265 */     setDefaultCloseOperation(2);
/* 266 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 267 */     setTitle(bundle.getString("copiarasientos"));
/*     */ 
/* 269 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 271 */     this.jLabel2.setText("Seleccione empresa de destino:");
/*     */ 
/* 273 */     this.comboEmpresas.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 275 */         TraspasarAsientos.this.comboEmpresasActionPerformed(evt);
/*     */       }
/*     */     });
/* 279 */     this.jLabel3.setText("Asientos a copiar:");
/*     */ 
/* 281 */     this.jLabel4.setText("Inicial");
/*     */ 
/* 283 */     this.campoInicio.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 285 */         TraspasarAsientos.this.campoInicioActionPerformed(evt);
/*     */       }
/*     */     });
/* 289 */     this.campoFin.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 291 */         TraspasarAsientos.this.campoFinActionPerformed(evt);
/*     */       }
/*     */     });
/* 295 */     this.jLabel5.setText("Final");
/*     */ 
/* 297 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel22.png")));
/* 298 */     this.jButton2.setText(bundle.getString("cancelar"));
/* 299 */     this.jButton2.setHorizontalTextPosition(2);
/* 300 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 302 */         TraspasarAsientos.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/* 306 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/Traspaso.png")));
/* 307 */     this.jButton1.setText(bundle.getString("copiar"));
/* 308 */     this.jButton1.setHorizontalTextPosition(2);
/* 309 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 311 */         TraspasarAsientos.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 315 */     this.botonCopiarBorrar.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/TraspasoBorro.png")));
/* 316 */     this.botonCopiarBorrar.setText("Copiar y borrar");
/* 317 */     this.botonCopiarBorrar.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 319 */         TraspasarAsientos.this.botonCopiarBorrarActionPerformed(evt);
/*     */       }
/*     */     });
/* 323 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 324 */     this.jPanel1.setLayout(jPanel1Layout);
/* 325 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1, false).add(this.botonCopiarBorrar).add(this.jLabel3).add(this.jLabel2).add(this.jButton1).add(jPanel1Layout.createSequentialGroup().add(this.jLabel4).add(18, 18, 18).add(this.campoInicio, -2, 92, -2).add(22, 22, 22).add(jPanel1Layout.createParallelGroup(1).add(this.jButton2).add(jPanel1Layout.createSequentialGroup().add(this.jLabel5).addPreferredGap(0).add(this.campoFin, -2, 89, -2)))).add(this.comboEmpresas, 0, -1, 32767)).addContainerGap(-1, 32767)));
/*     */ 
/* 348 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jLabel2).addPreferredGap(1).add(this.comboEmpresas, -2, -1, -2).add(18, 18, 18).add(this.jLabel3).addPreferredGap(1).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel4).add(this.campoInicio, -2, -1, -2).add(this.campoFin, -2, -1, -2).add(this.jLabel5)).add(18, 18, 18).add(this.jButton1).add(10, 10, 10).add(jPanel1Layout.createParallelGroup(3).add(this.botonCopiarBorrar).add(this.jButton2)).addContainerGap(-1, 32767)));
/*     */ 
/* 372 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 373 */     getContentPane().setLayout(layout);
/* 374 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 381 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -2, -1, -2).addContainerGap(-1, 32767)));
/*     */ 
/* 389 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 393 */     if (copiarAsientos(false)) {
/* 394 */       JOptionPane.showMessageDialog(getContentPane(), "Copia realizada", "Información", 1);
/* 395 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 400 */     dispose();
/*     */   }
/*     */ 
/*     */   private void comboEmpresasActionPerformed(ActionEvent evt) {
/* 404 */     if (this.comboEmpresas.getSelectedIndex() != -1) {
/* 405 */       String temp = (String)this.comboEmpresas.getSelectedItem();
/* 406 */       this.empresa = this.mE.getIdEmpresa(temp);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void campoInicioActionPerformed(ActionEvent evt) {
/* 411 */     this.campoFin.requestFocus();
/*     */   }
/*     */ 
/*     */   private void campoFinActionPerformed(ActionEvent evt) {
/* 415 */     this.jButton1.requestFocus();
/*     */   }
/*     */ 
/*     */   private void botonCopiarBorrarActionPerformed(ActionEvent evt) {
/* 419 */     if (copiarAsientos(true)) {
/* 420 */       JOptionPane.showMessageDialog(getContentPane(), "Copia realizada", "Información", 1);
/* 421 */       dispose();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.TraspasarAsientos
 * JD-Core Version:    0.6.2
 */