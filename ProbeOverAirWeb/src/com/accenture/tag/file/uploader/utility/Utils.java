package com.accenture.tag.file.uploader.utility;

import java.util.List;

	public class Utils {
	
		public static boolean isNotEmptyNull(final String inputString) {
			boolean flag = true;
			if(inputString == null || "".equals(inputString.trim())) {
				flag = false;
			}
			return flag;
		}
		
		@SuppressWarnings("rawtypes")
		public static boolean isListEmpty( List list ) {
			return ( list == null || list.size() == 0 );
		}
}
