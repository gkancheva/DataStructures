package exam_prep.sep_2018_royale_arena;

public class Battlecard implements Comparable<Battlecard>{

    private int id;

    private CardType type;

    private String name;

    private Double damage;

    private Double swag;


    public Battlecard(int id, CardType type, String name, Double damage, Double swag){
        this.id = id;
        this.type = type;
        this.name = name;
        this.damage = damage;
        this.swag = swag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDamage() {
        return damage;
    }

    public void setDamage(Double damage) {
        this.damage = damage;
    }

    public Double getSwag() {
        return swag;
    }

    public void setSwag(Double swag) {
        this.swag = swag;
    }

    public int compareTo(Battlecard o) {
        return this.id - o.id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("exam_prep.sep_2018_royale_arena.Battlecard{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", name='").append(name).append('\'');
        sb.append(", damage=").append(damage);
        sb.append(", swag=").append(swag);
        sb.append('}');
        return sb.toString();
    }
}
