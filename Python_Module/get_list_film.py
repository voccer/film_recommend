from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
import Browser, Setting as setting
from selenium import webdriver
import time
import numpy as np


class GetListFilm:
    """
    @:params request[] : Lưu tên checkbox cần check
    :param request_time[] : Lưu thời gian nhập vào nếu có
    :param number : Số lượng phim mặc đinh sẽ lấy
    :param browser : Trang web tìm kiếm film mặc định
    """
    def __init__(self):
        self.request = []
        self.request_time = []
        self.number = 0
        self.browser = Browser.get_driver()
        self.browser.get("https://www.imdb.com/search/title")

    """
    Đọc file text chứa các yêu cầu
    Chỉ lưu các trường hợp cần tìm vào mảng
    """
    def __read_request_file(self):
        with open(setting.DIR_PATH_COMMUNICATION + "/request.txt", mode ='r') as file:
            file = file.readlines()
        for f in file:
            if (f.find('1') != -1) and (not f.replace('-','').strip('\n ').isdigit()):
                self.request.append(f.strip('1\n '))
        self.request_time.append(file[-3].strip('\n '))
        self.request_time.append(file[-2].strip('\n '))
        self.number = int(file[-1].strip('\n'))

    """
    Thực hiện các thao tác ảo để click vào các check box đã chọn
    """
    def __select_checkbox(self):
        wait = WebDriverWait(self.browser, 10)
        xpath = ".//label[contains(text(),'%')]/preceding-sibling::input[@type='checkbox']"
        for x in self.request:
            print(self.request)
            e1 = wait.until(EC.presence_of_element_located((By.XPATH, xpath.replace('%', x))))
            e1.click()
            time.sleep(.2)

    """
    Thực hiện điền ngày tháng thích hợp vào hộp thời gian 
    """
    def __fill_time(self):
        wait = WebDriverWait(self.browser, 10)
        e1 = wait.until(EC.presence_of_element_located((By.NAME, "release_date-min")))
        e1.send_keys(self.request_time[0])
        time.sleep(.1)
        e1 = wait.until(EC.presence_of_element_located((By.NAME, "release_date-max")))
        e1.send_keys(self.request_time[1])

    """
    Thực hiện bấm nút search 
    """
    def __click_search(self):
        wait = WebDriverWait(self.browser, 10)
        e1 = wait.until(EC.presence_of_element_located((By.XPATH, "//button[@type='submit' and @class='primary']")))
        e1.click()

    """
    Thực hiện bấm nút Next
    """
    def __click_next(self):
        wait = WebDriverWait(self.browser, 10)
        try:
            e1 = wait.until(EC.presence_of_element_located((By.XPATH, "//a[@class='lister-page-next next-page']")))
            e1.click()
            return True
        except(Exception): return False

    """
    Thực hiện tìm link film và lấy vào list
    """
    def __get_list_film(self):
        html = self.browser.page_source
        soup = Browser.get_soup(html=html)
        film_div = []

        # Tìm đến khi nào đủ số lượng div
        while len(film_div) < self.number:
            film_div = film_div + soup.find_all("div", class_="lister-item mode-advanced")
            if (not self.__click_next()): break

        # Trích rút các href trong các thẻ 'a', đến khi nào đủ number thì dùng lại
        list_film = []
        count = 0
        for x in film_div:
            list_film.append("https://www.imdb.com/" + x.contents[3].find('a', href = True)['href'].split('?')[0])
            count += 1
            if count == self.number: break
        return np.array(list_film)

    def get_list(self):
        self.__read_request_file()
        self.__select_checkbox()
        self.__fill_time()
        self.__click_search()
        list = self.__get_list_film()
        self.browser.close()

        return list
