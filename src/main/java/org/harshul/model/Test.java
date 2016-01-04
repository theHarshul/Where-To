package org.harshul.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Test {
	private int i;
	private String str;
	
	public Test(){
		
	}
	
	
	public Test(int i,String str){
		super();
		this.i = i;
		this.str = str;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

}
