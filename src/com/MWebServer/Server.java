package com.MWebServer;

import java.util.Map;

public class Server {
	private final int _MAXDATALENTH_=20;
	private boolean adminSig;
 	private class Serverdata{
		private String data[];
		private int index;
		
		public Serverdata() {
			this.index=-1;
			this.data=new String[_MAXDATALENTH_];
		}
		
		public boolean addData(String s) {
			if(this.index>20)
				return false;
			this.data[++this.index]=s;
			return true;
		}
		
		public String getDataByIndex(int i) {
			return this.data[i];
		}
		public boolean editData(String s,int index) {
			if(index>this.index) return false;
			this.data[index]=s;
			return true;
		}
		public int getLength() {
			return this.index;
		}
		
	}
	private Serverdata DATA;
	public Server() {
		this.adminSig=false;
		this.DATA=new Serverdata();
	}
	public String HandleMessage(Map<String, String> map) {
		StringBuilder retBuilder=new StringBuilder();
		String bakstring=map.get("case");
		switch (bakstring) {
		case "getall":
			for(int i=0;i<=DATA.getLength();i++) {
				retBuilder.append(DATA.getDataByIndex(i)+"\n");
			}
			if(DATA.getLength()==-1)
				retBuilder.append("empty");
			break;
		case "post":
			if(DATA.getLength()>=_MAXDATALENTH_) {
				retBuilder.append("data overflowed max elem: "+_MAXDATALENTH_);
				break;
			}
			if(map.get("data").trim().equals("")) {
				retBuilder.append("data coudn't be empty");
				break;
			}
			DATA.addData(map.get("data"));
			retBuilder.append("recieved");
			break;
		case "login":
			if(map.get("username").trim().equals("")||map.get("password").trim().equals("")) {
				retBuilder.append("check username or password");
				break;
			}
			if(map.get("username").trim().equals("admin")&&map.get("password").trim().equals("admin")) {
				retBuilder.append("admin");
				setAdminSig(true);
			}
			else {
				retBuilder.append("guest");
				setAdminSig(false);
			}
			break;
		case "edit":
			
			if(getAdminSig()) {
				if(map.get("data").trim().equals("")||map.get("index").trim().equals("")) {
					retBuilder.append("check empty");
					break;
				}
				int ind=Integer.parseInt(map.get("index"));
				if(ind>DATA.index) {
					retBuilder.append("index invalid");
					break;
				}
				DATA.editData(map.get("data"), ind);
				retBuilder.append("edit seccess");
			}else {
				retBuilder.append("guest access failed");
			}

		default:
			break;
		}
		retBuilder.append("\n");
		return retBuilder.toString();
	}
	private void setAdminSig(boolean f) {
		this.adminSig=f;
	}
	private boolean getAdminSig() {
		return this.adminSig;
	}
}
