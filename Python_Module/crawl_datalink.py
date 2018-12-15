from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import requests
import shutil
import pandas as pd
from bs4 import BeautifulSoup
import os
import browser

class SeleniumCrawler():
    def __init__(self, film_list, exclusion_list = None):
        self.film_list = film_list
        self.exclusion_list = exclusion_list

        self.data_frame = pd.DataFrame(columns = ['Link_Film', 'Name_Film','Start','Date','Description',
                                        'Link_Image','Total_Comment','Positive_Comment'])

        self.browser = browser.get_driver()
        # self.browser = webdriver.Chrome()
        self.output_image = os.path.dirname(os.path.realpath(__file__)) + "/Data/image"
        # self.linkReview = url + "/reviews"

    """
    Mở page dựa trên url truyền vào 
    Tự động click các button load more để hiển thị hết các comment 
    return : source page sau khi đã click hết vào các nút load more 
    """
    def get_page(self, url):
        self.browser.get(url)
        return self.browser.page_source

    """
    Download ảnh từ link
    """
    def download_images(self,links):
        response = requests.get(links, stream=True)
        self.save_image_to_file(response)
        del response

    """
    Lưu ảnh vòa thư mục
    """
    def save_image_to_file(self,image):
        with open('{dirname}/{name}.jpg'.format(dirname = self.output_image, name = self.nameFilm),'wb') as out_file:
            shutil.copyfileobj(image.raw, out_file)

    """
    Phân tích mã nguồn ở soup truyền vào và lấy comment, đánh giá theo sao của người dùng 
    """
    def get_data(self, soup):
        nameFilm = soup.find("div", class_="title_wrapper").find("h1").get_text().replace("\n"," ")
        star = soup.find("div", class_="ratingValue").find("span", attrs = {"itemprop" : "ratingValue"}).get_text().replace("\n"," ")
        date = soup.find("div", class_="subtext").find("a", title="See more release dates").get_text().replace("\n"," ")
        description = (soup.find("div", class_="summary_text").get_text().strip().replace("\n"," "))
        linkImage = soup.find("div", class_="poster").find("img").get("src")
        return [nameFilm, star, date, description, linkImage]

    def run_crawler(self):
        for index, link in enumerate(self.film_list):
            html = self.get_page(link)
            soup = browser.get_soup(html)
            if soup is not None:  # If we have soup - parse and write to our csv file
                table = [link] + self.get_data(soup) + ['1', '1']
                self.data_frame.loc[index] = table
        self.data_frame.to_csv("../../Data/file.csv", sep='\t')
        self.browser.close()