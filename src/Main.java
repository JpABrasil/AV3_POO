import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path base_input = Paths.get("inputs/3tipos/input");
        Path base_output = Paths.get("outputs/3tipos/GreedyV01/output");
        for(int i = 0; i < 100; i++){
            Greedy.v01.solucionar(String.valueOf(base_input) + "_"+ i + ".txt" ,String.valueOf(base_output)+ "_"+ i + ".txt",false);
        }





    }
}
