/*     */ package pos.view.calendar;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class JClockPanel extends JPanel
/*     */ {
/*  35 */   private static Calendar m_calendar = new GregorianCalendar();
/*     */   private Date m_date;
/*     */   private boolean m_bSeconds;
/*     */   private long m_lPeriod;
/*     */ 
/*     */   public JClockPanel()
/*     */   {
/*  43 */     this(true);
/*     */   }
/*     */ 
/*     */   public JClockPanel(boolean bSeconds)
/*     */   {
/*  48 */     initComponents();
/*     */ 
/*  50 */     this.m_bSeconds = bSeconds;
/*  51 */     this.m_date = null;
/*  52 */     this.m_lPeriod = 0L;
/*     */   }
/*     */ 
/*     */   public void setSecondsVisible(boolean bValue) {
/*  56 */     this.m_bSeconds = bValue;
/*  57 */     repaint();
/*     */   }
/*     */   public boolean isSecondsVisible() {
/*  60 */     return this.m_bSeconds;
/*     */   }
/*     */   public void setPeriod(long period) {
/*  63 */     if (period >= 0L) {
/*  64 */       this.m_lPeriod = period;
/*  65 */       repaint();
/*     */     }
/*     */   }
/*     */ 
/*  69 */   public long getPeriod() { return this.m_lPeriod; }
/*     */ 
/*     */   public void setTime(Date dDate)
/*     */   {
/*  73 */     this.m_date = dDate;
/*  74 */     repaint();
/*     */   }
/*     */ 
/*     */   public Date getTime() {
/*  78 */     return this.m_date;
/*     */   }
/*     */ 
/*     */   public void paintComponent(Graphics g)
/*     */   {
/*  83 */     super.paintComponent(g);
/*     */ 
/*  85 */     int width = getWidth();
/*  86 */     int height = getHeight();
/*     */ 
/*  88 */     double dhour = 0.0D;
/*  89 */     double dminute = 0.0D;
/*  90 */     double dsecond = 0.0D;
/*     */ 
/*  93 */     if (this.m_date != null) {
/*  94 */       m_calendar.setTime(this.m_date);
/*  95 */       dhour = m_calendar.get(11);
/*  96 */       dminute = m_calendar.get(12);
/*  97 */       dsecond = m_calendar.get(13);
/*     */     }
/*     */ 
/* 100 */     Graphics2D g2 = (Graphics2D)g;
/* 101 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */ 
/* 104 */     Paint oldPainter = g2.getPaint();
/* 105 */     AffineTransform oldt = g2.getTransform();
/*     */ 
/* 112 */     int icenterx = width / 2;
/* 113 */     int icentery = height / 2;
/* 114 */     int iradius = Math.min(icenterx, icentery);
/*     */ 
/* 117 */     g2.transform(AffineTransform.getTranslateInstance(icenterx, icentery));
/* 118 */     g2.transform(AffineTransform.getScaleInstance(iradius / 1100.0D, iradius / 1100.0D));
/* 119 */     AffineTransform mytrans = g2.getTransform();
/*     */ 
/* 122 */     g2.setPaint(isEnabled() ? new GradientPaint(-1200.0F, -1200.0F, Color.BLUE, 1200.0F, 1200.0F, Color.CYAN) : new GradientPaint(-1200.0F, -1200.0F, Color.GRAY, 1200.0F, 1200.0F, Color.LIGHT_GRAY));
/*     */ 
/* 125 */     g2.fillOval(-1000, -1000, 2000, 2000);
/* 126 */     g2.setPaint(isEnabled() ? new GradientPaint(-1200.0F, -1200.0F, Color.CYAN, 1200.0F, 1200.0F, Color.BLUE) : new GradientPaint(-1200.0F, -1200.0F, Color.LIGHT_GRAY, 1200.0F, 1200.0F, Color.GRAY));
/*     */ 
/* 129 */     g2.fillOval(-900, -900, 1800, 1800);
/* 130 */     g2.setColor(Color.BLACK);
/* 131 */     g2.drawOval(-1000, -1000, 2000, 2000);
/*     */ 
/* 134 */     for (int i = 0; i < 60; i++) {
/* 135 */       g2.setColor(Color.WHITE);
/* 136 */       g2.fillRect(900, -5, 50, 10);
/* 137 */       g2.transform(AffineTransform.getRotateInstance(0.1047197551196598D));
/*     */     }
/*     */ 
/* 141 */     g2.setTransform(mytrans);
/* 142 */     for (int i = 0; i < 12; i++) {
/* 143 */       g2.setColor(Color.WHITE);
/* 144 */       g2.fillRect(800, -15, 150, 30);
/*     */ 
/* 147 */       g2.transform(AffineTransform.getRotateInstance(0.5235987755982988D));
/*     */     }
/*     */ 
/* 150 */     if (this.m_date != null)
/*     */     {
/* 152 */       g2.setTransform(mytrans);
/* 153 */       g2.transform(AffineTransform.getRotateInstance((dhour + dminute / 60.0D) * 3.141592653589793D / 6.0D));
/*     */ 
/* 155 */       if (this.m_lPeriod > 0L)
/*     */       {
/* 157 */         int iArc = (int)(this.m_lPeriod / 120000L);
/* 158 */         g2.setColor(new Color(255, 255, 255, 100));
/* 159 */         g2.fillArc(-1000, -1000, 2000, 2000, 90 - iArc, iArc);
/* 160 */         g2.setColor(Color.DARK_GRAY);
/* 161 */         g2.drawArc(-1000, -1000, 2000, 2000, 90 - iArc, iArc);
/*     */       }
/*     */       else {
/* 164 */         g2.setColor(Color.WHITE);
/* 165 */         g2.fillPolygon(new int[] { 0, -35, 0, 35 }, new int[] { 100, 0, -600, 0 }, 4);
/* 166 */         g2.setColor(Color.DARK_GRAY);
/* 167 */         g2.drawPolygon(new int[] { 0, -35, 0, 35 }, new int[] { 100, 0, -600, 0 }, 4);
/*     */ 
/* 170 */         g2.setTransform(mytrans);
/* 171 */         g2.transform(AffineTransform.getRotateInstance(dminute * 3.141592653589793D / 30.0D));
/* 172 */         g2.setColor(Color.WHITE);
/* 173 */         g2.fillPolygon(new int[] { 0, -35, 0, 35 }, new int[] { 100, 0, -900, 0 }, 4);
/* 174 */         g2.setColor(Color.DARK_GRAY);
/* 175 */         g2.drawPolygon(new int[] { 0, -35, 0, 35 }, new int[] { 100, 0, -900, 0 }, 4);
/*     */ 
/* 178 */         if (this.m_bSeconds) {
/* 179 */           g2.setTransform(mytrans);
/* 180 */           g2.transform(AffineTransform.getRotateInstance(dsecond * 3.141592653589793D / 30.0D));
/* 181 */           g2.setColor(Color.YELLOW);
/* 182 */           g2.fillPolygon(new int[] { -15, 0, 15 }, new int[] { 200, -900, 200 }, 3);
/* 183 */           g2.setColor(Color.DARK_GRAY);
/* 184 */           g2.drawPolygon(new int[] { -15, 0, 15 }, new int[] { 200, -900, 200 }, 3);
/*     */ 
/* 186 */           g2.setTransform(mytrans);
/* 187 */           g2.setColor(Color.YELLOW);
/* 188 */           g2.fillOval(-25, -25, 50, 50);
/* 189 */           g2.setColor(Color.DARK_GRAY);
/* 190 */           g2.drawOval(-25, -25, 50, 50);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 196 */     g2.setColor(Color.WHITE);
/* 197 */     g2.fillOval(-10, -10, 20, 20);
/* 198 */     g2.setColor(Color.BLACK);
/* 199 */     g2.drawOval(-10, -10, 20, 20);
/*     */ 
/* 202 */     g2.setTransform(oldt);
/* 203 */     g2.setPaint(oldPainter);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.calendar.JClockPanel
 * JD-Core Version:    0.6.2
 */