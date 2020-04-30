package com.ohap.monitor.owner.generatecode;

public class MarkDownGenerator implements Generator {
    @Override
    public String getStart() {
        return "";
    }

    @Override
    public String getTitle(String title, String packagePath) {
        return new StringBuilder("# ").append(title).append("\n")
                .append("- package path :  ").append(packagePath).append("\n").toString();
    }

    @Override
    public String getTableHeader(String title, String className) {
        return new StringBuilder("\n\n## ").append(title).append("\n")
                .append("- enum : ").append(className).append("\n\n")
                .append("이름 | 코드").append("\n")
                .append(":--- | :---:").toString();
    }

    @Override
    public String getTableRow(String name, String code) {
        return new StringBuilder("\n").append(name).append(" | ").append(code).toString();
    }

    @Override
    public String getTableBottom() {
        return "";
    }

    @Override
    public String getEnd() {
        return "";
    }

    @Override
    public String getFileExtension() {
        return "md";
    }
}
