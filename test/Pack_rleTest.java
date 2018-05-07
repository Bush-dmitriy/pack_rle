import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class Pack_rleTest {
    @Test
    public void packing() throws IOException {
        new Pack_rle().packing(System.getProperty("user.dir") + "\\test\\test1.txt", System.getProperty("user.dir") + "\\test\\test1.txtd");
        File test = new File(System.getProperty("user.dir") + "\\test\\test1.txtd");
        assertEquals("2 A-1 B\r\n-3 ABA\r\n-1 A2 B-1 A\r\n4 A\r\n\r\n-1 B3 A\r\n-5 A B A\r\n", FileUtils.readFileToString(test));
        test.delete();
        new Pack_rle().packing(System.getProperty("user.dir") + "\\test\\test3.txt", System.getProperty("user.dir") + "\\test\\test3.txtd");
        File test3 = new File(System.getProperty("user.dir") + "\\test\\test3.txtd");
        assertEquals("", FileUtils.readFileToString(test3));
        test3.delete();
    }

    @Test
    public void unpacking() throws IOException {
        new Pack_rle().unpacking(System.getProperty("user.dir") + "\\test\\test2.txtd", System.getProperty("user.dir") + "\\test\\test2.txt");
        File test2 = new File(System.getProperty("user.dir") + "\\test\\test2.txt");
        assertEquals("AAB\r\nABA\r\nABBA\r\nAAAA\r\n\r\nBAAA\r\nA B A\r\n", FileUtils.readFileToString(test2));
        test2.delete();
        new Pack_rle().unpacking(System.getProperty("user.dir") + "\\test\\test4.txtd", System.getProperty("user.dir") + "\\test\\test4.txt");
        File test4 = new File(System.getProperty("user.dir") + "\\test\\test4.txt");
        assertEquals("", FileUtils.readFileToString(test4));
        test4.delete();
    }
}