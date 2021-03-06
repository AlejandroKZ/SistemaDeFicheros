import java.util.ArrayList;
import java.util.LinkedList;

public class SistemaDeFicheros {

  int tamCluster = 10;
  int tamInodos = 10;
  int sizeCluster = 1;
  
  ArrayList<Boolean> mapaCluster;//False es libre True es ocupado
  ArrayList<Boolean> mapaInodos;//False es libre True es ocupado
  
  ArrayList<Inodo> listaInodos;  
  ArrayList<Cluster> listaCluster;

  public SistemaDeFicheros() {

    mapaInodos = new ArrayList<Boolean>();
    mapaCluster = new ArrayList<Boolean>();
    
    listaInodos = new ArrayList<Inodo>();
    listaCluster= new ArrayList<Cluster>();
    
    for (int x = 0; x<tamCluster; x++) {
      mapaCluster.add(false);
      listaCluster.add(new Cluster());

      if(x < tamInodos) {
        mapaInodos.add(false);
        listaInodos.add(new Inodo());
      }  
    }
    
    crearDir("C:");
  }
  
  public void mostrarMapas() {
    String estado;

    for (int x = 0; x < tamCluster; x++) {
      if(mapaCluster.get(x)) {
        estado="Ocupado";
      }
      else {
        estado="Disponible";
      }
      
      System.out.print("Cluster "+x+":  "+estado+"                   ");
      
      if(x < tamInodos) {
        if(mapaInodos.get(x)) {
          estado="Ocupado";
        }
        else {
          estado="Disponible";
        }
        System.out.print("Inodo: "+x+":  "+estado);
      }  
      System.out.println();
    }
  }
  
  public void mostrarContenidoInodos() {

    int x = 0;
    int i = 0;
    
    while(x < tamInodos) {
      System.out.println("El inodo "+x+" apunta a los siguientes clusters:");
        
      while (i<listaInodos.get(x).punteros.size()) {
        System.out.println(listaInodos.get(x).punteros.get(i));
        i++;
      }
        
      System.out.println("---");
      i=0;
      x++;
    }
  }
  
  
  public void mostrarContenidoCluster() {
    int x = 0;
      
    while(x<tamCluster) {
      System.out.println("El cluster "+x+" contiene:");
          
      if (listaCluster.get(x).getClass()!=Cluster.class) {
        System.out.println(listaCluster.get(x));
      }
      else {
        System.out.println("'Vacio'");
      }
          
      System.out.println("---");
      x++;
    }
  }
  
  private void crearDir(String name) {
    int iNodoLibre = buscarInodos();
    int clusterLibre = buscarCluster();
     
    if(iNodoLibre != -1 && clusterLibre != -1) {
       mapaInodos.set(iNodoLibre,true);
       mapaCluster.set(clusterLibre, true);    
      
       listaInodos.get(iNodoLibre).punteros.set(0, clusterLibre);
       listaCluster.set(clusterLibre, new Directorio());
       listaInodos.get(iNodoLibre).name=name;
    }
  }
   
   
   private int buscarInodos() {
     int x = 0;
     
     while (mapaInodos.get(x) && x < tamInodos) {
       x++;
     }
     
     if (x == tamInodos) {
       System.out.println("Error memoria llena: Inodos completos");
       return -1;
     }
     else {
       return x;
     }
   }
   
  private int buscarCluster() {
    int x = 0;
    while (mapaCluster.get(x) && x < tamCluster) {
      x++;
    }
         
    if (x == tamCluster) {
      System.out.println("Error memoria llena: Clusters completos");
      return -1;
    }
    else {
      return x;
    }
  }

