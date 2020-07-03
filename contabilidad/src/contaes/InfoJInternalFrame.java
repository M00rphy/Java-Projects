/*     */ package contaes;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JLabel;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ 
/*     */ public class InfoJInternalFrame extends JInternalFrame
/*     */ {
/*  27 */   private String empresa = "No disponible";
/*  28 */   private String ejercicio = "No disponible";
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel2;
/*     */ 
/*     */   public InfoJInternalFrame()
/*     */   {
/*  32 */     initComponents();
/*  33 */     setTitle("Empresa activa");
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  47 */     this.jLabel1 = new JLabel();
/*  48 */     this.jLabel2 = new JLabel();
/*     */ 
/*  50 */     setBackground(new Color(203, 210, 255));
/*  51 */     setDefaultCloseOperation(0);
/*     */ 
/*  53 */     this.jLabel1.setFont(new Font("Arial", 0, 20));
/*  54 */     this.jLabel1.setText("Empresa no seleccionada");
/*     */ 
/*  56 */     this.jLabel2.setFont(new Font("Arial", 0, 20));
/*  57 */     this.jLabel2.setText("Ejercicio: (no disponible)");
/*     */ 
/*  59 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  60 */     getContentPane().setLayout(layout);
/*  61 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1, -1, 382, 32767).addContainerGap()).addGroup(layout.createSequentialGroup().addComponent(this.jLabel2, -1, 376, 32767).addGap(12, 12, 12)))));
/*     */ 
/*  73 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1, -2, 39, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel2, -2, 40, -2).addContainerGap(-1, 32767)));
/*     */ 
/*  83 */     pack();
/*     */   }
/*     */ 
/*     */   public void redibujar(String empresa, String ejercicio, Rectangle parentBounds) {
/*  87 */     this.empresa = empresa;
/*  88 */     this.ejercicio = ejercicio;
/*  89 */     this.jLabel1.setText(empresa);
/*  90 */     this.jLabel2.setText("Ejercicio: " + ejercicio);
/*     */ 
/*  96 */     FontMetrics fm = this.jLabel1.getFontMetrics(this.jLabel1.getFont());
/*  97 */     int ancho = Math.max(fm.stringWidth(empresa), fm.stringWidth("Ejercicio: " + ejercicio));
/*  98 */     ancho += 40;
/*  99 */     int alto = 140;
/* 100 */     int x = parentBounds.width - ancho;
/* 101 */     int y = parentBounds.height - alto;
/* 102 */     setBounds(x, y, ancho, alto);
/* 103 */     repaint();
/*     */   }
/*     */ 
/*     */   public void redibujar(Rectangle parentBounds)
/*     */   {
/* 121 */     this.jLabel1.setText(this.empresa);
/* 122 */     this.jLabel2.setText("Ejercicio: " + this.ejercicio);
/* 123 */     FontMetrics fm = this.jLabel1.getFontMetrics(new Font("Arial", 0, 20));
/* 124 */     int ancho = Math.max(fm.stringWidth(this.empresa), fm.stringWidth("Ejercicio: " + this.ejercicio));
/* 125 */     ancho += 40;
/* 126 */     int alto = 140;
/* 127 */     int x = parentBounds.width - ancho;
/* 128 */     int y = parentBounds.height - alto;
/* 129 */     setBounds(x, y, ancho, alto);
/* 130 */     repaint();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.InfoJInternalFrame
 * JD-Core Version:    0.6.2
 */