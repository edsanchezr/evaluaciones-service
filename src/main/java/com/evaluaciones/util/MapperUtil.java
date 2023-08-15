package com.evaluaciones.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import com.evaluaciones.common.enums.ErroresEnum;
import com.evaluaciones.common.exception.TransactionDataException;

@Component
public class MapperUtil {

	private static final String FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	@Named (value = "convertStringToDate")
	public Date convertStringToDate (String fecha) {
		Date date = null;
		if (fecha != null && !fecha.isEmpty()) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE);
			try {
				date = simpleDateFormat.parse(fecha);
			} catch (ParseException e) {
				e.printStackTrace();
				throw new 
				TransactionDataException(ErroresEnum.PARSE_ERROR.getCode(), 
						ErroresEnum.PARSE_ERROR.getDescription() , "DATETIME");
			}
		}
		return date;
	}
}
