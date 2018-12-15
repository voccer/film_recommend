from nltk.tokenize import word_tokenize
import Setting as setting
import numpy as np

def _read_stop_word(filePath):
    with open(filePath, mode='r') as f:
        stopwords = set([w.strip() for w in f.readlines()])
    return stopwords

def _read_left_word(filePath):
    left_word = {}
    with open(filePath, mode='r') as f:
        for line in f.readlines():
            x = line.strip("\n").split(" ")
            left_word[x[0]] = x[1]
    return left_word

class NLP:
    def __init__(self, text):
        self.text = text
        self.stopword, self.leftword= self.set_stopword_and_leftword()

    # Tách từ
    def segmentation(self):
        tokens = word_tokenize(self.text)
        return tokens

    # Đọc stopword, leftword, reverse
    def set_stopword_and_leftword(self):
        stop_word = _read_stop_word(setting.DIR_PATH_DATA + "/stop_word.txt")
        left_word = _read_left_word(setting.DIR_PATH_DATA + "/left_word.txt")
        return stop_word, left_word

    def remove_negative_word(self):
        tokens = self.segmentation()
        for i in range(len(tokens)-1):
            if tokens[i] in ["n't","not","no"]:
                if tokens[i+1] != None and tokens[i+1] in self.leftword:
                    tokens[i] = ","
                    tokens[i+1] = self.leftword.get(tokens[i+1])
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
        return [word for word in tokens if word not in self.stopword]