  public boolean anadirDirectorio(String name, String direccion) {
    boolean result = false;
	  int clus;
    int conta = 0;
  
    String [] miDir = direccion.split("/");
    
    if(miDir[0].equals("C:")) {
      if(miDir.length == 1) { //Esta creando un directorio dentro del raiz
        conta = contabilizar(name);

        if(conta == ((Directorio)(listaCluster.get(0))).listaContenido.size()) {  //TODO OK        
          ((Directorio)(listaCluster.get(0))).listaContenido.add(new EntradaDir(name,buscarCluster(),false));
          result = true;
          crearDir(name);
        }
      }
      else {
        clus = apoyoCrearDir(((Directorio)(listaCluster.get(0))).listaContenido, miDir,1,1,1);
        if(clus != -1) {    
          conta = contabilizar (name);
        
          if(conta == ((Directorio)(listaCluster.get(0))).listaContenido.size()) {
            ((Directorio)(listaCluster.get(clus))).listaContenido.add(new EntradaDir(name,buscarCluster(),false));
            result = true;
            crearDir(name);
          }
        }
      }
    }
    return result;
  }
  
  private int apoyoCrearDir(LinkedList<EntradaDir> contenido, String [] miDir, 
                            int num,int comp,int clusterDestino) {
    //Contenido son los directorios y archivos dentro de la carpeta en al que estamos
    //miDir es la direccion en la que quiero crear un directorio
    //num es la iteracion por la que vamos
    //comp valdra -1 si ha habido un error si no nos pasa el numero de cluster de nuestra ultimo drectorio
    int numClusterDestino = 0;
    int conta = 0;
  
    if(comp == -1) {
      return comp;
    }
  
    if(num < miDir.length) {
      while(conta < contenido.size() && !miDir[num].equals(contenido.get(conta).nombre)) {    
        conta++;
      }
    
      if(conta == contenido.size()) {
        comp = -1;
      }
      else {
        comp = conta;
        numClusterDestino = contenido.get(conta).cluster;
        clusterDestino = 
        apoyoCrearDir(((Directorio)listaCluster.get(numClusterDestino))
          .listaContenido, miDir, num+1,comp,numClusterDestino);
      }
    }
    return clusterDestino;
  }

  public boolean anadirArchivo(String name, String direccion, float size) {
    int clus;
    int conta = 0;
  
    String [] miDir = direccion.split("/");

    if(miDir[0].equals("C:")) {
      if(miDir.length == 1) { //Esta creando un archivo dentro del raiz
        conta = contabilizar(name);
      
        if(conta == ((Directorio)(listaCluster.get(0))).listaContenido.size()) {  //TODO OK        
          ((Directorio)(listaCluster.get(0))).listaContenido.add(new EntradaDir(name,buscarCluster(),true));
          crearAr(size,name);
          return true;
        }
      }
      else {
        clus = apoyoCrearDir(((Directorio)(listaCluster.get(0))).listaContenido, miDir,1,1,1);
      
        if(clus != -1) {
          conta = contabilizar(name);
        
          if(conta == ((Directorio)(listaCluster.get(0))).listaContenido.size()) {//TODO codigo a cambiar
            ((Directorio)(listaCluster.get(clus))).listaContenido.add(new EntradaDir(name,buscarCluster(),true));
            crearAr(size,name);
            return true;
          }
        }
      }
    }
    return false;
  }
  
  private void crearAr(float size,String name){
    //Solo ocuparemos un inodo porque es un inodo por archivo o directorio pero
    //tenemos que ocupar mas de un cluster si el tam del archivo es mayor que el de cluster
    //asi que habra que modificarlo el codigo asociado a ocupar cluster
   
    int iNodoLibre = buscarInodos();
    int clusterLibre = buscarCluster();
    int contadorDeBusquedaDeClusters = 0;
    int clusterAOcupar;
   
    if((size%sizeCluster) > 0) {
      clusterAOcupar = (int) (size/sizeCluster)+1;
    }
    else {
      clusterAOcupar = (int) (size/sizeCluster);
    }
   
    if(iNodoLibre != -1) {
      mapaInodos.set(iNodoLibre,true);
      listaInodos.get(iNodoLibre).punteros.set(0, clusterLibre);
      listaInodos.get(iNodoLibre).name=name;
    }
    
    while(clusterLibre != -1 && contadorDeBusquedaDeClusters < clusterAOcupar) {
      mapaCluster.set(clusterLibre, true);
      listaCluster.set(clusterLibre, new Archivo(
         name+" ->"+ (contadorDeBusquedaDeClusters+1)+"/"+clusterAOcupar));
     
      if(contadorDeBusquedaDeClusters == 0) {
        listaInodos.get(iNodoLibre).punteros.set(0, clusterLibre);
      }
      else { 
        listaInodos.get(iNodoLibre).punteros.add(clusterLibre);
      } 
      clusterLibre = buscarCluster();
      contadorDeBusquedaDeClusters++;
    }
  }

