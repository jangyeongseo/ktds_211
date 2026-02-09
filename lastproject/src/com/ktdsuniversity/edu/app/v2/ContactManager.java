package com.ktdsuniversity.edu.app.v2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.ktdsuniversity.edu.app.v1.Contact;

public class ContactManager {

	private List<Contact> contactList;

	public ContactManager() {
		this.contactList = new ArrayList<>();
	}

	// 1. contactList에 Contact 인스턴스를 추가하는 기능.
	public void addContact(Contact contact) {

	}

	// 2. contactList의 모든 연락처 정보를 출력하는 기능.
	public void printAllContacts() {

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
		this.contactList.removeIf(predicate);
	}

	public static void main(String[] args) {

	}
}