// Parte III - Produtor/consumidor com monitor Java (Deitel, Fig. 6.5)
// Mesmo Producer e Consumer da Parte II, só troca o buffer por SynchronizedBuffer.
// O que observar: a ordem muda a cada execução, mas o total é sempre 10,
// e aparecem mensagens de espera quando o buffer está vazio ou cheio.

public class SharedBufferTest2
{
   public static void main( String [] args )
   {
      SynchronizedBuffer sharedLocation = new SynchronizedBuffer();

      // Cabeçalho da tabela de saída
      StringBuffer columnHeads = new StringBuffer( "Operation" );
      columnHeads.setLength( 40 );
      columnHeads.append( "Buffer\t\tOccupied Count" );
      System.err.println( columnHeads );
      System.err.println();
      sharedLocation.displayState( "Initial State" );

      Producer producer = new Producer( sharedLocation );
      Consumer consumer = new Consumer( sharedLocation );

      producer.start();
      consumer.start();
   }
}

// Código original: Copyright 1992-2004 Deitel & Associates, Inc. e Pearson Education Inc.
