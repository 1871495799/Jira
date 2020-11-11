package com.example.jira.model;


import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author zengh17
 * @since 2020-04-7
 */
@Component
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 项目总数
     */
    private int count;

    /**
     * 是否是系统字典，Y-是，N-否
     */
    private List<AzureProduct> value;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<AzureProduct> getValue() {
        return value;
    }

    public void setValue(List<AzureProduct> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Project{" +
                "count=" + count +
                ", value=" + value +
                '}';
    }
}
