package exam_prep.sep_2017_invaders;

import java.util.*;

public class ComputerImpl implements Computer {

    private int energy;
    private TreeMap<Integer, List<Invader>> invadersByDistance;
    private Set<Invader> invaders;

    ComputerImpl(int energy) {
        if(energy < 0) {
            throw new IllegalArgumentException();
        }
        this.energy = energy;
        this.invadersByDistance = new TreeMap<>();
        this.invaders = new LinkedHashSet<>();
    }

    public int getEnergy() {
        return this.energy < 0 ? 0 : this.energy;
    }

    public void addInvader(Invader invader) {
        this.invaders.add(invader);
        this.invadersByDistance.putIfAbsent(invader.getDistance(), new ArrayList<>());
        this.invadersByDistance.get(invader.getDistance()).add(invader);
    }

    public void skip(int turns) {
        List<Invader> toRemove = new ArrayList<>();
        for (Invader invader : this.invaders) {
            int oldDistance = invader.getDistance();
            int newDistance = invader.getDistance() - turns;
            invader.setDistance(newDistance);
            this.invadersByDistance.get(oldDistance).remove(invader);
            if (newDistance <= 0) {
                this.energy -= invader.getDamage();
                toRemove.add(invader);
            } else {
                this.invadersByDistance.putIfAbsent(newDistance, new ArrayList<>());
                this.invadersByDistance.get(newDistance).add(invader);
            }
        }
        for (Invader invader : toRemove) {
            this.invaders.remove(invader);
        }
    }

    public void destroyTargetsInRadius(int radius) {
        List<Integer> keysToRemove = new ArrayList<>();
        for (Map.Entry<Integer, List<Invader>> pair : this.invadersByDistance.entrySet()) {
            if (pair.getKey() <= radius) {
                keysToRemove.add(pair.getKey());
                for (Invader invader : pair.getValue()) {
                    this.invaders.remove(invader);
                }
            } else {
                break;
            }
        }
        for (Integer integer : keysToRemove) {
            this.invadersByDistance.remove(integer);
        }
    }

    public void destroyHighestPriorityTargets(int n) {
        int removedCount = 0;
        for (Map.Entry<Integer, List<Invader>> pair : this.invadersByDistance.entrySet()) {
            pair.getValue().sort(Comparator.comparing(Invader::getDamage));
            for (int i = pair.getValue().size() - 1; i >= 0; i--) {
                this.invaders.remove(pair.getValue().remove(i));
                removedCount++;
                if (removedCount == n) {
                    return;
                }
            }
        }
    }

    public Iterable<Invader> invaders() {
        return this.invaders;
    }
}
