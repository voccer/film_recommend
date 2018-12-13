from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.actions.action_builder import ActionBuilder
from selenium.webdriver.common.keys import Keys
import time

if __name__ == '__main__':
    browser = webdriver.Chrome()
    browser.get("https://www.imdb.com/title/tt5523010/reviews?ref_=tt_ov_rt")
    wait = WebDriverWait(browser,10)
    # action = action_chains.ActionChains(browser)
    # action.send_keys(keys.Keys.CONTROL+keys.Keys.SHIFT+"j")
    # assert 'NBA' in browser.page_source

    # action = action_chains.ActionChains(browser)
    #
    # action.send_keys(keys.Keys.CONTROL+keys.Keys.SHIFT+"j")
    # action.perform()
    # time.sleep(3)
    #
    # action.send_keys(keys.Keys.ENTER)
    #
    # action.send_keys("document.querySelectorAll('label.boxed')[1].click()" + keys.Keys.ENTER)
    # action.perform()
    # action = ActionChains(browser)

    i = 0
    while True:
        e1 = wait.until(EC.presence_of_element_located((By.XPATH,"//button[contains(text(),'Load More')]")))
        #e1.click()
        browser.execute_script("arguments[0].click();", e1)
        i += 1;
        print(i)
        time.sleep(1)
        # browser = WebDriverWait(browser, 30).\
        #     until(EC.pr#esence_of_element_located((By.XPATH, "//button[contains(text(),'Load More')]")))
        # browser.sleep(.1)
        # browser.click()
        # browser.perform()