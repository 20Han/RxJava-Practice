package rxjava;

public class Log {
    public static void i(Object logMessage) {
        System.out.println(Thread.currentThread().getName() + "| " + logMessage.toString());
    }
}
