// Buffer circular com monitor Java (Deitel, Fig. 6.6)
// Mesma ideia da Parte III, mas com 3 posições em vez de 1.
// Assim o produtor pode ficar até 3 valores à frente do consumidor
// e as threads esperam bem menos uma pela outra.

public class CircularBuffer implements Buffer
{
   private int buffers[] = { -1, -1, -1 };

   private int occupiedBuffers = 0; // quantas posições têm valor não lido

   private int readLocation = 0;  // R: próxima posição a ler
   private int writeLocation = 0; // W: próxima posição a gravar

   public synchronized void set( int value )
   {
      String name = Thread.currentThread().getName();

      // As 3 posições ocupadas: produtor espera
      while ( occupiedBuffers == buffers.length )
      {
         try
         {
            System.err.println( "\nAll buffers full. " + name + " waits." );
            wait();
         }
         catch ( InterruptedException exception )
         {
            exception.printStackTrace();
         }
      }

      buffers[ writeLocation ] = value;

      System.err.println( "\n" + name + " writes " +
         buffers[ writeLocation ] + " " );

      ++occupiedBuffers;

      // Parte "circular": depois da última posição volta para a 0
      writeLocation = ( writeLocation + 1 ) % buffers.length;

      System.err.println( createStateOutput() );

      notify();
   }

   public synchronized int get()
   {
      String name = Thread.currentThread().getName();

      // Nenhuma posição com valor: consumidor espera
      while ( occupiedBuffers == 0 )
      {
         try
         {
            System.err.println( "\nAll buffers empty. " + name + " waits." );
            wait();
         }
         catch ( InterruptedException exception )
         {
            exception.printStackTrace();
         }
      }

      int readValue = buffers[ readLocation ];

      System.err.println( "\n" + name + " reads " + readValue + " " );

      --occupiedBuffers;

      readLocation = ( readLocation + 1 ) % buffers.length;

      System.err.println( createStateOutput() );

      notify();

      return readValue;
   }

   // Só para visualização: mostra as 3 posições e onde estão R (leitura) e W (escrita)
   public String createStateOutput()
   {
      String output = "(buffers occupied: " +
         occupiedBuffers + ")\nbuffers: ";

      for ( int i = 0; i < buffers.length; ++i )
      {
         output += " " + buffers[ i ] + "  ";
      }

      output += "\n         ";

      for ( int i = 0; i < buffers.length; ++i )
      {
         output += "---- ";
      }

      output += "\n         ";

      for ( int i = 0; i < buffers.length; ++i )
      {
         if ( i == writeLocation && writeLocation == readLocation )
         {
            output += " WR  "; // R e W na mesma posição: buffer vazio ou cheio
         }
         else if ( i == writeLocation )
         {
            output += " W   ";
         }
         else if ( i == readLocation )
         {
            output += "  R  ";
         }
         else
         {
            output += "     ";
         }
      }

      output += "\n";

      return output;
   }
}

// Código original: Copyright 1992-2004 Deitel & Associates, Inc. e Pearson Education Inc.
