package exam_prep.sep_2017_invaders;

public class InvaderImpl implements Invader {

    private int damage;
    private int distance;

    public InvaderImpl(int damage, int distance) {
        this.damage = damage;
        this.distance = distance;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int compareTo(Invader o) {
        int compare = Integer.compare(this.getDistance(), o.getDistance());
        if(compare == 0) {
            return Integer.compare(this.getDamage(), o.getDamage());
        }
        return compare;
    }
}
