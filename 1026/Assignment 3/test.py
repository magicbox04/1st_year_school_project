capitalList = []
uniList = []
tempList = []
capital = open("capitals.csv", "r", encoding='utf8')
rankUni = open("TopUni.csv", "r", encoding='utf8')
rankUni.readline()

for line in capital:
    print(line)
    line = line.strip()
    print(line)
    line = line.split(",")
    print(line)
    tempList.append(line)

print (tempList)
