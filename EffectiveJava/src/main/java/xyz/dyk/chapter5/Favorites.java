package xyz.dyk.chapter5;

import java.util.HashMap;
import java.util.Map;

public class Favorites {
    private final Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(type, instance);
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

    public static void main(String[] args) {
        Favorites favorites = new Favorites();
        favorites.putFavorite(String.class, "Java");
        favorites.putFavorite(Integer.class, 0xcafebabe);
        favorites.putFavorite(Class.class, Favorites.class);
        String favoriteString = favorites.getFavorite(String.class);
        Integer favoriteInteger = favorites.getFavorite(Integer.class);
        Class<?> favoriteClass = favorites.getFavorite(Class.class);
        System.out.printf("%s %x %s%n", favoriteString, favoriteInteger, favoriteClass.getName());
    }
}
