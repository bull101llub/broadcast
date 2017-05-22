package jp.co.cococoa.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

    /**
     * 指定されたフォーマットに基づき日付文字列を取得する。
     * 返します。
     * @param format 日付フォーマット指定文字列
     * @return 変換後日付文字列
     * */
    public static String getDateString(String formatString) {
        //現在日時を取得する
        Calendar c = Calendar.getInstance();

        //フォーマットパターンを指定して表示する
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        return sdf.format(c.getTime());
    }

    /**
     * 日付キー用フォーマット取得
     * @return 変換後日付文字列
     * */
    public static String getDateKey() {
        return getDateString("yyyyMMddHHmmssSSS");
    }

}
