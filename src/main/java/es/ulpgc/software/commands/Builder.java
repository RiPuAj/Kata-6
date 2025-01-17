package es.ulpgc.software.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Builder {
    Command build(HttpServletRequest req, HttpServletResponse res);
}
