import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class ÑonsoleTest {
    @Test
    public void main() throws Exception {
        String[] args = {"123"};
        try {
            Ñonsole.main(args);
            fail("Exception not thrown");
        } catch (Exception ignored) {
        }

        String[] args1 = {"-123"};
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setErr(new PrintStream(out));
        Ñonsole.main(args1);
        assertEquals("\"-123\" is not a valid option\r\n" +
                "pack-rle [-z|-u] [-out outputname.txtd] inputname.txt\r\n", out.toString());

        String[] args2 = {"-z", "src/123.txt"};
        try {
            Ñonsole.main(args2);
            fail("Exception not thrown");
        } catch (Exception ignored) {
        }

        File outFile = new File("test/test1.txtd");
        String[] args3 = {"-z", "test/test1.txt"};
        Ñonsole.main(args3);
        assertEquals("2 A-1 B\r\n" +
                "-3 ABA\r\n" +
                "-1 A2 B-1 A\r\n" +
                "4 A\r\n" +
                "\r\n" +
                "-1 B3 A\r\n" +
                "-5 A B A\r\n", FileUtils.readFileToString(outFile));
        outFile.delete();

        File outFile2 = new File("test/test2.txt");
        String[] args4 = {"-u", "test/test2.txtd"};
        Ñonsole.main(args4);
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