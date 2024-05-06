package br.com.projeto.data.vo.v1;

import java.io.Serializable;

public class PersonVO implements Serializable{

	
		private static final long serialVersionUID = 1L;

		private Long id;
		private String firstName;
		private String lastName;
		private String gender;
		private String endereco;

		
		public PersonVO() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getEndereco() {
			return endereco;
		}

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}





}
