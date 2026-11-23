// Parte IV - Produtor/consumidor com buffer circular (Deitel, Fig. 6.7)
// O que observar: o total continua sempre 10, e o desenho mostra
// os ponteiros R e W andando e voltando para o início do vetor.
// Obs.: com os tempos originais, o caso "All buffers full" quase nunca acontece.
// Para ver esse caso, rode o CircularBufferFullDemo.

public class CircularBufferTest
{
   public static void main ( String args[] )
   {
      // Aqui a variável é do tipo CircularBuffer (e não Buffer)
      // para poder chamar createStateOutput()
      CircularBuffer sharedLocation = new CircularBuffer();

      System.err.println( sharedLocation.createStateOutput() ); // estado inicial

      Producer producer = new Producer( sharedLocation );
      Consumer consumer = new Consumer( sharedLocation );

      producer.start();
      consumer.start();
   }
}

// Código original: Copyright 1992-2004 Deitel & Associates, Inc. e Pearson Education Inc.
