package com.alex.theguide.model;

import java.util.Arrays;
import java.util.List;

public enum FileType {
    image(".png", ".jpg"),
    pdf,
    movie,
    music,
    text(".doc", ".docx"),
    presentation(".pptx");

    private final List<String> values;

    FileType(String... values) {
        this.values = Arrays.asList(values);
    }

    public List<String> getValues() {
        return values;
    }

    public static FileType findEnum(String string) {
        for (FileType type : FileType.values()) {
            if (type.getValues().contains(string)) {
                return type;
            }
        }
        return null;
    }
}
