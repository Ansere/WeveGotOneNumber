import org.apache.batik.transcoder.TranscoderException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class WeveGotOneNumber {

    private final static Map<Integer, String> map = new TreeMap<>();
    private final static Map<Long, String> mapExpanded = new TreeMap<>();
    private final static Set<Integer> list = new TreeSet<>();
    private final static Map<Integer, Integer> maxMap = new HashMap<>();


    public static void main(String[] args) throws IOException, TranscoderException {
        String s = "";
        Convert.toSVG("$$ " + s + " $$", "test.svg", true);
        System.out.println("size = " + NumberPathNew.f.factorials.size());
    }

    public static void individual(int num){
        //PrintWriter pw = new PrintWriter("numbers.out");
        long temp1 = System.currentTimeMillis();
        try {
            List<NumberPathNewer> listPaths = new ArrayList<>();
            listPaths.add(new NumberPathNewer(new ChildedInt(num, null, 0, true)));
            while (true) {
                NumberPathNewer min = listPaths.get(listPaths.size() - 1);
                if (min.thisValue() == null){
                    min.nextValue();
                }
                for (NumberPathNewer listPath : listPaths) {
                    if (listPath.thisValue() != null) {
                        if (listPath.thisValue().getValue() < min.thisValue().getValue()) {
                            min = listPath;
                        }
                    } else if (listPath.nextValueIfUnder(min.thisValue().getValue())) {
                        min = listPath;
                    }
                }
                ChildedInt temp = min.thisValue();
                if (temp.getValue() < 7 || list.contains(temp.getValue())) {
                    Map<Integer, NumberPathNewer> pathMap = listPaths.stream().collect(Collectors.toMap(x -> x.getValue().getValue(), x -> x));
                    String s = "";
                    while (temp.getChild() != null) {
                        String tempStr = "";
                        for (int i = 0; i < temp.getNumRoots(); i++){
                            tempStr += "\\sqrt{";
                        }
                        s = (temp.isFloor() ? "\\left\\lfloor {" : "\\left\\lceil {") + tempStr + (s.isEmpty() ? temp.getValue() : "") +  s;
                        tempStr = "";
                        s += "!";
                        for (int i = 0; i < temp.getNumRoots(); i++){
                            tempStr += "}";
                        }
                        s += tempStr + (temp.isFloor() ? "}\\right\\rfloor" : "}\\right\\rceil");
                        //pw.println(s);
                        //map.put(temp.getChild(), s);
                        list.add(temp.getChild());
                        temp = pathMap.get(temp.getChild()).getValue();
                    }
                    pathMap.clear();
                    Convert.toSVG("$$" + num + "=" + s + "$$", "test.svg", true);
                    break;
                }
                if (!temp.getValue().equals(min.getValue().getValue()) && !listPaths.stream().collect(Collectors.toMap(x -> x.getValue().getValue(), x -> x)).containsKey(temp.getValue())) {
                    listPaths.add(new NumberPathNewer(temp));
                }
                listPaths.remove(min);
                listPaths.add(min);
                min.nextValue();
            }
        } catch (Exception ignored){
            ignored.printStackTrace();;
        }
        //map.values().forEach(pw::println);
        //pw.close();
        System.out.println(System.currentTimeMillis() - temp1);
    }
    
    public static void newExpanded(int start, int end) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("numbers.out");
        long temp1 = System.currentTimeMillis();
        try {
            for (; start <= end; start++) {
                System.out.println(start);
                if (list.contains(start))
                    continue;
                List<NumberPathExpanded> listPaths = new ArrayList<>();
                listPaths.add(new NumberPathExpanded(new ChildedIntExpanded((long) start, null, 0, true)));
                while (true) {
                    NumberPathExpanded min = listPaths.get(listPaths.size() - 1);
                    if (min.thisValue() == null){
                        min.nextValue();
                    }
                    for (NumberPathExpanded listPath : listPaths) {
                        if (listPath.thisValue() != null) {
                            if (listPath.thisValue().getValue() < min.thisValue().getValue()) {
                                min = listPath;
                            }
                        } else if (listPath.nextValueIfUnder(min.thisValue().getValue())) {
                            min = listPath;
                        }
                    }
                    ChildedIntExpanded temp = min.thisValue();
                    if (temp.getValue() < start || mapExpanded.containsKey(temp.getValue())) {
                        Map<Long, NumberPathExpanded> pathMap = listPaths.stream().collect(Collectors.toMap(x -> x.getValue().getValue(), x -> x));
                        //int steps = (temp.getValue() >= 7 ? maxMap.get(temp.getValue()) : 0);
                        while (temp.getChild() != null) {
                            String s = "";
                            s += temp.getChild() + ": ";
                            s += ((temp.getValue() == null) ? "Known value" : temp.toString());
                            //pw.println(s);
                            //System.out.println(s);
                            mapExpanded.put(temp.getChild(), s);
                            //maxMap.put(temp.getChild(), ++steps);
                            temp = pathMap.get(temp.getChild()).getValue();
                        }
                        pathMap.clear();
                        break;
                    }
                    if (!temp.getValue().equals(min.getValue().getValue()) && !listPaths.stream().collect(Collectors.toMap(x -> x.getValue().getValue(), x -> x)).containsKey(temp.getValue())) {
                        listPaths.add(new NumberPathExpanded(temp));
                    }
                    listPaths.remove(min);
                    listPaths.add(min);
                    min.nextValue();
                }
            }
        } catch (Exception ignored){
            ignored.printStackTrace();;
        }
        map.values().forEach(pw::println);
        pw.close();
        System.out.println(System.currentTimeMillis() - temp1);
        //int max = Collections.max(maxMap.values());
        //maxMap.entrySet().stream().filter(e -> e.getValue() == max).forEach(System.out::println);
    }

    public static void newFaster(int start, int end) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("numbers.out");
        long temp1 = System.currentTimeMillis();
        try {
            for (; start <= end; start++) {
                System.out.println(start);
                if (list.contains(start))
                    continue;
                List<NumberPathNewer> listPaths = new ArrayList<>();
                listPaths.add(new NumberPathNewer(new ChildedInt(start, null, 0, true)));
                while (true) {
                    NumberPathNewer min = listPaths.get(listPaths.size() - 1);
                    if (min.thisValue() == null){
                        min.nextValue();
                    }
                    for (NumberPathNewer listPath : listPaths) {
                        if (listPath.thisValue() != null) {
                            if (listPath.thisValue().getValue() < min.thisValue().getValue()) {
                                min = listPath;
                            }
                        } else if (listPath.nextValueIfUnder(min.thisValue().getValue())) {
                            min = listPath;
                        }
                    }
                    ChildedInt temp = min.thisValue();
                    if (temp.getValue() < start || list.contains(temp.getValue())) {
                        Map<Integer, NumberPathNewer> pathMap = listPaths.stream().collect(Collectors.toMap(x -> x.getValue().getValue(), x -> x));
                        //int steps = (temp.getValue() >= 7 ? maxMap.get(temp.getValue()) : 0);
                        while (temp.getChild() != null) {
                            String s = "";
                            s += temp.getChild() + ": ";
                            s += ((temp.getValue() == null) ? "Known value" : temp.toString());
                            //pw.println(s);
                            //System.out.println(s);
                            map.put(temp.getChild(), s);
                            list.add(temp.getChild());
                            //maxMap.put(temp.getChild(), ++steps);
                            temp = pathMap.get(temp.getChild()).getValue();
                        }
                        pathMap.clear();
                        break;
                    }
                    if (!temp.getValue().equals(min.getValue().getValue()) && !listPaths.stream().collect(Collectors.toMap(x -> x.getValue().getValue(), x -> x)).containsKey(temp.getValue())) {
                        listPaths.add(new NumberPathNewer(temp));
                    }
                    listPaths.remove(min);
                    listPaths.add(min);
                    min.nextValue();
                }
            }
        } catch (Exception ignored){
            ignored.printStackTrace();;
        }
        map.values().forEach(pw::println);
        pw.close();
        System.out.println(System.currentTimeMillis() - temp1);
        //int max = Collections.max(maxMap.values());
        //maxMap.entrySet().stream().filter(e -> e.getValue() == max).forEach(System.out::println);
    }


    public static void regular() {
        //PrintWriter pw = new PrintWriter("numbers.out");
        long temp1 = System.currentTimeMillis();
        try {
            map.put(1, null);
            map.put(2, null);
            map.put(3, null);
            map.put(4, null);
            map.put(5, null);
            map.put(6, null);
            for (int start = 7; start <= 1000; start++) {
                if (list.contains(start))
                    continue;
                PriorityQueue<NumberPathRegular> set = new PriorityQueue<>();
                set.add(new NumberPathRegular(new ChildedInt(start, null, 0, true)));
                while (true) {
                    NumberPathRegular n = set.remove();
                    assert n != null;
                    ChildedInt temp = n.nextValue();
                    if (temp.getValue() < start || list.contains(temp.getValue())) {
                        set.add(n);
                        Map<Integer, NumberPathRegular> pathMap = set.stream().collect(Collectors.toMap(x -> x.getValue().getValue(), x -> x));
                        while (temp.getChild() != null) {
                            String s = "";
                            s += temp.getChild() + ": ";
                            s += ((temp.getValue() == null) ? "Known value" : temp.toString());
                            System.out.println(s);
                            //pw.println(s);
                            list.add(temp.getChild());
                            temp = pathMap.get(temp.getChild()).getValue();
                        }
                        pathMap.clear();
                        break;
                    }
                    if (!temp.getValue().equals(n.getValue().getValue()) && !set.stream().collect(Collectors.toMap(x -> x.getValue().getValue(), x -> x)).containsKey(temp.getValue())) {
                        set.add(new NumberPathRegular(temp));
                    }
                    set.add(n);
                }
            }
        } catch (Exception ignored){
            ignored.printStackTrace();
            //pw.close();
        }
        map.entrySet().stream().map(e -> e.getKey() + ": " + ((e.getValue() == null) ? "Known value" : e.getValue().toString())).toList().forEach(System.out::println);
        //pw.close();
        System.out.println(System.currentTimeMillis() - temp1);
    }


}
