package njoyshadow.enk.utils;

import java.util.UUID;

public class CrimeModeUtil {
    private boolean IsEnable;

    public CrimeModeUtil(boolean isEnable){
        this.IsEnable = isEnable;
    }


    public boolean getIsEnable(){
        return IsEnable;
    }
    public void setIsEnable(boolean isEnable){
        this.IsEnable = isEnable;
    }

}
