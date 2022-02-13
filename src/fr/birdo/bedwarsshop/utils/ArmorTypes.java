package fr.birdo.bedwarsshop.utils;

public enum ArmorTypes {

    LEATHER(0),
    CHAINMAIL(1),
    IRON(2),
    DIAMOND(3);

    private final int index;

    ArmorTypes(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}