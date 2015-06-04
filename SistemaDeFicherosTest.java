import static org.junit.Assert.*;
import org.junit.Test;

public class SistemaDeFicherosTest {

  @Test
  public void ScalarMultiplicationCheck() {
	  SistemaDeFicheros sf = new SistemaDeFicheros();
	  assertEquals(true, sf.anadirDirectorio("folder", "name"));
  }
}
