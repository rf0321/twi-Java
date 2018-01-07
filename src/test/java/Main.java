import org.junit.Test;
import twijava.config.TokenBuilder;

public class Main {
    private static void initialize(){
        TokenbuilderTest.somekey = "aaaaaaaa";
    }
    public static void main(String[]args) throws Exception{
        initialize();
        TokenbuilderTest tokenbuilderTest = new TokenbuilderTest();
        tokenbuilderTest.printKey();
    }
}
