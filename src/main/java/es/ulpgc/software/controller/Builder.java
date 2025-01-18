package es.ulpgc.software.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Builder {
    Command build(HttpServletRequest req, HttpServletResponse res);
}
