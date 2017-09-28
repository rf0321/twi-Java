
//this is test main class



public class Main {
    public static void main(String[]args)throws Exception{
        TestTwiJava twitter=new TestTwiJava("","","","");
        System.out.println(twitter.tweet("Hello"));
    }
}
