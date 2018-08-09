from googleapiclient.discovery import build
from httplib2 import Http
from oauth2client import file, client, tools
from pandas import DataFrame

class Gsheet:
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
