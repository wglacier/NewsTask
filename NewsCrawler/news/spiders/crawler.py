
import re, time
import scrapy
from scrapy.selector import Selector
from news.items import NewsItem
#from readability import Document

class NewsSpider(scrapy.Spider):
    # spider name
    name = 'news'
    allowed_domains = ["www.theguardian.com"]
    start_urls = [
        "https://www.theguardian.com/au"
    ]
    re_clean = re.compile('<.*?>')
    
    # parse the top link
    def parse(self, response):
        print "parsing: " + response.url
        news_links = Selector(response).xpath('//a[@data-link-name="article"]')
        i = 0;
        for link in news_links:
            new_link = link.re(r'href="(.+?)"')[0]
            print "link: " + new_link
            yield scrapy.Request(new_link, callback=self.parse_news)
            #i = i+1
            #if i > 2:
            #    break

    # parse the individual news pages
    def parse_news(self, response):
        print "parsing news: " + response.url
        res_selector = Selector(response)
        item = NewsItem()
        # time
        item['last_updated'] = time.strftime("%Y-%m-%d %H:%M:%S")
        
        # url
        item['url'] = response.url
        
        # title
        item['title'] = res_selector.xpath('//h1[@itemprop="headline"]/text()').extract_first()
        
        # author
        item['author'] = res_selector.xpath('//span[@itemprop="name"]/text()').extract_first()
        
        # content
        article = res_selector.xpath('//div[@itemprop="articleBody"]').extract_first()
        item['content'] = re.sub(self.re_clean, '', article)
        yield item
