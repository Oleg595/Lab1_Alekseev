import java.awt.image.TileObserver;
import java.io.*;
import java.util.logging.Logger;

public class Config_Reader {
    private String read;//строка, отвечающая за файл, с которого считываются данные
    private String write;//строка, отвечающая за файл, в который записываются данные
    private Mode mode;
    //private int num_byte;
    private int size;
    static Logger LOGGER = Logger.getLogger(Config_Reader.class.getName());

    public enum Mode{
        ENCODING,
        DECODING
    }

    public Config_Reader(String conf){
        try(BufferedReader reader = new BufferedReader(new FileReader(conf))){
            String str = reader.readLine();
            Grammar gr = new Grammar();
            while(str != null){
                if(str.indexOf(Grammar.TOKENS.INPUT.toString() + gr.Delimeter()) == 0){//все лексемы вынесены в отдельный класс Grammar
                    read = str.substring(str.lastIndexOf(" ") + 1);
                }
                if(str.indexOf(Grammar.TOKENS.OUTPUT.toString() + gr.Delimeter()) == 0){
                    write = str.substring(str.lastIndexOf(" ") + 1);
                }
                if (str.indexOf(Grammar.TOKENS.WORD_SIZE.toString() + gr.Delimeter()) == 0){
                    size = Integer.parseInt(str.substring(str.lastIndexOf(" ") + 1));
                }
                if(str.indexOf(Grammar.TOKENS.MODE.toString() + gr.Delimeter()) == 0){
                    if(str.substring(str.lastIndexOf(" ") + 1).equals(Mode.ENCODING.toString())) {
                        mode = Mode.ENCODING;
                    }
                    if(str.substring(str.lastIndexOf(" ") + 1).equals(Mode.DECODING.toString())){
                        mode = Mode.DECODING;
                    }
                }
                str = reader.readLine();
            }
        }
        catch(IOException e){
            LOGGER.warning("ERROR: cann't make file");
        }
    }

    public String Read() {
        return read;
    }

    public String Write() {
        return write;
    }

    public int Size_Elem(){
        return size;
    }

    public Mode Mode(){
        return mode;
    }

    public long File_Size() {
        File file = new File(read);
        if(file != null) {
            return file.length();
        }
        else{
            return 0;
        }
    }

}
