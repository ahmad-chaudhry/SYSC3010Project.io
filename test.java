
/**
 * Write a description of class test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import jssc.SerialPort; 
import jssc.SerialPortException;

public class test
{
   public static void main(String[] args) {
    SerialPort serialPort = new SerialPort("/dev/ttyACM0");
    try {
        System.out.println("Port opened: " + serialPort.openPort());
        System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0));
        System.out.println("\"Hello World!!!\" successfully writen to port: " + serialPort.writeBytes("Hello World!!!".getBytes()));
        System.out.println("Port closed: " + serialPort.closePort());
    }
    catch (SerialPortException ex){
        System.out.println(ex);
    }
}
}
