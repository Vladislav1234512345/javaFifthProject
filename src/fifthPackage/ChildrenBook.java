package fifthPackage;

public class ChildrenBook extends Book{
    protected int age;

    ChildrenBook() {
        super();
        this.age = 0;
    }
    ChildrenBook(String author, String name, double cost, int year, int age) {
        super(author, name, cost, age);
        this.age = age;
    }
    ChildrenBook(String author, int year) {
        super(author, year);
        this.age = 0;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "\nАвтор: " + getAuthor()
                + "\nИмя: " + getName()
                + "\nСтоимость: " + getCost()
                + "\nГод: " + getYear()
                + "\nВозраст: " + getAge();
    }
}
