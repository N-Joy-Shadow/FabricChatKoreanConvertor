package njoyshadowsmod.njoyshadowsmod.util;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SplitExceptString {
    public String getString(String string){
        // " ' "을 범위로 하여 영어로 변환할 지 말지 정한다.
        String[] rawString = string.split("\'");
        List<String> newString = null;

        if(rawString.length % 2 != 0){
            for(int i = 0; i < rawString.length; i++)
            {
                if(i % 2 != 0){
                    newString.add(rawString[i]);

                }
            }
          for (String j : rawString){
              System.out.println(j);
          }
        }
        return rawString.toString();
    }
}
