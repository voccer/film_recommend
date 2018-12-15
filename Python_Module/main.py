
from get_list_film import GetListFilm

list_film = GetListFilm().get_list()
#exclusion_list = ["https://www.imdb.com/title/tt4123430/?ref_=inth_ov_tt"]
from crawl_datalink import SeleniumCrawler
crawler = SeleniumCrawler(list_film).run_crawler()