// Buffer SEM sincronização (Deitel, Fig. 5.4)
// Um único inteiro compartilhado, sem nenhum controle de acesso.
// Problema: nada impede o produtor de sobrescrever um valor que ainda não foi lido,
// nem o consumidor de ler o mesmo valor duas vezes (condição de corrida).

public class UnsynchronizedBuffer implements Buffer
{
   private int buffer = -1; // -1 = ainda não foi produzido nada

   public void set( int value )
   {
      System.err.println( Thread.currentThread().getName() +
         " writes " + value );

      buffer = value; // grava sem checar se o valor anterior já foi lido
   }

   public int get()
   {
      System.err.println( Thread.currentThread().getName() +
         " reads " + buffer );

      return buffer; // lê sem checar se existe um valor novo
   }
}

// Código original: Copyright 1992-2004 Deitel & Associates, Inc. e Pearson Education Inc.
