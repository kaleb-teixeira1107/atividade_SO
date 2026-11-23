// Parte II - Execução concorrente sem sincronização (Deitel, Fig. 5.5)
// Produtor e consumidor usam o mesmo UnsynchronizedBuffer.
// O que observar: o total lido muda a cada execução e quase nunca dá 10.
// Nos meus testes deu 11 (perdeu o 3 e leu o 4 duas vezes) e 2 (leu -1 duas vezes).

public class SharedBufferTest
{
    public static void main( String [] args )
    {
        Buffer sharedLocation = new UnsynchronizedBuffer();

        Producer producer = new Producer( sharedLocation );
        Consumer consumer = new Consumer( sharedLocation );

        producer.start();
        consumer.start();
    }
}

// Código original: Copyright 1992-2004 Deitel & Associates, Inc. e Pearson Education Inc.
