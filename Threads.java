// Leitor
class Leitor extends Thread {
  int id; //identificador da thread
  int delay; //atraso bobo
  MonitorLE monitor;//objeto monitor para coordenar a lógica de execução das threads

  // Construtor
  Leitor (int id, int delayTime, MonitorLE m) {
    this.id = id;
    this.delay = delayTime;
    this.monitor = m;
  }

  // Método executado pela thread
  public void run () {
    double j=777777777.7, i;
    try {
      for (;;) {
        this.monitor.EntraLeitor(this.id);
        if(Main.variavel%2 == 1){
            System.out.println(Main.variavel + " --- Impar");
        }
        else{
            System.out.println(Main.variavel + " --- Par");
        }
        for (i=0; i<100000000; i++) {j=j/2;} //...loop bobo para simbolizar o tempo de leitura
        this.monitor.SaiLeitor(this.id);
        sleep(this.delay); 
      }
    } catch (InterruptedException e) { return; }
  }
}

//--------------------------------------------------------
// Escritor
class Escritor extends Thread {
  int id; //identificador da thread
  int delay; //atraso bobo...
  MonitorLE monitor; //objeto monitor para coordenar a lógica de execução das threads

  // Construtor
  Escritor (int id, int delayTime, MonitorLE m) {
    this.id = id;
    this.delay = delayTime;
    this.monitor = m;
  }

  // Método executado pela thread
  public void run () {
    double j=777777777.7, i;
    try {
      for (;;) {
        this.monitor.EntraEscritor(this.id);
        Main.variavel = this.id;
        this.monitor.SaiEscritor(this.id); 
        sleep(this.delay); //atraso bobo...
      }
    } catch (InterruptedException e) { return; }
  }
}

class LeitorEscritor extends Thread {
  int id; //identificador da thread
  int delay; //atraso bobo
  MonitorLE monitor;//objeto monitor para coordenar a lógica de execução das threads

  // Construtor
  LeitorEscritor (int id, int delayTime, MonitorLE m) {
    this.id = id;
    this.delay = delayTime;
    this.monitor = m;
  }

  // Método executado pela thread
  public void run () {
    double j=777777777.7, i;
    try {
      for (;;) {
        this.monitor.EntraLeitor(this.id);
        if(Main.variavel%2 == 1){
            System.out.println(Main.variavel + " --- Impar");
        }
        else{
            System.out.println(Main.variavel + " --- Par");
        }
        this.monitor.SaiLeitor(this.id);
        for (i=0; i<100000000; i++) {j=j/2;} //...processamento bobo qualquer
        this.monitor.EntraEscritor(this.id);
        Main.variavel += 1;
        this.monitor.SaiEscritor(this.id); 
        sleep(this.delay); 
      }
    } catch (InterruptedException e) { return; }
  }
}