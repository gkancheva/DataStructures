package exam_prep.sep_2017_invaders;

public interface Computer {

    int getEnergy();

    void addInvader(Invader invader);

    void skip(int turns);

    void destroyTargetsInRadius(int radius);

    void destroyHighestPriorityTargets(int n);

    Iterable<Invader> invaders();
}
