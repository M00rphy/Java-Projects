/*     */ package contaes.asientosModelo;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.manejoDatos.ManejoAsientosModelo;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.List;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ 
/*     */ public class Lista extends JInternalFrame
/*     */   implements ActionListener
/*     */ {
/*     */   private static final String CREAR = "crear";
/*     */   private static final String EDITAR = "editar";
/*     */   private static final String BORRAR = "borrar";
/*     */   private static final String USAR = "usar";
/*     */   private ManejoAsientosModelo manejoAM;
/*  66 */   JPanel panel1 = new JPanel();
/*     */   JScrollPane panel2;
/*  68 */   JPanel panel3 = new JPanel();
/*  69 */   BorderLayout bLayout = new BorderLayout();
/*  70 */   BorderLayout bLayout1 = new BorderLayout();
/*  71 */   GridBagLayout gbLayout1 = new GridBagLayout();
/*     */   JList listaConceptos;
/*  73 */   DefaultListModel modeloLista = new DefaultListModel();
/*  74 */   List listaId = new List();
/*  75 */   JButton crear = new JButton();
/*  76 */   JButton modificar = new JButton();
/*  77 */   JButton borrar = new JButton();
/*  78 */   JButton usar = new JButton();
/*  79 */   JPopupMenu menuEmergente = new JPopupMenu();
/*  80 */   JMenuItem menuCrear = new JMenuItem();
/*  81 */   JMenuItem menuModificar = new JMenuItem();
/*  82 */   JMenuItem menuBorrar = new JMenuItem();
/*  83 */   JMenuItem menuUsar = new JMenuItem();
/*  84 */   ImageIcon iconoCrear = createImageIcon("/contaes/iconos/new.png");
/*  85 */   ImageIcon iconoEditar = createImageIcon("/contaes/iconos/edit.png");
/*  86 */   ImageIcon iconoBorrar = createImageIcon("/contaes/iconos/delete.png");
/*  87 */   ImageIcon iconoUsar = createImageIcon("/contaes/iconos/ok.png");
/*  88 */   ImageIcon iconoCancelar = createImageIcon("/contaes/iconos/cancel.png");
/*     */ 
/*     */   public Lista() {
/*  91 */     super(Mensajes.getString("astoModel"), true, true, true, true);
/*  92 */     this.manejoAM = new ManejoAsientosModelo(Inicio.getCGeneral());
/*     */     try {
/*  94 */       setDefaultCloseOperation(2);
/*  95 */       initialize();
/*  96 */       pack();
/*     */     } catch (Exception exception) {
/*  98 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initialize() throws Exception {
/* 103 */     setFrameIcon(new ImageIcon(getClass().getResource("/contaes/iconos/Am18.png")));
/* 104 */     setBounds(80, 5, getPreferredSize().width, getPreferredSize().height);
/* 105 */     renovarTabla();
/* 106 */     this.listaConceptos = new JList(this.modeloLista);
/* 107 */     this.listaConceptos.setSelectionMode(0);
/* 108 */     this.listaConceptos.addMouseListener(new MouseAdapter()
/*     */     {
/*     */       public void mouseClicked(MouseEvent e) {
/* 111 */         if ((e.getClickCount() == 2) && (e.getButton() == 1))
/*     */         {
/* 113 */           Lista.this.utilizarAsiento(Lista.this.listaConceptos.getSelectedIndex());
/* 114 */         } else if (e.getButton() == 3) {
/* 115 */           Lista.this.listaConceptos.setSelectedIndex(Lista.this.listaConceptos.locationToIndex(new Point(e.getX(), e.getY())));
/*     */ 
/* 117 */           Lista.this.menuEmergente.show(e.getComponent(), e.getX(), e.getY());
/*     */         }
/*     */       }
/*     */     });
/* 122 */     this.crear.setText(Mensajes.getString("nuevo"));
/* 123 */     this.crear.setIcon(this.iconoCrear);
/* 124 */     this.crear.setVerticalTextPosition(0);
/* 125 */     this.crear.setHorizontalTextPosition(2);
/* 126 */     this.crear.setActionCommand("crear");
/* 127 */     this.crear.addActionListener(this);
/* 128 */     this.menuCrear.setText(Mensajes.getString("nuevo"));
/* 129 */     this.menuCrear.setIcon(this.iconoCrear);
/* 130 */     this.menuCrear.setActionCommand("crear");
/* 131 */     this.menuCrear.addActionListener(this);
/* 132 */     this.modificar.setText(Mensajes.getString("editar"));
/* 133 */     this.modificar.setIcon(this.iconoEditar);
/* 134 */     this.modificar.setVerticalTextPosition(0);
/* 135 */     this.modificar.setHorizontalTextPosition(2);
/* 136 */     this.modificar.setActionCommand("editar");
/* 137 */     this.modificar.addActionListener(this);
/* 138 */     this.menuModificar.setText(Mensajes.getString("editar"));
/* 139 */     this.menuModificar.setIcon(this.iconoEditar);
/* 140 */     this.menuModificar.setActionCommand("editar");
/* 141 */     this.menuModificar.addActionListener(this);
/* 142 */     this.borrar.setText(Mensajes.getString("borrar"));
/* 143 */     this.borrar.setIcon(this.iconoBorrar);
/* 144 */     this.borrar.setVerticalTextPosition(0);
/* 145 */     this.borrar.setHorizontalTextPosition(2);
/* 146 */     this.borrar.setActionCommand("borrar");
/* 147 */     this.borrar.addActionListener(this);
/* 148 */     this.menuBorrar.setText(Mensajes.getString("borrar"));
/* 149 */     this.menuBorrar.setIcon(this.iconoBorrar);
/* 150 */     this.menuBorrar.setActionCommand("borrar");
/* 151 */     this.menuBorrar.addActionListener(this);
/* 152 */     this.usar.setText(Mensajes.getString("utilizar"));
/* 153 */     this.usar.setIcon(this.iconoUsar);
/* 154 */     this.usar.setVerticalTextPosition(0);
/* 155 */     this.usar.setHorizontalTextPosition(2);
/* 156 */     this.usar.setActionCommand("usar");
/* 157 */     this.usar.addActionListener(this);
/* 158 */     this.menuUsar.setText(Mensajes.getString("utilizar"));
/* 159 */     this.menuUsar.setIcon(this.iconoUsar);
/* 160 */     this.menuUsar.setActionCommand("usar");
/* 161 */     this.menuUsar.addActionListener(this);
/*     */ 
/* 163 */     this.menuEmergente.add(this.menuCrear);
/* 164 */     this.menuEmergente.add(this.menuModificar);
/* 165 */     this.menuEmergente.add(this.menuBorrar);
/* 166 */     this.menuEmergente.add(this.menuUsar);
/*     */ 
/* 168 */     this.panel2 = new JScrollPane(this.listaConceptos);
/* 169 */     this.panel2.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
/*     */ 
/* 172 */     this.panel3.setLayout(this.gbLayout1);
/* 173 */     GridBagConstraints cons = new GridBagConstraints();
/* 174 */     cons.insets.top = 5;
/* 175 */     cons.insets.bottom = 5;
/* 176 */     cons.insets.left = 5;
/* 177 */     cons.insets.right = 5;
/* 178 */     cons.gridx = 0;
/* 179 */     cons.gridy = 0;
/* 180 */     this.gbLayout1.setConstraints(this.crear, cons);
/* 181 */     this.panel3.add(this.crear);
/* 182 */     cons.gridx = 1;
/* 183 */     this.gbLayout1.setConstraints(this.modificar, cons);
/* 184 */     this.panel3.add(this.modificar);
/* 185 */     cons.gridx = 2;
/* 186 */     this.gbLayout1.setConstraints(this.borrar, cons);
/* 187 */     this.panel3.add(this.borrar);
/* 188 */     cons.gridy = 1;
/* 189 */     cons.gridx = 0;
/* 190 */     cons.gridwidth = 2;
/* 191 */     cons.insets.right = 30;
/* 192 */     this.gbLayout1.setConstraints(this.usar, cons);
/* 193 */     this.panel3.add(this.usar);
/* 194 */     this.panel3.setBorder(BorderFactory.createEtchedBorder(1));
/*     */ 
/* 196 */     this.panel1.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
/*     */ 
/* 198 */     this.panel1.setLayout(this.bLayout1);
/* 199 */     this.panel1.add(this.panel2, "Center");
/* 200 */     this.panel1.add(this.panel3, "Last");
/* 201 */     getContentPane().setLayout(this.bLayout);
/* 202 */     getContentPane().add(this.panel1, "Center");
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e) {
/* 206 */     String cmd = e.getActionCommand();
/*     */ 
/* 208 */     if ("crear".equals(cmd))
/*     */     {
/* 210 */       Editar dlg = new Editar(Inicio.frame);
/* 211 */       mostrarDialogo(dlg);
/* 212 */     } else if ("editar".equals(cmd)) {
/* 213 */       int ind = this.listaConceptos.getSelectedIndex();
/* 214 */       if (ind != -1) {
/* 215 */         int ID = Integer.parseInt(this.listaId.getItem(ind));
/* 216 */         Editar dlg = new Editar(Inicio.frame, ID);
/* 217 */         mostrarDialogo(dlg);
/*     */       }
/* 219 */     } else if ("borrar".equals(cmd)) {
/* 220 */       int ind = this.listaConceptos.getSelectedIndex();
/* 221 */       if (ind != -1) {
/* 222 */         int ID = Integer.parseInt(this.listaId.getItem(ind));
/* 223 */         int confirmar = JOptionPane.showConfirmDialog(getContentPane(), Mensajes.getString("dlgConfElimAsto"), Mensajes.getString("confirma"), 0);
/*     */ 
/* 226 */         if (confirmar == 0) {
/* 227 */           borrar(ID);
/* 228 */           renovarTabla();
/*     */         }
/*     */       }
/* 231 */     } else if ("usar".equals(cmd)) {
/* 232 */       utilizarAsiento(this.listaConceptos.getSelectedIndex());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void utilizarAsiento(int indice) {
/* 237 */     if (indice != -1) {
/* 238 */       int ID = Integer.parseInt(this.listaId.getItem(indice));
/* 239 */       Utilizar dlg = new Utilizar(Inicio.frame, ID);
/* 240 */       mostrarDialogo(dlg);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void renovarTabla() {
/* 245 */     this.modeloLista.removeAllElements();
/* 246 */     this.listaId.removeAll();
/* 247 */     String[][] lista = this.manejoAM.listaAsientos();
/* 248 */     for (int x = 0; x < lista[1].length; x++) {
/* 249 */       this.listaId.add(lista[0][x]);
/* 250 */       this.modeloLista.addElement(lista[1][x]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void borrar(int id) {
/* 255 */     this.manejoAM.borrarAsiento(id);
/*     */   }
/*     */ 
/*     */   private static ImageIcon createImageIcon(String path) {
/* 259 */     URL imgURL = Lista.class.getResource(path);
/* 260 */     if (imgURL != null) {
/* 261 */       return new ImageIcon(imgURL);
/*     */     }
/* 263 */     System.err.println("Couldn't find file: " + path);
/* 264 */     return null;
/*     */   }
/*     */ 
/*     */   private void mostrarDialogo(JDialog dlg) {
/* 268 */     Dimension dlgSize = dlg.getSize();
/* 269 */     Dimension frmSize = Inicio.frame.getSize();
/* 270 */     Point loc = getLocation();
/* 271 */     dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*     */ 
/* 274 */     dlg.setVisible(true);
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.asientosModelo.Lista
 * JD-Core Version:    0.6.2
 */