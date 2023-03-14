package unibo.model;

public class RoomModel {
    private int lf;
    private int ld;
    private int lr;
    private int lu;

    public RoomModel(){
        lf = 0;
        ld = 0;
        lr = 0;
        lu = 0;
    }

    public int getLf() {
        return lf;
    }

    public void setLf(int lf) {
        this.lf = lf;
    }

    public int getLd() {
        return ld;
    }

    public void setLd(int ld) {
        this.ld = ld;
    }

    public int getLr() {
        return lr;
    }

    public void setLr(int lr) {
        this.lr = lr;
    }

    public int getLu() {
        return lu;
    }

    public void setLu(int lu) {
        this.lu = lu;
    }
}
