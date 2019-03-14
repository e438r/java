package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="definetest")
@PropertySource("classpath:define.properties")
public class democonfig {
    private String name;

    private Integer age;

    private String servermac;

    private String clientmac;

    private String clientip;

    private String clientport;

    private String opena;

    private String openb;

    private String closea;

    private String closeb;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getServermac() {
        return servermac;
    }

    public void setServermac(String servermac) {
        this.servermac = servermac;
    }

    public String getClientmac() {
        return clientmac;
    }

    public void setClientmac(String clientmac) {
        this.clientmac = clientmac;
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public String getClientport() {
        return clientport;
    }

    public void setClientport(String clientport) {
        this.clientport = clientport;
    }

    public String getOpena() {
        return opena;
    }

    public void setOpena(String opena) {
        this.opena = opena;
    }

    public String getOpenb() {
        return openb;
    }

    public void setOpenb(String openb) {
        this.openb = openb;
    }

    public String getClosea() {
        return closea;
    }

    public void setClosea(String closea) {
        this.closea = closea;
    }

    public String getCloseb() {
        return closeb;
    }

    public void setCloseb(String closeb) {
        this.closeb = closeb;
    }
}
