import java.io.*;
import java.util.logging.Logger;

public class Read {
    private FileInputStream inputStream;
    private int buf_size;
    static Logger LOGGER = Logger.getLogger(Read.class.getName());

    public Read(File file, int num){
        try{
            buf_size = num;
            inputStream = new FileInputStream(file);
        }
        catch(IOException e){
            LOGGER.warning("Not read file");
        }
    }

    public byte[] Read_File(int size) {
        try {
            byte[] data = new byte[size];
            int num = 0;
            int i = 0;
            for (; (i < buf_size) && ((num = inputStream.read()) != -1); i++) {
                data[i] = (byte)num;
            }
            return data;
        }
        catch(IOException e){
            LOGGER.warning("Cann't read file");
            return null;
        }
    }
}
