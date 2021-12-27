package com.eleven.icode;

import com.eleven.icode.entity.JobDetail;
import com.eleven.icode.service.JobFullTextService;
import com.eleven.icode.service.impl.JobFullTextServiceImpl;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JobFullTextServiceTest {

    private JobFullTextService jobFullTextService;

    @BeforeTest
    public void beforeTest() {
        jobFullTextService = new JobFullTextServiceImpl();
    }

    @Test
    public void addTest() throws IOException {
        {
            JobDetail jobDetail = new JobDetail();
            jobDetail.setId(1);
            jobDetail.setArea("北京");
            jobDetail.setCmp("北京大学");
            jobDetail.setEdu("本科及以上");
            jobDetail.setExp("四年工作经验");
            jobDetail.setTitle("大数据架构师");
            jobDetail.setJob_type("全职");
            jobDetail.setPv("8000次浏览");
            jobDetail.setJd("大数据架构");
            jobDetail.setSalary("80K/月");
            jobDetail.setAge(32);
            jobFullTextService.add(jobDetail);
        }
        {
            JobDetail jobDetail = new JobDetail();
            jobDetail.setId(2);
            jobDetail.setArea("北京777");
            jobDetail.setCmp("清华大学1");
            jobDetail.setEdu("本科及以上1");
            jobDetail.setExp("五年工作经验1");
            jobDetail.setTitle("java架构师1");
            jobDetail.setJob_type("全职1");
            jobDetail.setPv("5000次浏览");
            jobDetail.setJd("Java架构2");
            jobDetail.setSalary("50K/月");
            jobDetail.setAge(28);
            jobFullTextService.add(jobDetail);
        }
    }

    @Test
    public void getTest() throws IOException {
        System.out.println(jobFullTextService.findById(4));
    }

    @Test
    public void updateTest() throws IOException {
        JobDetail jobDetail = jobFullTextService.findById(4);
        jobDetail.setTitle("java高级架构师");
        jobFullTextService.update(jobDetail);
    }

    @Test
    public void deleteTest() throws IOException {
        jobFullTextService.deleteById(1);
    }

    @Test
    public void searchTest() throws IOException {
        List<JobDetail> jobDetailList = jobFullTextService.searchByKeywords("java架构");
        for (JobDetail jobDetail : jobDetailList) {
            System.out.println(jobDetail);
        }
    }

    @Test
    public void searchByPageTest() throws IOException {
        Map<String, Object> resultMap = jobFullTextService.searchByPage("大数据", 1, 3);
        System.out.println("一共查询到:" + resultMap.get("total").toString());

        ArrayList<JobDetail> content = (ArrayList<JobDetail>) resultMap.get("content");
        for (JobDetail jobDetail : content) {
            System.out.println(jobDetail);
        }
    }

    @Test
    public void searchByScrollPageTest1() throws IOException {
        Map<String, Object> resultMap = jobFullTextService.searchByScrollPage("大数据", null, 10);
        System.out.println("scroll_id:" + resultMap.get("scroll_id").toString());

        ArrayList<JobDetail> content = (ArrayList<JobDetail>) resultMap.get("content");
        for (JobDetail jobDetail : content) {
            System.out.println(jobDetail);
        }
    }

    @Test
    public void searchByScrollPageTest2() throws IOException {
        Map<String, Object> resultMap = jobFullTextService.searchByScrollPage("spark", "FGluY2x1ZGVfY29udGV4dF91dWlkDXF1ZXJ5QW5kRmV0Y2gBFnJKUnZmX1pIVGVpM05TWDBQX0JJeXcAAAAAAAbB4BZDUkdZN1FJNVIwYUJhYUxvNWVxd1Rn", 10);
        System.out.println("scroll_id:" + resultMap.get("scroll_id").toString());
        ArrayList<JobDetail> content = (ArrayList<JobDetail>) resultMap.get("content");
        for (JobDetail jobDetail : content) {
            System.out.println(jobDetail);
        }
    }

    @AfterTest
    public void afterTest() throws IOException {
        jobFullTextService.close();
    }
}
