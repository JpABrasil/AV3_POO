import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        /*Path base_input = Paths.get("inputs/3tipos/input");
        Path base_output = Paths.get("outputs/greedy_v02_dq/3tipos/output");
        for(int i = 0; i < 100; i++){
            Greedy.v02_dq.solucionar(String.valueOf(base_input) + "_" + i + ".txt" ,String.valueOf(base_output) + "_" + i + ".txt",false);
        }*/
        Path base_input = Paths.get("inputs/3tipos/input_34.txt");
        Path base_output = Paths.get("outputs/greedy_v02/3tipos/output_34.txt");
        Greedy.v02_dq.solucionar(String.valueOf(base_input)  ,String.valueOf(base_output),true);






    }
}
