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
 * @date 2017年10月9日上午10:59:04
 */
public class AddQQJournalismService implements PageProcessor {
	// 设置配置信息，重复1次，休眠时间3000毫秒
	private Site site = Site.me().setRetryTimes(1).setSleepTime(3000);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("爬虫");
		Spider.create(new AddQQJournalismService()).addUrl("http://finance.qq.com/").thread(5).run();
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	public void process(Page page) {
		// TODO Auto-generated method stub
		String content = null;
		List<String> list = new ArrayList<String>();
		Journalism jour = new Journalism();
		page.addTargetRequests(
				page.getHtml().links().regex("(http://finance\\.qq\\.com/a/[\\w\\-]+/[\\w\\-]+.*)").all());
		String j_url = page.getHtml().links().regex("(http://finance\\.qq\\.com/a/[\\w\\-]+/[\\w\\-]+.*)").get();
		String title = page.getHtml().xpath("//div[@class='hd']/h1/text()").toString();
		String time = page.getHtml().xpath("//span[@class='a_time']/text()").toString();
		list = page.getHtml().xpath("//div[@class='Cnt-Main-Article-QQ']//p[@class='text']/text()").all();
		ListFor lf = new ListFor();
		content = lf.forList(list);
		if ("".equals(content) || null == content) {
			jour.setJ_content(null);
			jour.setJ_title(title);
			jour.setJ_time(time);
			jour.setJ_digest("");
			jour.setJ_url(j_url);
		} else {
			String content1 = content.substring(0, 100);
			String content3 = content1.concat("......");
			jour.setJ_title(title);
			jour.setJ_time(time.substring(0, 11));
			jour.setJ_digest(content3);
			jour.setJ_content(content);
			jour.setJ_url(j_url);
			System.out.println("nihao");
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
