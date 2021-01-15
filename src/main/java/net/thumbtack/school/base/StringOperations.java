package net.thumbtack.school.base;

import javax.xml.stream.events.Characters;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringJoiner;

public class StringOperations {

    public static int getSummaryLength(String[] strings) {
        //Возвращает суммарную длину строк, заданных массивом strings.
        int result = 0;
        for (int i = 0; i < strings.length; i++){
            result += strings[i].length();
        }
        return result;
    }

    public static String getFirstAndLastLetterString(String string) {
        //Возвращает двухсимвольную строку, состоящую из начального и конечного символов заданной строки.
        return new String(concat(Character.toString(string.charAt(0)), Character.toString(string.charAt(string.length() - 1))));
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
        //Возвращает true, если обе строки в позиции index содержат один и тот же символ, иначе false.
        return string1.charAt(index) == string2.charAt(index);
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
        //Возвращает true, если в обеих строках первый встреченный символ character находится в одной и той же позиции.
        //Просмотр строк ведется от начала.
        return string1.indexOf(character) == string2.indexOf(character);
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
        //Возвращает true, если в обеих строках первый встреченный символ character находится в одной и той же позиции.
        //Просмотр строк ведется от конца.
        return string1.lastIndexOf(character) == string2.lastIndexOf(character);
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
        //Возвращает true, если в обеих строках первая встреченная подстрока str начинается в одной и той же позиции.
        //Просмотр строк ведется от начала.
        return string1.indexOf(str) == string2.indexOf(str);
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
        //Возвращает true, если в обеих строках первая встреченная подстрока str начинается в одной и той же позиции.
        //Просмотр строк ведется от конца.
        return string1.lastIndexOf(str) == string2.lastIndexOf(str);
    }

    public static boolean isEqual(String string1, String string2) {
        //Возвращает true, если строки равны.
        return string1.equals(string2);
    }

    public static boolean isEqualIgnoreCase(String string1, String string2) {
        //Возвращает true, если строки равны без учета регистра(например, строки “abc” и “aBC” в этом смысле равны).
        return string1.equalsIgnoreCase(string2);
    }

    public static boolean isLess(String string1, String string2) {
        //Возвращает true, если строка string1 меньше строки string2.
        return string1.compareTo(string2) < 0;
    }

    public static boolean isLessIgnoreCase(String string1, String string2) {
        //Возвращает true, если строка string1 меньше строки string2 без учета регистра (например, строка “abc”
        //меньше строки “ABCd”в этом смысле).
        return string1.compareToIgnoreCase(string2) < 0;
    }

    public static String concat(String string1, String string2) {
        //Возвращает строку, полученную путем сцепления двух строк.
        return string1 + string2;
    }

    public static boolean isSamePrefix(String string1, String string2, String prefix) {
        //Возвращает true, если обе строки string1 и string2 начинаются с одной и той же подстроки prefix.
        return string1.startsWith(prefix) && string2.startsWith(prefix);
    }

    public static boolean isSameSuffix(String string1, String string2, String suffix) {
        //Возвращает true, если обе строки string1 и string2 заканчиваются одной и той же подстрокой suffix.
        return string1.endsWith(suffix) && string2.endsWith(suffix);
    }

