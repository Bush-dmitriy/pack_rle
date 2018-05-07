import java.io.*;

public class Pack_rle {

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
                            if (unrepCount == 0 && repCount == 1) writer.write("-1 " + strLine.charAt(i));
                            if (unrepCount != 0) {
                                writer.append('-');
                                writer.append(String.valueOf(unrepCount + 1));
                                writer.append(" ");
                                writer.write(s.toString() + strLine.charAt(i));
                            }
                            if (repCount != 1) {
                                writer.append(String.valueOf(repCount));
                                writer.append(" ");
                                writer.append(strLine.charAt(i));
                            }
                        } else {
                            if (strLine.charAt(i) == (strLine.charAt(i + 1))) {
                                if (unrepCount != 0) {
                                    writer.append('-');
                                    writer.append(String.valueOf(unrepCount));
                                    writer.append(" ");
                                    writer.write(s.toString());
                                    unrepCount = 0;
                                    s = new StringBuilder();
                                }
                                repCount++;
                            } else {
                                if (repCount != 1) {
                                    writer.append(String.valueOf(repCount));
                                    writer.append(" ");
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
            System.out.println("Output Error" + e.getMessage());
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
                            i += 2;
                            int n = Character.digit(strLine.charAt(i - 1), 10);
                            while (!(strLine.charAt(i) == ' ')) {
                                if (i == strLine.length() - 1 || !Character.isDigit(strLine.charAt(i)))
                                    throw new IOException("Wrong Input File");
                                n = n * 10 + Character.digit(strLine.charAt(i), 10);
                                i++;
                            }
                            i++;
                            for (; n > 0; n--) {
                                writer.write(strLine.charAt(i));
                                i++;
                            }
                        } else {
                            if (Character.isDigit(strLine.charAt(i))) {
                                int n = Character.digit(strLine.charAt(i), 10);
                                i++;
                                while (!(strLine.charAt(i) == ' ')) {
                                    if (i == strLine.length() - 1 || !Character.isDigit(strLine.charAt(i)))
                                        throw new IOException("Wrong Input File");
                                    n = n * 10 + Character.digit(strLine.charAt(i), 10);
                                    i++;
                                }
                                i++;
                                for (; n > 0; n--) {
                                    writer.write(strLine.charAt(i));
                                }
                                i += 2;
                            } else throw new IOException("Wrong Input File");
                        }
                    }
                    writer.write("\r\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Output Error" + e.getMessage());
        }
    }
}