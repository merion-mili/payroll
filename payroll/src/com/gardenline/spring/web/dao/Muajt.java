package com.gardenline.spring.web.dao;


public enum Muajt {
 	January(1,"January"),
    February(2, "February"),
    March(3, "March"),
    April(4, "April"),
    May(5, "May"),
    Jun(6, "Jun"),
    July(7, "July"),
    August(8, "August"),
    September(9, "September"),
    October(10,"October"),
    November(11, "November"),
    December(12,"December");

    private int value;
    private String key;

    private Muajt(int value,String key) {
        this.value = value;
        this.key = key;
    }

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	   
}
