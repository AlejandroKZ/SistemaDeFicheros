public class EntradaDir {

  String nombre;
  int cluster;
  boolean tipo; //True=Archivo--Directorio
  
  public EntradaDir(String nombre, int cluster, boolean tipo) {
    this.nombre = nombre;
    this.cluster = cluster;
    this.tipo = tipo;
  }
  
  public String toString() {
    String esteTipo;
    
    if (tipo) {
      esteTipo = "Archivo";
    } else {
      esteTipo = "Directorio";
    }
    
    return (nombre+"  "+cluster+"  "+esteTipo);
  }
}
