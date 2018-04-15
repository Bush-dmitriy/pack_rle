import org.kohsuke.args4j.*;

import java.io.File;
import java.io.IOException;

public class console {

    @Argument(required = true, metaVar = "pack-rle")
    private String name;

    @Argument(required = true, metaVar = "InputName", index = 1)
    private String inputFileName;

    @Option(name = "-z", metaVar = "pack", forbids = "-u")
    private boolean z;

    @Option(name = "-u", metaVar = "unpack", forbids = "-z")
    private boolean u;

    @Option(name = "-out", metaVar = "OutputName")
    private String outputFileName;

    public static void main(String[] args) throws IOException {
        new console().launch(args);
    }
    private void launch(String[] args) throws IOException{
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("pack-rle [-z|-u] [-out outputname.txtd] inputname.txt");
            parser.printUsage(System.err);
            return;
        }
        if (name == null || !name.contentEquals("pack-rle")) {
            throw new IllegalArgumentException("pack-rle [-z|-u] [-out outputname.txtd] inputname.txt");
        }
        if (inputFileName != null && !new File(inputFileName).exists()) {
            throw new IOException("Wrong input file name");
        }
        if(z){
            if (outputFileName == null){
                outputFileName = inputFileName+"d";
            }
            new pack_rle().packing(inputFileName,outputFileName);
        }
        else if (u){
            if (outputFileName == null){
                outputFileName = inputFileName.substring(0,inputFileName.length()-2);
            }
            new pack_rle().unpacking(inputFileName,outputFileName);
        }
        else throw new IllegalArgumentException("pack-rle [-z|-u] [-out outputname.txtd] inputname.txt");
    }

}
