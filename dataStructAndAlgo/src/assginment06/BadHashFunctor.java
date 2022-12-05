package assginment06;

public class BadHashFunctor implements HashFunctor{
    /**
     *
     * @param item
                 - the item to get hashcode from the BadHashFunctor
     * @return the hash of the item in int, which is the ASCII value of the first char of the String
     *
     */
    @Override
    public int hash(String item) {
        return item.charAt(0);
    }
}
