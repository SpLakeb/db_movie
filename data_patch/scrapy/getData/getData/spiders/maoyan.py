import json
import re

import scrapy


class MaoyanSpider(scrapy.Spider):
    name = "maoyan"
    allowed_domains = ['www.maoyan.com']
    start_urls = ['https://www.maoyan.com/films']

    def parse(self, response):
        dd_list = response.xpath('//dl[@class="movie-list"]/dd')
        href_pattern = re.compile(r'<a href="(/films/\d+)"')
        hrefs = href_pattern.findall(dd_list.get())
        for i in range(len(hrefs)):
            hrefs[i] = int(hrefs[i].strip("'/films/").strip("'"))
        print(hrefs)
        # / html / body / div[4] / div / div[2] / div[2] / dl / dd[9]
        # print((dl.get()))
        # print(len(response.xpath('/html/body/div[4]/div/div[2]/div[2]/dl[1]/dd')))
        # print(len(dl.xpath('./dd')))
        # for dd in dl.xpath('./dd'):
        #     print(str(dd.xpath('./div/a').xpath('./@href').get()).strip('/films/'))
