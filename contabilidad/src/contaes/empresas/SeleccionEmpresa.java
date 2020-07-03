/*     */ package contaes.empresas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.ManejoEmpresas;
/*     */ import contaes.manejoDatos.auxiliar.ConfiguracionBean;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.LinkedList;
import java.util.List;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ 
/*     */ public class SeleccionEmpresa extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  37 */   private JPanel jContentPane = null;
/*  38 */   private JLabel jLabel = null;
/*  39 */   private JLabel jLabel1 = null;
/*  40 */   private JScrollPane jScrollPane = null;
/*  41 */   private JScrollPane jScrollPane1 = null;
/*  42 */   private JList listaEmpresas = null;
/*  43 */   private DefaultListModel modeloEmpresas = null;
/*  44 */   private JList listaEjercicios = null;
/*  45 */   private DefaultListModel modeloEjercicios = null;
/*  46 */   private JButton ok = null;
/*  47 */   private JButton cancel = null;
/*     */   private boolean paraBorrar;
/*  49 */   private boolean seleccionada = false;
/*  50 */   private String nombreEmpresa = "";
/*     */   private ManejoEmpresas empresaM;
/*  52 */   private boolean fullList = false;
/*     */ 
/*     */   public SeleccionEmpresa()
/*     */   {
/*  58 */     this(new Frame(), Mensajes.getString("selEmp"), false, false);
/*     */   }
/*     */ 
/*     */   public SeleccionEmpresa(Frame owner, String title, boolean modal, boolean borrar) {
/*  62 */     super(owner, title, modal);
/*  63 */     this.empresaM = new ManejoEmpresas(Inicio.getCGeneral(), Inicio.p.getEmpresa());
/*  64 */     this.paraBorrar = borrar;
/*  65 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  75 */     setSize(345, 280);
/*  76 */     setPreferredSize(new Dimension(345, 280));
/*  77 */     setContentPane(getJContentPane());
/*  78 */     this.listaEmpresas.setSelectedIndex(0);
/*     */   }
/*     */ 
/*     */   private JPanel getJContentPane()
/*     */   {
/*  87 */     if (this.jContentPane == null) {
/*  88 */       this.jLabel1 = new JLabel();
/*  89 */       this.jLabel1.setText(Mensajes.getString("ejercicio"));
/*  90 */       this.jLabel1.setLocation(new Point(230, 15));
/*  91 */       this.jLabel1.setSize(new Dimension(90, 16));
/*     */ 
/*  96 */       this.jContentPane = new JPanel();
/*  97 */       this.jContentPane.setLayout(null);
/*  98 */       this.jContentPane.add(getJLabel(), null);
/*  99 */       this.jContentPane.add(this.jLabel1, null);
/* 100 */       this.jContentPane.add(getJScrollPane(), null);
/* 101 */       this.jContentPane.add(getJScrollPane1(), null);
/* 102 */       this.jContentPane.add(getOk(), null);
/* 103 */       this.jContentPane.add(getCancel(), null);
/* 104 */       colocarEjercicios(this.listaEmpresas.getSelectedValue().toString());
/*     */     }
/* 106 */     return this.jContentPane;
/*     */   }
/*     */ 
/*     */   private JLabel getJLabel() {
/* 110 */     if (this.jLabel == null) {
/* 111 */       this.jLabel = new JLabel();
/* 112 */       this.jLabel.setText(Mensajes.getString("empresa"));
/* 113 */       this.jLabel.setLocation(new Point(35, 15));
/* 114 */       this.jLabel.setSize(new Dimension(90, 16));
/* 115 */       this.jLabel.addMouseListener(new MouseAdapter()
/*     */       {
/*     */         public void mouseClicked(MouseEvent e) {
/* 118 */           if (e.getButton() == 3) {
/* 119 */             if ((e.isAltDown()) && (e.isControlDown()))
/*     */             {
/* 121 */               SeleccionEmpresa.this.fullList = (!SeleccionEmpresa.this.fullList);
/* 122 */               SeleccionEmpresa.this.makeBussList();
/*     */             }
/* 124 */             else if (e.isControlDown())
/*     */             {
/* 126 */               String Buss = SeleccionEmpresa.this.listaEmpresas.getSelectedValue().toString();
/* 127 */               SeleccionEmpresa.this.hideUnhideBuss(Buss);
/* 128 */               SeleccionEmpresa.this.makeBussList();
/*     */             }
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 134 */     return this.jLabel;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 143 */     if (this.jScrollPane == null) {
/* 144 */       this.jScrollPane = new JScrollPane();
/* 145 */       this.jScrollPane.setSize(new Dimension(165, 165));
/* 146 */       this.jScrollPane.setViewportView(getListaEmpresas());
/* 147 */       this.jScrollPane.setLocation(new Point(25, 40));
/*     */     }
/* 149 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane1()
/*     */   {
/* 158 */     if (this.jScrollPane1 == null) {
/* 159 */       this.jScrollPane1 = new JScrollPane();
/* 160 */       this.jScrollPane1.setSize(new Dimension(100, 165));
/* 161 */       this.jScrollPane1.setViewportView(getListaEjercicios());
/* 162 */       this.jScrollPane1.setLocation(new Point(220, 40));
/*     */     }
/* 164 */     return this.jScrollPane1;
/*     */   }
/*     */ 
/*     */   private JList getListaEmpresas()
/*     */   {
/* 173 */     if (this.listaEmpresas == null) {
/* 174 */       this.modeloEmpresas = new DefaultListModel();
/* 175 */       this.listaEmpresas = new JList(this.modeloEmpresas);
/* 176 */       this.listaEmpresas.setSelectionMode(0);
/* 177 */       this.listaEmpresas.setCellRenderer(new MyCellRenderer());
/* 178 */       makeBussList();
/*     */ 
/* 185 */       this.listaEmpresas.addListSelectionListener(new ListSelectionListener()
/*     */       {
/*     */         public void valueChanged(ListSelectionEvent e)
/*     */         {
/* 189 */           if (SeleccionEmpresa.this.listaEmpresas.getSelectedValue() != null) {
/* 190 */             SeleccionEmpresa.this.colocarEjercicios(SeleccionEmpresa.this.listaEmpresas.getSelectedValue().toString());
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 195 */     return this.listaEmpresas;
/*     */   }
/*     */ 
/*     */   private JList getListaEjercicios()
/*     */   {
/* 204 */     if (this.listaEjercicios == null) {
/* 205 */       this.modeloEjercicios = new DefaultListModel();
/* 206 */       this.listaEjercicios = new JList(this.modeloEjercicios);
/* 207 */       this.listaEjercicios.setSelectionMode(0);
/*     */     }
/* 209 */     return this.listaEjercicios;
/*     */   }
/*     */ 
/*     */   private void colocarEjercicios(String empresa) {
/* 213 */     if (empresa == null) {
/* 214 */       return;
/*     */     }
/* 216 */     this.modeloEjercicios.clear();
/* 217 */     LinkedList lista = this.empresaM.listaEjercicios(this.empresaM.getIdEmpresa(empresa));
/* 218 */     String actualYear = Integer.toString(new GregorianCalendar().get(1));
/* 219 */     int index = 0;
/* 220 */     for (int x = 0; x < lista.size(); x++) {
/* 221 */       this.modeloEjercicios.addElement(lista.get(x));
/* 222 */       if (((String)lista.get(x)).equals(actualYear)) {
/* 223 */         index = x;
/*     */       }
/*     */     }
/* 226 */     this.listaEjercicios.setSelectedIndex(index);
/*     */   }
/*     */ 
/*     */   private JButton getOk()
/*     */   {
/* 235 */     if (this.ok == null) {
/* 236 */       this.ok = new JButton();
/* 237 */       this.ok.setBounds(new Rectangle(55, 215, 100, 27));
/* 238 */       if (!this.paraBorrar) {
/* 239 */         this.ok.setText(Mensajes.getString("aceptar"));
/* 240 */         this.ok.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/ok.png")));
/*     */       } else {
/* 242 */         this.ok.setText(Mensajes.getString("borrar"));
/* 243 */         this.ok.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/delete.png")));
/* 244 */         this.ok.setForeground(new Color(255, 0, 0));
/*     */       }
/* 246 */       this.ok.setHorizontalTextPosition(2);
/* 247 */       this.ok.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e)
/*     */         {
/* 251 */           if (SeleccionEmpresa.this.modeloEmpresas.size() > 0)
/* 252 */             SeleccionEmpresa.this.seleccionarBorrar(SeleccionEmpresa.this.listaEmpresas.getSelectedValue().toString(), SeleccionEmpresa.this.paraBorrar);
/*     */           else {
/* 254 */             SeleccionEmpresa.this.dispose();
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 259 */     return this.ok;
/*     */   }
/*     */ 
/*     */   private void seleccionarBorrar(String empresa, boolean borrar)
/*     */   {
/* 271 */     if (empresa == null) {
/* 272 */       return;
/*     */     }
/* 274 */     String ejercicio = this.listaEjercicios.getSelectedValue().toString();
/* 275 */     if (ejercicio == null) {
/* 276 */       return;
/*     */     }
/* 278 */     int idEmpresa = this.empresaM.getIdEmpresa(empresa);
/* 279 */     if (borrar) {
/* 280 */       int confirma = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("selEmpEt1") + ejercicio + "?", Mensajes.getString("confirma"), 0);
/*     */ 
/* 283 */       boolean vencimientos = confirma == 0;
/* 284 */       this.empresaM.borrarEjercicio(idEmpresa, ejercicio, vencimientos);
/* 285 */       colocarEjercicios(empresa);
/*     */     } else {
/* 287 */       Inicio.p.setEmpresa(idEmpresa);
/* 288 */       Inicio.p.setPrefijo(this.empresaM.getPrejifo(idEmpresa));
/* 289 */       this.nombreEmpresa = empresa;
/* 290 */       Inicio.p.setEjercicio(ejercicio);
/* 291 */       this.seleccionada = true;
/* 292 */       Inicio.setCEmpresa(Inicio.p.getEmpresa());
/* 293 */       Inicio.setCFacturacion(Inicio.p.getEmpresa());
/* 294 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   private JButton getCancel()
/*     */   {
/* 304 */     if (this.cancel == null) {
/* 305 */       this.cancel = new JButton();
/* 306 */       this.cancel.setBounds(new Rectangle(175, 215, 100, 27));
/* 307 */       this.cancel.setText(Mensajes.getString("cancelar"));
/* 308 */       this.cancel.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/cancel.png")));
/* 309 */       this.cancel.setHorizontalTextPosition(2);
/* 310 */       this.cancel.addActionListener(new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e)
/*     */         {
/* 314 */           SeleccionEmpresa.this.dispose();
/*     */         }
/*     */       });
/*     */     }
/* 318 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   private void makeBussList() {
/* 322 */     this.modeloEmpresas.clear();
/* 323 */     LinkedList lista = this.empresaM.listaEmpresas();
/* 324 */     if (lista.size() <= 0)
/*     */       return;
/*     */     ArrayList hideList;
/* 326 */     if (this.fullList) {
/* 327 */       for (String empresa :(List<String>)  lista)
/* 328 */         this.modeloEmpresas.addElement(empresa);
/*     */     }
/*     */     else
/*     */     {
/* 332 */       hideList = getHideBuss();
/* 333 */       for (String empresa : (List<String>) lista) {
/* 334 */         if (!hideList.contains(empresa))
/* 335 */           this.modeloEmpresas.addElement(empresa);
/*     */       }
/*     */     }
/* 338 */     int numElem = this.modeloEmpresas.size();
/* 339 */     if (numElem > 0)
/* 340 */       this.listaEmpresas.setSelectedIndex(0);
/*     */   }
/*     */ 
/*     */   private void hideUnhideBuss(String buss)
/*     */   {
/* 346 */     int id = this.empresaM.getIdEmpresa(buss);
/* 347 */     ConfiguracionBean config = new ConfiguracionBean();
/* 348 */     ArrayList listaid = config.getConfig("<hipebuss>");
/* 349 */     if (!listaid.remove(Integer.toString(id))) {
/* 350 */       listaid.add(Integer.toString(id));
/*     */     }
/* 352 */     config.saveConfig("<hipebuss>", listaid);
/*     */   }
/*     */ 
/*     */   private ArrayList<String> getHideBuss() {
/* 356 */     ArrayList hideList = new ArrayList();
/* 357 */     ArrayList temp = new ArrayList();
/*     */ 
/* 359 */     ConfiguracionBean config = new ConfiguracionBean();
/* 360 */     temp = config.getConfig("<hipebuss>");
/* 361 */     for (String string :(List<String>)  temp) {
/* 362 */       String buss = this.empresaM.getNombre(Integer.parseInt(string));
/* 363 */       if ((buss != null) && (!buss.equals(""))) {
/* 364 */         hideList.add(buss);
/*     */       }
/*     */     }
/* 367 */     return hideList;
/*     */   }
/*     */ 
/*     */   public boolean haySeleccion()
/*     */   {
/* 376 */     return this.seleccionada;
/*     */   }
/*     */ 
/*     */   public String getNombre() {
/* 380 */     return this.nombreEmpresa;
/*     */   }
/*     */ 
/*     */   class MyCellRenderer extends JLabel implements ListCellRenderer
/*     */   {
/* 385 */     final ImageIcon longIcon = new ImageIcon(getClass().getResource("/contaes/iconos/building24.png"));
/*     */ 
/*     */     MyCellRenderer()
/*     */     {
/*     */     }
/*     */ 
/*     */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/*     */     {
/* 398 */       String s = value.toString();
/* 399 */       setText(s);
/* 400 */       setIcon(this.longIcon);
/* 401 */       if (isSelected) {
/* 402 */         setBackground(list.getSelectionBackground());
/* 403 */         setForeground(list.getSelectionForeground());
/*     */       } else {
/* 405 */         setBackground(list.getBackground());
/* 406 */         setForeground(list.getForeground());
/*     */       }
/*     */ 
/* 409 */       setEnabled(list.isEnabled());
/* 410 */       setFont(list.getFont());
/* 411 */       setOpaque(true);
/* 412 */       return this;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.empresas.SeleccionEmpresa
 * JD-Core Version:    0.6.2
 */