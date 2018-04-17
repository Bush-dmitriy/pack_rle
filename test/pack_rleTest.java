import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class pack_rleTest {
    @Test
    public void packing() throws IOException {
        new pack_rle().packing(System.getProperty("user.dir") + "\\src\\test1.txt", System.getProperty("user.dir") + "\\src\\test1.txtd");
        File test = new File(System.getProperty("user.dir") + "\\src\\test1.txtd");
        assertEquals("2A-1B\r\n-3ABA\r\n-1A2B-1A\r\n4A\r\n\r\n-1B3A\r\n-5A B A\r\n", FileUtils.readFileToString(test));
        test.delete();
    }

    @Test
    public void unpacking() throws IOException{
        new pack_rle().unpacking(System.getProperty("user.dir") + "\\src\\test2.txtd", System.getProperty("user.dir") + "\\src\\test2.txt");
        File test = new File(System.getProperty("user.dir") + "\\src\\test2.txt");
        assertEquals("AAB\r\nABA\r\nABBA\r\nAAAA\r\n\r\nBAAA\r\nA B A\r\n", FileUtils.readFileToString(test));
        test.delete();
    }
}