import requests
import shutil
import pandas as pd
import Setting as setting
import get_comment
import os
import Browser

class SeleniumCrawler():
    def __init__(self, film_list, exclusion_list = None):
        self.film_list = film_list
        self.exclusion_list = exclusion_list
        self.data_frame = pd.DataFrame(columns = ['LinkFilm', 'NameFilm','Start','Date','Description',
                                        'LinkImage','TotalComment','PositiveComment'])
        self.browser = Browser.get_driver()
        self.output_image = os.path.dirname(os.path.realpath(__file__)) + "/Data/image"

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
        try:
            star = soup.find("div", class_="ratingValue").find("span", attrs = {"itemprop" : "ratingValue"}).get_text().replace("\n"," ")
        except(Exception):
            star = ""
        date = soup.find("div", class_="subtext").find("a", title="See more release dates").get_text().replace("\n"," ")
        description = (soup.find("div", class_="summary_text").get_text().strip().replace("\n"," "))
        try:
            linkImage = soup.find("div", class_="poster").find("img").get("src")
        except(Exception):
            linkImage = ""
        return [nameFilm, star, date, description, linkImage]

    def run_crawler(self):
        for index, link in enumerate(self.film_list):
            html = self.get_page(link)
            soup = Browser.get_soup(html)
            if soup is not None:  # If we have soup - parse and write to our csv file
                table = [link] + self.get_data(soup) + ['1', '1']
                get_comment.GetComment().run_crawler(link + "reviews")
                print("Lay phim thu %d" %index)
                self.data_frame.loc[index] = table

        self.data_frame.to_csv(setting.DIR_PATH_DATA + "/file.csv", index=False)
        self.browser.close()