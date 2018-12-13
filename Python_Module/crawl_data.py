import logging
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from Browser import Browser
from selenium.webdriver.chrome.options import Options
import time
from bs4 import BeautifulSoup
import os
import re

class SeleniumCrawler(object):

    def __init__(self, url):
        self.browser = Browser().get_driver()
        self.output_file = os.path.dirname(os.path.realpath(__file__))+"/Data/comment"
        self.url = url
        self.star = []
        self.comment = []

    """
    Mở page dựa trên url truyền vào 
    Tự động click các button load more để hiển thị hết các comment 
    return : source page sau khi đã click hết vào các nút load more 
    """
    def get_page(self, url):
        try:
            self.browser.get(url)
            wait = WebDriverWait(self.browser, 10)
            while True:
                e1 = wait.until(EC.presence_of_element_located((By.XPATH, "//button[contains(text(),'Load More')]")))
                if not e1.is_displayed():
                    count = 0
                    while (not e1.is_displayed()) and count < 10:
                        time.sleep(.2)
                        count += 1
                        print(count)
                if e1.is_displayed():
                    e1.click()
                else: break

            return self.browser.page_source
        except Exception as e:
            logging.exception(e)
            return

    """
    Phân tích mã nguồn ở soup truyền vào và lấy comment, đánh giá theo sao của người dùng 
    """
    def get_data(self, soup):
        comment_div = soup.find_all("div", class_=re.compile("lister-item-content"))
        for x in comment_div:
            self.comment.append(x.find("div", class_="content").find("div").get_text().replace("\n"," "))
            if x.find("div", class_= "ipl-ratings-bar") == None:
                self.star.append(0)
            else:
                self.star.append(x.find("div", class_= "ipl-ratings-bar").find("span").find("span").get_text())

    """
    Lưu comment và số sao đọc được vào text 
    """
    def txt_output(self):
        for x, y in zip(self.star, self.comment):
            with open(self.output_file, mode = 'a' , encoding='utf-8') as outputfile:
                outputfile.write(x+'\n')
                outputfile.write(y+'\n')

    def run_crawler(self):
        html = self.get_page(self.url)
        soup = Browser().get_soup(html = html)
        if soup is not None:  # If we have soup - parse and write to our csv file
            self.get_data(soup)
            self.txt_output()
        self.browser.close()

if __name__ == '__main__':
    url = "https://www.imdb.com/title/tt7401588/reviews"
    crawler = SeleniumCrawler(url = url)
    try:
        crawler.run_crawler()
    except Exception as e:
        crawler.browser.close()