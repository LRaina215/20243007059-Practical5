package com.example.practicalfive;
public class TextProcessor {
    private static final String[] MUNICIPALITIES = {
            "Beijing", "Shanghai", "Chongqing", "Tianjin"
    };
    public static String processMunicipality(String municipality) {
        if (municipality == null) {
            return null;
        }

        String trimmed = municipality.trim();
        if (trimmed.isEmpty()) {
            return null;
        }

        for (String supported : MUNICIPALITIES) {
            if (supported.equalsIgnoreCase(trimmed)) {
                return supported;
            }
        }

        return null;
    }
}
