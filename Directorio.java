import java.util.LinkedList;

public class Directorio extends Cluster {
  LinkedList<EntradaDir> listaContenido;
  
  public Directorio() {
    listaContenido = new LinkedList<EntradaDir>();
  }
  
  public String toString(){
    return ("Dir"+listaContenido);
  }
}
