package assginment06;

public class JavaHashFunctor implements HashFunctor{
    @Override
    public int hash(String item) {
        return item.hashCode();
    }
}
