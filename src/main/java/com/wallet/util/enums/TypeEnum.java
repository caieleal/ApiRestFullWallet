package com.wallet.util.enums;

public enum TypeEnum {

    EN("ENTRADA"),
    SD("SAÍDA");

    private String value;

    public String getValue() {
        return value;
    }

    TypeEnum(String value) {
        this.value = value;
    }
    public static TypeEnum getEnum(String value){

        for(TypeEnum te : values()){
            if(value.equals(te.getValue())){
                return te;
            }
        }
        return null;
    }
}
