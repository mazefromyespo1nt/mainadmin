package com.reporter.pdf.casinorep.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReporterDateTimeUtil {
	//dateFormatDef preferred to use "dd-mm-yyyy"
	public static Date getDateFromString(String dateToParse, String dateFormatDef) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormatDef, Locale.US);

		Date date = null;
		try {
			date = formatter.parse(dateToParse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
