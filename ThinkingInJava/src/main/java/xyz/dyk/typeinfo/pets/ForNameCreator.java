package xyz.dyk.typeinfo.pets;

import java.util.ArrayList;
import java.util.List;

public class ForNameCreator extends PetCreator {
    public static List<Class<? extends Pet>> types =
            new ArrayList<Class<? extends Pet>>();
    //  Types that you want to be randomly created:
    private static String[] typeNames = {
            "xyz.dyk.typeinfo.pets.Mutt",
            "xyz.dyk.typeinfo.pets.Pug",
            "xyz.dyk.typeinfo.pets.EgyptianMau",
            "xyz.dyk.typeinfo.pets.Manx",
            "xyz.dyk.typeinfo.pets.Cymric",
            "xyz.dyk.typeinfo.pets.Rat",
            "xyz.dyk.typeinfo.pets.Mouse",
            "xyz.dyk.typeinfo.pets.Hamster"
    };
    @SuppressWarnings("unchecked")
    private static void loader() {
        try {
            for (String name : typeNames)
                types.add(
                        (Class<? extends Pet>)Class.forName(name));
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    static { loader(); }
    public List<Class<? extends Pet>> types(){return types;}
}
