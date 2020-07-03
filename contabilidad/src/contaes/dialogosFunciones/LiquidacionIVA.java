/*     */ package contaes.dialogosFunciones;
/*     */ 
/*     */ import contaes.Inicio;
/*     */ import contaes.MarcoInicio;
/*     */ import contaes.Puente;
/*     */ import contaes.manejoDatos.funciones.LiquidacionTrimIVA;
/*     */ import internationalization.Mensajes;
/*     */ import java.awt.Container;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class LiquidacionIVA extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private static final String ACEPTAR = "aceptar";
/*     */   private static final String CANCELAR = "cancelar";
/*     */   private static final String CAJA = "opciones";
/*     */   private LiquidacionTrimIVA clase;
/*  59 */   private int seleccion = -1;
/*     */ 
/*  61 */   JLabel eTrimestre = new JLabel(Mensajes.getString("trimestre"));
/*  62 */   JLabel eReper = new JLabel(Mensajes.getString("IVAs") + " " + Mensajes.getString("repercutido"));
/*  63 */   JLabel eSopor = new JLabel(Mensajes.getString("IVAs") + " " + Mensajes.getString("soportado"));
/*  64 */   JLabel eSaldo = new JLabel(Mensajes.getString("diferencia"));
/*  65 */   String[] trimestres = { Mensajes.getString("primero"), Mensajes.getString("segundo"), Mensajes.getString("tercero"), Mensajes.getString("cuarto") };
/*  66 */   JComboBox trimestre = new JComboBox(this.trimestres);
/*  67 */   JTextField cReper = new JTextField();
/*  68 */   JTextField cSopor = new JTextField();
/*  69 */   JTextField cSaldo = new JTextField();
/*  70 */   JButton crear = new JButton();
/*  71 */   JButton cancelar = new JButton();
/*  72 */   JPanel panel = new JPanel();
/*  73 */   GridBagLayout gbLayout = new GridBagLayout();
/*     */ 
/*  75 */   ImageIcon iconoOK = createImageIcon("/contaes/iconos/ok.png");
/*  76 */   ImageIcon iconoCancel = createImageIcon("/contaes/iconos/cancel.png");
/*     */   DecimalFormat fn;
/*     */ 
/*     */   public LiquidacionIVA(Frame owner, String title, boolean modal)
/*     */   {
/*  80 */     super(owner, title, modal);
/*     */     try {
/*  82 */       this.clase = new LiquidacionTrimIVA(Inicio.getCEmpresa(), Inicio.p.getEjercicio());
/*  83 */       setDefaultCloseOperation(2);
/*  84 */       initialize();
/*  85 */       pack();
/*     */     }
/*     */     catch (Exception exception) {
/*  88 */       exception.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initialize() throws Exception {
/*  93 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  94 */     formato.setDecimalSeparator(',');
/*  95 */     formato.setPerMill('.');
/*  96 */     this.fn = new DecimalFormat("#,###,##0.00", formato);
/*     */ 
/*  98 */     this.crear.setText(Mensajes.getString("crear") + " " + Mensajes.getString("asiento"));
/*  99 */     this.crear.setIcon(this.iconoOK);
/* 100 */     this.crear.setVerticalTextPosition(0);
/* 101 */     this.crear.setHorizontalTextPosition(2);
/* 102 */     this.crear.setActionCommand("aceptar");
/* 103 */     this.crear.addActionListener(this);
/* 104 */     this.cancelar.setText(Mensajes.getString("cancelar"));
/* 105 */     this.cancelar.setIcon(this.iconoCancel);
/* 106 */     this.cancelar.setVerticalTextPosition(0);
/* 107 */     this.cancelar.setHorizontalTextPosition(2);
/* 108 */     this.cancelar.setActionCommand("cancelar");
/* 109 */     this.cancelar.addActionListener(this);
/* 110 */     this.trimestre.setActionCommand("opciones");
/* 111 */     this.trimestre.addActionListener(this);
/* 112 */     this.cReper.setHorizontalAlignment(4);
/* 113 */     this.cSopor.setHorizontalAlignment(4);
/* 114 */     this.cSaldo.setHorizontalAlignment(4);
/* 115 */     this.panel.setLayout(this.gbLayout);
/* 116 */     this.panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(1), BorderFactory.createEmptyBorder(5, 5, 5, 5))));
/*     */ 
/* 119 */     GridBagConstraints cons = new GridBagConstraints();
/* 120 */     cons.insets.top = 5;
/* 121 */     cons.insets.bottom = 10;
/* 122 */     cons.insets.left = 10;
/* 123 */     cons.insets.right = 10;
/* 124 */     cons.gridy = 0;
/* 125 */     cons.gridx = 0;
/* 126 */     this.gbLayout.setConstraints(this.eTrimestre, cons);
/* 127 */     this.panel.add(this.eTrimestre);
/* 128 */     cons.gridx = 1;
/* 129 */     cons.weighty = 1.0D;
/* 130 */     this.gbLayout.setConstraints(this.trimestre, cons);
/* 131 */     this.panel.add(this.trimestre);
/* 132 */     cons.insets.bottom = 5;
/* 133 */     cons.weighty = 0.0D;
/* 134 */     cons.gridy = 1;
/* 135 */     cons.gridx = 0;
/* 136 */     this.gbLayout.setConstraints(this.eReper, cons);
/* 137 */     this.panel.add(this.eReper);
/* 138 */     cons.gridx = 1;
/* 139 */     this.gbLayout.setConstraints(this.eSopor, cons);
/* 140 */     this.panel.add(this.eSopor);
/* 141 */     cons.weighty = 1.0D;
/* 142 */     cons.fill = 2;
/* 143 */     cons.gridy = 2;
/* 144 */     cons.gridx = 0;
/* 145 */     cons.insets.left = 25;
/* 146 */     cons.insets.right = 25;
/* 147 */     this.gbLayout.setConstraints(this.cReper, cons);
/* 148 */     this.panel.add(this.cReper);
/* 149 */     cons.gridx = 1;
/* 150 */     cons.insets.left = 10;
/* 151 */     cons.insets.right = 10;
/* 152 */     this.gbLayout.setConstraints(this.cSopor, cons);
/* 153 */     this.panel.add(this.cSopor);
/* 154 */     cons.weighty = 0.0D;
/* 155 */     cons.fill = 0;
/* 156 */     cons.anchor = 13;
/* 157 */     cons.gridy = 3;
/* 158 */     cons.gridx = 0;
/* 159 */     this.gbLayout.setConstraints(this.eSaldo, cons);
/* 160 */     this.panel.add(this.eSaldo);
/* 161 */     cons.weighty = 1.0D;
/* 162 */     cons.fill = 2;
/* 163 */     cons.anchor = 10;
/* 164 */     cons.gridx = 1;
/* 165 */     this.gbLayout.setConstraints(this.cSaldo, cons);
/* 166 */     this.panel.add(this.cSaldo);
/* 167 */     cons.weighty = 0.0D;
/* 168 */     cons.fill = 0;
/* 169 */     cons.insets.top = 10;
/* 170 */     cons.gridy = 4;
/* 171 */     cons.gridx = 0;
/* 172 */     this.gbLayout.setConstraints(this.crear, cons);
/* 173 */     this.panel.add(this.crear);
/* 174 */     cons.gridx = 1;
/* 175 */     this.gbLayout.setConstraints(this.cancelar, cons);
/* 176 */     this.panel.add(this.cancelar);
/*     */ 
/* 178 */     getContentPane().add(this.panel);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e) {
/* 182 */     String cmd = e.getActionCommand();
/*     */ 
/* 184 */     if ("opciones".equals(cmd)) {
/* 185 */       this.seleccion = this.trimestre.getSelectedIndex();
/* 186 */       if (this.seleccion != -1) {
/* 187 */         this.clase.calculaImportes(this.seleccion + 1);
/* 188 */         this.cReper.setText(this.fn.format(this.clase.getSReper()));
/* 189 */         this.cSopor.setText(this.fn.format(this.clase.getSSopor()));
/* 190 */         this.cSaldo.setText(this.fn.format(this.clase.getSaldo()));
/*     */       }
/*     */     }
/* 193 */     else if ("aceptar".equals(cmd)) {
/* 194 */       if (this.seleccion != -1)
/* 195 */         if (this.clase.crearAsiento(this.seleccion + 1)) {
/* 196 */           Inicio.frame.renovarTabla(0);
/* 197 */           JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("asiento") + " " + Mensajes.getString("creado"));
/*     */         }
/*     */         else {
/* 200 */           JOptionPane.showMessageDialog(getContentPane(), Mensajes.getString("astoLiquiYaEx"));
/*     */         }
/*     */     }
/* 203 */     else if ("cancelar".equals(cmd)) {
/* 204 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static ImageIcon createImageIcon(String path)
/*     */   {
/* 210 */     URL imgURL = LiquidacionIVA.class.getResource(path);
/* 211 */     if (imgURL != null)
/* 212 */       return new ImageIcon(imgURL);
/* 213 */     System.err.println("Couldn't find file: " + path);
/* 214 */     return null;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.dialogosFunciones.LiquidacionIVA
 * JD-Core Version:    0.6.2
 */