    public static String getCommonPrefix(String string1, String string2) {
        //Возвращает самое длинное общее “начало”двух строк.Если у строк нет общего начала, возвращает пустую строку.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(string1.length(), string2.length()); i++){
            if (string1.charAt(i) == string2.charAt(i)){
                sb.append(string1.charAt(i));
            }
            else break;
        }
        return  sb.toString();
    }

    public static String reverse(String string) {
        //Возвращает перевернутую строку.
        return new StringBuilder(string).reverse().toString();
    }

    public static boolean isPalindrome(String string) {
        //Возвращает true, если строка является палиндромом, то есть читается слева направо так же, как и справа налево.
        return string.equals((new StringBuilder(string)).reverse().toString());
    }

    public static boolean isPalindromeIgnoreCase(String string) {
        //Возвращает true, если строка является палиндромом, то есть читается слева направо так же, как и справа
        //налево, без учета регистра.
        return isPalindrome(string.toLowerCase());
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings) {
        //Возвращает самый длинный палиндром (без учета регистра)из массива заданных строк.Если в массиве не
        // палиндромов, возвращает пустую строку.
       String resultPol = "";
        for (int i = 0; i < strings.length; i++) {
            if (isPalindromeIgnoreCase(strings[i])) {
                if (strings[i].length() > resultPol.length()) {
                    resultPol = strings[i];
                }
            }
        }
        return  resultPol;
    }

    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
        //Возвращает true, если обе строки содержат один и тот же фрагмент длиной length, начиная с позиции index.
        if(string1.length() >= (index + length) && string2.length() >= (index + length)) {
            return (string1.substring(index, index + length).equals(string2.substring(index, index + length)));
        }
        return false;
    }

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1, String string2, char replaceInStr2, char replaceByInStr2) {
        //Возвращает true, если после замены в string1 всех вхождений replaceInStr1 на replaceByInStr1 и замены в
        return isEqual(string1.replace(replaceInStr1, replaceByInStr1), string2.replace(replaceInStr2, replaceByInStr2));
    }

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1, String string2, String replaceInStr2, String replaceByInStr2) {
        //Возвращает true, если после замены в string1 всех вхождений строки replceInStr1 на replaceByInStr1 и замены
        //в string2 всех вхождений replceInStr2 на replaceByInStr2 полученные строки равны.
        return isEqual(string1.replaceAll(replaceInStr1, replaceByInStr1), string2.replaceAll(replaceInStr2, replaceByInStr2));
    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {
        //Возвращает true, если строка после выбрасывания из нее всех пробелов является палиндромом, без учета регистра.
        return isPalindromeIgnoreCase(string.replace(" ", ""));
    }

    public static boolean isEqualAfterTrimming(String string1, String string2) {
        //Возвращает true, если две строки равны, если не принимать во внимание все пробелы в начале и конце каждой строки.
        return isEqual(string1.trim(), string2.trim());
    }

    public static String makeCsvStringFromInts(int[] array) {
        //Для заданного массива целых чисел создает текстовую строку, в которой числа разделены знаком “запятая”
        //(т.н.формат CSV - comma separated values).Для пустого массива возвращается пустая строка.
        StringJoiner tempResult = new StringJoiner(",");
        if(array.length != 0) {
            for (int i = 0; i < array.length; i++) {
                tempResult.add(Integer.toString(array[i]));
            }
            return tempResult.toString();
        }
        else return "";
    }

    public static String makeCsvStringFromDoubles(double[] array) {
        //Для заданного массива вещественных чисел создает текстовую строку, в которой числа разделены знаком “запятая”,
        //причем каждое число записывается с двумя знаками после точки.Для пустого массива возвращается пустая строка.
        StringBuilder sb = new StringBuilder();
        if(array.length != 0){
            for (int i = 0; i < array.length; i++){
                sb.append(new DecimalFormat("#0.00").format(array[i])).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } else return "";
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {
        //То же, что и в упражнении 25, но возвращает StringBuilder.
        return  new StringBuilder().append(makeCsvStringFromInts(array));
    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {
        return new StringBuilder().append(makeCsvStringFromDoubles(array));
    }

    public static StringBuilder removeCharacters(String string, int[] positions) {
        //Удаляет из строки символы, номера которых заданы в массиве positions.Предполагается, что будут передаваться
        //только допустимые номера, упорядоченные по возрастанию.Номера позиций для удаления указаны для исходной строки.
        //Возвращает полученный в результате StringBuilder.
        StringBuilder sb = new StringBuilder(string);
        for (int i = 0; i < positions.length; i++) {
            sb.deleteCharAt(sb.toString().indexOf(string.charAt(positions[i])));
        }
        return sb;
    }

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
        //Вставляет в строку символы.Массивы positions и characters имеют одинаковую длину.В позицию positions[i]
        //в исходной строке string вставляется символ characters[i].Если в массиве positions один и тот же номер
        //позиции повторяется несколько раз, это значит, что в указанную позицию вставляется несколько символов, в
        //том порядке, в котором они перечислены в массиве characters.Предполагается, что будут передаваться
        //только допустимые номера, упорядоченные по неубыванию.Возвращает полученный в результате StringBuilder.
        StringBuilder sb = new StringBuilder(string);
        int deltaPos = 0;
        for (int i = 0; i < positions.length; i++) {
            sb.insert(positions[i]+deltaPos, characters[i]);
            deltaPos++;
        }
        return sb;
    }
}
