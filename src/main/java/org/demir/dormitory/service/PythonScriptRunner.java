package org.demir.dormitory.service;

public interface PythonScriptRunner {
    void runPythonScript(String scriptPath, String... args) throws Exception;

    String exportToWord(Long id);
}
