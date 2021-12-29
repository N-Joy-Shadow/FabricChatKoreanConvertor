package njoyshadowsmod.fabricchatkoreanconvertor.utils;

import java.util.UUID;

public class EnKData {
    private UUID playerUUID;
    private boolean IsEnable;

    public EnKData(UUID uuid,boolean isEnable){
        this.playerUUID = uuid;
        this.IsEnable = isEnable;
    }


    public void setUUID(UUID uuid){
        this.playerUUID = uuid;
    }
    public void setIsEnable(boolean isEnable){
        this.IsEnable = isEnable;
    }
    public UUID getUUID(){
        return playerUUID;
    }
    public boolean getIsEnable(){
        return IsEnable;
    }
}