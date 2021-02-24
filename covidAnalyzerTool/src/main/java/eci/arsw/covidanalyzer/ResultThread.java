package eci.arsw.covidanalyzer;

import java.io.File;
import java.util.List;

public class ResultThread extends Thread {
	
	int start;
	int end;
	List<File> resultFiles;
	public ResultThread(int start, int end, List<File> resultFiles) {
		this.start = start;
		this.end = end;
		this.resultFiles = resultFiles;
	}
	
	public void run(){
		for (int i=start;i<end;i++)
		{
			File resultFile=resultFiles.get(i);
			List<Result> results = CovidAnalyzerTool.testReader.readResultsFromFile(resultFile);
			for (Result res:results)
			{
				synchronized (CovidAnalyzerTool.monitor)
				{
					
					if (CovidAnalyzerTool.pause)
					{
						CovidAnalyzerTool.pausedThreads.incrementAndGet();
						
						try {
							CovidAnalyzerTool.monitor.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				CovidAnalyzerTool.resultAnalyzer.addResult(res);
			}
			CovidAnalyzerTool.amountOfFilesProcessed.incrementAndGet();
			
		}
		CovidAnalyzerTool.activeThreads.decrementAndGet();
	}

	
}
