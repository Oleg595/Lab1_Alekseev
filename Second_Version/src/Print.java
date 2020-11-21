import java.io.*;
import java.util.logging.Logger;

public class Print {

    private BufferedWriter write;
    static Logger LOGGER = Logger.getLogger(Print.class.getName());

    public Print(String writer){
        try {
            write = new BufferedWriter(new FileWriter(writer));
        }
        catch(IOException e){
            LOGGER.warning("Not write file");
        }
    }

    public void Write(Symbols symb) {
        Double[] arr = symb.Elem_Prob();
        try {
            write.write(symb.length() + "\n");
            Byte[] arrc = symb.Element();
            for (int i = 0; i < arrc.length; i++) {
                write.write((byte)arrc[i] + " ");
            }
            write.write("\n");
            for (int i = 0; i < arr.length; i++) {
                write.write(arr[i].toString() + " ");
            }
            write.write("\n");
        } catch (IOException e) {
            LOGGER.warning("Cann't write to file");
        }
    }

    public void Write_Code(Double[] code, int[] num){
        try {
            write.write(code.length + "\n");
            for (int i = 0; i < code.length; i++) {
                write.write(code[i].toString() + " " + num[i] + "\n");
            }
        } catch (IOException e) {
            LOGGER.warning("Cann't write to file");
        }
    }
    public void End_Print(){
        try{
            write.close();
        }
        catch (IOException e){
            LOGGER.warning("Cann't close file");
        }
    }

}

