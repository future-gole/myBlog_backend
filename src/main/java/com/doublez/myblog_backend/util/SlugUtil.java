package com.doublez.myblog_backend.util;

//import org.springframework.stereotype.Component;
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SlugUtil {
//
//    private static final HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
//
//    static {
//        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//    }
//
//    public String toSlug(String input) {
//        if (input == null || input.trim().isEmpty()) {
//            return "";
//        }
//        try {
//            // 尝试将整个字符串转换为拼音
//            String pinyin = PinyinHelper.toHanyuPinyinString(input, format, "-");
//            // 清理特殊字符，并确保 slug 不以-开头或结尾
//            return pinyin.replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");
//        } catch (BadHanyuPinyinOutputFormatCombination e) {
//            // 如果转换失败（例如包含非中文字符），则使用备用方法
//            return input.trim().toLowerCase()
//                    .replaceAll("\\s+", "-") // 将空格替换为-
//                    .replaceAll("[^a-z0-9-]", ""); // 移除所有非字母数字和-的字符
//        }
//    }
//}
