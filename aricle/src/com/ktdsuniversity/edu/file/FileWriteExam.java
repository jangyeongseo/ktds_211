package com.ktdsuniversity.edu.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileWriteExam {
	// 요즘 File Output 버전
	public static void writeFileDescriptionUseNIO(File target, List<String> description) {
		try {
			Files.write(target.toPath(), description);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 옛날 File Output 버전
	public static void writeFileDescriptionUseIO(File target, List<String> description) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			// 파일 쓰기 위한 Pipe 생성
			fileWriter = new FileWriter(target);
			bufferedWriter = new BufferedWriter(fileWriter);

			// 파일 쓰기
			for (String line : description) {
				bufferedWriter.write(line + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Properties props = System.getProperties();
		String homePath = props.get("user.home").toString();

		File file = new File(homePath + File.separator + "Java Exam", "Files test.txt");

		List<String> description = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			description.add("난수 입니다. ==>" + Math.random() * 10_000);
		}

//		writeFileDescriptionUseIO(file, description);
		writeFileDescriptionUseNIO(file, description);

	}
}
