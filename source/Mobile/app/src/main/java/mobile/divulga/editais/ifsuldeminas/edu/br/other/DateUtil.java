package mobile.divulga.editais.ifsuldeminas.edu.br.other;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    private static final String MASK_DATE = "dd/MM/yyyy";

    public static String createDateFormatyyyyMMdd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(MASK_DATE);
        String ret = null;
        ret = sdf.format(date);
        return ret;
    }
}
