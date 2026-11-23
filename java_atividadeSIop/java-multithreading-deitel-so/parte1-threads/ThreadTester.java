// Parte I - Threads (Deitel, cap. 4, Fig. 4.9)
// Cria 3 threads que dormem um tempo aleatório e avisam quando acordam.
// O que observar: a main termina antes delas, e a ordem de término
// depende só do tempo de sono de cada uma (não da ordem do start).

public class ThreadTester {

   public static void main( String [] args )
   {
      PrintThread thread1 = new PrintThread( "thread1" );
      PrintThread thread2 = new PrintThread( "thread2" );
      PrintThread thread3 = new PrintThread( "thread3" );

      System.err.println( "Starting threads" );

      // start() não executa na hora: só coloca a thread no estado "pronto".
      // Quem decide quando cada uma roda é o escalonador.
      thread1.start();
      thread2.start();
      thread3.start();

      System.err.println( "Threads started, main ends\n" );
   }
}

class PrintThread extends Thread {
   private int sleepTime;

   public PrintThread( String name )
   {
      super( name );
      sleepTime = ( int ) ( Math.random() * 5001 ); // 0 a 5 segundos
   }

   // run() é o código que a thread executa depois do start()
   public void run()
   {
      try {
         System.err.println( getName() + " going to sleep for " +
            sleepTime + " milliseconds" );

         Thread.sleep( sleepTime ); // estado "adormecido"
      }
      catch ( InterruptedException exception ) {
         exception.printStackTrace();
      }

      System.err.println( getName() + " done sleeping" ); // depois disso a thread morre
   }
}

// Código original: Copyright 1992-2004 Deitel & Associates, Inc. e Pearson Education Inc.
