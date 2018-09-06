package immersivefood;

public enum FoodDecayTimers {
	
	apple(10);
	
	
    private int value;

	FoodDecayTimers(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

}
