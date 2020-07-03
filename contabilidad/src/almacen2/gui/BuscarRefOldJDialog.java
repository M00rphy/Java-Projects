/*    */ package almacen2.gui;
/*    */ 
/*    */ import almacen2.data.MySQLConectionOldAlmacen;
/*    */ import java.awt.Color;
/*    */ import java.awt.Container;
/*    */ import java.awt.Font;
/*    */ import java.awt.Frame;
/*    */ import java.awt.GridBagConstraints;
/*    */ import java.awt.GridBagLayout;
/*    */ import java.awt.Insets;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.DecimalFormatSymbols;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTextArea;
/*    */ import javax.swing.JTextField;
/*    */ import javax.swing.JViewport;
/*    */ 
/*    */ public class BuscarRefOldJDialog extends JDialog
/*    */ {
/*    */   private ResultSet res;
/*    */   MySQLConectionOldAlmacen con;
/*    */   DecimalFormat fn;
/* 30 */   JPanel panel1 = new JPanel();
/* 31 */   JButton jButton1 = new JButton();
/* 32 */   JLabel jLabel1 = new JLabel();
/* 33 */   JTextArea jTextArea1 = new JTextArea();
/* 34 */   JScrollPane jScrollPane1 = new JScrollPane();
/* 35 */   JTextField jTextField1 = new JTextField();
/* 36 */   JButton jButton2 = new JButton();
/* 37 */   GridBagLayout gridBagLayout1 = new GridBagLayout();
/*    */ 
/*    */   public BuscarRefOldJDialog(Frame owner, String title) {
/* 40 */     super(owner, title);
/* 41 */     this.con = new MySQLConectionOldAlmacen();
/*    */     try {
/* 43 */       setDefaultCloseOperation(2);
/* 44 */       jbInit();
/* 45 */       pack();
/* 46 */       this.jTextField1.requestFocus();
/*    */     } catch (Exception exception) {
/* 48 */       exception.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public BuscarRefOldJDialog() {
/* 53 */     this(new Frame(), "Búsqueda de referencias");
/*    */   }
/*    */ 
/*    */   private void jbInit() throws Exception {
/* 57 */     DecimalFormatSymbols formato = new DecimalFormatSymbols();
/* 58 */     formato.setDecimalSeparator('.');
/* 59 */     this.fn = new DecimalFormat("#.##", formato);
/* 60 */     this.panel1.setLayout(this.gridBagLayout1);
/* 61 */     this.jButton1.setBackground(Color.red);
/* 62 */     this.jButton1.setText("Cancelar");
/* 63 */     this.jButton1.addActionListener(new Busca_ref_jButton1_actionAdapter(this));
/* 64 */     this.jLabel1.setFont(new Font("Dialog", 0, 12));
/* 65 */     this.jLabel1.setText("Referencia a buscar:");
/* 66 */     this.jTextArea1.setEditable(false);
/* 67 */     this.jTextArea1.setFont(new Font("Courier", 0, 12));
/* 68 */     setModal(true);
/* 69 */     this.jTextField1.setText("");
/* 70 */     this.jButton2.setBackground(Color.cyan);
/* 71 */     this.jButton2.setText("Buscar");
/* 72 */     this.jButton2.addActionListener(new Busca_ref_jButton2_actionAdapter(this));
/* 73 */     this.panel1.setBackground(new Color(204, 204, 255));
/* 74 */     getContentPane().add(this.panel1);
/* 75 */     this.panel1.add(this.jScrollPane1, new GridBagConstraints(0, 1, 3, 1, 1.0D, 1.0D, 10, 1, new Insets(11, 23, 0, 31), 496, 117));
/*    */ 
/* 77 */     this.panel1.add(this.jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(39, 23, 0, 0), 7, 0));
/*    */ 
/* 79 */     this.panel1.add(this.jTextField1, new GridBagConstraints(1, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(35, 7, 0, 0), 56, 0));
/*    */ 
/* 81 */     this.panel1.add(this.jButton2, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(29, 13, 0, 170), 13, 0));
/*    */ 
/* 83 */     this.panel1.add(this.jButton1, new GridBagConstraints(1, 2, 2, 1, 0.0D, 0.0D, 10, 0, new Insets(13, 57, 31, 250), 6, 0));
/*    */ 
/* 85 */     this.jScrollPane1.getViewport().add(this.jTextArea1);
/* 86 */     setResizable(true);
/*    */   }
/*    */ 
/*    */   public void jButton1_actionPerformed(ActionEvent e) {
/* 90 */     dispose();
/*    */   }
/*    */ 
/*    */   public void jButton2_actionPerformed(ActionEvent e) {
/* 94 */     String ref = this.jTextField1.getText();
/* 95 */     if ((ref != null) && (!ref.equals(""))) {
/* 96 */       this.jTextArea1.setText("");
/*    */       try
/*    */       {
/* 99 */         this.res = this.con.getRes("SELECT * FROM productos WHERE RefProv LIKE '%" + ref + "%'");
/* 100 */         if (this.res.next())
/*    */           do
/*    */           {
/* 103 */             String r = "                                         ";
/* 104 */             String[] ficha = new String[8];
/* 105 */             ficha[0] = this.res.getString(1);
/* 106 */             if (ficha[0].length() == 7) {
/* 107 */               ficha[0] = ("0" + ficha[0]);
/*    */             }
/* 109 */             ref = this.res.getString(2);
/* 110 */             ficha[1] = this.res.getString(3);
/* 111 */             ficha[2] = this.res.getString(4);
/* 112 */             ficha[3] = this.res.getString(5);
/* 113 */             ficha[4] = this.res.getString(6);
/* 114 */             if (ficha[4].equals("1960-01-01")) {
/* 115 */               ficha[4] = "          ";
/*    */             }
/* 117 */             ficha[5] = this.fn.format(this.res.getDouble(7));
/* 118 */             ficha[6] = this.fn.format(this.res.getDouble(8));
/* 119 */             ficha[7] = this.fn.format(this.res.getDouble(9));
/* 120 */             if (ficha[7].equals("0")) {
/* 121 */               ficha[7] = "";
/*    */             }
/* 123 */             String linea = ficha[0] + " " + r.substring(0, 18 - ref.length()) + ref + " " + ficha[1] + r.substring(0, 41 - this.res.getString(3).length()) + ficha[2] + " " + ficha[3] + " " + ficha[4] + " ";
/* 124 */             for (int i = 5; i < 8; i++) {
/* 125 */               linea = linea + r.substring(0, 10 - ficha[i].length()) + ficha[i];
/*    */             }
/* 127 */             linea = linea + "\n";
/* 128 */             this.jTextArea1.append(linea);
/* 129 */           }while (this.res.next());
/*    */         else
/* 131 */           this.jTextArea1.setText("No se encontró la referencia");
/*    */       }
/*    */       catch (SQLException exc) {
/* 134 */         exc.printStackTrace();
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     almacen2.gui.BuscarRefOldJDialog
 * JD-Core Version:    0.6.2
 */