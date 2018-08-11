from googleapiclient.discovery import build
from httplib2 import Http
from oauth2client import file, client, tools
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
		metadata = read(header, index_col)
		metadata.set_index("檢體編號", inplace = True)
		metadata.性別 = metadata.性別.astype("category")
		metadata.出生日期 = metadata.出生日期.astype("datetime64[ns]")
		metadata.本院最初診斷日 = metadata.本院最初診斷日.astype("datetime64[ns]")
		metadata.診斷性及分期性手術處置日期 = metadata.診斷性及分期性手術處置日期.astype("datetime64[ns]")
		metadata.首次治療日 = metadata.首次治療日.astype("datetime64[ns]")
		metadata.首次手術日期 = metadata.首次手術日期.astype("datetime64[ns]")
		metadata.放射治療開始日期 = metadata.放射治療開始日期.astype("datetime64[ns]")
		metadata.放射治療結束日期 = metadata.放射治療結束日期.astype("datetime64[ns]")
		metadata.化學治療開始日期 = metadata.化學治療開始日期.astype("datetime64[ns]")
		metadata.最後聯絡或死亡日期 = metadata.最後聯絡或死亡日期.astype("datetime64[ns]")
		metadata.生存狀態 = metadata.生存狀態.astype("category")
		return metadata
