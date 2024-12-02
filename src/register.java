public class register {
    private static final int CAMPS = 30;

    private int owners[] = new int[CAMPS];

    public int getCamps()
    {
        return CAMPS;
    }

    public void setOwner(int position, int ownerId)
    {
        owners[position] = ownerId;
    }
    
    public int getOwner(int position)
    {
        return owners[position]; 
    }
}
