import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Double;
import java.util.TreeMap;
import java.util.logging.Logger;

public class Encoded_Read {

    private BufferedReader read;
    private Double[] code;
    private Integer[] num;
    static Logger LOGGER = Logger.getLogger(Encoded_Read.class.getName());

    public Encoded_Read(String str){
        try{
            read = new BufferedReader(new FileReader(str));
        }
        catch(IOException e){
            LOGGER.warning("Not read file");
        }
    }

    public Symbols Next_Sym(){
        try {
            String str = read.readLine();
            if(str == null){
                return null;
            }
            int n = Integer.parseInt(str);
            String[] el = new String[Integer.parseInt(str)];
            str = read.readLine();
            el = str.split(" ");
            Byte[] key = new Byte[el.length];
            for(int i = 0; i < key.length; i++){
                key[i] = Byte.parseByte(el[i]);
            }
            str = read.readLine();
            el = str.split(" ");
            Double[] values = new Double[el.length];
            for(int i = 0; i < values.length; i++){
                values[i] = Double.parseDouble(el[i]);
            }
            Words();
            return new Symbols(key, values);
        }
        catch (IOException e){
            LOGGER.warning("Cann't read file");
            return null;
        }
    }

    private void Words(){
        try{
            String str = read.readLine();
            String[] s = new String[2];
            int n = Integer.parseInt(str);
            code = new Double[n];
            num = new Integer[n];
            for(int i = 0; i < n; i++){
                str = read.readLine();
                s = str.split(" ");
                code[i] = Double.parseDouble(s[0]);
                num[i] = Integer.parseInt(s[1]);
            }
        }
        catch(IOException e) {
            LOGGER.warning("Cann't read file");
        }
    }

    public Double[] Code() {
        return code;
    }

    public Integer[] Num(){
        return num;
    }

}
