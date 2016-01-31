package org.koko.event;

public class Event {

    private String giftId;
    private EventType typeEnum;

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getType() {
        return typeEnum.name();
    }

    public EventType getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(EventType typeEnum) {
        this.typeEnum = typeEnum;
    }

}
