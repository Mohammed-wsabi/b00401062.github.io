from googleapiclient.discovery import build
from httplib2 import Http
from oauth2client import file, client, tools
from numpy import NaN
from pandas import DataFrame

class Metadata:
	def __init__(self, sheet: str, range: str):
		self.scope = "https://www.googleapis.com/auth/spreadsheets.readonly"
		self.sheet = sheet
		self.range = range
	def read(self, header: int = 0, index_col: int = 0) -> DataFrame:
		store = file.Storage("./Documents/Personal/Token.json")
		creds = store.get()
		if not creds or creds.invalid:
			flow = client.flow_from_clientsecrets("./Documents/Personal/Credentials.json", self.scope)
			creds = tools.run_flow(flow, store)
		service = build("sheets", "v4", http = creds.authorize(Http()))
		result = service.spreadsheets().values().get(spreadsheetId = self.sheet, range = self.range).execute()
		values = result.get("values", [])
		return DataFrame(values, columns = values[header]).drop(header).set_index(values[header][index_col])
	def load(self, header: int = 0, index_col: int = 0) -> DataFrame:
		metadata = self.read(header, index_col)
		metadata.set_index("檢體編號", inplace = True)
		metadata.replace("", NaN, inplace = True)
		metadata.性別 = metadata.性別.astype("category")
		metadata.出生日期 = metadata.出生日期.astype("datetime64")
		metadata.癌症發生順序號碼 = metadata.癌症發生順序號碼.astype("category")
		metadata.診斷年齡 = metadata.診斷年齡.astype(int)
		metadata.本院最初診斷日 = metadata.本院最初診斷日.astype("datetime64")
		metadata.原發部位 = metadata.原發部位.astype("category")
		metadata.組織型態 = metadata.組織型態.astype("category")
		metadata.腫瘤大小 = metadata.腫瘤大小.astype(float)
		metadata.區域淋巴結侵犯數目 = metadata.區域淋巴結侵犯數目.astype(float)
		metadata.診斷性及分期性手術處置日期 = metadata.診斷性及分期性手術處置日期.astype("datetime64")
		metadata.臨床T = metadata.臨床T.astype("category")
		metadata.臨床N = metadata.臨床N.astype("category")
		metadata.臨床M = metadata.臨床M.astype("category")
		metadata.病理T = metadata.病理T.astype("category")
		metadata.病理N = metadata.病理N.astype("category")
		metadata.病理M = metadata.病理M.astype("category")
		metadata.病理切片中的腫瘤深度 = metadata.病理切片中的腫瘤深度.astype(float)
		metadata.病理報告中的腫瘤細胞與手術切緣的最近距離 = metadata.病理報告中的腫瘤細胞與手術切緣的最近距離.astype(float)
		metadata.首次治療日 = metadata.首次治療日.astype("datetime64")
		metadata.首次手術日期 = metadata.首次手術日期.astype("datetime64")
		metadata.放射治療開始日期 = metadata.放射治療開始日期.astype("datetime64")
		metadata.放射治療結束日期 = metadata.放射治療結束日期.astype("datetime64")
		metadata.最高放射劑量 = metadata.最高放射劑量.astype(float)
		metadata.最高放射劑量治療次數 = metadata.最高放射劑量治療次數.astype(float)
		metadata.化學治療開始日期 = metadata.化學治療開始日期.astype("datetime64")
		metadata.最後聯絡或死亡日期 = metadata.最後聯絡或死亡日期.astype("datetime64")
		metadata.生存狀態 = metadata.生存狀態.astype("category")
		metadata.身高 = metadata.身高.astype(float)
		metadata.體重 = metadata.體重.astype(float)
		return metadata
