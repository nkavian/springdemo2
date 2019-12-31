package info.jerrinot.springdemo.java;

public class SpringService {
    private final int magicValue;

    public SpringService(int magicValue) {
        this.magicValue = magicValue;
    }
    public int getMagicValue() {
        return magicValue;
    }
}
