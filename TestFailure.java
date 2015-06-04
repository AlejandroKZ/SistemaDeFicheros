import static org.junit.Assert.assertEquals;
import junit.framework.TestCase;

public class TestFailure extends TestCase {
		
public void testCrearArchivoEnC() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	
	if(sf.anadirArchivo("NuevoArchivo", "C:", 1)){
	System.out.println("---------Test anadir Archivo en C:---------");	
	sf.mostrarContenidoCluster();
	sf.mostrarContenidoInodos();
	sf.mostrarMapas();
	}
	else fail();	
	
}

public void testCrearDirectorioEnC() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	
	if(sf.anadirDirectorio("PruebaDirectorio", "C:")){
		System.out.println("---------Test anadir Directorio en C:---------");	
		sf.mostrarContenidoCluster();
		sf.mostrarContenidoInodos();
		sf.mostrarMapas();
	}
	else fail();
	
}

public void testMultiplesDirectorios() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	String direccion="C:";
	char carpeta = 97;
	String directorio = "";
	int directoriosCreados = 0;
	directorio =directorio+carpeta;
	
	while(directoriosCreados < sf.listaInodos.size()-1){
		
		if(sf.anadirDirectorio(directorio, direccion)){
			carpeta++;
			direccion = direccion+"/"+directorio;
			directorio = ""+carpeta;			
		}else fail();
		
		directoriosCreados++;
	}
	
	System.out.println("---------Test Multiples Directorios:---------");	
	sf.mostrarContenidoCluster();
	sf.mostrarContenidoInodos();
	sf.mostrarMapas();
	
}

public void testSobrepasarLimiteDeDirectorios() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	String direccion="C:";
	char carpeta = 97;
	String directorio = "";
	int directoriosCreados = 0;
	directorio =directorio+carpeta;
	
	while(directoriosCreados < sf.listaInodos.size()){
		
		if(sf.anadirDirectorio(directorio, direccion)){
			carpeta++;
			direccion = direccion+"/"+directorio;
			directorio = ""+carpeta;			
		}else fail();
		
		directoriosCreados++;
	}
	
	System.out.println("---------Test Sobrepasar numero de directorios:---------");	
	sf.mostrarContenidoCluster();
	sf.mostrarContenidoInodos();
	sf.mostrarMapas();
	
}

public void testMultiplesArchivos() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	char archivo = 97;
	String directorio = "";
	int directoriosCreados = 0;
	directorio =directorio+archivo;
	
	while(directoriosCreados < sf.listaInodos.size()-1){

		System.out.println(archivo);
		if(sf.anadirArchivo(directorio, "C:",1)){
			archivo++;
			directorio = ""+archivo;			
		}else fail();
		
		directoriosCreados++;
	}
	
	System.out.println("---------Test Multiples Directorios:---------");	
	sf.mostrarContenidoCluster();
	sf.mostrarContenidoInodos();
	sf.mostrarMapas();
	
}

public void testSobrepasarElNumeroDeArchivos() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	char archivo = 97;
	String directorio = "";
	int directoriosCreados = 0;
	directorio =directorio+archivo;
	
	while(directoriosCreados < sf.listaInodos.size()){

		System.out.println(archivo);
		if(sf.anadirArchivo(directorio, "C:",1)){
			archivo++;
			directorio = ""+archivo;			
		}else fail();
		
		directoriosCreados++;
	}
	
	System.out.println("---------Test Sobrepasar limite de archivos:---------");	
	sf.mostrarContenidoCluster();
	sf.mostrarContenidoInodos();
	sf.mostrarMapas();
	
}

public void testSobrepasarTamDeArchivo() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	
	assertEquals(false,sf.anadirArchivo("NuevoArchivo", "C:", 500));
	
	System.out.println("---------Test Sobrepasar tamaño de archivo---------");	
	sf.mostrarContenidoCluster();
	sf.mostrarContenidoInodos();
	sf.mostrarMapas();
	
}

public void testArchivosEnDirectorio() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	sf.anadirDirectorio("Carpeta", "C:");
	sf.anadirArchivo("a", "C:/Carpeta", 1);
	
	if(sf.anadirArchivo("b", "C:/Carpeta", 1)){
		System.out.println("---------Test Archivos en directorios:---------");	
		sf.mostrarContenidoCluster();
		sf.mostrarContenidoInodos();
		sf.mostrarMapas();
	}else fail();
	
}

