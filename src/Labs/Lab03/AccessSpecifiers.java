package Labs.Lab03;

public class AccessSpecifiers {

    private int privateVar = 0;
    protected int protectedVar = 0;
    int defaultVar = 0;
    public int publicVar = 0;

    private void privateMethod(){

        privateVar++;

    }

    protected void protectedMethod(){

        protectedVar++;

    }

    void defaultMethod(){

        defaultVar++;

    }

    public void publicMethod(){

        publicVar++;

    }

}
