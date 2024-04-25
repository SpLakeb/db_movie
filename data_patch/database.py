#导入pymysql包
import pymysql
import os
# 创建数据库连接
conn = pymysql.connect(host="localhost",port=3306,user="root",passwd="root",db="db_movie" )
# #获取一个游标对象
cursor=conn.cursor()
#执行数据库插入操作

def parse_cinema_data(line):
    # 分割每行的数据
    parts = line.split(', ')
    # 解析每个键值对，并存入字典
    cinema_dict = {}
    for part in parts:
        key, value = part.split(': ', 1)
        if key == "Cinema ID" or key == "BrandId":  # 将ID转换为整数
            value = int(value)
        cinema_dict[key] = value
    return cinema_dict


with open(f'E:/code/movie/data_patch/brand.txt', 'r',encoding='utf-8') as f:
    for line in f:
        if line == "\n":
            break
        dict = parse_cinema_data(line)
        print(dict)
        sql = 'insert into t_cinema(cinemaId,cinemaName,brand, address) values (%s, %s, %s, %s)'
        cursor.execute(sql,(dict.get("Cinema ID"), dict.get("Name"),dict.get("BrandName"), dict.get("Address")))
#提交
conn.commit()
#关闭连接
conn.close()
cursor.close()
