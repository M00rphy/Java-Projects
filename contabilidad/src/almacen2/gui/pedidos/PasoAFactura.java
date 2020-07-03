/*     */ package almacen2.gui.pedidos;
/*     */ 
/*     */ import almacen2.data.AlmacenInterno;
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.calendario.ICalendarTextField;
/*     */ import contaes.manejoDatos.TipoIVA;
/*     */ import facturacion.control.AlmacenControl;
/*     */ import facturacion.control.FacturaControl;
/*     */ import facturacion.control.LineaFacturaControl;
/*     */ import facturacion.model.Factura;
/*     */ import facturacion.model.LineaFactura;
/*     */ import facturacion.model.Producto;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import org.jdesktop.layout.GroupLayout;
/*     */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*     */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*     */ 
/*     */ public class PasoAFactura extends JDialog
/*     */ {
/*     */   private Factura factura;
/*     */   private ArrayList<LineaFactura> lineasFactura;
/*     */   private boolean ventas;
/*  37 */   private boolean isOk = false;
/*     */   private ArrayList<AlmacenInterno> almacenes;
/*     */   private ICalendarTextField campoFecha;
/*     */   private JTextField campoNumero;
/*     */   private JComboBox comboAlmacenes;
/*     */   private JCheckBox isRecargo;
/*     */   private JButton jButton1;
/*     */   private JButton jButton2;
/*     */   private JCheckBox jCheckBox1;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JPanel jPanel1;
/*     */ 
/*     */   public PasoAFactura(Frame parent, boolean modal, Factura factura, ArrayList<LineaFactura> lineasFactura, boolean ventas, ArrayList<AlmacenInterno> almacenes)
/*     */   {
/*  42 */     super(parent, modal);
/*  43 */     this.factura = factura;
/*  44 */     this.lineasFactura = lineasFactura;
/*  45 */     this.ventas = ventas;
/*  46 */     this.almacenes = almacenes;
/*  47 */     initComponents();
/*     */ 
/*  49 */     DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
/*  50 */     for (AlmacenInterno almacenInterno : almacenes) {
/*  51 */       modeloCombo.addElement(almacenInterno);
/*     */     }
/*  53 */     this.comboAlmacenes.setModel(modeloCombo);
/*     */ 
/*  55 */     this.campoFecha.setDate(new Date());
/*  56 */     if (ventas) {
/*  57 */       colocarNuevoNumero();
/*  58 */       this.isRecargo.setEnabled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isIsOk() {
/*  63 */     return this.isOk;
/*     */   }
/*     */ 
/*     */   private void colocarNuevoNumero() {
/*  67 */     FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/*  68 */     this.campoNumero.setText(fC.nuevoNumeroFactEmitida(Inicio.p.getPrefijo()));
/*  69 */     fC.cerrarRs();
/*     */   }
/*     */ 
/*     */   private void addRecargoToFactura() {
/*  73 */     double ivaTotal = 0.0D;
/*  74 */     for (LineaFactura linea : this.lineasFactura) {
/*  75 */       double iva = linea.getBase().doubleValue() * (linea.getTipoIva().getIva() / 100.0D) + linea.getBase().doubleValue() * (linea.getTipoIva().getRe() / 100.0D);
/*  76 */       iva = doubleTwoDecimals(Double.valueOf(iva)).doubleValue();
/*  77 */       linea.setTotal(Double.valueOf(iva + linea.getBase().doubleValue()));
/*  78 */       ivaTotal += iva;
/*     */     }
/*  80 */     this.factura.setIva(Double.valueOf(ivaTotal));
/*     */   }
/*     */ 
/*     */   private Double doubleTwoDecimals(Double number) {
/*  84 */     if (number != null) {
/*  85 */       BigDecimal dec = new BigDecimal(number.doubleValue());
/*  86 */       return Double.valueOf(dec.setScale(2, 4).doubleValue());
/*     */     }
/*  88 */     return Double.valueOf(-1.0D);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 100 */     this.jPanel1 = new JPanel();
/* 101 */     this.jLabel1 = new JLabel();
/* 102 */     this.campoNumero = new JTextField();
/* 103 */     this.jLabel2 = new JLabel();
/* 104 */     this.campoFecha = new ICalendarTextField();
/* 105 */     this.jButton1 = new JButton();
/* 106 */     this.jButton2 = new JButton();
/* 107 */     this.jCheckBox1 = new JCheckBox();
/* 108 */     this.isRecargo = new JCheckBox();
/* 109 */     this.jLabel3 = new JLabel();
/* 110 */     this.comboAlmacenes = new JComboBox();
/*     */ 
/* 112 */     setDefaultCloseOperation(2);
/* 113 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 114 */     setTitle(bundle.getString("crearFactura"));
/*     */ 
/* 116 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*     */ 
/* 118 */     this.jLabel1.setText(bundle.getString("numero"));
/*     */ 
/* 120 */     this.jLabel2.setText(bundle.getString("fecha"));
/*     */ 
/* 122 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/* 123 */     this.jButton1.setText(bundle.getString("crearFactura"));
/* 124 */     this.jButton1.setHorizontalTextPosition(2);
/* 125 */     this.jButton1.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 127 */         PasoAFactura.this.jButton1ActionPerformed(evt);
/*     */       }
/*     */     });
/* 131 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 132 */     this.jButton2.setText(bundle.getString("cancelar"));
/* 133 */     this.jButton2.setHorizontalTextPosition(2);
/* 134 */     this.jButton2.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 136 */         PasoAFactura.this.jButton2ActionPerformed(evt);
/*     */       }
/*     */     });
/* 140 */     this.jCheckBox1.setSelected(true);
/* 141 */     this.jCheckBox1.setText(bundle.getString("updatealmacen"));
/*     */ 
/* 143 */     this.isRecargo.setText(bundle.getString("recargoe"));
/* 144 */     this.isRecargo.setEnabled(false);
/*     */ 
/* 146 */     this.jLabel3.setText(bundle.getString("almacen"));
/*     */ 
/* 148 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 149 */     this.jPanel1.setLayout(jPanel1Layout);
/* 150 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(this.jLabel1).add(this.jLabel2)).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(1).add(this.campoNumero, -2, 163, -2).add(this.campoFecha, -2, 129, -2))).add(jPanel1Layout.createSequentialGroup().add(this.jButton1).add(18, 18, 18).add(this.jButton2))).addContainerGap(47, 32767)).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.isRecargo).addContainerGap(83, 32767)).add(2, jPanel1Layout.createSequentialGroup().addContainerGap(20, 32767).add(jPanel1Layout.createParallelGroup(1, false).add(jPanel1Layout.createSequentialGroup().add(this.jLabel3).addPreferredGap(0).add(this.comboAlmacenes, 0, -1, 32767)).add(this.jCheckBox1)).addContainerGap()));
/*     */ 
/* 182 */     jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(jPanel1Layout.createParallelGroup(3).add(this.jLabel1).add(this.campoNumero, -2, -1, -2)).addPreferredGap(0).add(jPanel1Layout.createParallelGroup(2).add(this.jLabel2).add(this.campoFecha, -2, -1, -2)).add(18, 18, 18).add(this.isRecargo).addPreferredGap(1).add(this.jCheckBox1).addPreferredGap(1).add(jPanel1Layout.createParallelGroup(3).add(this.jLabel3).add(this.comboAlmacenes, -2, -1, -2)).addPreferredGap(0, -1, 32767).add(jPanel1Layout.createParallelGroup(3).add(this.jButton1).add(this.jButton2)).addContainerGap()));
/*     */ 
/* 208 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 209 */     getContentPane().setLayout(layout);
/* 210 */     layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 217 */     layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1, -1, -1, 32767).addContainerGap()));
/*     */ 
/* 225 */     pack();
/*     */   }
/*     */ 
/*     */   private void jButton1ActionPerformed(ActionEvent evt)
/*     */   {
/* 230 */     String numero = this.campoNumero.getText();
/* 231 */     Date fecha = this.campoFecha.getDate();
/* 232 */     boolean updateAlmacen = this.jCheckBox1.isSelected();
/* 233 */     if ((!numero.equals("")) && (fecha != null)) {
/* 234 */       this.factura.setNumero(numero);
/* 235 */       this.factura.setFecha(fecha);
/* 236 */       if ((this.ventas) && (this.isRecargo.isSelected())) {
/* 237 */         this.factura.setRecargo(true);
/* 238 */         addRecargoToFactura();
/*     */       }
/* 240 */       this.factura.setIsAlmacenada(updateAlmacen);
/*     */       try {
/* 242 */         AlmacenControl aC = new AlmacenControl();
/* 243 */         FacturaControl fC = new FacturaControl(Inicio.getCFacturacion(), this.ventas);
/* 244 */         Integer idF = fC.crear(this.factura);
/* 245 */         fC.cerrarRs();
/* 246 */         this.factura.setId(idF);
/* 247 */         LineaFacturaControl lFC = new LineaFacturaControl(Inicio.getCFacturacion(), this.ventas);
/* 248 */         for (LineaFactura lineaFactura : this.lineasFactura) {
/* 249 */           if (lineaFactura != null) {
/* 250 */             lineaFactura.setIdFactura(this.factura.getId());
/* 251 */             lFC.crear(lineaFactura);
/* 252 */             if (updateAlmacen) {
/* 253 */               AlmacenInterno almacen = (AlmacenInterno)this.comboAlmacenes.getSelectedItem();
/* 254 */               int idAlmacen = 1;
/* 255 */               if (almacen != null) {
/* 256 */                 idAlmacen = almacen.getId().intValue();
/*     */               }
/* 258 */               if (this.ventas) {
/* 259 */                 aC.insertVenta(lineaFactura.getIdProducto().getReferencia(), fecha, lineaFactura.getTotal().doubleValue() / lineaFactura.getUnidades().doubleValue(), lineaFactura.getUnidades().intValue(), idAlmacen);
/*     */               }
/*     */               else {
/* 262 */                 aC.insertCompra(lineaFactura.getIdProducto().getReferencia(), fecha, lineaFactura.getBase().doubleValue() / lineaFactura.getUnidades().doubleValue(), lineaFactura.getUnidades().intValue(), idAlmacen);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 267 */         lFC.cerrarRs();
/* 268 */         if (this.ventas) {
/* 269 */           Inicio.frame.renovarTabla(7);
/*     */         }
/*     */         else {
/* 272 */           Inicio.frame.renovarTabla(8);
/*     */         }
/* 274 */         this.isOk = true;
/*     */       } catch (Exception ex) {
/* 276 */         Logger.getLogger(PasoAFactura.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */     }
/* 279 */     dispose();
/*     */   }
/*     */ 
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 283 */     dispose();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.pedidos.PasoAFactura
 * JD-Core Version:    0.6.2
 */