public class Devices
{
    private Park park;
    private String name;
    private int minAge;
    private double minHeight;
    private double maxWeight;
    private boolean isOpen = true;
    private boolean isInorder = true;
    private double price;


    public Devices(String name, int minAge, double minHeight)
    {
        this.name = name;
        this.minAge = minAge;
        this.minHeight = minHeight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAllowed(int age, double height)
    {
        if(age >= this.minAge && height >= this.minHeight && isOpen && isInorder)
            return true;
        return false;
    }

    public double getPrice()
    {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}
