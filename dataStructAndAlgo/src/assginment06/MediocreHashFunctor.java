package assginment06;

public class MediocreHashFunctor implements HashFunctor{
    /**
     *
     * @param item
                    - the item to get hashcode from the MediocreHashFunctor
     * @return the hash of the item in int, which is the sum of ASCII values of all the chars in the String
     */
    @Override
    public int hash(String item) {
        long hash = 0;
        for (char c : item.toCharArray()) {
            hash += c;
        }
        return (int) hash;
    }
}
