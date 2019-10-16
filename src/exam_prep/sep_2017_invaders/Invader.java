package exam_prep.sep_2017_invaders;

public interface Invader extends Comparable<Invader> {

    int getDamage();

    int getDistance();

    void setDistance(int distance);
}
