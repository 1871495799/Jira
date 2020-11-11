package com.example.jira.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *
 * @author zengh17
 * @since 2020-04-7
 */

@Component
public class WorkItem implements Serializable {

    private static final long serialVersionUID = 2L;


    /**
     *
     */
    private int id;

    /**
     *
     */
    private String url;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "WorkItem{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