public void testBorrarArchivoEnC() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	sf.anadirArchivo("a", "C:", 1);
	
	if(sf.borrarArchivo("a", "C:", true)){
		System.out.println("---------Test Borrar Archivo en C:---------");	
		sf.mostrarContenidoCluster();
		sf.mostrarContenidoInodos();
		sf.mostrarMapas();
	}else fail();
	
}

public void testBorrarArchivoInexistenteEnC() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	
	if(sf.borrarArchivo("a", "C:", true)){
		System.out.println("---------Test Borrar Archivo Inexistente en C:---------");	
		sf.mostrarContenidoCluster();
		sf.mostrarContenidoInodos();
		sf.mostrarMapas();
	}else fail();
	
}

public void testBorrarDiretorioEnC() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	sf.anadirDirectorio("a", "C:");
	
	if(sf.borrarDirectorio("a", "C:", true)){
		System.out.println("---------Test Borrar Archivo en C:---------");	
		sf.mostrarContenidoCluster();
		sf.mostrarContenidoInodos();
		sf.mostrarMapas();
	}else fail();
	
}

public void testBorrarDiretorioInexistenteEnC() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	
	if(sf.borrarDirectorio("a", "C:", true)){
		System.out.println("---------Test Borrar Directorio Inexistente en C:---------");	
		sf.mostrarContenidoCluster();
		sf.mostrarContenidoInodos();
		sf.mostrarMapas();
	}else fail();
	
}

public void testBorrarDiretorioEnDirectorio() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	sf.anadirDirectorio("a", "C:");
	sf.anadirDirectorio("b", "C:/a");
	
	if(sf.borrarDirectorio("b", "C:/a", true)){
		System.out.println("---------Test Borrar Directorio dentro de un directorio:---------");	
		sf.mostrarContenidoCluster();
		sf.mostrarContenidoInodos();
		sf.mostrarMapas();
	}else fail();
	
}

public void testBorrarDiretorioConDirectorioContenido() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	sf.anadirDirectorio("a", "C:");
	sf.anadirDirectorio("b", "C:/a");
	
	if(sf.borrarDirectorio("a", "C:", true)){
		System.out.println("---------Test Borrar Directorio con un directorio:---------");	
		sf.mostrarContenidoCluster();
		sf.mostrarContenidoInodos();
		sf.mostrarMapas();
	}else fail();
	
}

public void testBorrarDiretorioConContenido() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	sf.anadirDirectorio("a", "C:");
	sf.anadirDirectorio("b", "C:/a");
	sf.anadirArchivo("c", "C:/a", 1);
	
	if(sf.borrarDirectorio("a", "C:", true)){
		System.out.println("---------Test Borrar Directorio con un directorio:---------");	
		sf.mostrarContenidoCluster();
		sf.mostrarContenidoInodos();
		sf.mostrarMapas();
	}else fail();
	
}

public void testBorrado() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	sf.anadirDirectorio("a", "C:");
	sf.anadirDirectorio("e", "C:");
	sf.anadirDirectorio("b", "C:/a");
	sf.anadirDirectorio("a", "C:/e");
	sf.anadirDirectorio("a", "C:/a/b");
	
	if(sf.borrarDirectorio("a", "C:", true)){
		System.out.println("---------Test Borrado---------");	
		sf.mostrarContenidoCluster();
		sf.mostrarContenidoInodos();
		sf.mostrarMapas();
	}else fail();
	
}

public void testBorrarArchivoDeDirectorioInexistente() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	sf.anadirArchivo("a", "C:", 1);
	
	if(sf.borrarArchivo("a", "C:/b", true)){
		System.out.println("---------Test Borrar Archivo Inexistente en C:---------");	
		sf.mostrarContenidoCluster();
		sf.mostrarContenidoInodos();
		sf.mostrarMapas();
	}else fail();
	
}

public void testBorrarArchivoYCrearArchivo() throws Exception {
	SistemaDeFicheros sf = new SistemaDeFicheros();
	sf.anadirArchivo("a", "C:", 5);
	sf.borrarArchivo("a", "C:", true);
	
	if(sf.anadirArchivo("c", "C:", 1)){
		System.out.println("---------Test Borrar un archivo y crear uno nuevo---------");	
		sf.mostrarContenidoCluster();
		sf.mostrarContenidoInodos();
		sf.mostrarMapas();
	}else fail();
	
}

}

