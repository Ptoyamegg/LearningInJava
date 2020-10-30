package xyz.dyk.typeinfo;

class Bulilding{}

class House extends Bulilding{}

public class ClassCasts {
    public static void main(String[] args) {
        Bulilding b = new House();
        Class<House> houseType = House.class;
        House h = houseType.cast(b);
        h = (House)b;   //  ... or just do this.
    }
}
