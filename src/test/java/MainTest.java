import org.example.Main;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;

public class MainTest {

    @Test
    void givenValidArgs_whenMainIsCalled_thenMethodsAreExecuted() {
        String[] args = {"-import", "importFile.txt", "-export", "exportFile.txt"};
        String userInputs = "add\nexit\nadd\nexit\n";
        System.setIn(new ByteArrayInputStream(userInputs.getBytes()));
        Main.main(args);
    }

}