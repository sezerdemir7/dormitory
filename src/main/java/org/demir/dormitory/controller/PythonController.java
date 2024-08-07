package org.demir.dormitory.controller;

import org.demir.dormitory.service.PythonScriptRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PythonController {

    private final PythonScriptRunner pythonScriptRunner;

    public PythonController(PythonScriptRunner pythonScriptRunner) {
        this.pythonScriptRunner = pythonScriptRunner;
    }

    @GetMapping("/export")
    public String exportToWord(@RequestParam Long id) {
        return pythonScriptRunner.exportToWord(id);
    }
}
