import org.junit.jupiter.api.Test;

public class ExecutorTest {

    @Test
    public void executeLines(){
        Executor executor = new Executor();

        String program = "let foo : number= 5;\n" +
                         "let bar :number = foo + 4;\n" +
                         "print(bar + \"is a\" + \'word\');\n";

        executor.execute(program);
    }
}
