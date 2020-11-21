import java.util.*;

public class Coding {
    private Double[] code;
    private int[] size_b;
    private int buf_size;

    /*public Coding(Read read, Print write, long File_Size, long Num_Buf, int Size_Elem){
        long size = File_Size / Num_Buf + ((File_Size % Num_Buf == 0) ? 0 : 1);
        for(long i = 0; i < size; i++){
            byte[] buffer;
            if((size - 1 == i) && (File_Size % Num_Buf != 0)){
                buffer = new byte[(int)(File_Size % Num_Buf)];
            }
            else {
                buffer = new byte[(int)Num_Buf];
            }
            buffer = read.Read_File(buffer.length);
            Symbols symb = new Symbols(buffer);
            Init(symb, buffer, Size_Elem);
            write.Write(symb);
            write.Write_Code(Data(), Num_Elem());
        }
    }*/

    public Coding(Read read, Print write, long File_Size, int Size_Elem){
        long size = File_Size / Size_Elem + ((File_Size % Size_Elem == 0) ? 0 : 1);
        for(long i = 0; i < size; i++){
            byte[] buffer;
            if((size - 1 == i) && (File_Size % Size_Elem != 0)){
                buffer = new byte[(int)(File_Size % Size_Elem)];
            }
            else {
                buffer = new byte[(int)Size_Elem];
            }
            buffer = read.Read_File(buffer.length);
            Symbols symb = new Symbols(buffer);
            Init(symb, buffer, Size_Elem);
            write.Write(symb);
            write.Write_Code(Data(), Num_Elem());
        }
    }

    private void Init(Symbols elements, byte[] elem, int size){
        int k = (elem.length % size != 0) ? 1 : 0;
        size_b = new int[(elem.length / size) + k];
        code = new Double[(elem.length / size) + k];
        for(int i = 0; i < elem.length; i += size){
            byte[] arr;
            if(i + size > elem.length){
                arr = new byte[elem.length - i];
            }
            else{
                arr = new byte[size];
            }
            int j = 0;
            for(; (j < size) && (i + j < elem.length); j++){
                arr[j] = elem[i + j];
            }
            size_b[i / size] = j;
            code[i / size] = Code(elements, arr);
        }
    }

    private double Code(Symbols elements, byte[] arr){
        double a = 0.;
        double b = 1.;
        for(int i = 0; i < arr.length; i++){
            double a1 = a + (b - a) * elements.Prev_Val_Key(arr[i]);
            b = a + (b - a) * elements.Val_Key(arr[i]);
            a = a1;
        }
        return (a + b) / 2;
    }

    private Double[] Data(){
        return code;
    }

    private int[] Num_Elem(){
        return size_b;
    }
}
