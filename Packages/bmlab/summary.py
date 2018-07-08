import re
from numpy import *
from pandas import read_csv

df = read_csv("./Databank/TADSubjListAllbackup.csv", header = 0, index_col = 0)
df = df[[bool(re.search("TADZ0", s)) if s is not nan else False for s in df.index]]
df = df[df["Excluded"].isnull()]
assert mean(df["Age"]) == 24.509672619048217
assert std(df["Age"]) == 2.4844655695774711
assert sum(df["Gender"] == 0) == 29
