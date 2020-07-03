/*    */ package contaes.auxiliar;
/*    */ 
/*    */ import java.awt.Container;
/*    */ import java.io.OutputStream;
/*    */ import java.io.PrintStream;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTextArea;
/*    */ 
/*    */ public class ConsoleWindow
/*    */ {
/*    */   public static final int DEFAULT_WIDTH = 300;
/*    */   public static final int DEFAULT_HEIGHT = 200;
/*    */   public static final int DEFAULT_LEFT = 200;
/*    */   public static final int DEFAULT_TOP = 200;
/*    */ 
/*    */   public static void init()
/*    */   {
/* 17 */     JFrame frame = new JFrame();
/* 18 */     frame.setTitle("Consola Contaes");
/* 19 */     final JTextArea output = new JTextArea();
/* 20 */     output.setEditable(false);
/* 21 */     frame.getContentPane().add(new JScrollPane(output));
/* 22 */     frame.setSize(300, 200);
/* 23 */     frame.setLocation(200, 200);
/* 24 */     frame.setFocusableWindowState(false);
/*    */ 
/* 26 */     frame.setVisible(true);
/*    */ 
/* 30 */     PrintStream consoleStream = new PrintStream(new OutputStream()
/*    */     {
/*    */       public void write(int b)
/*    */       {
/*    */       }
/*    */ 
/*    */       public void write(byte[] b, int off, int len) {
/* 37 */         output.append(new String(b, off, len));
/*    */       }
/*    */     });
/* 42 */     System.setOut(consoleStream);
/* 43 */     System.setErr(consoleStream);
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.auxiliar.ConsoleWindow
 * JD-Core Version:    0.6.2
 */