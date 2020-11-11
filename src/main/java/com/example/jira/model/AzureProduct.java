package com.example.jira.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * auzre devops 所有项目表
 * </p>
 *
 * @author zengh17
 * @since 2020-04-08
 */
@Component
public class AzureProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */

    private Integer tid;

    /**
     * 项目id
     */
    private String id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目描述
     */
    private String description;
    /**
     * 项目url
     */
    private String url;
    /**
     * 项目状态
     */
    private String state;
    /**
     * 项目版本
     */
    private Integer revision;
    /**
     * 项目属性
     */
    private String visibility;
    /**
     * 项目时间
     */
    private Date lastUpdateTime;
    /**
     * 创建时间
     */
    private Date creatTime;

    private Integer removed;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        return "AzureProduct{" +
                "tid=" + tid +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", state='" + state + '\'' +
                ", revision=" + revision +
                ", visibility='" + visibility + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", creatTime=" + creatTime +
                ", removed=" + removed +
                '}';
    }
}
