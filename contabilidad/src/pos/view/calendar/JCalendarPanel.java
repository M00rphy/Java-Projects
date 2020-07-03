/*     */ package pos.view.calendar;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.UIDefaults;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.LineBorder;
/*     */ 
/*     */ public class JCalendarPanel extends JPanel
/*     */ {
/*  34 */   private static GregorianCalendar m_CalendarHelper = new GregorianCalendar();
/*     */   private Date m_date;
/*     */   private JButtonDate[] m_ListDates;
/*     */   private JLabel[] m_jDays;
/*     */   private JButtonDate m_jCurrent;
/*     */   private JButtonDate m_jBtnMonthInc;
/*     */   private JButtonDate m_jBtnMonthDec;
/*     */   private JButtonDate m_jBtnYearInc;
/*     */   private JButtonDate m_jBtnYearDec;
/*     */   private JButtonDate m_jBtnToday;
/*  47 */   private DateFormat fmtMonthYear = new SimpleDateFormat("MMMMM yyyy");
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JPanel jPanel3;
/*     */   private JPanel m_jActions;
/*     */   private JPanel m_jDates;
/*     */   private JLabel m_jLblMonth;
/*     */   private JPanel m_jMonth;
/*     */   private JPanel m_jWeekDays;
/*     */ 
/*     */   public JCalendarPanel()
/*     */   {
/*  51 */     this(new Date());
/*     */   }
/*     */ 
/*     */   public JCalendarPanel(Date dDate)
/*     */   {
/*  58 */     initComponents();
/*  59 */     initComponents2();
/*     */ 
/*  63 */     this.m_date = dDate;
/*     */ 
/*  66 */     renderMonth();
/*  67 */     renderDay();
/*     */   }
/*     */ 
/*     */   public void setDate(Date dNewDate)
/*     */   {
/*  73 */     Date dOldDate = this.m_date;
/*  74 */     this.m_date = dNewDate;
/*     */ 
/*  77 */     renderMonth();
/*  78 */     renderDay();
/*     */ 
/*  81 */     firePropertyChange("Date", dOldDate, dNewDate);
/*     */   }
/*     */ 
/*     */   public Date getDate() {
/*  85 */     return this.m_date;
/*     */   }
/*     */ 
/*     */   public void setEnabled(boolean bValue)
/*     */   {
/*  91 */     super.setEnabled(bValue);
/*     */ 
/*  94 */     renderMonth();
/*  95 */     renderDay();
/*     */   }
/*     */ 
/*     */   private void renderMonth()
/*     */   {
/* 103 */     for (int j = 0; j < 7; j++) {
/* 104 */       this.m_jDays[j].setEnabled(isEnabled());
/*     */     }
/*     */ 
/* 108 */     for (int i = 0; i < 42; i++) {
/* 109 */       JButtonDate jAux = this.m_ListDates[i];
/* 110 */       jAux.DateInf = null;
/* 111 */       jAux.setEnabled(false);
/* 112 */       jAux.setText(null);
/* 113 */       jAux.setForeground((Color)UIManager.getDefaults().get("TextPane.foreground"));
/* 114 */       jAux.setBackground((Color)UIManager.getDefaults().get("TextPane.background"));
/* 115 */       jAux.setBorder(null);
/*     */     }
/*     */ 
/* 118 */     if (this.m_date == null) {
/* 119 */       this.m_jLblMonth.setEnabled(isEnabled());
/* 120 */       this.m_jLblMonth.setText(null);
/*     */     } else {
/* 122 */       m_CalendarHelper.setTime(this.m_date);
/*     */ 
/* 124 */       this.m_jLblMonth.setEnabled(isEnabled());
/* 125 */       this.m_jLblMonth.setText(this.fmtMonthYear.format(m_CalendarHelper.getTime()));
/*     */ 
/* 127 */       int iCurrentMonth = m_CalendarHelper.get(2);
/* 128 */       m_CalendarHelper.set(5, 1);
/*     */ 
/* 130 */       while (m_CalendarHelper.get(2) == iCurrentMonth)
/*     */       {
/* 132 */         JButtonDate jAux = getLabelByDate(m_CalendarHelper.getTime());
/* 133 */         jAux.DateInf = m_CalendarHelper.getTime();
/* 134 */         jAux.setEnabled(isEnabled());
/* 135 */         jAux.setText(String.valueOf(m_CalendarHelper.get(5)));
/*     */ 
/* 137 */         m_CalendarHelper.add(5, 1);
/*     */       }
/*     */     }
/*     */ 
/* 141 */     this.m_jCurrent = null;
/*     */   }
/*     */ 
/*     */   private void renderDay()
/*     */   {
/* 146 */     this.m_jBtnToday.setEnabled(isEnabled());
/*     */ 
/* 148 */     if (this.m_date == null) {
/* 149 */       this.m_jBtnMonthDec.setEnabled(false);
/* 150 */       this.m_jBtnMonthInc.setEnabled(isEnabled());
/* 151 */       this.m_jBtnYearDec.setEnabled(isEnabled());
/* 152 */       this.m_jBtnYearInc.setEnabled(isEnabled());
/*     */     } else {
/* 154 */       m_CalendarHelper.setTime(this.m_date);
/*     */ 
/* 156 */       m_CalendarHelper.add(2, -1);
/* 157 */       this.m_jBtnMonthDec.DateInf = m_CalendarHelper.getTime();
/* 158 */       this.m_jBtnMonthDec.setEnabled(isEnabled());
/* 159 */       m_CalendarHelper.add(2, 2);
/* 160 */       this.m_jBtnMonthInc.DateInf = m_CalendarHelper.getTime();
/* 161 */       this.m_jBtnMonthInc.setEnabled(isEnabled());
/*     */ 
/* 163 */       m_CalendarHelper.setTime(this.m_date);
/* 164 */       m_CalendarHelper.add(1, -1);
/* 165 */       this.m_jBtnYearDec.DateInf = m_CalendarHelper.getTime();
/* 166 */       this.m_jBtnYearDec.setEnabled(isEnabled());
/* 167 */       m_CalendarHelper.add(1, 2);
/* 168 */       this.m_jBtnYearInc.DateInf = m_CalendarHelper.getTime();
/* 169 */       this.m_jBtnYearInc.setEnabled(isEnabled());
/*     */ 
/* 171 */       if (this.m_jCurrent != null) {
/* 172 */         this.m_jCurrent.setForeground((Color)UIManager.getDefaults().get("TextPane.foreground"));
/* 173 */         this.m_jCurrent.setBackground((Color)UIManager.getDefaults().get("TextPane.background"));
/* 174 */         this.m_jCurrent.setBorder(null);
/*     */       }
/*     */ 
/* 177 */       JButtonDate jAux = getLabelByDate(this.m_date);
/* 178 */       jAux.setBackground((Color)UIManager.getDefaults().get("TextPane.selectionBackground"));
/* 179 */       jAux.setForeground((Color)UIManager.getDefaults().get("TextPane.selectionForeground"));
/* 180 */       jAux.setBorder(new LineBorder((Color)UIManager.getDefaults().get("TitledBorder.titleColor")));
/* 181 */       this.m_jCurrent = jAux;
/*     */     }
/*     */   }
/*     */ 
/*     */   private JButtonDate getLabelByDate(Date d)
/*     */   {
/* 187 */     GregorianCalendar oCalRender = new GregorianCalendar();
/* 188 */     oCalRender.setTime(d);
/* 189 */     int iDayOfMonth = oCalRender.get(5);
/*     */ 
/* 191 */     oCalRender.set(5, 1);
/*     */ 
/* 193 */     int iCol = oCalRender.get(7) - oCalRender.getFirstDayOfWeek();
/* 194 */     if (iCol < 0) {
/* 195 */       iCol += 7;
/*     */     }
/* 197 */     return this.m_ListDates[(iCol + iDayOfMonth - 1)];
/*     */   }
/*     */ 
/*     */   private void initComponents2()
/*     */   {
/* 240 */     ActionListener dateclick = new DateClick();
/*     */ 
/* 242 */     this.m_jBtnYearDec = new JButtonDate(new ImageIcon(getClass().getResource("/pos/view/images/2uparrow.png")), dateclick);
/* 243 */     this.m_jBtnMonthDec = new JButtonDate(new ImageIcon(getClass().getResource("/pos/view/images/1uparrow.png")), dateclick);
/* 244 */     this.m_jBtnToday = new JButtonDate("Hoy", dateclick);
/* 245 */     this.m_jBtnMonthInc = new JButtonDate(new ImageIcon(getClass().getResource("/pos/view/images/1downarrow.png")), dateclick);
/* 246 */     this.m_jBtnYearInc = new JButtonDate(new ImageIcon(getClass().getResource("/pos/view/images/2downarrow.png")), dateclick);
/*     */ 
/* 248 */     this.m_jBtnToday.DateInf = new Date();
/* 249 */     this.m_jActions.add(this.m_jBtnYearDec);
/* 250 */     this.m_jActions.add(this.m_jBtnMonthDec);
/* 251 */     this.m_jActions.add(this.m_jBtnToday);
/* 252 */     this.m_jActions.add(this.m_jBtnMonthInc);
/* 253 */     this.m_jActions.add(this.m_jBtnYearInc);
/*     */ 
/* 255 */     this.m_ListDates = new JButtonDate[42];
/* 256 */     for (int i = 0; i < 42; i++) {
/* 257 */       JButtonDate jAux = new JButtonDate(dateclick);
/*     */ 
/* 259 */       jAux.setHorizontalAlignment(0);
/* 260 */       jAux.setText(null);
/* 261 */       jAux.setOpaque(true);
/* 262 */       jAux.setForeground((Color)UIManager.getDefaults().get("TextPane.foreground"));
/* 263 */       jAux.setBackground((Color)UIManager.getDefaults().get("TextPane.background"));
/* 264 */       jAux.setBorder(null);
/* 265 */       this.m_ListDates[i] = jAux;
/* 266 */       this.m_jDates.add(jAux);
/*     */     }
/*     */ 
/* 269 */     this.m_jDays = new JLabel[7];
/* 270 */     for (int iHead = 0; iHead < 7; iHead++) {
/* 271 */       JLabel JAuxHeader = new JLabel();
/*     */ 
/* 273 */       JAuxHeader.setHorizontalAlignment(0);
/* 274 */       this.m_jDays[iHead] = JAuxHeader;
/* 275 */       this.m_jWeekDays.add(JAuxHeader);
/*     */     }
/*     */ 
/* 278 */     DateFormat fmtWeekDay = new SimpleDateFormat("E");
/* 279 */     Calendar oCalRender = new GregorianCalendar();
/*     */ 
/* 281 */     for (int j = 0; j < 7; j++) {
/* 282 */       oCalRender.add(5, 1);
/* 283 */       int iCol = oCalRender.get(7) - oCalRender.getFirstDayOfWeek();
/* 284 */       if (iCol < 0) {
/* 285 */         iCol += 7;
/*     */       }
/* 287 */       this.m_jDays[iCol].setText(fmtWeekDay.format(oCalRender.getTime()));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 300 */     this.jPanel1 = new JPanel();
/* 301 */     this.m_jMonth = new JPanel();
/* 302 */     this.m_jWeekDays = new JPanel();
/* 303 */     this.m_jDates = new JPanel();
/* 304 */     this.jPanel2 = new JPanel();
/* 305 */     this.m_jLblMonth = new JLabel();
/* 306 */     this.jPanel3 = new JPanel();
/* 307 */     this.m_jActions = new JPanel();
/*     */ 
/* 309 */     setLayout(new BorderLayout());
/*     */ 
/* 311 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/* 312 */     this.jPanel1.setLayout(new BorderLayout());
/*     */ 
/* 314 */     this.m_jMonth.setLayout(new BorderLayout());
/*     */ 
/* 316 */     this.m_jWeekDays.setLayout(new GridLayout(1, 7));
/* 317 */     this.m_jMonth.add(this.m_jWeekDays, "North");
/*     */ 
/* 319 */     this.m_jDates.setBackground(UIManager.getDefaults().getColor("TextPane.background"));
/* 320 */     this.m_jDates.setLayout(new GridLayout(6, 7));
/* 321 */     this.m_jMonth.add(this.m_jDates, "Center");
/*     */ 
/* 323 */     this.jPanel1.add(this.m_jMonth, "Center");
/*     */ 
/* 325 */     this.m_jLblMonth.setFont(new Font("Dialog", 1, 14));
/* 326 */     this.jPanel2.add(this.m_jLblMonth);
/*     */ 
/* 328 */     this.jPanel1.add(this.jPanel2, "North");
/*     */ 
/* 330 */     add(this.jPanel1, "Center");
/*     */ 
/* 332 */     this.jPanel3.setLayout(new BorderLayout());
/*     */ 
/* 334 */     this.m_jActions.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
/* 335 */     this.m_jActions.setLayout(new GridLayout(0, 1, 0, 5));
/* 336 */     this.jPanel3.add(this.m_jActions, "North");
/*     */ 
/* 338 */     add(this.jPanel3, "After");
/*     */   }
/*     */ 
/*     */   private static class JButtonDate extends JButton
/*     */   {
/*     */     public Date DateInf;
/*     */ 
/*     */     public JButtonDate(ActionListener datehandler)
/*     */     {
/* 216 */       initComponent();
/* 217 */       addActionListener(datehandler);
/*     */     }
/*     */     public JButtonDate(String sText, ActionListener datehandler) {
/* 220 */       super();
/* 221 */       initComponent();
/* 222 */       addActionListener(datehandler);
/*     */     }
/*     */     public JButtonDate(Icon icon, ActionListener datehandler) {
/* 225 */       super();
/* 226 */       initComponent();
/* 227 */       addActionListener(datehandler);
/*     */     }
/*     */ 
/*     */     private void initComponent() {
/* 231 */       this.DateInf = null;
/* 232 */       setRequestFocusEnabled(false);
/* 233 */       setFocusPainted(false);
/* 234 */       setFocusable(false);
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
/* 203 */       JCalendarPanel.JButtonDate oLbl = (JCalendarPanel.JButtonDate)e.getSource();
/* 204 */       if (oLbl.DateInf != null)
/* 205 */         JCalendarPanel.this.setDate(oLbl.DateInf);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.calendar.JCalendarPanel
 * JD-Core Version:    0.6.2
 */