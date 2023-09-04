import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Convertor {
    private final Map<String, Integer> rimArabicMap = new HashMap<>();
    private final Map<Integer, String> arabicRimMap = new HashMap<>();

    public Convertor() {
        initRimArabicMap();
        initArabicRimMap();
    }

    private void initArabicRimMap() {
        RimNumber[] values = RimNumber.values();
        IntStream.range(1, 101).forEach(i -> arabicRimMap.put(i, values[i - 1].toString()));
    }

    private void initRimArabicMap() {
        RimNumber[] values = RimNumber.values();
        IntStream.range(1, 11).forEach(i -> rimArabicMap.put(values[i - 1].toString(), i));
    }

    public Integer rimToArabic(String rimNumber) {
        return rimArabicMap.get(rimNumber);
    }

    public boolean contains(String num) {
        return rimArabicMap.containsKey(num);
    }

    public String arabicToRim(Integer arabicNum) {
        return arabicRimMap.get(arabicNum);
    }
}
