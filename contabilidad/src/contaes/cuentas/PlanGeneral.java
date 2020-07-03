/*     */ package contaes.cuentas;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.auxiliar.ArbolRenderer;
/*     */ import contaes.manejoDatos.TipoCuenta;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import javax.swing.tree.TreeSelectionModel;
/*     */ 
/*     */ public class PlanGeneral extends JPanel
/*     */ {
/*  55 */   private JPanel jPanel = null;
/*  56 */   private JButton bCrear = null;
/*  57 */   private JScrollPane jScrollPane = null;
/*  58 */   private JTree arbol = null;
/*  59 */   private JPopupMenu menuEmergente = new JPopupMenu();
/*  60 */   private JMenuItem jMenuItem = null;
/*  61 */   private Acciones escuchaAccion = null;
/*  62 */   int inset = 124;
/*  63 */   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  64 */   int ancho_padre = this.screenSize.width - this.inset * 2;
/*     */ 
/*     */   public PlanGeneral()
/*     */   {
/*  71 */     initialize();
/*     */   }
/*     */ 
/*     */   private void initialize()
/*     */   {
/*  80 */     this.escuchaAccion = new Acciones();
/*  81 */     this.menuEmergente.add(getJMenuItem());
/*  82 */     setLayout(new BorderLayout());
/*  83 */     setSize(582, 380);
/*  84 */     add(getJPanel(), "South");
/*  85 */     add(getJScrollPane(), "Center");
/*     */   }
/*     */ 
/*     */   private JPanel getJPanel()
/*     */   {
/*  94 */     if (this.jPanel == null) {
/*  95 */       this.jPanel = new JPanel();
/*     */ 
/*  97 */       this.jPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
/*  98 */       this.jPanel.add(getBCrear());
/*     */     }
/* 100 */     return this.jPanel;
/*     */   }
/*     */ 
/*     */   private JButton getBCrear()
/*     */   {
/* 109 */     if (this.bCrear == null) {
/* 110 */       this.bCrear = new JButton();
/* 111 */       this.bCrear.setText(Mensajes.getString("crear") + " " + Mensajes.getString("subcuenta"));
/* 112 */       this.bCrear.setHorizontalTextPosition(2);
/* 113 */       this.bCrear.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/* 114 */       this.bCrear.addActionListener(this.escuchaAccion);
/*     */     }
/* 116 */     return this.bCrear;
/*     */   }
/*     */ 
/*     */   private JMenuItem getJMenuItem() {
/* 120 */     if (this.jMenuItem == null) {
/* 121 */       this.jMenuItem = new JMenuItem();
/* 122 */       this.jMenuItem.setText(Mensajes.getString("crear") + " " + Mensajes.getString("subcuenta"));
/* 123 */       this.jMenuItem.setIcon(new ImageIcon(getClass().getResource("/contaes/iconos/new.png")));
/* 124 */       this.jMenuItem.addActionListener(this.escuchaAccion);
/*     */     }
/* 126 */     return this.jMenuItem;
/*     */   }
/*     */ 
/*     */   private JScrollPane getJScrollPane()
/*     */   {
/* 136 */     if (this.jScrollPane == null) {
/* 137 */       this.jScrollPane = new JScrollPane();
/* 138 */       this.jScrollPane.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createMatteBorder(10, 10, 10, 10, Color.white)));
/*     */ 
/* 140 */       this.jScrollPane.setViewportView(getArbol());
/*     */     }
/* 142 */     return this.jScrollPane;
/*     */   }
/*     */ 
/*     */   private JTree getArbol() {
/* 146 */     if (this.arbol == null) {
/* 147 */       this.arbol = Inicio.getArbolPGC();
/* 148 */       this.arbol.setCellRenderer(new ArbolRenderer());
/* 149 */       this.arbol.getSelectionModel().setSelectionMode(1);
/* 150 */       this.arbol.addMouseListener(new MouseAdapter()
/*     */       {
/*     */         public void mouseClicked(MouseEvent e)
/*     */         {
/* 154 */           if (e.getButton() == 3) {
/* 155 */             PlanGeneral.this.arbol.setSelectionRow(PlanGeneral.this.arbol.getRowForLocation(e.getX(), e.getY()));
/* 156 */             PlanGeneral.this.menuEmergente.show(e.getComponent(), e.getX(), e.getY());
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/*     */ 
/* 162 */     return this.arbol;
/*     */   }
/*     */ 
/*     */   private void creaCuenta()
/*     */   {
/* 171 */     CrearSubcuenta dlg = new CrearSubcuenta(Inicio.frame, Mensajes.getString("crear") + " " + Mensajes.getString("subcuenta"), true, true);
/* 172 */     TipoCuenta cuenta = nodoSeleccionado();
/* 173 */     if (cuenta != null)
/*     */     {
/* 175 */       String codigo = String.valueOf(cuenta.getCodigo());
/* 176 */       String nombre = cuenta.getNombre();
/* 177 */       String gBal = cuenta.getGbalance();
/* 178 */       dlg.datos(codigo, nombre, gBal);
/*     */     }
/* 180 */     Dimension dlgSize = dlg.getPreferredSize();
/* 181 */     Dimension frmSize = getSize();
/* 182 */     Point loc = getLocation();
/* 183 */     dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
/*     */ 
/* 185 */     dlg.setModal(true);
/* 186 */     dlg.setVisible(true);
/* 187 */     String creada = dlg.getSubcuenta();
/* 188 */     if (creada != null) {
/* 189 */       String grupo = creada.substring(0, 2);
/* 190 */       if (grupo.equals("57")) {
/* 191 */         Inicio.frame.renovarModeloTesoreria();
/*     */       }
/* 193 */       if ((grupo.equals("40")) || (grupo.equals("41")) || (grupo.equals("43")) || (grupo.equals("44"))) {
/* 194 */         if (grupo.equals("40")) {
/* 195 */           Inicio.frame.renovarModeloProveedoresFacturacion();
/*     */         }
/* 197 */         else if (grupo.equals("43")) {
/* 198 */           Inicio.frame.renovarModeloClientesFacturacion();
/* 199 */           Inicio.frame.renovarModeloClientesPedidos();
/*     */         }
/* 201 */         Inicio.frame.activarAgendaPanelCuentas(creada, true);
/*     */       }
/*     */       else {
/* 204 */         Inicio.frame.activarAgendaPanelCuentas(creada, false);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private TipoCuenta nodoSeleccionado()
/*     */   {
/* 220 */     if (!this.arbol.isSelectionEmpty()) {
/* 221 */       DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)this.arbol.getLastSelectedPathComponent();
/*     */ 
/* 223 */       if (!nodo.isRoot()) {
/* 224 */         return (TipoCuenta)nodo.getUserObject();
/*     */       }
/*     */     }
/* 227 */     this.arbol.setSelectionRow(1);
/* 228 */     return nodoSeleccionado();
/*     */   }
/*     */ 
/*     */   public String cuentaSeleccionada()
/*     */   {
/* 236 */     TipoCuenta nodo = nodoSeleccionado();
/* 237 */     if (nodo != null) {
/* 238 */       return String.valueOf(nodo.getCodigo());
/*     */     }
/* 240 */     return "Error";
/*     */   }
/*     */ 
/*     */   public String nombreSeleccionado()
/*     */   {
/* 248 */     TipoCuenta nodo = nodoSeleccionado();
/* 249 */     if (nodo != null) {
/* 250 */       return nodo.getNombre();
/*     */     }
/* 252 */     return "Error";
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
/* 212 */       Object campo = e.getSource();
/* 213 */       if ((campo == PlanGeneral.this.bCrear) || (campo == PlanGeneral.this.jMenuItem))
/* 214 */         PlanGeneral.this.creaCuenta();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.cuentas.PlanGeneral
 * JD-Core Version:    0.6.2
 */