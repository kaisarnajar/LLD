package SnakeAndFood;

public class FoodItemFactory {
    public static FoodItem createFood(int[] position, String type) {
        if ("bonus".equals(type)) {
            return new BonusFood(position[0], position[1]);
        } else
            return new NormalFood(position[0], position[1]);
    }
}
