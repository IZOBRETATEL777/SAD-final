# Logout
# A user is already logged in and wants to logout from the forum. 

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
print("Login button is clicked")

# click on the "Logout" button
elem = driver.find_element(By.XPATH, """//*[@id="navbarBasicExample"]/div[2]/div/div/div/a[2]""")
elem.click() 
print("Logout button is clicked")

# assert that we have bye in html page
if "Bye" in driver.page_source:
    print("LOGOUT TEST PASSED")
else:
    print('LOGIN TEST FAILED')

time.sleep(60)