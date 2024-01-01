package fifthPackage;

public class Book implements IBook{
    private String author ;
    private String name ;
    private double cost ;
    private int year ;
    public Book() {
        this.author = "Не определено";
        this.name = "Не определено";
        this.cost = 0.0;
        this.year = 0;
    }

    public Book(String author, String name, double cost, int year) {
        this.author = author;
        this.name = name;
        this.cost = cost;
        this.year = year;
    }
    public Book(String author, int year) {
        this();
        this.author = author;
        this.year = year;
    }
    @Override
    public void setAuthor(String author) {
        this.author = author;
    }
    @Override

    public void setName(String name) {
        this.name = name;
    }
    @Override

    public void setCost(double cost) {
        this.cost = cost;
    }
    @Override

    public void setYear(int year) {
        this.year = year;
    }
    @Override

    public String getAuthor() {
        return author;
    }
    @Override

    public String getName() {
        return name;
    }
    @Override

    public double getCost() {
        return cost;
    }
    @Override

    public int getYear() {return year;}
    @Override
    public String toString() {
        return "\nАвтор: " + getAuthor()
                + "\nИмя: " + getName()
                + "\nСтоимость: " + getCost()
                + "\nГод: " + getYear();
    }
}

