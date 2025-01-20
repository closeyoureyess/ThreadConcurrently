import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        HelperClass helperClass = new HelperClass();
        Runnable runnable = () -> helperClass.increment();
        try (ExecutorService executorService = Executors.newFixedThreadPool(2);) {
            executorService.execute(runnable);
            executorService.execute(runnable);
        }
    }
}
//Напишите программу, которая создаст два потока, которые по очереди будут выводить числа.
// Первый поток чётные числа, второй потом нечётные.