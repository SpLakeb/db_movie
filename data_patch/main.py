import requests
from bs4 import BeautifulSoup
import json
import pymysql
conn = pymysql.connect(host="localhost",port=3306,user="root",passwd="root",db="db_movie" )
# #获取一个游标对象
cursor=conn.cursor()

headers = {
    'User-Agent':
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36',
    'Cookie': '__mta=47051416.1713928393702.1713960589443.1713969215535.123; uuid_n_v=v1; uuid=91ED195001E811EFA3C20DA673AEB1D0C59848C4486D49A0ACD70B9182A77D4E; _lxsdk_cuid=18f0e17c0d5c8-07fda687c21411-26001d51-240000-18f0e17c0d5c8; _csrf=219e32f492f211c9cdc9af589b1cc902b55114c0176f9a952ee877edcd15bf1b; Hm_lvt_703e94591e87be68cc8da0da7cbd0be2=1713928392,1713953611,1713958444; _lx_utm=utm_source%3Dbing%26utm_medium%3Dorganic; WEBDFPID=x4v2w340xx435872z4x5685w6y53702681u891ww6zx97958xy8657yv-2029329681402-1713969680898WQEMWMCfd79fef3d01d5e9aadc18ccd4d0c95073635; token=AgGwIOAXYNeDiM7gPJ6TBpH5HKeaX_vsbBZ1bFKQcFkIyRMBi8YetOeLIbozqT9igP5qjfd8oMN_aQAAAAC0HwAA2yahvKGfEs9BBDI46B2S8aKRoz-5Gxc4qOZLljFEFUbFEeLsGJZRTXPNdsAVTggP; uid=720635605; uid.sig=XX5gSxlFzygvDigC-YJE30gIT_c; _lxsdk=91ED195001E811EFA3C20DA673AEB1D0C59848C4486D49A0ACD70B9182A77D4E; Hm_lpvt_703e94591e87be68cc8da0da7cbd0be2=1713969818; __mta=47051416.1713928393702.1713969215535.1713969818446.124; _lxsdk_s=18f1072b02e-efd-8b4-582%7C%7C40'
}
# proxies = {
#   'http': 'http://10.16.20.125:7890',
#   'https': 'https://10.16.20.125:7890',
# }
# 目标网站URL

url = 'https://www.maoyan.com/films?showType=1'

# 发送HTTP请求
response = requests.get(url, headers=headers)
print(response.status_code)
# 判断请求是否成功
if response.status_code == 200:
    soup = BeautifulSoup(response.text, 'html.parser')
    type_cell = soup.find_all('div', class_='movie-item-hover')
    for type in type_cell:
        movie_id = (str(type.find('a')['href']).split("/films/")[1])
        print(movie_id)
        print(str((type.find_all('div', class_='movie-hover-title'))[1]).split("</span>")[1].split('            </div>')[0].strip())
    # print(type_cell)
else:
    print("Failed to retrieve the website")