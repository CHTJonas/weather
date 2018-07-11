#!/usr/bin/python
import feedparser
import MySQLdb
import xml.etree.ElementTree as ET
import time

url = "http://www.cl.cam.ac.uk/research/dtg/weather/rss.xml"
MySQLconn = MySQLdb.connect(
  host= "127.0.0.1",
  user="weather",
  passwd="password",
  db="weather"
)
cursor = MySQLconn.cursor()

while(not time.sleep(15)):
  try:
    d = feedparser.parse(url)
    html = d['entries'][0]['summary']

    # remove any units
    html = html.replace(" C", "")
    html = html.replace(" %", "")
    html = html.replace(" deg", "")
    html = html.replace(" secs" ,"")
    html = html.replace(" knots" ,"")
    html = html.replace(" mm/1000" ,"")
    html = html.replace(" mBar" ,"")

    root = ET.fromstring(html)
    data = dict([child[0].text,child[1].text] for child in root)

    # form a string for the data to submit
    SQLstring = "'"
    SQLstring = SQLstring + data['Time'] + "',"
    SQLstring = SQLstring + data['Temp'] + ","
    SQLstring = SQLstring + data['Humidity'] + ","
    SQLstring = SQLstring + data['Dew point'] + ","
    SQLstring = SQLstring + data['Pressure'] + ","
    SQLstring = SQLstring + data['Wind speed'] + ","
    SQLstring = SQLstring + data['Wind direction'] + ","
    SQLstring = SQLstring + data['Sun since midnight'] + ","
    SQLstring = SQLstring + "0"
    #SQLstring = SQLstring + data['Rain since midnight']

    # submit the data to the server
    SQLquery = """INSERT INTO `cl-weather` VALUES (""" + SQLstring + """)"""
    cursor.execute(SQLquery)
    MySQLconn.commit()
  except Exception as e:
    # exception at this point is probably caused by duplicate PRIMARY KEY
    # which is okay since we want to avoid duplicate data
    MySQLconn.rollback()
    print e

# clean up
MySQLconn.close()
