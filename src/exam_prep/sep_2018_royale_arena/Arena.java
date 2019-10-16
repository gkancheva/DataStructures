package exam_prep.sep_2018_royale_arena;

public interface Arena extends Iterable<Battlecard> {

    void add(Battlecard card);

    boolean contains(Battlecard card);

    int getCount();

    void changeCardType(int id, CardType type);

    Battlecard getById(int id);
    void removeById(int id);

    Iterable<Battlecard> getByCardType(CardType type);
    Iterable<Battlecard> getByTypeAndDamageRangeOrderedByDamageThenById(CardType type, double lo, double hi);
    Iterable<Battlecard> getByCardTypeAndMaximumDamage(CardType type, double damage);

    Iterable<Battlecard> getByNameOrderedBySwagDescending(String name);
    Iterable<Battlecard> getByNameAndSwagRange(String name, double lo, double hi);

    Iterable<Battlecard> getAllByNameAndSwag();
    Iterable<Battlecard> findFirstLeastSwag(int n);

    Iterable<Battlecard> getAllInSwagRange(double lo, double hi);


}
