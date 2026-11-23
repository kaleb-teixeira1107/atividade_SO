// Thread consumidora (Deitel, Fig. 5.3)
// Lê o buffer 4 vezes e soma. Se tudo der certo, o total é 1+2+3+4 = 10.

public class Consumer extends Thread
{
   private Buffer sharedLocation; // buffer compartilhado com o produtor

   public Consumer( Buffer shared )
   {
      super( "Consumer" );
      sharedLocation = shared;
   }

   public void run()
   {
      int sum = 0;

      for ( int count = 1; count <= 4; ++count )
      {
         try
         {
            Thread.sleep( ( int ) ( Math.random() * 3001 ) ); // 0 a 3 s
            sum += sharedLocation.get();
         }
         catch ( InterruptedException exception )
         {
            exception.printStackTrace();
         }
      }

      // Esse total é o que mostra se a sincronização funcionou
      System.err.println( getName() + " read values totaling: "
         + sum + ".\nTerminating " + getName() + "." );
   }
}

// Código original: Copyright 1992-2004 Deitel & Associates, Inc. e Pearson Education Inc.
