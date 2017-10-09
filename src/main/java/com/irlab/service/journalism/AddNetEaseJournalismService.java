package com.irlab.service.journalism;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.irlab.dao.JournalismDao;
import com.irlab.entity.Journalism;
import com.irlab.util.ListFor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author fangrongfu
 * @version 1.0
 * @date 2017年10月9日上午11:13:03
 */
public class AddNetEaseJournalismService implements PageProcessor {
	// 设置配置信息，重复1次，休眠时间3000毫秒
	private Site site = Site.me().setRetryTimes(1).setSleepTime(3000);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("爬虫");
		Spider.create(new AddNetEaseJournalismService()).addUrl("http://money.163.com/").thread(5).run();

	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	public void process(Page page) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		Journalism jour = new Journalism();
		String s = "http://money.163.com/17/0731/07/CQLJ6EKS0025816E.html";
		page.addTargetRequests(page.getHtml().links()
				.regex("(http://money\\.163\\.com/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+.*)").all());
		String j_url = page.getHtml().links()
				.regex("(http://money\\.163\\.com/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+/[\\w\\-]+.*)").get();
		// System.out.println("获取链接"+url);
		String title = page.getHtml().xpath("//div[@class='post_content_main']/h1/text()").toString();
		String time = page.getHtml().xpath("//div[@class='post_time_source']/text()").toString();
		list = page.getHtml().xpath("//div[@class='post_text']/p/text()").all();
		ListFor lf = new ListFor();
		String content = lf.forList(list);
		if ("".equals(content) || null == content) {
			jour.setJ_title(title);
			jour.setJ_time(time);
			jour.setJ_digest("");
			jour.setJ_content(content);
			jour.setJ_url(j_url);
		} else {
			String content1 = content.substring(0, 100);
			String content3 = content1.concat("......");
			jour.setJ_title(title);
			jour.setJ_time(time.substring(1, 11));
			jour.setJ_digest(content3);
			jour.setJ_content(content);
			jour.setJ_url(j_url);
			// mybatis配置文件
			String resource = "mybatis-config.xml";
			Reader reader = null;
			try {
				// 得到配置文件流
				reader = Resources.getResourceAsReader(resource);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 创建会话工厂，传入mybatis的配置文件
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
			// 通过工厂得到SqlSession
			SqlSession session = ssf.openSession();
			// 通过SqlSession操作数据库
			JournalismDao journalismDao = session.getMapper(JournalismDao.class);
			// 通过url来查询新闻信息以此来判断新闻表中是否已经存在爬到的新闻
			List<Journalism> j = journalismDao.selectJournalismByUrl(jour);
			if (j.size() == 0) {
				// 将爬到的新闻添加到数据库
				journalismDao.addJournalism(jour);
			}
			// 释放资源
			session.commit();
		}
		// page.putField("page", jour);
	}
}
