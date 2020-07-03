/*     */ package contaes;
/*     */ 
/*     */ import contaes.auxiliar.DocTreintaCarac;
/*     */ import contaes.manejoDatos.ManejoConceptos;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.List;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ import javax.swing.event.InternalFrameAdapter;
/*     */ import javax.swing.event.InternalFrameEvent;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ 
/*     */ public class Conceptos extends JInternalFrame
/*     */   implements ActionListener, ListSelectionListener
/*     */ {
/*     */   private static final String CREAR = "crear";
/*     */   private static final String MODIFICAR = "modificar";
/*     */   private static final String BORRAR = "borrar";
/*  60 */   JPanel panel1 = new JPanel();
/*     */   JScrollPane panel2;
/*  62 */   JPanel panel3 = new JPanel();
/*  63 */   BorderLayout bLayout = new BorderLayout();
/*  64 */   BorderLayout bLayout1 = new BorderLayout();
/*  65 */   GridBagLayout gbLayout1 = new GridBagLayout();
/*     */   JList listaConceptos;
/*  67 */   DefaultListModel modeloLista = new DefaultListModel();
/*  68 */   List listaId = new List();
/*  69 */   JTextField concepto = new JTextField();
/*  70 */   JButton crear = new JButton();
/*  71 */   JButton modificar = new JButton();
/*  72 */   JButton borrar = new JButton();
/*  73 */   ImageIcon iconoCrear = createImageIcon("/contaes/iconos/new.png");
/*  74 */   ImageIcon iconoModificar = createImageIcon("/contaes/iconos/edit.png");
/*  75 */   ImageIcon iconoBorrar = createImageIcon("/contaes/iconos/delete.png");
/*     */ 
/*     */   public Conceptos() {
/*  78 */     super(Mensajes.getString("conceptos"), true, true, true, true);
/*     */     try
/*     */     {
/*  85 */       setDefaultCloseOperation(2);
/*  86 */       initialize();
/*     */     } catch (Exception exception) {
/*  88 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initialize() throws Exception
/*     */   {
/*  94 */     addInternalFrameListener(new InternalFrameAdapter()
/*     */     {
/*     */       public void internalFrameClosed(InternalFrameEvent e)
/*     */       {
/*  98 */         Inicio.frame.eliminarMarcoMenu(Mensajes.getString("conceptos"));
/*     */       }
/*     */     });
/* 102 */     setFrameIcon(new ImageIcon(getClass().getResource("/contaes/iconos/Co18.png")));
/* 103 */     int inset = 160;
/* 104 */     setBounds(inset / 2, inset / 2, 400, Inicio.frame.getHeight() - (inset + 20));
/*     */ 
/* 106 */     construirListas();
/* 107 */     this.listaConceptos = new JList(this.modeloLista);
/* 108 */     this.listaConceptos.setSelectionMode(0);
/* 109 */     this.listaConceptos.addListSelectionListener(this);
/* 110 */     this.concepto.setDocument(new DocTreintaCarac());
/* 111 */     this.crear.setText(Mensajes.getString("nuevo"));
/* 112 */     this.crear.setIcon(this.iconoCrear);
/* 113 */     this.crear.setVerticalTextPosition(0);
/* 114 */     this.crear.setHorizontalTextPosition(2);
/* 115 */     this.crear.setActionCommand("crear");
/* 116 */     this.crear.addActionListener(this);
/* 117 */     this.modificar.setText(Mensajes.getString("modificar"));
/* 118 */     this.modificar.setIcon(this.iconoModificar);
/* 119 */     this.modificar.setVerticalTextPosition(0);
/* 120 */     this.modificar.setHorizontalTextPosition(2);
/* 121 */     this.modificar.setActionCommand("modificar");
/* 122 */     this.modificar.addActionListener(this);
/* 123 */     this.borrar.setText(Mensajes.getString("borrar"));
/* 124 */     this.borrar.setIcon(this.iconoBorrar);
/* 125 */     this.borrar.setVerticalTextPosition(0);
/* 126 */     this.borrar.setHorizontalTextPosition(2);
/* 127 */     this.borrar.setActionCommand("borrar");
/* 128 */     this.borrar.addActionListener(this);
/*     */ 
/* 130 */     this.panel2 = new JScrollPane(this.listaConceptos);
/* 131 */     this.panel2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25), new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createMatteBorder(5, 5, 5, 5, Color.white))));
/*     */ 
/* 135 */     this.panel3.setLayout(this.gbLayout1);
/* 136 */     GridBagConstraints cons = new GridBagConstraints();
/* 137 */     cons.insets.top = 5;
/* 138 */     cons.insets.bottom = 5;
/* 139 */     cons.insets.left = 5;
/* 140 */     cons.insets.right = 5;
/* 141 */     cons.gridx = 0;
/* 142 */     cons.gridy = 0;
/* 143 */     cons.fill = 2;
/* 144 */     cons.weightx = 1.0D;
/* 145 */     cons.gridwidth = 2;
/* 146 */     this.gbLayout1.setConstraints(this.concepto, cons);
/* 147 */     this.panel3.add(this.concepto);
/* 148 */     cons.fill = 0;
/* 149 */     cons.weightx = 0.0D;
/* 150 */     cons.gridwidth = 1;
/* 151 */     cons.gridy = 1;
/* 152 */     cons.gridx = 0;
/* 153 */     this.gbLayout1.setConstraints(this.crear, cons);
/* 154 */     this.panel3.add(this.crear);
/* 155 */     cons.gridx = 1;
/* 156 */     this.gbLayout1.setConstraints(this.modificar, cons);
/* 157 */     this.panel3.add(this.modificar);
/* 158 */     cons.gridx = 2;
/* 159 */     this.gbLayout1.setConstraints(this.borrar, cons);
/* 160 */     this.panel3.add(this.borrar);
/*     */ 
/* 162 */     this.panel1.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
/*     */ 
/* 164 */     this.panel1.setLayout(this.bLayout1);
/* 165 */     this.panel1.add(this.panel2, "Center");
/* 166 */     this.panel1.add(this.panel3, "Last");
/* 167 */     getContentPane().setLayout(this.bLayout);
/* 168 */     getContentPane().add(this.panel1, "Center");
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e) {
/* 172 */     String cmd = e.getActionCommand();
/*     */ 
/* 174 */     if ("crear".equals(cmd)) {
/* 175 */       ManejoConceptos.crear(this.concepto.getText());
/* 176 */     } else if ("modificar".equals(cmd)) {
/* 177 */       int ind = this.listaConceptos.getSelectedIndex();
/* 178 */       int ID = Integer.parseInt(this.listaId.getItem(ind));
/* 179 */       ManejoConceptos.modificar(ID, this.concepto.getText());
/* 180 */     } else if ("borrar".equals(cmd)) {
/* 181 */       int ind = this.listaConceptos.getSelectedIndex();
/* 182 */       int ID = Integer.parseInt(this.listaId.getItem(ind));
/* 183 */       ManejoConceptos.borrar(ID);
/*     */     }
/* 185 */     construirListas();
/*     */   }
/*     */ 
/*     */   public void valueChanged(ListSelectionEvent e)
/*     */   {
/* 192 */     if ((!e.getValueIsAdjusting()) && 
/* 193 */       (this.listaConceptos.getSelectedIndex() != -1))
/*     */     {
/* 197 */       this.concepto.setText(this.listaConceptos.getSelectedValue().toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void construirListas()
/*     */   {
/* 207 */     LinkedList id = new LinkedList();
/* 208 */     id.addAll(ManejoConceptos.listaID());
/* 209 */     LinkedList descrips = new LinkedList();
/* 210 */     descrips.addAll(ManejoConceptos.listaDescripcion());
/* 211 */     this.modeloLista.removeAllElements();
/* 212 */     this.listaId.removeAll();
/* 213 */     for (Iterator i$ = id.iterator(); i$.hasNext(); ) { int y = ((Integer)i$.next()).intValue();
/* 214 */       this.listaId.add(String.valueOf(y));
/*     */     }
/* 216 */     for (Object x : descrips)
/* 217 */       this.modeloLista.addElement(x);
/*     */   }
/*     */ 
/*     */   protected static ImageIcon createImageIcon(String path)
/*     */   {
/* 222 */     URL imgURL = Asientos.class.getResource(path);
/* 223 */     if (imgURL != null) {
/* 224 */       return new ImageIcon(imgURL);
/*     */     }
/* 226 */     System.err.println("Couldn't find file: " + path);
/* 227 */     return null;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.Conceptos
 * JD-Core Version:    0.6.2
 */