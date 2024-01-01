package fifthPackage;

public class Item {
    protected Item next;
    protected IBook data;

    public Item(IBook data) {
        this.next= null;
        this.data = data;
    }
    public Item() {
        this.next = null;
        this.data = new ScientificBook();
    }
    @Override
    public String toString() {
        return this.data.toString();
    }
}
