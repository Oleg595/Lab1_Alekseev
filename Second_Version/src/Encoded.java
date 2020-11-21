import java.util.TreeMap;

public class Encoded {
    private Symbols symb;
    double[] code;
    int[] num;

    public Encoded(Encoded_Read reader, Encoded_Print print){
        Symbols symb;
        while((symb = reader.Next_Sym()) != null) {
            Init(symb, reader.Code(), reader.Num());
            print.Print(Answer());
        }
    }

    private void Init(Symbols s, Double[] data, Integer[] n){
        symb = s;
        code = new double[data.length];
        num = new int[n.length];
        for(int i = 0; i < n.length; i++){
            code[i] = data[i];
            num[i] = n[i];
        }
    }

    private Byte[] Word(Double word, Integer len){
        Byte[] str = new Byte[len];
        Byte ch;
        for(int i = 0; i < len; i++){
            ch = symb.Symbol(word);
            str[i] = ch;
            word = (word - symb.Prev_Val_Key(ch)) / (symb.Val_Key(ch) - symb.Prev_Val_Key(ch));
        }
        return str;
    }

    private Byte[][] Answer(){
        Byte[][] ans = new Byte[num.length][];
        for(int i = 0; i < ans.length; i++){
            ans[i] = Word(code[i], num[i]);
        }
        return ans;
    }

}
