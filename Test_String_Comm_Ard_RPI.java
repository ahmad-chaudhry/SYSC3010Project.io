import jssc.SerialPort; 
import jssc.SerialPortException;
import java.util.ArrayList;

public class Test_String_Comm_Ard_RPI {

    public static void main(String[] args) {
        SerialPort serialPort = new SerialPort("/dev/ttyACM0");
        ArrayList<String> ar = new ArrayList<>();
        int i = 0;
        while (i != 5) {
           try {
                serialPort.openPort();//Open serial port
                serialPort.setParams(9600, 8, 1, 0);//Set params.
                String s = serialPort.readString(1);
                ar.add(s);
                System.out.println(s);
                serialPort.closePort();//Close serial port
                i++;
            }
           catch (SerialPortException ex) {
                System.out.println(9);
            }
        }
        
        System.out.println("printing out messages");

        for (int j=0;  j< 5; j++) {
            System.out.println(ar.get(j));
        }
    }
}