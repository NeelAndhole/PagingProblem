import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FMTester {


  @Test
  public void ExampleTester() {
    TextUITester tester = new TextUITester(
        "3\n2\n7\n1 2 3 2 3 1 2\n4\n12\n12 3 33 14 12 20 12 3 14 33 12 20\n3\n20\n1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5\n");
    FurthestMain.runner();
    String output = tester.checkOutput();
    Assertions.assertEquals("4\n6\n12\n", output);

  }


}
