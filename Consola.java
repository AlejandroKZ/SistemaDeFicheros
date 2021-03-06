import java.util.Scanner;

public class Consola {  
  
  Scanner cin = new Scanner(System.in);
  
  private String mostrar="Mostrar";
  private String cDir="cd";
  private String cAr="ca";
  private String bAr="ba";
  private String bDir="bd";
  private SistemaDeFicheros ns;
  
  public Consola(SistemaDeFicheros ns) {
    this.ns=ns;
  }
  
  void mostrarOpciones() {
    
    boolean comprobar=true;
    
    do {
      System.out.println(mostrar+"--->Mostrar sistema de ficheros");
      System.out.println(cDir+"---->Crear un nuevo directorio");
      System.out.println(cAr+"---->Crear un nuevo archivo");
      System.out.println(bAr+"---->Borrar un archivo");
      System.out.println(bDir+"---->Borrar un archivo");
      System.out.println("Escribre tu comando:  ");
      
      String comando = cin.nextLine();
      String direccion;
      String name;

      if(comando.equals(mostrar)) {
        ns.mostrarMapas();
        ns.mostrarContenidoInodos();
        ns.mostrarContenidoCluster();
        comprobar = true;
      }
    
      if (comando.equals(cDir)) {
        System.out.println("Escribe tu direccion:  ");
        direccion = cin.nextLine();
      
        System.out.println("Escribe el nombre de la carpeta:  ");
        name = cin.nextLine();
      
        ns.anadirDirectorio(name, direccion);
        comprobar = true;
      }
    
      if (comando.equals(cAr)) {
        Float size = (float) 0;
        String tam;
      
        System.out.println("Escribe tu direccion:  ");
        direccion = cin.nextLine();
      
        System.out.println("Escribe el nombre del archivo:  ");
        name = cin.nextLine();
        
        System.out.println("Escribe el tamano del archivo:  ");
        tam = cin.nextLine();
  
        size = size.valueOf(tam);
        ns.anadirArchivo(name, direccion,size);
        comprobar = true;
      }
      
      if (comando.equals(bAr)) {
        System.out.println("Escribe tu direccion:  ");
        direccion = cin.nextLine();
      
        System.out.println("Escribe el nombre del archivo:  ");
        name = cin.nextLine();
        
        ns.borrarArchivo(name, direccion,true);
        comprobar = true;
      }
    
      if (comando.equals(bDir)) {
        System.out.println("Escribe tu direccion:  ");
        direccion = cin.nextLine();
        
        System.out.println("Escribe el nombre del directorio:  ");
        name = cin.nextLine();
      
        ns.borrarDirectorio(name, direccion,true);
        comprobar = true;
      }
    } while(comprobar);
  }
}
