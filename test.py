import requests
import json

APIKEY="16alphanumapikey"
host=None
port=None

class UnauthorizedError(Exception): pass
class NotFoundError(Exception): pass


def apiCall(params, action, json):
    params["apiKey"] = APIKEY

    headers = {
        "Accept": "application/%s" % (json and "json" or "xml")
    }

    resp = requests.get("http://%s:%d/tlapi/api/%s" % (host, port, action), params=params, headers=headers)
    if resp.status_code == 200: return resp.text
    elif resp.status_code == 401: raise UnauthorizedError()
    elif resp.status_code == 404: raise NotFoundError()
    else: raise Exception("Error interacting with tlapi. Status=%d" % resp.status_code)

def getInfo(user, json=True):
    return apiCall({"user": user, "includeFollowing": "true", "includeFollowers": "true"}, "info", json)

def getTweets(user, filterKeyword=None, json=True):
    params = { "user": user }
    if filterKeyword: params["filter"] = filterKeyword

    return apiCall(params, "tweets", json)

def follow(user, targetUser):
    params = { "user": user, "followUser": targetUser }
    apiCall(params, "follow", True)

def unfollow(user, targetUser):
    params = { "user": user, "followedUser": targetUser }
    apiCall(params, "unfollow", True)


def cleanBeforeTest():
    # Remove all follows from user nivox to make sure the demo flow works
    u = json.loads(getInfo("nivox"))
    following = u.get("following", [])
    if following == None: following = []
    for followed in following:
        unfollow("nivox", followed)


def prettyPrintJson(data, out):
    r = json.loads(data)
    json.dump(r, out, indent=4)
    out.write("\n")

if __name__ == "__main__":
    import sys
    if len(sys.argv) != 3:
        print("Usage: %s <host> <port>" % sys.argv[0])
        sys.exit(2)

    host = sys.argv[1]
    port = int(sys.argv[2])
    cleanBeforeTest()

    print("====================\nINITIAL STATE\n====================")
    for user in ["nivox", "thousandeyes"]:
        print("Getting user info for: %s" % user)
        prettyPrintJson(getInfo(user), sys.stdout)
        print

    print("---------------------------------------------------------")
    print("Nivox start following thousandeyes")
    print("---------------------------------------------------------")
    follow("nivox", "thousandeyes")

    print("\n====================\nAFTER STATE\n====================")
    for user in ["nivox", "thousandeyes"]:
        print("Getting user info for: %s" % user)
        prettyPrintJson(getInfo(user), sys.stdout)
        print

    print("====================\nTWEETS\n====================")

    print("Getting all the tweets for user: nivox")
    prettyPrintJson(getTweets("nivox"), sys.stdout)

    print("\nGetting tweets containing 'Internet' for user: nivox")
    prettyPrintJson(getTweets("nivox", filterKeyword="Internet"), sys.stdout)

    print("\nGetting tweets containing 'black' for user: nivox")
    prettyPrintJson(getTweets("nivox", filterKeyword="black"), sys.stdout)


    print("\n---------------------------------------------------------")
    print("Nivox stop following thousandeyes")
    print("---------------------------------------------------------")
    unfollow("nivox", "thousandeyes")

    print("\n====================\nTWEETS\n====================")

    print("Getting all the tweets for user: nivox")
    prettyPrintJson(getTweets("nivox"), sys.stdout)


    print("\n====================\nXML\n====================")
    print("Getting all the tweets in XML for user: nivox")
    print(getTweets("nivox", json=False))

    print("\n====================\nAPIKEY\n====================")
    print("Silly me, I entered the wrong apikey: boom!")
    APIKEY="wrong"
    prettyPrintJson(getTweets("nivox"), sys.stdout)
