import numpy as np
from get_list_film import GetListFilm

# List film sau khi crawl
list_film = GetListFilm().get_list()

# List film đã có lấy từ database
exclusion_list = np.array(['https://www.imdb.com//title/tt0944947/'])

# List film không có trong database
list_not_exists = [item for item in list_film if item not in exclusion_list]

from crawl_datalink import SeleniumCrawler

# Pandas sau khi crawl
data_frame_not_exists = SeleniumCrawler(list_film).run_crawler()
# Thực hiện import vào csdl

# Tìm kiếm list film trong csdl

# import dữ liệu tìm được vào pandas

# Xuất pandas sang csv