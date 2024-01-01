package fifthPackage;

public class HallIndexOutOfBoundsException extends IndexOutOfBoundsException{
    public HallIndexOutOfBoundsException(int index) {
        super("Index out of range: " + index);
    }
}
