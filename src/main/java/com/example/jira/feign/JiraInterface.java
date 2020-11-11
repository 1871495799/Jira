package com.example.jira.feign;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.ProjectRestClient;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.BasicProject;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.domain.input.FieldInput;
import com.atlassian.jira.rest.client.domain.input.IssueInput;
import com.atlassian.jira.rest.client.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.domain.input.TransitionInput;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Description : XXXX
 * Author by zengh17
 * Date on 2020/11/11 15:36
 */
public class JiraInterface {
    static JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
    static NullProgressMonitor pm = new NullProgressMonitor();
    static String uri="https://vxit.vx56.com";
    static String user="zengh17";
    static String pwd="test12345@";


    /**
     * 获取jira上所有的项目
     * @return
     * @throws URISyntaxException
     */
    public static Iterable<BasicProject> getProjects() throws URISyntaxException {
        URI jiraServerUri = new URI(uri);
        JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, user, pwd);
        ProjectRestClient project = restClient.getProjectClient();
        Iterable<BasicProject> basicProject= project.getAllProjects(pm);
        System.out.println("project ------------ : "+basicProject.toString());
        return basicProject;
    }


    /**
     * @param jql
     * @throws URISyntaxException
     */
    public static void  searchJql(String jql) throws URISyntaxException {
        URI jiraServerUri = new URI(uri);
        JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, user, pwd);
        SearchResult issue = restClient.getSearchClient().searchJql(jql,pm);
        System.out.println("project ------------ : "+issue.toString());
    }


    /**
     * @param jql
     * @param maxResults
     * @param startAt
     * @throws URISyntaxException
     */
    public static void  searchJqlWithFullIssues(String jql,int maxResults, int startAt) throws URISyntaxException {
        URI jiraServerUri = new URI(uri);
        JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, user, pwd);
        SearchResult issue = restClient.getSearchClient().searchJqlWithFullIssues(jql,maxResults,startAt,pm);
        System.out.println("project ------------ : "+issue.toString());

    }



    /**
     * 通过issueKey获取Issue的详细信息
     * @param issueKey
     * @throws URISyntaxException
     */
    public static void getIssue(String issueKey) throws URISyntaxException {
        URI jiraServerUri = new URI(uri);
        JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, user, pwd);
        Issue issue = restClient.getIssueClient().getIssue(issueKey, pm);
        System.out.println("issue ------------ : "+issue);
    }




    public static void createIssue(String projectName,String issueTypeId,String description,String summary) throws URISyntaxException{
        //JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
        URI jiraServerUri = new URI(uri);
        JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, user, pwd);
        IssueInputBuilder issueBuilder = new IssueInputBuilder("ProjectKey", Long.valueOf(issueTypeId));

        final URI projectUri = new URI(uri+"/rest/api/2/project");
        BasicProject bporject = new BasicProject(projectUri, projectName, "", (long) 10000);
        issueBuilder.setProject(bporject);
        issueBuilder.setDescription(description);
        issueBuilder.setSummary(summary);

        IssueInput issueInput = issueBuilder.build();
        BasicIssue bIssue = restClient.getIssueClient().createIssue(issueInput, pm);
        // print the newly created issuekey
        System.out.println(bIssue.getKey());
    }


    public static void updateIssueField(String key,String field,String value) throws URISyntaxException{
        URI jiraServerUri = new URI(uri);
        JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, user, pwd);
        //get the issue through issuekey
        Issue issue = restClient.getIssueClient().getIssue(key, pm);
        //update the default field environment,if the field is defined by self,pls use the filed FieldInput fieldinput = new FieldInput("environment", value);
        List<FieldInput> fields = new ArrayList<FieldInput>();
        FieldInput fieldInput=new FieldInput("","");
        fields.add(fieldInput);
        restClient.getIssueClient().update(issue, fields, pm);
    }


    public static void changeIssueStatus(String issuekey,int id) throws URISyntaxException {
        URI jiraServerUri = new URI(uri);
        JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, user, pwd);
        Issue issue = restClient.getIssueClient().getIssue(issuekey, pm);
        //the number 3 is involed below picture,you can focus on the red rectangular
        TransitionInput tinput= new TransitionInput(id );
        restClient.getIssueClient().transition(issue, tinput, pm);
    }



    public static void main(String[] args) {
        try {
//            //获取所有的项目信息
//            getProjects();
//            //通过jql查询最多50条数据
//            searchJql("project = ITDESK");
//            //通过jql查询自定义查询数据
//            searchJqlWithFullIssues("project = ITDESK",100,0);
//            //通过key查询Issue详情
//            getIssue("ITDESK-265");

            //创建Issue
//            createIssue("LSWMS","10001","自动化","测试");
            //更新Issue工作流状态
            changeIssueStatus("WA-1025",41);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
