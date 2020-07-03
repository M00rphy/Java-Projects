/*     */ package pos.view;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.UIDefaults;
/*     */ import javax.swing.UIManager;
/*     */ import pos.model.BasicException;
/*     */ import pos.model.VentaPOS;
/*     */ import pos.view.editors.JEditorCurrency;
/*     */ import pos.view.editors.JEditorDouble;
/*     */ import pos.view.editors.JEditorKeys;
/*     */ import pos.view.editors.JEditorString;
/*     */ 
/*     */ public class JProductLineEdit extends JDialog
/*     */ {
/*     */   private VentaPOS returnLine;
/*     */   private VentaPOS m_oLine;
/*     */   private boolean m_bunitsok;
/*     */   private boolean m_bpriceok;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel6;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JPanel jPanel3;
/*     */   private JPanel jPanel4;
/*     */   private JPanel jPanel5;
/*     */   private JButton m_jButtonCancel;
/*     */   private JButton m_jButtonOK;
/*     */   private JEditorKeys m_jKeys;
/*     */   private JEditorString m_jName;
/*     */   private JEditorCurrency m_jPrice;
/*     */   private JLabel m_jTotal;
/*     */   private JEditorDouble m_jUnits;
/*     */ 
/*     */   private JProductLineEdit(Frame parent, boolean modal)
/*     */   {
/*  47 */     super(parent, modal);
/*     */   }
/*     */ 
/*     */   private JProductLineEdit(Dialog parent, boolean modal) {
/*  51 */     super(parent, modal);
/*     */   }
/*     */ 
/*     */   private VentaPOS init(VentaPOS oLine) throws BasicException
/*     */   {
/*  56 */     initComponents();
/*     */ 
/*  58 */     this.m_oLine = new VentaPOS(oLine);
/*  59 */     this.m_bunitsok = true;
/*  60 */     this.m_bpriceok = true;
/*     */ 
/*  62 */     this.m_jName.setText(this.m_oLine.getDescripcion());
/*  63 */     this.m_jUnits.setDoubleValue(Double.valueOf(oLine.getUnidades()));
/*  64 */     this.m_jPrice.setDoubleValue(Double.valueOf(oLine.getImporte()));
/*     */ 
/*  68 */     this.m_jName.addPropertyChangeListener("Edition", new RecalculateName());
/*  69 */     this.m_jUnits.addPropertyChangeListener("Edition", new RecalculateUnits());
/*  70 */     this.m_jPrice.addPropertyChangeListener("Edition", new RecalculatePrice());
/*     */ 
/*  73 */     this.m_jName.addEditorKeys(this.m_jKeys);
/*  74 */     this.m_jUnits.addEditorKeys(this.m_jKeys);
/*  75 */     this.m_jPrice.addEditorKeys(this.m_jKeys);
/*     */ 
/*  78 */     if (this.m_jName.isEnabled())
/*  79 */       this.m_jName.activate();
/*     */     else {
/*  81 */       this.m_jUnits.activate();
/*     */     }
/*     */ 
/*  84 */     printTotals();
/*     */ 
/*  86 */     getRootPane().setDefaultButton(this.m_jButtonOK);
/*  87 */     this.returnLine = null;
/*  88 */     setVisible(true);
/*     */ 
/*  90 */     return this.returnLine;
/*     */   }
/*     */ 
/*     */   private void printTotals()
/*     */   {
/*  95 */     if ((this.m_bunitsok) && (this.m_bpriceok)) {
/*  96 */       DecimalFormatSymbols formato = new DecimalFormatSymbols();
/*  97 */       formato.setDecimalSeparator(',');
/*  98 */       formato.setPerMill('.');
/*  99 */       DecimalFormat fn = new DecimalFormat("#,###,##0.00", formato);
/* 100 */       this.m_jTotal.setText(fn.format(this.m_oLine.getTotal()));
/* 101 */       this.m_jButtonOK.setEnabled(true);
/*     */     } else {
/* 103 */       this.m_jTotal.setText(null);
/* 104 */       this.m_jButtonOK.setEnabled(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static Window getWindow(Component parent)
/*     */   {
/* 146 */     if (parent == null)
/* 147 */       return new JFrame();
/* 148 */     if (((parent instanceof Frame)) || ((parent instanceof Dialog))) {
/* 149 */       return (Window)parent;
/*     */     }
/* 151 */     return getWindow(parent.getParent());
/*     */   }
/*     */ 
/*     */   public static VentaPOS showMessage(Component parent, VentaPOS oLine)
/*     */     throws BasicException
/*     */   {
/* 157 */     Window window = getWindow(parent);
/*     */     JProductLineEdit myMsg;
/*     */   //  JProductLineEdit myMsg;
/* 160 */     if ((window instanceof Frame))
/* 161 */       myMsg = new JProductLineEdit((Frame)window, true);
/*     */     else {
/* 163 */       myMsg = new JProductLineEdit((Dialog)window, true);
/*     */     }
/* 165 */     return myMsg.init(oLine);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 177 */     this.jPanel5 = new JPanel();
/* 178 */     this.jPanel2 = new JPanel();
/* 179 */     this.m_jName = new JEditorString();
/* 180 */     this.m_jUnits = new JEditorDouble();
/* 181 */     this.m_jPrice = new JEditorCurrency();
/* 182 */     this.jLabel6 = new JLabel();
/* 183 */     this.m_jTotal = new JLabel();
/* 184 */     this.jLabel3 = new JLabel();
/* 185 */     this.jLabel4 = new JLabel();
/* 186 */     this.jLabel1 = new JLabel();
/* 187 */     this.jPanel1 = new JPanel();
/* 188 */     this.m_jButtonOK = new JButton();
/* 189 */     this.m_jButtonCancel = new JButton();
/* 190 */     this.jPanel3 = new JPanel();
/* 191 */     this.jPanel4 = new JPanel();
/* 192 */     this.m_jKeys = new JEditorKeys();
/*     */ 
/* 194 */     setDefaultCloseOperation(2);
/* 195 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 196 */     setTitle(bundle.getString("editar"));
/*     */ 
/* 198 */     this.jPanel5.setLayout(new BorderLayout());
/*     */ 
/* 200 */     this.jPanel2.setLayout(null);
/* 201 */     this.jPanel2.add(this.m_jName);
/* 202 */     this.m_jName.setBounds(100, 50, 250, 60);
/* 203 */     this.jPanel2.add(this.m_jUnits);
/* 204 */     this.m_jUnits.setBounds(100, 120, 240, 25);
/* 205 */     this.jPanel2.add(this.m_jPrice);
/* 206 */     this.m_jPrice.setBounds(100, 150, 240, 25);
/*     */ 
/* 208 */     this.jLabel6.setText(bundle.getString("total"));
/* 209 */     this.jPanel2.add(this.jLabel6);
/* 210 */     this.jLabel6.setBounds(10, 190, 90, 16);
/*     */ 
/* 212 */     this.m_jTotal.setBackground(UIManager.getDefaults().getColor("TextField.disabledBackground"));
/* 213 */     this.m_jTotal.setHorizontalAlignment(4);
/* 214 */     this.m_jTotal.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(UIManager.getDefaults().getColor("Button.darkShadow")), BorderFactory.createEmptyBorder(1, 4, 1, 4)));
/* 215 */     this.m_jTotal.setOpaque(true);
/* 216 */     this.m_jTotal.setPreferredSize(new Dimension(150, 25));
/* 217 */     this.m_jTotal.setRequestFocusEnabled(false);
/* 218 */     this.jPanel2.add(this.m_jTotal);
/* 219 */     this.m_jTotal.setBounds(100, 190, 210, 25);
/*     */ 
/* 221 */     this.jLabel3.setText(bundle.getString("descripcion"));
/* 222 */     this.jPanel2.add(this.jLabel3);
/* 223 */     this.jLabel3.setBounds(10, 70, 80, 16);
/*     */ 
/* 225 */     this.jLabel4.setText(bundle.getString("unidades"));
/* 226 */     this.jPanel2.add(this.jLabel4);
/* 227 */     this.jLabel4.setBounds(10, 120, 80, 16);
/*     */ 
/* 229 */     this.jLabel1.setText(bundle.getString("importe"));
/* 230 */     this.jPanel2.add(this.jLabel1);
/* 231 */     this.jLabel1.setBounds(10, 150, 70, 16);
/*     */ 
/* 233 */     this.jPanel5.add(this.jPanel2, "Center");
/*     */ 
/* 235 */     this.jPanel1.setLayout(new FlowLayout(2));
/*     */ 
/* 237 */     this.m_jButtonOK.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_ok.png")));
/* 238 */     this.m_jButtonOK.setText(bundle.getString("bien"));
/* 239 */     this.m_jButtonOK.setFocusPainted(false);
/* 240 */     this.m_jButtonOK.setFocusable(false);
/* 241 */     this.m_jButtonOK.setMargin(new Insets(8, 16, 8, 16));
/* 242 */     this.m_jButtonOK.setRequestFocusEnabled(false);
/* 243 */     this.m_jButtonOK.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 245 */         JProductLineEdit.this.m_jButtonOKActionPerformed(evt);
/*     */       }
/*     */     });
/* 248 */     this.jPanel1.add(this.m_jButtonOK);
/*     */ 
/* 250 */     this.m_jButtonCancel.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_cancel.png")));
/* 251 */     this.m_jButtonCancel.setText(bundle.getString("cancelar"));
/* 252 */     this.m_jButtonCancel.setFocusPainted(false);
/* 253 */     this.m_jButtonCancel.setFocusable(false);
/* 254 */     this.m_jButtonCancel.setMargin(new Insets(8, 16, 8, 16));
/* 255 */     this.m_jButtonCancel.setRequestFocusEnabled(false);
/* 256 */     this.m_jButtonCancel.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 258 */         JProductLineEdit.this.m_jButtonCancelActionPerformed(evt);
/*     */       }
/*     */     });
/* 261 */     this.jPanel1.add(this.m_jButtonCancel);
/*     */ 
/* 263 */     this.jPanel5.add(this.jPanel1, "South");
/*     */ 
/* 265 */     getContentPane().add(this.jPanel5, "Center");
/*     */ 
/* 267 */     this.jPanel3.setLayout(new BorderLayout());
/*     */ 
/* 269 */     this.jPanel4.setLayout(new BoxLayout(this.jPanel4, 1));
/* 270 */     this.jPanel4.add(this.m_jKeys);
/*     */ 
/* 272 */     this.jPanel3.add(this.jPanel4, "North");
/*     */ 
/* 274 */     getContentPane().add(this.jPanel3, "East");
/*     */ 
/* 276 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 277 */     setBounds((screenSize.width - 580) / 2, (screenSize.height - 362) / 2, 580, 362);
/*     */   }
/*     */ 
/*     */   private void m_jButtonCancelActionPerformed(ActionEvent evt)
/*     */   {
/* 282 */     dispose();
/*     */   }
/*     */ 
/*     */   private void m_jButtonOKActionPerformed(ActionEvent evt)
/*     */   {
/* 288 */     this.returnLine = this.m_oLine;
/*     */ 
/* 290 */     dispose();
/*     */   }
/*     */ 
/*     */   private class RecalculateName
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private RecalculateName()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void propertyChange(PropertyChangeEvent evt)
/*     */     {
/* 141 */       JProductLineEdit.this.m_oLine.setDescripcion(JProductLineEdit.this.m_jName.getText());
/*     */     }
/*     */   }
/*     */ 
/*     */   private class RecalculatePrice
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private RecalculatePrice()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void propertyChange(PropertyChangeEvent evt)
/*     */     {
/* 126 */       Double value = JProductLineEdit.this.m_jPrice.getDoubleValue();
/* 127 */       if ((value == null) || (value.doubleValue() == 0.0D)) {
/* 128 */         JProductLineEdit.this.m_bpriceok = false;
/*     */       } else {
/* 130 */         JProductLineEdit.this.m_oLine.setImporte(value.doubleValue());
/* 131 */         JProductLineEdit.this.m_oLine.recalcularTotal();
/* 132 */         JProductLineEdit.this.m_bpriceok = true;
/*     */       }
/*     */ 
/* 135 */       JProductLineEdit.this.printTotals();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class RecalculateUnits
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private RecalculateUnits()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void propertyChange(PropertyChangeEvent evt)
/*     */     {
/* 110 */       Double value = JProductLineEdit.this.m_jUnits.getDoubleValue();
/* 111 */       if ((value == null) || (value.doubleValue() == 0.0D)) {
/* 112 */         JProductLineEdit.this.m_bunitsok = false;
/*     */       } else {
/* 114 */         JProductLineEdit.this.m_oLine.setUnidades(value.intValue());
/* 115 */         JProductLineEdit.this.m_oLine.recalcularTotal();
/* 116 */         JProductLineEdit.this.m_bunitsok = true;
/*     */       }
/*     */ 
/* 119 */       JProductLineEdit.this.printTotals();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.JProductLineEdit
 * JD-Core Version:    0.6.2
 */