
from crawl_datalink import SeleniumCrawler
from get_list_film import GetListFilm
from crawl_datalink import SeleniumCrawler
import connect_db


# List film sau khi crawl
list_film = GetListFilm().get_list()


# List film đã có lấy từ database
exclusion_list = get_exclusion()


def get_exclusion():
    e_list = []
    sql_link = connect_db.select_link()
    for link in sql_link:
        e_list.append(link[0])
    return e_list


# List film không có trong database
list_not_exists = [item for item in list_film if item not in exclusion_list]


# Pandas sau khi crawl
data_frame_not_exists = SeleniumCrawler(list_film_not_exists).run_crawler()
# Thực hiện import vào csdl
'''
Đầu vào là 1 dataframe với các header giống với các trường trong db 
--> add toàn bộ vào db


'''

# Tìm kiếm list film trong csdl
'''
Lấy list film cần show ra(bao gồm cả có và không có trong db)
'''



# import dữ liệu tìm được vào pandas
'''
đầu vào là 1 danh sach sql, đầu ra là 1 biến pandas 
'''

# Xuất pandas sang csv

'''
từ pandas xuất csv để xuất ra hiển thị trong java
'''

