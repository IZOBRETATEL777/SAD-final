# Send comment 
# A user wants to send a comment in a post. 

# import all necessary libraries
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
import time

# constants
URL = "http://s4h.404.mn"
MOCK_USERNAME = "roman@mail.ru"
MOCK_PASSWORD= "12345678"
MOCK_COMMENT = "This is a comment from Selenium"

# create a driver
driver = webdriver.Safari()
driver.set_window_size(1500, 700)

# open login page
driver.get(f"{URL}/login")
print("Login page is opened")

# enter mock data
# find element by xpath
elem = driver.find_element(By.NAME, "username")
elem.send_keys(MOCK_USERNAME)
elem = driver.find_element(By.NAME, "password")
elem.send_keys(MOCK_PASSWORD)
print("Mock data is entered")

# click button
elem = driver.find_element(By.XPATH, "//button[@type='submit']")
elem.click()
time.sleep(1)

# take the first <a> element content from /html/body/div[2]/div/div/div/article[1]
elem = driver.find_element(By.XPATH, "/html/body/div[2]/div/div/div/article[1]/div/div/p[1]/a")
elem.click()
time.sleep(1)

# find the comment field
elem = driver.find_element(By.XPATH, """//*[@id="content"]""")
elem.send_keys(MOCK_COMMENT)
print("Comment is entered")

# click on the "Post Comment" button
elem = driver.find_element(By.XPATH, "/html/body/div[2]/div[2]/div/article/div/form/div[2]/button")
elem.click()
time.sleep(1)
print("Post Comment button is clicked")

if MOCK_COMMENT in driver.page_source:
    print("POST COMMENT TEST PASSED")
else:
    print('POST COMMENT TEST FAILED')

time.sleep(15)