package com.ohap.monitor.owner.generatecode;

public interface Generator {
    String getStart();

    String getTitle(String title, String packagePath);

    String getTableHeader(String title, String className);

    String getTableRow(String name, String code);

    String getTableBottom();

    String getEnd();

    String getFileExtension();
}
