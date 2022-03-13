import gnu.trove.list.array.TDoubleArrayList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Factorializer {

    public TDoubleArrayList factorials;
    int size = 1;

    public Factorializer(){
        factorials = new TDoubleArrayList();
        factorials.add(0.);
    }

    public double goToNumberLowMemory(int i){
        try {
            BufferedReader br = new BufferedReader(new FileReader("logs.in"));
            String line = null;
            for (int j = 1; j <= Math.min(i, size); j++){
                line = br.readLine();
            }
            br.close();
            if (i <= size) {
                assert line != null;
                return Double.parseDouble(line);
            } else {
                PrintWriter pw = new PrintWriter(new FileOutputStream("logs.in", true));
                assert line != null;
                double lastFact = Double.parseDouble(line);
                for (; size <= i;){
                    size++;
                    lastFact += Math.log(size);
                    pw.println(lastFact);
                }
                pw.close();
                return lastFact;
            }
        } catch (IOException e) {
            try {
                PrintWriter pw = new PrintWriter("logs.in");
                pw.println(0.);
                pw.close();
                goToNumber(i);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return -1.0;
    }
    public double goToNumber(int i){
        for (int j = factorials.size(); factorials.size() < i; j++){
            factorials.add(factorials.get(j - 1) + Math.log(j));
        }
        return factorials.get(i - 1);
    }


}
