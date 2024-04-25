import requests
from bs4 import BeautifulSoup
import json

headers = {
    'User-Agent':
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36',
    'Cookie': 'uuid=91ED195001E811EFA3C20DA673AEB1D0C59848C4486D49A0ACD70B9182A77D4E; _csrf=c368f69e8dee5fb19aeefcef6ea27898c226c9310fef589496a2489c1853cd74; _lx_utm=utm_source%3Dbing%26utm_medium%3Dorganic; _lxsdk_cuid=18f0e17c0d5c8-07fda687c21411-26001d51-240000-18f0e17c0d5c8; _lxsdk=91ED195001E811EFA3C20DA673AEB1D0C59848C4486D49A0ACD70B9182A77D4E; Hm_lvt_703e94591e87be68cc8da0da7cbd0be2=1713928392; Hm_lpvt_703e94591e87be68cc8da0da7cbd0be2=1713936615; __mta=47051416.1713928393702.1713934845315.1713936614881.74; _lxsdk_s=18f0e17c0d5-2ee-866-90%7C%7C192'
}
# proxies = {
#   'http': 'http://10.16.20.125:7890',
#   'https': 'https://10.16.20.125:7890',
# }
# 目标网站URL
url = 'https://www.maoyan.com/cinemas'

# 发送HTTP请求
response = requests.get(url, headers=headers)
if response.status_code == 200:
    soup = BeautifulSoup(response.text, 'html.parser')
    brand_list = soup.find('li', class_='tags-line', attrs={'data-type': 'brand'})
    brand_list = (brand_list.find_all('a'))
    for id, brand in enumerate(brand_list):
        if id == 0 or id == len(brand_list) - 1:
            continue
        else:
            print(brand.attrs['href'].split("?")[1] + ", " + brand.text)
            # print(brand.attrs['href'].split("?")[1])
