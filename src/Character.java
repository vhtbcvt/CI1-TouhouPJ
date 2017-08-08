public class Character {

    private String name;
    private int x;
    private int y;



    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public Character(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Character(String name) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
