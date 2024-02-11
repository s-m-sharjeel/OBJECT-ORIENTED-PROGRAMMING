package Labs.Lab03;

public class TestAccessSpeciiers {

    public static void main(String[] args){

        AccessSpecifiers object = new AccessSpecifiers();
        // object of the AccessSpecifiers class created

        // object.privateVar cannot be accessed

        // object.privateMethod cannot be accessed

        object.protectedVar = 0;
        // protectedVar can be accessed

        object.protectedMethod();
        // protectedMethod can be accessed

        object.defaultVar = 0;
        // defaultVar can be accessed

        object.defaultMethod();
        // defaultMethod() can be accessed

        object.publicVar = 0;
        // publicVar can be accessed

        object.publicMethod();
        // publicMethod() can be accessed

    }

}
