import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class consoleTest {
    @Test
    public void main() throws Exception {
        String[] args = {"123"};
        try{
            console.main(args);
            fail("Exception not thrown");
        }catch(Exception ignored){
        }

        String[] args1 = {"-123"};
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setErr(new PrintStream(out));
        console.main(args1);
        assertEquals("\"-123\" is not a valid option\r\n" +
                "pack-rle [-z|-u] [-out outputname.txtd] inputname.txt\r\n",out.toString());

        String[] args2 = {"-z","src/123.txt"};
        try{
            console.main(args2);
            fail("Exception not thrown");
        }catch(Exception ignored){
        }

        File outFile = new File("src/test1.txtd");
        String[] args3 = {"-z","src/test1.txt"};
        console.main(args3);
        assertEquals("2A-1B\r\n" +
                "-3ABA\r\n" +
                "-1A2B-1A\r\n" +
                "4A\r\n" +
                "\r\n" +
                "-1B3A\r\n" +
                "-5A B A\r\n", FileUtils.readFileToString(outFile));
        outFile.delete();

        File outFile2 = new File("src/test2.txt");
        String[] args4 = {"-u","src/test2.txtd"};
        console.main(args4);
        assertEquals("AAB\r\n" +
                "ABA\r\n" +
                "ABBA\r\n" +
                "AAAA\r\n" +
                "\r\n" +
                "BAAA\r\n" +
                "A B A\r\n", FileUtils.readFileToString(outFile2));
        outFile2.delete();

    }
}