/*     */ package pos.view.calendar;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRootPane;
/*     */ 
/*     */ public class JCalendarDialog extends JDialog
/*     */ {
/*     */   private Date m_date;
/*  37 */   private JCalendarPanel myCalendar = null;
/*  38 */   private JTimePanel myTime = null;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JPanel jPanelGrid;
/*     */   private JButton jcmdCancel;
/*     */   private JButton jcmdOK;
/*     */ 
/*     */   public JCalendarDialog(Frame parent, boolean modal)
/*     */   {
/*  42 */     super(parent, modal);
/*     */   }
/*     */ 
/*     */   public JCalendarDialog(Dialog parent, boolean modal)
/*     */   {
/*  47 */     super(parent, modal);
/*     */   }
/*     */ 
/*     */   private static Window getWindow(Component parent)
/*     */   {
/*  52 */     if (parent == null)
/*  53 */       return new JFrame();
/*  54 */     if (((parent instanceof Frame)) || ((parent instanceof Dialog))) {
/*  55 */       return (Window)parent;
/*     */     }
/*  57 */     return getWindow(parent.getParent());
/*     */   }
/*     */ 
/*     */   public static Date showCalendarTimeHours(Component parent, Date date)
/*     */   {
/*  62 */     return internalCalendarTime(parent, date == null ? DateUtils.getToday() : date, true);
/*     */   }
/*     */ 
/*     */   public static Date showCalendarTime(Component parent, Date date) {
/*  66 */     return internalCalendarTime(parent, date == null ? DateUtils.getTodayMinutes() : date, true);
/*     */   }
/*     */ 
/*     */   public static Date showCalendar(Component parent, Date date) {
/*  70 */     return internalCalendarTime(parent, date == null ? DateUtils.getTodayMinutes() : date, false);
/*     */   }
/*     */ 
/*     */   private static Date internalCalendarTime(Component parent, Date date, boolean bTimePanel)
/*     */   {
/*  75 */     Window window = getWindow(parent);
/*     */     JCalendarDialog myMsg;
/*     */    // JCalendarDialog myMsg;
/*  78 */     if ((window instanceof Frame))
/*  79 */       myMsg = new JCalendarDialog((Frame)window, true);
/*     */     else {
/*  81 */       myMsg = new JCalendarDialog((Dialog)window, true);
/*     */     }
/*     */ 
/*  84 */     myMsg.initComponents();
/*     */ 
/*  86 */     Date d = date;
/*  87 */     int dialogwidth = 400;
/*     */ 
/*  89 */     myMsg.myCalendar = new JCalendarPanel(d);
/*  90 */     myMsg.myCalendar.addPropertyChangeListener("Date", new JPanelCalendarChange(myMsg));
/*  91 */     myMsg.jPanelGrid.add(myMsg.myCalendar);
/*     */ 
/*  93 */     if (bTimePanel) {
/*  94 */       myMsg.myTime = new JTimePanel(d);
/*  95 */       myMsg.myTime.addPropertyChangeListener("Date", new JPanelTimeChange(myMsg));
/*  96 */       myMsg.jPanelGrid.add(myMsg.myTime);
/*  97 */       dialogwidth += 400;
/*     */     }
/*     */ 
/* 100 */     myMsg.getRootPane().setDefaultButton(myMsg.jcmdOK);
/*     */ 
/* 102 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 103 */     myMsg.setBounds((screenSize.width - dialogwidth) / 2, (screenSize.height - 359) / 2, dialogwidth, 359);
/*     */ 
/* 106 */     myMsg.m_date = null;
/* 107 */     myMsg.setVisible(true);
/* 108 */     return myMsg.m_date;
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 139 */     this.jPanel1 = new JPanel();
/* 140 */     this.jcmdOK = new JButton();
/* 141 */     this.jcmdCancel = new JButton();
/* 142 */     this.jPanel2 = new JPanel();
/* 143 */     this.jPanelGrid = new JPanel();
/*     */ 
/* 145 */     setTitle("Calendario");
/* 146 */     setResizable(false);
/* 147 */     addWindowListener(new WindowAdapter() {
/*     */       public void windowClosing(WindowEvent evt) {
/* 149 */         JCalendarDialog.this.closeWindow(evt);
/*     */       }
/*     */     });
/* 153 */     this.jPanel1.setLayout(new FlowLayout(2));
/*     */ 
/* 155 */     this.jcmdOK.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_ok.png")));
/* 156 */     ResourceBundle bundle = ResourceBundle.getBundle("internationalization/Mensajes");
/* 157 */     this.jcmdOK.setText(bundle.getString("bien"));
/* 158 */     this.jcmdOK.setMargin(new Insets(8, 16, 8, 16));
/* 159 */     this.jcmdOK.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 161 */         JCalendarDialog.this.jcmdOKActionPerformed(evt);
/*     */       }
/*     */     });
/* 164 */     this.jPanel1.add(this.jcmdOK);
/*     */ 
/* 166 */     this.jcmdCancel.setIcon(new ImageIcon(getClass().getResource("/pos/view/images/button_cancel.png")));
/* 167 */     this.jcmdCancel.setText(bundle.getString("cancelar"));
/* 168 */     this.jcmdCancel.setMargin(new Insets(8, 16, 8, 16));
/* 169 */     this.jcmdCancel.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 171 */         JCalendarDialog.this.jcmdCancelActionPerformed(evt);
/*     */       }
/*     */     });
/* 174 */     this.jPanel1.add(this.jcmdCancel);
/*     */ 
/* 176 */     getContentPane().add(this.jPanel1, "South");
/*     */ 
/* 178 */     this.jPanel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
/* 179 */     this.jPanel2.setLayout(new BorderLayout());
/*     */ 
/* 181 */     this.jPanelGrid.setLayout(new GridLayout(1, 0, 5, 0));
/* 182 */     this.jPanel2.add(this.jPanelGrid, "Center");
/*     */ 
/* 184 */     getContentPane().add(this.jPanel2, "Center");
/*     */   }
/*     */ 
/*     */   private void jcmdOKActionPerformed(ActionEvent evt)
/*     */   {
/* 191 */     GregorianCalendar date1 = new GregorianCalendar();
/* 192 */     date1.setTime(this.myCalendar.getDate());
/*     */     GregorianCalendar dateresult;
/*     */   //  GregorianCalendar dateresult;
/* 194 */     if (this.myTime == null) {
/* 195 */       dateresult = new GregorianCalendar(date1.get(1), date1.get(2), date1.get(5));
/*     */     }
/*     */     else
/*     */     {
/* 201 */       GregorianCalendar date2 = new GregorianCalendar();
/* 202 */       date2.setTime(this.myTime.getDate());
/* 203 */       dateresult = new GregorianCalendar(date1.get(1), date1.get(2), date1.get(5), date2.get(11), date2.get(12));
/*     */     }
/*     */ 
/* 211 */     this.m_date = dateresult.getTime();
/*     */ 
/* 213 */     setVisible(false);
/* 214 */     dispose();
/*     */   }
/*     */ 
/*     */   private void jcmdCancelActionPerformed(ActionEvent evt)
/*     */   {
/* 219 */     setVisible(false);
/* 220 */     dispose();
/*     */   }
/*     */ 
/*     */   private void closeWindow(WindowEvent evt)
/*     */   {
/* 225 */     setVisible(false);
/* 226 */     dispose();
/*     */   }
/*     */ 
/*     */   private static class JPanelCalendarChange
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private JCalendarDialog m_me;
/*     */ 
/*     */     public JPanelCalendarChange(JCalendarDialog me)
/*     */     {
/* 124 */       this.m_me = me;
/*     */     }
/*     */     public void propertyChange(PropertyChangeEvent evt) {
/* 127 */       this.m_me.myCalendar.setDate(this.m_me.myCalendar.getDate());
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class JPanelTimeChange
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     private JCalendarDialog m_me;
/*     */ 
/*     */     public JPanelTimeChange(JCalendarDialog me)
/*     */     {
/* 114 */       this.m_me = me;
/*     */     }
/*     */     public void propertyChange(PropertyChangeEvent evt) {
/* 117 */       this.m_me.myCalendar.setDate(this.m_me.myTime.getDate());
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.calendar.JCalendarDialog
 * JD-Core Version:    0.6.2
 */