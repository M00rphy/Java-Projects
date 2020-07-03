/*     */ package contaes.calendario;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFormattedTextField;
/*     */ import javax.swing.JFormattedTextField.AbstractFormatterFactory;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.event.AncestorEvent;
/*     */ import javax.swing.event.AncestorListener;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import javax.swing.text.DateFormatter;
/*     */ import javax.swing.text.DefaultFormatterFactory;
/*     */ 
/*     */ public class ICalendarTextField extends JPanel
/*     */   implements Serializable, ICalendarField, AncestorListener
/*     */ {
/*  66 */   JComponent componente = null;
/*     */   private JFormattedTextField text;
/*     */   private JButton change;
/*  78 */   private ICalendar calendario = null;
/*     */   private DateFormat format;
/*  86 */   private EventListenerList listeners = new EventListenerList();
/*     */ 
/*     */   public ICalendarTextField()
/*     */   {
/*  92 */     this.format = DateFormat.getDateInstance(2);
/*  93 */     this.format.setCalendar(Calendar.getInstance());
/*     */ 
/*  95 */     DateFormatter formatoDataExibicao = new DateFormatter(this.format);
/*  96 */     DateFormatter formatoDataEdicao = new DateFormatter(new SimpleDateFormat("ddMMyyyy"));
/*     */ 
/*  98 */     setOpaque(false);
/*  99 */     setLayout(null);
/* 100 */     this.text = new JFormattedTextField();
/* 101 */     this.text.setFormatterFactory(new DefaultFormatterFactory(formatoDataExibicao, formatoDataExibicao, formatoDataEdicao));
/*     */ 
/* 103 */     this.text.addKeyListener(new IntroAction());
/* 104 */     this.text.setPreferredSize(new Dimension(100, 24));
/* 105 */     add(this.text);
/*     */ 
/* 107 */     this.change = new JButton(new ImageIcon(getClass().getResource("/contaes/iconos/date.png")));
/*     */ 
/* 109 */     this.change.addActionListener(new InnerAction());
/* 110 */     this.change.setPreferredSize(new Dimension(100, 24));
/* 111 */     add(this.change);
/*     */ 
/* 113 */     setName("ICalandarTextField");
/* 114 */     setPreferredSize(new Dimension(100, 26));
/*     */ 
/* 116 */     addAncestorListener(this);
/*     */   }
/*     */ 
/*     */   public ICalendarTextField(JFormattedTextField.AbstractFormatterFactory f)
/*     */   {
/* 126 */     this();
/* 127 */     this.text.setFormatterFactory(f);
/*     */   }
/*     */ 
/*     */   public ICalendarTextField(ICalendar calendario)
/*     */   {
/* 137 */     this();
/* 138 */     this.calendario = calendario;
/* 139 */     calendario.setVisible(false);
/* 140 */     calendario.setTextComponent(this);
/* 141 */     this.change.setEnabled(true);
/*     */   }
/*     */ 
/*     */   public void setBounds(int x, int y, int width, int height)
/*     */   {
/* 153 */     super.setBounds(x, y, width, height);
/* 154 */     this.text.setBounds(0, 0, width - 22, height);
/* 155 */     this.change.setBounds(width - 20, 0, 20, height);
/*     */   }
/*     */ 
/*     */   public void setMask(JFormattedTextField.AbstractFormatterFactory f)
/*     */   {
/* 164 */     this.text.setFormatterFactory(f);
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/* 173 */     return this.text.getText();
/*     */   }
/*     */ 
/*     */   public void setText(String datestring)
/*     */   {
/* 182 */     this.text.setValue(datestring);
/* 183 */     fireDataAlterada();
/*     */   }
/*     */ 
/*     */   public synchronized void setCalendar(Calendar timeKeeper)
/*     */   {
/* 193 */     this.format.setCalendar(timeKeeper);
/* 194 */     this.text.setText(this.format.format(this.format.getCalendar().getTime()));
/* 195 */     fireDataAlterada();
/*     */   }
/*     */ 
/*     */   public Calendar getCalendar()
/*     */   {
/* 204 */     return this.format.getCalendar();
/*     */   }
/*     */ 
/*     */   public void setDate(Date time)
/*     */   {
/* 213 */     this.text.setText(time == null ? "" : this.format.format(time));
/* 214 */     fireDataAlterada();
/*     */   }
/*     */ 
/*     */   public Date getDate()
/*     */   {
/* 223 */     if (this.text.getText().trim().equals("")) {
/* 224 */       return null;
/*     */     }
/* 226 */     return this.format.getCalendar().getTime();
/*     */   }
/*     */ 
/*     */   public void setEnabled(boolean enabled)
/*     */   {
/* 235 */     this.text.setEnabled(enabled);
/* 236 */     this.change.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   public void ancestorAdded(AncestorEvent event)
/*     */   {
/* 243 */     if (this.calendario != null) {
/* 244 */       this.calendario.setTextComponent(this);
/* 245 */       if ((isEnabled()) && (this.calendario.isAbilitado()))
/* 246 */         this.calendario.setVisible(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void ancestorMoved(AncestorEvent event)
/*     */   {
/* 255 */     if ((this.calendario != null) && (isShowing()) && (isEnabled()) && (this.calendario.isAbilitado()))
/* 256 */       if (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getLocationOnScreen().getY() - getHeight() < this.calendario.getSize().getHeight())
/* 257 */         this.calendario.setLocation((int)getLocationOnScreen().getX(), (int)(getLocationOnScreen().getY() - this.calendario.getSize().getHeight()));
/*     */       else
/* 259 */         this.calendario.setLocation((int)getLocationOnScreen().getX(), (int)getLocationOnScreen().getY() + getHeight());
/*     */   }
/*     */ 
/*     */   public void ancestorRemoved(AncestorEvent event)
/*     */   {
/* 268 */     if (this.calendario != null) {
/* 269 */       this.calendario.setVisible(false);
/* 270 */       this.calendario.setAbilitado(false);
/* 271 */       this.calendario.setTextComponent(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addActionListener(ActionListener l)
/*     */   {
/* 280 */     this.listeners.add(ActionListener.class, l);
/*     */   }
/*     */ 
/*     */   public void removeActionListener(ActionListener l)
/*     */   {
/* 289 */     this.listeners.remove(ActionListener.class, l);
/*     */   }
/*     */ 
/*     */   private void fireDataAlterada() {
/* 293 */     Object[] listeners = this.listeners.getListeners(ActionListener.class);
/*     */ 
/* 295 */     for (int i = 0; i < listeners.length; i++)
/* 296 */       ((ActionListener)listeners[i]).actionPerformed(new ActionEvent(this, 0, ""));
/*     */   }
/*     */ 
/*     */   public void requestFocus()
/*     */   {
/* 304 */     this.text.requestFocus();
/*     */   }
/*     */ 
/*     */   public boolean requestFocus(boolean temporary)
/*     */   {
/* 311 */     return this.text.requestFocus(temporary);
/*     */   }
/*     */ 
/*     */   public void setSelectionEnd(int selectionEnd)
/*     */   {
/* 318 */     this.text.setSelectionEnd(selectionEnd);
/*     */   }
/*     */ 
/*     */   public void setSelectionStart(int selectionStart)
/*     */   {
/* 325 */     this.text.setSelectionStart(selectionStart);
/*     */   }
/*     */ 
/*     */   public void setToolTipText(String texto)
/*     */   {
/* 333 */     super.setToolTipText(texto);
/* 334 */     this.text.setToolTipText(texto);
/* 335 */     this.change.setToolTipText(texto);
/*     */   }
/*     */ 
/*     */   public void setComponente(JComponent componente)
/*     */   {
/* 345 */     this.componente = componente;
/*     */   }
/*     */ 
/*     */   private class IntroAction extends KeyAdapter
/*     */   {
/*     */     private IntroAction()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void keyPressed(KeyEvent e)
/*     */     {
/* 398 */       int tecla = e.getKeyCode();
/* 399 */       Object campo = e.getSource();
/* 400 */       if ((tecla == 10) && (campo == ICalendarTextField.this.text)) {
/* 401 */         if (ICalendarTextField.this.text.getText().equals("")) {
/* 402 */           SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
/* 403 */           ICalendarTextField.this.text.setText(sdf.format(new Date()));
/*     */         }
/*     */ 
/* 406 */         if (ICalendarTextField.this.componente != null)
/* 407 */           ICalendarTextField.this.componente.requestFocus();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class InnerAction
/*     */     implements ActionListener
/*     */   {
/*     */     private InnerAction()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent e)
/*     */     {
/* 359 */       if (e.getSource() == ICalendarTextField.this.change) {
/* 360 */         if (ICalendarTextField.this.calendario == null) {
/* 361 */           ICalendarTextField.this.calendario = new ICalendarWindow(null);
/* 362 */           ICalendarTextField.this.calendario.setTextComponent(ICalendarTextField.this);
/*     */         }
/*     */         try {
/* 365 */           ICalendarTextField.this.calendario.setDate((Date)new DateFormatter(ICalendarTextField.this.format).stringToValue(ICalendarTextField.this.text.getText()));
/*     */         } catch (ParseException pe) {
/* 367 */           ICalendarTextField.this.calendario.setDate(new Date());
/*     */         }
/*     */ 
/* 371 */         if (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - ICalendarTextField.this.getLocationOnScreen().getY() - ICalendarTextField.this.getHeight() < ICalendarTextField.this.calendario.getSize().getHeight())
/* 372 */           ICalendarTextField.this.calendario.setLocation((int)ICalendarTextField.this.getLocationOnScreen().getX(), (int)(ICalendarTextField.this.getLocationOnScreen().getY() - ICalendarTextField.this.calendario.getSize().getHeight()));
/*     */         else {
/* 374 */           ICalendarTextField.this.calendario.setLocation((int)ICalendarTextField.this.getLocationOnScreen().getX(), (int)ICalendarTextField.this.getLocationOnScreen().getY() + ICalendarTextField.this.getHeight());
/*     */         }
/* 376 */         ICalendarTextField.this.calendario.setVisible(true);
/* 377 */         ICalendarTextField.this.calendario.setAbilitado(true);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.calendario.ICalendarTextField
 * JD-Core Version:    0.6.2
 */