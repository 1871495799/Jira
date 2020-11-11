package com.example.jira.feign;


import com.example.jira.model.AzureProduct;
import com.example.jira.model.Project;
import com.example.jira.model.WorkItem;
import com.example.jira.model.WorkItems;
import com.example.jira.utils.HttpUtills;
import com.example.jira.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengh17
 * @since 2020-04-08
 */
@Service
@Transactional
public class AzureInterface {
    private static final Logger log = LoggerFactory.getLogger(AzureInterface.class);

    /**
     * 调用Azure Devops接口 ：https://dev.azure.com/vxit,返回项目详情
     * @return
     */
//    @Transactional(rollbackFor = Exception.class)
    public static Project getAzureProductInfo()  {
        String url="https://dev.azure.com/vxit/_apis/projects?api-version=5.1";
        return JsonUtils.getObjectFromJson(HttpUtills.get(url),Project.class);
    }


    /**
     * 调用Azure Devops接口 ;返回items详情
     * @return
     */
//    @Transactional(rollbackFor = Exception.class)
    public static String getAzureItems(String name) {
        String url ="https://dev.azure.com/vxit/"+name+"/"+name+" team/_apis/wit/wiql?api-version=5.1";
        String query ="SELECT [System.Id],[System.WorkItemType],[System.Title],[System.State],[System.AreaPath],[System.RelatedLinkCount],[System.IterationPath]  FROM workitems WHERE [System.TeamProject] = \""+name+"\"  ORDER BY [System.ChangedDate] DESC";
        return HttpUtills.post(url,query);
    }



    /**
     * 调用Azure Devops接口 ：返回relation关联关系
     * @return
     */
//    @Transactional(rollbackFor = Exception.class)
    public static String getAzureRelation(String name,int id) {
        String url ="https://dev.azure.com/vxit/"+name+"/_apis/wit/workitems/"+id+"?$expand=relations&api-version=5.1";
        String res=HttpUtills.get(url);
        return res;
    }



    /**
     * 调用Azure Devops接口 ：返回Iteration信息
     * @return
     */
//    @Transactional(rollbackFor = Exception.class)
    public String getAzureIteration(String name){
        String url ="https://dev.azure.com/vxit/"+name+"/"+name+" team/_apis/work/teamsettings/iterations?api-version=5.1";
        String res=HttpUtills.get(url);
        return res;
    }




    /**
     * 调用Azure Devops  timelog接口 ：返回TimeLoginfo信息
     * @return
     */
//    @Transactional(rollbackFor = Exception.class)
    public String getTimeLoginfo(){
        String url ="https://vxit.extmgmt.visualstudio.com/_apis/ExtensionManagement/InstalledExtensions/timelog/time-logging-extension/Data/Scopes/Default/Current/Collections/TimeLogData/Documents";
        String res=HttpUtills.get(url);
        return res;
    }



    /**
     * 调用Azure Devops  timelog接口 ：返回TimeLoginfo信息
     * @return
     */
//    @Transactional(rollbackFor = Exception.class)
    public String getAzureTeamInfo(String prject) {
        String url ="https://dev.azure.com/vxit/_apis/projects/"+prject+"/teams/"+prject+" Team/members?api-version=5.1";
        String res=HttpUtills.get(url);
        return res;
    }




    public String getAzureDevOpsTeamInfo() {
        String url ="https://dev.azure.com/vxit/_apis/projects/DevOps/teams/All/members?api-version=5.1";
        String res=HttpUtills.get(url);
        return res;
    }




    public List<String>  getRelease(String project) {
        String url ="https://vsrm.dev.azure.com/vxit/"+project+"/_apis/release/releases?api-version=5.1";
        List<String> list=HttpUtills.getMore(url);
        return list;
    }

    public List<String>  getRelease(String project,String continuationToken) {
        String url ="https://vsrm.dev.azure.com/vxit/"+project+"/_apis/release/releases?continuationToken="+continuationToken+"";
        List<String> list=HttpUtills.getMore(url);
        return list;
    }


    public String getReleaseInfo(String url) {
        String res=HttpUtills.get(url);
        return res;
    }






    public static void main(String args[]){

//        List<AzureProduct> list = getAzureProductInfo().getValue();        //查出微软接口所有项目信息
//        for (AzureProduct a : list) {
//            a.setRemoved(0);
//            a.setCreatTime(new Date());
//            log.info("插入的第： " + list.size() + "  个项目为： " + a);
//        }



        String res = getAzureItems("JiraDemo");          //通过项目名称查询微软项目items接口
        String result = "{" + HttpUtills.regExString("\"workItems\":.*", res);   //包装workItems结果，用于转对象
        WorkItems projectinfo = JsonUtils.getObjectFromJson(result, WorkItems.class);  //将json转换成WorkItems
        List<WorkItem> listWorkItem = projectinfo.getWorkItems();                     //的到items对象集合
        if (listWorkItem.size() > 0) {
            for (int i = 0; i < listWorkItem.size(); i++) {
                log.info("总共：" + listWorkItem.size() + " 条数据，" + "查询出第： " + i + "  个项目为： " + listWorkItem.get(i));

                }
            }
        }



}
