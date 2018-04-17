import java.io.*;

public class pack_rle {

    public void packing(String inputName, String outputName) throws IOException {

        try (FileInputStream fr = new FileInputStream(inputName)) {
            try (FileWriter writer = new FileWriter(outputName, true)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(fr));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    int repCount = 1;
                    int unrepCount = 0;
                    StringBuilder s = new StringBuilder();
                    for (int i = 0; i < strLine.length(); i++) {
                        if (i == strLine.length() - 1) {
                            if (unrepCount==0&&repCount==1) writer.write("-1" + strLine.charAt(i));
                            if (unrepCount != 0) {
                                writer.append('-');
                                writer.append(String.valueOf(unrepCount + 1));
                                writer.write(s.toString() + strLine.charAt(i));
                            }
                            if (repCount != 1) {
                                writer.append(String.valueOf(repCount));
                                writer.append(strLine.charAt(i));
                            }
                        } else {
                            if (strLine.charAt(i) == (strLine.charAt(i + 1))) {
                                if (unrepCount != 0) {
                                    writer.append('-');
                                    writer.append(String.valueOf(unrepCount));
                                    writer.write(s.toString());
                                    unrepCount = 0;
                                    s = new StringBuilder();
                                }
                                repCount++;
                            } else {
                                if (repCount != 1) {
                                    writer.append(String.valueOf(repCount));
                                    writer.append(strLine.charAt(i));
                                    repCount = 1;
                                    continue;
                                }
                                unrepCount++;
                                s.append(strLine.charAt(i));
                            }
                        }
                    }
                    writer.append("\r\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }

    public void unpacking(String inputName, String outputName) throws IOException {
        try (FileInputStream fr = new FileInputStream(inputName)) {
            try (FileWriter writer = new FileWriter(outputName, true)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(fr));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    int i = 0;
                    while (i < strLine.length()) {
                        if (strLine.charAt(i) == '-') {
                            i+=2;
                            int n = i;
                            for (; i < n + Character.digit(strLine.charAt(n - 1), 10); i++) {
                                writer.write(strLine.charAt(i));
                            }
                        } else {
                            int n = i+1;
                            for (; n < i + 1 + Character.digit(strLine.charAt(i), 10); n++) {
                                writer.write(strLine.charAt(i + 1));
                            }
                            i += 2;
                        }
                    }
                    writer.write("\r\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }
}