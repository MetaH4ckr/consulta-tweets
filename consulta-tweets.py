from twython import Twython
from twython import TwythonStreamer
from pymongo import MongoClient
from datetime import datetime

#import sys
#import os
#import calendar
#import time
#import datetime

#replace with your own keys
APP_KEY = 'APP_KEY'
APP_SECRET = 'APP_SECRET'
ACCESS_TOKEN = 'ACCESS_TOKEN'
ACCESS_TOKEN_STREAMING = 'ACCESS_TOKEN_STREAMING'
ACCESS_SECRET = 'ACCESS_SECRET'

dbclient = MongoClient('localhost', 27017)
db = dbclient['consulta-tweets']
tweets = db['tweets']
    
def get_posts(hashtag):
    twitter = Twython(APP_KEY, access_token = ACCESS_TOKEN)
    max_id = None
    results = twitter.search(q=hashtag, count=100)
    metadata = results['search_metadata']
    statuses = results['statuses']
    for post in statuses:
        print(post)
        print("")
        print("Gravando no MongoDB...")
        tweet = post
        tweet['searchedtag'] = hashtag
        tweet['created_at_dt'] = datetime.strptime(str(tweet['created_at']), '%a %b %d %H:%M:%S %z %Y')
        tweet_id = tweets.insert_one(tweet).inserted_id
        print("Gravou! id: " + str(tweet_id))
        print("")

def clean_collection(collectionName):
    collection = db[collectionName]
    collection.delete_many({})

class MyStreamer(TwythonStreamer):
    def on_success(self, data):
        if 'text' in data:
            print(data['text'])

    def on_error(self, status_code, data):
        print(status_code)

#results = twitter.cursor(twitter.search, q=hashtag, count=2)
#for result in results:
#    print(result)
# stream = MyStreamer(APP_KEY, APP_SECRET, ACCESS_TOKEN_STREAMING, ACCESS_SECRET)
# stream.statuses.filter(track='bolsonaro')

tags = ['#openbanking', '#apifirst', '#devops', '#cloudfirst', '#microservices', '#apigateway', '#oauth', '#swagger', '#raml', '#openapis']

clean_collection('tweets')

for tag in tags:
    get_posts(tag)






















