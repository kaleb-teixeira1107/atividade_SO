// Buffer COM monitor Java (Deitel, Fig. 6.4)
// synchronized garante exclusão mútua: só uma thread por vez dentro de set/get.
// wait() e notify() fazem o papel das variáveis condicionais do monitor.

public class SynchronizedBuffer implements Buffer
{
   private int buffer = -1;
   private int occupiedBuffers = 0; // 0 = vazio, 1 = tem valor para ler

   public synchronized void set( int value )
   {
      String name = Thread.currentThread().getName();

      // Buffer cheio: o produtor espera o consumidor ler.
      // É "while" e não "if" porque, ao acordar, a condição precisa ser testada de novo.
      while ( occupiedBuffers == 1 )
      {
         try
         {
            System.err.println( name + " tries to write." );
            displayState( "Buffer full. " + name + " waits." );
            wait(); // libera o lock e dorme até alguém chamar notify()
         }
         catch ( InterruptedException exception )
         {
            exception.printStackTrace();
         }
      }

      buffer = value;
      ++occupiedBuffers;

      displayState( name + " writes " + buffer );

      notify(); // acorda o consumidor, se ele estiver esperando
   }

   public synchronized int get()
   {
      String name = Thread.currentThread().getName();

      // Buffer vazio: o consumidor espera o produtor gravar
      while ( occupiedBuffers == 0 )
      {
         try
         {
            System.err.println( name + " tries to read." );
            displayState( "Buffer empty. " + name + " waits." );
            wait();
         }
         catch ( InterruptedException exception )
         {
            exception.printStackTrace();
         }
      }

      --occupiedBuffers;

      displayState( name + " reads " + buffer );

      notify(); // acorda o produtor, se ele estiver esperando

      return buffer;
   }

   // Só para imprimir a tabela: operação | valor no buffer | ocupado (0/1)
   public void displayState( String operation )
   {
      StringBuffer outputLine = new StringBuffer( operation );
      outputLine.setLength( 40 );
      outputLine.append( buffer + "\t\t" + occupiedBuffers );
      System.err.println( outputLine );
      System.err.println();
   }
}

// Código original: Copyright 1992-2004 Deitel & Associates, Inc. e Pearson Education Inc.