  public void liberarCluster(ArrayList<Integer>clustersABorrar) {
    int numeroDeClustersBorrados=0;

    while (numeroDeClustersBorrados<clustersABorrar.size()) {
      mapaCluster.set(clustersABorrar.get(numeroDeClustersBorrados),false);
      numeroDeClustersBorrados++;
    }
  }

  public boolean borrarArchivo(String name, String direccion, boolean eliminacionEspecifica) {
    String [] miDir = direccion.split("/");
    int clusterDelDirectorio;
  
    if(miDir[0].equals("C:")) {
      if(miDir.length > 1) {
        clusterDelDirectorio = 
        apoyoCrearDir(((Directorio)(listaCluster.get(0))).listaContenido, miDir,1,1,1);  
      } else {
        clusterDelDirectorio = 0;
      }

      if(clusterDelDirectorio != -1) {
        Directorio directorioDeArchivo = ((Directorio)(listaCluster.get(clusterDelDirectorio)));
        int indexListaContenido = buscarIndex(directorioDeArchivo, name);
    
        if(indexListaContenido != -1) {
          int primerClusterDeArchivo = directorioDeArchivo.listaContenido
          .get(indexListaContenido).cluster;
          int busquedaDelInodo = buscarInodo (primerClusterDeArchivo);

          ArrayList<Integer> clustersALiberar = listaInodos.get(busquedaDelInodo).punteros;
          liberarCluster(clustersALiberar);
          mapaInodos.set(busquedaDelInodo,false);
      
          if(eliminacionEspecifica) {
            directorioDeArchivo.listaContenido.remove(indexListaContenido);
            return true;
          }
        }    
      }
    }
    return false;
  }

  public boolean borrarDirectorio(String name, String direccion, boolean soyElPrimero) {
  
    String [] miDir = direccion.split("/");
    String direccionNueva;
    int indexDelDirectoriOriginal = 0;
    int clusterDelDirectorio;
    int clusterOriginal = 0;
    Directorio directorioDeDirectorio;
    Directorio directorioOriginal = null;

  
    if(miDir[0].equals("C:")) {
      if(miDir.length > 1) {
        clusterDelDirectorio = apoyoCrearDir(((Directorio)(listaCluster.get(0))).listaContenido, miDir,1,1,1);  
      } else {
        clusterDelDirectorio=0;
      }

      if (clusterDelDirectorio != -1) {
        directorioDeDirectorio = ((Directorio)(listaCluster.get(clusterDelDirectorio)));
        
        if (soyElPrimero) {
          indexDelDirectoriOriginal = buscarIndex(directorioDeDirectorio ,name);  
          directorioOriginal = directorioDeDirectorio;
        }
      
        int indexListaContenido = buscarIndex(directorioDeDirectorio , name);
      
        int clusterDelDirectorioALiberar = directorioDeDirectorio.listaContenido.get(indexListaContenido).cluster;
      
        LinkedList<EntradaDir> archivosDeMiDirectorio = 
          ((Directorio)listaCluster.get(clusterDelDirectorioALiberar)).listaContenido;
      
        if (soyElPrimero) {
          clusterOriginal = clusterDelDirectorioALiberar;
        }
      
        int elementosBorrados = 0;
      
        while(elementosBorrados < archivosDeMiDirectorio.size()) {
          if(archivosDeMiDirectorio.get(elementosBorrados).tipo == false) {//Caso directorio
            direccionNueva = direccion+"/"+name;
            borrarDirectorio(archivosDeMiDirectorio
                .get(elementosBorrados).nombre, direccionNueva,false);
          } else {//Caso archivo
            direccionNueva = direccion+"/"+name;
             borrarArchivo(archivosDeMiDirectorio.get(elementosBorrados)
                  .nombre, direccionNueva,false);
            indexListaContenido = -1;
          }
          elementosBorrados++;
        }
      
        if(indexListaContenido != -1) {
          int busquedaDelInodo = buscarInodo(clusterOriginal);
        
          mapaInodos.set(busquedaDelInodo,false);
          mapaCluster.set(listaInodos.get(busquedaDelInodo).punteros.get(0),false);
        }
      
        if (soyElPrimero) {
          directorioOriginal.listaContenido.remove(indexDelDirectoriOriginal);
      
          int busquedaDelInodo = buscarInodo(clusterOriginal);
          mapaInodos.set(busquedaDelInodo,false);
          mapaCluster.set(listaInodos.get(busquedaDelInodo).punteros.get(0),false);
          return true;
        }
      }
    }
    if(soyElPrimero)return false;
    else return true;
  }
  
