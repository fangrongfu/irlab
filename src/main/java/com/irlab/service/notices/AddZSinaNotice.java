package com.irlab.service.notices;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.irlab.dao.FirmDao;
import com.irlab.dao.NoticeDao;
import com.irlab.entity.Firm;
import com.irlab.entity.Notice;
import com.irlab.util.GetSession;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author  fangrongfu
 * @version 1.0
 * @date    2017年10月9日下午1:12:35
 */
public class AddZSinaNotice implements PageProcessor {
	private Notice notice = new Notice();
	private Firm firm = new Firm();
	private static GetSession session = new GetSession();
	// 设置配置信息，重复1次，休眠时间3000毫秒
	private Site site = Site.me().setRetryTimes(1).setSleepTime(3000);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 通过SqlSession操作数据库
		FirmDao firmDao = session.getSqlSession().getMapper(FirmDao.class);
		//释放资源
		session.getSqlSession().commit();	
		List<Firm> f = firmDao.selectAllFirm();
		for (Firm firm : f) {
			String url = "http://vip.stock.finance.sina.com.cn/corp/go.php/vCB_BulletinZhong/stockid/";
			String urlZ = url.concat(firm.getF_code()).concat("/page_type/zqbg.phtml");
			// 中期报告
			Spider.create(new AddZSinaNotice()).addUrl(urlZ).thread(5).run();
		}
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	public void process(Page page) {
		String n_code = null;
		String title = null;
		String n_name = null;
		List<String> urls = page.getHtml().xpath("*//[@class='datelist']/ul/a").links().all();
		page.addTargetRequests(urls);
		// 获取网页标题进行处理
		title = page.getHtml().xpath("title/text()").toString();
		String n_title = page.getHtml().xpath("//div[@class='tagmain']/table/thead/tr/th/text()").toString();
		String n_time = page.getHtml().xpath("//div[@class='tagmain']/table/tbody//td[@class='graybgH2']/text()")
				.toString();
		String n_content = page.getHtml().xpath("//div[@id='content']/pre/text()").toString();
		String n_url = page.getHtml().xpath("//div[@class='tagmain']/table/thead/tr/th/font/a").links().get();
		Pattern pattern = Pattern.compile("[0-9]{6}");
		Matcher matcher = pattern.matcher(title);
		if (matcher.find() && matcher.group(0) != null) {
			n_code = matcher.group(0);
			int index1 = title.indexOf("(");
			n_name = title.substring(0, index1);
		}
		// 通过SqlSession操作数据库
	    NoticeDao noticeDao = session.getSqlSession().getMapper(NoticeDao.class);
		if ((noticeDao.selectNoticeByUrl(notice).size() == 0) && n_time != null) {
			notice.setN_name(n_name);
			notice.setN_code(n_code);
			notice.setN_title(n_title);
			notice.setN_time(n_time.substring(5, n_time.length()));
			notice.setN_content(n_content);
			notice.setN_url(n_url);
			notice.setN_event("中期报告");
			noticeDao.addNotice();
		}
		//释放资源
	    session.getSqlSession().commit();
	}
}
