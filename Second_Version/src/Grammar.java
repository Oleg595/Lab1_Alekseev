public class Grammar {
    private final static String str = ": ";

    Grammar(){}

    public enum TOKENS{
        INPUT,
        OUTPUT,
        WORD_SIZE,
        MODE;
    }

    String Delimeter(){
        return str;
    }
}
