package exception;

import utils.PopUp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuplicataException extends Exception {
    String found = "";

    public DuplicataException(String message) {
        Pattern p = Pattern.compile("'.[^']*' ");
        Matcher m = p.matcher(message);
        if (m.find()) {
            this.found = m.group(0);
        }
    }

    public void showError() {
        PopUp.showError("Duplicata error", "Cannot dupliacte the following value : " + found);
    }
}
