package exception;

import utils.PopUp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuplicataException extends BISException {
    private String typeError = "Duplicata error";
    private String message = "Cannot duplicate the following value : ";
    String found = "";

    public DuplicataException(String message) {
        Pattern p = Pattern.compile("'.[^']*' ");
        Matcher m = p.matcher(message);
        if (m.find()) {
            this.found = m.group(0);
        }
    }

    @Override
    public String getTypeError() {
        return typeError;
    }

    @Override
    public String getMessage() {
        return message + found;
    }
}
