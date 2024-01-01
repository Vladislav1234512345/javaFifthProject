package fifthPackage;

public class ScientificBook extends Book{
    private double index;
    ScientificBook() {
        super();
        this.index = 0.0;
    }
    ScientificBook(String author, String name, double cost, int year, double index) {
        super(author, name, cost, year);
        this.index = index;
    }
    ScientificBook(String author, int year) {
        super(author, year);
        this.index = 0.0;
    }
    public void setIndex(double index) {
        this.index = index;
    }
    public double getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "\nАвтор: " + getAuthor()
                + "\nИмя: " + getName()
                + "\nСтоимость: " + getCost()
                + "\nГод: " + getYear()
                + "\nИндекс цитируемости: " + getIndex();
    }
}
