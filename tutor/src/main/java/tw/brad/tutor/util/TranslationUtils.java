package tw.brad.tutor.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import tw.brad.tutor.entity.TeacherSchedule;

public class TranslationUtils {
    private static final Map<String, String> MAPPINGS = Map.ofEntries(
        Map.entry("under_5", "5歲以下"), Map.entry("6_10", "6-10歲"),
        Map.entry("11_18", "11-18歲"), Map.entry("19_25", "19-25歲"),
        Map.entry("26_44", "26-44歲"), Map.entry("45_64", "45-64歲"),
        Map.entry("65_plus", "65歲以上"),
        Map.entry("conversation", "日常會話"), Map.entry("speaking", "口說練習"),
        Map.entry("listening", "聽力強化"), Map.entry("business", "商務英文"),
        Map.entry("abroad", "出國留學"), Map.entry("TOEFL", "托福"),
        Map.entry("TOEIC", "多益"),
        Map.entry("weekday", "平日"), Map.entry("weekend", "週末"),
        Map.entry("morning_before_12", "上午6點到12點"), Map.entry("afternoon_12_18", "下午12點到18點"),
        Map.entry("evening_18_24", "晚上18點到24點"),
        Map.entry("Taiwan", "台灣"), Map.entry("USA", "美國"),
        Map.entry("Japan", "日本"), Map.entry("Spain", "西班牙")
    );

    public static List<String> translateSet(String setValues) {
        if (setValues == null || setValues.isEmpty()) return List.of();
        return Arrays.stream(setValues.split(","))
                     .map(val -> MAPPINGS.getOrDefault(val.trim(), val))
                     .collect(Collectors.toList());
    }

    public static String translateSingle(String value) {
        if (value == null) return "";
        return MAPPINGS.getOrDefault(value, value);
    }
    
    public boolean hasSchedule(List<TeacherSchedule> schedules, String day, String slot) {
        if (schedules == null) return false;
        // 比對資料庫傳回的 weekday 和 timeSlot 是否符合格子的定義
        return schedules.stream().anyMatch(s -> 
            s.getWeekday().equalsIgnoreCase(day) && s.getTimeSlot().equalsIgnoreCase(slot)
        );
    }
}
