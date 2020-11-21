import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class Encoded_Print {

    private FileOutputStream write;
    static Logger LOGGER = Logger.getLogger(Encoded_Print.class.getName());

    public Encoded_Print(String str){
        try{
            write = new FileOutputStream(new File(str));
        }
        catch(IOException e){
            LOGGER.warning("Not print file");
        }
    }

    public void Print(Byte[][] data){
        try {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++)
                    write.write(data[i][j]);
            }
        }
        catch(IOException e){
            LOGGER.warning("Cann't print to file");
        }
    }

}
