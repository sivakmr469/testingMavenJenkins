package com.accenture.pota.utils;

import java.util.List;

public class TagUtils {
	
	public static boolean isEmpty( String str ) {
		return ( str == null || str.trim().length() == 0 );
	}
	
	public static boolean isCharEmpty( Character chr ) {
		return ( chr == null || String.valueOf( chr.charValue() ).trim().length() == 0 );
	}
	
	public static boolean isNull( Object obj ) {
		return ( obj == null );
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isListEmpty( List list ) {
		return ( list == null || list.size() == 0 );
	}
	
	public static boolean isTrue(Boolean bool) {
		return !(bool == null || !bool);
	}
	
	public static boolean isPositiveInteger (String n){
		try{
			int a=new Integer(n);
			if (a>0){
				return true;
			}else {
				return false;
			}
		}catch (Exception e){
			return false;
		}
	}

	
}
