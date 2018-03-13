#!/bin/sh

# Script that starts Local Selenium Grid

# Start Hub
docker run -d -p 4444:4444 --name local_selenium_hub selenium/hub
# Start Chrome Node
docker run -d -p 5901:5900 --name local_chrome_node --link local_selenium_hub:hub -v /dev/shm:/dev/shm selenium/node-chrome-debug
# Start Firefox Node
docker run -d -p 5902:5900 --name local_firefox_node --link local_selenium_hub:hub -v /dev/shm:/dev/shm selenium/node-firefox-debug
