/*     */ package contaes.calendario;
/*     */ 
/*     */ import contaes.calendario.event.ICalendarEvent;
/*     */ import contaes.calendario.event.ICalendarEventListener;
/*     */ import contaes.calendario.event.ISpinnerModelEvent;
/*     */ import contaes.calendario.event.ISpinnerModelListener;
/*     */ import java.awt.AWTEvent;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.AWTEventListener;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.Serializable;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.JSpinner.DateEditor;
/*     */ import javax.swing.border.BevelBorder;
/*     */ import javax.swing.event.EventListenerList;
/*     */ 
/*     */ public class ICalendarPanel extends JPanel
/*     */   implements Serializable, ISpinnerModelListener
/*     */ {
/*     */   private JSpinner jsMes;
/*     */   private JSpinner jsAno;
/*     */   private JButton ok;
/*     */   private JPanel dias;
/*     */   private JPanel mesEAno;
/*  77 */   private Font f = new Font("TimesRoman", 0, 12);
/*     */   private int diaAtual;
/*  83 */   private int[] monthLength = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
/*     */ 
/*  85 */   private String[] diasSemana = null;
/*     */   private DayCell[] cells;
/*  89 */   private EventListenerList listeners = new EventListenerList();
/*     */ 
/*     */   public ICalendarPanel()
/*     */   {
/*  96 */     Toolkit.getDefaultToolkit().addAWTEventListener(new ICalendarPanelEventProxy(), 8L);
/*     */ 
/*  98 */     setLayout(new BorderLayout());
/*     */ 
/* 100 */     this.diasSemana = new String[] { new SimpleDateFormat("E").format(new Date(345600000L)), new SimpleDateFormat("E").format(new Date(432000000L)), new SimpleDateFormat("E").format(new Date(518400000L)), new SimpleDateFormat("E").format(new Date(604800000L)), new SimpleDateFormat("E").format(new Date(691200000L)), new SimpleDateFormat("E").format(new Date(777600000L)), new SimpleDateFormat("E").format(new Date(864000000L)) };
/*     */ 
/* 109 */     setFont(this.f);
/* 110 */     setBackground(new Color(224, 231, 239));
/*     */ 
/* 112 */     Date hoje = new Date();
/* 113 */     Calendar timeKeeper = Calendar.getInstance();
/* 114 */     timeKeeper.add(1, -100);
/* 115 */     Date dataMinima = timeKeeper.getTime();
/* 116 */     timeKeeper.add(1, 1000);
/* 117 */     Date dataMaxima = timeKeeper.getTime();
/* 118 */     ISpinnerDateModel model = new ISpinnerDateModel(hoje, dataMinima, dataMaxima, 1);
/*     */ 
/* 120 */     model.addModelListener(this);
/* 121 */     this.jsAno = new JSpinner(model);
/* 122 */     this.jsAno.setPreferredSize(new Dimension(50, 20));
/* 123 */     this.jsAno.setEditor(new JSpinner.DateEditor(this.jsAno, "yyyy"));
/*     */ 
/* 125 */     model = new ISpinnerDateModel(hoje, dataMinima, dataMaxima, 2);
/* 126 */     model.addModelListener(this);
/* 127 */     this.jsMes = new JSpinner(model);
/* 128 */     this.jsMes.setPreferredSize(new Dimension(80, 20));
/* 129 */     this.jsMes.setEditor(new JSpinner.DateEditor(this.jsMes, "MMMMM"));
/*     */ 
/* 131 */     this.ok = new JButton(new ImageIcon(getClass().getResource("/contaes/iconos/apply.png")));
/* 132 */     this.ok.setPreferredSize(new Dimension(16, 16));
/* 133 */     this.ok.setBorder(null);
/* 134 */     OkListener listener = new OkListener();
/* 135 */     this.ok.addMouseListener(listener);
/* 136 */     this.ok.addActionListener(listener);
/*     */ 
/* 138 */     this.mesEAno = new JPanel();
/* 139 */     this.mesEAno.add(this.jsMes);
/* 140 */     this.mesEAno.add(this.jsAno);
/* 141 */     this.mesEAno.add(this.ok);
/*     */ 
/* 143 */     add(this.mesEAno, "North");
/*     */ 
/* 145 */     this.dias = new JPanel();
/* 146 */     this.dias.setLayout(new GridLayout(0, 7));
/* 147 */     add(this.dias, "Center");
/* 148 */     setName("ICalendarPanel");
/*     */ 
/* 150 */     timeKeeper = Calendar.getInstance();
/* 151 */     this.diaAtual = timeKeeper.get(5);
/* 152 */     arrangeDays();
/*     */   }
/*     */ 
/*     */   public Date getDate()
/*     */   {
/* 162 */     Calendar timeKeeper = Calendar.getInstance();
/* 163 */     timeKeeper.set(getYear(), getMonth(), getDay());
/* 164 */     return timeKeeper.getTime();
/*     */   }
/*     */ 
/*     */   public void setDate(Date newDate)
/*     */   {
/* 174 */     Calendar timeKeeper = Calendar.getInstance();
/* 175 */     timeKeeper.setTime(newDate);
/* 176 */     setYear(timeKeeper.get(1));
/* 177 */     setMonth(timeKeeper.get(2));
/* 178 */     setDay(timeKeeper.get(5));
/* 179 */     arrangeDays();
/*     */   }
/*     */ 
/*     */   public int getYear()
/*     */   {
/* 189 */     Calendar timeKeeper = Calendar.getInstance();
/*     */     try {
/* 191 */       timeKeeper.setTime((Date)this.jsAno.getValue());
/*     */     }
/*     */     catch (NullPointerException ex) {
/* 194 */       ex.printStackTrace();
/*     */     }
/* 196 */     return timeKeeper.get(1);
/*     */   }
/*     */ 
/*     */   public void setYear(int newYear)
/*     */   {
/* 207 */     Calendar timeKeeper = Calendar.getInstance();
/* 208 */     timeKeeper.set(newYear, getMonth(), getDay());
/* 209 */     this.jsAno.setValue(timeKeeper.getTime());
/*     */ 
/* 211 */     new Thread()
/*     */     {
/*     */       public void run()
/*     */       {
/* 215 */         ICalendarPanel.this.fireAnoSelecionado();
/*     */       }
/*     */     }
/* 211 */     .start();
/*     */   }
/*     */ 
/*     */   public int getMonth()
/*     */   {
/* 227 */     Calendar timeKeeper = Calendar.getInstance();
/*     */     try {
/* 229 */       timeKeeper.setTime((Date)this.jsMes.getValue());
/*     */     }
/*     */     catch (NullPointerException e) {
/* 232 */       e.printStackTrace();
/*     */     }
/* 234 */     return timeKeeper.get(2);
/*     */   }
/*     */ 
/*     */   public void setMonth(int newMonth)
/*     */   {
/* 244 */     Calendar timeKeeper = Calendar.getInstance();
/* 245 */     timeKeeper.set(getYear(), newMonth, 1);
/* 246 */     if (timeKeeper.getActualMaximum(5) >= getDay())
/* 247 */       timeKeeper.set(getYear(), newMonth, getDay());
/*     */     else
/* 249 */       timeKeeper.set(getYear(), newMonth, timeKeeper.getActualMaximum(5));
/* 250 */     this.jsMes.setValue(timeKeeper.getTime());
/*     */ 
/* 252 */     new Thread()
/*     */     {
/*     */       public void run()
/*     */       {
/* 256 */         ICalendarPanel.this.fireMesSelecionado();
/*     */       }
/*     */     }
/* 252 */     .start();
/*     */   }
/*     */ 
/*     */   public int getDay()
/*     */   {
/* 268 */     return this.diaAtual;
/*     */   }
/*     */ 
/*     */   public void setDay(int newDay)
/*     */   {
/* 278 */     newDay = newDay < 1 ? 1 : newDay;
/* 279 */     this.diaAtual = newDay;
/* 280 */     if (this.cells != null) {
/* 281 */       arrangeDays();
/*     */     }
/* 283 */     new Thread()
/*     */     {
/*     */       public void run()
/*     */       {
/* 287 */         ICalendarPanel.this.fireDiaSelecionado();
/*     */       }
/*     */     }
/* 283 */     .start();
/*     */   }
/*     */ 
/*     */   public Calendar getCalendar()
/*     */   {
/* 299 */     Calendar timeKeeper = Calendar.getInstance();
/* 300 */     timeKeeper.set(getYear(), getMonth(), getDay());
/* 301 */     return timeKeeper;
/*     */   }
/*     */ 
/*     */   private void arrangeDays()
/*     */   {
/* 309 */     Calendar hoje = Calendar.getInstance();
/* 310 */     this.dias.removeAll();
/*     */ 
/* 313 */     int cMonth = getMonth();
/* 314 */     int cYear = getYear();
/*     */ 
/* 316 */     Calendar timeKeeper = Calendar.getInstance();
/* 317 */     timeKeeper.set(cYear, cMonth, 1);
/* 318 */     timeKeeper.setTime(timeKeeper.getTime());
/* 319 */     int diaQueComeca = timeKeeper.get(7) - 1;
/* 320 */     if (diaQueComeca == 0) diaQueComeca = 7;
/*     */     int max;
/*     */     
/* 324 */     if ((cMonth == 1) && ((timeKeeper instanceof GregorianCalendar)) && (((GregorianCalendar)timeKeeper).isLeapYear(cYear)))
/*     */     {
/* 326 */       max = 29;
/*     */     }
/*     */     else {
/* 329 */       max = this.monthLength[cMonth];
/*     */     }
/* 331 */     if (this.diaAtual > max) {
/* 332 */       this.diaAtual = max;
/*     */     }
/* 334 */     timeKeeper.set(cYear, cMonth, max);
/* 335 */     timeKeeper.setTime(timeKeeper.getTime());
/* 336 */     int diaQueTermina = timeKeeper.get(7) - 1;
/* 337 */     if (diaQueTermina == 0) diaQueTermina = 7;
/*     */ 
/* 340 */     for (int i = 0; i < 7; i++) {
/* 341 */       this.dias.add(new WeekdayCell(this.diasSemana[i], this));
/*     */     }
/*     */ 
/* 347 */     for (int i = 1; i < diaQueComeca; i++) {
/* 348 */       this.dias.add(new WeekdayCell("", this));
/*     */     }
/*     */ 
/* 351 */     this.cells = new DayCell[max];
/* 352 */     for (int i = 0; i < max; i++)
/*     */     {
/* 354 */       if ((hoje.get(1) == timeKeeper.get(1)) && (hoje.get(2) == timeKeeper.get(2)) && (hoje.get(5) == timeKeeper.get(5) + 1))
/*     */       {
/* 358 */         this.cells[i] = new DayCell(i + 1, this, true);
/*     */       }
/*     */       else
/*     */       {
/* 362 */         this.cells[i] = new DayCell(i + 1, this, false);
/*     */       }
/*     */ 
/* 365 */       timeKeeper.set(cYear, cMonth, i + 1);
/* 366 */       timeKeeper.setTime(timeKeeper.getTime());
/*     */ 
/* 368 */       if (timeKeeper.get(7) == 1)
/* 369 */         this.cells[i].setForeground(new Color(255, 0, 0));
/* 370 */       else if (timeKeeper.get(7) == 7) {
/* 371 */         this.cells[i].setForeground(new Color(0, 155, 0));
/*     */       }
/* 373 */       this.dias.add(this.cells[i]);
/*     */     }
/*     */ 
/* 376 */     for (int i = diaQueTermina; i < 7; i++) {
/* 377 */       this.dias.add(new WeekdayCell("", this));
/*     */     }
/*     */ 
/* 380 */     this.cells[(this.diaAtual - 1)].activate();
/*     */ 
/* 382 */     this.dias.validate();
/*     */   }
/*     */ 
/*     */   public void previoValorSelecionado(ISpinnerModelEvent e)
/*     */   {
/* 392 */     if (e.getSource() == this.jsAno)
/* 393 */       setYear(getYear());
/*     */     else
/* 395 */       setMonth(getMonth());
/* 396 */     arrangeDays();
/*     */   }
/*     */ 
/*     */   public void proximoValorSelecionado(ISpinnerModelEvent e)
/*     */   {
/* 406 */     if (e.getSource() == this.jsAno)
/* 407 */       setYear(getYear());
/*     */     else
/* 409 */       setMonth(getMonth());
/* 410 */     arrangeDays();
/*     */   }
/*     */ 
/*     */   public void addCalendarListener(ICalendarEventListener listener)
/*     */   {
/* 420 */     this.listeners.add(ICalendarEventListener.class, listener);
/*     */   }
/*     */ 
/*     */   public void removeCalendarListener(ICalendarEventListener listener)
/*     */   {
/* 430 */     this.listeners.remove(ICalendarEventListener.class, listener);
/*     */   }
/*     */ 
/*     */   private void fireFechandoCalendario()
/*     */   {
/* 449 */     Object[] listeners = this.listeners.getListeners(ICalendarEventListener.class);
/*     */ 
/* 451 */     for (int i = 0; i < listeners.length; i++)
/* 452 */       ((ICalendarEventListener)listeners[i]).fechandoCalendario(new ICalendarEvent(this));
/*     */   }
/*     */ 
/*     */   private void fireAnoSelecionado()
/*     */   {
/* 460 */     Object[] listeners = this.listeners.getListeners(ICalendarEventListener.class);
/*     */ 
/* 462 */     for (int i = 0; i < listeners.length; i++)
/* 463 */       ((ICalendarEventListener)listeners[i]).anoSelecionado(new ICalendarEvent(this));
/*     */   }
/*     */ 
/*     */   private void fireMesSelecionado()
/*     */   {
/* 471 */     Object[] listeners = this.listeners.getListeners(ICalendarEventListener.class);
/*     */ 
/* 473 */     for (int i = 0; i < listeners.length; i++)
/* 474 */       ((ICalendarEventListener)listeners[i]).mesSelecionado(new ICalendarEvent(this));
/*     */   }
/*     */ 
/*     */   private void fireDiaSelecionado()
/*     */   {
/* 482 */     Object[] listeners = this.listeners.getListeners(ICalendarEventListener.class);
/*     */ 
/* 484 */     for (int i = 0; i < listeners.length; i++)
/* 485 */       ((ICalendarEventListener)listeners[i]).diaSelecionado(new ICalendarEvent(this));
/*     */   }
/*     */ 
/*     */   private class ICalendarPanelEventProxy
/*     */     implements AWTEventListener
/*     */   {
/*     */     private ICalendarPanelEventProxy()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void eventDispatched(AWTEvent event)
/*     */     {
/* 715 */       if (((event instanceof KeyEvent)) && (ICalendarPanel.this.getParent().getParent().getParent().isVisible()))
/*     */       {
/* 717 */         if ((((KeyEvent)event).getKeyCode() == 39) && (((KeyEvent)event).getModifiers() == 2) && (event.getID() == 401))
/*     */         {
/* 719 */           ICalendarPanel.this.setDay(ICalendarPanel.this.getDay() + 1);
/* 720 */         } else if ((((KeyEvent)event).getKeyCode() == 37) && (((KeyEvent)event).getModifiers() == 2) && (event.getID() == 401))
/*     */         {
/* 722 */           ICalendarPanel.this.setDay(ICalendarPanel.this.getDay() - 1);
/* 723 */         } else if ((((KeyEvent)event).getKeyCode() == 38) && (((KeyEvent)event).getModifiers() == 2) && (event.getID() == 401))
/*     */         {
/* 725 */           ICalendarPanel.this.setDay(ICalendarPanel.this.getDay() - 7);
/* 726 */         } else if ((((KeyEvent)event).getKeyCode() == 40) && (((KeyEvent)event).getModifiers() == 2) && (event.getID() == 401))
/*     */         {
/* 728 */           ICalendarPanel.this.setDay(ICalendarPanel.this.getDay() + 7);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class DayCell extends JComponent
/*     */     implements Serializable
/*     */   {
/*     */     private ICalendarPanel calendario;
/*     */     private int day;
/* 582 */     private Color corFonte = null;
/*     */ 
/* 584 */     private boolean selected = false;
/*     */ 
/* 586 */     private boolean isToday = false;
/*     */ 
/*     */     public DayCell(int newDay, ICalendarPanel frame, boolean isToday)
/*     */     {
/* 597 */       this.calendario = frame;
/* 598 */       this.day = newDay;
/* 599 */       this.isToday = isToday;
/* 600 */       setFont(this.calendario.getFont());
/* 601 */       addMouseListener(new Mouser());
/*     */     }
/*     */ 
/*     */     public void activate()
/*     */     {
/* 609 */       this.selected = true;
/*     */     }
/*     */ 
/*     */     public void deactivate()
/*     */     {
/* 617 */       this.selected = false;
/*     */     }
/*     */ 
/*     */     public void configuraFoco(boolean foco)
/*     */     {
/* 627 */       if (foco)
/*     */       {
/* 629 */         this.corFonte = getForeground();
/* 630 */         setForeground(Color.CYAN);
/*     */       }
/*     */       else {
/* 633 */         setForeground(this.corFonte);
/*     */       }
/*     */     }
/*     */ 
/*     */     public void paint(Graphics g)
/*     */     {
/* 642 */       if (this.isToday)
/*     */       {
/* 644 */         g.setColor(Color.ORANGE);
/* 645 */         g.fillOval(0, 0, getWidth(), getHeight());
/*     */       }
/*     */ 
/* 648 */       super.paint(g);
/*     */ 
/* 650 */       if (this.selected)
/*     */       {
/* 652 */         Color c = g.getColor();
/* 653 */         g.setColor(Color.RED);
/* 654 */         g.drawOval(0, 0, 20, 15);
/* 655 */         g.drawOval(1, 1, 20, 15);
/* 656 */         g.setColor(c);
/*     */       }
/*     */ 
/* 659 */       if (getForeground().equals(Color.CYAN)) {
/* 660 */         g.setColor(Color.CYAN);
/*     */       }
/* 662 */       g.drawString("" + this.day, 5, 12);
/*     */     }
/*     */ 
/*     */     private class Mouser extends MouseAdapter
/*     */     {
/*     */       private Mouser()
/*     */       {
/*     */       }
/*     */ 
/*     */       public void mouseClicked(MouseEvent e)
/*     */       {
/* 679 */         ICalendarPanel.DayCell.this.activate();
/* 680 */         ICalendarPanel.this.setDay(ICalendarPanel.DayCell.this.day);
/*     */       }
/*     */ 
/*     */       public void mouseEntered(MouseEvent e)
/*     */       {
/* 690 */         ((ICalendarPanel.DayCell)e.getSource()).configuraFoco(true);
/*     */       }
/*     */ 
/*     */       public void mouseExited(MouseEvent e)
/*     */       {
/* 700 */         ((ICalendarPanel.DayCell)e.getSource()).configuraFoco(false);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class WeekdayCell extends JComponent
/*     */     implements Serializable
/*     */   {
/* 547 */     private String day = null;
/*     */ 
/*     */     public WeekdayCell(String newDay, ICalendarPanel calendario)
/*     */     {
/* 557 */       setFont(calendario.getFont());
/* 558 */       setForeground(new Color(0, 0, 255));
/* 559 */       this.day = newDay;
/*     */     }
/*     */ 
/*     */     public void paint(Graphics g)
/*     */     {
/* 567 */       g.drawString(this.day, 2, 10);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class OkListener extends MouseAdapter
/*     */     implements ActionListener
/*     */   {
/*     */     private OkListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void mouseEntered(MouseEvent e)
/*     */     {
/* 502 */       if (e.getSource() == ICalendarPanel.this.ok)
/* 503 */         ICalendarPanel.this.ok.setBorder(new BevelBorder(0));
/*     */     }
/*     */ 
/*     */     public void mouseExited(MouseEvent e)
/*     */     {
/* 513 */       if (e.getSource() == ICalendarPanel.this.ok)
/* 514 */         ICalendarPanel.this.ok.setBorder(null);
/*     */     }
/*     */ 
/*     */     public void mousePressed(MouseEvent e)
/*     */     {
/* 524 */       if (e.getSource() == ICalendarPanel.this.ok)
/* 525 */         ICalendarPanel.this.ok.setBorder(new BevelBorder(1));
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent e)
/*     */     {
/* 535 */       if (e.getSource() == ICalendarPanel.this.ok)
/* 536 */         ICalendarPanel.this.fireFechandoCalendario();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.calendario.ICalendarPanel
 * JD-Core Version:    0.6.2
 */