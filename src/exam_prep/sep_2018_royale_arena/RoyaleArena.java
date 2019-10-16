package exam_prep.sep_2018_royale_arena;

import java.util.*;
import java.util.stream.Collectors;

public class RoyaleArena implements Arena {

    private Map<Integer, Battlecard> byId;
    private Map<CardType, Set<Battlecard>> byType;
    private TreeMap<Double, Set<Battlecard>> bySwagRange;
    private Map<String, TreeSet<Battlecard>> byNameAndSwag;
    

    public RoyaleArena() {
        this.byId = new LinkedHashMap<>();
        this.byType = new HashMap<>();
        this.byType.put(CardType.BUILDING, new HashSet<>());
        this.byType.put(CardType.MELEE, new HashSet<>());
        this.byType.put(CardType.RANGED, new HashSet<>());
        this.byType.put(CardType.SPELL, new HashSet<>());
        this.bySwagRange = new TreeMap<>();
        this.byNameAndSwag = new HashMap<>();
    }

    public void add(Battlecard card) {
        if(this.byId.containsKey(card.getId())) {
            return;
        }
        this.byId.put(card.getId(), card);
        this.byType.get(card.getType()).add(card);
        this.bySwagRange.putIfAbsent(card.getSwag(), new HashSet<>());
        this.bySwagRange.get(card.getSwag()).add(card);
        this.byNameAndSwag.putIfAbsent(card.getName(), new TreeSet<>(new Comparator<Battlecard>() {
            @Override
            public int compare(Battlecard o1, Battlecard o2) {
                double compare = o2.getSwag() - o1.getSwag();
                if(compare == 0) {
                    return o1.getId() - o2.getId();
                }
                return (int) compare;
            }
        }));
        this.byNameAndSwag.get(card.getName()).add(card);
    }

    public boolean contains(Battlecard card) {
        return this.byId.containsKey(card.getId());
    }

    public int getCount() {
        return this.byId.size();
    }

    public void changeCardType(int id, CardType type) {
       if(!this.byId.containsKey(id)) {
           throw new IllegalArgumentException();
       }
       Battlecard card = this.byId.get(id);
       this.byType.get(card.getType()).remove(card);
       card.setType(type);
       this.byType.get(type).add(card);
       this.byId.get(id).setType(type);
    }

    public Battlecard getById(int id) {
        if(!this.byId.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return this.byId.get(id);
    }

    public void removeById(int id) {
        Battlecard card = this.byId.remove(id);
        this.byType.get(card.getType()).remove(card);
        this.bySwagRange.get(card.getSwag()).remove(card);
        this.byNameAndSwag.get(card.getName()).remove(card);
    }

    @Override
    public Iterable<Battlecard> getByCardType(final CardType type) {
        return this.byType.get(type).stream().sorted(new Comparator<Battlecard>() {
            @Override
            public int compare(Battlecard o1, Battlecard o2) {
                double compare = o2.getDamage() - o1.getDamage();
                if(compare == 0) {
                    return Integer.compare(o1.getId(), o2.getId());
                }
                return (int)compare;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public Iterable<Battlecard> getByTypeAndDamageRangeOrderedByDamageThenById(final CardType type, final double lo, final double hi) {
        if(this.byType.get(type).size() == 0) {
            throw new IllegalArgumentException();
        }
        Set<Battlecard> cards = this.byType.get(type);
        return cards.stream()
                .filter(x -> x.getDamage() >= lo && x.getDamage() <= hi)
                .sorted(new Comparator<Battlecard>() {
                    @Override
                    public int compare(Battlecard o1, Battlecard o2) {
                        double compare = o2.getDamage() - o1.getDamage();
                        if(compare == 0) {
                            return o1.getId() - o2.getId();
                        }
                        return (int)compare;
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Battlecard> getByCardTypeAndMaximumDamage(final CardType type, final double damage) {
        List<Battlecard> result = this.byType.getOrDefault(type, new HashSet<>())
                .stream()
                .filter(x -> x.getDamage() <= damage)
                .collect(Collectors.toList());
        if(result.size() == 0) {
            throw new IllegalArgumentException();
        }
        result.sort(new Comparator<Battlecard>() {
            @Override
            public int compare(Battlecard o1, Battlecard o2) {
                double compare = o2.getDamage() - o1.getDamage();
                if(compare == 0) {
                    return o2.getId() - o1.getId();
                }
                return (int)compare;
            }
        });
        return result;
    }

    @Override
    public Iterable<Battlecard> getByNameOrderedBySwagDescending(final String name) {
        if(!this.byNameAndSwag.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        return this.byNameAndSwag.get(name);
    }

    @Override
    public Iterable<Battlecard> getByNameAndSwagRange(final String name, final double lo, final double hi) {
        return null;
    }

    @Override
    public Iterable<Battlecard> getAllByNameAndSwag() {
        List<Battlecard> result = new ArrayList<>();
        for (TreeSet<Battlecard> cards : this.byNameAndSwag.values()) {
            result.add(cards.first());
        }
        if(result.isEmpty()) {
            throw new UnsupportedOperationException();
        }
        return result;
    }

    @Override
    public Iterable<Battlecard> findFirstLeastSwag(final int n) {
        if(n > this.byId.size()) {
            throw new IllegalArgumentException();
        }
        List<Battlecard> result = new ArrayList<>(n);
        int count = 0;
        for (Set<Battlecard> cards : this.bySwagRange.values()) {
            if(cards.size() + count <= n) {
                result.addAll(cards);
                count += cards.size();
            }
            if(count == n) {
                break;
            }
        }
        result.sort(new Comparator<Battlecard>() {
            @Override
            public int compare(Battlecard o1, Battlecard o2) {
                double compare = o1.getSwag() - o2.getSwag();
                if(compare == 0) {
                    return o1.getId() - o2.getId();
                }
                return (int)compare;
            }
        });
        return result;
    }

    @Override
    public Iterable<Battlecard> getAllInSwagRange(final double lo, final double hi) {
        List<Battlecard> result = new ArrayList<>();
        Collection<Set<Battlecard>> values = this.bySwagRange.subMap(lo, hi + 0.01).values();
        if(values.size() == 0) {
            return new ArrayList<>();
        }
        for (Set<Battlecard> value : values) {
            result.addAll(value);
        }
        return result;
    }


    @Override
    public Iterator<Battlecard> iterator() {
        return this.byId.values().iterator();
    }
}
