import java.io.File;
import java.util.logging.Logger;

public class Main {
    static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if(args.length > 0){
            Config_Reader conf = new Config_Reader(args[0]);
            if(conf.Mode() == Config_Reader.Mode.ENCODING) {
                Read read = new Read(new File(conf.Read()), conf.Size_Elem());
                Print write = new Print(conf.Write());
                Coding code = new Coding(read, write, conf.File_Size(), conf.Size_Elem());
                write.End_Print();
            }
            else {
                if(conf.Mode() == Config_Reader.Mode.DECODING) {
                    Encoded_Read reader = new Encoded_Read(conf.Read());
                    Encoded_Print print = new Encoded_Print(conf.Write());
                    Encoded enc = new Encoded(reader, print);
                }
                else{
                    LOGGER.warning("Not mode");
                }
            }
        }
        else{
            LOGGER.warning("Not arguments");
        }
    }
}
