import touhou.GameWindow;

/**
 * Created by huynq on 7/4/17.
 */
public class Program {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.loop();

        Character theLinh = new Character("theLinh", 2, 6);
        Character x = theLinh;
        System.out.println(x);
    }
}
