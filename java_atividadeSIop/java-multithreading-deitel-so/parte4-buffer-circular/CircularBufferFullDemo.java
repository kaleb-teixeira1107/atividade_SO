// Experimento extra (não é do livro): força o caso de buffer cheio.
// Usa o mesmo CircularBuffer, mas com produtor rápido (100 ms) e consumidor lento (1 s).
// O que observar: o produtor enche as 3 posições, aparece "All buffers full. Producer waits."
// e ele só volta a gravar quando o consumidor libera uma posição.
// Total esperado: 1+2+3+4+5+6 = 21.

public class CircularBufferFullDemo
{
   public static void main( String[] args )
   {
      CircularBuffer buffer = new CircularBuffer();
      System.err.println( buffer.createStateOutput() );

      Thread producer = new Thread( () -> {
         for ( int i = 1; i <= 6; i++ )
         {
            try { Thread.sleep( 100 ); }
            catch ( InterruptedException e ) { e.printStackTrace(); }
            buffer.set( i );
         }
         System.err.println( "Producer done producing." );
      }, "Producer" );

      Thread consumer = new Thread( () -> {
         int sum = 0;
         for ( int i = 1; i <= 6; i++ )
         {
            try { Thread.sleep( 1000 ); }
            catch ( InterruptedException e ) { e.printStackTrace(); }
            sum += buffer.get();
         }
         System.err.println( "Consumer read values totaling: " + sum + "." );
      }, "Consumer" );

      producer.start();
      consumer.start();
   }
}
