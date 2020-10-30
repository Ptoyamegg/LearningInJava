package xyz.dyk.typeinfo.pets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class PetCreator {
    private Random rand = new Random(47L);

    public PetCreator() {
    }

    public abstract List<Class<? extends Pet>> types();

    public Pet randomPet() {
        int n = this.rand.nextInt(this.types().size());

        try {
            return (Pet)((Class)this.types().get(n)).newInstance();
        } catch (InstantiationException var3) {
            throw new RuntimeException(var3);
        } catch (IllegalAccessException var4) {
            throw new RuntimeException(var4);
        }
    }

    public Pet[] createArray(int size) {
        Pet[] result = new Pet[size];

        for(int i = 0; i < size; ++i) {
            result[i] = this.randomPet();
        }

        return result;
    }

    public ArrayList<Pet> arrayList(int size) {
        ArrayList<Pet> result = new ArrayList();
        Collections.addAll(result, this.createArray(size));
        return result;
    }
}
