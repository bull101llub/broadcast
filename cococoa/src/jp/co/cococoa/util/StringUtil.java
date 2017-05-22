package jp.co.cococoa.util;

public class StringUtil {

	/**
	 * 検査文字列がNullまたは0byteの場合は変換文字列を
	 * 返します。
	 * @param value   検査文字列
	 * @param comvStr 変換文字列
	 * @return 文字列
	 * */
	public static String NVL(String value, String comvStr) {
        if (isEmpty(value)) {
        	return comvStr;
        }
        return value;
	}

    public static boolean isEmpty(String value) {
        if ( value == null || value.length() == 0 )
            return true;
        else
            return false;
    }


    public static String leftString(String text, int length, String addTxt) {
        String str = "";
        if (null != text) {
    	    if(text.length() >= length) {
    		    str = text.substring(0, length);
    	    } else {
    		    str = text;
    	    }
		    str += NVL(addTxt, "");
        }
        return str;
    }
}
