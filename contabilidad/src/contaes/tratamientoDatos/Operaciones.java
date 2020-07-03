/*    */ package contaes.tratamientoDatos;
/*    */ 
/*    */ public class Operaciones
/*    */ {
/*    */   public int[] ordenarArrayEnteros(int[] array)
/*    */   {
/*  8 */     for (int i = 0; i < array.length - 1; i++) {
/*  9 */       for (int j = i + 1; j < array.length; j++) {
/* 10 */         if (array[j] < array[i]) {
/* 11 */           int temp = array[i];
/* 12 */           array[i] = array[j];
/* 13 */           array[j] = temp;
/*    */         }
/*    */       }
/*    */     }
/* 17 */     return array;
/*    */   }
/*    */ 
/*    */   public String completarCuenta(String codigo) {
/* 21 */     int largo = codigo.length();
/* 22 */     if ((largo > 0) && (largo < 8)) {
/* 23 */       String ceros = "";
/* 24 */       for (int x = largo; x < 9; x++) {
/* 25 */         ceros = ceros + "0";
/*    */       }
/* 27 */       if (codigo.indexOf(".") > 0) codigo = codigo.replaceFirst("[.]", ceros); else
/* 28 */         codigo = (codigo + ceros).substring(0, 8);
/*    */     }
/* 30 */     return codigo;
/*    */   }
/*    */ }

/* Location:           /media/sda1/contaes4/contaes4.jar
 * Qualified Name:     contaes.tratamientoDatos.Operaciones
 * JD-Core Version:    0.6.2
 */