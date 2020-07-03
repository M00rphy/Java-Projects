/*     */ package contaes.calendario;
/*     */ 
/*     */ import contaes.calendario.event.ICalendarEvent;
/*     */ import contaes.calendario.event.ICalendarEventListener;
/*     */ import java.awt.Frame;
/*     */ import java.util.Date;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ public class ICalendarFrame extends JDialog
/*     */   implements ICalendar, ICalendarEventListener
/*     */ {
/*  50 */   private ICalendarField texto = null;
/*     */ 
/*  52 */   private boolean abilitado = false;
/*     */ 
/*     */   public ICalendarFrame(Frame owner, boolean modal)
/*     */   {
/*  62 */     super(owner, modal);
/*  63 */     ICalendarPanel painel = new ICalendarPanel();
/*  64 */     painel.addCalendarListener(this);
/*  65 */     setContentPane(painel);
/*  66 */     setSize(180, 150);
/*     */   }
/*     */ 
/*     */   public ICalendarFrame(Frame owner, boolean modal, Date date, ICalendarField texto)
/*     */   {
/*  79 */     this(owner, modal);
/*  80 */     this.texto = texto;
/*     */   }
/*     */ 
/*     */   public void setTextComponent(ICalendarField text)
/*     */   {
/*  88 */     this.texto = text;
/*     */   }
/*     */ 
/*     */   public void setDate(Date data)
/*     */   {
/*  98 */     ((ICalendarPanel)getContentPane()).setDate(data);
/*     */   }
/*     */ 
/*     */   public void diaSelecionado(ICalendarEvent e)
/*     */   {
/* 108 */     this.texto.setCalendar(((ICalendarPanel)e.getSource()).getCalendar());
/*     */   }
/*     */ 
/*     */   public void mesSelecionado(ICalendarEvent e)
/*     */   {
/* 118 */     this.texto.setCalendar(((ICalendarPanel)e.getSource()).getCalendar());
/*     */   }
/*     */ 
/*     */   public void anoSelecionado(ICalendarEvent e)
/*     */   {
/* 128 */     this.texto.setCalendar(((ICalendarPanel)e.getSource()).getCalendar());
/*     */   }
/*     */ 
/*     */   public void fechandoCalendario(ICalendarEvent e)
/*     */   {
/* 138 */     setVisible(false);
/* 139 */     this.texto.requestFocus();
/*     */   }
/*     */ 
/*     */   public void abrindoCalendario(ICalendarEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setAbilitado(boolean enabled)
/*     */   {
/* 159 */     if (enabled)
/* 160 */       SwingUtilities.updateComponentTreeUI(this);
/* 161 */     this.abilitado = enabled;
/*     */   }
/*     */ 
/*     */   public boolean isAbilitado()
/*     */   {
/* 169 */     return this.abilitado;
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.calendario.ICalendarFrame
 * JD-Core Version:    0.6.2
 */