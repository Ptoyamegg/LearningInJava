package xyz.dyk.chapter2.builderPatten.antipatten.patten;

/**
 * Builder Pattern
 */
public class NutritionFacts {
    private final int servingSize;  //  (mL)            required
    private final int servings;     //  (per container) required
    private final int calories;     //  (per serving)   optional
    private final int fat;          //  (g/serving)     optional
    private final int sodium;       //  (mg/serving)    optional
    private final int carbohydrate; //  (g/serving)     optional

    public static class Builder {
        //  required parameters
        private final int servingSize;  //  (mL)            required
        private final int servings;     //  (per container) required
        //  optional parameters - initialized to default values
        private int calories;           //  (per serving)   optional
        private int fat;                //  (g/serving)     optional
        private int sodium;             //  (mg/serving)    optional
        private int carbohydrate;       //  (g/serving)     optional

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder setCalories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder setFat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder setSodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder setCarbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    public NutritionFacts(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }
}
