/*Matheus Moura Gorchinsky
DRE: 119043032*/

//Aplicacao de exemplo--------------------------------------------------------


//--------------------------------------------------------
// Classe principal
class Main {
  static final int L = 4;
  static final int E = 3;
  static final int LE = 2;
  static int variavel = 0;

  public static void main (String[] args) {
    int i;
    MonitorLE monitor = new MonitorLE();            // Monitor (objeto compartilhado entre leitores e escritores)
    Leitor[] l = new Leitor[L];       // Threads leitores
    Escritor[] e = new Escritor[E];   // Threads escritores
    LeitorEscritor[] le = new LeitorEscritor[LE]; //Threads leitores escritores
    //inicia o log de saida
    System.out.println ("import verificaLE");
    System.out.println ("le = verificaLE.LE()");
    
    for (i=0; i<L; i++) {
       l[i] = new Leitor(i+1, (i+1)*500, monitor);
       l[i].start(); 
    }
    for (i=0; i<E; i++) {
       e[i] = new Escritor(i+1, (i+1)*500, monitor);
       e[i].start(); 
    }
    for (i=0; i<LE; i++) {
       le[i] = new LeitorEscritor(i+1, (i+1)*500, monitor);
       le[i].start(); 
    }
  }
}
