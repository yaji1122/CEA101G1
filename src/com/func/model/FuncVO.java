package com.func.model;

import java.io.Serializable;

public class FuncVO implements Serializable {

   private static final long serialVersionUID = 1L;	
   private String func_no;
   private String func_name;
   
   public String getFunc_no() {
	return func_no;
   }
   
   public void setFunc_no(String func_no) {
	this.func_no = func_no;
   }
   
   public String getFunc_name() {
	return func_name;
   }
   
   public void setFunc_name(String func_name) {
	this.func_name = func_name;
   }
   
}
