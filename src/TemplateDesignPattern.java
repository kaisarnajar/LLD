abstract class Beverage
{
    public final void prepareRecipe()
    {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    private void boilWater()
    {
        System.out.println("Boil water........");
    }

    private void pourInCup()
    {
        System.out.println("Pouring in Cup.......");
    }

    abstract void brew();
    abstract void addCondiments();
}

class Tea extends Beverage
{
    @Override
    public void brew()
    {
        System.out.println("Brewing Tea......");
    }

    @Override
    public void addCondiments()
    {
        System.out.println("Adding condiements in tea......");
    }
}

//class Coffee extends Beverage
//{
//    @Override
//    public void brew()
//    {
//        System.out.println("Brewing coffee......");
//    }
//
//    @Override
//    public void addCondiments()
//    {
//        System.out.println("Adding condiments in coffee.....");
//    }
//}

public class TemplateDesignPattern {
    public static void main(String[] args) {
//        Coffee coffee = new Coffee();
//        coffee.prepareRecipe();

        Tea tea = new Tea();
        tea.prepareRecipe();
    }
}
