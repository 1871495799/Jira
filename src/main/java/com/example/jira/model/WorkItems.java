package com.example.jira.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author zengh17
 * @since 2020-04-7
 */

@Component
public class WorkItems implements Serializable {

    private static final long serialVersionUID = 2L;


    /**
     *
     */
    private List<WorkItem> workItems;

    public List<WorkItem> getWorkItems() {
        return workItems;
    }

    public void setWorkItems(List<WorkItem> workItems) {
        this.workItems = workItems;
    }

    @Override
    public String toString() {
        return "WorkItems{" +
                "workItems=" + workItems +
                '}';
    }
}
