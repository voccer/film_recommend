import logging
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
import Browser
import time
import re

from nltk.tokenize import word_tokenize
import numpy as np
import Setting as setting


class GetComment():
    def __init__(self):
        self.browser = Browser.get_driver()
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
                if e1.is_displayed(): e1.click()
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
            self.comment.append(x.find("div", class_="content").find("div").get_text().replace("\n", " "))
            if x.find("div", class_="ipl-ratings-bar") is None:
                self.star.append(0)
            else:
                self.star.append(x.find("div", class_="ipl-ratings-bar").find("span").find("span").get_text())

    def run_crawler(self, url):
        html = self.get_page(url)
        soup = Browser.get_soup(html=html)
        if soup is not None:  # If we have soup - parse and write to our csv file
            self. get_data(soup)
            FeatureFileBuilder().build_feature_from_list_review(self.comment)
        self.browser.close()


class NLP:
    def __init__(self, text):
        self.text = text
        self.stop_word, self.left_word = self.set_stopword_and_leftword()

    # Tách từ
    def segmentation(self):
        tokens = word_tokenize(self.text)
        return tokens

    # Đọc stopword, leftword, reverse
    def set_stopword_and_leftword(self):
        stop_word = FileReader().read_stop_word(setting.DIR_PATH_DATA + "/stop_word.txt")
        left_word = FileReader().read_left_word(setting.DIR_PATH_DATA + "/left_word.txt")
        return stop_word, left_word

    def remove_negative_word(self):
        tokens = self.segmentation()
        for i in range(len(tokens)-1):
            if tokens[i] in ["n't", "not", "no"]:
                if tokens[i+1] is not None and tokens[i+1] in self.left_word:
                    tokens[i] = ","
                    tokens[i+1] = self.left_word.get(tokens[i + 1])
                    i += 1
        return tokens

    # Xóa các ký tự đặc biệt và viết thường tất cả các chữ
    def split_words(self):
        tokens = self.remove_negative_word()
        try:
            return [x.strip(setting.SPECIAL_CHARACTER).lower()
                    for x in tokens if len(x.strip(setting.SPECIAL_CHARACTER)) > 0]
        except TypeError:
            return []

    # Loại bỏ stopword
    def get_words_feature(self):
        tokens = self.split_words()
        return [word for word in tokens if word not in self.stop_word]


class FeatureFileBuilder:
    # Đọc number file trong folder cho trước, biểu thị dưới dạng BoW
    def build_feature_from_list_review(self, list_review):
        feature = np.array([]).astype(int)
        count = 0
        for review in list_review:
            list_word = NLP(review).get_words_feature()
            print(list_word)
            feature = np.append(feature, self.__build_feature_from_file(list_word, count))
            count += 1
        return feature.reshape(-1, 3)

    """
    Đếm số lần xuất hiện của các từ, sử dụng pp BoW
    Đối với lần lượt từng từ trong list_word, kiểm tra xem có trong dictionary không
    Nếu không có trong dictionary thì không được gán index
    Nếu có trong dictionary, kiểm tra xem đã xuất hiện trong bow không. Sau có cập nhật từ đó trong BoW
    """
    def __build_feature_from_file(self, list_word, count):
        bow = {}
        for word in list_word:
            index_dict = dictionary.get(word)
            if (index_dict is None): continue
            if index_dict in bow:
                bow[index_dict] = bow.get(index_dict) + 1
            else: bow[index_dict] = 1

        # Lưu tệp sau khi đã mã hóa vào 1 numpy
        S = np.array([]).astype(int)
        for word in bow:
            S = np.append(S, np.array([count, word, bow.get(word)]))
        return S


class FileReader():
    def read_stop_word(self, file_path):
        with open(file_path, mode='r') as f:
            stopwords = set([w.strip() for w in f.readlines()])
        return stopwords

    def read_left_word(self, filePath):
        left_word = {}
        with open(filePath, mode='r') as f:
            for line in f.readlines():
                x = line.strip("\n").split(" ")
                left_word[x[0]] = x[1]
        return left_word

    def read_dictionary(self, filePath):
        count = 0
        dictionary_ = {}
        # Mỗi từ trong từ điển, loại bỏ ký từ \n ở cuối
        with open(file=filePath, mode='r') as f:
            for word in f.readlines():
                dictionary_[''.join(word.split('\n'))] = count
                count += 1
        return dictionary_

dictionary = FileReader().read_dictionary(setting.DIR_PATH_DATA + "/dictionary.txt")