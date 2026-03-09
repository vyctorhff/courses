package br.course.elite.java.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internacionalization {
    public void execute() {
        ResourceBundle rbPt = ResourceBundle.getBundle("messagens");
        IO.println(rbPt.getString("categoria.title"));

        ResourceBundle rbEn = ResourceBundle.getBundle("messagens", Locale.ENGLISH);
        IO.println(rbEn.getString("categoria.title"));
    }
}
