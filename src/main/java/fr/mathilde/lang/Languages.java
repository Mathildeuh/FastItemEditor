package fr.mathilde.lang;

import java.io.File;

public enum Languages {


    CUSTOM("langs/custom.yml", "Custom"), FR_FR("langs/fr_fr.yml", "Français"), EN_US("langs/en_us.yml", "English"), ES_ES("langs/es_es.yml", "Spanish");

    private final String langName;
    private final File file;
    private final String fileName;

    Languages(String fileString, String langName) {
        this.langName = langName;
        file = new File("plugins/FastItemEditor/" + fileString);
        this.fileName = fileString;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }

    public String getLangName() {
        return langName;
    }

}
