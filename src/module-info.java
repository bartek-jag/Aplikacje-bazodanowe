module Hurtownia {
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires com.sun.istack.runtime;
    requires java.logging;
    requires java.sql;
    requires java.naming;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.base;
    requires javafx.web;
    requires javafx.swing;
    requires com.sun.xml.bind;
    requires net.bytebuddy;

    opens main;
    opens main.model;
    opens main.controller;
}

