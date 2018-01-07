public class TokenbuilderTest {
    public static String somekey;

    public void printKey() throws Exception{
        if(somekey == null){
            throw new Exception("key not found");
        }
        else{
            System.out.println(somekey);
        }
    }
}
