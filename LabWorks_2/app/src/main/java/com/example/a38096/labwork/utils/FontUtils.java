package com.example.a38096.labwork.utils;

import android.content.Context;
import android.graphics.Typeface;

public class FontUtils {
    public static Typeface tf = Typeface.SANS_SERIF;

    public static Typeface getTypefaceByString(String font, Context context) {
        Typeface tf;
        switch (font) {
            case "Avenir":
                tf = Typeface.createFromAsset(context.getAssets(),
                        "fonts/avenir.otf");
                break;
            case "Cinzel Regular":
                tf = Typeface.createFromAsset(context.getAssets(),
                        "fonts/cinzel_regular.otf");
                break;
            case "Keepcalm medium":
                tf = Typeface.createFromAsset(context.getAssets(),
                        "fonts/keepcalm_medium.ttf");
                break;
            case "Platino linotype":
                tf = Typeface.createFromAsset(context.getAssets(),
                        "fonts/palatino_linotype.ttf");
                break;
            case "PZ Raleigh":
                tf = Typeface.createFromAsset(context.getAssets(),
                        "fonts/pz_raleigh.otf");
                break;
            case "Rounded elegance":
                tf = Typeface.createFromAsset(context.getAssets(),
                        "fonts/rounded_elegance.ttf");
                break;
                default:
                    tf = Typeface.SANS_SERIF;
        }
        return tf;
    }
}
