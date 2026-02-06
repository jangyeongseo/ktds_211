package com.ktdsuniversity.edu.fp.basic.stream.advanced;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.ktdsuniversity.edu.fp.basic.stream.advanced.utils.ArrayUtil;
import com.ktdsuniversity.edu.fp.basic.stream.advanced.utils.StringUtil;

public class City {
	private int id; // 0
	private String name; // 1
	private int stateId; // 2
	private String stateCode;// 3
	private String stateName;// 4
	private int countryId;// 5
	private String countryCode;// 6
	private String countryName;// 7
	private String latitude;// 8
	private String longitude;// 9
	private String _native;// 10
	private String type;// 11
	private int level;// 12
	private int parentId;// 13
	private int population;// 14
	private String timezone;// 15
	private String wikiDataId;// 16

	public static List<City> loadCityData() {
		String path = "C:\\Devprograms\\java-stream-countries-states-cities-database-master\\java-stream-countries-states-cities-database-master\\csv\\cities.csv";
		try {
			return Files.lines(new File(path).toPath())// Stream<String>
					.skip(1) // Stream<String>
					.parallel() // 병렬로 처리 시작.
					.map(City::new) // Stream<City>
					.toList(); // List<City>
		} catch (IOException e) {
			return null;
		}
	}

	public City(String cityLineString) {
		// CSV : Comma Separate Value
		String[] values = cityLineString.split(",");

		this.id = StringUtil.toInt(ArrayUtil.getValue(values, 0));
		this.name = ArrayUtil.getValue(values, 1);
		this.stateId = StringUtil.toInt(ArrayUtil.getValue(values, 2));
		this.stateCode = ArrayUtil.getValue(values, 3);
		this.stateName = ArrayUtil.getValue(values, 4);
		this.countryId = StringUtil.toInt(ArrayUtil.getValue(values, 5));
		this.countryCode = ArrayUtil.getValue(values, 6);
		this.countryName = ArrayUtil.getValue(values, 7);
		this.latitude = ArrayUtil.getValue(values, 8);
		this.longitude = ArrayUtil.getValue(values, 9);
		this._native = ArrayUtil.getValue(values, 10);
		this.type = ArrayUtil.getValue(values, 11);
		this.level = StringUtil.toInt(ArrayUtil.getValue(values, 12));
		this.parentId = StringUtil.toInt(ArrayUtil.getValue(values, 13));
		this.population = StringUtil.toInt(ArrayUtil.getValue(values, 14));
		this.timezone = ArrayUtil.getValue(values, 15);
		this.wikiDataId = ArrayUtil.getValue(values, 16);

	}

	// getter만
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStateId() {
		return stateId;
	}

	public String getStateCode() {
		return stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public int getCountryId() {
		return countryId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String get_native() {
		return _native;
	}

	public String getType() {
		return type;
	}

	public int getLevel() {
		return level;
	}

	public int getParentId() {
		return parentId;
	}

	public int getPopulation() {
		return population;
	}

	public String getTimezone() {
		return timezone;
	}

	public String getWikiDataId() {
		return wikiDataId;
	}

}
