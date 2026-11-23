// Thread produtora (Deitel, Fig. 5.2)
// Grava os valores 1, 2, 3 e 4 no buffer, com uma pausa aleatória antes de cada um.

public class Producer extends Thread
{
   private Buffer sharedLocation; // buffer compartilhado com o consumidor

   public Producer( Buffer shared )
   {
       super( "Producer" );
       sharedLocation = shared;
   }

   public void run()
   {
      for ( int count = 1; count <= 4; count++ )
      {
         try
         {
            Thread.sleep( ( int ) ( Math.random() * 3001 ) ); // 0 a 3 s
            sharedLocation.set( count );
         }
         catch ( InterruptedException exception )
         {
            exception.printStackTrace();
         }
      }

      System.err.println( getName() + " done producing." +
         "\nTerminating " + getName() + "." );
   }
}

// Código original: Copyright 1992-2004 Deitel & Associates, Inc. e Pearson Education Inc.
