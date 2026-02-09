package com.ktdsuniversity.edu.app.v2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.ktdsuniversity.edu.app.v1.Contact;

public class ContactManager {

	private List<Contact> contactList;
	private static Scanner scanner;

	public ContactManager() {
		this.contactList = new ArrayList<>();
		this.scanner = new Scanner(System.in);
	}

	// 1. contactList에 Contact 인스턴스를 추가하는 기능.
	public void addContact(Contact contact) {
		if (contactList.isEmpty()) {
			System.out.println("등록된 정보가 없습니다.");
			return;
		}

		System.out.println("전화번호 추가");
		this.contactList.add(contact);
	}

	// 2. contactList의 모든 연락처 정보를 출력하는 기능.
	public void printAllContacts() {
		if (contactList.isEmpty()) {
			System.out.println("등록된 정보가 없습니다.");
			return;
		}

		System.out.println("전화번호 목록");
		this.contactList.forEach(System.out::println);
	}

	// 3. contactList에서 전화번호 검색 결과 출력하는 기능
	// 예> 010을 파라미터로 전달하면 전화번호에 010이 포함된 연락처의 모든 정보를 출력.
	//
	// conctactList에서 이름 검색 결과 출력하는 기능.
	// 예> "김"을 파라미터로 전달하면 name, firstName, lastName에
	// "김"이 포함된 연락처의 모든 정보를 출력.
	//
	// contactList에서 이메일 검색 결과 출력하는 기능.
	// 예> "@abc.com"을 파라미터로 전달하면 이메일에 "@abc.com"이 포함된
	// 연락처의 모든 정보를 출력.
	public List<Contact> search(Predicate<Contact> predicate) {
		if (this.contactList == null) {
			return Collections.emptyList();
		}

		return this.contactList.stream().filter(predicate).collect(Collectors.toList());
	}

	// 5. 삭제하려는 연락처의 정보를 이용한 연락처 삭제 기능.
	// 예> 이름이 "tt"인 연락처는 삭제한다., 전화번호 마지막 자리가 7777 인 연락처는 삭제한다.
	public void deleteContact(Predicate<Contact> predicate) {
		if (contactList.isEmpty()) {
			System.out.println("삭제할 정보가 없습니다.");
			return;
		}

		this.contactList.removeIf(predicate);
	}

	public static void main(String[] args) {
		System.out.println("주소록 애플리게이켠2");
		ContactManager contact = new ContactManager();

		while (true) {
			System.out.println("1. 주소록 목록");
			System.out.println("2. 주소록 추가");
			System.out.println("3. 주소록 수정");
			System.out.println("4. 주소록 삭제");
			System.out.println("5. 주소록 검색");
			System.out.println("6. 종료");
			System.out.print("숫자 선택 : ");
			int menu = Integer.parseInt(scanner.nextLine());

			if (menu < 1 || menu > 6) {
				System.out.println("\n잘못된 번호입니다.\n");
				continue;
			}

			if (menu == 1) {
				System.out.println("========= 주소록 목록 =========\n");
				contact.printAllContacts();

			} else if (menu == 2) {
				System.out.println("========= 주소록 추가 =========\n");
				contact.addContact(null);

			} else if (menu == 3) {
				System.out.println("========= 주소록 수정 =========\n");

			} else if (menu == 4) {
				System.out.println("========= 주소록 삭제 =========\n");
				contact.deleteContact(null);

			} else if (menu == 5) {
				System.out.println("========= 주소록 검색 =========\n");
				contact.search(null);

			} else {
				System.out.println("\n주소록 애플리케이션을 종료합니다.");
				break;
			}
		}
	}
}