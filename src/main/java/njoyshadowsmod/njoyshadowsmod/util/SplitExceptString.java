package njoyshadowsmod.njoyshadowsmod.util;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SplitExceptString {
    private final static EnglishToKorean englishToKorean = new KoreanConvertorUtil();

    public String getString(String string){
        // " "을 범위로 하여 영어로 변환할 지 말지 정함
        List<String> rawString = Arrays.asList(string.split("\""));
        List<String> newString = null;

        if(rawString.size() % 2 == 0){
            System.out.println(string);
            return string;
        }
        else{
            System.out.println(string);
            for(int i = 0; i < rawString.size(); i++)
            {
                if(i % 2 == 0)
                {
                    newString.add(englishToKorean.engToKor(rawString.get(i)));

                }
                else
                {
                    newString.add(rawString.get(i));
                }
            }
            return newString.stream().collect(Collectors.joining(" "));
        }
    }
}
