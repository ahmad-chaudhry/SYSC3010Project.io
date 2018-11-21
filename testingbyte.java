import jssc.SerialPort; 
import jssc.SerialPortException;

public class testingbyte {

    public static void main(String[] args) {
        SerialPort serialPort = new SerialPort("/dev/ttyACM0");
        while (true) {
            try {
                serialPort.openPort();//Open serial port
                serialPort.setParams(9600, 8, 1, 0);//Set params.
                byte[] buffer = serialPort.readBytes(5);//Read 10 bytes from serial port
                String s = new String(buffer);
                System.out.println(s);
                serialPort.closePort();//Close serial port
            }
            catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }
}