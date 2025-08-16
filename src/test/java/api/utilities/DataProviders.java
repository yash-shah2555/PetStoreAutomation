package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException{
		
		String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rownm = xl.getRowCount("Sheet1");
		int colcount = xl.getCellCount("Sheet1", 1);
		
		String apidata[][] = new String[rownm][colcount];
		
		for(int i=1;i<=rownm;i++) {
			
			for(int j=0;j<colcount;j++) {
				
				apidata[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		
		return apidata;
		
	}
	
	@DataProvider(name = "UserNames")	
	public String[] getUserNames() throws IOException {
		String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rownm = xl.getRowCount("Sheet1");
		
		String apidata[] = new String[rownm];
		
		for(int i=1;i<=rownm;i++) {
			apidata[i-1] = xl.getCellData("Sheet1",i,1);
		}
		
		return apidata;
	}
	
	@DataProvider(name = "StoreData")
	public String[][] getStoreData() throws IOException{
		
		String path = System.getProperty("user.dir")+"//testData//StoreData.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rowCount = xl.getRowCount("Sheet1");
		int colCount = xl.getCellCount("Sheet1", 1);
		
		 String storeData[][] = new String[rowCount][colCount];
		 
		 for(int i=1;i<=rowCount;i++) {
			 
			 for(int j=0;j<colCount;j++) {
				 storeData[i - 1][j] = xl.getCellData("Sheet1", i, j);
			 }
		 }
		 
		 return storeData;
	}
	
	@DataProvider(name = "PetData")
	public String[][] getPetData() throws IOException{
		
		String path = System.getProperty("user.dir")+"//testData//PetData.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rowCount = xl.getRowCount("Sheet1");
		int colCount = xl.getCellCount("Sheet1", 1);
		
		String petData[][] = new String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) {
			
			for(int j=0;j<colCount;j++) {
				petData[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		
		return petData;
	}
}
