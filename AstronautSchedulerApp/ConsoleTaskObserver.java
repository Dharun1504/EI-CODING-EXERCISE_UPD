

public class ConsoleTaskObserver implements TaskObserver {
    @Override
    public void notify(String message) {
        System.out.println(message);
    }
}
