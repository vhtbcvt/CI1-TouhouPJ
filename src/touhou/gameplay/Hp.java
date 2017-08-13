package touhou.gameplay;

public class Hp {
    public int hp;

    public Hp(int hp) {
        this.hp = hp;
    }

    public Hp(){ this(0); }

    public void hpSub(int dx){
        this.hp -= dx;
    }

    public int getHp() {
        return hp;
    }

    public boolean die(){
        if (hp == 0) return true;
        else return false;
    }
}


