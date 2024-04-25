import requests
from bs4 import BeautifulSoup
import json

headers = {
    'User-Agent':
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36',
    'Cookie': 'uuid=91ED195001E811EFA3C20DA673AEB1D0C59848C4486D49A0ACD70B9182A77D4E; _csrf=c368f69e8dee5fb19aeefcef6ea27898c226c9310fef589496a2489c1853cd74; _lx_utm=utm_source%3Dbing%26utm_medium%3Dorganic; _lxsdk_cuid=18f0e17c0d5c8-07fda687c21411-26001d51-240000-18f0e17c0d5c8; _lxsdk=91ED195001E811EFA3C20DA673AEB1D0C59848C4486D49A0ACD70B9182A77D4E; Hm_lvt_703e94591e87be68cc8da0da7cbd0be2=1713928392; Hm_lpvt_703e94591e87be68cc8da0da7cbd0be2=1713934838; __mta=47051416.1713928393702.1713934837717.1713934837766.72; _lxsdk_s=18f0e17c0d5-2ee-866-90%7C%7C186'
}
# proxies = {
#   'http': 'http://10.16.20.125:7890',
#   'https': 'https://10.16.20.125:7890',
# }
# 目标网站URL


total_cinema = 39

brandid_list = [188982,
                357367,
                316970,
                358411,
                195014,
                188757,
                230647,
                494661,
                196371,
                327981,
                357245,
                358666,
                201744,
                366878,
                484416,
                956356,
                192146,
                498523,
                187555,
                324137,
                343400,
                383985,
                495495,
                358464,
                218699,
                398348,
                398989,
                358794,
                800153,
                496912,
                393630,
                494621,
                495463,
                813865,
                366755,
                495407,
                210217,
                494572,
                490686]

for brand_id in (brandid_list):
    url = f'https://www.maoyan.com/cinemas?brandId={brand_id}'
    response = requests.get(url)
    if response.status_code == 200:
        soup = BeautifulSoup(response.text, 'html.parser')
        cinema_cells = soup.find_all('div', class_='cinema-cell')
        for cinema in cinema_cells:
            # 提取影院名称
            cinema_name_tag = cinema.find('a', class_='cinema-name')
            cinema_id = (str(cinema_name_tag['data-val']).split(", ")[1].split(": ")[1].split("}")[0])
            cinema_name = cinema_name_tag.text.strip() if cinema_name_tag else 'Not found'

            # 提取影院地址
            cinema_address_tag = cinema.find('p', class_='cinema-address')
            cinema_address = str(cinema_address_tag.text.strip()).split("：")[1] if cinema_address_tag else 'Not found'

            print(f"Cinema ID: {cinema_id}, Name: {cinema_name}, Address: {cinema_address}, BrandId: {brand_id}")

    else:
        print("request error")
