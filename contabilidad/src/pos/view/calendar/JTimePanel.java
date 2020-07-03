/*     */ package pos.view.calendar;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class JTimePanel extends JPanel
/*     */ {
/*     */   public static final int BUTTONS_ALL = 3;
/*     */   public static final int BUTTONS_HOUR = 1;
/*     */   public static final int BUTTONS_MINUTE = 2;
/*  40 */   private DateFormat fmtTime = DateFormat.getTimeInstance(3);
/*     */   private JClockPanel m_jclock;
/*     */   private Date m_dMinDate;
/*     */   private Date m_dMaxDate;
/*  46 */   private JButtonDate m_jbtnplushour = null;
/*  47 */   private JButtonDate m_jbtnminushour = null;
/*  48 */   private JButtonDate m_jbtnplusfifteen = null;
/*  49 */   private JButtonDate m_jbtnminusfifteen = null;
/*  50 */   private JButtonDate m_jbtnplusminute = null;
/*  51 */   private JButtonDate m_jbtnminusminute = null;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JPanel m_jactions;
/*     */   private JLabel m_jlblSeparator;
/*     */   private JLabel m_jlblTime;
/*     */   private JLabel m_jlblTime2;
/*     */   private JPanel m_jtime;
/*     */ 
/*     */   public JTimePanel()
/*     */   {
/*  55 */     this(null, 3);
/*     */   }
/*     */   public JTimePanel(Date dDate) {
/*  58 */     this(dDate, 3);
/*     */   }
/*     */ 
/*     */   public JTimePanel(Date dDate, int iButtons)
/*     */   {
/*  63 */     initComponents();
/*     */ 
/*  65 */     this.m_jclock = new JClockPanel(false);
/*  66 */     this.jPanel2.add(this.m_jclock, "Center");
/*     */ 
/*  70 */     DateFormat f = new SimpleDateFormat("H:mm");
/*     */ 
/*  72 */     ActionListener dateclick = new DateClick();
/*     */ 
/*  74 */     if ((iButtons & 0x1) > 0) {
/*  75 */       GregorianCalendar c = new GregorianCalendar(1900, 0, 0, 1, 0);
/*  76 */       this.m_jbtnplushour = new JButtonDate(f.format(c.getTime()), new ImageIcon(getClass().getResource("/pos/view/images/1uparrow.png")), dateclick);
/*  77 */       this.m_jactions.add(this.m_jbtnplushour);
/*     */     }
/*     */ 
/*  80 */     if ((iButtons & 0x2) > 0) {
/*  81 */       GregorianCalendar c = new GregorianCalendar(1900, 0, 0, 0, 15);
/*  82 */       this.m_jbtnplusfifteen = new JButtonDate(f.format(c.getTime()), new ImageIcon(getClass().getResource("/pos/view/images/1uparrow.png")), dateclick);
/*  83 */       this.m_jactions.add(this.m_jbtnplusfifteen);
/*     */     }
/*     */ 
/*  86 */     if ((iButtons & 0x2) > 0) {
/*  87 */       GregorianCalendar c = new GregorianCalendar(1900, 0, 0, 0, 1);
/*  88 */       this.m_jbtnplusminute = new JButtonDate(f.format(c.getTime()), new ImageIcon(getClass().getResource("/pos/view/images/1uparrow.png")), dateclick);
/*  89 */       this.m_jactions.add(this.m_jbtnplusminute);
/*     */     }
/*     */ 
/*  95 */     if ((iButtons & 0x2) > 0) {
/*  96 */       GregorianCalendar c = new GregorianCalendar(1900, 0, 0, 0, 1);
/*  97 */       this.m_jbtnminusminute = new JButtonDate(f.format(c.getTime()), new ImageIcon(getClass().getResource("/pos/view/images/1downarrow.png")), dateclick);
/*  98 */       this.m_jactions.add(this.m_jbtnminusminute);
/*     */     }
/*     */ 
/* 101 */     if ((iButtons & 0x2) > 0) {
/* 102 */       GregorianCalendar c = new GregorianCalendar(1900, 0, 0, 0, 15);
/* 103 */       this.m_jbtnminusfifteen = new JButtonDate(f.format(c.getTime()), new ImageIcon(getClass().getResource("/pos/view/images/1downarrow.png")), dateclick);
/* 104 */       this.m_jactions.add(this.m_jbtnminusfifteen);
/*     */     }
/*     */ 
/* 107 */     if ((iButtons & 0x1) > 0) {
/* 108 */       GregorianCalendar c = new GregorianCalendar(1900, 0, 0, 1, 0);
/* 109 */       this.m_jbtnminushour = new JButtonDate(f.format(c.getTime()), new ImageIcon(getClass().getResource("/pos/view/images/1downarrow.png")), dateclick);
/* 110 */       this.m_jactions.add(this.m_jbtnminushour);
/*     */     }
/*     */ 
/* 113 */     this.m_dMinDate = null;
/* 114 */     this.m_dMaxDate = null;
/* 115 */     this.m_jclock.setTime(dDate);
/* 116 */     renderTime();
/*     */   }
/*     */ 
/*     */   public void setDateMidNight() {
/* 120 */     setDate(new GregorianCalendar(1900, 0, 0, 0, 0).getTime());
/*     */   }
/*     */ 
/*     */   public void setDate(Date dNewDate)
/*     */   {
/* 125 */     Date dOldDate = this.m_jclock.getTime();
/* 126 */     if (((dNewDate == null) && (dOldDate != null)) || ((dNewDate != null) && (!dNewDate.equals(dOldDate)) && (checkDates(dNewDate))))
/*     */     {
/* 128 */       this.m_jclock.setTime(dNewDate);
/* 129 */       renderTime();
/* 130 */       firePropertyChange("Date", dOldDate, dNewDate);
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean checkDates(Date dDate) {
/* 135 */     return (dDate == null) || (((this.m_dMaxDate == null) || (this.m_dMaxDate.compareTo(dDate) > 0)) && ((this.m_dMinDate == null) || (this.m_dMinDate.compareTo(dDate) <= 0)));
/*     */   }
/*     */ 
/*     */   public Date getDate() {
/* 139 */     return this.m_jclock.getTime();
/*     */   }
/*     */ 
/*     */   public void setCheckDates(Date dMinDate, Date dMaxDate)
/*     */   {
/* 144 */     this.m_dMinDate = dMinDate;
/* 145 */     this.m_dMaxDate = dMaxDate;
/* 146 */     setDate(null);
/* 147 */     renderTime();
/*     */   }
/*     */ 
/*     */   public void setEnabled(boolean bValue)
/*     */   {
/* 152 */     super.setEnabled(bValue);
/* 153 */     renderTime();
/*     */   }
/*     */ 
/*     */   public void setPeriod(long period) {
/* 157 */     this.m_jclock.setPeriod(period);
/* 158 */     renderTime();
/*     */   }
/*     */ 
/*     */   private void renderTime()
/*     */   {
/* 163 */     Date dDate = this.m_jclock.getTime();
/* 164 */     if (dDate == null) {
/* 165 */       if (this.m_jbtnplushour != null) this.m_jbtnplushour.setEnabled(false);
/* 166 */       if (this.m_jbtnminushour != null) this.m_jbtnminushour.setEnabled(false);
/* 167 */       if (this.m_jbtnplusfifteen != null) this.m_jbtnplusfifteen.setEnabled(false);
/* 168 */       if (this.m_jbtnminusfifteen != null) this.m_jbtnminusfifteen.setEnabled(false);
/* 169 */       if (this.m_jbtnplusminute != null) this.m_jbtnplusminute.setEnabled(false);
/* 170 */       if (this.m_jbtnminusminute != null) this.m_jbtnminusminute.setEnabled(false);
/* 171 */       this.m_jlblTime.setText("  ");
/* 172 */       this.m_jlblSeparator.setVisible(false);
/* 173 */       this.m_jlblTime2.setVisible(false);
/* 174 */       this.m_jtime.revalidate();
/*     */     } else {
/* 176 */       GregorianCalendar oCalRender = new GregorianCalendar();
/* 177 */       oCalRender.setTime(dDate);
/*     */ 
/* 180 */       oCalRender.add(11, 1);
/* 181 */       if (this.m_jbtnplushour != null) {
/* 182 */         this.m_jbtnplushour.DateInf = oCalRender.getTime();
/* 183 */         this.m_jbtnplushour.setEnabled((isEnabled()) && (checkDates(oCalRender.getTime())));
/*     */       }
/* 185 */       oCalRender.add(11, -2);
/* 186 */       if (this.m_jbtnminushour != null) {
/* 187 */         this.m_jbtnminushour.DateInf = oCalRender.getTime();
/* 188 */         this.m_jbtnminushour.setEnabled((isEnabled()) && (checkDates(oCalRender.getTime())));
/*     */       }
/*     */ 
/* 191 */       oCalRender.setTime(dDate);
/*     */ 
/* 193 */       oCalRender.add(12, 15);
/* 194 */       if (this.m_jbtnplusfifteen != null) {
/* 195 */         this.m_jbtnplusfifteen.DateInf = oCalRender.getTime();
/* 196 */         this.m_jbtnplusfifteen.setEnabled((isEnabled()) && (checkDates(oCalRender.getTime())));
/*     */       }
/* 198 */       oCalRender.add(12, -30);
/* 199 */       if (this.m_jbtnminusfifteen != null) {
/* 200 */         this.m_jbtnminusfifteen.DateInf = oCalRender.getTime();
/* 201 */         this.m_jbtnminusfifteen.setEnabled((isEnabled()) && (checkDates(oCalRender.getTime())));
/*     */       }
/* 203 */       oCalRender.setTime(dDate);
/*     */ 
/* 205 */       oCalRender.add(12, 1);
/* 206 */       if (this.m_jbtnplusminute != null) {
/* 207 */         this.m_jbtnplusminute.DateInf = oCalRender.getTime();
/* 208 */         this.m_jbtnplusminute.setEnabled((isEnabled()) && (checkDates(oCalRender.getTime())));
/*     */       }
/* 210 */       oCalRender.add(12, -2);
/* 211 */       if (this.m_jbtnminusminute != null) {
/* 212 */         this.m_jbtnminusminute.DateInf = oCalRender.getTime();
/* 213 */         this.m_jbtnminusminute.setEnabled((isEnabled()) && (checkDates(oCalRender.getTime())));
/*     */       }
/*     */ 
/* 216 */       if (this.m_jclock.getPeriod() > 0L)
/*     */       {
/* 218 */         this.m_jlblTime.setText(this.fmtTime.format(dDate));
/* 219 */         this.m_jlblTime2.setText(this.fmtTime.format(new Date(dDate.getTime() + this.m_jclock.getPeriod())));
/* 220 */         this.m_jlblSeparator.setVisible(true);
/* 221 */         this.m_jlblTime2.setVisible(true);
/* 222 */         this.m_jtime.revalidate();
/*     */       }
/*     */       else {
/* 225 */         this.m_jlblTime.setText(this.fmtTime.format(dDate));
/* 226 */         this.m_jlblSeparator.setVisible(false);
/* 227 */         this.m_jlblTime2.setVisible(false);
/* 228 */         this.m_jtime.revalidate();
/*     */       }
/*     */     }
/*     */ 
/* 232 */     this.m_jclock.setEnabled(isEnabled());
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 283 */     this.jPanel1 = new JPanel();
/* 284 */     this.m_jactions = new JPanel();
/* 285 */     this.jPanel2 = new JPanel();
/* 286 */     this.m_jtime = new JPanel();
/* 287 */     this.m_jlblTime = new JLabel();
/* 288 */     this.m_jlblSeparator = new JLabel();
/* 289 */     this.m_jlblTime2 = new JLabel();
/*     */ 
/* 291 */     setLayout(new BorderLayout());
/*     */ 
/* 293 */     this.jPanel1.setLayout(new BorderLayout());
/*     */ 
/* 295 */     this.m_jactions.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/* 296 */     this.m_jactions.setLayout(new GridLayout(0, 1, 0, 5));
/* 297 */     this.jPanel1.add(this.m_jactions, "North");
/*     */ 
/* 299 */     add(this.jPanel1, "After");
/*     */ 
/* 301 */     this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/* 302 */     this.jPanel2.setLayout(new BorderLayout());
/*     */ 
/* 304 */     this.m_jtime.add(this.m_jlblTime);
/*     */ 
/* 306 */     this.m_jlblSeparator.setText(" - ");
/* 307 */     this.m_jtime.add(this.m_jlblSeparator);
/* 308 */     this.m_jtime.add(this.m_jlblTime2);
/*     */ 
/* 310 */     this.jPanel2.add(this.m_jtime, "North");
/*     */ 
/* 312 */     add(this.jPanel2, "Center");
/*     */   }
/*     */ 
/*     */   private static class JButtonDate extends JButton
/*     */   {
/*     */     public Date DateInf;
/*     */ 
/*     */     public JButtonDate(ActionListener datehandler)
/*     */     {
/* 251 */       initComponent();
/* 252 */       addActionListener(datehandler);
/*     */     }
/*     */ 
/*     */     public JButtonDate(String sText, ActionListener datehandler) {
/* 256 */       super();
/* 257 */       initComponent();
/* 258 */       addActionListener(datehandler);
/*     */     }
/*     */ 
/*     */     public JButtonDate(String sText, Icon ico, ActionListener datehandler) {
/* 262 */       super(ico);
/* 263 */       initComponent();
/* 264 */       addActionListener(datehandler);
/*     */     }
/*     */ 
/*     */     private void initComponent() {
/* 268 */       this.DateInf = null;
/* 269 */       setRequestFocusEnabled(false);
/* 270 */       setFocusPainted(false);
/* 271 */       setFocusable(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class DateClick
/*     */     implements ActionListener
/*     */   {
/*     */     private DateClick()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent e)
/*     */     {
/* 238 */       JTimePanel.JButtonDate oLbl = (JTimePanel.JButtonDate)e.getSource();
/* 239 */       if (oLbl.DateInf != null)
/* 240 */         JTimePanel.this.setDate(oLbl.DateInf);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.calendar.JTimePanel
 * JD-Core Version:    0.6.2
 */