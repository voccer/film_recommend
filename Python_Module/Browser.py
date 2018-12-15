from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from bs4 import BeautifulSoup

"""
Cài đặt driver chorme và không cho hiển thị window chrome khi chạy
@:return : driver Chrome
"""
def get_driver():
    chrome_options = Options()
<<<<<<< HEAD:Python_Module/browser.py
    #chrome_options.add_argument("--headless")
    #chrome_options.add_argument("--window-size=%s" % "1920,1080")
    browser = webdriver.Chrome(options=chrome_options)
=======
    # chrome_options.add_argument("--headless")
    # chrome_options.add_argument("--window-size=%s" % "1920,1080")
    browser = webdriver.Chrome(chrome_options=chrome_options)
>>>>>>> fd7c61a5a83611d6a5f8857d1257eec8e84a13ce:Python_Module/Browser.py
    return browser

"""
Sử dụng beautifulsoup4 để lấy mã ở dạng lxml 
@:return soup lưu mã nguồn dạng lxml 
"""
def get_soup(html):
    if html is not None:
        soup = BeautifulSoup(html, 'lxml')
        return soup
    else:
        return