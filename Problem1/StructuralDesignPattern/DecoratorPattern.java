// Use Case: Coffee Shop

// A system where different types of coffee can have additional ingredients (decorators) like milk, sugar, etc.

// Component interface
interface Beverage {
    double cost();
}

// Concrete components
class Espresso implements Beverage {
    @Override
    public double cost() {
        return 1.99;
    }
}

class HouseBlend implements Beverage {
    @Override
    public double cost() {
        return 0.89;
    }
}

// Decorator class
abstract class CoffeeDecorator implements Beverage {
    protected Beverage beverage;

    public CoffeeDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost();
    }
}

// Concrete decorators
class Milk extends CoffeeDecorator {
    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.30;
    }
}

class Sugar extends CoffeeDecorator {
    public Sugar(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.20;
    }
}

// Main
public class DecoratorPattern {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        beverage = new Milk(beverage);
        beverage = new Sugar(beverage);

        System.out.println("Cost of the beverage: $" + beverage.cost());
    }
}
