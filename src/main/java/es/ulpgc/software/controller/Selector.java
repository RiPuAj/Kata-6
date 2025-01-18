package es.ulpgc.software.controller;

public interface Selector {
    Command build(String name);
}
