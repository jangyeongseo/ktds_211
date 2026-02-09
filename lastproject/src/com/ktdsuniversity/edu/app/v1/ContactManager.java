package com.ktdsuniversity.edu.app.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManager {
	private List<Contact> contactList;
	private Scanner scanner;

	public ContactManager() {
		this.contactList = new ArrayList<>();
		this.scanner = new Scanner(System.in);
	}

	// 모든 연락처 목록
	public void printAllContacts() {
		if (contactList.isEmpty()) {
			// isEmpty : 문자열의 길이가 0인 경우에, true
			System.out.println("등록된 연락처가 없습니다.");
			System.out.println("=".repeat(28) + "\n");
			return;
		}

		for (int i = 0; i < contactList.size(); i++) {
			System.out.println("[" + i + "]");
			System.out.println(contactList.get(i));
		}
		System.out.println("=".repeat(28) + "\n");
	}

	// 연락처 추가
	public void addContact() {
		// Contact
		Contact contact = new Contact();
		System.out.print("이름 : ");
		contact.setName(scanner.nextLine());
		System.out.print("성 : ");
		contact.setLastName(scanner.nextLine());
		System.out.print("이름 : ");
		contact.setFirstName(scanner.nextLine());
		System.out.print("닉네임 : ");
		contact.setNickname(scanner.nextLine());
		System.out.print("이메일 : ");
		contact.setEmail(scanner.nextLine());

		// phones
		System.out.print("\n연락처(010-2456-2456) : ");
		String phoneNumber = scanner.nextLine();
		Phone phone = new Phone(Phone.Type.PERSONAL, phoneNumber);
		contact.getPhones().add(phone); // 폰 정보 추가

		// company
		System.out.print("\n회사 이름 : ");
		String companyName = scanner.nextLine();
		System.out.print("직급 : ");
		String jop = scanner.nextLine();
		System.out.print("회사 주소 :");
		String address = scanner.nextLine();
		Company company = new Company(companyName, jop, address);
		contact.getCompany().add(company);

		// 메모 작성 - 50자 내외인데 이상 작성이 됨.
		System.out.print("\n메모(50자 내외) : ");
		String memo;
		do {
			memo = scanner.nextLine();
			if (memo.length() > 50) {
				System.out.println("메모는 50자 이내로 작성해주세요.");
			}
		} while (memo.length() > 50);

		contact.setMeno(memo);
		contactList.add(contact);
		System.out.println("연락처에 추가되었습니다.\n");

	}

	// 연락처 수정
	public void updateContact() {
		if (contactList.isEmpty()) {
			// isEmpty : 문자열의 길이가 0인 경우에, true
			System.out.println("수정할 연락처가 없습니다.");
			System.out.println("=".repeat(28) + "\n");
			return;
		}

		printAllContacts();

		// 여기부분 이상함 - 수정해야할듯
		System.out.print("수정할 연락처 번호 선택 : ");
		int index = Integer.parseInt(scanner.nextLine());

		// 사이즈 기준으로
		if (index < 0 || index >= contactList.size()) {
			System.out.println("잘못된 번호입니다.");
			System.out.println("=".repeat(28) + "\n");
			return;
		}

		Contact contact = contactList.get(index); // 갑 읽기
		System.out.println("1. 연락처");
		System.out.println("2. 이름");
		System.out.println("3. 이메일");
		System.out.println("4. 회사 주소");
		System.out.println("5. 메모");
		System.out.print("번호 선택 : ");
		int choice = Integer.parseInt(scanner.nextLine());

		if (choice < 1 || choice > 5) {
			System.out.println("\n잘못된 번호입니다.\n");
			return;
		}

		switch (choice) {
		case 1:
			System.out.print("새 전화번호 입력 : ");
			String newPhone = scanner.nextLine();
			if (!contact.getPhones().isEmpty()) {
				contact.getPhones().get(0).setPhoneNumber(newPhone);
			} else {
				contact.getPhones().add(new Phone(Phone.Type.PERSONAL, newPhone));
			}
			System.out.println("전화번호가 수정되었습니다.");
			break;

		case 2:
			System.out.print("새 이름 입력 : ");
			contact.setName(scanner.nextLine());
			System.out.println("이름이 수정되었습니다.");
			break;

		case 3:
			System.out.print("새 이메일 입력 : ");
			contact.setEmail(scanner.nextLine());
			System.out.println("이메일이 수정되었습니다.");
			break;

		case 4:
			System.out.print("새 회사 이름 입력 : ");
			String companyName = scanner.nextLine();
			System.out.print("새 직급 입력 : ");
			String job = scanner.nextLine();
			System.out.print("새 회사 주소 입력 : ");
			String address = scanner.nextLine();
			contact.getCompany().clear();
			contact.getCompany().add(new Company(companyName, job, address));
			System.out.println("회사 정보가 수정되었습니다.");
			break;

		case 5:
			System.out.print("새 메모 입력 (50자 이내) : ");
			String memo;
			do {
				memo = scanner.nextLine();
				if (memo.length() > 50) {
					System.out.println("메모는 50자 이내로 작성해주세요.");
				}
			} while (memo.length() > 50);
			contact.setMeno(memo);
			System.out.println("메모가 수정되었습니다.");
			break;

		default:
			System.out.println("잘못된 번호입니다.");
			return;
		}

		// 수정된 연락처를 리스트에 반영
		contactList.set(index, contact);
		System.out.println("연락처 수정이 완료되었습니다.\n");
	}

	// 연락처 삭제
	public void deleteContact() {
		if (contactList.isEmpty()) {
			// isEmpty : 문자열의 길이가 0인 경우에, true
			System.out.println("삭제할 연락처가 없습니다.");
			System.out.println("=".repeat(28) + "\n");
			return;
		}

		printAllContacts();

		System.out.print("삭제할 연락처 번호 선택 : ");
		int index = Integer.parseInt(scanner.nextLine());

		if (index < 0 || index >= contactList.size()) {
			System.out.println("잘못된 번호입니다.");
			System.out.println("=".repeat(28) + "\n");
			return;
		}

		System.out.print("정말 삭제하시겠습니까? (y/n) : ");
		String result = scanner.nextLine().toLowerCase();
		if (result.equals("y")) {
			Contact remove = contactList.remove(index);
			System.out.println(remove.toString() + "\n삭제가 완료되었습니다.");
		}
		return;
	}

	// 연락처 검색
	public void search() {
		if (contactList.isEmpty()) {
			System.out.println("검색할 연락처가 없습니다.");
			System.out.println("=".repeat(28) + "\n");
			return;
		}

		System.out.println("검색어 입력 : ");
		String keyword = scanner.nextLine();

		boolean found = false;
		for (Contact contact : contactList) {
			if (contact.getName().contains(keyword) || contact.getFirstName().contains(keyword)
					|| contact.getLastName().contains(keyword) || contact.getNickname().contains(keyword)
					|| contact.getEmail().contains(keyword)
					|| contact.getCompany().stream().anyMatch(c -> c.getCompanyName().contains(keyword))
					|| contact.getPhones().stream().anyMatch(p -> p.getPhoneNumber().contains(keyword))) {

				System.out.println(contact);
				found = true;
			}
		}

	}

	// 주소록 애플리케이션
	public void run() {
		System.out.println("주소록 애플리케이션");

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
				printAllContacts();

			} else if (menu == 2) {
				System.out.println("========= 주소록 추가 =========\n");
				addContact();

			} else if (menu == 3) {
				System.out.println("========= 주소록 수정 =========\n");
				updateContact();

			} else if (menu == 4) {
				System.out.println("========= 주소록 삭제 =========\n");
				deleteContact();

			} else if (menu == 5) {
				System.out.println("========= 주소록 검색 =========\n");
				deleteContact();

			} else {
				System.out.println("\n주소록 애플리케이션을 종료합니다.");
				break;
			}

		}
	}

	// 1. contactList 에 contact 인스턴스를 추가하는 기능.
	// 2. contactList 의 모든 연락처 정보를 출력하는 기능
	// 3. contactList 에서 전화번호 검색 결과 출력하는 기능
	// 예> 010 전화번호에 010이 포함된 연락처의 모든 정보를 출력
	// 4. contactList 에서 이름 겸색 결과 출력하는 기능
	// 예> "김"을 파라미터로 전달하면 name, firstName, lastName, companyName에 "김"이 포함된 연락처의 모든
	// 정보를 출력
	// 5. contactList 에서 이메일 검색 결과 출력하는 기능
	// 예> "@adc.com" 을 파라미터로 전달하면 이메일에 "@adc.com" 이 포함된 연락처의 모든 정보를 출력
	// 6. 연락처 정보 수정 가능
	// 예> 다양한 겸색의 결과중 하나를 선택해 연락처 정보를 수정할 수 있는 기능.
	// 이름을 변경, 전화번호 추가, 전화번호 수정, 회사 정보 수정...
	// 7. 연락처 정보 삭제 기능
	// 예> 다양한 검색의 결과 중 하나를 선택해 contactList 에 제거하는 기능

	public static void main(String[] args) {
		ContactManager manager = new ContactManager();
		manager.run();

	}
}
