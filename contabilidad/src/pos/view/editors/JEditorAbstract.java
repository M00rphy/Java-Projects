/*     */ package pos.view.editors;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.UIDefaults;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.LineBorder;
/*     */ import pos.model.BasicException;
/*     */ 
/*     */ public abstract class JEditorAbstract extends JPanel
/*     */   implements EditorComponent
/*     */ {
/*     */   private EditorKeys editorkeys;
/*     */   private boolean m_bActive;
/*  35 */   private final Border m_borderactive = new CompoundBorder(new LineBorder(UIManager.getDefaults().getColor("TextField.selectionBackground")), new EmptyBorder(new Insets(1, 4, 1, 4)));
/*  36 */   private final Border m_borderinactive = new CompoundBorder(new LineBorder(UIManager.getDefaults().getColor("Button.darkShadow")), new EmptyBorder(new Insets(1, 4, 1, 4)));
/*     */   private JLabel m_jMode;
/*     */   private JButton m_jText;
/*     */   private JPanel panBackground;
/*     */ 
/*     */   public JEditorAbstract()
/*     */   {
/*  41 */     initComponents();
/*     */ 
/*  43 */     this.editorkeys = null;
/*  44 */     this.m_bActive = false;
/*  45 */     this.m_jText.setBorder(this.m_borderinactive); } 
/*     */   protected abstract int getMode();
/*     */ 
/*     */   protected abstract int getAlignment();
/*     */ 
/*     */   protected abstract String getEditMode();
/*     */ 
/*     */   protected abstract String getTextEdit();
/*     */ 
/*     */   protected abstract String getTextFormat() throws BasicException;
/*     */ 
/*     */   protected abstract void typeCharInternal(char paramChar);
/*     */ 
/*     */   protected abstract void transCharInternal(char paramChar);
/*     */ 
/*  57 */   public void typeChar(char c) { typeCharInternal(c);
/*  58 */     reprintText();
/*  59 */     firePropertyChange("Edition", null, null); }
/*     */ 
/*     */   public void transChar(char c)
/*     */   {
/*  63 */     transCharInternal(c);
/*  64 */     reprintText();
/*  65 */     firePropertyChange("Edition", null, null);
/*     */   }
/*     */ 
/*     */   public void addEditorKeys(EditorKeys ed) {
/*  69 */     this.editorkeys = ed;
/*     */   }
/*     */   public void deactivate() {
/*  72 */     setActive(false);
/*     */   }
/*     */   public Component getComponent() {
/*  75 */     return this;
/*     */   }
/*     */   public void activate() {
/*  78 */     if (isEnabled()) {
/*  79 */       this.editorkeys.setActive(this, getMode());
/*  80 */       setActive(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void setActive(boolean bValue) {
/*  85 */     this.m_bActive = bValue;
/*  86 */     this.m_jText.setBorder(this.m_bActive ? this.m_borderactive : this.m_borderinactive);
/*  87 */     reprintText();
/*     */   }
/*     */ 
/*     */   protected void reprintText()
/*     */   {
/*  92 */     this.m_jText.setHorizontalAlignment(getAlignment());
/*  93 */     if (this.m_bActive) {
/*  94 */       this.m_jMode.setText(getEditMode());
/*  95 */       this.m_jText.setText(getTextEdit());
/*  96 */       this.m_jText.setForeground(UIManager.getDefaults().getColor("Label.foreground"));
/*     */     } else {
/*  98 */       this.m_jMode.setText(null);
/*     */       try {
/* 100 */         this.m_jText.setText(getTextFormat());
/* 101 */         this.m_jText.setForeground(UIManager.getDefaults().getColor("Label.foreground"));
/*     */       } catch (BasicException e) {
/* 103 */         this.m_jText.setText(getTextEdit());
/* 104 */         this.m_jText.setForeground(Color.RED);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setEnabled(boolean b)
/*     */   {
/* 111 */     if (this.editorkeys != null) {
/* 112 */       this.editorkeys.setInactive(this);
/*     */     }
/* 114 */     this.panBackground.setBackground(b ? UIManager.getDefaults().getColor("TextField.background") : UIManager.getDefaults().getColor("TextField.disabledBackground"));
/*     */ 
/* 117 */     super.setEnabled(b);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/* 128 */     this.panBackground = new JPanel();
/* 129 */     this.m_jText = new JButton();
/* 130 */     this.m_jMode = new JLabel();
/*     */ 
/* 132 */     setLayout(new BorderLayout());
/*     */ 
/* 134 */     this.panBackground.setBackground(UIManager.getDefaults().getColor("TextField.background"));
/* 135 */     this.panBackground.setLayout(new BorderLayout());
/*     */ 
/* 137 */     this.m_jText.setBackground(UIManager.getDefaults().getColor("TextField.background"));
/* 138 */     this.m_jText.setContentAreaFilled(false);
/* 139 */     this.m_jText.setFocusPainted(false);
/* 140 */     this.m_jText.setFocusable(false);
/* 141 */     this.m_jText.setMinimumSize(new Dimension(100, 25));
/* 142 */     this.m_jText.setPreferredSize(new Dimension(100, 25));
/* 143 */     this.m_jText.setRequestFocusEnabled(false);
/* 144 */     this.m_jText.setVerticalAlignment(1);
/* 145 */     this.m_jText.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 147 */         JEditorAbstract.this.m_jTextActionPerformed(evt);
/*     */       }
/*     */     });
/* 150 */     this.panBackground.add(this.m_jText, "Center");
/*     */ 
/* 152 */     add(this.panBackground, "Center");
/*     */ 
/* 154 */     this.m_jMode.setFont(new Font("Dialog", 0, 9));
/* 155 */     this.m_jMode.setHorizontalAlignment(0);
/* 156 */     this.m_jMode.setVerticalAlignment(1);
/* 157 */     this.m_jMode.setPreferredSize(new Dimension(32, 0));
/* 158 */     add(this.m_jMode, "After");
/*     */   }
/*     */ 
/*     */   private void m_jTextActionPerformed(ActionEvent evt)
/*     */   {
/* 163 */     activate();
/*     */   }
/*     */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     pos.view.editors.JEditorAbstract
 * JD-Core Version:    0.6.2
 */