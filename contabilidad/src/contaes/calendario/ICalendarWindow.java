/*     */ package contaes.calendario;
/*     */ 
/*     */ import contaes.calendario.event.ICalendarEvent;
/*     */ import contaes.calendario.event.ICalendarEventListener;
/*     */ import java.awt.Frame;
/*     */ import java.awt.KeyboardFocusManager;
/*     */ import java.util.Date;
/*     */ import javax.swing.JWindow;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.border.BevelBorder;
/*     */ 
/*     */ public class ICalendarWindow extends JWindow
/*     */   implements ICalendar, ICalendarEventListener
/*     */ {
/*  52 */   private ICalendarField texto = null;
/*  53 */   private boolean abilitado = false;
/*     */ 
/*     */   public ICalendarWindow(Frame owner)
/*     */   {
/*  61 */     super(owner == null ? KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow() : owner);
/*  62 */     setVisible(false);
/*  63 */     ICalendarPanel painel = new ICalendarPanel();
/*  64 */     painel.setBorder(new BevelBorder(0));
/*  65 */     setContentPane(painel);
/*  66 */     painel.addCalendarListener(this);
/*  67 */     setSize(180, 150);
/*     */   }
/*     */ 
/*     */   public ICalendarWindow(Frame owner, Date date, ICalendarField texto)
/*     */   {
/*  77 */     this(owner);
/*  78 */     this.texto = texto;
/*     */   }
/*     */ 
/*     */   public void setTextComponent(ICalendarField text)
/*     */   {
/*  86 */     this.texto = text;
/*     */   }
/*     */ 
/*     */   public void setDate(Date data)
/*     */   {
/*  95 */     ((ICalendarPanel)getContentPane()).setDate(data);
/*     */   }
/*     */ 
/*     */   public void diaSelecionado(ICalendarEvent e)
/*     */   {
/* 103 */     if (this.texto != null)
/* 104 */       this.texto.setCalendar(((ICalendarPanel)e.getSource()).getCalendar());
/*     */   }
/*     */ 
/*     */   public void mesSelecionado(ICalendarEvent e)
/*     */   {
/* 112 */     if (this.texto != null)
/* 113 */       this.texto.setCalendar(((ICalendarPanel)e.getSource()).getCalendar());
/*     */   }
/*     */ 
/*     */   public void anoSelecionado(ICalendarEvent e)
/*     */   {
/* 121 */     if (this.texto != null)
/* 122 */       this.texto.setCalendar(((ICalendarPanel)e.getSource()).getCalendar());
/*     */   }
/*     */ 
/*     */   public void fechandoCalendario(ICalendarEvent e)
/*     */   {
/* 130 */     setVisible(false);
/* 131 */     setAbilitado(false);
/* 132 */     this.texto.requestFocus();
/*     */   }
/*     */ 
/*     */   public void abrindoCalendario(ICalendarEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setAbilitado(boolean enabled)
/*     */   {
/* 148 */     if (enabled)
/* 149 */       SwingUtilities.updateComponentTreeUI(this);
/* 150 */     this.abilitado = enabled;
/*     */   }
/*     */ 
/*     */   public boolean isAbilitado()
/*     */   {
/* 158 */     return this.abilitado;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.calendario.ICalendarWindow
 * JD-Core Version:    0.6.2
 */