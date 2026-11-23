// Interface comum dos buffers (Deitel, Fig. 5.1)
// Produtor usa set() para gravar, consumidor usa get() para ler.
// Cada parte troca só a implementação: sem sincronização, com monitor ou circular.

public interface Buffer
{
   public void set( int value );
   public int get();
}

// Código original: Copyright 1992-2004 Deitel & Associates, Inc. e Pearson Education Inc.
