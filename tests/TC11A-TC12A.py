# Create a post
# A user wants to create a post. 

# and

# Delete a post
# A user wants to delete a post.

# import all necessary libraries
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
import time

# constants
URL = "http://s4h.404.mn"
MOCK_USERNAME = "roman@mail.ru"
MOCK_PASSWORD = "12345678"

# create a driver
driver = webdriver.Safari()
driver.set_window_size(1500, 700)

# open login page
driver.get(f"{URL}/login")

# enter mock data
# find element by xpath
elem = driver.find_element(By.NAME, "username")
elem.send_keys(MOCK_USERNAME)
elem = driver.find_element(By.NAME, "password")
elem.send_keys(MOCK_PASSWORD)

# click button
elem = driver.find_element(By.XPATH, "//button[@type='submit']")
elem.click()
time.sleep(1)

# click on the "Create post" button
driver.get(f'{URL}/posts/new')
time.sleep(1)

def randomText():
    import random
    import string
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for i in range(10))

# generate random text
title = randomText()
body = randomText()

# find element by xpath
elem = driver.find_element(By.XPATH, "//*[@id='title']")
elem.send_keys(title)
elem = driver.find_element(By.XPATH, "//*[@id='content']")
elem.send_keys(body)
elem = driver.find_element(By.XPATH, "/html/body/section/div/div/form/div[3]/button")
elem.click()
time.sleep(1)

# if we see the title in the page source, then test passed
if title in driver.page_source:
    print("Create post TEST PASSED")
else:
    print('Create post TEST FAILED')
    time.sleep(10)
    driver.close()
    exit(0)

# find all article.media
articles = driver.find_elements(By.XPATH, "//article[@class='media']")

# find article.media with title variable
for article in articles:
    if title in article.text:
        # click 'a' tag in article
        article.find_element(By.TAG_NAME, "a").click()
        break

time.sleep(1)

# click "Delete" button
elem = driver.find_element(By.XPATH, "/html/body/section/div/div/div[2]/div/div[2]/a")
elem.click()

time.sleep(1)

# if we do not see the title in the page source, then test passed
if not title in driver.page_source:
    print("Delete post TEST PASSED")
else:
    print('Delete post TEST FAILED')
    
time.sleep(10)
driver.close()