import org.junit.jupiter.api.Test;

public class ExecutorTest {

    @Test
    public void executeLines(){
        Executor executor = new Executor();

        String program = "let foo : number= 5;\n" +
                         "let bar :number = foo + 4;\n" +
                         "print(\"This is the \" + \'result: \' + bar);\n";

        System.out.println("\n### EXPECTING SUCCESS ###\n");
        executor.execute(program);
        System.out.println("\n");
    }
}
