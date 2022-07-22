import requests
def backEnd(txt):
    txt=str(txt)
    url="https://api.dictionaryapi.dev/api/v2/entries/en/"+txt.lower()
    meaning = requests.get(url).json()
    return meaning[0]['meanings'][0]['definitions'][0]['definition']