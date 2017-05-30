package com.tech.real;

public class ProvinceUtil {
	
	
	static String pro;
    public static String ProcessPro(String province) {
		// TODO Auto-generated method stub
      if (province.endsWith("省")) {
		pro=province.substring(0, province.length()-1);
		return pro;
	}else if (province.endsWith("市")) {
		pro=province.substring(0, province.length()-1);
		return pro;
	}else if (province.endsWith("自治区")&&!province.equals("内蒙古自治区")) {
		pro=province.substring(0, 2);
		return pro;
	}else if (province.endsWith("行政区")) {
		pro=province.substring(0, 2);
		return pro;
	}
      else{
    	  pro=province.substring(0, 3);
  		return pro;
      }
	}
    
    public static void main(String[] args) {
		//System.out.println(ProvinceUtil.ProcessPro("湖南省"));
		//System.out.println(ProvinceUtil.ProcessPro("重庆市"));
		//System.out.println(ProvinceUtil.ProcessPro("宁夏回族自治区"));
		//System.out.println(ProvinceUtil.ProcessPro("澳门特别行政区"));
		System.out.println(ProvinceUtil.ProcessPro("内蒙古自治区"));
	}
}
