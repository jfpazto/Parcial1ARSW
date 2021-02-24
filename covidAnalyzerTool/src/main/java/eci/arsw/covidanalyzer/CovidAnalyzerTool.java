package eci.arsw.covidanalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Camel Application
 */
public class CovidAnalyzerTool {

	public static ResultAnalyzer resultAnalyzer;
	public static TestReader testReader;
	private int amountOfFilesTotal;
	public static AtomicInteger amountOfFilesProcessed;
	public static AtomicInteger activeThreads;
	public static Object monitor = new Object();
	public static boolean pause = false;
	public static AtomicInteger pausedThreads = new AtomicInteger(0);

	public CovidAnalyzerTool() {
		resultAnalyzer = new ResultAnalyzer();
		testReader = new TestReader();
		amountOfFilesProcessed = new AtomicInteger();
		activeThreads = new AtomicInteger();
	}

	public void processResultData(int numberOfThreads) {
		amountOfFilesProcessed.set(0);
		List<File> resultFiles = getResultFileList();
		amountOfFilesTotal = resultFiles.size();

		ResultThread[] hilos = new ResultThread[numberOfThreads];
		int start = 0;
		int end = 0;
		int step = amountOfFilesTotal / numberOfThreads;
		for (int i = 0; i < numberOfThreads; i++) {
			end = start + step;
			if (i == 0)
				end += amountOfFilesTotal % numberOfThreads;
			hilos[i] = new ResultThread(start, end, resultFiles);
			hilos[i].start();
			System.out.println(start + " " + end);
			start = end;
		}
		/*
		 * for (File resultFile : resultFiles) { List<Result> results =
		 * testReader.readResultsFromFile(resultFile); for (Result result : results) {
		 * resultAnalyzer.addResult(result); } amountOfFilesProcessed.incrementAndGet();
		 * }
		 */
	}

	private List<File> getResultFileList() {
		List<File> csvFiles = new ArrayList<>();
		try (Stream<Path> csvFilePaths = Files.walk(Paths.get("src/main/resources/"))
				.filter(path -> path.getFileName().toString().endsWith(".csv"))) {
			csvFiles = csvFilePaths.map(Path::toFile).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return csvFiles;
	}

	public Set<Result> getPositivePeople() {
		return resultAnalyzer.listOfPositivePeople();
	}

	/**
	 * A main() so we can easily run these routing rules in our IDE
	 */
	public static void main(String... args) throws Exception {
		CovidAnalyzerTool covidAnalyzerTool = new CovidAnalyzerTool();
		int numberOfThreads = 5;
		activeThreads.set(numberOfThreads);
		covidAnalyzerTool.processResultData(numberOfThreads);
		while (numberOfThreads > 0) {
			Scanner scanner = new Scanner(System.in);
			String line = scanner.nextLine();
			if (pause) {
				pause = false;
				System.out.println("Running");
				pausedThreads.set(10);
				synchronized (monitor) {
					monitor.notifyAll();
				}
			} else {
				System.out.println("paused");
				pause = true;
				while (activeThreads.get() != pausedThreads.get()) {
					Thread.sleep(0,01);
				}
				System.out.println("Found " + covidAnalyzerTool.getPositivePeople().size() + " suspect accounts");
			}
			if (line.contains("exit"))
				break;
		}
        String message = "Processed %d out of %d files.\nFound %d positive people:\n%s";
        Set<Result> positivePeople = covidAnalyzerTool.getPositivePeople();
        String affectedPeople = positivePeople.stream().map(Result::toString).reduce("", (s1, s2) -> s1 + "\n" + s2);
        message = String.format(message, covidAnalyzerTool.amountOfFilesProcessed.get(), covidAnalyzerTool.amountOfFilesTotal, positivePeople.size(), affectedPeople);
        System.out.println(message);

	}

}
