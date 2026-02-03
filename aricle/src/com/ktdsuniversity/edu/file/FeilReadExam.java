package com.ktdsuniversity.edu.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;

/**
 * 옛날 자바 버전
 */
public class FeilReadExam {
	public static void main(String[] args) {
		// getenv : 환경 변수
		// 운영체제의 정보를 취득
		Properties props = System.getProperties();
		String homePath = props.get("user.home").toString();
		System.out.println(homePath);

//		System.out.println(props);
//		System.out.println();
//		System.out.println(props.get("user.home"));
//
//		Map<String, String> env = System.getenv();
//		System.out.println(env);
//		System.out.println(env.get("HOME"));

		// C:\Java Exam 폴더의 정보를 추출
		// File.separator : 윈도우는 "\\Java Exam" 로 해주고 맥은 "/Java Exam" 로 알아서 해준다
		File file = new File(homePath + File.separator + "Java Exam");

		// 1. 폴더의 이름 출력.
		String directoryName = file.getName();
		System.out.println(directoryName);

		// 2. 이 경로가 가리키는 것이 파일인지 폴더인지를 구분.
		boolean isFile = file.isFile();
		// isFile 의 값이 true 이면 파일 이다. false 이면 파일이 아니다.
		System.out.println(isFile);

		boolean isDirectory = file.isDirectory();
		// isDirectory 의 값이 true 이면 폴더 이다. false 이면 폴더가 아니다.
		System.out.println(isDirectory);

		// 3. 이 경로가 실제 존재하는 것인지.
		boolean exists = file.exists();
		// exists 의 값이 true 이면 존재하는 폴더, false 이면 존재하지 않는 폴더.
		System.out.println(exists);

		// 4. 이 경로의 크기(폴더의 크기) 출력.
		// 파일의 크기는 byte
		// int 로는 표현할 수 없다.
		long bytes = file.length();
		System.out.println(bytes + " byte");

		// C:\Java Exam\Java Exam.txt 파일의 정보를 추출.
		// homePath + File.separator + "Java Exam", "Java Exam.txt" : 앞에 있는 폴더에서 이 파일을
		// 가져와라.
		File textFile = new File(homePath + File.separator + "Java Exam", "Java Exam.txt");
		// 1. 파일의 이름을 출력
		String textFileName = textFile.getName();
		System.out.println(textFileName + "\n");

		// 2. 폴더인지 파일인지 구분
		boolean isTextFile = textFile.isFile();
		System.out.println(isTextFile + "\n");

		// 3. 이 파일이 실제 존재하는 것인지
		boolean isTextFileDirectory = textFile.isDirectory();
		System.out.println(isTextFileDirectory);

		// 4. 파일의 크기를 출력
		boolean existsTextFile = textFile.exists();
		System.out.println(existsTextFile);

		// 5. 이 파일이 있는 부모의 경로를 출력
		// - 첫 번째 방법
		String parentPath = textFile.getParent(); // 이 파일이 위치한 폴더의 경로
		System.out.println(parentPath);

		// - 두 번째 방법
		File parentFile = textFile.getParentFile(); // 파일의 객체를 돌려주게 되어 있다.
		System.out.println(parentFile);

		// 6. 이 파일의 경로를 출력한다.
		String textFilePath = textFile.getAbsolutePath(); // 경로를 가져와라
		System.out.println(textFilePath);

//		readAndPrintFileDescriptionUseIO(homePath + File.separator + "Java Exam", "Java Exam.txt"); // 옛날 버전 File input
		readAndPrintFileDescriptionUseNIO(homePath + File.separator + "Java Exam", "Java Exam.txt"); // 요즘 버전 File input

	}

	// 메모리를 많이 사용한다는 단점을 가지고 있다.
	// 요즘의 File input 방법
	public static void readAndPrintFileDescriptionUseNIO(String parentPath, String file) {
		// 1. 읽으려는 파일을 특정
		File target = new File(parentPath, file);

		// 2. 파일의 내용을 읽는다.
		// 3. 파일의 내용을 List 에 할당
		try {
			List<String> lines = Files.readAllLines(target.toPath());
			// 4. 파일의 내용을 출력
			for (String line : lines) {
				System.out.println(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 파일 읽기 : File input
	public static void readAndPrintFileDescriptionUseIO(String parentPath, String file) {
		// 1. 읽으려는 파일 특정
		File target = new File(parentPath, file);

		// 2. 파일이 존재하는지 확인
		// 3. 읽으려는 대상이 진짜 파일이 맞는지 확인
		if (target.exists() && target.isFile()) {
			// 4. 파일의 내영을 읽기 시작
			// 4-1. 파일의 바이트를 ChunKing 해서 가져온다. / ChunKing : - Chunk = 덩어리, 데이터를 일정한 크기의 블록으로
			// 잘라서 처리하는 것.
			FileReader fileReader = null;
			BufferedReader bufferedReader = null;
			try {
				fileReader = new FileReader(target);
				// 4-2. 파일의 내용을 String 으로 반환
				// fileReader : 이것을 넣어도 오류가 안나는 이유는 Reader 상속 받은 관계이기 때문이다.
				bufferedReader = new BufferedReader(fileReader);

				String line = null;
				while (true) {
					line = bufferedReader.readLine();

					// EOF까지 갔느냐?
					if (line == null) {
						break;
					} else {
						// 4-3. 내용을 출력
						System.out.println(line);
					}
				}
			} catch (IOException ioe) {
				// 내가 읽을려는 파일이 존제하지 않을때
				ioe.printStackTrace();

			} finally {
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						// 우리가 할 수 있는게 없어서
					} // pipe 해제.

				}
				if (fileReader != null) {
					try {
						fileReader.close();
					} catch (IOException e) {

					} // pipe 해제

				}
			}

		}

	}

}
