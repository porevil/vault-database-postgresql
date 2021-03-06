/*
 * Copyright 2016-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.annotation.VaultPropertySource;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultResponse;
/**
 * Sample Application using Spring Cloud Vault with Token authentication. Vault will
 * obtain PostgreSQL credentials to be used with a {@link javax.activation.DataSource}.
 *
 * @author Natapong S.
 */
@SpringBootApplication
public class PostgreSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostgreSqlApplication.class, args);
	}

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void postConstruct() throws Exception {

		System.out.println("##########################");
         
		try {Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement(); 

			ResultSet resultSet = statement.executeQuery("SELECT * FROM artworks;");
			resultSet.next();

			System.out.println("Connection works with User: " + resultSet.getString("title"));

			resultSet.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		System.out.println("##########################");
	}
}