  public int buscarInodo (int clusterBusqueda) {
    int  busquedaDelInodo = 0;
  
    while (busquedaDelInodo < listaInodos.size() && 
      !listaInodos.get(busquedaDelInodo).punteros.contains(clusterBusqueda)) {
      busquedaDelInodo++;
    }
    return busquedaDelInodo;
  }

  public int buscarIndex(Directorio directorioDeBusqueda , String name) {
    int indexListaContenido = 0;
    while(indexListaContenido < directorioDeBusqueda.listaContenido.size() && 
      !directorioDeBusqueda.listaContenido.get(indexListaContenido).nombre.equals(name)) {
      indexListaContenido++;  
    }
    return indexListaContenido;
  }

  public int contabilizar(String name){
    int conta = 0;
  
    while (conta < ((Directorio)(listaCluster.get(0))).listaContenido.size() && 
      !((Directorio)(listaCluster.get(0)))
      .listaContenido.get(conta).equals(name)) {
      conta++;
    }
  
    return conta;
  }

  public ArrayList<Integer> comprobarInodos(String name, String direccion){
	 
	  String[] miDireccion = direccion.split("/");
	  int longitudDireccion = miDireccion.length;
	  int entrandoEnDirectorio = 1;
	  int clusterDirectorioContenedor = 0;
	  EntradaDir directorioContenedor;
	  ArrayList<Integer> inodosADevolver = new ArrayList<Integer>();
	  int index;
	  
	  while(entrandoEnDirectorio < longitudDireccion){//Buscamos el cluster del directorio que contiene al archivo name		  
		 index = buscarIndex((Directorio)listaCluster.get(clusterDirectorioContenedor), miDireccion[entrandoEnDirectorio]);
		 clusterDirectorioContenedor = ((Directorio)listaCluster.get(clusterDirectorioContenedor))
				 						.listaContenido.get(index).cluster;
		 entrandoEnDirectorio++;
	  }
	  
	  //Calculamos el cluster de name
	  index = buscarIndex((Directorio)listaCluster.get(clusterDirectorioContenedor), name);
	  int clusterArchivo = ((Directorio)listaCluster.get(clusterDirectorioContenedor))
				 						.listaContenido.get(index).cluster;

	index = 0;
	if(listaCluster.get(clusterArchivo).getClass() == Directorio.class){ 
	inodosADevolver.add(buscarInodo(clusterArchivo));//Inodo del directorio que vamos a boarr
	 while(index < ((Directorio)listaCluster.get(clusterArchivo)).listaContenido.size()){//su contenido
		  inodosADevolver.add(((Directorio)listaCluster.get(clusterArchivo))
				  				.listaContenido.get(index).cluster);
		  index++;
	  }
	}else{
		inodosADevolver.add(buscarInodo(clusterArchivo));//Inodo del archivo a borrar
	}
	  return inodosADevolver;
  }
  
  
  
}
