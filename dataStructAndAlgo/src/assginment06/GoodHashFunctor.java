package assginment06;

public class GoodHashFunctor implements HashFunctor {
    /**
     *
     * @param item
                    - the item to get hashcode from the MediocreHashFunctor

     * @return the hash of the item in int,
       which is (the sum of ASCII value of each char in the String)*(index of the char in the String +1)
     */
    @Override
    public int hash(String item) {
        long hash = 0;
        long index = 0;
        for (char i : item.toCharArray()) {
            index++;
            hash = hash + i * index;
        }
        return (int) hash;
    }
}
